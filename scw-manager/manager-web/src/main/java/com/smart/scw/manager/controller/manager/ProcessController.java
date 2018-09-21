package com.smart.scw.manager.controller.manager;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.github.pagehelper.PageInfo;
import com.smart.scw.manager.exception.CustomException.DeployFailException;
import com.smart.scw.manager.exception.CustomException.ShowProcessImgException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理流程上传部署的Controller
 */

@Controller
@RequestMapping("/processCtrl/process")
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("/list")
    public String list() {
        return "manager/servicemanager/process";
    }

    @RequestMapping("/json")
    @ResponseBody
    public PageInfo getProJson(@RequestParam("pn") Integer pageNumber) {
        //整合流程框架,查到所有的流程信息,并且使用activiti的分页方法
        List<ProcessDefinition> listAll = repositoryService.createProcessDefinitionQuery().list();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(listAll.size());
        //设置总页数
        int pages = (int) (pageInfo.getTotal() % 5 == 0 ? pageInfo.getTotal() / 5 : (pageInfo.getTotal() / 5 + 1));
        pageInfo.setPages(pages);
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageNumber > pages) {
            pageNumber = pages;
        }
        //每页第一个数据显示的行号
        int startRow = (pageNumber - 1) * 5 < 0 ? 0 : (pageNumber - 1) * 5;
        pageInfo.setStartRow(startRow);
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().listPage(pageInfo.getStartRow(), 5);
        List<Map<String, Object>> result = new ArrayList<>();
        for (ProcessDefinition processDefinition : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", processDefinition.getId());
            map.put("name", processDefinition.getName());
            map.put("key", processDefinition.getKey());
            map.put("version", processDefinition.getVersion());
            result.add(map);
        }
        //设置总记录数
        pageInfo.setList(result);
        //设置当前页
        pageInfo.setPageNum(pageNumber > pages || pageNumber < 1 ? pages : pageNumber);
        //设置当前页的数量
        pageInfo.setSize(list.size());
        //设置前一页
        pageInfo.setPrePage(pageNumber - 1 > 0 ? pageNumber - 1 : 1);
        //设置后一页
        pageInfo.setNextPage(pageNumber + 1 > pageInfo.getPages() ? pageInfo.getPages() : pageNumber + 1);
        //是否为第一页
        pageInfo.setIsFirstPage(pageInfo.getPageNum() == 1);
        //是否为最后一页
        pageInfo.setIsLastPage(pageInfo.getPageNum() == pageInfo.getPages());
        //是否有前一页
        pageInfo.setHasPreviousPage(pageNumber > 1);
        //是否有后一页
        pageInfo.setHasNextPage(pageNumber < pageInfo.getPages());
        //设置导航页码数
        pageInfo.setNavigatePages(pageInfo.getPages());
        //所有导航页号
        int[] number = new int[pageInfo.getNavigatePages()];
        for (int i = 0; i < number.length; ++i) {
            number[i] = i + 1;
        }
        pageInfo.setNavigatepageNums(number);
        return pageInfo;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public FastJsonJsonView upload(@RequestParam("processFile") MultipartFile file) throws DeployFailException {
        FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
        Map<String, Object> result = new HashMap<>();
        if (!file.getOriginalFilename().endsWith(".bpmn")) {
            throw new DeployFailException("上传文件不符合格式");
        }
        //部署流程
        try {
            Deployment deploy = repositoryService.createDeployment().addInputStream(file.getOriginalFilename(), file.getInputStream()).deploy();
            result.put("deploySuccess", "文件部署成功");
            fastJsonJsonView.setAttributesMap(result);
            return fastJsonJsonView;
        } catch (IOException e) {
            throw new DeployFailException();
        }
    }

    //使用部署id能获取到流程图
    //按照流程id找到部署id,按照部署id查询流程图的二进制信息
    @RequestMapping("/img")
    @ResponseBody
    public byte[] getProcessImg(@RequestParam("pdId") String processDefinitionId) throws ShowProcessImgException {
        //获取流程定义的数据库信息,通过其找到流程部署id
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        //拿到流程部署id
        String deploymentId = processDefinition.getDeploymentId();
        //按照部署id查出图片名字
        List<String> deploymentResourceNames = repositoryService.getDeploymentResourceNames(deploymentId);
        String pngName = "";
        for (String s : deploymentResourceNames) {
            if (s.endsWith(".png")) {
                pngName = s;
            }
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, pngName);
        try {
            byte[] bytes = IOUtils.toByteArray(resourceAsStream);
            resourceAsStream.close();
            return bytes;
        } catch (IOException e) {
            throw new ShowProcessImgException();
        }
    }

}
