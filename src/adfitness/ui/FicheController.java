/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.ui;

import adfitness.entities.fiche;
import adfitness.services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author SabriMarz
 */
public class FicheController implements Initializable {

    @FXML
    private JFXCheckBox diabete;
    @FXML
    private JFXCheckBox hypo;
    @FXML
    private DatePicker tfdate;
    @FXML
    private JFXTextField tfnom;
    @FXML
    private JFXCheckBox hyper;
    @FXML
    private JFXCheckBox perte;
    @FXML
    private JFXCheckBox saignement;
    @FXML
    private JFXCheckBox fumeur;
    @FXML
    private JFXCheckBox pratiquant;
    @FXML
    private JFXCheckBox poids;
    @FXML
    private JFXCheckBox strength;
    @FXML
    private JFXButton validerfiche;
    @FXML
    private Label controle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

 tfnom.setText( ModifierusController.user.getFullname()) ;
 if (ModifierusController.test==true) { 
     validerfiche.setVisible(true);
     validerfiche.setDisable(false);
     
     
 }
 else {
        validerfiche.setVisible(false);
     validerfiche.setDisable(true);
     UserServices us = new UserServices(); 
     try {
         int a = us.getUserIdByEmail(ModifierusController.user.getEmail()) ;
         fiche f=us.getFiche(a) ;
         if(f.getDiabete()==1) 
             diabete.setSelected(true);
         
         if(f.getFumeur()==1) 
             fumeur.setSelected(true);
         if(f.getHyper()==1) 
             hyper.setSelected(true);
         if(f.getHypo()==1) 
            hypo.setSelected(true);
         if(f.getPerte()==1) 
             perte.setSelected(true);
         if(f.getPratiquant()==1) 
             pratiquant.setSelected(true);
         if(f.getStrength()==1) 
             strength.setSelected(true);
         if(f.getSaignement()==1) 
             saignement.setSelected(true);
         if(f.getPredre()==1) 
             poids.setSelected(true);

         tfdate.setValue(f.getNaissance());
         
         
     } catch (SQLException ex) {
         Logger.getLogger(FicheController.class.getName()).log(Level.SEVERE, null, ex);
     }
     
     
 }
 
    }    

    @FXML
    private void validerfiche_signal(ActionEvent event) {
if (tfdate.getValue()==null) { 
    controle.setText("veuillez saisir la date de naissance");
}
else {  
fiche f =new fiche() ;
    if (diabete.isSelected()) 
        f.setDiabete(1);
    if (hypo.isSelected())
    f.setHypo(1); 
    if (hyper.isSelected())
        f.setHyper(1);
    if(perte.isSelected())
        f.setPerte(1);
    if (saignement.isSelected())
        f.setSaignement(1); 
    if (fumeur.isSelected())
f.setFumeur(1); 
    if (pratiquant.isSelected())
        f.setPratiquant(1); 
    if (poids.isSelected())
        f.setPredre(1);
    if(strength.isSelected())
        f.setStrength(1); 
    
   LocalDate d =  tfdate.getValue() ;
     f.setNaissance(d);

      
   try {UserServices us=new UserServices() ;
      String mail=ModifierusController.user.getEmail() ;
       int id = us.getUserIdByEmail(mail) ;
       us.ajouterFiche(f, id);
       ((Node)(event.getSource())).getScene().getWindow().hide();  
        } catch (SQLException ex) {
      }
        
}    
    
    
    }
    
}
