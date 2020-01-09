package com.heanzyzabala.jpost.repository;

import com.heanzyzabala.jpost.domain.Post;

import java.util.UUID;

public interface PostRepository {
    Post save(Post post);
    Post find(UUID id);
}
