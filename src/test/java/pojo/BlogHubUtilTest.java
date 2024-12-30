package pojo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BlogHubUtilTest {

    private SessionFactory mockSessionFactory;

    @Before
    public void setUp() {
        mockSessionFactory = Mockito.mock(SessionFactory.class);
    }

    @Test
    public void testGetSessionFactory() {
        BlogHubUtil.setSessionFactory(mockSessionFactory);
        assertNotNull(BlogHubUtil.getSessionFactory());
        assertEquals(mockSessionFactory, BlogHubUtil.getSessionFactory());
    }

    @Test
    public void testSetSessionFactory() {
        BlogHubUtil.setSessionFactory(mockSessionFactory);
        assertEquals(mockSessionFactory, BlogHubUtil.getSessionFactory());
    }

    @Test(expected = ExceptionInInitializerError.class)
    public void testSessionFactoryInitializationFailure() {
        Configuration mockConfiguration = Mockito.mock(Configuration.class);
        when(mockConfiguration.configure()).thenThrow(new RuntimeException("Simulated Configuration failure"));
        
        try {
            BlogHubUtil.setSessionFactory(null); 
            BlogHubUtil.getSessionFactory().openSession();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
