/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import pojo.BlogHubUtil;
import pojo.Posts;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class PostDAOTest {

    private PostDAO postDAO;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public PostDAOTest() {
    }

    @Before
    public void setUp() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        BlogHubUtil.setSessionFactory(sessionFactory);
        postDAO = new PostDAO();
    }

    @After
    public void tearDown() {
        BlogHubUtil.setSessionFactory(null);
    }

    /**
     * Test of getPosts method, of class PostDAO.
     */
    @Test
    public void testGetPosts_Success() {
//      Expected result
        Users mockUser = new Users(1, "username", "user@email.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        List<Posts> expResult = new ArrayList<>();
        expResult.add(new Posts(1, mockUser, "title-1", "content-1", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));
        expResult.add(new Posts(2, mockUser, "title-2", "content-2", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));
        expResult.add(new Posts(3, mockUser, "title-3", "content-3", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));

//      Mock the database
        Query query = mock(Query.class);
        when(session.createQuery("FROM Posts")).thenReturn(query);
        when(query.list()).thenReturn(expResult);

        List<Posts> result = postDAO.getPosts();

//      Assert the result
        for (int i = 0; i < result.size(); i++) {
            assertPostEquals(expResult.get(i), result.get(i));
        }
        verify(session).createQuery("FROM Posts");
        verify(query).list();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testGetPosts_Failure() {
//      Create an exception
        when(session.createQuery("FROM Posts")).thenReturn(null);

        List<Posts> result = postDAO.getPosts();

        assertNull(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    /**
     * Test of getNewPosts method, of class PostDAO.
     */
    @Test
    public void testGetNewPosts_Success() {
        final String q = "FROM Posts p ORDER BY p.createdAt DESC";

//      Expected result
        Users mockUser = new Users(1, "username", "user@email.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        List<Posts> expResult = new ArrayList<>();
        expResult.add(new Posts(1, mockUser, "title-1", "content-1", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));
        expResult.add(new Posts(2, mockUser, "title-2", "content-2", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));
        expResult.add(new Posts(3, mockUser, "title-3", "content-3", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));

//      Mock the database
        Query query = mock(Query.class);
        when(session.createQuery(q)).thenReturn(query);
        when(query.list()).thenReturn(expResult);

        List<Posts> result = postDAO.getNewPosts();

//      Assert the result
        for (int i = 0; i < result.size(); i++) {
            assertPostEquals(expResult.get(i), result.get(i));
        }
        verify(session).createQuery(q);
        verify(query).list();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testGetNewPosts_Failure() {
//      Create an exception
        when(session.createQuery("FROM Posts p ORDER BY p.createdAt DESC")).thenReturn(null);

        List<Posts> result = postDAO.getNewPosts();

        assertNull(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    /**
     * Test of getPostByUser method, of class PostDAO.
     */
    @Test
    public void testGetPostByUser_Success() {
        final String q = "FROM Posts WHERE users= :users";
        
//      Expected result
        Users mockUser = new Users(1, "username", "user@email.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        List<Posts> expResult = new ArrayList<>();
        expResult.add(new Posts(1, mockUser, "title-1", "content-1", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));
        expResult.add(new Posts(2, mockUser, "title-2", "content-2", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));
        expResult.add(new Posts(3, mockUser, "title-3", "content-3", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>()));

//      Mock the database
        Query query = mock(Query.class);
        when(session.createQuery(q)).thenReturn(query);
        when(query.setParameter("users", mockUser)).thenReturn(query);
        when(query.list()).thenReturn(expResult);

        List<Posts> result = postDAO.getPostByUser(mockUser);

//      Assert the result
        for (int i = 0; i < result.size(); i++) {
            assertPostEquals(expResult.get(i), result.get(i));
        }
        verify(session).createQuery(q);
        verify(query).setParameter("users", mockUser);
        verify(query).list();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testGetPostByUser_Failure() {
        Users user = new Users();
//      Create an exception
        when(session.createQuery("FROM Posts WHERE users= :users")).thenReturn(null);
        
        List<Posts> result = postDAO.getPostByUser(user);

        assertNull(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    /**
     * Test of getPostById method, of class PostDAO.
     */
    @Test
    public void testGetPostById_Success() {
        final String q = "FROM Posts p JOIN FETCH p.users JOIN FETCH p.tagses LEFT JOIN FETCH p.commentses c LEFT JOIN FETCH c.users WHERE p.id = :id";

//      Expected result     
        Users mockUser = new Users(1, "username", "user@email.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        Posts expResult = new Posts(1, mockUser, "title-1", "content-1", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());

//      Mock the database
        Query query = mock(Query.class);
        when(session.createQuery(q)).thenReturn(query);
        when(query.setParameter("id", expResult.getId())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(expResult);

        Posts result = postDAO.getPostById(expResult.getId());

        assertPostEquals(result, expResult);
        verify(session).createQuery(q);
        verify(query).setParameter("id", expResult.getId());
        verify(query).uniqueResult();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testGetPostById_Failure() {
//      Create an exception
        when(session.createQuery("FROM Posts p JOIN FETCH p.users JOIN FETCH p.tagses LEFT JOIN FETCH p.commentses c LEFT JOIN FETCH c.users WHERE p.id = :id"))
                .thenReturn(null);
        
        Posts result = postDAO.getPostById(1);

        assertNull(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    /**
     * Test of save method, of class PostDAO.
     */
    @Test
    public void testSave_Success() {
//      Mock Posts object
        Users mockUser = new Users(1, "username", "user@email.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        Posts mockPost = new Posts(1, mockUser, "title-1", "content-1", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());

        boolean result = postDAO.save(mockPost);
        
        assertTrue(result);
        verify(session).save(mockPost);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testSave_Failure() {
        Users mockUser = new Users(1, "username", "user@email.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        Posts post = new Posts(1, mockUser, "title-1", "content-1", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        doThrow(new RuntimeException("Save failed")).when(session).save(post);

        boolean result = postDAO.save(post);
        
        assertFalse(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    /**
     * Test of update method, of class PostDAO.
     */
    @Test
    public void testUpdate_Success() {
//      Mock Posts object
        Users mockUser = new Users(1, "username", "user@email.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        Posts mockPost = new Posts(1, mockUser, "title-1", "content-1", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());

        boolean result = postDAO.update(mockPost);

        assertTrue(result);
        verify(session).update(mockPost);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testUpdate_Failure() {
//      Make an exception
        Posts post = null;
        doThrow(new RuntimeException("Update failed")).when(session).update(post);

        boolean result = postDAO.update(post);

        assertFalse(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    /**
     * Test of delete method, of class PostDAO.
     */
    @Test
    public void testDelete_Success() {
//      Mock post object
        Users mockUser = new Users(1, "username", "user@email.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        Posts mockPost = new Posts(1, mockUser, "title-1", "content-1", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());

        boolean result = postDAO.delete(mockPost);

        assertTrue(result);
        verify(session).delete(mockPost);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testDelete_Failure() {
//      Create an exception
        Posts post = null;
        doThrow(new RuntimeException("Delete failed")).when(session).delete(post);

        boolean result = postDAO.delete(post);

        assertFalse(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    public void assertPostEquals(Posts result, Posts expected) {
        assertEquals(result.getId(), expected.getId());
        assertEquals(result.getTitle(), expected.getTitle());
        assertEquals(result.getContent(), expected.getContent());
        assertEquals(result.getCreatedAt(), expected.getCreatedAt());
        assertEquals(result.getUsers().getUsername(), expected.getUsers().getUsername());
        assertEquals(result.getCommentses(), expected.getCommentses());
        assertEquals(result.getTagses(), expected.getTagses());
    }
}
