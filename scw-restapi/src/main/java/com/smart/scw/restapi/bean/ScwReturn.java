package com.smart.scw.restapi.bean;

import java.util.Map;

public class ScwReturn<T> {

    private int code;//状态码：0表示失败 1表示成功

    private String msg;//要给页面的提示信息

    private Map<String,Object> ext;//额外的数据

    private T content;//响应的内容


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    //两个快速成功失败的方法
    public static <T> ScwReturn<T> success(String msg,T content,Map<String,Object> ext){
        ScwReturn<T> scwReturn=new ScwReturn<>();
        scwReturn.setCode(1);
        scwReturn.setMsg(msg);
        scwReturn.setContent(content);
        scwReturn.setExt(ext);
        return scwReturn;
    }

    //两个快速成功失败的方法
    public static <T> ScwReturn<T> fail(String msg,T content,Map<String,Object> ext){
        ScwReturn<T> scwReturn=new ScwReturn<>();
        scwReturn.setCode(0);
        scwReturn.setMsg(msg);
        scwReturn.setContent(content);
        scwReturn.setExt(ext);
        return scwReturn;
    }

}
