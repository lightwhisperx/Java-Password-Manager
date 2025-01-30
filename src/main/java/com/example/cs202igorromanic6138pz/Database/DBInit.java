package com.example.cs202igorromanic6138pz.Database;

import java.sql.*;

public class DBInit {
    private Connection con;
    private CallableStatement st;

    public Connection getCon() {
        return con;
    }

    public CallableStatement getSt() {
        return st;
    }

    public void setSt(CallableStatement st) {
        this.st = st;
    }

    public DBInit() {
        try
        {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/passdb", "root", "");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public CallableStatement prepareCall(String sql) throws SQLException
    {
        return con.prepareCall(sql);
    }
}
