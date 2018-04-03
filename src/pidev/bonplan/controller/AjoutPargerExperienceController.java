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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
 
import org.apache.commons.codec.binary.Hex;
import org.controlsfx.control.Rating;
/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class AjoutPargerExperienceController implements Initializable {

    @FXML
    private Rating raiting;

    @FXML
    private ImageView expimage;
    @FXML
    private JFXButton btnajouterexp;
FileChooser fileChooser;
    @FXML
    private JFXButton selectimg;
    @FXML
    private AnchorPane ajoutpane;
    @FXML
    private JFXTextField nompx;
    @FXML
    private JFXTextField descripex;
    @FXML
    private JFXTextField adresseexp;
    @FXML
    private JFXComboBox<?> regionexp;
    @FXML
    private JFXComboBox<?> catexp;
    @FXML
    private JFXComboBox<?> prixexp;
    @FXML
    private JFXCheckBox pcarte;
    @FXML
    private JFXCheckBox wifi;
    @FXML
    private JFXCheckBox clim;
    @FXML
    private JFXCheckBox snckb;
    @FXML
    private JFXCheckBox park;
    @FXML
    private JFXCheckBox picine;
    @FXML
    private JFXCheckBox balcon;
    @FXML
    private JFXCheckBox vguider;
    @FXML
    private JFXCheckBox fumer;
    @FXML
    private JFXCheckBox resrv;
    @FXML
    private JFXCheckBox familial;
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

    File file = fileChooser.showOpenDialog(ajoutpane.getScene().getWindow());
    if (file != null) {
        System.out.println("File Was Selected");
        URL url = file.toURI().toURL();
             String urlimg=url.getFile().replaceFirst("/", "");
       //  System.out.println("url = "+urlimg);
    
         MessageDigest md = MessageDigest.getInstance("MD5");
           byte[] urlmd5=md.digest(urlimg.getBytes());
           final String result = new String(Hex.encodeHex(urlmd5));
     //    System.out.println("url md5 = "+result);
        expimage.setImage(new Image(url.toExternalForm()));

    }
    }
    
}
