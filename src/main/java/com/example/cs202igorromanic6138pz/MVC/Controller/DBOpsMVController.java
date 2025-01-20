package com.example.cs202igorromanic6138pz.MVC.Controller;

import com.example.cs202igorromanic6138pz.Database.DB;
import com.example.cs202igorromanic6138pz.Database.DBOperations;
import com.example.cs202igorromanic6138pz.Security.AESEncryptor;
import com.example.cs202igorromanic6138pz.Security.Hash;

public class DBOpsMVController
{
    private AESEncryptor AESEncryptor;
    private DB database;
    private DBOperations dbOperations;
    private Hash hash;

    public AESEncryptor getEncryptor() {
        return AESEncryptor;
    }

    public DB getDatabase() {
        return database;
    }

    public DBOperations getDbOperations() {
        return dbOperations;
    }

    public Hash getHash() {
        return hash;
    }

    public DBOpsMVController()
    {
        AESEncryptor = new AESEncryptor();
        database = new DB();
        dbOperations = new DBOperations(database);
        hash = new Hash();
    }
}
