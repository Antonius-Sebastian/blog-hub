/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CommentDAO;
import DAO.PostDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import pojo.Comments;
import pojo.Posts;
import pojo.Tags;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class BlogControllerTest {
    private BlogController blogController;
    private SessionController sessionController;
    private PostDAO postDAO;
    private CommentDAO commentDAO;
    
    public BlogControllerTest() {
    }
    
    @Before
    public void setUp() {
        blogController = new BlogController();
        sessionController = mock(SessionController.class);
        postDAO = mock(PostDAO.class);
        commentDAO = mock(CommentDAO.class);
        
        blogController.setSessionController(sessionController);
        
        blogController.setPostDAO(postDAO);
        blogController.setCommentDAO(commentDAO);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadPost method, of class BlogController.
     */
    @Test
    public void testLoadPost() {
        Posts mockPost = mock(Posts.class);
        blogController.setPostId(2);
        when(postDAO.getPostById(blogController.getPostId())).thenReturn(mockPost);
        
        blogController.loadPost();
        
        assertNotNull(blogController.getPost());
        assertNotNull(blogController.getComments());
        assertNotNull(blogController.getTagList());
        
        verify(postDAO).getPostById(blogController.getPostId());
    }
    
    @Test
    public void testLoadPost_WrongPostId() {
        blogController.setPostId(0);
        blogController.loadPost();
        
        assertNull(blogController.getPost());
        assertNull(blogController.getComments());
        assertNull(blogController.getTagList());
        
        verify(postDAO, never()).getPostById(blogController.getPostId());
    }
    
    @Test
    public void testLoadPost_PostNotFound() {
        blogController.setPostId(1000);
        blogController.loadPost();
        
        assertNull(blogController.getPost());
        assertNull(blogController.getComments());
        assertNull(blogController.getTagList());
        
        verify(postDAO).getPostById(blogController.getPostId());
    }

    /**
     * Test of addComment method, of class BlogController.
     */
    @Test
    public void testAddCommentWhenLoggedIn() {
        blogController.setPostId(1);
        when(sessionController.isLoggedIn()).thenReturn(true);
        when(sessionController.getCurrentUser()).thenReturn(new Users());
        when(commentDAO.save(blogController.getComment())).thenReturn(true);
        
        String result = blogController.addComment();
        
        assertNull(result);
        verify(commentDAO).save(blogController.getComment());
    }

    @Test
    public void testAddCommentWhenNotLoggedIn() {
        when(sessionController.isLoggedIn()).thenReturn(false);
        
        String result = blogController.addComment();
        
        assertEquals("login.xhtml?faces-redirect=true", result);
        verify(sessionController, never()).getCurrentUser();
        verify(commentDAO, never()).save(blogController.getComment());
    }
    
    @Test
    public void testAddCommentWhenPostNotFound() {
        blogController.setPostId(0);
        when(sessionController.isLoggedIn()).thenReturn(true);
        
        String result = blogController.addComment();
        
        assertNull(result);
        verify(sessionController, never()).getCurrentUser();
        verify(commentDAO, never()).save(blogController.getComment());
    }

    @Test
    public void testGettersAndSetters() {
        // Test SessionController
        SessionController sc = new SessionController();
        blogController.setSessionController(sc);
        assertEquals(sc, blogController.getSessionController());

        // Test Comment
        Comments comment = new Comments();
        blogController.setComment(comment);
        assertEquals(comment, blogController.getComment());

        // Test Comments List
        List<Comments> commentsList = Arrays.asList(new Comments(), new Comments());
        blogController.setComments(commentsList);
        assertEquals(commentsList, blogController.getComments());

        // Test Post
        Posts post = new Posts();
        blogController.setPost(post);
        assertEquals(post, blogController.getPost());

        // Test Post ID
        int postId = 42;
        blogController.setPostId(postId);
        assertEquals(postId, blogController.getPostId());

        // Test Tag List
        List<Tags> tagsList = new ArrayList<>();
        tagsList.add(new Tags());
        blogController.setTagList(tagsList);
        assertEquals(tagsList, blogController.getTagList());
    }
}
