package com.smart.scw.restapi.service.impl;

import com.smart.scw.manager.bean.TMember;
import com.smart.scw.manager.bean.TMemberTicket;
import com.smart.scw.manager.dao.TMemberMapper;
import com.smart.scw.manager.dao.TMemberTicketMapper;
import com.smart.scw.restapi.service.MemberService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private TMemberMapper memberMapper;

    @Autowired
    private TMemberTicketMapper memberTicketMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public TMember addRegist(TMember tMember) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        String password = tMember.getUserpswd();
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1);
        password = simpleHash.toString();
        tMember.setUserpswd(password);
        tMember.setSalt(salt);
        tMember.setUsername(tMember.getLoginacct());
        //实名认证状态:0代表未实名认证,1代表实名认证
        tMember.setAuthstatus("0");
        //真实姓名:实名认证时保存
        tMember.setRealname("未实名");
        //用户类型(暂时不用) 0 普通会员 1 月费会员 2 年费会员
        tMember.setUsertype("0");
        //身份证号,账户类型(实名认证时做的)
        //账户类型,直接保存账户全名
        //保存进数据库
        int i = memberMapper.insert(tMember);
        //刚才就是按照这个对象给数据库插入值,希望获取到数据库分配的自增主键
        return tMember;
    }

    @Override
    public boolean isEmailExist(String email) {
        return memberMapper.selectByEmail(email) > 0;
    }

    @Override
    public boolean isLoginacctExist(String loginacct) {
        return memberMapper.selectByLoginacct(loginacct) > 0;
    }

    @Override
    public TMember findTMemberByLoginacct(String loginacct) {
        return memberMapper.selectTMemberByLoginacct(loginacct);
    }

    @Override
    public boolean isAuthenticated(TMember member) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(member.getLoginacct(), member.getUserpswd());
        subject.login(usernamePasswordToken);
        return subject.isAuthenticated();
    }

    /**
     * 根据id更新已经携带的字段
     *
     * @param member
     * @return
     */
    @Override
    public boolean updateBaseInfo(TMember member) {
        int primaryKey = memberMapper.updateByPrimaryKey(member);
        return primaryKey > 0;
    }

    @Override
    public boolean saveEmail(TMember member) {
        int i = memberMapper.updateEmailByMemberId(member.getEmail(), member.getId());
        return i > 0;
    }

    @Override
    public void sendEmail(String email, String username,Integer memberid) {
        //启动流程框架的流程发送任务,查询最新版流程定义信息
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("auth-process").latestVersion().singleResult();
        //根据流程定义的id产生流程实例,并把每个任务里定义的一些数据传入
        Map<String, Object> data = new HashMap<>();
        data.put("memberemail", email);
        data.put("member", username);
        String code = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        data.put("code", code);
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), data);
        //保存流程实例信息到数据库
        //保存用户对应的流程实例id,我们需要看走到哪一步流程,我们就去流程框架里按照实例id查询即可
        //需要创建一个用户工单表
        TMemberTicket tMemberTicket=new TMemberTicket();
        tMemberTicket.setMemberid(memberid);
        tMemberTicket.setTicketid(processInstance.getId());
        memberTicketMapper.insert(tMemberTicket);
    }

}
