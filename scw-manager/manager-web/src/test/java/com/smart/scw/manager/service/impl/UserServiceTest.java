package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TUser;
import com.smart.scw.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration("classpath*:/applicationContext.xml")
@Rollback(value = false)
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        TUser user=new TUser();
        for(int i=0;i<3;i++){
            if(i==2){
                user.setLoginacct(null);
            }else{
                user.setLoginacct("qinqingjie"+i);
            }
            user.setUsername("qinqingjie"+i);
            user.setUserpswd("123456");
            user.setEmail("283271603@qq.com");
            userService.addRegist(user);
        }
    }

}
