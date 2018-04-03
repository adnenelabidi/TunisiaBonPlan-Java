/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Abdelkader
 */
public class AdminMenuController implements Initializable {

    @FXML
    private VBox btnListeGestion;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnprofilAvance;
    @FXML
    private JFXButton btnReclamations;
    @FXML
    private JFXButton btnBonPlans;
    @FXML
    private JFXButton btnDeals;
    @FXML
    private JFXButton btnForum;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private AnchorPane btnGestionAffichage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    private void setNode(Node node) {
        btnGestionAffichage.getChildren().clear();
        btnGestionAffichage.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void Home(ActionEvent event) {
        
        
        
        
    }

    @FXML
    private void GestionProfileAvance(ActionEvent event) throws IOException {
        
        
         
    }

    @FXML
    private void GestionReclamations(ActionEvent event) {
    }

    @FXML
    private void GestionBonPlans(ActionEvent event) {
    }

    @FXML
    private void GestionDeals(ActionEvent event) {
    }

    @FXML
    private void GestionForum(ActionEvent event) {
    }

    @FXML
    private void MyProfile(ActionEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
    }

    @FXML
    private void ListeGestion(MouseEvent event) {
    }

    @FXML
    private void GestionAffichage(MouseEvent event) {
    }
    
}
