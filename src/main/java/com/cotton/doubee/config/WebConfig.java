package com.cotton.doubee.config;


import com.cotton.doubee.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017-05-31.
 */
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/wechat/*");
        super.addInterceptors(registry);
    }
}
