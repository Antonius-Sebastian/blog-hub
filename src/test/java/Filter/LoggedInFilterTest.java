/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import Controller.SessionController;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class LoggedInFilterTest {
    
        private LoggedInFilter loggedInFilter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private FilterChain chain;
    private SessionController sessionController;
    
    @Before
    public void setUp() {
        loggedInFilter = new LoggedInFilter();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        chain = mock(FilterChain.class);
        sessionController = mock(SessionController.class);

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("sessionController")).thenReturn(sessionController);
    }

    @Test
    public void testDoFilter_NotLoggedIn() throws IOException, ServletException {
        when(sessionController.getCurrentUser()).thenReturn(new Users(null, null, null, null));

        loggedInFilter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
        verify(response, never()).sendRedirect(anyString());
    }

    @Test
    public void testDoFilter_LoggedIn() throws IOException, ServletException {
        Users user = new Users();
        user.setUsername("testUser");
        when(sessionController.getCurrentUser()).thenReturn(user);

        loggedInFilter.doFilter(request, response, chain);

        verify(response).sendRedirect(request.getContextPath() + "/index.xhtml");
        verify(chain, never()).doFilter(request, response);
    }

    @Test
    public void testDoFilter_NoSession() throws IOException, ServletException {
        when(request.getSession(false)).thenReturn(null);

        loggedInFilter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
        verify(response, never()).sendRedirect(anyString());
    }
}
