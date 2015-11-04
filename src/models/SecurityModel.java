package models;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class SecurityModel {

    public boolean authenticate(char[] attemptedPassword, byte[] encryptedPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        //Encrypt clear-text password using the same salt that wa used to encrypt original password
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);

        //Authentication succeeds if value returned is True
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);

    }

    public byte[] getEncryptedPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //Use PBKDF2 with SHA-1 for hashing algorithm
        String algorithm = "PBKDF2WithHmacSHA1";

        //160 bit hash
        int derivedKeyLength = 160;

        //Iteration count, at least 1000
        int iterations = 20000;

        KeySpec spec = new PBEKeySpec(password, salt, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

        return f.generateSecret(spec).getEncoded();
    }

    public byte[] generateSalt() throws NoSuchAlgorithmException {

        //Use secure random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        //Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;

    }
}
