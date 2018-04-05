/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class DetailExperienceController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private VBox vbox;
    @FXML
    private Label backexp;
    @FXML
    private JFXButton btnclose;
    @FXML
    private FontAwesomeIcon notif;
    @FXML
    private AnchorPane listpane;
    @FXML
    private JFXButton addexp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BackListExp(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/bonplan/GUI/PartageExperience.fxml"));
        Parent root = loader.load();
        backexp.getScene().setRoot(root);
    }

    @FXML
    private void closeApp(ActionEvent event) {
    }

    @FXML
    private void getnotif(MouseEvent event) {
    }

    @FXML
    private void showAddPartageExp(ActionEvent event) {
    }
    
}
