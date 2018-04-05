/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;
 
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import impl.org.controlsfx.skin.NotificationBar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
 
/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class UserInterfaceController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;
    @FXML
    private Label btndemanderec;
    @FXML
    private AnchorPane UserPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
   public void closeApp(){
        
          // get a handle to the stage
    Stage stage = (Stage) btnclose.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    @FXML
    private void DemandeRecomanadation(MouseEvent event) throws IOException {
      
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/bonplan/GUI/AjoutDemande.fxml"));
    Parent root=loader.load();
        btndemanderec.getScene().setRoot(root);
    //    Notifications.create().darkStyle().title("xxx").text("fff").showConfirm();
      
  
        
        
    }
 @FXML
    private void AjouterParataeExperience(MouseEvent event) throws IOException {
      
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/bonplan/GUI/PartageExperience.fxml"));
    Parent root=loader.load();
        UserPane.getScene().setRoot(root);
      
  
        
        
    }
}
