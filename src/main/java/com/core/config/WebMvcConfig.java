package com.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description: MVC配置
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/22 0022 下午 14:37
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final CrossOriginConfig crossOriginConfig;

    @Autowired
    public WebMvcConfig(CrossOriginConfig crossOriginConfig) {
        this.crossOriginConfig = crossOriginConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crossOriginConfig).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    }

}
