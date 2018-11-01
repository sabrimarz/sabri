/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.ui;

import adfitness.entities.User;
import adfitness.services.UserServices;
import static adfitness.ui.ModifierusController.test;
import static adfitness.ui.ModifierusController.user;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SabriMarz
 */
public class EspaceadminController implements Initializable {

    @FXML
    private AnchorPane modifier_user;
    @FXML
    private JFXTextField tffullname;
    @FXML
    private JFXTextField tfphone;
    @FXML
    private JFXTextField tfemail;
    @FXML
    private JFXButton sav;
    @FXML
    private JFXButton updat;
    @FXML
    private Label controle;
    @FXML
    private JFXButton delet;
    @FXML
    private JFXButton clear;
    @FXML
    private JFXComboBox<String> cbrole;
    @FXML
    private JFXComboBox<String> cbsexe;
    @FXML
    private TableView<User> tab;
    @FXML
    private TableColumn<User, String> fullname;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableColumn<User, String> sexe;
    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton refresh;
public static User user ;
    @FXML
    private Button deco;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          tfemail.setEditable(true);
        cbrole.getItems().addAll("gerant") ;
        cbsexe.getItems().addAll("homme","femme","autre") ;
         cbsexe.getSelectionModel().selectFirst(); 
                  cbrole.getSelectionModel().selectFirst(); 

