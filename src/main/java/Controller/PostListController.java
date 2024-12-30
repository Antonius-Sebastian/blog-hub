/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import pojo.Posts;

/**
 *
 * @author silviarianto
 */
@ManagedBean
@ViewScoped
public class PostListController {

    private List<Posts> blogPosts;
    private List<Posts> userPosts;
    private PostDAO postDAO;

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    public PostListController() {
        postDAO = new PostDAO();
    }
    
    public List<Posts> loadBlogPosts() {
        blogPosts = postDAO.getPosts();
        return blogPosts;
    }

    public List<Posts> loadNewPosts() {
        blogPosts = postDAO.getNewPosts();
        return blogPosts;
    }

    public List<Posts> loadUserPosts() {
        userPosts = postDAO.getPostByUser(sessionController.getCurrentUser());
        return userPosts;
    }

    public List<Posts> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(List<Posts> blogPosts) {
        this.blogPosts = blogPosts;
    }

    public List<Posts> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Posts> userPosts) {
        this.userPosts = userPosts;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
    
    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }
}
