/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pojo.BlogHubUtil;
import pojo.Posts;
import pojo.Tags;

/**
 *
 * @author silviarianto
 */
public class TagDAOTest {

    private TagDAO tagDAO;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public TagDAOTest() {
    }

    @Before
    public void setUp() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        BlogHubUtil.setSessionFactory(sessionFactory);
        tagDAO = new TagDAO();
    }

    @After
    public void tearDown() {
        BlogHubUtil.setSessionFactory(null);
    }

    /**
     * Test of getTags method, of class TagDAO.
     */
    @Test
    public void testGetTags_Success() {
//      Expected result
        List<Tags> expResult = new ArrayList<>();
        expResult.add(new Tags(1, "tag-name-1", "tag-desc-1", new HashSet<>()));
        expResult.add(new Tags(2, "tag-name-2", "tag-desc-2", new HashSet<>()));
        expResult.add(new Tags(3, "tag-name-3", "tag-desc-3", new HashSet<>()));

        Query query = mock(Query.class);
        when(session.createQuery("FROM Tags")).thenReturn(query);
        when(query.list()).thenReturn(expResult);

        List<Tags> result = tagDAO.getTags();

//      Assert the result
        for (int i = 0; i < result.size(); i++) {
            assertTagEquals(result.get(i), expResult.get(i));
        }
        verify(session).createQuery("FROM Tags");
        verify(query).list();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testGetTags_Failure() {
//      Create an exception
        when(session.createQuery("FROM Tags")).thenReturn(null);
        
        List<Tags> result = tagDAO.getTags();

        assertNull(result);
        verify(transaction).rollback();
        verify(session).close();
    }

    /**
     * Test of getTags(List) method, of class TagDAO.
     */
    @Test
    public void testGetTagsList_Success() {
        final String q = "FROM Tags WHERE id IN (:ids)";
        List<Integer> tagIds = new ArrayList<>();
        tagIds.add(1);
        tagIds.add(2);
        tagIds.add(3);
        
//      Expected result
        List<Tags> expResult = new ArrayList<>();
        expResult.add(new Tags(1, "tag-name-1", "tag-desc-1", new HashSet<>()));
        expResult.add(new Tags(2, "tag-name-2", "tag-desc-2", new HashSet<>()));
        expResult.add(new Tags(3, "tag-name-3", "tag-desc-3", new HashSet<>()));

        Query query = mock(Query.class);
        when(session.createQuery(q)).thenReturn(query);
        when(query.setParameter("ids", tagIds)).thenReturn(query);
        when(query.list()).thenReturn(expResult);

        List<Tags> result = tagDAO.getTags(tagIds);

//      Assert the result
        for (int i = 0; i < result.size(); i++) {
            assertTagEquals(result.get(i), expResult.get(i));
        }
        verify(session).createQuery(q);
        verify(query).list();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testGetTagsList_Failure() {
        List<Integer> tagIds = new ArrayList<>();
        tagIds.add(1);
        
//      Create an exception
        when(session.createQuery("FROM Tags WHERE id IN (:ids)")).thenReturn(null);
                
        List<Tags> result = tagDAO.getTags(tagIds);
        
        assertNull(result);
        verify(transaction).rollback();
        verify(session).close();
    }
    
    public void assertTagEquals(Tags result, Tags expected) {
        assertEquals(result.getId(), expected.getId());
        assertEquals(result.getName(), expected.getName());
        assertEquals(result.getDescription(), expected.getDescription());
        assertEquals(result.getPostses(), expected.getPostses());
    }
}
