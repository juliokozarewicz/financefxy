package com.example.demo.utils.others;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {

        ResourceBundleMessageSource messageSource =
            new ResourceBundleMessageSource();

        messageSource.setBasename("messages/messages");
        return messageSource;

    }
}