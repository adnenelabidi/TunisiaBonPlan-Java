/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;
import pidev.bonplan.entites.notif_demande;
import pidev.bonplan.entites.offre_experience;
import pidev.bonplan.services.PartageExperience;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class PartageExperienceController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;
    @FXML
    private TableView<offre_experience> lview;
    PartageExperience pexp=new PartageExperience();
    @FXML
    private AnchorPane listpane;
    @FXML
    private JFXButton addexp;
    @FXML
    private TableColumn<offre_experience, String> imgcell;
    @FXML
    private TableColumn<offre_experience, String> nomcell;
    @FXML
    private TableColumn<offre_experience, String> desccell;
    @FXML
    private TableColumn<offre_experience, String> addcell;
    @FXML
    private TableColumn<String, Integer> regcell;
    @FXML
    private TableColumn<String, Integer> ratcell;
    @FXML
    private FontAwesomeIcon notif;
    @FXML
    private TableColumn<?, ?> detailcell;
    

     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        List<offre_experience> listo=pexp.listerPartageExp();
       ObservableList<offre_experience> list= FXCollections.observableArrayList();
      //  lview.setItems(list);
      for (offre_experience o:listo)
      { list.add(o);
      nomcell.setCellValueFactory(new PropertyValueFactory<>("nom"));
      desccell.setCellValueFactory(new PropertyValueFactory<>("description"));
      addcell.setCellValueFactory(new PropertyValueFactory<>("addrese"));
      imgcell.setCellValueFactory(new PropertyValueFactory<>("url_image"));
      imgcell.setCellFactory(tc -> {
       TableCell<offre_experience, String> cell = new TableCell<offre_experience, String>() {
             @Override
             protected void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item == null || empty) {
                        setText(null);
                  } else {
                        ImageView imageview = new ImageView();
                        imageview.setImage(new Image( "file:C:\\xampp\\htdocs\\PhpstormProjects\\Pidev3\\web\\uploads\\images\\"+ item));
                        imageview.setFitWidth(300);
                        imageview.setFitHeight(150);
                        imageview.setPreserveRatio(true);
                        StackPane stackPane = new StackPane(imageview);
                        setGraphic(stackPane);
                  }
             }
       };
       return cell;
});
      ratcell.setCellValueFactory(new PropertyValueFactory<>("rating"));
      ratcell.setCellFactory(param -> new TableCell<String, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Rating rtt=new Rating(item);
                    rtt.setOrientation(Orientation.VERTICAL);
                   
                  //  setText(pexp.GetRegion(item));
                    setGraphic(rtt);
                    
                     
                }
            }
        }); 
      regcell.setCellValueFactory(new PropertyValueFactory<>("region_id"));
      regcell.setCellFactory(param -> new TableCell<String, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    System.out.println(item);
                    setText(pexp.GetRegion(item));
                    
                    
                     
                }
            }
        });
 
      
      }  
      lview.setItems(list);
      lview.setRowFactory( tv -> {
    TableRow<offre_experience> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            offre_experience rowData = row.getItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/bonplan/GUI/DetailExperience.fxml"));
            Parent root;
            String nomPE=rowData.getNom().toString();
            try {
                root = loader.load();
                DetailExperienceController detail=loader.getController();
                             
                 detail.setNomPExp(nomPE);
                 detail.initData(rowData);
                 DetailExperienceController.id=rowData.getId();
                        lview.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    return row ;
});
    }

    @FXML
    public void BackHomes(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/bonplan/GUI/UserInterface.fxml"));
        Parent root = loader.load();
        vbox.getScene().setRoot(root);
    }

    @FXML
    public void closeApp(ActionEvent event) {

        // get a handle to the stage
        Stage stage = (Stage) btnclose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML
    public void showAddPartageExp() throws IOException
    {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/bonplan/GUI/AjoutPartageExperience.fxml"));
        Parent root = loader.load();
        listpane.getScene().setRoot(root);
    }
    @FXML
    public void getnotif()
    {
        PopOver ntf=new PopOver();
        
        ntf.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        PartageExperience pexp=new PartageExperience();
        List<notif_demande> ntflist=pexp.GetNotificationById(5);
        List<String> ll=new ArrayList<>();
        for(notif_demande n:ntflist)
        {
            ll.add(n.getMessage());
        }
        ListView<String> l=new ListView<String>(); 
        ObservableList<String> items =FXCollections.observableArrayList(ll);
        l.setItems(items);
        l.setPrefSize(400, 200);
        //Label lb=new Label("tesst");
     ntf.setContentNode(l);
     ntf.setDetachable(false);
   //  ntf.setHeight(200);
        ntf.show(notif);
    }
    
}
