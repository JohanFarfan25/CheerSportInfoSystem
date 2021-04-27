/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.utils;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author johan.farfan
 */
public class ConexionMySQL {
    String driver = "com.mysql.jdbc.Driver";
    String database = "gargolasproject";
    String hostname = "localhost";
    String url = "jdbc:mysql://"+hostname+"/"+database+"?useSSL=false";
    String username = "Johan";
    String password = "12345";
    
    public Connection conectarMySQL() {
        Connection con = null;
        
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return con;
    }
    
}

