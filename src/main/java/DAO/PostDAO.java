/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.BlogHubUtil;
import pojo.Posts;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class PostDAO {

    public List<Posts> getPosts() {
        List<Posts> posts;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Posts");
            posts = query.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] PostDAO.getPosts: " + e.getMessage());
            posts = null;
        } finally {
            session.close();
        }
        return posts;
    }

    public List<Posts> getNewPosts() {
        List<Posts> posts;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Posts p ORDER BY p.createdAt DESC");
            query.setMaxResults(5);
            posts = query.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] PostDAO.getNewPosts: " + e.getMessage());
            posts = null;
        } finally {
            session.close();
        }
        return posts;
    }

    public List<Posts> getPostByUser(Users users) {
        List<Posts> posts;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Posts WHERE users= :users");
            query.setParameter("users", users);
            posts = query.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] PostDAO.getPostByUser: " + e.getMessage());
            posts = null;
        } finally {
            session.close();
        }
        return posts;
    }

    public Posts getPostById(int postId) {
        Posts posts;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Posts p JOIN FETCH p.users JOIN FETCH p.tagses LEFT JOIN FETCH p.commentses c LEFT JOIN FETCH c.users WHERE p.id = :id");
            query.setParameter("id", postId);
            posts = (Posts) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] PostDAO.getPostById: " + e.getMessage());
            posts = null;
        } finally {
            session.close();
        }
        return posts;
    }

    public boolean save(Posts post) {
        boolean success = true;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(post);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] PostDAO.save: " + e.getMessage());
            success = false;
       } finally {
            session.close();
        }
        return success;
    }

    public boolean update(Posts post) {
        boolean success = true;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(post);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] PostDAO.update: " + e.getMessage());
            success = false;
        } finally {
            session.close();
        }
        return success;
    }

    public boolean delete(Posts post) {
        boolean success = true;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(post);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] PostDAO.delete: " + e.getMessage());
            success = false;
        } finally {
            session.close();
        }
        return success;
    }
}
