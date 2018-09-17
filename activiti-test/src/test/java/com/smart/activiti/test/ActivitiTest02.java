package com.smart.activiti.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitiTest02 {
    //在类路径下找activiti.cfg.xml的配置文件来初始化流程引擎
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    FormService formService;

    HistoryService historyService;

    IdentityService identityService;

    ManagementService managementService;

    //部署流程,查询部署信息
    RepositoryService repositoryService;

    RuntimeService runtimeService;

    TaskService taskService;

    //组件初始化
    @BeforeClass
    public void serviceInit() {
        //表单组件 没用
        formService = engine.getFormService();
        //查询流程历史记录信息的,历史组件
        historyService = engine.getHistoryService();
        //用户模块组件,CRUD用户以及一些用户分组信息
        identityService = engine.getIdentityService();
        //管理组件,监听流程,定时任务等
        managementService = engine.getManagementService();
        //持久化服务组件,保存流程定义信息等
        repositoryService = engine.getRepositoryService();
        //运行时服务组件,查询流程运行期间一些信息,控制流程等
        runtimeService = engine.getRuntimeService();
        //任务服务组件,完成任务,任务签收等操作
        taskService = engine.getTaskService();
    }

    @Test
    public void test() {
        //部署这个流程
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("process/请假带变量版.bpmn").name("带变量的流程部署").deploy();
        System.out.println("部署的信息:" + deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void testStart() {
        //启动流程
        //启动时设置一些流程变量,方便下个任务获取
        Map<String, Object> map = new HashMap<>();
        map.put("assigneer", "lisi");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("myProcess001:8:107504", map);
        System.out.println("流程启动完成" + processInstance);
    }

    @Test
    public void testTask() {
        //使用lisi获取到它要执行的任务
        Task task = taskService.createTaskQuery().processDefinitionId("myProcess001:8:107504").taskAssignee("lisi").singleResult();
        //任务由指定人去处理就不用签收
        Map<String, Object> map = new HashMap<>();
        map.put("days", 2);
        map.put("reasons", "生病了");
        map.put("requester", "lisi");
        System.out.println("任务信息:" + task.getName() + "==>" + task.getId());
        taskService.complete(task.getId(), map);
        System.out.println("完成任务");
    }

    @Test
    public void test01() {
        //组长审批
        List<Task> tasks = taskService.createTaskQuery().processDefinitionId("myProcess001:8:107504").list();
        for (Task task : tasks) {
            System.out.println("任务信息:" + task.getName() + "==>" + task.getId());
        }
        List<Task> tasks1 = taskService.createTaskQuery().taskAssignee("wangwu").list();
        for (Task task : tasks1) {
            System.out.println("任务信息:" + task.getName() + "==>" + task.getId());
            taskService.complete(task.getId());
        }
        List<Task> task2 = taskService.createTaskQuery().processDefinitionId("myProcess001:8:107504").list();
        System.out.println("还有任务吗?"+task2);
    }

}
