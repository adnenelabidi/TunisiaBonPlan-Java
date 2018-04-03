/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.test;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidev.bonplan.entites.BonPlan;
import pidev.bonplan.entites.Cadeaux;
import pidev.bonplan.entites.Categoris;
import pidev.bonplan.entites.Personne;
import pidev.bonplan.entites.User;
 
import pidev.bonplan.services.serviceCryptage;
//mail import

/**
 *
 * @author Abdelkader
 */
public class MenuAdmin1 extends Application {

    
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/UserInterface.fxml"));
        
        Scene scene = new Scene(root);
        
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML.fxml")); 
        //FXMLController controller = fxmlLoader.<FXMLController>getController();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           
        launch(args);

    }

}
