package com.example.core.filter;


import org.springframework.util.StopWatch;

import javax.servlet.*;
import java.io.IOException;

public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("========进入过滤器========");
        filterChain.doFilter(servletRequest, servletResponse);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Override
    public void destroy() {

    }
}
