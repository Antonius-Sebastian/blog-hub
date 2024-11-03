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
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pojo.BlogHubUtil;
import pojo.Comments;
import pojo.Posts;
import pojo.Tags;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class CommentDAOTest {

    private CommentDAO commentDAO;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public CommentDAOTest() {
    }

    @Before
    public void setUp() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        BlogHubUtil.setSessionFactory(sessionFactory);
        commentDAO = new CommentDAO();
    }

    @After
    public void tearDown() {
        BlogHubUtil.setSessionFactory(null);
    }

    /**
     * Test of save method, of class CommentDAO.
     */
    @Test
    public void testSave_Success() {
        Users user = new Users("username", "email@gmail.com", "password", new Date(), new HashSet<>(), new HashSet<>());
        Posts post = new Posts(1, user, "title", "content", new Date(), new HashSet<>(), new HashSet<>());
        Comments comment = new Comments(1, "comment", new Date(2024, 10, 31), post, user);

        boolean result = commentDAO.save(comment);
        
        assertTrue(result);
        verify(session).save(comment);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testSave_Failure() {
        Comments comment = null;
        doThrow(new RuntimeException("Save failed")).when(session).save(comment);

        boolean result = commentDAO.save(comment);

        assertFalse(result);
        verify(transaction).rollback();
        verify(session).close();
    }
}
