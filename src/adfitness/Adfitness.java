/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness;

import adfitness.entities.User;
import adfitness.entities.fiche;
import adfitness.services.UserServices;
import adfitness.util.BCrypt;
//import java.net.PasswordAuthentication;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author SabriMarz
 */
public class Adfitness {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AddressException, MessagingException {
fiche f = new fiche() ;

   //User u =new User( "sabri", "yemchi@yahoo.com", "123456","coach","male") ;
//   UserServices us =new UserServices() ;
//    int leftLimit = 97; // letter 'a'
//    int rightLimit = 122; // letter 'z'
//    int targetStringLength = 10;
//    Random random = new Random();
//    StringBuilder buffer = new StringBuilder(targetStringLength);
//    for (int i = 0; i < targetStringLength; i++) {
//        int randomLimitedInt = leftLimit + (int) 
//          (random.nextFloat() * (rightLimit - leftLimit + 1));
//        buffer.append((char) randomLimitedInt);
//    }
//    String generatedString = buffer.toString();
// 
//    System.out.println(generatedString);
        //System.out.println(us.getUserIdByEmail("yemchi@yahoo.com"));
      //us.ajouterFiche(f, 312);
     //  us.ajouterUser(u,"12gf47f5e21") ;
        //System.out.println(us.login("marz", "12345") );
     //  us.effacerUser("marz") ;
 //    us.modifierPhone("sabri.benmarzouk@google.com", "44444444444444");
  
    //for (User u : us.getPersonnel() )
            //System.out.println(u);
          
            
        System.out.println( BCrypt.hashpw("0000", BCrypt.gensalt(12)));
            
    
} }
