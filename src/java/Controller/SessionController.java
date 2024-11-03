/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.UserService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.Valid;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
@ManagedBean
@SessionScoped
public class SessionController implements Serializable {

    @Valid
    private Users currentUser = new Users();
    private boolean error = false;
    private FacesContext facesContext;
    private UserService userService;

    public SessionController() {
        facesContext = FacesContext.getCurrentInstance();
        userService = new UserService();
    }
    
    public boolean isLoggedIn() {
        return currentUser.getUsername()!= null;
    }

    public String login() {
        Users user = userService.login(currentUser.getEmail(), currentUser.getPassword());
        if (user != null) {
            setCurrentUser(user);
            System.out.println(currentUser.getUsername());
            return "index.xhtml?faces-redirect=true";
        } else {
            setError(true);
            facesContext.addMessage("formin:testing", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
            return null;
        }
    }
    
    public String logout() {
        currentUser = new Users(); // Clear the session
        facesContext.getExternalContext().invalidateSession(); // Invalidate the session
        return "index.xhtml?faces-redirect=true"; // Redirect to login page
    }
    
    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public Users getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(Users user) {
        this.currentUser = user;
    }
    
    
    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}

