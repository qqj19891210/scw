package com.smart.activiti.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ActivitiTest {

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
        System.out.println("流程引擎:" + engine);
        System.out.println("其他组件:");
    }

    /**
     * 测试定义一个流程,
     * 并把流程信息保存在数据库中
     * 1 每次流程图修改以后,都需要重新部署流程引擎才能用到新的流程
     * 2 以前旧的流程都是存在数据库中,新的流程也要存在数据库中
     * 3 每次部署都会保存一个新的流程定义:act_re_procdef
     * 4 同一流程多次部署相当于只是版本叠加
     * 5 流程定义的key是用来识别版本是否叠加的
     */
    @Test
    public void testProcessDefinitionDeployee() {
        //如何定义一个流程(画流程图)
        //部署定义好的流程
        //部署就是去数据库保存这个流程定义好的信息
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("process/请假流程.bpmn").deploy();
        System.out.println("部署的流deploy程对象:" + deploy);
    }

    /**
     * 查询所有流程信息
     */
    @Test
    public void testQueryProcessDefinition() {
        //查询部署信息
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        //查流程定义 每次部署都有一个部署id
        //也可以用流程唯一标识
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        long count = processDefinitionQuery.count();
        System.out.println("流程的数量:" + count);
        System.out.println();
        //拿到数据库中所有流程定义信息
        List<ProcessDefinition> list = processDefinitionQuery.list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程id(流程框架生成的):" + processDefinition.getId());
            System.out.println("流程name(画流程时的流程名):" + processDefinition.getName());
            System.out.println("流程的key(画流程时的id):" + processDefinition.getKey());
            System.out.println("流程的资源信息:" + processDefinition.getResourceName() + "==>" + processDefinition.getDiagramResourceName());
            System.out.println("流程版本号:" + processDefinition.getVersion());
            System.out.println("=============================");
        }
        System.out.println("精确查询");
        //按照流程id,按照流程的key,按照流程的版本
        ProcessDefinitionQuery definitionQuery = processDefinitionQuery.deploymentId("40001");
        System.out.println(definitionQuery);
        //拿到流程的最新版本
        // ProcessDefinition singleResult = processDefinitionQuery.processDefinitionKeyLike("myProcess_1").latestVersion().singleResult();
        // System.out.println("版本:" + singleResult.getVersion() + "==>" + singleResult.getKey() + "==>" + singleResult.getName());
        long myProcess_1 = processDefinitionQuery.processDefinitionKeyLike("myProcess_1").count();
        System.out.println("myProcess_1:" + myProcess_1);
    }

    /**
     * 一个流程定义可以创建多个流程实例
     */
    @Test
    public void test04() {
        //启动一个请假流程
        //查出要启动流程的信息
        ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery().
                processDefinitionKey("myProcess_1").latestVersion().singleResult();
        System.out.println("流程的信息:" + singleResult.getId() + "==>" + singleResult.getName() + "==>" + singleResult.getKey());
        //启动这个流程,启动以后返回流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(singleResult.getId());
        System.out.println("流程启动成功" + processInstance.getActivityId());
        System.out.println("流程启动成功" + processInstance.getProcessDefinitionId());
    }

    /**
     * 测试任务
     * 1 查询所有任务
     * 2 将任务签收(领任务完成任务)
     */
    @Test
    public void testTask() {
        //查出当前系统有哪些任务
        //可以查某个流程走在了哪个任务:taskService.createTaskQuery().processDefinitionId("");
        //可以查某个人现在需要做哪些任务(前提是任务分配给了这些人):
        //某个流程定义的所有任务
        List<Task> tasks = taskService.createTaskQuery().processDefinitionId("myProcess_1:3:45004").list();
        for (Task task : tasks) {
            System.out.println("当前的任务信息:" + task.getId());
            System.out.println("任务分给哪个人(由哪个人来受理):" + task.getAssignee());
            System.out.println("任务名:" + task.getName());
            String assignee = "zhangsan";
            System.out.println("任务签收:" + assignee + "签收了" + task.getId());
            taskService.claim(task.getId(), assignee);
            System.out.println("===================");
        }
        System.out.println("张三所要执行的任务...");
        List<Task> list = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        for (Task task : list) {
            System.out.println("当前的任务信息:" + task.getId());
            System.out.println("任务分给哪个人(由哪个人来受理):" + task.getAssignee());
            System.out.println("任务名:" + task.getName());
        }
    }

    /**
     * 测试完成任务
     * 任务的相关操作都是使用taskService
     * claim和complete 签收和完成
     */
    @Test
    public void testTaskComplete() {
        // 1 查出张三拥有的任务
        long count = taskService.createTaskQuery().taskAssignee("zhangsan").count();
        System.out.println("任务数量:" + count);
        System.out.println("==================================");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        for (Task task : tasks) {
            //完成任务
            System.out.println("当前的任务信息:" + task.getId());
            System.out.println("任务名:" + task.getName());
            System.out.println("张三完成任务" + task.getId());
            taskService.complete(task.getId());
        }
        System.out.println("==================================");
        long count1 = taskService.createTaskQuery().taskAssignee("zhangsan").count();
        System.out.println("任务数量:" + count1);
    }

    @Test
    public void testProcessTask() {
        //当前流程定义执行到哪个任务了
        List<Task> tasks = taskService.createTaskQuery().processDefinitionId("myProcess_1:3:45004").list();
        for (Task task : tasks) {
            System.out.println("当前的任务信息:" + task.getId());
            System.out.println("任务名:" + task.getName());
            taskService.complete(task.getId());
        }
        System.out.println("===================================");
        List<Task> tasks1 = taskService.createTaskQuery().processDefinitionId("myProcess_1:3:45004").list();
        for (Task task1 : tasks1) {
            System.out.println("当前的任务信息:" + task1.getId());
            System.out.println("任务名:" + task1.getName());
            taskService.complete(task1.getId());
        }
        System.out.println("===================================");
        List<Task> tasks2 = taskService.createTaskQuery().processDefinitionId("myProcess_1:3:45004").list();
        for (Task task2 : tasks2) {
            System.out.println("当前的任务信息:" + task2.getId());
            System.out.println("任务名:" + task2.getName());
            taskService.complete(task2.getId());
        }
    }

}
