/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.ui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SabriMarz
 */
public class EspaceController implements Initializable {

    @FXML
    private Hyperlink store;
    @FXML
    private Hyperlink amine;
    @FXML
    private Hyperlink takwa;
    @FXML
    private Hyperlink firas;
    @FXML
    private JFXButton deco;
    @FXML
    private Label welcome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void store_signal(ActionEvent event) {
    }


    @FXML
    private void amine_signal(ActionEvent event) {
    }

 

    @FXML
    private void takwa_signal(ActionEvent event) {
    }

    @FXML
    private void firas_signal(ActionEvent event) {
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
