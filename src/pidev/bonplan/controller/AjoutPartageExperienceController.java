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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Hex;
import org.controlsfx.control.Rating;
 
/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class AjoutPartageExperienceController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;
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
    private JFXCheckBox pcarte;
    @FXML
    private Rating raiting;
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
    @FXML
    private JFXButton btnajouterexp;
    @FXML
    private ImageView expimage;
    @FXML
    private JFXButton selectimg;
FileChooser fileChooser;
    @FXML
    private Pane partageexpbtn;
    @FXML
    private AnchorPane partageexppane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void BackHomes(MouseEvent event) throws IOException {
      
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/bonplan/GUI/UserInterface.fxml"));
    Parent root=loader.load();
        partageexppane.getScene().setRoot(root);
      
   
    }

    @FXML
    private void closeApp(ActionEvent event) {
                
          // get a handle to the stage
    Stage stage = (Stage) btnclose.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
public  String upload(File file,String fileName) throws  IOException {
        BufferedOutputStream stream = null;
        String globalPath="C:\\xampp\\htdocs\\PhpstormProjects\\Pidev3\\web\\uploads\\images";
        String localPath="localhost:80/";
       /*fileName = file.getName();
        fileName=fileName.replace(" ", "_");*/
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
    
            File dir = new File(globalPath);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()+File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath+"/"+fileName;
        } catch (IOException ex) {
            Logger.getLogger(AjoutDemandeController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        
        }}
     @FXML
         public void addimage(ActionEvent event) throws MalformedURLException, NoSuchAlgorithmException, IOException {
      
       System.out.println("Load Image Button Pressed");
    fileChooser = new FileChooser();

    File file = fileChooser.showOpenDialog(partageexppane.getScene().getWindow());
    if (file != null) {
        System.out.println("File Was Selected");
        URL url = file.toURI().toURL();
             String urlimg=url.getFile().replaceFirst("/", "");
       //  System.out.println("url = "+urlimg);
    
         MessageDigest md = MessageDigest.getInstance("MD5");
           byte[] urlmd5=md.digest(urlimg.getBytes());
           final String result = new String(Hex.encodeHex(urlmd5));
             upload(file,result);
     //    System.out.println("url md5 = "+result);
        expimage.setImage(new Image(url.toExternalForm()));

    }
    }
}
