package com.heanzyzabala.jpost.service;

import com.heanzyzabala.jpost.domain.Comment;
import com.heanzyzabala.jpost.domain.Post;

import java.util.UUID;

public interface PostService {
    Post create(Post post);
    Post comment(UUID id, Comment comment);
}
