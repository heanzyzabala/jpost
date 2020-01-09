package com.heanzyzabala.jpost.repository;

import com.heanzyzabala.jpost.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
