package com.example.cs202igorromanic6138pz.Security;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryptor
{
    private final String ALGORITHM = "AES";
    private final String TRANSFORMATION = "AES/GCM/NoPadding";
    private final int KEY_SIZE = 256;
    private final int IV_SIZE = 12;
    private final int TAG_SIZE = 128;

    private SecretKey secretKey;

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public AESEncryptor()
    {
        this.secretKey = fetchKey();
    }

    public String getB64Key()
    {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public SecretKey fetchKey()
    {
        try
        {
            Document doc = Jsoup.connect("https://www.digitalsanctuary.com/aes-key-generator-free").get();
            String b64key = doc.getElementsByTag("strong").text();
            byte[] keyBytes = Base64.getDecoder().decode(b64key);
            return new SecretKeySpec(keyBytes, ALGORITHM);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            String backupKey = "SU+H7Hwk6M1NJS3NXXzEMRguMrPAoNd+/Gbn1b5ZBHo=";
            return new SecretKeySpec(backupKey.getBytes(), ALGORITHM);
        }
    }

    public SecretKey createKeyFromExisting(String aesKey)
    {
        byte[] keyBytes = Base64.getDecoder().decode(aesKey);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public String encrypt(String text) throws Exception
    {
        byte[] iv = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(text.getBytes());

        byte[] encryptedData = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, encryptedData, 0, iv.length);
        System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decrypt(String encrypted) throws Exception {
        byte[] encryptedData = Base64.getDecoder().decode(encrypted);

        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(encryptedData, 0, iv, 0, IV_SIZE);

        byte[] cipherText = new byte[encryptedData.length - IV_SIZE];
        System.arraycopy(encryptedData, IV_SIZE, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
