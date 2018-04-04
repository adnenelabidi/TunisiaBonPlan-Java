/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.*;//c a d regular expression

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
 
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import pidev.bonplan.entites.Badge;
import pidev.bonplan.entites.Cadeaux;
import pidev.bonplan.entites.Compte;
import pidev.bonplan.entites.Mailing;
import pidev.bonplan.entites.User;
import pidev.bonplan.services.MyServices;
import pidev.bonplan.services.ServiceNotification;
import pidev.bonplan.services.ServiceRandomMailConfirmation;
import pidev.bonplan.services.serviceCryptage;

/**
 * FXML Controller class
 *
 * @author Abdelkader
 */
public class RegistrationController implements Initializable {

    //les composants
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField ccnfirmation_email;
    @FXML
    private JFXComboBox<String> genre;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField Confirmation_password;

    @FXML
    private JFXComboBox<String> roles;
   
    @FXML
    private Label inscrirLabel;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton signup;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXTextField nom;
   
    @FXML
    private JFXDatePicker date_naissance;
    @FXML
    private JFXButton annulerBtn;

    @FXML
    private Label labelusername;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelconfEmail;
    @FXML
    private Label labelpassword;
    @FXML
    private Label labelConfirmationMdp;
    @FXML
    private JFXDatePicker date_inscrit;
    @FXML
    private Label labelcontainsDigit;
    @FXML
    private Label labelcontainsLowerCaseLetter;
    @FXML
    private Label labelpasswordcontainsUpperCaseLetter;
    @FXML
    private Label labelpasswordcontainsSpecialCharacter;

    @FXML
    private Label labelpasswordlength;
    @FXML
    private Label labelPhone;
    String path;
    File selectedFile;

    //liste des roles qui ont va le remplir dans ini
    List<String> listeroles = new ArrayList<String>();
    ObservableList<String> observableListroles;

    //liste des genres qui ont va le remplir dans ini
    List<String> listegenre = new ArrayList<String>();
    ObservableList<String> observableListgenre;

    //les verification qui va faire pour confirmer la registration
    private boolean verificationUserName;
    private boolean verificationUserEmail;
    private boolean verificationUserConfirmEmail;
    private boolean verificationUserPhone;
    private boolean verificationUserpasword;
    private boolean verificationUserConfirmpasword;
    private boolean verificationImage;

    private boolean verificationUserNom;
    private boolean verificationUserPrenom;
    private boolean verificationUserSexe;
    private boolean verificationUserRole;
    private boolean verificationUserDateNaissance;
    private boolean verificationUserDateInscrit;

    //les verfication de la mot de passe
    boolean containsDigit = false;
    boolean containsLowerCaseLetter = false;
    boolean containsUpperCaseLetter = false;
    boolean containsSpecialCharacter = false;
    boolean length = false;
    

    private MyServices myServices = new MyServices();
    ServiceRandomMailConfirmation serviceMail = new ServiceRandomMailConfirmation();
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField phone;
    @FXML
    private ImageView ImporterImage;
    @FXML
    private JFXButton ImporterImagePath;
    @FXML
    private Label labelImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        listegenre.add("male");
        listegenre.add("femelle");
        observableListgenre = FXCollections.observableList(listegenre);//convertir la liste des genre
        genre.setItems(observableListgenre);

