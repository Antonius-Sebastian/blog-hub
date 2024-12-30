/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.UserDAO;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import pojo.Users;

/**
 *
 * @author silviarianto
 */
public class UserService {

    UserDAO userDAO = new UserDAO();

    public String register(Users user) {
        String hash = hashPassword(user.getPassword());
        if (hash == null) {
            return "There's something wrong with the server. Try again later.";
        }
        if (userDAO.getByUsername(user.getUsername()) != null) {
            return "Username already exists.";
        }
        if (userDAO.getByEmail(user.getEmail()) != null) {
            return "Email already exists.";
        }
        user.setPassword(hash);
        if (!userDAO.save(user)) {
            return "There's something wrong with the server. Try again later.";
        }
        return "success";
    }

    public static String hashPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            
            String encodedHash = Base64.getEncoder().encodeToString(hash);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            return encodedHash + ":" + encodedSalt;
        } catch (Exception e) {
            System.out.println("UserService.hashPassword: " + e.getMessage());
        }
        return null;
    }

    public Users login(String email, String password) {
        Users user = userDAO.getByEmail(email);
        try {
            String[] parts = user.getPassword().split(":");
            String storedHash = parts[0];
            String storedSalt = parts[1];

            byte[] salt = Base64.getDecoder().decode(storedSalt);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] computedHash = factory.generateSecret(spec).getEncoded();

            String encodedComputedHash = Base64.getEncoder().encodeToString(computedHash);

            // Compare the computed hash with the stored hash
            if (encodedComputedHash.equals(storedHash)) {
                return user;
            }

        } catch (Exception e) {
            System.out.println("UserService.login: " + e.getMessage());
        }
        return null;
    }

}
