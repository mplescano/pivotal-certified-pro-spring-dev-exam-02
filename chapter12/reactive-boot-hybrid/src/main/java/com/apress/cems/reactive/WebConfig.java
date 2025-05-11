package com.apress.cems.reactive;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        final Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("charset", "UTF-8");

        configurer.parameterName()

        configurer.defaultContentType(new MediaType(
                MediaType.TEXT_EVENT_STREAM, parameterMap));
    }*/
}
