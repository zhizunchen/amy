package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Created by chenhe
 * @Date 2019-07-31 14:10
 * @Description 自定义拦截器
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)srequest;

        System.out.println("====================" + request.getHeader("sso_token"));

        filterChain.doFilter(srequest, sresponse);
    }

    @Override
    public void destroy() {

    }
}
