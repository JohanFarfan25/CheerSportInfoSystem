/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

/**
 *
 * @author johan.farfan
 */
import java.sql.Connection;  
import java.sql.DriverManager;  
 
public class ConnectionMySQL{  
 
    public static Connection con_mysql;  
 
    public static Connection conectar(String host, String user, String password, String db) throws Exception  {  
            String databaseURL = "jdbc:mysql://" + host + "/" + db;  
            Class.forName("com.mysql.jdbc.Driver");  
            con_mysql = DriverManager.getConnection(databaseURL, user, password);  
        return con_mysql;  
    }  
 
}