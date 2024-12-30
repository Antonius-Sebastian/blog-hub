/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silviarianto
 */
public class CommentsTest {

    private Comments comments;
    private Posts posts;
    private Users users;

    public CommentsTest() {
    }

    @Before
    public void setUp() {
        posts = new Posts();
        posts.setId(1);
        
        users = new Users();
        users.setId(1);
        
        comments = new Comments(posts, users);
    }

    @After
    public void tearDown() {
    }

            @Test
    public void testPostsConstructorWithAllFieldsWithoutId() {
        String content = "Sample Content";
        Date createdAt = new Date();

        Comments postConst = new Comments(content, createdAt, posts, users);

        assertEquals(users, postConst.getUsers());
        assertEquals(content, postConst.getContent());
        assertEquals(createdAt, postConst.getCreatedAt());
        assertEquals(posts, postConst.getPosts());
        assertEquals(users, postConst.getUsers());
    }
    
    /**
     * Test of Getters and Setters method, of class Comments.
     */
    @Test
    public void testGettersAndSetters() {
        // Test ID
        comments.setId(123);
        assertEquals(Integer.valueOf(123), comments.getId());

        // Test Content
        String content = "This is a test comment.";
        comments.setContent(content);
        assertEquals(content, comments.getContent());

        // Test CreatedAt
        Date date = new Date();
        comments.setCreatedAt(date);
        assertEquals(date, comments.getCreatedAt());

        // Test Posts
        Posts newPost = new Posts();
        newPost.setId(2);
        comments.setPosts(newPost);
        assertEquals(newPost, comments.getPosts());

        // Test Users
        Users newUser = new Users();
        newUser.setId(2);
        comments.setUsers(newUser);
        assertEquals(newUser, comments.getUsers());
    }
}
