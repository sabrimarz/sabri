/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SabriMarz
 */
public class Database {
    
    final private String url ="jdbc:mysql://localhost/adfitness" ;
    final String login="root" ;
    final String mdp="" ;
    Connection cnx ; 
    static Database instance ;
    
    private Database () {
        try {
             cnx =  DriverManager.getConnection(url, login, mdp) ;
             System.out.println("Connection etablie");   
        }
        catch (SQLException e) { 
            
            System.out.println("echec de la conncetion a la base de données");
        }
        
    }

    public Connection getCnx() {
        return cnx;
    }

    public static Database getInstance() {
            if (instance==null)
        instance=new Database() ;
    return instance ;
    }

    public Connection getConnection() {
return cnx;    }
    
    
}
