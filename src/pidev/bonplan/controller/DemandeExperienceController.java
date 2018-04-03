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
import org.apache.commons.codec.binary.Hex;
import javafx.stage.FileChooser;
import javafxapplication1.forum.entities.demande_experience;
import pidev.bonplan.services.DemandeRecommandation;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class DemandeExperienceController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    @FXML
    private JFXButton btnajoutdemande;
    @FXML
    private JFXButton ajouterdemande;
    @FXML
    private ImageView imgdemande;
  
  FileChooser fileChooser;
    @FXML
    private AnchorPane demandepane;
    @FXML
    private JFXTextField titledem;
    @FXML
    private JFXTextArea descripdem;
    @FXML
    private JFXTextField adressdem;
      demande_experience p=new demande_experience();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void addimage(ActionEvent event) throws MalformedURLException, NoSuchAlgorithmException {

        System.out.println("Load Image Button Pressed");
        fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(demandepane.getScene().getWindow());
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
    
    
}
