/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.UserDAO;
import java.util.Date;
import java.util.HashSet;
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
public class UserServiceTest {

    UserDAO userDAO;
    UserService userService;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public UserServiceTest() {
    }

    @Before
    public void setUp() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        BlogHubUtil.setSessionFactory(sessionFactory);
        userDAO = mock(UserDAO.class);
        userService = new UserService();
        userService.userDAO = userDAO;
    }

    @After
    public void tearDown() {
        BlogHubUtil.setSessionFactory(null);
    }

    /**
     * Test of register method, of class UserService.
     */
    @Test
    public void testRegister_Success() {
        Users user = new Users(1, "username", "test@email.com", "password", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        
        when(userDAO.getByUsername(user.getUsername())).thenReturn(null);
        when(userDAO.getByEmail(user.getEmail())).thenReturn(null);
        when(userDAO.save(user)).thenReturn(true);

        String result = userService.register(user);

        assertEquals("success", result);
        verify(userDAO).getByUsername(user.getUsername());
        verify(userDAO).getByEmail(user.getEmail());
        verify(userDAO).save(user);
    }

    @Test
    public void testRegister_UsernameAlreadyExists() {
        Users user = new Users(1, "username", "test@email.com", "password", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        
        when(userDAO.getByUsername(user.getUsername())).thenReturn(new Users());

        String result = userService.register(user);

        assertEquals("Username already exists.", result);
        verify(userDAO).getByUsername(user.getUsername());
        verify(userDAO, never()).getByEmail(user.getEmail());
        verify(userDAO, never()).save(user);
    }

    @Test
    public void testRegister_EmailAlreadyExists() {
        Users user = new Users(1, "username", "test@email.com", "password", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        
        when(userDAO.getByUsername(user.getUsername())).thenReturn(null);
        when(userDAO.getByEmail(user.getEmail())).thenReturn(new Users());
        
        String result = userService.register(user);

        assertEquals("Email already exists.", result);
        verify(userDAO).getByUsername(user.getUsername());
        verify(userDAO).getByEmail(user.getEmail());
        verify(userDAO, never()).save(user);
    }

    @Test
    public void testRegister_PasswordHashingFailure() {
        Users user = new Users(1, "username", "test@email.com", null, new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());

        String result = userService.register(user);

        assertEquals("There's something wrong with the server. Try again later.", result);
        verify(userDAO, never()).getByUsername(user.getUsername());
        verify(userDAO, never()).getByEmail(user.getEmail());
        verify(userDAO, never()).save(user);
    }

    @Test
    public void testRegister_SaveFailure() {
        Users user = new Users(1, "username", "test@email.com", "password", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        
        when(userDAO.getByUsername(user.getUsername())).thenReturn(null);
        when(userDAO.getByEmail(user.getEmail())).thenReturn(null);
        when(userDAO.save(user)).thenReturn(false);
        
        String result = userService.register(user);

        assertEquals("There's something wrong with the server. Try again later.", result);
        verify(userDAO).getByUsername(user.getUsername());
        verify(userDAO).getByEmail(user.getEmail());
        verify(userDAO).save(user);
    }

    /**
     * Test of login method, of class UserService.
     */
    @Test
    public void testLogin_Success() {
        Users user = new Users(1, "username", "test@email.com", "c5MNqov62Ts7b6zv66YfOw==:salt", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        when(userDAO.getByEmail(user.getEmail())).thenReturn(user);

        Users result = userService.login(user.getEmail(), "password123");
        
        assertEquals(result.getId(), user.getId());
        assertEquals(result.getUsername(), user.getUsername());
        assertEquals(result.getEmail(), user.getEmail());
        assertEquals(result.getPassword(), user.getPassword());
        assertEquals(result.getCreatedAt(), user.getCreatedAt());
        assertEquals(result.getCommentses(), user.getCommentses());
        assertEquals(result.getPostses(), user.getPostses());
        verify(userDAO).getByEmail(user.getEmail());
    }

    @Test
    public void testLogin_Failure_WrongPassword() {
        Users user = new Users(1, "username", "test@example.com", "pass:word", new Date(2024, 10, 31), new HashSet<>(), new HashSet<>());
        when(userDAO.getByEmail("test@example.com")).thenReturn(user);

        Users result = userService.login(user.getEmail(), "password123");

        assertNull(result);
        verify(userDAO).getByEmail(user.getEmail());
    }

    @Test
    public void testLogin_Failure_UserNotFound() {
        String email = "nonexistent@example.com";
        
        Users result = userService.login(email, "password123");

        assertNull(result);
        verify(userDAO).getByEmail("nonexistent@example.com");
    }

}
