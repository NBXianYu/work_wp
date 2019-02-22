package com.core.work.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 吴鹏
 * @Date Created in 上午 11:22 2019/1/28 0028
 * @Email 694798354@qq.com
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .withMethodAnnotation(ApiOperation.class)).build();
    }

}
