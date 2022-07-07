package com.dorr.spring.neo4j.common.inteceptor;

import com.alibaba.fastjson.JSON;
import com.dorr.spring.neo4j.common.entity.WebJsonInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String userId = request.getRemoteUser();

        if (StringUtils.isNotEmpty(userId)){


        }else {

            response.setCharacterEncoding(Charset.defaultCharset().name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            try(ServletOutputStream out = response.getOutputStream()){

                out.write(JSON.toJSONString(WebJsonInfo.build("400","未登录")).getBytes());
            }catch (IOException e ){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}
