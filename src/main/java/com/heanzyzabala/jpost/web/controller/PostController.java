package com.heanzyzabala.jpost.web.controller;

import com.heanzyzabala.jpost.domain.Comment;
import com.heanzyzabala.jpost.domain.Post;
import com.heanzyzabala.jpost.service.PostService;
import com.heanzyzabala.jpost.web.CommentRequest;
import com.heanzyzabala.jpost.web.PostRequest;
import com.heanzyzabala.jpost.web.converter.RequestToCommentConverter;
import com.heanzyzabala.jpost.web.converter.RequestToPostConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private RequestToPostConverter toPostConverter;

    @Autowired
    private RequestToCommentConverter toCommentConverter;

    @PostMapping("api/v1/posts")
    public ResponseEntity<Post> create(@RequestBody PostRequest request) {
        Post post = toPostConverter.convert(request);
        Post createdPost = postService.create(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("api/v1/posts/{id}")
    public ResponseEntity<Post> get(@PathVariable UUID id) {
        Post post = postService.get(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("api/v1/posts/{id}/comments")
    public ResponseEntity<Comment> comment(@PathVariable(value = "id") UUID id,
                                        @RequestBody CommentRequest request) {
        Comment comment = toCommentConverter.convert(request);
        Comment createdComment = postService.comment(id, comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
}
