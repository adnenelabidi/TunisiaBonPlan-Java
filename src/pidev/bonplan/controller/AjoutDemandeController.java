/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxapplication1.forum.entities.demande_experience;
import org.apache.commons.codec.binary.Hex;
import pidev.bonplan.services.DemandeRecommandation;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class AjoutDemandeController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;
    @FXML
    private JFXTextField titledem;
    @FXML
    private JFXTextArea descripdem;
    @FXML
    private JFXTextField adressdem;
    @FXML
    private JFXButton btnajoutdemande;
    @FXML
    private JFXButton ajouterdemande;
    @FXML
    private ImageView imgdemande;
  FileChooser fileChooser;
   demande_experience p=new demande_experience();
    @FXML
    private AnchorPane demandepanes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void addimage(ActionEvent event) throws MalformedURLException, NoSuchAlgorithmException {

        System.out.println("Load Image Button Pressed");
        fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(demandepanes.getScene().getWindow());
        if (file != null) {
            System.out.println("File Was Selected");
            URL url = file.toURI().toURL();
            String urlimg = url.getFile().replaceFirst("/", "");
            //  System.out.println("url = "+urlimg);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] urlmd5 = md.digest(urlimg.getBytes());
            final String result = new String(Hex.encodeHex(urlmd5));
            //    System.out.println("url md5 = "+result);
            p.setImage(result);
            imgdemande.setImage(new Image(url.toExternalForm()));

        }
        
        
    }
    @FXML
    public void ajouterdemandebtn(){
      
        DemandeRecommandation dem=new DemandeRecommandation();
        p.setNom(titledem.getText());
        p.setDescripion(descripdem.getText());
        p.setAddresse(adressdem.getText());
        dem.ajouterDemande(p);
    
    }

    @FXML
     public void closeApp(){
        
          // get a handle to the stage
    Stage stage = (Stage) btnclose.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
}
