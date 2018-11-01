/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.services;

import adfitness.entities.User;
import adfitness.util.BCrypt;
import adfitness.util.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import adfitness.entities.fiche;
import java.time.LocalDate;
/**
 *
 * @author SabriMarz
 */
public class UserServices {

    private Connection cnx;

    public UserServices() {
        cnx = Database.getInstance().getConnection();
    }

    public boolean login(String email, String password) throws SQLException {
        if (!email.isEmpty() && !password.isEmpty() ) {
            String req = "SELECT password from users where email = '" + email +"'";
            Statement s = cnx.createStatement();
            ResultSet rs = s.executeQuery(req);
            if (rs.next()){
            String pw = rs.getString(1);
                     return (BCrypt.checkpw(password, pw));}
            else return false ;
        }  
        else {
            return false;
        }

    }

    public void ajouterAbonne(User u, String password) throws SQLException {

        
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
            String req = "INSERT INTO users(fullname, email, phone, password,sexe,role) VALUES ('" + u.getFullname() + "' ,'" + u.getEmail() + "' ,'"   + u.getPhone() + "','"+ hashedpw +  "' ,'" +u.getSexe()+"','"+"abonne"+  "')";
            Statement s = cnx.createStatement();
             s.executeUpdate(req);
      
      }
    
