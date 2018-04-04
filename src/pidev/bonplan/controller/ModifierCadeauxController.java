/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import pidev.bonplan.entites.Cadeaux;
import pidev.bonplan.services.MyServices;

/**
 * FXML Controller class
 *
 * @author Abdelkader
 */
public class ModifierCadeauxController implements Initializable {

    @FXML
    private JFXTextField TXValeurPrixReel;
    @FXML
    private JFXTextField TXLibelle;
    @FXML
    private JFXTextField TXQuantite;
    @FXML
    private JFXTextField TXDesciption;
    @FXML
    private JFXTextField TXValeurPointCadeaux;

    
    
          private Stage stage;//controle de classe(annuler ou fermer) stage(serie)
    private boolean buttonConfimClicked = false;//nous informer est ce que on clicker sur confimer ou annuler
             MyServices services = new MyServices();
    private Cadeaux cadeaux;
    @FXML
    private Button buttonConfirmer;
    @FXML
    private Button BtAnuller;
    @FXML
    private ImageView ImgCadeaux;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isButtonConfimClicked() {
        return buttonConfimClicked;
    }

    public void setButtonConfimClicked(boolean buttonConfimClicked) {
        this.buttonConfimClicked = buttonConfimClicked;
    }

    public Cadeaux getCadeaux() {
        return cadeaux;
    }

    public void setCadeaux(Cadeaux cadeaux) {
        this.cadeaux = cadeaux;
        this.TXDesciption.setText(cadeaux.getDescription());
        this.TXLibelle.setText(cadeaux.getLibelle());
        this.TXQuantite.setText(String.valueOf(cadeaux.getQuantite_actuel()));
        this.TXValeurPointCadeaux.setText(String.valueOf(cadeaux.getValeur_point()));
        this.TXValeurPrixReel.setText(String.valueOf(cadeaux.getPrix_reel()));
        
    }
    
    
      
        public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    return true;
}
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
     TXLibelle.setEditable(false);
 
    } 
    
   

      @FXML
    private void handleButtonConfirmer(ActionEvent event) {
  
        
          Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
        
        if (TXLibelle.getText().equals(""))
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez entre la Libelle ");
                            alert.show();
                            TXLibelle.requestFocus();
        }
        
       
     
  
  
     
  
        
          
 
       else if (TXDesciption.getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la Desciption ");
                            alert.show();
                            TXDesciption.requestFocus();   
        }
       else if (TXQuantite.getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la Quantite");
                            alert.show();
                            TXQuantite.requestFocus();   
        }
       
             
       else if (!isInteger(TXQuantite.getText()))
        {    
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Le prix doit être un Quantite ");
                n.showError();          
            
        }
       else if (TXValeurPointCadeaux.getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la Quantite");
                            alert.show();
                            TXQuantite.requestFocus();   
                        
        }
       
                    
       else if (!isInteger(TXValeurPrixReel.getText()))
        {    
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("Le prix reel du Cadeaux doit être un entier ");
                n.showError();          
            
        }
       else if (!isInteger(TXValeurPointCadeaux.getText()))
        {    
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("Le Valeur Point Cadeaux du Cadeaux doit être un entier ");
                n.showError();          
            
        }
       
       
 
 
      
       
    
      
   
            
            
            
            else
            {
       this.cadeaux = cadeaux;
      cadeaux.setDescription(TXDesciption.getText());
      cadeaux.setLibelle(TXLibelle.getText());
     cadeaux.setQuantite_actuel((Integer.valueOf(TXQuantite.getText()))+cadeaux.getQuantite_actuel());
      cadeaux.setValeur_point(Float.valueOf(TXValeurPointCadeaux.getText()));
      cadeaux.setPrix_reel(Float.valueOf(TXValeurPrixReel.getText()));
      cadeaux.setQuantite_initial(cadeaux.getQuantite_initial());
      cadeaux.setCategorisCadeaux(cadeaux.getCategorisCadeaux());
 
         
                buttonConfimClicked = true;
 
            stage.close();

 
               
          
                
                  
                     
                
            }}

    @FXML
    private void handleButtonAnnuler(ActionEvent event) {
        
         stage.close();
    }
        }

  
    
 
