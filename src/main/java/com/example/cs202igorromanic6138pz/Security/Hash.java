package com.example.cs202igorromanic6138pz.Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash
{
    private final String ALGORITHM = "SHA-256";
    private MessageDigest md;

    public Hash()
    {
        try
        {
            md = MessageDigest.getInstance(ALGORITHM);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String toHash(String input)
    {
        try
        {
            this.md.update(input.getBytes());
            return bytesToHex(this.md.digest());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes)
    {
        StringBuffer result = new StringBuffer();

        for(byte b : bytes)
        {
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }
}
