package com.example.core.config;

import com.example.core.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean registerFilter() {
//        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new RequestFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("requestFilter");
//        registration.setOrder(1);
//        return registration;
//    }
}
