package com.heanzyzabala.jpost.configuration;

import com.heanzyzabala.jpost.repository.CommentRepository;
import com.heanzyzabala.jpost.repository.PostRepository;
import com.heanzyzabala.jpost.service.DefaultPostService;
import com.heanzyzabala.jpost.service.PostService;
import com.heanzyzabala.jpost.web.converter.RequestToCommentConverter;
import com.heanzyzabala.jpost.web.converter.RequestToPostConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
public class JPostConfig {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Bean
    public PostService postService() {
        return new DefaultPostService(postRepository, commentRepository, UUID::randomUUID, LocalDateTime::now);
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
