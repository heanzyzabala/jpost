package com.heanzyzabala.jpost.service;

import com.heanzyzabala.jpost.domain.Comment;
import com.heanzyzabala.jpost.domain.Post;
import com.heanzyzabala.jpost.exception.PostNotFoundException;
import com.heanzyzabala.jpost.repository.CommentRepository;
import com.heanzyzabala.jpost.repository.PostRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DefaultPostServiceTest {
    private DefaultPostService service;
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UuidSupplier uuidSupplier = new UuidSupplier();
    private NowSupplier nowSupplier = new NowSupplier();

    private UUID id = UUID.randomUUID();
    private LocalDateTime now = LocalDateTime.of(2020, 1, 10, 9, 0);

    @BeforeAll
    public void setup() {
        uuidSupplier.setId(id);
        nowSupplier.setNow(now);
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);
        service = new DefaultPostService(postRepository, commentRepository, uuidSupplier, nowSupplier);
    }

    @Test
    public void shouldCreatePost() {
        Post expected = new Post();
        expected.setTitle("Title");
        expected.setContent("Content");
        expected.setComments(Collections.emptyList());

        when(postRepository.save(expected)).thenReturn(expected);

        Post actual = service.create(expected);
        assertPost(expected, actual);
        assertEquals(id, actual.getId());
        assertEquals(now, actual.getCreatedAt());
        assertEquals(now, actual.getUpdatedAt());
    }

    private void assertPost(Post expected, Post actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
        assertComments(expected.getComments(), actual.getComments());
    }

    private void assertComments(List<Comment> expected, List<Comment> actual) {
        assertEquals(expected.size(), actual.size());
        for(int i=0; i<expected.size(); i++)
            assertComment(expected.get(i), actual.get(i));
    }

    private void assertComment(Comment expected, Comment actual) {
        assertEquals(expected.getPostId(), actual.getPostId());
        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    public void shouldGetPost() {
        Post expected = new Post();
        expected.setTitle("Title");
        expected.setContent("Content");
        expected.setComments(Collections.emptyList());
        expected.setCreatedAt(now);
        expected.setUpdatedAt(now);

        when(postRepository.findById(id)).thenReturn(Optional.of(expected));
        when(commentRepository.findByPostId(id)).thenReturn(Collections.emptyList());

        assertPost(expected, service.get(id));
    }

    @Test
    public void onGetShouldThrowExceptionWhenPostDoesNotExist() {
        when(postRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(PostNotFoundException.class, () -> service.get(id));
    }

    @Test
    public void shouldPostComment() {
        Post post = new Post();
        post.setId(id);
        post.setTitle("Title");
        post.setContent("Content");
        post.setComments(Collections.emptyList());
        post.setCreatedAt(now);
        post.setUpdatedAt(now);
        Comment expected = new Comment();
        expected.setContent("Comment content");

        when(postRepository.existsById(id)).thenReturn(true);
        when(commentRepository.save(expected)).thenReturn(expected);

        assertComment(expected, service.comment(id, expected));
    }

    @Test
    public void onCommentShouldThrowExceptionWhenPostDoesNotExist() {
        when(postRepository.existsById(id)).thenReturn(false);
        assertThrows(PostNotFoundException.class, () -> service.comment(id, new Comment()));
    }

    static class UuidSupplier implements Supplier<UUID> {
        private UUID id;

        public void setId(UUID id) {
            this.id = id;
        }

        @Override
        public UUID get() {
            return id;
        }
    }

    static class NowSupplier implements Supplier<LocalDateTime> {
        private LocalDateTime now;

        public void setNow(LocalDateTime now) {
            this.now = now;
        }

        @Override
        public LocalDateTime get() {
            return now;
        }
    }
}
