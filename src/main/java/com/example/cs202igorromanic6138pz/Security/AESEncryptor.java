package com.example.cs202igorromanic6138pz.Security;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryptor
{
    private final String ALGORITHM = "AES";
    private final String TRANSFORMATION = "AES/GCM/NoPadding";
    private final int KEY_SIZE = 256;
    private final int IV_SIZE = 12;
    private final int TAG_SIZE = 128;

    private final String BASE64_KEY = "SU+H7Hwk6M1NJS3NXXzEMRguMrPAoNd+/Gbn1b5ZBHo=";
    private final byte[] KEY_BYTES = Base64.getDecoder().decode(BASE64_KEY);
    private final SecretKey SECRET_KEY = new SecretKeySpec(KEY_BYTES, ALGORITHM);

    public String encrypt(String plainText) throws Exception
    {
        byte[] iv = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(plainText.getBytes());

        byte[] encryptedData = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, encryptedData, 0, iv.length);
        System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decrypt(String encryptedText) throws Exception
    {
        byte[] encryptedData = Base64.getDecoder().decode(encryptedText);

        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(encryptedData, 0, iv, 0, IV_SIZE);

        byte[] cipherText = new byte[encryptedData.length - IV_SIZE];
        System.arraycopy(encryptedData, IV_SIZE, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY, gcmParameterSpec);

        return new String(cipher.doFinal(cipherText));
    }
}
