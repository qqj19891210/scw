package com.smart.scw.manager.controller.audi;

import com.smart.scw.manager.bean.TMember;
import com.smart.scw.manager.bean.TMemberCert;
import com.smart.scw.manager.service.MemberCertService;
import com.smart.scw.manager.service.MemberService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实名审核控制器
 */
@Controller
@RequestMapping("/audi/auth")
public class AudiController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCertService memberCertService;

    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        // 1领取实名认证任务
        List<Task> tasks = taskService.createTaskQuery().taskName("人工审核").list();
        //根据当前任务的流程实例id,找出是哪个任务申请的,去查工单表
        //根据工单表中的会员id找出会员信息
        List<Map<String, Object>> pageResult = new ArrayList<>();
        for (Task task : tasks) {
            Map<String, Object> map = new HashMap<>();
            //先放一些流程信息
            map.put("taskId", task.getId());
            map.put("processId", task.getProcessInstanceId());
            // 2放一些数据信息
            TMember member = memberService.getMemberByTicket(task.getProcessInstanceId());
            map.put("member", member);
            //查资质图片信息
            List<TMemberCert> certs = memberCertService.getCertsByTicket(task.getProcessInstanceId());
            map.put("certs",certs);
            pageResult.add(map);
        }
        modelMap.addAttribute("pageInfo",pageResult);
        return "manager/audi/auth_cert";
    }

}
