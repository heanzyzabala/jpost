package com.heanzyzabala.jpost.service;

import com.heanzyzabala.jpost.domain.Comment;
import com.heanzyzabala.jpost.domain.Post;
import com.heanzyzabala.jpost.repository.CommentRepository;
import com.heanzyzabala.jpost.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;

public class DefaultPostService implements PostService {
    private PostRepository repository;
    private CommentRepository commentRepository;
    private Supplier<UUID> uuidSupplier;
    private Supplier<LocalDateTime> nowSupplier;

    public DefaultPostService(PostRepository repository,
                              CommentRepository commentRepository,
                              Supplier<UUID> uuidSupplier,
                              Supplier<LocalDateTime> nowSupplier) {
        this.repository = repository;
        this.commentRepository = commentRepository;
        this.uuidSupplier = uuidSupplier;
        this.nowSupplier = nowSupplier;
    }

    @Override
    public Post create(Post post) {
        post.setId(uuidSupplier.get());
        post.setCreatedAt(nowSupplier.get());
        post.setUpdatedAt(nowSupplier.get());
        return repository.save(post);
    }

    @Override
    public Post get(UUID id) {
        Post post = repository.getOne(id);
        post.setComments(commentRepository.findByPostId(id));
        return post;
    }

    @Override
    public Comment comment(UUID id, Comment comment) {
        Post post = repository.getOne(id);
        comment.setId(uuidSupplier.get());
        comment.setPostId(post.getId());
        comment.setCreatedAt(nowSupplier.get());
        comment.setUpdatedAt(nowSupplier.get());
        return commentRepository.save(comment);
    }
}
