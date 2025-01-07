package com.example.cs202igorromanic6138pz;

public class Credentials
{
    private String  Platform;
    private String  Username;
    private String  Password;

    public Credentials()
    {
        this.Platform = "";
        this.Username = "";
        this.Password = "";
    }

    public Credentials(String platform, String username, String password) {
        this.Platform = platform;
        this.Username = username;
        this.Password = password;
    }

    @Override
    public String toString() {
        return "Platform: " + Platform + "\nUsername: " + Username + "\nPassword: " + Password;
    }
}
