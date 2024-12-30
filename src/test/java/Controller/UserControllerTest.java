/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.UserService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class UserControllerTest {

    private UserController userController;
    private UserService userService;
    private FacesContext facesContext;
    private UIComponent uiComponent;

    public UserControllerTest() {
    }

    @Before
    public void setUp() {
        userController = new UserController();
        userService = mock(UserService.class);
        userController.setUserService(userService);
        uiComponent = mock(UIComponent.class);

        facesContext = mock(FacesContext.class);
        userController.setFacesContext(facesContext);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRegister_Success() {
        Users user = new Users();
        userController.setUser(user);
        when(userService.register(user)).thenReturn("success");

        String result = userController.register();

        assertEquals("login.xhtml?faces-redirect=true", result);
        assertFalse(userController.getError());
        verify(userService).register(user);
    }

    @Test
    public void testRegister_Failure() {
        Users user = new Users();
        userController.setUser(user);
        when(userService.register(user)).thenReturn("There's something wrong with the server. Try again later.");

        String result = userController.register();

        assertNull(result);
        assertTrue(userController.getError());
        verify(userService).register(user);
    }

    @Test(expected = ValidatorException.class)
    public void testValidateConfirmPassword_Failure() {
        userController.setFinalPassword("password123");
        userController.validateConfirmPassword(facesContext, uiComponent, "wrongPassword");
    }

    @Test
    public void testValidateConfirmPassword_Success() {
        userController.setFinalPassword("password123");
        userController.validateConfirmPassword(facesContext, uiComponent, "password123");
        System.out.println("Passwords matched as expected.");
    }

    @Test
    public void testGettersAndSetters() {
        // Test User
        Users user = new Users();
        user.setUsername("testUser");
        userController.setUser(user);
        assertEquals("testUser", userController.getUser().getUsername());

        // Test FinalPassword
        String finalPassword = "password123";
        userController.setFinalPassword(finalPassword);
        assertEquals("password123", userController.getFinalPassword());

        // Test ConfirmPassword
        String confirmPassword = "password123";
        userController.setConfirmPassword(confirmPassword);
        assertEquals("password123", userController.getConfirmPassword());

        // Test Error flag
        // Initially, error should be false
        assertFalse(userController.getError());

        // Set error to true and verify
        userController.setError(true);
        assertTrue(userController.getError());

        // Set error back to false and verify
        userController.setError(false);
        assertFalse(userController.getError());
    }
}
