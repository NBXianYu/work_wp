package com.core.work.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 配置流程页面
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/19 0019 下午 18:18
 */
@Configuration
public class WorkflowWebMvcConfig implements WebMvcConfigurer {
    private final CrossOriginConfig crossOriginConfig;

    @Autowired
    public WorkflowWebMvcConfig(CrossOriginConfig crossOriginConfig) {
        this.crossOriginConfig = crossOriginConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/demo/**").addResourceLocations("classpath:/public/demo/");
//        registry.addResourceHandler("/diagram-viewer/**").addResourceLocations("classpath:/public/diagram-viewer/");
//        registry.addResourceHandler("/editor-app/**").addResourceLocations("classpath:/public/editor-app/");
//        registry.addResourceHandler("/modeler.html").addResourceLocations("classpath:/public/modeler.html");
//        registry.addResourceHandler("/testUpload.html").addResourceLocations("classpath:/public/testUpload.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crossOriginConfig).addPathPatterns("/**");
    }
}
