package com.obagajesse.BookingFlightSystem1.Config;

import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SwaggerConfig implements WebMvcConfigurer {


    public SwaggerConfig(){
        SpringDocUtils.getConfig().addAnnotationsToIgnore(org.springframework.web.bind.annotation.RequestMapping.class,
                org.springframework.web.bind.annotation.GetMapping.class,
                org.springframework.web.bind.annotation.PostMapping.class,
                org.springframework.web.bind.annotation.PutMapping.class,
                org.springframework.web.bind.annotation.PatchMapping.class,
                org.springframework.web.bind.annotation.DeleteMapping.class,
                org.springframework.web.bind.annotation.RequestParam.class,
                org.springframework.web.bind.annotation.RequestAttribute.class,
                org.springframework.web.bind.annotation.RequestHeader.class,
                org.springframework.web.bind.annotation.RequestBody.class,
                org.springframework.web.bind.annotation.RequestPart.class);
    }

    @Bean
    public SwaggerUiConfigProperties swaggerUiConfigProperties() {
        return new SwaggerUiConfigProperties();
    }
}
