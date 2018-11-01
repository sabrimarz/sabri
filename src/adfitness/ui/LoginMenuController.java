/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.ui;

import adfitness.entities.CurrentUser;
import adfitness.services.SendingMail;
import adfitness.services.UserServices;
import adfitness.util.BCrypt;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SabriMarz
 */
public class LoginMenuController implements Initializable {

    @FXML
    private ImageView back;
    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private Button btn_login;
    @FXML
    private Label verif;
    @FXML
    private JFXButton forgotten;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void btn_login_signal(ActionEvent event) throws SQLException, IOException {
           UserServices us = new UserServices () ;
                
        if (user.getText().equals("")) {
            verif.setText("veuillez saisir votre email");
        }
        else if (pass.getText().equals("")){ 
            verif.setText("veuillez saisir votre mot de passe");
        }
        else if ( !us.login(user.getText(),pass.getText())) {
           verif.setText("cordonnées invalides");
        }
        else { 
            CurrentUser cu=new CurrentUser(us.getUserByEmail(user.getText())) ;
       
            if (CurrentUser.role.equals("gerant")) {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("usreinterface.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("espace gerant");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
       
            }
            
             else  if (CurrentUser.role.equals("administrateur")) {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("espaceadmin.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("espace administrateur");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
       
            }
            
            else  {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("espace.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("espace client");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
       
            }
            
            
       }
        
    }

    @FXML
    private void forgotten_signal(ActionEvent event) throws SQLException {
        
            int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 9;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
        int randomLimitedInt = leftLimit + (int) 
          (random.nextFloat() * (rightLimit - leftLimit + 1));
        buffer.append((char) randomLimitedInt);
    }
    String generatedString = buffer.toString();
        UserServices us = new UserServices () ;
                
        if (user.getText().equals("")) {
            verif.setText("veuillez saisir votre email");
        }
        else if (!us.existeUser(user.getText())) {
            verif.setText("email non valide");
        }
        else {
        SendingMail sm =new SendingMail("your password has been reset  , you can now login with with : \n password="+ generatedString,user.getText(), "mot de passe reinitialiser");
        sm.envoyer();
        us.modifierPassword(user.getText(), generatedString);
        verif.setText("veuillez consulter votre Email");
        
        
        
        
        
        }   
    }
    
}
