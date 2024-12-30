/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.BlogHubUtil;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class UserDAO {

    public boolean save(Users user) {
        boolean success = true;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] UserDAO.save: " + e.getMessage());
            success = false;
        } finally {
            session.close();
        }
        return success;
    }

    public Users getByUsername(String username) {
        Users user;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Users WHERE username = :username");
            query.setParameter("username", username);
            user = (Users) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] UserDAO.getByUsername: " + e.getMessage());
            user = null;
        } finally {
            session.close();
        }
        return user;
    }

    public Users getByEmail(String email) {
        Users user;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Users WHERE email = :email");
            query.setParameter("email", email);
            user = (Users) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] UserDAO.getByEmail: " + e.getMessage());
            user = null;
        } finally {
            session.close();
        }
        return user;
    }
}
