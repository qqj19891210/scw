package com.smart.scw.portal.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.scw.manager.bean.TMember;
import com.smart.scw.portal.bean.ScwReturn;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.net.URI;

public class HttpCilentTest {

    @Test
    public void test01() throws Exception {
        //1请求参数带在问号后面
        // HttpGet get = new HttpGet("http://localhost:8082/scw-restapi/member/regist");

        //2 构造请求地址和参数的形式
        URI uri = new URIBuilder().setScheme("http").setHost("localhost:8082")
                .setPath("/scw-restapi/member/regist")
                .setParameter("loginacct", "qqj19891210")
                .setParameter("userpswd", "123456")
                .setParameter("email", "283271603@qq.com").build();
        // 1 创建一个get请求
        HttpGet get = new HttpGet(uri);
        // 2 创建出一个发送请求的对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 3 发送请求,得到响应
        CloseableHttpResponse response = client.execute(get);
        // 4 查看响应对象(包含响应头,响应体等所有内容)
        HttpEntity entity = response.getEntity();
        //拿到响应内容
        InputStream content = entity.getContent();
        //String s = IOUtils.toString(content, "utf-8");
        String s1 = EntityUtils.toString(entity, "utf-8");
        //System.out.println("io打印:" + s);
        //System.out.println("自带打印:" + s1);
        JSONObject object = JSON.parseObject(s1);
        ScwReturn<TMember> scwReturn = JSON.parseObject(String.valueOf(object.get("msg")), ScwReturn.class);
        System.out.println(scwReturn);
        //释放资源
        response.close();
        client.close();
    }

}
