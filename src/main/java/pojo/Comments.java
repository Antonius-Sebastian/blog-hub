package pojo;
// Generated Sep 19, 2024 11:28:30 AM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Comments generated by hbm2java
 */
public class Comments implements java.io.Serializable {

    private Integer id;
    private String content;
    private Date createdAt;
    private Posts posts;
    private Users users;

    public Comments() {
    }
    
    public Comments(Posts posts, Users users) {
        this.posts = posts;
        this.users = users;
    }

    public Comments(String content, Date createdAt, Posts posts, Users users) {
        this.content = content;
        this.createdAt = createdAt;
        this.posts = posts;
        this.users = users;
    }
    
    public Comments(int id, String content, Date createdAt, Posts posts, Users users) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.posts = posts;
        this.users = users;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Posts getPosts() {
        return this.posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}