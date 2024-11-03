/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CommentDAO;
import DAO.PostDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import pojo.Comments;
import pojo.Posts;
import pojo.Tags;

/**
 *
 * @author silviarianto
 */
@ManagedBean
@ViewScoped
public class BlogController {

    private int postId;
    private Comments comment = new Comments();
    private List<Comments> comments;
    private List<Tags> tagList;
    private Posts post;
    private PostDAO postDAO;
    private CommentDAO commentDAO;

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    public BlogController() {
        postDAO = new PostDAO();
        commentDAO = new CommentDAO();
    }

    public void loadPost() {
        if (postId > 0) {
            post = postDAO.getPostById(postId);
            if (post != null) {
                comments = new ArrayList<>(post.getCommentses());
                tagList = new ArrayList<>(post.getTagses());
            }
        }
    }

    public String addComment() {
        if (!sessionController.isLoggedIn()) {
            System.out.println("belum login");
            return "login.xhtml?faces-redirect=true";
        }
        if (postId > 0) {
            comment.setPosts(post);
            comment.setUsers(sessionController.getCurrentUser());
            if (commentDAO.save(comment)) {
                System.out.println("success");
                return null;
            }
        }
        System.out.println("failed");
        return null;
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
    
    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Comments getComment() {
        return comment;
    }

    public void setComment(Comments comment) {
        this.comment = comment;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> commments) {
        this.comments = commments;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public List<Tags> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tags> tagsList) {
        this.tagList = tagsList;
    }
}
