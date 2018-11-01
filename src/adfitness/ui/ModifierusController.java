package adfitness.ui;


import adfitness.entities.CurrentUser;
import javax.mail.* ;

import javax.mail.internet.* ;
import adfitness.entities.User;
import adfitness.services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import adfitness.ui.FicheController ;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import com.itextpdf.text.Document;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * FXML Controller class
 *
 * @author SabriMarz
 */
public class ModifierusController implements Initializable {


    @FXML
   private TableView<User> tab;
    @FXML
    private  TableColumn<User, String> fullname;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> sexe;
    @FXML
    private TableColumn<User, String> role;
    public JFXComboBox<String> rolecombobox;
    @FXML
    private JFXTextField tffullname;
    @FXML
    private JFXTextField tfemail;
    @FXML
    private JFXTextField tfphone;
    private JFXTextField tfsexe;
    private JFXTextField tfrole;
    @FXML
    private TableColumn<User, String> specialite;
    @FXML
    private JFXTextField tfspecialite;
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
    private JFXButton edit;
    public static User user ;
    public static boolean test ;
    @FXML
    private JFXButton refresh;
    @FXML
    private AnchorPane modifier_user;
    @FXML
    private JFXComboBox<String> cbrole;
    @FXML
    private JFXComboBox<String> cbsexe;
    @FXML
    private JFXButton fiche_medicale;
    @FXML
    private JFXButton retour;
    @FXML
    private TableColumn<?,?> datead;
    @FXML
    private JFXButton presence;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updat.setVisible(false);
     delet.setVisible(false);
        tfspecialite.setText("");
      
        tfemail.setEditable(true);
        cbrole.getItems().addAll("abonne","coach" , "maintenancier","medicale") ;
        cbsexe.getItems().addAll("homme","femme","autre") ;
         cbsexe.getSelectionModel().selectFirst(); 
         cbrole.getSelectionModel().selectFirst();;
       UserServices us = new UserServices() ;
      ArrayList<User> users = null;
        try {
            users = (ArrayList <User>) us.getPersonnel();
            users.addAll((ArrayList <User>) us.getAbonne());
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
         specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
         datead.setCellValueFactory(new PropertyValueFactory<>("dateadhesion"));
       
        System.out.println(tab.getSelectionModel().getSelectedItem());
       sav.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 UserServices us = new UserServices() ;
                 boolean existe=false ;
                 try {
                      existe =us.existeUser(tfemail.getText()) ;
                 } catch (SQLException ex) {
                     Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     if (!chaine_non_valide(tffullname.getText())) {
                   controle.setText("le champs fullname est invalide");
                }
                else if (tffullname.getText().length()<5) {
                    controle.setText("le champs fullname doit etre de longueur 5") ;
                }
                else if (!controle_mail(tfemail.getText())) {
                    controle.setText("email invalde");
                }
                else if (existe==true) {
                    controle.setText("Email déja existant");
                }
                else if (!phone_valide(tfphone.getText()))
                controle.setText("numéro de téléphone est invalide");
                else if (cbrole.getSelectionModel().getSelectedIndex()==-1)
                    controle.setText("veillez selectionner le role de cet utilisateur");
                else if ((cbrole.getSelectionModel().getSelectedIndex()!=0) && (tfspecialite.getText().equals("")))
                    controle.setText("les personnel doivent avoir une specialité spécifique");
                else if ((cbrole.getSelectionModel().getSelectedIndex()==0) && (tfspecialite.getText().equals("")==false) )
                    controle.setText("les abonnés n'ont pas de specialité");
                        
                else {
                 test=true ;
                 user=new User() ;
                 user.setEmail(tfemail.getText());
                 user.setFullname(tffullname.getText());
                 user.setPhone(tfphone.getText());
                 //user.setRole(tfrole.getText()); 
                 user.setRole(cbrole.getSelectionModel().getSelectedItem());
                 user.setSexe(cbsexe.getSelectionModel().getSelectedItem());
                 user.setSpecialite(tfspecialite.getText());
                 
        try {
     FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("askpassword.fxml"));
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
            Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
        } 
             } }
    });
       
       
