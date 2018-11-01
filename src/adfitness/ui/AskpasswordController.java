/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.ui;

import adfitness.services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import adfitness.util.BCrypt ;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.* ;
import javax.mail.internet.* ;
import adfitness.services.SendingMail ;
/**
 * FXML Controller class
 *
 * @author SabriMarz
 */
public class AskpasswordController implements Initializable {

    @FXML
    private JFXButton valider;
    @FXML
    private JFXTextField pass;
    @FXML
    private AnchorPane askpw;
    @FXML
    private JFXTextField conf;
    @FXML
    private Label controlepw;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    

    @FXML
    private void valider_signal(ActionEvent event) {
     UserServices us = new UserServices () ;
        try {
            if(pass.getText().length()<5 )
                controlepw.setText("mot de passe trés courte");
            else if (   true != (pass.getText().equals(conf.getText()))    ) 
                  controlepw.setText("les mots de passe ne sont pas identiques");
            else{
                     us.ajouterPersonnel(ModifierusController.user, pass.getText());
                     String contenu = "Welcome to  AD Fitness ,\n your account has been created , you can now Sign in with \n username="+ModifierusController.user.getEmail() + "\n password = " +pass.getText() +"\n l'équipe de AD Fitness vous remercie !" ;
                     SendingMail sm = new SendingMail( contenu,ModifierusController.user.getEmail(),"welcome") ;
                     SendingMail.envoyer();
                   
            ((Node)(event.getSource())).getScene().getWindow().hide();  
            
            try {
     FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fiche.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("fiche medicale");
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
                System.out.println(ex.getCause());
        } 
            
            
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AskpasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
       
    }

}
