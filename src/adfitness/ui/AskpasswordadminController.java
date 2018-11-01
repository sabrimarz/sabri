/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.ui;

import adfitness.services.SendingMail;
import adfitness.services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SabriMarz
 */
public class AskpasswordadminController implements Initializable {

    @FXML
    private AnchorPane askpw;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXTextField pass;
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
                     us.ajouterPersonnel(EspaceadminController.user, pass.getText());
                     String contenu = "Welcome to  AD Fitness ,\n your account has been created , you can now Sign in with \n username="+EspaceadminController.user.getEmail() + "\n password = " +pass.getText() +"\n l'équipe de AD Fitness vous remercie !" ;
                     SendingMail sm = new SendingMail( contenu,EspaceadminController.user.getEmail(),"welcome") ;
                     SendingMail.envoyer();
                     
            ((Node)(event.getSource())).getScene().getWindow().hide();  
            
            
            
            
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AskpasswordadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }
    
}
