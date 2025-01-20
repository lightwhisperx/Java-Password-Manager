package com.example.cs202igorromanic6138pz.Database;

import com.example.cs202igorromanic6138pz.Security.AESEncryptor;
import com.example.cs202igorromanic6138pz.User.Credentials;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperations
{
    private DB database;
    private AESEncryptor encryptor;

    public DBOperations(DB database) {
        this.database = database;
        this.encryptor = new AESEncryptor();
    }

    public void insertIntoPassDB(Connection con, String platform, String username, String password)
    {
        con = database.getCon();
        String saveEntry = String.format("INSERT INTO passwords(platform, username, password) VALUES ('%s', '%s', '%s')",
                platform, username, password);

        try
        {
            database.setSt(con.createStatement());
            int rs = database.getSt().executeUpdate(saveEntry);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void deleteFromPassDB(Connection con, String platform, String username, String password)
    {
        con = database.getCon();
        String deleteEntry = String.format("DELETE FROM passwords WHERE platform = '%s' AND username = '%s' AND password = '%s';",
                platform, username, password);

        try
        {
            database.setSt(con.createStatement());
            int rs = database.getSt().executeUpdate(deleteEntry);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void editInPassDB(Connection con, String platformOld, String usernameOld, String passwordOld, String platformNew, String usernameNew, String passwordNew)
    {
        con = database.getCon();

        try
        {
            String editEntry = String.format("UPDATE passwords SET platform = '%s', username = '%s', password = '%s' " +
                            "WHERE platform = '%s' AND username = '%s' AND password = '%s'",
                    platformOld, usernameOld, passwordOld, platformNew, usernameNew, encryptor.encrypt(passwordNew));

            database.setSt(con.createStatement());
            int rs = database.getSt().executeUpdate(editEntry);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void insertIntoMasterTable(Connection con, String masterPass)
    {
        con = database.getCon();
        String insertMaster = String.format("INSERT INTO masterpass(masterPass) VALUES ('%s')", masterPass);

        try
        {
            database.setSt(con.createStatement());
            int rowsAffected = database.getSt().executeUpdate(insertMaster);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public String selectFromMaster(Connection con)
    {
        con = database.getCon();
        String selectEntry = String.format("SELECT masterPass FROM masterpass");
        String password = null;

        try
        {
            database.setSt(con.createStatement());
            ResultSet rs = database.getSt().executeQuery(selectEntry);

            if(rs.next())
            {
                password = rs.getString("masterPass");
            }

            rs.close();
            database.getSt().close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return password;
    }

    public ObservableList<Credentials> fetchData(Connection con)
    {
        ObservableList<Credentials> data = FXCollections.observableArrayList();

        try
        {
            String fetchQuery = String.format("SELECT platform, username, password FROM passwords");
            ResultSet rs = database.getSt().executeQuery(fetchQuery);

            while(rs.next())
            {
                data.addAll(new Credentials(rs.getString("platform"), rs.getString("username"), encryptor.decrypt(rs.getString("password"))));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return data;
    }

}