//           fiche_medicale.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 user=new User() ;
//                 user.setEmail(tfemail.getText());
//                 user.setFullname(tffullname.getText());
//                 user.setPhone(tfphone.getText());
//                 //user.setRole(tfrole.getText()); 
//                 user.setRole(cbrole.getSelectionModel().getSelectedItem());
//                 user.setSexe(cbsexe.getSelectionModel().getSelectedItem());
//                 user.setSpecialite(tfspecialite.getText());
//                 
//        try {
//     FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("fiche.fxml"));
//        /* 
//         * if "fx:controller" is not set in fxml
//         * fxmlLoader.setController(NewWindowController);
//         */
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        stage.setTitle("New Window");
//        stage.setScene(scene);
//        stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//             }
//    });
       
       
    }

    @FXML
    private void sav_signal(ActionEvent event) throws IOException {
        
        
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
                else if (cbrole.getSelectionModel().getSelectedIndex()==-1)
                    controle.setText("veillez selectionner le role de cet utilisateur");
                else if ((cbrole.getSelectionModel().getSelectedIndex()!=0) && (tfspecialite.getText().equals("")))
                    controle.setText("les personnel doivent avoir une specialité spécifique");
                else if ((cbrole.getSelectionModel().getSelectedIndex()==0) && (tfspecialite.getText().equals("")==false) )
                    controle.setText("les abonnés n'ont pas de specialité");
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
  // us.modifierRole(tfemail.getText(), tfrole.getText());
   us.modifierSexe(tfemail.getText(),cbsexe.getSelectionModel().getSelectedItem());
   us.modifierSpecialite(tfemail.getText(), tfspecialite.getText());
   
    ArrayList<User> users = null;
        try {
            users = (ArrayList <User>) us.getPersonnel();
            users.addAll((ArrayList <User>) us.getAbonne());
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
         specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
          updat.setVisible(false);
        delet.setVisible(false);
   
     }
        catch (SQLException e) { 
            System.out.println("erreur base de donnée");
        } }
                }
    }

    @FXML
    private void delet_signal(ActionEvent event) {
        if(tfemail.getText().equals("")) 
            controle.setText("saisissez au moins l'email de l'utilisateur effacer , utiliser le bouton edit");
        
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
       tfspecialite.setText("");
        cbrole.getItems().clear();
    cbrole.getItems().addAll("abonne","coach" , "maintenancier","medicale" ) ;
     tfemail.setEditable(true);
     updat.setVisible(false);
     delet.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
       
        ArrayList<User> users = null;
        try {
            users = (ArrayList <User>) us.getPersonnel();
            users.addAll((ArrayList <User>) us.getAbonne());
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
       specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        
}
         
    }

    @FXML
    private void edit_signal(ActionEvent event) {
    updat.setVisible(true);
     delet.setVisible(true);
         tfemail.setEditable(false);
               User u=tab.getSelectionModel().getSelectedItem();
               System.out.println(u);
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
    cbrole.getItems().addAll(u.getRole()) ;
    cbrole.getSelectionModel().selectFirst();
       tfspecialite.setText(u.getSpecialite());
       if(tfspecialite.getText()==null)
           tfspecialite.setText("");
       
    }

    @FXML
    private void clear_signal(ActionEvent event) {
                 tfemail.setEditable(true);
     tffullname.setText("");
         tfemail.setText("");
         tfphone.setText("");
     
       tfspecialite.setText("");
    cbrole.getItems().clear();
    cbrole.getItems().addAll("abonne","coach" , "maintenancier","medicale") ;
    }

    @FXML
    public  void refresh_signal(ActionEvent event) {
        
         UserServices us = new UserServices() ;
      ArrayList<User> users = null;
       ArrayList<User> users1 =null ;
        try {
            users = (ArrayList <User>) us.getPersonnel();
          users.addAll((ArrayList <User>) us.getAbonne());
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
       specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
       
    }

    @FXML
    private void fiche_medicale_signal(ActionEvent event) throws IOException {
        test =false ;
user=tab.getSelectionModel().getSelectedItem() ;
//                 user.setEmail(tfemail.getText());
//                 user.setFullname(tffullname.getText());
//                 user.setPhone(tfphone.getText());
//                 user.setRole(cbrole.getSelectionModel().getSelectedItem());
//                 user.setSexe(cbsexe.getSelectionModel().getSelectedItem());
//                 user.setSpecialite(tfspecialite.getText());
                 
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
            Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
        } 
     
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
    private void retour_signal(ActionEvent event) {
        
        try {
     FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("usreinterface.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("interface utilisateur");
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
                System.out.println(ex.getCause());
        } 
        ((Node)(event.getSource())).getScene().getWindow().hide();  
        
    }

    @FXML
    private void presence_signal(ActionEvent event) throws DocumentException {
          String ad="C:\\Users\\SabriMarz\\Desktop\\presence.pdf";
                Document doc=new Document();
               try {
                   PdfWriter.getInstance((com.itextpdf.text.Document) doc, new FileOutputStream(ad));
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (DocumentException ex) {
                   Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.open();
               try {
                    doc.add(new Paragraph("ADFitness"));
                    doc.add(new Paragraph("Rapport des Cours"));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();  
                    String d=dtf.format(now);
                    doc.add(new Paragraph("Date: "+d));
                    doc.add(new Paragraph(" "));
                    doc.add(new Paragraph(" Gerant responsable : "+CurrentUser.fullname ));
                    doc.add(new Paragraph(" "));
                    PdfPTable table = new PdfPTable(2);
                    PdfPCell c1=new PdfPCell(new Phrase("nom du personnel"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("présent"));
                    table.addCell(c1);
                    
                    // table.setHeaderRows(0);
                    UserServices s=new UserServices();
                    ArrayList<User> e =(ArrayList<User>)s.getPersonnel();
                    for(int i=0;i<e.size();i++)
                    {
                        String n=e.get(i).getFullname();
                        table.addCell(n);
                        String r="";
                        table.addCell(r);
                 
                    }
                    doc.add(table);
               }catch (DocumentException ex) {
                   Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(ModifierusController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.close();
                
        
    }

    
    
    
    
    }
    

