package com.sx.springbootexample.config;

import javax.servlet.Filter;

import com.sx.common.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yinyihang
 * @Description: 跨域配置
 * @date 2020年05月6日
 */
@Configuration
public class AppWebMvcConfigurer extends  WebMvcConfigurerAdapter{
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public Filter corsFilter() {
        return new CorsFilter();
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        /*
//         * 说明：增加虚拟路径(经过本人测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
//         * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
//         */
//        //Windows下
////        registry.addResourceHandler("/uploads2/**").addResourceLocations("file:D:/uploads2/");
//        //Mac或Linux下(没有CDEF盘符)
//        registry.addResourceHandler("/uploads/**").addResourceLocations("file:./data");
//        super.addResourceHandlers(registry);
//    }
}
