/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.bonplan.entites.Boncadeaux;
import pidev.bonplan.entites.Cadeaux;
import pidev.bonplan.entites.User;
import pidev.bonplan.services.MyServices;
import pidev.bonplan.services.ServiceNotification;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Abdelkader
 */
public class ListeCadeauxAdminController implements Initializable {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    @FXML
    private ImageView imgpro;
    @FXML
    private Label labeleLibelle;
    @FXML
    private Label labelDescription;
    @FXML
    private Button buttonModifierCadeaux;
    @FXML
    private Button buttonSupprimerCadeaux;
    @FXML
    private Button buttonAffecterCadeaux;
    @FXML
    private Button buttonAfficherAffectation;
    @FXML
    private Button buttonAjouterCadeaux;
    @FXML
    private TextField searchfield;
    @FXML
    private TableView<Cadeaux> tableviewCadeaux;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauId;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauImage;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauLibelle;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauDescription;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauPrix;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauQuantite;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauValeur;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauCategeoris;

    public static int numeroCommande = 0;
    Document doc = new Document();
    private List<Cadeaux> listCadeaux;//retourner liste de la select
    private ObservableList<Cadeaux> ObservablelisteCadeaux;//pour la table view
    @FXML
    private Button buttonAfficgherListeCadeaux;
    @FXML
    private BorderPane ListeCadeauxBorderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createtableviewCadeaux();
        tableviewCadeaux.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            try {
                selectionnerUnCadeaux(newValue);
            } catch (BadElementException ex) {
                Logger.getLogger(ListeCadeauxAdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ListeCadeauxAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void selectionnerUnCadeaux(Cadeaux cadeaux) throws BadElementException, IOException//quand on selectionner un user il declanche  
    {

        labelDescription.setText(cadeaux.getDescription());
        labeleLibelle.setText(cadeaux.getLibelle());
        
        
           File file = new File("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\images\\"+cadeaux.getImage());
        javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toURL().toString());
        
        
 
              
            imgpro.setImage(img) ;
    }

    @FXML
    private void modifierCadeau(ActionEvent event) throws IOException {

        MyServices services = new MyServices();
        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();

        if (cadeaux != null) {

            boolean buttonConfimClicked = showModifierCadeauxFXML(cadeaux);//cette qui va ouvrir cette page (dmender cete methode) 
            if (buttonConfimClicked) {

                services.modifierCadeaux(cadeaux);
                createtableviewCadeaux();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un cadeaux");
            alert.show();
        }

    }

    @FXML
    private void supprimerCadeau(ActionEvent event) {
        MyServices services = new MyServices();
        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();

        if (cadeaux != null) {

            services.supprimerCadeaux(cadeaux);
            createtableviewCadeaux();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un Cadeaux");
            alert.show();
        }

    }

    @FXML
    private void AffecterCadeaux(ActionEvent event) throws IOException, SQLException {
        MyServices services = new MyServices();
        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();
        Boncadeaux boncadeaux = new Boncadeaux();

        if (cadeaux != null) {

            boolean buttonConfimClicked = showAjouterBonCadeauxFXML(cadeaux, boncadeaux);//cette qui va ouvrir cette page (dmender cete methode) 
            if (buttonConfimClicked) {
                System.out.println("eeeeeee====>" + boncadeaux.toString());

                services.AjouteBonCadeaux(boncadeaux);
                
                   ServiceNotification.showNotif("Succe", "Membre affecter avec succée");
                                
 
          
                createtableviewCadeaux();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner uu cadeaux");
            alert.show();
        }

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @FXML
    private void AfficherAffectation(ActionEvent event) throws IOException {

        setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/ListAffectationDemandeCadeaux.fxml")));

    }

    private void setNode(Node node) {
        ListeCadeauxBorderPane.getChildren().clear();
        ListeCadeauxBorderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void ajouterCadeaux(ActionEvent event) throws IOException, SQLException {

        MyServices services = new MyServices();
        Cadeaux cadeaux = new Cadeaux();
        Boncadeaux boncadeaux = new Boncadeaux();
        boolean Ajout = true;

        //connecter au fb bech nimchi lil set client fil controlleur AjouterPersonne2Controller
        boolean buttonConfimClicked = showAjouterCadeauxFXML(cadeaux, boncadeaux, Ajout);//cette qui va ouvrir cette page (dmender cete methode) 

        if (buttonConfimClicked) {

            numeroCommande = numeroCommande + 1;
            String nom = "BonCadeaux" + numeroCommande + ".pdf";
            try {

          

                 

              SimpleDateFormat  date = new SimpleDateFormat("dd/MM/yyyy");
             SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");

                
                //Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\BonCadeaux\\BonCadeaux" + nom));
                doc.open();
                       Image img = Image.getInstance("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\images\\User.png");
                doc.add(img);
                doc.add(new Paragraph("                                                           "
                        + "                                                                                           "
                        + "    " + date.format(new Date()), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        smallBold));

                doc.add(new Paragraph("                                                           "
                        + "                                                                                                 "
                        + "  " + Heure.format(new Date()), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        smallBold));
                doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                doc.add(new Paragraph("                                    Bon de demande N°" + numeroCommande + " Ajouter un cadeaux dans le stock                                                         "));
                doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                doc.add(new Paragraph("Bon Plan",subFont));
                

         
         
  
              
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(60);
                table.setSpacingBefore(11f);
                table.setSpacingBefore(11f);
                Font f = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);

                
             

            
                PdfPCell c1 = new PdfPCell(new Paragraph("Libelle"));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                  PdfPCell c2 = new PdfPCell(new Paragraph("Quantité"));
                  c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell c3 = new PdfPCell(new Paragraph(boncadeaux.getCadeaux().getLibelle()));
              c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell c4 = new PdfPCell(new Paragraph(boncadeaux.getCadeaux().getQuantite_actuel()));
         
                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
                table.addCell(c4);
        
             

                doc.add(table);

                doc.close();
                Desktop.getDesktop().open(new File("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\BonCadeaux\\BonCadeaux" + nom));
                writer.close();

            } catch (DocumentException e) {
                e.printStackTrace();
            }

            services.AjouteBonCadeaux(boncadeaux);

            createtableviewCadeaux();

        }

    }

    private boolean showAjouterCadeauxFXML(Cadeaux cadeaux, Boncadeaux boncadeaux, boolean Ajout) throws IOException {
        /**
         * ***********************1ere etape*******************************
         */

        FXMLLoader loader = new FXMLLoader();//qui va charger la fichier de type fxml
        loader.setLocation(AjouterUilisateurController.class.getResource("/pidev/bonplan/GUI/AjouterCadeaux.fxml"));//ouvrire ce fenetre
        AnchorPane page = (AnchorPane) loader.load();//d'ou et cree un attribut page qui va enregister mon loder deja telechagere
        //==> mon fichier et deje tawa en memoire
        /**
         * ***********************2eme etape(configuration pour new fenter*******************************
         */

        //TAWA POUR en voie cette page il faut cree un stage
        Stage dialogeStage = new Stage();
        //a partir de ce stage j'ai cree un titre pour le client
        dialogeStage.setTitle("Ajouter des cadeaux");

        //craetion de la scene et passe l'attrbut page qui le petite ecran de la l'affichage qui on a telechager
        Scene scene = new Scene(page);

        //charger la scene
        dialogeStage.setScene(scene);
        /**
         * ***********************3eme etape*******************************
         */
//cree un attribut unique et nous avons  utliser cete endroit pour appele le controller de cette (AjouterPersonneFXML)
//on va tawa lil AjouterPersonne2Controller donner lui 2 clics(pour voire les attribut stage,personne,confiramation bbuton)
        AjouterCadeauxController controller = loader.getController();

//tawa on va vers les methode ili 3amlou applele lil methode hethi lekbira
        controller.setStage(dialogeStage);
        controller.setCadeaux(cadeaux);//ce client passer en parametre passer pa le controlleur
        controller.setBoncadeaux(boncadeaux);//ce client passer en parametre passer pa le controlleur
        controller.setAjout(Ajout);//ce client passer en parametre passer pa le controlleur

        /**
         * ***********************4eme etape enfin montre l'ecran a l'user
         * attend l'insertion des donne*******************************
         */
//je ne encore envoyoer cet ecran il va aparaitre a partir
        dialogeStage.showAndWait();//je veut lui montre et attender que l'user fermer cette petite ecran plus tard

        /**
         * **********************5eme etape attendre l'user fermer le fentre***************************
         */
//on va modifier setClient pour //on va modifier setClient pour  
        return controller.isButtonConfimClicked();
    }

    @FXML
    private void createtableviewCadeaux() {

        MyServices services = new MyServices();
        tablecolonneCadeauId.setCellValueFactory(new PropertyValueFactory<>("Id"));//nom reference pour la colonne
        tablecolonneCadeauLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));//nom reference pour la colonne
        //reference pour la colonne
        tablecolonneCadeauDescription.setCellValueFactory(new PropertyValueFactory<>("description"));//reference pour la colonne
        tablecolonneCadeauPrix.setCellValueFactory(new PropertyValueFactory<>("prix_reel"));//reference pour la colonne
        tablecolonneCadeauQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite_actuel"));//reference pour la colonne
        tablecolonneCadeauValeur.setCellValueFactory(new PropertyValueFactory<>("valeur_point"));//reference pour la colonne
        tablecolonneCadeauCategeoris.setCellValueFactory(new PropertyValueFactory<>("categorisCadeaux"));//reference pour la colonne
        listCadeaux = services.afficherlisteCadeaux();

        ObservablelisteCadeaux = FXCollections.observableArrayList(listCadeaux);//convertir la liste des client en observable liste
        tableviewCadeaux.setItems(ObservablelisteCadeaux);

    }

    public void supprimerCadeaux() throws IOException {
        MyServices services = new MyServices();
        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();

        if (cadeaux != null) {

            services.supprimerCadeaux(cadeaux);
            createtableviewCadeaux();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un cadeaux");
            alert.show();
        }

    }

    public void modifierCadeaux() throws IOException {
        MyServices services = new MyServices();
        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();

        if (cadeaux != null) {

            boolean buttonConfimClicked = showModifierCadeauxFXML(cadeaux);//cette qui va ouvrir cette page (dmender cete methode) 
            if (buttonConfimClicked) {

                services.modifierCadeaux(cadeaux);

                createtableviewCadeaux();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un cadeaux");
            alert.show();
        }

    }

    private boolean showAjouterBonCadeauxFXML(Cadeaux cadeaux, Boncadeaux boncadeaux) throws IOException {
        /**
         * ***********************1ere etape*******************************
         */

        FXMLLoader loader = new FXMLLoader();//qui va charger la fichier de type fxml
        loader.setLocation(AffecterCadeauxController.class.getResource("/pidev/bonplan/GUI/AffecterCadeaux.fxml"));//ouvrire ce fenetre
        AnchorPane page = (AnchorPane) loader.load();//d'ou et cree un attribut page qui va enregister mon loder deja telechagere
        //==> mon fichier et deje tawa en memoire
        /**
         * ***********************2eme etape(configuration pour new fenter*******************************
         */

        //TAWA POUR en voie cette page il faut cree un stage
        Stage dialogeStage = new Stage();
        //a partir de ce stage j'ai cree un titre pour le client
        dialogeStage.setTitle("Gestion des cadeaux");

        //craetion de la scene et passe l'attrbut page qui le petite ecran de la l'affichage qui on a telechager
        Scene scene = new Scene(page);

        //charger la scene
        dialogeStage.setScene(scene);
        /**
         * ***********************3eme etape*******************************
         */
//cree un attribut unique et nous avons  utliser cete endroit pour appele le controller de cette (AjouterPersonneFXML)
//on va tawa lil AjouterPersonne2Controller donner lui 2 clics(pour voire les attribut stage,personne,confiramation bbuton)
        AffecterCadeauxController controller = loader.getController();

//tawa on va vers les methode ili 3amlou applele lil methode hethi lekbira
        controller.setStage(dialogeStage);
        controller.setCadeaux(cadeaux);
        controller.setBoncadeaux(boncadeaux);//ce client passer en parametre passer pa le controlleur

        /**
         * ***********************4eme etape enfin montre l'ecran a l'user
         * attend l'insertion des donne*******************************
         */
//je ne encore envoyoer cet ecran il va aparaitre a partir
        dialogeStage.showAndWait();//je veut lui montre et attender que l'user fermer cette petite ecran plus tard

        /**
         * **********************5eme etape attendre l'user fermer le fentre***************************
         */
//on va modifier setClient pour //on va modifier setClient pour  
        return controller.isButtonConfimClicked();
    }

    private boolean showModifierCadeauxFXML(Cadeaux cadeaux) throws IOException {
        /**
         * ***********************1ere etape*******************************
         */

        FXMLLoader loader = new FXMLLoader();//qui va charger la fichier de type fxml
        loader.setLocation(ModifierCadeauxController.class.getResource("/pidev/bonplan/GUI/ModifierCadeaux.fxml"));//ouvrire ce fenetre
        AnchorPane page = (AnchorPane) loader.load();//d'ou et cree un attribut page qui va enregister mon loder deja telechagere
        //==> mon fichier et deje tawa en memoire
        /**
         * ***********************2eme etape(configuration pour new fenter*******************************
         */

        //TAWA POUR en voie cette page il faut cree un stage
        Stage dialogeStage = new Stage();
        //a partir de ce stage j'ai cree un titre pour le client
        dialogeStage.setTitle("Modifer cadeaux");

        //craetion de la scene et passe l'attrbut page qui le petite ecran de la l'affichage qui on a telechager
        Scene scene = new Scene(page);

        //charger la scene
        dialogeStage.setScene(scene);
        /**
         * ***********************3eme etape*******************************
         */
//cree un attribut unique et nous avons  utliser cete endroit pour appele le controller de cette (AjouterPersonneFXML)
//on va tawa lil AjouterPersonne2Controller donner lui 2 clics(pour voire les attribut stage,personne,confiramation bbuton)
        ModifierCadeauxController controller = loader.getController();

//tawa on va vers les methode ili 3amlou applele lil methode hethi lekbira
        controller.setStage(dialogeStage);
        controller.setCadeaux(cadeaux);//ce client passer en parametre passer pa le controlleur

        /**
         * ***********************4eme etape enfin montre l'ecran a l'user
         * attend l'insertion des donne*******************************
         */
//je ne encore envoyoer cet ecran il va aparaitre a partir
        dialogeStage.showAndWait();//je veut lui montre et attender que l'user fermer cette petite ecran plus tard

        /**
         * **********************5eme etape attendre l'user fermer le fentre***************************
         */
//on va modifier setClient pour //on va modifier setClient pour  
        return controller.isButtonConfimClicked();
    }
}
