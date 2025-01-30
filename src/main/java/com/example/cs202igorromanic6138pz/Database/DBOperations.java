package com.example.cs202igorromanic6138pz.Database;

import com.example.cs202igorromanic6138pz.Security.AESEncryptor;
import com.example.cs202igorromanic6138pz.User.Credentials;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBOperations
{
    private DBInit database;
    private AESEncryptor encryptor;

    public DBOperations(DBInit database) {
        this.database = database;
        this.encryptor = new AESEncryptor();
    }

    public void insertIntoPassDB(Connection con, String platform, String username, String password)
    {
        con = database.getCon();

        try
        {
            database.setSt(con.prepareCall("{call save_credentials(?, ?, ?, ?)}"));
            database.getSt().setString(1, platform);
            database.getSt().setString(2, username);
            database.getSt().setString(3, encryptor.encrypt(password));
            database.getSt().setString(4, encryptor.getB64Key());
            database.getSt().execute();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void deleteFromPassDB(Connection con, String platform, String username)
    {
        con = database.getCon();

        try
        {
            database.setSt(con.prepareCall("{call delete_cred_by_id(?, ?)}"));
            database.getSt().setString(1, platform);
            database.getSt().setString(2, username);
            database.getSt().execute();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void insertIntoMasterTable(Connection con, String masterPass)
    {
        con = database.getCon();

        try
        {
            database.setSt(con.prepareCall("{call insert_into_master(?)}"));
            database.getSt().setString(1, masterPass);
            database.getSt().execute();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public ObservableList<Credentials> fetchData(Connection con)
    {
        ObservableList<Credentials> data = FXCollections.observableArrayList();

        try
        {
            database.setSt(con.prepareCall("{call fetch_data()}"));
            ResultSet rs = database.getSt().executeQuery();

            while(rs.next())
            {
                String platform = rs.getString("platform");
                String username = rs.getString("username");
                String encryptedPassword = rs.getString("password");

                String aesKey = fetchKey(database.getCon(), platform, username);

                String decryptedPassword = "";

                if(aesKey != null)
                {
                    encryptor.setSecretKey(encryptor.createKeyFromExisting(aesKey));
                    decryptedPassword = encryptor.decrypt(encryptedPassword);
                }

                data.add(new Credentials(platform, username, decryptedPassword));
            }

            rs.close();
            database.getSt().close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return data;
    }

    public String fetchKey(Connection con, String platform, String username)
    {
        int credentialsId = -1;
        String aesKey = null;

        try
        {
            database.setSt(con.prepareCall("{call fetch_cred_id(?, ?, ?)}"));
            database.getSt().setString(1, platform);
            database.getSt().setString(2, username);
            database.getSt().registerOutParameter(3, java.sql.Types.INTEGER);

            database.getSt().execute();

            credentialsId = database.getSt().getInt(3);
            database.getSt().close();

            if(credentialsId == 0)
            {
                return "No matching Credentials and Key";
            }

            database.setSt(con.prepareCall("call fetch_aes_key(?, ?)"));
            database.getSt().setInt(1, credentialsId);
            database.getSt().registerOutParameter(2, java.sql.Types.VARBINARY);

            database.getSt().execute();

            aesKey = database.getSt().getString(2);
            database.getSt().close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return aesKey;
    }
}
