package com.dorr.spring.neo4j.common.aop;

import java.util.Date;

public class LogEntity {

    private String logId;//日志id
    private String logMethod;//操作菜单名字
    private String logDescription;//描述
    private String logParams;//params
    private String logAddr;//ip地址
    private String logBrowser;//浏览器
    private String logType;//日志类型
    private String userName;//用户名
    private String userPhone;//用户名
    private Long executeTime;//请求耗时(毫秒)
    private byte[] exceptionDetail;//异常详细
    private Date createTime;//日志记录时间创建时间

    public LogEntity(String logId, String logMethod, String logDescription, String logParams, String logAddr, String logBrowser, String logType, String userName, String userPhone, Long executeTime, byte[] exceptionDetail, Date createTime) {
        this.logId = logId;
        this.logMethod = logMethod;
        this.logDescription = logDescription;
        this.logParams = logParams;
        this.logAddr = logAddr;
        this.logBrowser = logBrowser;
        this.logType = logType;
        this.userName = userName;
        this.userPhone = userPhone;
        this.executeTime = executeTime;
        this.exceptionDetail = exceptionDetail;
        this.createTime = createTime;
    }

    public LogEntity(String logType, Long executeTime) {
        this.logType = logType;
        this.executeTime = executeTime;
    }
    public LogEntity(){}

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogMethod() {
        return logMethod;
    }

    public void setLogMethod(String logMethod) {
        this.logMethod = logMethod;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public String getLogParams() {
        return logParams;
    }

    public void setLogParams(String logParams) {
        this.logParams = logParams;
    }

    public String getLogAddr() {
        return logAddr;
    }

    public void setLogAddr(String logAddr) {
        this.logAddr = logAddr;
    }

    public String getLogBrowser() {
        return logBrowser;
    }

    public void setLogBrowser(String logBrowser) {
        this.logBrowser = logBrowser;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public byte[] getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(byte[] exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