        listeroles.add("Admin ");
        listeroles.add("Partenaire");
        listeroles.add("Membre");
        observableListroles = FXCollections.observableList(listeroles);//convertir la liste des genre
        roles.setItems(observableListroles);

    }

    public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'application");
        alert.setHeaderText("Vous allez quitter l'application");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            alert.close();
        }
    }

    @FXML
    private void verifusername(KeyEvent event) {
        if (myServices.chercherUtilisateurBylogin(username.getText()) == true) {
            labelusername.setText("Username existe déja");
            verificationUserName = false;
        }
        if (myServices.chercherUtilisateurBylogin(username.getText()) == false) {
            labelusername.setText("Username n'existe pas");
            verificationUserName = true;
        }

    }

    @FXML
    private void verifEmail(KeyEvent event) {

        if (myServices.chercherUtilisateurByEmail(email.getText()) == true) {
            labelEmail.setText("Email Existe déja");
            verificationUserEmail = false;
        }
        if (myServices.chercherUtilisateurByEmail(email.getText()) == false) {//alphanumerique@alphanumerique.com
            //{ici longeur  }
            //debut ^
            //fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelEmail.setVisible(false);
                labelEmail.setText("Email valide !");
                verificationUserEmail = true;

            } else {
                labelEmail.setVisible(true);
                labelEmail.setText("Email Format invalide !");
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }

    }

    @FXML
    private void ConfirmEmail(KeyEvent event) {

        if (email.getText().equals(ccnfirmation_email.getText())) {
            labelconfEmail.setText("les deux email sont confondu");
            verificationUserConfirmEmail = true;
        } else {
            labelconfEmail.setText("Verifier votre email");
            verificationUserConfirmEmail = false;
        }

    }

    @FXML
    private void typeuser(MouseEvent event) {
    }

    @FXML
    private void controlMdp(KeyEvent event) {

        String PAS = password.getText().trim();

        if (PAS.length() >= 6) {// Check for Digits in password
//•	Contains at least 1 numeric digit
            labelpasswordlength.setText("longeur just");
            verificationUserConfirmpasword = true;

            for (int i = 0; i < PAS.length(); i++) {
                char ch = PAS.charAt(i);

                if (Character.isDigit(ch)) {// Check for Digits in password
//•	Contains at least 1 numeric digit
                    labelcontainsDigit.setText("Contient un nombre");
                    containsDigit = true;
                }

                if (Character.isLetter(ch) && Character.isLowerCase(ch)) {// Check for Letters in password
//•	Contains at least 1 lower letter character
                    labelcontainsLowerCaseLetter.setText("Contient une lettre minus");
                    containsLowerCaseLetter = true;

                }

                if (Character.isLetter(ch) && Character.isUpperCase(ch)) {// Check for Letters in password
//•	Contains at least 1 upper letter character
                    labelpasswordcontainsUpperCaseLetter.setText("Contient une lettre majus");
                    containsUpperCaseLetter = true;

                }
                if (ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*') {
//•	Contains at least 1 special character from the set: !@#$%^&*
                    labelpasswordcontainsSpecialCharacter.setText("Contient un lettre sepcial");
                    containsSpecialCharacter = true;

                }
                System.out.println(containsUpperCaseLetter + "containsUpperCaseLetter\n" + containsLowerCaseLetter + "containsLowerCaseLetter\n"
                        + containsDigit + "containsDigit\n" + containsSpecialCharacter + "containsSpecialCharacter\n\n\n");

                if (containsUpperCaseLetter && containsLowerCaseLetter && containsDigit && containsSpecialCharacter) {
                    labelpassword.setText("Mot de passe valide!");

                    verificationUserpasword = true;
                }

            }
        } else {
            labelpasswordlength.setText("Utilisez au moins huit caractères\n"
                    + " avec des lettres, des chiffres et des symboles");
            labelpassword.setText("Mot de passe  invalide!");
            length = false;
            verificationUserpasword = false;
            labelpasswordcontainsSpecialCharacter.setText("");
            containsSpecialCharacter = false;
            labelpasswordcontainsUpperCaseLetter.setText("");
            containsUpperCaseLetter = false;
            labelcontainsLowerCaseLetter.setText("");
            containsLowerCaseLetter = false;
            labelcontainsDigit.setText("");
            containsDigit = false;
        }

    }

    @FXML
    private void ConfirmMDP(KeyEvent event) {
        if (password.getText().equals(Confirmation_password.getText())) {
            labelConfirmationMdp.setText("Mot de passe Conforme!");
            verificationUserConfirmpasword = true;
        } else {
            labelConfirmationMdp.setText("Verifier votre\n"
                    + " mot de passe");
            verificationUserConfirmpasword = false;
        }

    }
@FXML
    private void controphone(KeyEvent event) {
        if (phone.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < phone.getText().trim().length(); i++) {
                char ch = phone.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                labelPhone.setText("number valide");
                verificationUserPhone = true;
            } else {
                labelPhone.setText("invalide number \n"
                        + " Il exist des char");
                verificationUserPhone = false;

            }

        } else {
            labelPhone.setText("Il faut 8 chiffres");
            verificationUserPhone = false;
        }
    }


    @FXML
    private void Pagelogin(ActionEvent event) {

        labelusername.getScene().getWindow().hide();
        loadWindow(getClass().getResource("/pidev/bonplan/GUI/Login.fxml"), "Dashboard", null);

    }
