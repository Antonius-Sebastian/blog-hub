/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import Controller.SessionController;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class AuthFilterTest {
    
    private AuthFilter authFilter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private FilterChain chain;
    private SessionController sessionController;

    @Before
    public void setUp() {
        authFilter = new AuthFilter();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        chain = mock(FilterChain.class);
        sessionController = mock(SessionController.class);

        when(request.getSession(false)).thenReturn(session);
    }

    @Test
    public void testDoFilter_UnauthenticatedUser() throws IOException, ServletException {
        when(session.getAttribute("sessionController")).thenReturn(sessionController);
        when(sessionController.getCurrentUser()).thenReturn(new Users(null, null, null, null));

        authFilter.doFilter(request, response, chain);

        verify(response).sendRedirect(request.getContextPath() + "/login.xhtml");   
    }

    @Test
    public void testDoFilter_AuthenticatedUser() throws IOException, ServletException {
        when(session.getAttribute("sessionController")).thenReturn(sessionController);
        when(sessionController.getCurrentUser()).thenReturn(new Users("username", "email@email.com", "password", new Date()));
        
        authFilter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
        verify(response, never()).sendRedirect(anyString());
    }

    @Test
    public void testDoFilter_NoSession() throws IOException, ServletException {
        when(request.getSession(false)).thenReturn(null);

        authFilter.doFilter(request, response, chain);

        verify(response).sendRedirect(request.getContextPath() + "/login.xhtml");
        verify(chain, never()).doFilter(request, response);
    }
}
