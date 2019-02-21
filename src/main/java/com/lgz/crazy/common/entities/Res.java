package com.lgz.crazy.common.entities;

/**
 * Created by lgz on 2019/2/20.
 */
public class Res<T> {
    public static final String SUCCESSMSG = "success";
    public static final String FAILMSG = "fail";
    public static final String NOLOGINMSG = "noLogin";
    public static final String EXCEPTMSG = "exception";
    public static final Integer  SUCCESSSTATUS = 1;
    public static final Integer FAILSTATUS = 0;
    public static final Integer EXCEPTSTATUS = -1;
    public static final Integer NOLOGINSTATUS = 2;

    private String msg;
    private Integer status;
    private T data;

    public static Res getSuccessResult() {
        Res result = new Res();
        result.setMsg(SUCCESSMSG);
        result.setStatus(SUCCESSSTATUS);
        return result;
    }

    public static <T> Res getSuccessResult(T t) {
        Res result = new Res();
        result.setMsg(SUCCESSMSG);
        result.setStatus(SUCCESSSTATUS);
        result.setData(t);
        return result;
    }

    public static Res getFailedResult() {
        Res result = new Res();
        result.setMsg(FAILMSG);
        result.setStatus(FAILSTATUS);
        return result;
    }

    public static Res getFailedResult(String msg) {
        Res result = new Res();
        result.setMsg(msg);
        result.setStatus(FAILSTATUS);
        return result;
    }

    public static Res getExceptResult(String msg) {
        Res result = new Res();
        if(msg==null)msg=EXCEPTMSG;
        result.setMsg(msg);
        result.setStatus(EXCEPTSTATUS);
        return result;
    }

    public static Res getNoLoginResult() {
        Res result = new Res();
        result.setMsg(NOLOGINMSG);
        result.setStatus(NOLOGINSTATUS);
        return result;
    }

    public static <T> Res getCommonResult(T obj) {
        Res result;
        if(obj == null){
            result = getFailedResult();
        }else{
            result = getSuccessResult();
            result.setData(obj);
        }
        return result;
    }

    public String getMsg() {
        return msg;
    }
    public Res setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
