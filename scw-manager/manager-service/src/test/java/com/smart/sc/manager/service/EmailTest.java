package com.smart.sc.manager.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.Test;

public class EmailTest {

    /**
     * 测试用commons-email操作发送和接受邮件
     */
    @Test
    public void emailTest() throws EmailException {
        SimpleEmail simpleEmail=new SimpleEmail();
        //设置远程服务器主机名
        simpleEmail.setHostName("smtp.163.com");
        //设置登录远程服务器的账号和密码
        simpleEmail.setAuthentication("13523637650@163.com","qqj13523637650");
        //编写一个邮件
        //设置发送给谁
        simpleEmail.addTo("283271603@qq.com");
        //设置这个邮件来源于哪里
        simpleEmail.setFrom("13523637650@163.com");
        //设置邮件主题
        simpleEmail.setSubject("这是一封测试邮件");
        //设置邮件的内容
        simpleEmail.setMsg("呵呵");
        //邮件发送
        simpleEmail.send();
    }

}
