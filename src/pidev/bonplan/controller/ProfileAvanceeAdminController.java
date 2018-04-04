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
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev.bonplan.services.AdminService;

/**
 * FXML Controller class
 *
 * @author Abdelkader
 */
public class ProfileAvanceeAdminController implements Initializable {

    @FXML
    private AnchorPane Homepane;
    @FXML
    private Label labelhome;
    @FXML
    private Separator sep;
    @FXML
    private VBox vboxx;
    @FXML
    private HBox hbx;
    @FXML
    private Label reclamamtion;
    @FXML
    private JFXButton Partenaire;
    @FXML
    private JFXButton Membre;
    @FXML
    private Label UtilisateursNombre;
    @FXML
    private Label Utlisateurs;
    @FXML
    private Label BonPlan;
    @FXML
    private Label Comment;
    @FXML
    private HBox hbxxx;
    @FXML
    private Pane BTGestionUtilisateur;
    @FXML
    private Label UtlisateursNombre;
    @FXML
    private Pane btGestionCadeaux;
    @FXML
    private Label UtlisateursCadeaux;
    @FXML
    private AnchorPane AnchoInfoUser;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelPrenom;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelDateNaissance;
    @FXML
    private Label LabelPhone;
    @FXML
    private Pane PaneInfoUser;
    @FXML
    private Label FirstLastName;
    @FXML
    private JFXButton ModifierProfil;
  AdminService adminService=new AdminService();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Utlisateurs.setText(String.valueOf(adminService.get_Number_User()));
        Comment.setText(String.valueOf(adminService.get_Number_Comment()));
        BonPlan.setText(String.valueOf(adminService.get_Number_User()));
        reclamamtion.setText(String.valueOf(adminService.get_Number_Reclamation()));
    }  
    
    
    
    
    
      private void setNode(Node node) {
        Homepane.getChildren().clear();
        Homepane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void modifierProfilClick(MouseEvent event) {
    }

   
    
    
    @FXML
    private void GestionUtilisateur(MouseEvent event) throws IOException {
        
        
        
setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/ListeUtlisateurFXML.fxml")));
        
        
        
    }

    @FXML
    private void GestionCadeaux(MouseEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/ListeCadeauxAdmin.fxml")));
    }

    @FXML
    private void AfficherNbrPartenaire(ActionEvent event) {
        
        Utlisateurs.setText(String.valueOf(adminService.get_Number_partenaire()));
    }

    @FXML
    private void AfficherNbrMembre(ActionEvent event) {
        
        
        Utlisateurs.setText(String.valueOf(adminService.get_Number_Membre()));
    }
 

    
}
