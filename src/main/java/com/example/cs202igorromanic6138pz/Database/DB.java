package com.example.cs202igorromanic6138pz.Database;

import java.sql.*;

public class DB {
    private Connection con;
    private Statement st;

    public void setSt(Statement st) {
        this.st = st;
    }

    public Connection getCon() {
        return con;
    }

    public Statement getSt() {
        return st;
    }

    public DB() {
        try
        {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/passdb", "root", "");
            this.st = con.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
