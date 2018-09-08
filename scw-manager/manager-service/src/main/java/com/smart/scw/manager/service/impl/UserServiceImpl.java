package com.smart.scw.manager.service.impl;

import com.smart.project.MyStringUtils;
import com.smart.scw.manager.bean.TUser;
import com.smart.scw.manager.bean.TUserToken;
import com.smart.scw.manager.dao.TUserMapper;
import com.smart.scw.manager.dao.TUserTokenMapper;
import com.smart.scw.manager.service.UserService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private TUserTokenMapper tUserTokenMapper;

    @Override
    public boolean isLoginacctExist(String loginacct) {
        return tUserMapper.findTUserByLoginacct(loginacct) == 1;
    }

    @Override
    public boolean isEmailExist(String email) {
        return tUserMapper.findTuserByEmail(email) == 1;
    }

    @Override
    public Map<String, Object> check(BindingResult bindingResult, Map<String, Object> map) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }

    @Override
    public void addRegist(TUser user) {
        String password = user.getUserpswd();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1);
        password = simpleHash.toString();
        user.setUserpswd(password);
        user.setSalt(salt);
        //设置昵称和创建时间
        //昵称默认是loginacct
        user.setUsername(user.getLoginacct());
        //创建时间默认是String类型
        user.setCreatetime(MyStringUtils.formatDate(new Date()));
        tUserMapper.insertTUser(user);
    }

    @Override
    public boolean isAuthenticated(TUser user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getLoginacct(), user.getUserpswd());
        usernamePasswordToken.setRememberMe(user.isRememberMe());
        subject.login(usernamePasswordToken);
        return subject.isAuthenticated();
    }

    @Override
    public TUser findTUserByLoginacct(String loginacct) {
        return tUserMapper.selectTUserByLoginacct(loginacct);
    }

    @Override
    public List<TUser> getAll() {
        return tUserMapper.selectAll();
    }

    @Override
    public List<TUser> getAllByCondition(String search) {
        return tUserMapper.selectAllByCondition(search);
    }

    @Override
    public void deleteBatchOrSingle(String ids) {
        if (ids.contains(",")) {
            //进行批量删除
            List<Integer> list = new ArrayList<>();
            String[] split = ids.split(",");
            for (String s : split) {
                list.add(Integer.parseInt(s));
            }
            tUserMapper.deleteTuserByIds(list);
        } else {
            //按照id进行删除
            tUserMapper.deleteTUserById(Integer.parseInt(ids));
        }
    }

    @Override
    public boolean isSendEmail(String email) {
        TUser tUser = tUserMapper.selectByEmail(email);
        if (tUser != null) {
            //发送邮件
            // 1 生成token
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            // 2 给数据库保存token
            //先查询数据库有没有令牌记录
            List<TUserToken> list = tUserTokenMapper.selectTUserTokenByUserId(tUser.getId());
            if (list != null && list.size() > 0) {
                TUserToken userToken = list.get(0);
                userToken.setPswToken(token);
                //将令牌更新到数据库
                tUserTokenMapper.updateTokenByUserId(tUser.getId(), token);
            } else {
                //添加用户id和令牌进数据库
                TUserToken userToken = new TUserToken();
                userToken.setUserid(tUser.getId());
                userToken.setPswToken(token);
                tUserTokenMapper.insert(tUser.getId(), token);
            }
            //3 把带token的连接发给用户
            HtmlEmail htmlEmail = new HtmlEmail();
            htmlEmail.setHostName("localhost");
            htmlEmail.setSmtpPort(25);
            //设置连接服务器的账号
            htmlEmail.setAuthentication("qinqingjie@atguigu.com", "123456");
            try {
                //设置发送给谁
                Email addTo = htmlEmail.addTo(email);
                //设置来源
                htmlEmail.setFrom("qinqingjie@atguigu.com");
                //设置邮件标题
                htmlEmail.setSubject("找回密码");
                //设置邮件内容
                htmlEmail.setContent("<h1>半小时内点击密码重置连接</h1><a href='http://127.0.0.1:8080/scw/permission/user/getpwd?token=" + token + "'>重置密码</a>", "text/html;charset=utf-8");
                //发送邮件
                htmlEmail.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public Integer getUserIdByToken(String token) {
        return tUserTokenMapper.selectUserIdByToken(token);
    }

    @Override
    public boolean updatePasswordByUserId(String password, String token) {
        //将密码进行md5加密
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1);
        password = simpleHash.toString();
        Integer userid = getUserIdByToken(token);
        //令牌一旦使用后,就直接移除,把令牌设置为空
        tUserTokenMapper.updateTokenByUserId(userid, null);
        return tUserMapper.updatePasswordByUserId(userid, password, salt) > 0;
    }

}
