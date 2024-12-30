package pojo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

public class TagsTest {

    private Tags tag;
    private Set<Posts> postsSet;

    @Before
    public void setUp() {
        postsSet = new HashSet<>();
        tag = new Tags();
    }

    @Test
    public void testTagsConstructorWithMandatoryFields() {
        String name = "Technology";

        Tags tags = new Tags(name);

        assertEquals(name, tags.getName());
    }

    @Test
    public void testTagsConstructorWithAllFields() {
        String name = "Technology";
        String description = "Posts related to technology";
        Set posts = new HashSet();

        Tags tags = new Tags(name, description, posts);

        assertEquals(name, tags.getName());
        assertEquals(description, tags.getDescription());
        assertEquals(posts, tags.getPostses());
    }

    @Test
    public void testGettersAndSetters() {
        // Test ID
        tag.setId(123);
        assertEquals(Integer.valueOf(123), tag.getId());

        // Test Name
        String name = "Technology";
        tag.setName(name);
        assertEquals(name, tag.getName());

        // Test Description
        String description = "Posts related to technology.";
        tag.setDescription(description);
        assertEquals(description, tag.getDescription());

        // Test Posts Set
        tag.setPostses(postsSet);
        assertEquals(postsSet, tag.getPostses());
    }

}
