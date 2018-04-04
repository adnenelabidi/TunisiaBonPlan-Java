/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.bonplan.entites.User;
import pidev.bonplan.services.MyServices;

/**
 * FXML Controller class
 *
 * @author Abdelkader
 */
public class LoginController implements Initializable {

    @FXML
    private Label inscrirLabel;
    @FXML
    private JFXButton btnlogin;
    @FXML
    private JFXTextField labelusername;
    @FXML
    private JFXPasswordField labelpassword;
    @FXML
    private Hyperlink labelmdo;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton signup;
    
    /*****************Bech ne5thou ili connecter****************************/
   private User loggedUser;
       private static LoginController instance;
 public static final Map<Integer, User> USERS = new HashMap<>();
    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }
    
    
      public User getLoggedUser() {
        return loggedUser;
    }
      
      
  
          private void gotoLogin() {
        try {
             loadWindow(getClass().getResource("/pidev/bonplan/GUI/Login.fxml"), "Dashboard", null);
 
  
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
          
          
              public void gotoProfile() {
        try {   loadWindow(getClass().getResource("/pidev/bonplan/GUI/profile.fxml"), "Dashboard", null);
          
          
        } catch (Exception ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
              
              
              
              

                
                
                 public void userLogout(){
        loggedUser = null;
        gotoLogin();
    }
 
    
    /**************************************************************************/
    public JFXTextField getLabelusername() {
        return labelusername;
    }

    public void setLabelusername(String labelusername) {
        this.labelusername.setText(labelusername);
    }

    public JFXPasswordField getLabelpassword() {
        return labelpassword;
    }

    public void setLabelpassword(String labelpassword) {
        this.labelpassword.setText(labelpassword);
    }

    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private boolean handleLoginButtonAction(ActionEvent event) {
        
        MyServices myServices=new MyServices();
        String mdp=labelpassword.getText();
        String username=labelusername.getText();
        
 
         String errorMessage = "";

        if (username == null || username.length() == 0) {
            errorMessage += "Username invalide \n";
        }

        if (mdp  == null || mdp.length() == 0) {
            errorMessage += "Mot de passe invalide \n";
        }

        if (errorMessage.length() != 0) {

       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error valeur");
            alert.setHeaderText("Invalide input");
            alert.setContentText(errorMessage);
            alert.show();
        } else {

           
           
      
        
       
        Boolean pas=myServices.verifierpassword(mdp, username);
          
       if (myServices.chercherUtilisateurBylogin(username) && pas==true/*BCrypt.checkpw(pword, user.getPassword())*/) {
           
          if (myServices.Gettype(username).equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
             
                System.out.println("hello Admin");
                
                /*************Pour Recupere ili connecter********************/
                
                User userConnecter=myServices.chercherUtilisateurByUsername(username);
              loggedUser = User.of(userConnecter.getId());
              
              
             
                loadWindow(getClass().getResource("/pidev/bonplan/GUI/AdminMenu.fxml"), "Dashboard", null);
                
                labelusername.getScene().getWindow().hide();
                    Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Vous étes connecté en tant que Administrateur!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
           
           
            }
          
          
          if (myServices.Gettype(username).equals("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}")) {
             
                System.out.println("hello Membre");
                  User userConnecter=myServices.chercherUtilisateurByUsername(username);
              loggedUser = User.of(userConnecter.getId());
                loadWindow(getClass().getResource("/pidev/bonplan/GUI/AdminMenu.fxml"), "Dashboard", null);
                labelusername.getScene().getWindow().hide();
                Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Vous étes connecté en tant que Membre!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
                n.showInformation();
           
           
           
            }
          
          
           if (myServices.Gettype(username).equals("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}")) {
             
                System.out.println("hello Partenaire");
                  User userConnecter=myServices.chercherUtilisateurByUsername(username);
              loggedUser = User.of(userConnecter.getId());
                loadWindow(getClass().getResource("/pidev/bonplan/GUI/AdminMenu.fxml"), "Dashboard", null);
                labelusername.getScene().getWindow().hide();
           Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Vous étes connecté en tant que Partenaire!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
                n.showInformation();
           
           
            }
       
      return true;
       }else
       {
       
              Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Username ou mot de passe invalide!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
                n.showInformation();
           
         return false;
       
       }
         }
         return true;
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
    private void Pagelogin(ActionEvent event) {
        
        
         labelusername.getScene().getWindow().hide();
          loadWindow(getClass().getResource("/pidev/bonplan/GUI/Login.fxml"), "Dashboard", null);
    }

    @FXML
    private void Signup(MouseEvent event) {
    }

    @FXML
    private void Pageregister(ActionEvent event) {
        
          labelusername.getScene().getWindow().hide();
          loadWindow(getClass().getResource("/pidev/bonplan/GUI/Registration.fxml"), "Registration", null);
    }

    @FXML
    private void Pagerecupmdp(ActionEvent event) {
         labelusername.getScene().getWindow().hide();
            loadWindow(getClass().getResource("/pidev/bonplan/GUI/Resetmdp.fxml"), "Resetmdp", null);
        
    }
    
        
        
   

    
    
}