//verification 3
    public boolean verifconfirMail(String code) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Confirmez votre inscription");
        dialog.setHeaderText("Un mail vous a été envoyer où vous trouvez le code");
        dialog.setContentText("Entrez votre code de confirmation:");
        Optional<String> result = dialog.showAndWait();
        if (result.get().equals(code)) {

            if (result.get().equals(code)) {
                return true;
            }
        } else {
            return verifconfirMail(code);
        }
        return false;
    }

    
    
    
    //verification 1
    private boolean Signup() {

        if (nom.getText().trim() != "") {
            
            verificationUserNom = true;
        } else {
            verificationUserNom = false;
        }

        if (prenom.getText().trim() != "") {
            verificationUserPrenom = true;
        } else {
            verificationUserPrenom = false;
        }

        if (genre.getValue() != null) {
            verificationUserSexe = true;
        } else {
            verificationUserSexe = false;
        }

        if (roles.getValue() != null) {
            verificationUserRole = true;
        } else {
            verificationUserRole = false;
        }
        if (date_inscrit.getValue() != null) {
            verificationUserDateInscrit = true;
        } else {
            verificationUserDateInscrit = false;
        }
        if (date_naissance.getValue() != null) {
            verificationUserDateNaissance = true;
        } else {
            verificationUserDateNaissance = false;
        }

        if (verificationUserName && verificationUserEmail && verificationUserConfirmEmail && verificationUserPhone
                && verificationUserpasword && verificationUserConfirmpasword && verificationUserNom
                && verificationUserPrenom && verificationUserSexe && verificationUserRole
                && verificationUserDateNaissance && verificationUserDateInscrit) {

            return true;
        } else {

            return false;
        }

    }
 //verification 2===>appelle a  //verification 3
    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
        User u = new User();
        if (Signup()) {

            String code = serviceMail.generateRandomString();
            System.out.println(code);

             String to = email.getText().trim();
            String subject = "Confirmation d'inscription";
          String message = "Bienvenu " + prenom.getText() + " " + nom.getText() + " dans notre application voici votre code de confirmation " + code + "  Veillez saisir votre code pour confirmer votre inscription";
            String usermail = "abdelkader.jouini@esprit.tn";
         String passmail = "SALIHsonia22";
           Mailing.send(to, subject, message, usermail, passmail);

            if (verifconfirMail(code) == true) {
                serviceCryptage cryptage = new serviceCryptage();
                String passCrypt = cryptage.cryptWithMD5(password.getText());

                u.setUsername(username.getText());
                u.setUsername_canonical(username.getText());
                u.setEmail(email.getText());
                u.setEmail_canonical(ccnfirmation_email.getText());
                u.setEnabled(1);
                u.setPassword(passCrypt);
                
                
                
                if ((String.valueOf(roles.getValue())).equals("Membre")) {
                       u.setRoles("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}");
                } else if ((String.valueOf(roles.getValue())).equals("Admin")) {
                    u.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
                }
                else
                {
                u.setRoles("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}");
                }
   
                 
             
                u.setDate_inscription(date_inscrit.getValue().toString());
                u.setDate_naissance(date_naissance.getValue().toString());

                u.setNom(nom.getText());
                u.setPrenom(prenom.getText());
                u.setPhone(phone.getText());
               
                //user.setDate_naissance(date_naissance.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));

                // user.setDate_inscription(date_inscrit.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));
                u.setGenre(genre.getValue());
                u.setImage(path);
                
                if (selectedFile != null)
            {
                try {
                    //System.out.println(selectedFile.toString());
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\images");

                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                
                myServices.ajouterUtilisateurs(u);
                
                User user=new User();
                user.setId(1);
                        
                       user= myServices.chercherUtilisateurByUsername(u.getUsername());
                //Badge badge=new Badge();
                //badge.setIdBadge(1);
                //Cadeaux cadeaux=new Cadeaux();
                //cadeaux.setId(82);
                
                //badge=myServices.chercherBadgeById(badge.getIdBadge());
                 //cadeaux=myServices.chercherCadeauxByLibelle( cadeaux.getLibelle());
               
            
                // System.out.println(badge.getLevel()+"===>"+cadeaux.getLibelle());
               // Compte compte=new Compte(0, badge, cadeaux, user, 0);
                System.out.println(user.toString());
                Compte compte=new Compte();
                compte.setId_user(user);
                compte.setPoint_merci(0);
                myServices.ajouterCompteUtilisateurs(compte);
                System.out.println("sssssssss");

                labelusername.getScene().getWindow().hide();

                loadWindow(getClass().getResource("/pidev/bonplan/GUI/Login.fxml"), "Login", null);
                ServiceNotification.showNotif("Bienvenu", "Bienvenu dans Bon plan Vous Pouvez Inscrir maintenant");

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir tous les champs");
            alert.show();

        }

    }
    
    
    
    
    @FXML
    private void Pageregister(ActionEvent event) {

        labelusername.getScene().getWindow().hide();
        loadWindow(getClass().getResource("/pidev/bonplan/GUI/Registration.fxml"), "Registration", null);
        
        

    }


    @FXML
    private void handleButtonCancelar(ActionEvent event) {

        labelusername.getScene().getWindow().hide();
        loadWindow(getClass().getResource("/pidev/bonplan/GUI/Login.fxml"), "Registration", null);

    }
    
    
    
    
    
    @FXML
     private void image(ActionEvent event) throws MalformedURLException 
    {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));//importation de l'image ou
        fc.setTitle("Veuillez choisir l'image"); //titre de la
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
          Image image = new Image(selectedFile.toURI().toString());
           ImporterImage.setImage(image) ;
            ImporterImagePath.setText(path);
            labelImage.setText("Cliquez sur le button pour la changer!");
              if(path.equals(""))
            {
            verificationImage=false;
            }
            else 
                  
              { verificationImage=true;}  
               
//             ImporterImage.

        }

    }


}
