package com.smart.activiti.test;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitiTest03 {

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
     * 部署流程 部署的id:125001
     * 流程定义key:gov-process:1:125004
     */
    @Test
    public void testDeploy() {
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("process/政府审核.bpmn").deploy();
        System.out.println("本次部署信息:" + deploy);
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcessInstance() {
        //查出所有流程,看哪一个是要启动的
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("gov-process:1:125004");
        System.out.println("流程启动成功" + processInstance);
    }

    @Test
    public void testTaskAssignee() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("qinqingjie").list();
        for (Task task : list) {
            System.out.println("任务信息:" + task.getId());
            System.out.println("任务名:" + task.getName());
            //完成任务
            Map<String, Object> map = new HashMap<>();
            map.put("isFoodCompany", false);
            map.put("money", 1000001);
            map.put("isCurrent", true);
            taskService.complete(task.getId(), map);
        }
    }

    @Test
    public void test04() {
        List<Task> tasks = taskService.createTaskQuery().processDefinitionId("gov-process:1:125004").list();
        for (Task task : tasks) {
            System.out.println("任务信息:" + task.getId());
            System.out.println("任务名:" + task.getName());
            taskService.complete(task.getId());
        }
        long count = historyService.createHistoricProcessInstanceQuery().processDefinitionId("gov-process:1:125004").finished().count();
        System.out.println(count);
    }

}
