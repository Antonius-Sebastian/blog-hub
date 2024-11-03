package pojo;

import org.junit.Before;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.HashSet;
import static org.junit.Assert.*;
import java.util.Set;

public class UsersTest {

    private Users user;
    private Validator validator;

    @Before
    public void setUp() {
        // Initialize a validator for validation constraints testing
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        // Initialize the Users object
        user = new Users();
    }
    
    @Test
    public void testUsersConstructorWithMandatoryFields() {
        String username = "JohnDoe";
        String email = "john@example.com";
        String password = "securepassword";
        Date createdAt = new Date();

        Users userConst = new Users(username, email, password, createdAt);

        assertEquals(username, userConst.getUsername());
        assertEquals(email, userConst.getEmail());
        assertEquals(password, userConst.getPassword());
        assertEquals(createdAt, userConst.getCreatedAt());
    }

    @Test
    public void testUsersConstructorWithAllFields() {
        String username = "JohnDoe";
        String email = "john@example.com";
        String password = "securepassword";
        Date createdAt = new Date();
        Set comments = new HashSet();
        Set posts = new HashSet();

        Users userConst = new Users(1, username, email, password, createdAt, comments, posts);

        assertEquals(username, userConst.getUsername());
        assertEquals(email, userConst.getEmail());
        assertEquals(password, userConst.getPassword());
        assertEquals(createdAt, userConst.getCreatedAt());
        assertEquals(comments, userConst.getCommentses());
        assertEquals(posts, userConst.getPostses());
    }

    @Test
    public void testGettersAndSetters() {
        // Test ID
        user.setId(1);
        assertEquals(Integer.valueOf(1), user.getId());

        // Test Username
        String username = "JohnDoe";
        user.setUsername(username);
        assertEquals(username, user.getUsername());

        // Test Email
        String email = "john@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());

        // Test Password
        String password = "securepassword";
        user.setPassword(password);
        assertEquals(password, user.getPassword());

        // Test CreatedAt
        Date createdAt = new Date();
        user.setCreatedAt(createdAt);
        assertEquals(createdAt, user.getCreatedAt());

        // Test Comments Set
        Set commentsSet = new HashSet();
        user.setCommentses(commentsSet);
        assertEquals(commentsSet, user.getCommentses());

        // Test Posts Set
        Set postsSet = new HashSet();
        user.setPostses(postsSet);
        assertEquals(postsSet, user.getPostses());
    }

    @Test
    public void testValidUser() {
        // Valid user input
        user.setUsername("JohnDoe");
        user.setEmail("john@example.com");
        user.setPassword("securepassword");
        user.setCreatedAt(new Date());

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertTrue(violations.isEmpty()); // No violations, valid user
    }

    @Test
    public void testInvalidUserWithBlankUsername() {
        // Invalid user with blank username
        user.setUsername("    ");
        user.setEmail("john@example.com");
        user.setPassword("securepassword");
        user.setCreatedAt(new Date());

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertFalse(violations.isEmpty()); // There should be violations

        ConstraintViolation<Users> violation = violations.iterator().next();
        assertEquals("Username is required", violation.getMessage());
    }

    @Test
    public void testInvalidEmail() {
        // Invalid email format
        user.setUsername("JohnDoe");
        user.setEmail("invalid-email");
        user.setPassword("securepassword");
        user.setCreatedAt(new Date());

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertFalse(violations.isEmpty()); // There should be violations

        ConstraintViolation<Users> violation = violations.iterator().next();
        assertEquals("Email should be valid", violation.getMessage());
    }

    @Test
    public void testInvalidPassword() {
        // Invalid password (too short)
        user.setUsername("JohnDoe");
        user.setEmail("john@example.com");
        user.setPassword("short");
        user.setCreatedAt(new Date());

        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        assertFalse(violations.isEmpty()); // There should be violations

        ConstraintViolation<Users> violation = violations.iterator().next();
        assertEquals("Password must be at least 8 characters long", violation.getMessage());
    }

}
