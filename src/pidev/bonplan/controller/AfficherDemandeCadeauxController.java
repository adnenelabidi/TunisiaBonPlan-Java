/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import pidev.bonplan.entites.DemandeCadeau;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.bonplan.entites.Boncadeaux;
import pidev.bonplan.entites.Cadeaux;
import pidev.bonplan.entites.Compte;
import pidev.bonplan.entites.Mailing;
import pidev.bonplan.entites.User;
import pidev.bonplan.services.MyServices;
import pidev.bonplan.services.ServiceSysdate;

/**
 * FXML Controller class
 *
 * @author Abdelkader
 */
public class AfficherDemandeCadeauxController implements Initializable {
ServiceSysdate sys = new ServiceSysdate();
    @FXML
    private AnchorPane ListeDesDemandeCadeaAnchorPane;
    @FXML
    private AnchorPane anchorListePatissier;
    @FXML
    private TableView<DemandeCadeau> tableviewListeDemandeAffectation;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneNumero;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneDateDemande;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonnePhotoMembreDemande;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneMembreDemande;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonnetablecolonneMembrPoint;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonnetablecolonneMembreId;
    @FXML
    private Button buttonAccepteDemande;
   
    @FXML
    private Button buttonAttenteDemande;
    @FXML
    private Button buttonRetourBonCadeaux;

        
      private List<DemandeCadeau> listDemandeCadeau;//retourner liste de la select
    private ObservableList<DemandeCadeau> ObservablelisteDemandeCadeau;//pour la table view
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonnetablecolonneDemandeCadeau;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonnetablecolonneDemandeStatus1;
    @FXML
    private Button buttonRefuserDemande;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createtableviewDemandeCadeau();
    }    
    private void createtableviewDemandeCadeau() {
        
          MyServices services = new MyServices();
        
          tablecolonneNumero.setCellValueFactory(new PropertyValueFactory<>("idDemandeCadeaux"));//nom reference pour la colonne
        tablecolonneDateDemande.setCellValueFactory(new PropertyValueFactory<>("datedemandeDemandeCadeaux"));//nom reference pour la colonne
 
                tablecolonnePhotoMembreDemande.setCellValueFactory(new PropertyValueFactory<>("ImageMembreDemande"));//reference pour la colonne
       tablecolonnetablecolonneMembreId.setCellValueFactory(new PropertyValueFactory<>("IdMembreDemande"));//reference pour la colonne

          tablecolonnetablecolonneMembrPoint.setCellValueFactory(new PropertyValueFactory<>("PointMembreDemande"));//nom reference pour la colonne
        //reference pour la colonne
         tablecolonnetablecolonneDemandeCadeau.setCellValueFactory(new PropertyValueFactory<>("LibelleCadeaux"));//reference pour la colonne
         tablecolonnetablecolonneDemandeStatus1.setCellValueFactory(new PropertyValueFactory<>("StatusDemandeCadeaux"));//reference pour la colonne
        listDemandeCadeau = services.afficherlisteDemandecadeaux();

        ObservablelisteDemandeCadeau = FXCollections.observableArrayList(listDemandeCadeau);//convertir la liste des client en observable liste
        tableviewListeDemandeAffectation.setItems(ObservablelisteDemandeCadeau);
    }
    @FXML
    private void AccepteDemande(ActionEvent event) throws ParseException {
        
        
               MyServices services = new MyServices();
        DemandeCadeau  demandeCadeau = tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem();

        if (demandeCadeau != null) {
            
            Cadeaux cadeaux=new Cadeaux();
            Compte compte=new  Compte();
            
             cadeaux=services.chercherCadeauxById(demandeCadeau.getCadeaux().getId());
            compte=services.chercherCompteById(demandeCadeau.getMembreDemande().getIdCompte());
            User user=services.chercherUtilisateurByid(demandeCadeau.getMembreDemande().getId_user());
            System.out.println("user.toString()==>"+user.toString());
//            System.out.println("Compte"+compte.toString());
//           System.out.println(compte.getId_user().getEmail());
//               System.out.println(compte.getId_user().getNom());
//               System.out.println(compte.getId_user().getPrenom());
//            
             Alert alert = new Alert(Alert.AlertType.ERROR);
             
             
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = demandeCadeau.getDatedexpirationDemandeCadeaux();

 

            Date dateo = formatter.parse(dateInString);
 
             
              
            if (dateo.after(sys.selectDate()))
            {
                
              System.out.println(dateo);
            System.out.println(sys.selectDate());
            
            
                System.out.println("Date experation apres la date d'aujourd'hui ");
            
          
                       
                if (cadeaux.getValeur_point() < compte.getPoint_merci()) {
                    
                    System.out.println("cadeaux.getValeur_point() < compte.getPoint_merci()");
                       demandeCadeau.setStatusDemandeCadeaux("Accepte");
                       demandeCadeau.setMessageDemandeCadeaux("Ton demande  est Accepter");
                 
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application ton demande du cadeaux est accepter vous pouver confirmer ton recevoire du email et de imprimer la demande en papier";
            String usermail = "abdelkader.jouini@esprit.tn";
            String passmail = "SALIHsonia22";
             Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu as accepter la demande et tu as envoyer une email de confirmation a!"+compte.getId_user().getUsername())
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                    
                } else  if ((cadeaux.getValeur_point() - compte.getPoint_merci()) > 5) {
                    
                       demandeCadeau.setStatusDemandeCadeaux("Attente");
                        demandeCadeau.setMessageDemandeCadeaux("Ton demande  est en Attente");
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application Tu ne peut pas avoire ce cadeaux car le nombre de point est insuffisant ton demande est en cours de traitement";
            String usermail = "abdelkader.jouini@esprit.tn";
            String passmail = "SALIHsonia22";
             Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu ne peut pas donner ce cadeaux pour ce membre ca les points est insuffisant le demande est en cours de traitement!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                    
                 
                }
                else {
                    
                    
                     
                       demandeCadeau.setStatusDemandeCadeaux("Reffuse");
                        demandeCadeau.setMessageDemandeCadeaux("Ton demande  est  Reffuser");
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application Tu ne peut pas avoire ce cadeaux car le nombre de point est insuffisant ton demande est en cours de traitement";
            String usermail = "abdelkader.jouini@esprit.tn";
            String passmail = "SALIHsonia22";
        Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu ne peut pas donner ce cadeaux pour ce membre ca les points est insuffisant le demande est Reffuser!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                }
                
                
                            
        } else {
                
                
                
                
                   alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date d'expiration de votre Demande est superieur à la date actuelle");
                            alert.show();
                
            }
            
            
            
            
            
            
            
            
            
            
            
 
                     
          services.modifierDemandecadeaux(demandeCadeau);
                 createtableviewDemandeCadeau();    
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner une demande");
            alert.show();
        }
                    
        
    }
  

    

    @FXML
    private void AttenteDemande(ActionEvent event) throws ParseException {
        
        
          
               MyServices services = new MyServices();
        DemandeCadeau  demandeCadeau = tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem();

        if (demandeCadeau != null) {
            
            Cadeaux cadeaux=new Cadeaux();
            Compte compte=new  Compte();
            
            
            
            
            
            cadeaux=services.chercherCadeauxById(demandeCadeau.getCadeaux().getId());
            compte=services.chercherCompteById(demandeCadeau.getMembreDemande().getIdCompte());
             User user=services.chercherUtilisateurByid(demandeCadeau.getMembreDemande().getId_user());
             Alert alert = new Alert(Alert.AlertType.ERROR);
              
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = demandeCadeau.getDatedexpirationDemandeCadeaux();

 

            Date dateo = formatter.parse(dateInString);
 
             
              
            if (dateo.after(sys.selectDate()))
            {
                
              System.out.println(dateo);
            System.out.println(sys.selectDate());
            
            
                System.out.println("Date experation apres la date d'aujourd'hui ");
            
          
 
                    
                       demandeCadeau.setStatusDemandeCadeaux("Attente");
                        demandeCadeau.setMessageDemandeCadeaux("Ton demande  est en Attente");
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application Tu ne peut pas avoire ce cadeaux car le nombre de point est insuffisant ton demande est en cours de traitement";
            String usermail = "abdelkader.jouini@esprit.tn";
            String passmail = "SALIHsonia22";
            //Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu ne peut pas donner ce cadeaux pour ce membre ca les points est insuffisant le demande est en cours de traitement!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                    
                 
              
                
                
                
                            
        } else {
                
                
                
                
                   alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date d'expiration de votre Demande est superieur à la date actuelle");
                            alert.show();
                
            }
            
            
            
  
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner une demande");
            alert.show();
        }
                  services.modifierDemandecadeaux(demandeCadeau);
                 createtableviewDemandeCadeau();     
        
        
        
        
        
        
        
        
        
    }

    @FXML
    private void RetourBonCadeaux(ActionEvent event) throws IOException {
        
         setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/ListAffectationDemandeCadeaux.fxml")));
    }
    
    
     
                   private void setNode(Node node) {
        ListeDesDemandeCadeaAnchorPane.getChildren().clear();
        ListeDesDemandeCadeaAnchorPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void RefuserDemande(ActionEvent event) throws ParseException {
        
            
          
               MyServices services = new MyServices();
        DemandeCadeau  demandeCadeau = tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem();

        if (demandeCadeau != null) {
            
            Cadeaux cadeaux=new Cadeaux();
            Compte compte=new  Compte();
            
            
            cadeaux=services.chercherCadeauxById(demandeCadeau.getCadeaux().getId());
            compte=services.chercherCompteById(demandeCadeau.getMembreDemande().getIdCompte());
             User user=services.chercherUtilisateurByid(demandeCadeau.getMembreDemande().getId_user());
             Alert alert = new Alert(Alert.AlertType.ERROR);
              
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = demandeCadeau.getDatedexpirationDemandeCadeaux();

 

            Date dateo = formatter.parse(dateInString);
 
             
              
            if (dateo.after(sys.selectDate()))
            {
                
              System.out.println(dateo);
            System.out.println(sys.selectDate());
            
            
                System.out.println("Date experation apres la date d'aujourd'hui ");
            
          
 
                     
                       demandeCadeau.setStatusDemandeCadeaux("Reffuse");
                        demandeCadeau.setMessageDemandeCadeaux("Ton demande  est  Reffuser");
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application Tu ne peut pas avoire ce cadeaux car le nombre de point est insuffisant ton demande est en cours de traitement";
            String usermail = "abdelkader.jouini@esprit.tn";
            String passmail = "SALIHsonia22";
             Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu ne peut pas donner ce cadeaux pour ce membre ca les points est insuffisant le demande est Reffuser!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                    
                 
              
                
                
                
                            
        } else {
                
                
                
                
                   alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date d'expiration de votre Demande est superieur à la date actuelle");
                            alert.show();
                
            }
            
            
            
  
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner une demande");
            alert.show();
        }
                  services.modifierDemandecadeaux(demandeCadeau);
                 createtableviewDemandeCadeau();    
        
    }
                   
    
    
}
