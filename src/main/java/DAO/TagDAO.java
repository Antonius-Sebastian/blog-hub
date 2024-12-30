/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.BlogHubUtil;
import pojo.Tags;

/**
 *
 * @author silviarianto
 */
public class TagDAO {

    public List<Tags> getTags() {
        List<Tags> tags;
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Tags");
            tags = query.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] TagDAO.getTags: " + e.getMessage());
            tags = null;
        } finally {
            session.close();
        }
        return tags;
    }

    public List<Tags> getTags(List<Integer> tagIds) {
        List<Tags> tags = new ArrayList<>();
        Session session = BlogHubUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Tags WHERE id IN (:ids)");
            query.setParameterList("ids", tagIds);
            tags = query.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("[ERROR] TagDAO.getTags: " + e.getMessage());
            tags = null;
        } finally {
            session.close();
        }
        return tags;
    }
}
