package pojo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostsTest {

    private Posts post;
    private Users user;
    private Set<Tags> tagsSet;
    private Set<Comments> commentsSet;

    @Before
    public void setUp() {
        user = new Users();
        user.setId(1);

        tagsSet = new HashSet<>();
        commentsSet = new HashSet<>();

        post = new Posts();
    }

    @Test
    public void testPostsConstructorWithMandatoryFields() {
        String title = "Sample Title";
        String content = "Sample Content";
        Date createdAt = new Date();

        Posts postConst = new Posts(user, title, content, createdAt);

        assertEquals(user, postConst.getUsers());
        assertEquals(title, postConst.getTitle());
        assertEquals(content, postConst.getContent());
        assertEquals(createdAt, postConst.getCreatedAt());
    }

    @Test
    public void testPostsConstructorWithAllFields() {
        String title = "Sample Title";
        String content = "Sample Content";
        Date createdAt = new Date();
        Set<Tags> tags = new HashSet<>();
        Set comments = new HashSet();

        Posts postConst = new Posts(1, user, title, content, createdAt, tags, comments);

        assertEquals(user, postConst.getUsers());
        assertEquals(title, postConst.getTitle());
        assertEquals(content, postConst.getContent());
        assertEquals(createdAt, postConst.getCreatedAt());
        assertEquals(tags, postConst.getTagses());
        assertEquals(comments, postConst.getCommentses());
    }
    
    @Test
    public void testPostsConstructorWithAllFieldsWithoutId() {
        String title = "Sample Title";
        String content = "Sample Content";
        Date createdAt = new Date();
        Set<Tags> tags = new HashSet<>();
        Set comments = new HashSet();

        Posts postConst = new Posts(user, title, content, createdAt, tags, comments);

        assertEquals(user, postConst.getUsers());
        assertEquals(title, postConst.getTitle());
        assertEquals(content, postConst.getContent());
        assertEquals(createdAt, postConst.getCreatedAt());
        assertEquals(tags, postConst.getTagses());
        assertEquals(comments, postConst.getCommentses());
    }

    @Test
    public void testGettersAndSetters() {
        // Test ID
        post.setId(123);
        assertEquals(Integer.valueOf(123), post.getId());

        // Test Users
        post.setUsers(user);
        assertEquals(user, post.getUsers());

        // Test Title
        String title = "Post Title";
        post.setTitle(title);
        assertEquals(title, post.getTitle());

        // Test Content
        String content = "This is the post content.";
        post.setContent(content);
        assertEquals(content, post.getContent());

        // Test CreatedAt
        Date date = new Date();
        post.setCreatedAt(date);
        assertEquals(date, post.getCreatedAt());

        // Test Tagses (Tags Set)
        post.setTagses(tagsSet);
        assertEquals(tagsSet, post.getTagses());

        // Test Commentses (Comments Set)
        post.setCommentses(commentsSet);
        assertEquals(commentsSet, post.getCommentses());
    }

}
