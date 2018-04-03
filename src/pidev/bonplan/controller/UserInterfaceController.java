/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class UserInterfaceController implements Initializable {
    
    @FXML
    private AnchorPane parent;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    public void closeApp(){
        
          // get a handle to the stage
    Stage stage = (Stage) btnclose.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
}
