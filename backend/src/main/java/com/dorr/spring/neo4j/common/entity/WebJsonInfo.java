package com.dorr.spring.neo4j.common.entity;

import com.dorr.spring.neo4j.common.utils.HttpStatusEnum;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

// 通过getter setter转化json
public class WebJsonInfo<T> implements Serializable {


    /**
     * 错误码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体的内容
     */
    private T data;

    /**
     * 成功时候的调用
     * @param <T>
     * @return
     */
    public static <T> WebJsonInfo<T> success(T data) {
        return new WebJsonInfo<>("200","成功",data);
    }

    public static <T> WebJsonInfo<T> success() {
        return new WebJsonInfo("200","执行成功");
    }

    /**
    public static <T> WebJsonInfo<T> build(HttpStatusEnum WebJsonInfoEnum) {
        return new WebJsonInfo<T>(WebJsonInfoEnum);
    }

    /**
     * 根据 code， 和  msg 构建返回结果
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> WebJsonInfo<T> build(String code, String msg) {
        return new WebJsonInfo<T>(code, msg);
    }

    /**
     * 根据 code， 和  msg, 和 data 构建返回结果
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> WebJsonInfo<T> build(String code, String msg, T data) {
        return new WebJsonInfo<T>(code, msg, data);
    }

    /**
     * 失败的调用
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> WebJsonInfo<T> error(String codeMsg) {
        return new WebJsonInfo<>(codeMsg);
    }

    /**
     * 失败的调用 将返回结果传入
     * @param data
     * @param <T>
     * @return
     */
    public static <T> WebJsonInfo<T> error(T data) {
        return new WebJsonInfo<>(HttpStatusEnum.ERROR.code()+"", HttpStatusEnum.ERROR.reasonPhraseCN(), data);
    }

    private WebJsonInfo(List<T> data) {
        this.code = HttpStatusEnum.SUCCESS.code()+"";
        this.msg = HttpStatusEnum.SUCCESS.reasonPhraseCN();
        PageInfo<T> pageInfo = new PageInfo<>(data);


    }

    private WebJsonInfo(T data,Integer pageSize,Integer pageInfo) {
        this.code = HttpStatusEnum.SUCCESS.code()+"";
        this.msg = HttpStatusEnum.SUCCESS.reasonPhraseCN();

        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private WebJsonInfo(String msg) {
        this.code = HttpStatusEnum.ERROR.code()+"";
        this.data = null;
        this.msg = msg;
    }

    private WebJsonInfo(HttpStatusEnum WebJsonInfoEnum) {
        this.code = WebJsonInfoEnum.code()+"";
        this.msg = WebJsonInfoEnum.reasonPhraseCN();
    }

    private WebJsonInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private WebJsonInfo(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
