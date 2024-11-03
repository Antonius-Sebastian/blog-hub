/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.UserService;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class SessionControllerTest {
    private SessionController sessionController;
    private UserService userService;
    private FacesContext facesContext;
    private ExternalContext externalContext;
    
    public SessionControllerTest() {
    }
    
    @Before
    public void setUp() {
        sessionController = new SessionController();
        userService = mock(UserService.class);
        facesContext = mock(FacesContext.class);
        externalContext = mock(ExternalContext.class);

        when(facesContext.getExternalContext()).thenReturn(externalContext);
        sessionController.setFacesContext(facesContext);
        sessionController.setUserService(userService);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLogin_Success() {
        Users mockUser = new Users();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password123");
        mockUser.setUsername("testUser");

        when(userService.login(mockUser.getEmail(), mockUser.getPassword())).thenReturn(mockUser);
        
        System.out.println(mockUser);
        sessionController.setCurrentUser(mockUser);
        String result = sessionController.login();

        assertEquals("index.xhtml?faces-redirect=true", result);
        assertEquals(mockUser, sessionController.getCurrentUser());
        assertFalse(sessionController.getError());
    }

    @Test
    public void testLogin_Failure() {
        Users mockUser = new Users();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("wrongPassword");

        when(userService.login(mockUser.getEmail(), mockUser.getPassword())).thenReturn(null);
        sessionController.setCurrentUser(mockUser);

        String result = sessionController.login();

        assertNull(result);
        assertTrue(sessionController.getError());
    }

    @Test
    public void testLogout() {
        String result = sessionController.logout();

        verify(externalContext).invalidateSession();

        assertEquals("index.xhtml?faces-redirect=true", result);
        assertNull(sessionController.getCurrentUser().getUsername());
    }

    @Test
    public void testIsLoggedIn() {
        Users mockUser = new Users();
        mockUser.setUsername("testUser");
        assertFalse(sessionController.isLoggedIn());

        sessionController.setCurrentUser(mockUser);
        boolean result = sessionController.isLoggedIn();

        assertTrue(result);
    }

    @Test
    public void testGettersAndSetters() {
        // Test currentUser
        Users user = new Users();
        user.setUsername("testUser");
        sessionController.setCurrentUser(user);
        assertEquals("testUser", sessionController.getCurrentUser().getUsername());

        // Test error flag
        sessionController.setError(true);
        assertTrue(sessionController.getError());

        sessionController.setError(false);
        assertFalse(sessionController.getError());
    }
}
