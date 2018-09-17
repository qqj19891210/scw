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

public class ActivitiTest04 {

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

    /**
     * 部署
     * gov-process:2:135004
     */
    @Test
    public void testDeploy() {
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("process/政府审核01.bpmn").deploy();
        System.out.println("部署完成:" + deploy);
    }

    @Test
    public void testStartProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("gov-process:2:135004");
        System.out.println("流程启动:" + processInstance);
    }

    @Test
    public void testTask() {
        //查询流程有哪些任务
        List<Task> list = taskService.createTaskQuery().processDefinitionId("gov-process:2:135004").list();
        //查询你的任务
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("qinqingjie").list();
        for (Task task : tasks) {
            System.out.println(task);
            //完成任务
            Map<String, Object> map = new HashMap<>();
            map.put("money", 10001);
            taskService.complete(task.getId(), map);
            System.out.println("完成任务" + task);
        }
    }

    @Test
    public void testCompletion() {
        List<Task> list = taskService.createTaskQuery().processDefinitionId("gov-process:2:135004").list();
        for (Task task : list) {
            System.out.println("完成任务:" + task);
            taskService.complete(task.getId());
        }
    }

}
