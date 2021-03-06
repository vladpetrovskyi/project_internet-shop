package com.internetshop.util;

import com.internetshop.exceptions.HashingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashUtil {

    private HashUtil() {
    }

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        StringBuilder hashedPassword = new StringBuilder();
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            for (byte b : hash) {
                hashedPassword.append(String.format("%02x", b));
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new HashingException("Could not hash password.", e);
        }
        return hashedPassword.toString();
    }

    public static boolean isValid(String givenPassword, String truePassword, byte[] salt) {
        return hashPassword(givenPassword, salt).equals(truePassword);
    }
}
