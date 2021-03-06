package com.heanzyzabala.jpost.web.converter;

import com.heanzyzabala.jpost.domain.Post;
import com.heanzyzabala.jpost.web.PostRequest;

public class RequestToPostConverter implements Converter<PostRequest, Post> {
    @Override
    public Post convert(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return post;
    }
}
