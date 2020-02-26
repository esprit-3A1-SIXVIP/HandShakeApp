
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Refuge;
import Entities.User;
import Services.ServiceRefuge;
import Services.ServiceUser;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author steph
 */
public class HomeController  implements Initializable, MapComponentInitializedListener {

    /**
     * Initializes the controller class.
     */
     ObservableList<Refuge> listU = FXCollections.observableArrayList();
    @FXML
    private Hyperlink fairedon;

    @FXML
    private ImageView usericon;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Circle cercledon;
    @FXML
    private JFXButton btn;
     @FXML
    private Hyperlink appel_aide;
    @FXML
    private ImageView shakehub;
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    @FXML
    private Circle cercledon1;
    private Circle profil;
    @FXML
    private JFXButton btn11;
    @FXML
    private Label logout;
    @FXML
    private JFXButton btn2;
     List<Refuge> arr = new ArrayList<>();
    @FXML
    private JFXButton btnArticle;
    @FXML
    private JFXButton btndons;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
             // TODO
             mapView.addMapInializedListener((this));
             ServiceUser sa = new ServiceUser();
             int us = UserSession.getInstance().getId();
             String login = UserSession.getInstance().getLogin();
         
    }
    public void mapInitialized() {
        try {
            ServiceRefuge ser=new ServiceRefuge() ;
            
            Refuge r=new Refuge();
            
            arr=ser.gps();
          
            MapOptions mapOptions = new MapOptions();
            
            mapOptions.center(new LatLong(33.918534, 8.122933))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(12);
            
            map = mapView.createMap(mapOptions);
              for(Refuge x: arr )
        {
            User p=ser.infomarker(x.getDonId());
         LatLong all = new LatLong(x.getLatitude(),x.getLongitude());
           MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(all);
         Marker allMarker = new Marker(markerOptions1);
        map.addMarker( allMarker );
         InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>'"+p.getEmail()+"'</h2>"
                                   +"<h2>'"+p.getTelephone()+"'</h2>"
                                 +"<h2>'"+x.getDate_debut()+"'</h2>"
                                +"<h2>'"+x.getDate_fin()+"'</h2><br>"
                                );
           InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, allMarker);
        }
            

        } catch (SQLException ex) {
            Logger.getLogger(MapsController.class.getName()).log(Level.SEVERE, null, ex);
        }
      


    }   
   
    @FXML
    private void handleButtonFairedon(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == fairedon) {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Type Don");
            alert.setHeaderText("Quel type de Don souhaiter vous faire ");
            alert.setContentText("Choisir votre option.");

            ButtonType buttonTypeOne = new ButtonType("Don en Especes");
            ButtonType buttonTypeTwo = new ButtonType("Don en Nature");
            ButtonType buttonTypeThree = new ButtonType("Don Refuge");
            
           
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,buttonTypeThree);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                loadStage("FormulaireDonEspece.fxml");
                
            } else if (result.get() == buttonTypeTwo) {
                
                loadStage("FormulaireDonNature.fxml");
            }
            else if(result.get()==buttonTypeThree)
            {
            loadStage("FormRefuge.fxml");
            }

        }
    }
    
    @FXML
    private void handleImageUser() {
        loadStage("User.fxml");
    }
    
    @FXML
    private void handleShakeHub() {
        if (UserSession.getU().isAccesShakeHub()==1 ){
           
        loadStage("ShakeHub.fxml");
        }
        else {              System.out.println(UserSession.getU().isAccesShakeHub());
                            Alert A = new Alert(Alert.AlertType.ERROR);
                            A.setContentText("Vous n'avez plus accès au Shakehub suite à votre transgression des règles de celui-ci.");
                            A.showAndWait();
        }
    }
    private void loadStage(String fxml) {
        try {
             AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));
                
               rootPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void GoToEvenement(ActionEvent event) {
        
        try {
                    FXMLLoader top=new FXMLLoader(getClass().getResource("evenement.fxml"));

            Parent root =top.load();
                    EvenementController dpc=top.getController();
                    
                    
                   btn.getScene().setRoot(root);

        } catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
    }
   @FXML
    private void interbenif(ActionEvent event) {
        if (event.getSource() == appel_aide) {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Aide ou Beneficiaire");
            alert.setHeaderText("Que voulez vous? ");
            alert.setContentText("Choisir votre option.");

            ButtonType buttonTypeOne = new ButtonType("Aide");
            ButtonType buttonTypeTwo = new ButtonType("Beneficaire");
           
            
           
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                loadStage("AjouterAide.fxml");
                
            } else if (result.get() == buttonTypeTwo) {
                
                loadStage("AjouterBeneficiaire.fxml");
            }

        }
        
    }


    private void Monprofil(MouseEvent event) {
        loadStage("profilUser.fxml");
    }

    @FXML
    private void articles(ActionEvent event) {
        loadStage("article.fxml");
    }
    @FXML
    private void logout(MouseEvent event) {
       UserSession.getInstance().cleanUserSession();
        loadStage("login2.fxml");
    }
}
