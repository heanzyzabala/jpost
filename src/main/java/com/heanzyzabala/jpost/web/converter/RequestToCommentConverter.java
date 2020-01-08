package com.heanzyzabala.jpost.web.converter;

import com.heanzyzabala.jpost.domain.Comment;
import com.heanzyzabala.jpost.web.CommentRequest;

public class RequestToCommentConverter implements Converter<CommentRequest, Comment>{
    @Override
    public Comment convert(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        return comment;
    }
}
