package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TPermission;
import com.smart.scw.manager.bean.TRole;
import com.smart.scw.manager.bean.TUser;
import com.smart.scw.manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

@ContextConfiguration("classpath*:/applicationContext.xml")
@Rollback(value = false)
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TPermissionService tPermissionService;

    @Autowired
    private TRoleService tRoleService;

    @Autowired
    private TUserRoleService tUserRoleService;

    @Autowired
    private TRolePermissionService tRolePermissionService;

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

    @Test
    public void test1(){
        List<TPermission> list=tPermissionService.getAllMenus();
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void test2(){
        List<TUser> list=userService.getAllByCondition("q");
        System.out.println(list);
    }

    @Test
    public void test3(){
        userService.deleteBatchOrSingle("86,87,88");
    }

    @Test
    public void test4(){
        List<TRole> list=tRoleService.getAllRoles();
        System.out.println(list);
    }

    @Test
    public void test5(){
        List<TRole> list=tRoleService.getUserRoles(45);
        System.out.println(list);
    }

    @Test
    public void test6(){
        tUserRoleService.addRoles("6,7,8",45);

    }

    @Test
    public void test7(){
        tUserRoleService.deleteRoles("1,2,8",45);
    }

    @Test
    public void test8(){
        List<TRole> roles=tRoleService.getAllByCondition("");
        System.out.println(roles);
    }

    @Test
    public void test9(){
        List<TPermission> list=tPermissionService.getRolePermission(1);
        System.out.println(list);
    }

    @Test
    public void test10(){
        tRolePermissionService.updateRolePermission(null,1);
    }

    @Test
    public void test11(){
        tRolePermissionService.updateRolePermission("2,3,8",1);
    }

    @Test
    public  void test12(){
        boolean flag=userService.isSendEmail("zhangsan@atguigu.com");
        System.out.println(flag);
    }

    @Test
    public void test13(){
        int x=userService.getUserIdByToken("b77e3a432af14ac0b2f679b9fc3470fc");
        System.out.println(x);
    }

    @Test
    public void test14(){
        boolean flag=userService.updatePasswordByUserId("123456","b77e3a432af14ac0b2f679b9fc3470fc");
        System.out.println(flag);
    }

}
