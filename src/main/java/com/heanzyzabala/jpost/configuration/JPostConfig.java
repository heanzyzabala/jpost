package com.heanzyzabala.jpost.configuration;

import com.heanzyzabala.jpost.service.DefaultPostService;
import com.heanzyzabala.jpost.service.PostService;
import com.heanzyzabala.jpost.web.converter.RequestToCommentConverter;
import com.heanzyzabala.jpost.web.converter.RequestToPostConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPostConfig {

    @Bean
    public PostService postService() {
        return new DefaultPostService();
    }

    @Bean
    public RequestToCommentConverter toCommentConverter() {
        return new RequestToCommentConverter();
    }

    @Bean
    public RequestToPostConverter toPostConverter() {
        return new RequestToPostConverter();
    }
}
