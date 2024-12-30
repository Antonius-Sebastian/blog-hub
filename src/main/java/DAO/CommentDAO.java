/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.BlogHubUtil;
import pojo.Comments;

/**
 *
 * @author silviarianto
 */
public class CommentDAO {

    public boolean save(Comments comment) {
        boolean success = true;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(comment);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] CommentDAO.save: " + e.getMessage());
            success = false;
        } finally {
            session.close();
        }
        return success;
    }
}
