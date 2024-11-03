/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pojo.Posts;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class PostListControllerTest {
    private PostListController postListController;
    private SessionController sessionController;
    private PostDAO postDAO;
    
    public PostListControllerTest() {
    }
    
    @Before
    public void setUp() {
        postListController = new PostListController();
        sessionController = mock(SessionController.class);
        postDAO = mock(PostDAO.class);
        postListController.setSessionController(sessionController);
        postListController.setPostDAO(postDAO);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLoadBlogPosts() {
        List<Posts> expResult = new ArrayList<>();
        expResult.add(new Posts());
        when(postDAO.getPosts()).thenReturn(expResult);
        
        List<Posts> result = postListController.loadBlogPosts();
        
        assertEquals(expResult, result);
        verify(postDAO).getPosts();
    }

    @Test
    public void testLoadNewPosts() {
        List<Posts> expResult = new ArrayList<>();
        expResult.add(new Posts());
        when(postDAO.getNewPosts()).thenReturn(expResult);
        
        List<Posts> result = postListController.loadNewPosts();
        
        assertEquals(expResult, result);
        verify(postDAO).getNewPosts();
    }

    @Test
    public void testLoadUserPosts() {
        Users user = mock(Users.class);
        List<Posts> expResult = new ArrayList<>();
        expResult.add(new Posts());
        when(sessionController.getCurrentUser()).thenReturn(user);
        when(postDAO.getPostByUser(user)).thenReturn(expResult);
        
        List<Posts> result = postListController.loadUserPosts();
        
        assertEquals(expResult, result);
        verify(sessionController).getCurrentUser();
        verify(postDAO).getPostByUser(user);
    }

    @Test
    public void testGettersAndSetters() {
        // Test blogPosts getter and setter
        List<Posts> blogPosts = Arrays.asList(new Posts());
        postListController.setBlogPosts(blogPosts);
        assertEquals(blogPosts, postListController.getBlogPosts());

        // Test userPosts getter and setter
        List<Posts> userPosts = Arrays.asList(new Posts());
        postListController.setUserPosts(userPosts);
        assertEquals(userPosts, postListController.getUserPosts());

        // Test sessionController getter and setter
        SessionController sessionCtrl = new SessionController();
        postListController.setSessionController(sessionCtrl);
        assertEquals(sessionCtrl, postListController.getSessionController());
    }

}
