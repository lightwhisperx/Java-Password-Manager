package com.example.cs202igorromanic6138pz.PasswordCheck;

public class PasswordStrengthChecker
{
    private String password;

    public PasswordStrengthChecker(String password) {
        this.password = password;
    }

    public String checkStrength(String password)
    {
        if (password == null || password.length() < 8) {
            return "Password is very weak!";
        }

        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&]).{8,}$")) {
            return "Password is strong!";
        }

        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            return "Password is okay!";
        }

        if (password.matches("^(?=.*[a-z])(?=.*\\d).{8,}$")) {
            return "Password is weak!";
        }

        return "Password is very weak!";
    }
}