      public void ajouterPersonnel(User u, String password) throws SQLException {

        
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
            String req = "INSERT INTO users(fullname, email, phone, password,sexe,role,specialite) VALUES ('" + u.getFullname() + "' ,'" + u.getEmail() + "' ,'"   + u.getPhone() + "','"+ hashedpw +  "' ,'" +u.getSexe()+"','"+u.getRole()+"','" +u.getSpecialite()+ "')";
            Statement s = cnx.createStatement();
             s.executeUpdate(req);
      
      }  
      
//      public void ajouterUser(User u, String password) throws SQLException {
//
//        
//            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
//            String req = "INSERT INTO users(fullname, email, phone, password,sexe,role,specialite) VALUES ('" + u.getFullname() + "' ,'" + u.getEmail() + "' ,'"   + u.getPhone() + "','"+ hashedpw +  "' ,'" +u.getSexe()+"','"+u.getRole()+"','" +u.getSpecialite()+ "')";
//            Statement s = cnx.createStatement();
//             s.executeUpdate(req);
//      
//      }  
    
    
    public List<User> getAbonne( ) throws SQLException { 
        List<User> liste=new ArrayList <> () ;
        String req="select * from users where role='abonne' "   ;
        Statement s = cnx.createStatement() ;
        ResultSet rs =s.executeQuery(req) ;
             while (rs.next()) {
                 User u=new User(rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("dateadhesion"), rs.getString("role"), rs.getString("sexe")) ;
                 System.out.println(u.getDateadhesion());
            liste.add(u) ;
             }   
        return liste ;
        
    }
    
    
    
    
    
    
    
    
    
    
        public User getUserByEmail( String mail) throws SQLException { 
        String req="select * from users where email='"+mail+"'"    ;
        Statement s = cnx.createStatement() ;
        ResultSet rs =s.executeQuery(req) ;
        User u = new User() ;
            if (rs.next()) {
                u=new User(rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("dateadhesion"), rs.getString("role"), rs.getString("sexe")) ;
            u.setId(rs.getInt("id")); 
            u.setSpecialite(rs.getString("specialite"));
             }   
        return u ;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
        public List<User> getPersonnel( ) throws SQLException { 
        List<User> liste=new ArrayList <> () ;
        String req="select * from users where role != 'abonne'  and role!= 'administrateur' and role != 'gerant' "   ;
        Statement s = cnx.createStatement() ;
        ResultSet rs =s.executeQuery(req) ;
             while (rs.next()) {
                 User u=new User(rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("dateadhesion"), rs.getString("role"),rs.getString("specialite"), rs.getString("sexe")) ;
            liste.add(u) ;
             }   
        return liste ;
    }
        
        
           public List<User> getByRole( String role) throws SQLException { 
        List<User> liste=new ArrayList <> () ;
        String req="select * from users where role ='"+role+ "'"  ;
        Statement s = cnx.createStatement() ;
        ResultSet rs =s.executeQuery(req) ;
             while (rs.next()) {
                 User u=new User(rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("dateadhesion"), rs.getString("role"),rs.getString("specialite"), rs.getString("sexe")) ;
            liste.add(u) ;
             }   
        return liste ;
    }    
        
        
        
        
    
    public void modifierFullName(String email , String fullname) throws SQLException {
        String req=" update users set fullname='" +fullname+"'"+"where email='"+email+"'" ;
        Statement s = cnx.createStatement(); 
        s.executeUpdate(req) ; 
        
    }
    
        public void modifierEmail(String email , String mail) throws SQLException {
        String req=" update users set email='" +mail+"'"+"where email='"+email+"'";
        Statement s = cnx.createStatement(); 
        s.executeUpdate(req) ; 
        
    }
            public void modifierPassword(String email , String pass) throws SQLException {
                String hpass=BCrypt.hashpw(pass, BCrypt.gensalt(12)) ;
        String req=" update users set password='" +hpass+"'"+"where email='"+email+"'";
        Statement s = cnx.createStatement(); 
        s.executeUpdate(req) ; 
        
    }
                public void modifierRole(String email , String role) throws SQLException {
        String req=" update users set role='" +role+"'"+"where email='"+email+"'";
        Statement s = cnx.createStatement(); 
        s.executeUpdate(req) ; 
        
    }
                
                public void modifierSpecialite(String email , String specialite) throws SQLException {
        String req=" update users set specialite='" +specialite+"'"+"where email='"+email+"'";
        Statement s = cnx.createStatement(); 
        s.executeUpdate(req) ; 
        
    }
                
                
        public void modifierSexe(String email , String sexe) throws SQLException {
        String req=" update users set sexe='" +sexe+"'" +"where email='"+email+"'" ;
        Statement s = cnx.createStatement(); 
        s.executeUpdate(req) ; 
        
    }
            public void modifierPhone(String email , String phone) throws SQLException {
        String req=" update users set phone='" +phone+"'"+"where email='"+email+"'";
        Statement s = cnx.createStatement(); 
        s.executeUpdate(req) ; 
        
    }
    
    
    
    
    
    public void effacerUser(String email) throws SQLException {
        String req="delete from users where email='"+email+"'" ;
        Statement s=cnx.createStatement() ;
        s.executeUpdate(req) ;
        
    }
    
    public boolean existeUser (String email) throws SQLException {
        
          String req = "SELECT * from users where email = '" + email +"'";
            Statement s = cnx.createStatement();
            ResultSet rs = s.executeQuery(req);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
 
    public void ajouterFiche(fiche f , int id ) throws SQLException { 
        
        String req = "INSERT INTO `fiche`( `idclient`, `naissance`, `diabete`, `hypo`, `hyper`, `perte`, `saignement`, `fumeur`, `pratiquant`, `perdre`, `strength`) VALUES ("+id+",'"+f.getNaissance() +"',"+f.getDiabete()+","+f.getHypo()+","+f.getHyper()+","+f.getPerte()+","+f.getSaignement()+","+f.getFumeur()+","+f.getPratiquant()+","+f.getPredre()+","+f.getStrength() +")"  ; 
        Statement s = cnx.createStatement() ;
        s.executeUpdate(req) ;
               
    
    }
    public fiche getFiche (int id ) throws SQLException {
      String req = "select * from fiche where idclient="+id ;  
       Statement s = cnx.createStatement() ;
       ResultSet rs = s.executeQuery(req) ; 
       if(rs.next()) {
       fiche f = new fiche (rs.getInt("id"), rs.getInt("idclient"), LocalDate.parse(rs.getString("naissance")), rs.getInt("diabete"), rs.getInt("hypo"), rs.getInt("hyper"), rs.getInt("perte"), rs.getInt("saignement"), rs.getInt("fumeur"), rs.getInt("pratiquant"), rs.getInt("perdre"), rs.getInt("strength")) ;
       return f ; }
       else return null ;
    }
    public int getUserIdByEmail(String mail) throws SQLException { 
      String req = "select id from users where email='"+mail+"'" ;  
       Statement s = cnx.createStatement() ;
       ResultSet rs = s.executeQuery(req) ;    
        if(rs.next())
            return (rs.getInt("id")) ;
        else return -1 ;
        
    }
    
 
}
