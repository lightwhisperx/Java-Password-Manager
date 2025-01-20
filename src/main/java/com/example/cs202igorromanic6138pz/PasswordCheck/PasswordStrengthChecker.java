package com.example.cs202igorromanic6138pz.PasswordCheck;

public class PasswordStrengthChecker
{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PasswordStrengthChecker(String password) {
        this.password = password;
    }

    public String checkStrength(String password)
    {
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%&])(?=.{8,})"))
        {
            return "Password is strong!";
        }
        else if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})"))
        {
            return "Password is okay!";
        }
        else if (password.matches("^(?=.*[a-z])(?=.*[0-9])(?=.{8,})"))
        {
            return "Password is weak!";
        }
        else
        {
            return "Password is very weak!";
        }
    }
}
