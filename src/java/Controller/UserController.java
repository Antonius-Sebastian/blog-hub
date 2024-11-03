/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.Valid;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
@ManagedBean
@RequestScoped
public class UserController {

    @Valid
    private Users user = new Users();
    private UserService userService = new UserService();
    private String finalPassword = null;
    private String confirmPassword = null;
    private boolean error = false;
    private FacesContext facesContext;

    public UserController() {
        this.facesContext = FacesContext.getCurrentInstance(); // Default in JSF environment
    }

    // For testing, allow FacesContext to be injected
    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }
    
    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    
    public String getFinalPassword() {
        return finalPassword;
    }

    public void setFinalPassword(String finalPassword) {
        this.finalPassword = finalPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public String register() {
        String result = userService.register(user);
        System.out.println(result);
        if (result.equals("success")) {
            System.out.println("Berhasil");
            return "login.xhtml?faces-redirect=true";
        } else {
            setError(true);
            System.out.println(facesContext);
            System.out.println("bb");
            facesContext.addMessage("formin:testing", new FacesMessage(FacesMessage.SEVERITY_ERROR, result, null));
            return null;
        }
    }

    public void validateFinalPassword(FacesContext fc, UIComponent c, Object value) throws
            ValidatorException {
        setFinalPassword(value.toString());
    }

    public void validateConfirmPassword(FacesContext fc, UIComponent c, Object value) throws
            ValidatorException {
        String confirmPassword = value.toString();
        System.out.println("Confirm: " + confirmPassword + " || " + "Final: " +  getFinalPassword());
        if (!confirmPassword.equals(getFinalPassword())) {
            FacesMessage message = new FacesMessage();
            message.setSummary("Password does not match");
            throw new ValidatorException(message);
        } else {
            System.out.println("password matched.");
        }
    }
}
