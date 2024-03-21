package ru.itgirl.checklistproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/form")
                .allowedOrigins("*")
                .allowedMethods("POST", "DELETE", "GET")
                .allowedHeaders("*");

        registry.addMapping("/question")
                .allowedOrigins("*")
                .allowedMethods("POST", "DELETE", "GET")
                .allowedHeaders("*");
    }
}