       UserServices us = new UserServices() ;
      ArrayList<User> users = null;
        try {
            users = (ArrayList <User>) us.getByRole("gerant") ;
            
        } catch (SQLException ex) {
            Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList obs= FXCollections.observableArrayList(users);
            tab.setItems(obs);
            fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
       sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
       role.setCellValueFactory(new PropertyValueFactory<>("role"));
       
       
       
        sav.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                if (!chaine_non_valide(tffullname.getText())) {
                   controle.setText("le champs fullname est invalide");
                }
                else if (tffullname.getText().length()<5) {
                    controle.setText("le champs fullname doit etre de longueur 5") ;
                }
                else if (!controle_mail(tfemail.getText())) {
                    controle.setText("email invalde");
                }
                else if (!phone_valide(tfphone.getText()))
                controle.setText("numéro de téléphone est invalide");
                else {
                   
                 user=new User() ;
                 user.setEmail(tfemail.getText());
                 user.setFullname(tffullname.getText());
                 user.setPhone(tfphone.getText());
                 //user.setRole(tfrole.getText()); 
                 user.setRole(cbrole.getSelectionModel().getSelectedItem());
                 user.setSexe(cbsexe.getSelectionModel().getSelectedItem());
                 
                 
        try {
     FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("askpasswordadmin.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("mot de passe");
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceadminController.class.getName()).log(Level.SEVERE, null, ex);
        } 
             }}
    });
       
       
    }    

    @FXML
    private void sav_signal(ActionEvent event) {
    }

    @FXML
    private void updat_signal(ActionEvent event) {
        
        
     if (!chaine_non_valide(tffullname.getText())) {
                   controle.setText("le champs fullname est invalide");
                }
                else if (tffullname.getText().length()<5) {
                    controle.setText("le champs fullname doit etre de longueur 5") ;
                }
                else if (!controle_mail(tfemail.getText())) {
                    controle.setText("email invalde");
                }
                else if (!phone_valide(tfphone.getText()))
                controle.setText("numéro de téléphone est invalide");
                else {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                         alert.setTitle("Notification");
                         alert.setContentText("Etes vous sure de vouloir modifier "+tffullname.getText()+"?");
                         Optional<ButtonType> result = alert.showAndWait();
ButtonType button = result.orElse(ButtonType.CANCEL);

if (button == ButtonType.OK) {
          UserServices us = new UserServices() ;
     try{
   us.modifierFullName(tfemail.getText(), tffullname.getText());
   us.modifierPhone(tfemail.getText(), tfphone.getText()); 
   us.modifierSexe(tfemail.getText(),cbsexe.getSelectionModel().getSelectedItem());
   ArrayList<User> users = null;
        try {
            users = (ArrayList <User>) us.getByRole("gerant") ;
        } catch (SQLException ex) {
            Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList obs= FXCollections.observableArrayList(users);
            tab.setItems(obs);
            fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
       sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
       role.setCellValueFactory(new PropertyValueFactory<>("role"));
   
   
   
     }
        catch (SQLException e) { 
            System.out.println("erreur base de donnée");
        } }
                } 
        
    }

    @FXML
    private void delet_signal(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                         alert.setTitle("Notification");
                         alert.setContentText("Etes vous sure de vouloir supprimer "+tffullname.getText()+"?");
                         Optional<ButtonType> result = alert.showAndWait();
ButtonType button = result.orElse(ButtonType.CANCEL);

if (button == ButtonType.OK) {
                    
 
        
        
        UserServices us = new UserServices();
        try {
            

            
            us.effacerUser(tfemail.getText());
                         
     tffullname.setText("");
         tfemail.setText("");
         tfphone.setText("");
      //   tfsexe.setText("");
        // tfrole.setText("");
     
     
     tfemail.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(EspaceadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
       
        ArrayList<User> users = null;
        try {
            users = (ArrayList <User>) us.getByRole("gerant") ;
        } catch (SQLException ex) {
            Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList obs= FXCollections.observableArrayList(users);
            tab.setItems(obs);
            fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
       sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
       role.setCellValueFactory(new PropertyValueFactory<>("role"));
//         alert.setTitle("Notification");
//                         alert.setContentText("utilisateur supprimer");
//                         alert.showAndWait(); 
}
        
    }

    @FXML
    private void clear_signal(ActionEvent event) {
        
         tfemail.setEditable(true);
     tffullname.setText("");
         tfemail.setText("");
         tfphone.setText("");
     
      
        
    }

    @FXML
    private void edit_signal(ActionEvent event) {
        
         tfemail.setEditable(false);
               User u=tab.getSelectionModel().getSelectedItem();
         tffullname.setText(u.getFullname());
         tfemail.setText(u.getEmail());
         tfphone.setText(u.getPhone());
        // tfsexe.setText(u.getSexe());
       
        if (u.getSexe().equalsIgnoreCase("homme"))
            cbsexe.getSelectionModel().select(0);
        else  if (u.getSexe().equalsIgnoreCase("femme"))
            cbsexe.getSelectionModel().select(1);
        else 
       cbsexe.getSelectionModel().select(2);
    //     tfrole.setText(u.getRole());
    cbrole.getItems().clear() ;
    cbrole.getItems().addAll("gerant") ;
    cbrole.getSelectionModel().selectFirst();
        
    }

    @FXML
    private void refresh_signal(ActionEvent event) {
          tfemail.setEditable(true);
                cbsexe.getItems().addAll("homme","femme","autre") ;
         cbsexe.getSelectionModel().selectFirst(); 
          cbrole.getSelectionModel().selectFirst(); 

       UserServices us = new UserServices() ;
      ArrayList<User> users = null;
        try {
            users = (ArrayList <User>) us.getByRole("gerant") ;
        } catch (SQLException ex) {
            Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList obs= FXCollections.observableArrayList(users);
            tab.setItems(obs);
            fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
       sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
       role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }
    

public boolean chaine_non_valide(String s) { 
    s=s.toLowerCase() ;
    if (s.contains("  "))
        return false ;
    
for (int i=0;i<s.length();i++) {
   if ((( s.charAt(i)<'a') || (s.charAt(i)>'z')) && (s.charAt(i)!=' '))
    return false ; 
}

return true;
}

public boolean controle_mail (String mail ) {
    if(mail.indexOf(" ")!=-1)
        return false ;
return ( Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail));
}

public boolean chaine_en_espace(String s) { 
    for (int i=0;i<s.length();i++)
        if(s.charAt(i)!=' ')
            return false ;
    return true ;
}

public boolean phone_valide(String phone ) { 
    if ((phone.indexOf("+")!=0) && (phone.indexOf("+")!=-1))  
        return false ;
    else if (phone.length()<8)
        return false ;
    else if (phone.contains(" "))
        return false ;
    if (phone.indexOf("+")!=-1) {
       for (int i=1;i<phone.length();i++) {
   if (( phone.charAt(i)<'0') || (phone.charAt(i)>'9'))
    return false ; 
}}
    else { for (int i=0;i<phone.length();i++) {
   if (( phone.charAt(i)<'0') || (phone.charAt(i)>'9'))
    return false ; 
}}
    return true ;
}

    @FXML
    private void deco_signal(ActionEvent event) throws IOException {
        
          FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("LoginMenu.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
    }

}