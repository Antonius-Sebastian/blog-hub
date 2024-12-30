/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostDAO;
import DAO.TagDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import pojo.Posts;
import pojo.Tags;

/**
 *
 * @author silviarianto
 */
@ManagedBean
@ViewScoped
public class PostController {

    private Posts post = new Posts();
    private List<Tags> availableTags;
    private List<Integer> selectedTags;
    private PostDAO postDAO;
    private TagDAO tagDAO;

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    private int postId;

    public PostController() {
        postDAO = new PostDAO();
        tagDAO = new TagDAO();
        loadTags();
    }
    
    public void loadPost() {
        if (postId > 0) {
            post = postDAO.getPostById(postId);
            for (Tags tag : post.getTagses()) {
                selectedTags.add(tag.getId());
            }
        }
    }

    public String addPost() {
        List<Integer> tagIds = new ArrayList<>();

//        Check @selectedTags if it's integer
        for (Object tagId : selectedTags) {
            if (tagId instanceof String) {
                tagIds.add(Integer.parseInt((String) tagId));
            } else if (tagId instanceof Integer) {
                tagIds.add((Integer) tagId);
            }
        }

//        Get Tags Set
        List<Tags> tagList = tagDAO.getTags(tagIds);
        Set<Tags> tagSet = new HashSet<>();
        for (Tags tag : tagList) {
            tagSet.add(tag);
        }

//        Save
        post.setUsers(sessionController.getCurrentUser());
        post.setTagses(tagSet);

        if (postDAO.save(post)) {
            System.out.println("berhasil");
            return "index.xhtml?faces-redirect=true";
        }
        System.out.println("failed");
        return null;
    }

    public String deletePost() {
        if (postId == 0) {
            return null;
        }
        post = postDAO.getPostById(postId);
        postDAO.delete(post);

        return null;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public void loadTags() {
        availableTags = tagDAO.getTags();
        selectedTags = new ArrayList<Integer>();
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public List<Tags> getAvailableTags() {
        return availableTags;
    }

    public void setAvailableTags(List<Tags> availableTags) {
        this.availableTags = availableTags;
    }

    public List<Integer> getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags(List<Integer> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }
    
    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }
}
