package com.heanzyzabala.jpost.service;

import com.heanzyzabala.jpost.domain.Comment;
import com.heanzyzabala.jpost.domain.Post;
import com.heanzyzabala.repository.PostRepository;

import java.util.UUID;

public class DefaultPostService implements PostService {
    private PostRepository repository;

    public Post create(Post post) {
        return repository.save(post);
    }

    @Override
    public Post comment(UUID id, Comment comment) {
        Post post = repository.find(id);
        post.getComments().add(comment);
        return repository.save(post);
    }
}
