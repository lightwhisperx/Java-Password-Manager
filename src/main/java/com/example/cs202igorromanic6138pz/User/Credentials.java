package com.example.cs202igorromanic6138pz.User;

public class Credentials
{
    private String  Platform;
    private String  Username;
    private String  Password;

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

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
