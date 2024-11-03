/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import Controller.SessionController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author silviarianto
 */
@WebFilter({"/create.xhtml", "/edit.xhtml", "/yourBlogList.xhtml"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        if (session != null) {
            SessionController sessionController = (SessionController) session.getAttribute("sessionController");
            if (sessionController.getCurrentUser().getUsername() == null) {
                response.sendRedirect(request.getContextPath() + "/login.xhtml");
            }
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
        }
    }

    @Override
    public void destroy() {
    }

}
