package com.ling.stums.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
* 解决跨域问题
* */
@Component
public class CrosFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type,Authentication,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
        res.setHeader("Content-Type", "application/json;charset=utf-8");//解决前端post请求跨域问题
        if (((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")) {
            ((HttpServletResponse) servletResponse).setStatus(HttpStatus.OK.value());
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
