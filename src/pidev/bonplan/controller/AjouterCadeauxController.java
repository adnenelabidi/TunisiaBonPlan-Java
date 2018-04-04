/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import pidev.bonplan.entites.BonPlan;
import pidev.bonplan.entites.Boncadeaux;
import pidev.bonplan.entites.Cadeaux;
import pidev.bonplan.entites.Categoris;
import pidev.bonplan.entites.Compte;
import pidev.bonplan.entites.User;
import pidev.bonplan.services.MyServices;
import pidev.bonplan.services.ServiceSysdate;

/**
 * FXML Controller class
 *
 * @author Abdelkader
 */
public class AjouterCadeauxController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxSelectionnerPartenaire;
    @FXML
    private TableView<BonPlan> tableViewBonPlan;
    @FXML
    private TableColumn<BonPlan, String> tableColumnLibelle;
    @FXML
    private TableColumn<BonPlan, String> tableColumnCategoris;
    @FXML
    private TableColumn<BonPlan, String> tableColumnDescription;
 
    @FXML
    private ComboBox<String> comboBoxSelectionnerBonPlan;
 
    
    private List<String> listPartnaire;
    private List<BonPlan> listPlan;
    private ObservableList<String> ObservableListPartnaire;
    private ObservableList<String> ObservableListBonPlan;
    private ObservableList<BonPlan> ObservableListBonPlanTableView;
    
    
      private Stage stage;//controle de classe(annuler ou fermer) stage(serie)
    private boolean buttonConfimClicked = false;//nous informer est ce que on clicker sur confimer ou annuler
    
    private Cadeaux cadeaux;
    private Boncadeaux boncadeaux;
         MyServices services = new MyServices();
         private boolean  Ajout = false;

    public boolean getAjout() {
        return Ajout;
    }

    public void setAjout(boolean Ajout) {
        this.Ajout = Ajout;
    }

  
         
         
    
    @FXML
    private DatePicker datePickerAjoutCadeaux;
    @FXML
    private TextField TXValeurPointCadeaux;
    @FXML
    private TextField TXValeurPrixReel;
    @FXML
    private TextField TXTypeBonCadeaux;
    @FXML
    private Button buttonConfirmer;
    @FXML
    private Button BtAnuller;
    @FXML
    private JFXTextField TXQuantite;
 
         
         
 
  
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
 
       
    }

    public Boncadeaux getBoncadeaux() {
        return boncadeaux;
    }

    public void setBoncadeaux(Boncadeaux boncadeaux) {
        this.boncadeaux = boncadeaux;
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
    
    public void ChargerComboBoxListeDesPartenaire()
            {
                    String ROLE="a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}";
                     listPartnaire=services.afficherlistePartenaire(ROLE);
                    if (listPartnaire.equals("")) {
                    
                     Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text(" Tu ne peut pas demander des cadeaux il y n'est des partenaire");
                n.showError();
                } else {
                               
                     Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("Choisir un Bon Plan pour Ajouter un cadeaux");
                n.showError(); 
                       ObservableListPartnaire=FXCollections.observableArrayList(listPartnaire);
                     comboBoxSelectionnerPartenaire.setItems(ObservableListPartnaire); 
                }
                    
            
            }
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        ChargerComboBoxListeDesPartenaire();
        TXTypeBonCadeaux.setText("demande");
        TXTypeBonCadeaux.setEditable(false);
        TXTypeBonCadeaux.isDisable();
 
            MyServices services = new MyServices();
    }    


       ServiceSysdate sys = new ServiceSysdate();
    @FXML
    private void handleButtonConfirmer(ActionEvent event) {
  
        
          Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
        
        if (comboBoxSelectionnerPartenaire.getValue()==null)
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir le partenaire");
                            alert.show();
                            comboBoxSelectionnerPartenaire.requestFocus();
        }
        
           else  if (comboBoxSelectionnerBonPlan.getValue()==null)
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir le bon plan ");
                            alert.show();
                            comboBoxSelectionnerBonPlan.requestFocus();
        }
     
        else if (datePickerAjoutCadeaux.getEditor().getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la date de votre demande");
                            alert.show();
                            datePickerAjoutCadeaux.requestFocus();   
        }
  
     
  
        
          
 
       else if (TXTypeBonCadeaux.getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la  Type BonCadeaux ");
                            alert.show();
                            datePickerAjoutCadeaux.requestFocus();   
        }
       else if (TXValeurPointCadeaux.getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la point du cadeaux");
                            alert.show();
                            datePickerAjoutCadeaux.requestFocus();   
        }
       else if (TXTypeBonCadeaux.getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la point du cadeaux");
                            alert.show();
                            datePickerAjoutCadeaux.requestFocus();   
        }
       else if (TXQuantite.getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la quantite demander du cadeaux");
                            alert.show();
                            datePickerAjoutCadeaux.requestFocus();   
        }
       
       else if (!isInteger(TXQuantite.getText()))
        {    
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Le Quantite doit être un entier ");
                n.showError();          
          
        }
       else if (!isInteger(TXValeurPointCadeaux.getText()))
        {    
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Le Valeur du cadeaux doit être un entier ");
                n.showError();          
          
        }
        
        
        
          
        else
        { 
            Date dateo = Date.valueOf(datePickerAjoutCadeaux.getValue());
            if (dateo.before(sys.selectDate()))
            {
                
              System.out.println(datePickerAjoutCadeaux.getEditor().getText());
            System.out.println(sys.selectDate());
             alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date de votre Demande est superieur à la date actuelle");
                            alert.show();
                            datePickerAjoutCadeaux.requestFocus();
                            
        }
            
            
            
            else
            {
                String username = comboBoxSelectionnerPartenaire.getSelectionModel().getSelectedItem();
                String  bonPlanlLibelle=  comboBoxSelectionnerBonPlan.getSelectionModel().getSelectedItem();
                User user = services.chercherUtilisateurByUsername(username);
               
               
                
                /************************Compte******************************/
                Compte compte = services.chercherUtilisateurByUsernameDansLecompte(username);
                /************************Creation de la cadeau******************************/
                
          
                
          //     `libelle`, `description`, `prix_reel`, `valeur_point`,
         //   `quantite_initial`, `quantite_actuel`, `image`, `categorisCadeaux`)

 
                 BonPlan bonPlan=services.chercherBonPlanBylibelle(bonPlanlLibelle);
    
                
                
                Cadeaux cadeauxAjout=services.chercherCadeauxByLibelle(bonPlan.getLibelle());
                  
                 if (cadeauxAjout.getId()==0) {
                      System.out.println("Ajout====>"+cadeauxAjout.toString());
                       cadeaux.setLibelle(bonPlan.getLibelle());
                 cadeaux.setDescription(bonPlan.getDescription());
                 cadeaux.setPrix_reel(Float.valueOf(TXValeurPrixReel.getText()) );
                cadeaux.setValeur_point(Float.valueOf(TXValeurPointCadeaux.getText()) ); 
                 cadeaux.setQuantite_actuel(Integer.valueOf(TXQuantite.getText()) ); 
                  cadeaux.setQuantite_initial(Integer.valueOf(TXQuantite.getText())); 
                 cadeaux.setImage(bonPlan.getImage()) ; 
                 Categoris categoris=new Categoris();
                 categoris.setId(bonPlan.getRefcategorie().getId());
                 cadeaux.setCategorisCadeaux(categoris); 
                     Ajout=true;
                  
                }
                 
                else
                    
                 
                     
                 {  System.out.println("Modification====>"+cadeauxAjout.toString());
                 cadeaux.setId(cadeauxAjout.getId());
                             cadeaux.setLibelle(bonPlan.getLibelle());
                 cadeaux.setDescription(bonPlan.getDescription());
                 cadeaux.setPrix_reel(Float.valueOf(TXValeurPrixReel.getText()) );
                cadeaux.setValeur_point(Float.valueOf(TXValeurPointCadeaux.getText()) ); 
                     System.out.println("TXQuantite.getText()"+(Integer.valueOf(TXQuantite.getText())));
                     System.out.println("cadeauxAjout.getQuantite_actuel())"+cadeauxAjout.getQuantite_actuel());
                 cadeaux.setQuantite_actuel((Integer.valueOf(TXQuantite.getText()))+cadeauxAjout.getQuantite_actuel() ); 
                 cadeaux.setQuantite_initial(cadeauxAjout.getQuantite_initial()); 
                 cadeaux.setImage(bonPlan.getImage()) ; 
                 Categoris categoris=new Categoris();
                 categoris.setId(bonPlan.getRefcategorie().getId());
                 cadeaux.setCategorisCadeaux(categoris);
                     
                      Ajout=false;
                 
                 }
                 
              
            System.out.println("Ajouttttt====>"+Ajout);

            if (Ajout==true) {
                services.ajouterCadeaux(cadeaux);
            } else {
                
                 services.modifierCadeaux(cadeaux);
            }
                   
      
         cadeaux=services.chercherCadeauxByLibelle(cadeaux.getLibelle());
            
                
                
                
                
                
                       
                   boncadeaux.setCadeaux(cadeaux);
                 boncadeaux.setMembreConcerne(compte);         
                 boncadeaux.setDate_cadeaux(datePickerAjoutCadeaux.getValue().toString());
                 boncadeaux.setType_bonBoncadeaux(TXTypeBonCadeaux.getText());
               boncadeaux.setDescriptionBoncadeaux("Demande du cadeaux");
           
           
//                              cadeaux.setQuantite_actuel(cadeaux.getQuantite_actuel() - 1);
//                //compte.setPoint_merci((int) (compte.getPoint_merci()- cadeaux.getValeur_point()));  
//                 
//                 boncadeaux.setCadeaux(cadeaux);
//                 boncadeaux.setMembreConcerne(compte);
//                 boncadeaux.setDescriptionBoncadeaux("Demande du cadeaux");
//                 boncadeaux.setType_bonBoncadeaux(TXYpeCadeaux.getText());
//                 boncadeaux.setDate_cadeaux(DateAffectationCadeaux.getValue().toString());
//                 
//                 System.out.println("Bon ====>"+boncadeaux.toString());
//                 
//            
          
                buttonConfimClicked = true;
 
            stage.close();

 
               
          
                
                  
                     
                
            }}
        }

    @FXML
    private void handleButtonAnnuler(ActionEvent event) {
            stage.close();
    }
 
 

    @FXML
    private void AfficherBonPlan(ActionEvent event) {
        
                String Username=comboBoxSelectionnerPartenaire.getSelectionModel().getSelectedItem();
                    User Partenaire=new User();
                    System.out.println(Username);
                    Partenaire=services.chercherUtilisateurByUsername(Username);
                     listPlan=services.afficherlisteBonPlanParUser22(Partenaire);
                     List<String> listeNameBonPlan=new ArrayList<>();
                     
      for (BonPlan bonPlan : listPlan) {
            listeNameBonPlan.add(bonPlan.getLibelle());
            System.out.println(bonPlan.toString());
            
        }
        if (listeNameBonPlan.isEmpty()) {
             Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text(" Tu ne peut pas demander des cadeaux ce partenaire 'a pas des bon plan");
                n.showError();
        } else {
    
            System.out.println(listeNameBonPlan.toString());
             ObservableListBonPlan=FXCollections.observableArrayList(listeNameBonPlan);
                          comboBoxSelectionnerBonPlan.setItems(ObservableListBonPlan);
        }
            
     
                     
                    
    }

    @FXML
    private void createtableviewBonPlan(ActionEvent event) {
        
        
        
        MyServices services = new MyServices();
        tableColumnLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));//nom reference pour la colonne
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));//nom reference pour la colonne
        //reference pour la colonne
        tableColumnCategoris.setCellValueFactory(new PropertyValueFactory<>("refcategorie"));//reference pour la colonne
    
         
         
          
       String Username=comboBoxSelectionnerPartenaire.getSelectionModel().getSelectedItem();
                    User Partenaire=new User();
                    System.out.println(Username);
                    Partenaire=services.chercherUtilisateurByUsername(Username);
                     listPlan=services.afficherlisteBonPlanParUser22(Partenaire);
                     
                                        List<BonPlan> listeNameBonPlan=new ArrayList<>();
      
                     
                     ObservableListBonPlanTableView=FXCollections.observableArrayList(listPlan);
           
        tableViewBonPlan.setItems(ObservableListBonPlanTableView);
    }
    
    
  
    }
    
 
