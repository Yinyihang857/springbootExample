package com.sx.springbootexample.config;

import com.sx.common.filter.ServiceInterceptor;
import com.sx.common.filter.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author yinyihang
 * @Description: 拦截器配置
 * @date 2020年05月6日
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new ServiceInterceptor());
        registry.addInterceptor(new TokenInterceptor()).excludePathPatterns("/common/**");
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index.html");
//    }
}
