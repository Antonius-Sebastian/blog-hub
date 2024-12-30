/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostDAO;
import DAO.TagDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static org.mockito.Mockito.*;
import pojo.Comments;
import pojo.Posts;
import pojo.Tags;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class PostControllerTest {

    private SessionController sessionController;
    private PostController postController;
    private PostDAO postDAO;
    private TagDAO tagDAO;

    public PostControllerTest() {
    }

    @Before
    public void setUp() {
        postController = new PostController();
        sessionController = mock(SessionController.class);
        postDAO = mock(PostDAO.class);
        tagDAO = mock(TagDAO.class);

        postController.setSessionController(sessionController);
        postController.setPostDAO(postDAO);
        postController.setTagDAO(tagDAO);
    }

    @After
    public void tearDown() {
        postController.setAvailableTags(null);
        postController.setPost(null);
        postController.setPostId(0);
        postController.setSelectedTags(null);
    }

    @Test
    public void testLoadPost_success() {
        Set<Tags> tags = new HashSet<>();
        tags.add(new Tags(1, "tag-name-1", "tag-desc-1", new HashSet<>()));
        tags.add(new Tags(2, "tag-name-2", "tag-desc-2", new HashSet<>()));
        tags.add(new Tags(3, "tag-name-3", "tag-desc-3", new HashSet<>()));

        Posts mockPost = new Posts(1, new Users(), "title-1", "content-1", new Date(2024, 10, 31), tags, new HashSet<>());

        postController.setPostId(1);
        when(postDAO.getPostById(postController.getPostId())).thenReturn(mockPost);

        postController.loadPost();

        assertEquals(mockPost, postController.getPost());
        verify(postDAO).getPostById(postController.getPostId());
    }

    @Test
    public void testLoadPost_Failure() {
        postController.setPost(null);
        postController.setPostId(0);
        postController.loadPost();

        assertNull(postController.getPost());
        verify(postDAO, never()).getPostById(postController.getPostId());
    }

    @Test
    public void testAddPost_Success() {
        Users mockUser = mock(Users.class);
        Posts post = mock(Posts.class);
        List<Tags> tagList = new ArrayList<>();

        tagList.add(new Tags(1, "tag-name-1", "tag-desc-1", new HashSet<>()));
        tagList.add(new Tags(2, "tag-name-2", "tag-desc-2", new HashSet<>()));
        tagList.add(new Tags(3, "tag-name-3", "tag-desc-3", new HashSet<>()));

        post.setId(10);
        post.setContent("Content");
        post.setCreatedAt(new Date());
        post.setTitle("Title");

        Set<Comments> comments = new HashSet<>();
        comments.add(new Comments());
        post.setCommentses(comments);
        
        List<Integer> tagIds = Arrays.asList(1, 2);
                
        when(tagDAO.getTags(tagIds)).thenReturn(tagList);
        when(sessionController.getCurrentUser()).thenReturn(mockUser);
        when(postDAO.save(post)).thenReturn(true);

        postController.setPost(post);
        postController.setSelectedTags(tagIds);

        String result = postController.addPost();

        assertEquals("index.xhtml?faces-redirect=true", result);

        verify(tagDAO).getTags(tagIds);
        verify(sessionController).getCurrentUser();
        verify(postDAO).save(post);
    }

    @Test
    public void testAddPost_Failure() {
        String result = postController.addPost();
        assertNull(result);
    }

    @Test
    public void testDeletePost_Success() {
        Posts mockPost = mock(Posts.class);
        postController.setPostId(1);
        when(postDAO.getPostById(1)).thenReturn(mockPost);
        when(postDAO.delete(mockPost)).thenReturn(true);

        String result = postController.deletePost();

        assertNull(result);
        verify(postDAO).getPostById(1);
        verify(postDAO).delete(mockPost);
    }

    @Test
    public void testDeletePost_Failed() {
        Posts mockPost = mock(Posts.class);
        postController.setPostId(0);
        String result = postController.deletePost();

        assertNull(result);
        verify(postDAO, never()).getPostById(0);
        verify(postDAO, never()).delete(mockPost);
    }

    @Test
    public void testGettersAndSetters() {
        // Test availableTags
        List<Tags> availableTags = new ArrayList<>();
        availableTags.add(new Tags(1, "tag-name-1", "tag-desc-1", new HashSet<>()));
        availableTags.add(new Tags(2, "tag-name-2", "tag-desc-2", new HashSet<>()));
        availableTags.add(new Tags(3, "tag-name-3", "tag-desc-3", new HashSet<>()));

        postController.setAvailableTags(availableTags);
        for (int i = 0; i < availableTags.size(); i++) {
            assertTagEquals(availableTags.get(i), postController.getAvailableTags().get(i));

        }

        // Test Selected Tags
        List<Integer> selectedTags = new ArrayList<>();
        selectedTags.add(1);
        selectedTags.add(2);
        selectedTags.add(3);

        postController.setSelectedTags(selectedTags);
        for (int i = 0; i < selectedTags.size(); i++) {
            assertEquals(selectedTags.get(i), postController.getSelectedTags().get(i));
        }

//      Test Session controller
        SessionController mockSC = new SessionController();
        postController.setSessionController(mockSC);
        assertSame(mockSC, postController.getSessionController());
    }

    public void assertTagEquals(Tags result, Tags expected) {
        assertEquals(result.getId(), expected.getId());
        assertEquals(result.getName(), expected.getName());
        assertEquals(result.getDescription(), expected.getDescription());
        assertEquals(result.getPostses(), expected.getPostses());
    }
}
