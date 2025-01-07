package com.example.cs202igorromanic6138pz.PwdGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class PasswordGenerator {
    String chars = "abcdefghijlkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!=?_()";
    private int len;

    public PasswordGenerator(int pwgLen)
    {
       this.len =  pwgLen;
    }

    public String Generate()
    {
        int length = this.len;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++)
        {
            sb.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
        }

        return sb.toString();
    }
}
