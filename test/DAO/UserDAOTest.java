/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.Date;
import java.util.HashSet;
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
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class UserDAOTest {

    private UserDAO userDAO;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public UserDAOTest() {
    }

    @Before
    public void setUp() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        BlogHubUtil.setSessionFactory(sessionFactory);
        userDAO = new UserDAO();
    }

    @After
    public void tearDown() {
        BlogHubUtil.setSessionFactory(null);
    }

    /**
     * Test of save method, of class UserDAO.
     */
    @Test
    public void testSaveUser_Success() {
        Users user = new Users(1, "username", "user@email.com", "password", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        
        boolean result = userDAO.save(user);

        assertTrue(result);
        verify(session).save(user);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testSaveUserFailure() {
//      Create an exception
        Users user = null;
        doThrow(new RuntimeException("Save failed")).when(session).save(user);

        boolean result = userDAO.save(user);

        assertFalse(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    /**
     * Test of getByUsername method, of class UserDAO.
     */
    @Test
    public void testGetByUsername_Success() {
        final String q = "FROM Users WHERE username = :username";
        
//      Expected result
        String username = "testUser";
        Users expResult = new Users(1, username, "user@email.com", "password", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        
        Query query = mock(Query.class);
        when(session.createQuery(q)).thenReturn(query);
        when(query.setParameter("username", username)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(expResult);

        Users result = userDAO.getByUsername(username);

        assertUserEquals(expResult, result);
        verify(session).createQuery(q);
        verify(query).setParameter("username", username);
        verify(query).uniqueResult();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testGetByUsername_Failure() {
//      Create an exception
        Users result = userDAO.getByUsername(null);

        assertNull(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    @Test
    public void testGetByEmailSuccess() {
        String email = "test@example.com";
//      Expected result
        Users expResult = new Users(1, "username", email, "password", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        
        Query query = mock(Query.class);
        when(session.createQuery("FROM Users WHERE email = :email")).thenReturn(query);
        when(query.setParameter("email", email)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(expResult);

        Users result = userDAO.getByEmail(email);

        assertUserEquals(expResult, result);
        verify(session).createQuery("FROM Users WHERE email = :email");
        verify(query).setParameter("email", email);
        verify(query).uniqueResult();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testGetByEmailFailure() {
//      Create an exception
        Users result = userDAO.getByEmail(null);

        assertNull(result);
        verify(transaction).rollback();
        verify(session).close();
    }
    
    public void assertUserEquals(Users result, Users expected) {
        assertEquals(result.getId(), expected.getId());
        assertEquals(result.getUsername(), expected.getUsername());
        assertEquals(result.getEmail(), expected.getEmail());
        assertEquals(result.getPassword(), expected.getPassword());
        assertEquals(result.getCreatedAt(), expected.getCreatedAt());
        assertEquals(result.getCommentses(), expected.getCommentses());
        assertEquals(result.getPostses(), expected.getPostses());
    }
}
