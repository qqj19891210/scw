package com.smart.scw.restapi.controller.auth;

import com.smart.scw.manager.bean.TMember;
import com.smart.scw.manager.bean.TMemberCert;
import com.smart.scw.manager.bean.TMemberTicket;
import com.smart.scw.restapi.bean.ScwReturn;
import com.smart.scw.restapi.exception.CustomException.*;
import com.smart.scw.restapi.service.MemberCertService;
import com.smart.scw.restapi.service.MemberService;
import com.smart.scw.restapi.service.MemberTicketService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCertService memberCertService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private MemberTicketService memberTicketService;

    /**
     * member接受到的参数有id 账户类型id 身份证,实名,电话号码
     *
     * @param member
     * @return
     */
    @RequestMapping("/baseInfo")
    public Map<String, Object> auth(TMember member) throws UpdateBaseInfoException {
        Map<String, Object> map = new HashMap<>();
        //按照id更新部分认证信息
        boolean result = memberService.updateBaseInfo(member);
        if (result) {
            ScwReturn<Object> success = ScwReturn.success("基本信息已经录入", null, null);
            map.put("msg", success);
            return map;
        }
        throw new UpdateBaseInfoException("信息录入失败请重新提交");
    }

    @RequestMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile[] multipartFiles,
                                      @RequestParam("certid") String[] certids,
                                      @RequestParam("memberid") String memberid,
                                      HttpServletRequest request) throws UploadException, MemberCertFailException {
        try {
            Map<String, Object> map = new HashMap<>();
            List<TMemberCert> memberCerts = new ArrayList<>();
            for (int i = 0; i < certids.length; i++) {
                TMemberCert memberCert = new TMemberCert();
                MultipartFile file = multipartFiles[i];
                String uploadFile = uploadFile("/certsImg", file, request);
                String certid = certids[i];
                memberCert.setMemberid(Integer.parseInt(memberid));
                memberCert.setCertid(Integer.parseInt(certid));
                memberCert.setIconpath(uploadFile);
                memberCerts.add(memberCert);
            }
            //批量存进数据库,删除原有资质,保存新的资质
            memberCertService.addMemberCert(memberCerts);
            ScwReturn<Object> success = ScwReturn.success("保存成功", null, null);
            map.put("msg", success);
            return map;
        } catch (Exception e) {
            throw new MemberCertFailException("资质保存失败");
        }
    }

    /**
     * 处理发邮件的请求
     *
     * @return
     */
    @RequestMapping("/sendEmail")
    public Map<String, Object> saveEmailInfo(TMember member) throws EmailIsExistException, SendEmailException {
        Map<String, Object> result = new HashMap<>();
        //先判断这个邮箱是否存在
        boolean flag = memberService.saveEmail(member);
        if (flag) {
            //如果邮箱有效,就发送邮件,并且启动流程
            try {
                memberService.sendEmail(member.getEmail(), member.getUsername(), member.getId());
                ScwReturn<Object> success = ScwReturn.success("邮件发送成功", null, null);
                result.put("msg", success);
                return result;
            } catch (Exception e) {
                throw new SendEmailException("邮件发送失败");
            }

        }
        throw new EmailIsExistException("邮箱无效,已被其他用户占用");
    }

    //提交申请进行校验的任务
    @RequestMapping("/vcode")
    public Map<String, Object> validateCode(String code, Integer memberid) throws ProcessApplyException {
        Map<String, Object> result = new HashMap<>();
        try {
            //领取用户之前的实名任务,完成任务即可
            //先查询工单表得到流程实例的Id
            TMemberTicket memberTicket = memberTicketService.getAuthTicket(memberid);
            Task task = taskService.createTaskQuery().processInstanceId(memberTicket.getTicketid()).singleResult();
            //完成任务需要给框架提交用户输入的验证码
            Map<String, Object> map = new HashMap<>();
            map.put("membercode", code);
            taskService.complete(task.getId(), map);
            Task otherTask = taskService.createTaskQuery().processInstanceId(memberTicket.getTicketid()).singleResult();
            //判断新查询的人物名与完成前的任务名是否一致,如果一致则证明任务失败
            if (task.getName().equals(otherTask.getName())) {
                throw new ProcessApplyException("申请失败请重新提交验证码");
            }
            ScwReturn<Object> success = ScwReturn.success("申请成功等待审核3-5个工作日", null, null);
            result.put("msg", success);
            return result;
        } catch (Exception e) {
            throw new ProcessApplyException("申请失败请重新提交验证码");
        }
    }

    //文件上传方法
    private String uploadFile(String webPath, MultipartFile file, HttpServletRequest request) throws UploadException {
        String realPath = request.getServletContext().getRealPath(webPath);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filename = uuid + "_file_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(realPath + "/" + filename));
            //返回图片在服务器下的路径
            return webPath + "/" + filename;
        } catch (IOException e) {
            throw new UploadException("文件上传失败");
        }
    }

}
