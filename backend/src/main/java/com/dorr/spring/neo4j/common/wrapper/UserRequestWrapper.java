package com.dorr.spring.neo4j.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserRequestWrapper extends HttpServletRequestWrapper {
    private String userId;
    private String userName;

    public UserRequestWrapper(HttpServletRequest request) {
        super(request);
        this.userId = (String) request.getSession(false).getAttribute("userId");
        this.userName = (String) request.getSession(false).getAttribute((String) request.getSession(false).getAttribute("userId"));
    }

    @Override
    public String getRemoteUser() {

        return userId;
    }

    public String getRemoteUser(String userId) {

        return userName;
    }


}
