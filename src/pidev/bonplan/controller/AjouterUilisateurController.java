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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.parser.TokenType;
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
public class AjouterUilisateurController implements Initializable {

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
    private ImageView view1;
    @FXML
    private Label inscrirLabel;
  
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
 
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField phone;
    
    
     private Stage stage;//controle de classe(annuler ou fermer) stage(serie)
    private boolean buttonConfimClicked = false;//nous informer est ce que on clicker sur confimer ou annuler
    private User user;
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {

        this.user = user;

        //je suis interesser quand j'ai un new client
        //accepter les champs technique avec cette champs
        this.username.setText(user.getUsername());
        this.email.setText(user.getEmail());
        this.ccnfirmation_email.setText(user.getEmail_canonical());
        this.Confirmation_password.setText(user.getPassword());
        this.password.setText(user.getPassword());
        
     
        //this.password.setText(user.getPassword());

        listegenre.add(user.getGenre());
        observableListgenre = FXCollections.observableList(listegenre);//convertir la liste des genre
        genre.setItems(observableListgenre);

        listeroles.add(user.getRoles());
        observableListroles = FXCollections.observableList(listeroles);//convertir la liste des genre
        this.roles.setItems(observableListroles);

        this.nom.setText(user.getNom());
   genre.setValue(user.getGenre());
    roles.setValue(user.getRoles());
        this.prenom.setText(user.getPrenom());

        this.phone.setText(user.getPhone());


        // this.date_inscrit.setValue(String.valueOf()  );
        // this.date_naissance.setValue(user.getDate_naissance());
        //  this.date.setText(String.valueOf(personne.getDate_inscrit().format(DateTimeFormatter.ofPattern(pattern), args)));
//tawan bech nifhmou 3lech 3malna les 2 phrases ili just fou9i
//imchi ListeUtilisateurFXMLController tawa tifhim
    }

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

        //listeroles.add("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
       // listeroles.add("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}");
        listeroles.add("MEMBRE");
        listeroles.add("ADMIN");
        listeroles.add("PROPRIETAIRE");
        
        
        
        
        
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
            labelusername.setText("Nom d'utilisateur existe déja");
            verificationUserName = false;
        }
        if (myServices.chercherUtilisateurBylogin(username.getText()) == false) {
            labelusername.setText("Nom d'utilisateur n'existe pas");
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
            labelpasswordlength.setText("Il faut 6 caractere");
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
            labelConfirmationMdp.setText("Mot de passe vide!");
            verificationUserConfirmpasword = true;
        } else {
            labelConfirmationMdp.setText("Verifier votre mot de passe");
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
            labelPhone.setText("8 chiffres");
            verificationUserPhone = false;
        }
    }
    
    
    

    @FXML
    private void ChargeImage(MouseEvent event) {
    }

    
//verification 3
    public boolean verifconfirMail() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Confirmez l'ajout de l'utilisateur");
        dialog.setHeaderText("Un mail a été envoyer au l'utlisateur avec l'username et mot de passe");
        dialog.setContentText("Entre oui pour la confirmation:");
        Optional<String> result = dialog.showAndWait();
        if (result.get().equals("oui")) {

            if (result.get().equals("oui")) {
                return true;
            }
        } else {
            return verifconfirMail();
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
 
        if (Signup()) {

            
         

            String to = "abdelkader.jouini@esprit.tn";
            String subject = "Confirmation d'inscription";
            String message = "Bienvenu " + prenom.getText() + " " + nom.getText() + " dans notre application voici votre mot de passe de confirmation " + password.getText() + " et votre Username ==>"+username.getText();
            String usermail = "abdelkader.jouini@esprit.tn";
            String passmail = "SALIHsonia22";
            Mailing.send(to, subject, message, usermail, passmail);

            if (verifconfirMail() == true) {
                serviceCryptage cryptage = new serviceCryptage();
                String passCrypt = cryptage.cryptWithMD5(password.getText());

                user.setUsername(username.getText());
                user.setUsername_canonical(username.getText());
                user.setEmail(email.getText());
                user.setEmail_canonical(ccnfirmation_email.getText());
               user.setEnabled(1);
                user.setPassword(passCrypt);
                
                
                
                String role=((String.valueOf(roles.getValue())));
                
                if (role.equals("ADMIN")) {
                    user.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
                } else if (role.equals("PROPRIETAIRE")) {
                      user.setRoles("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}");
                    
                }
                else
                {
                 user.setRoles("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}");
                }
                
                
                
                
 
                
                
                
                
                user.setDate_inscription(date_inscrit.getValue().toString());
                user.setDate_naissance(date_naissance.getValue().toString());

               user.setNom(nom.getText());
                user.setPrenom(prenom.getText());
                user.setPhone(phone.getText());
               
                //user.setDate_naissance(date_naissance.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));

                // user.setDate_inscription(date_inscrit.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));
                user.setGenre(genre.getValue());
                
                
                buttonConfimClicked = true;
 
            stage.close();//boite de dialogue
               

            //    loadWindow(getClass().getResource("/pidev/bonplan/GUI/Login.fxml"), "Login", null);
               
//
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
    private void handleButtonCancelar(ActionEvent event) {

      stage.close();

    }

    
}
