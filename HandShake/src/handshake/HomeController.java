

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
    @FXML
    private Circle profil;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn11;
    @FXML
    private Label logout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             // TODO
             mapView.addMapInializedListener((this));
             ServiceUser sa = new ServiceUser();
             int us = UserSession.getInstance().getId();
             String login = UserSession.getInstance().getLogin();
             User a = sa.chercherUser(us);
             Image I=null;  
             chargerimagecircle(I,profil,a.getProfil());
         } catch (SQLException ex) {
             Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

  
       void chargerimagecircle(Image I, Circle c, String x) throws MalformedURLException, IOException {
        URL urlp;
        urlp = new URL(x);
        URLConnection connection = urlp.openConnection();
        InputStream inputStream = connection.getInputStream();
        c.setStroke(Color.GOLDENROD);
        I = new Image(inputStream);
        c.setFill(new ImagePattern(I));
        c.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }
    public void mapInitialized() {
         LatLong joeSmithLocation = new LatLong(33.918534, 8.122933);
        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
        
        
        //Set the initial properties of the map.
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

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);
        
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);
        
        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(bobUnderwoodLocation);
        
        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(tomChoiceLocation);
        
        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(fredWilkieLocation);
        
        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
        Marker bobUnderwoodMarker = new Marker(markerOptions3);
        Marker tomChoiceMarker= new Marker(markerOptions4);
        Marker fredWilkieMarker = new Marker(markerOptions5);
        
        map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
        map.addMarker( bobUnderwoodMarker );
        map.addMarker( tomChoiceMarker );
        map.addMarker( fredWilkieMarker );
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);
    }   
    public void allRefugeonMap()
    {
        ServiceRefuge ser=new ServiceRefuge() ;
        Refuge r=new Refuge();
        
        
        
        
        
    LatLong joeSmithLocation = new LatLong(33.918534, 8.122933);
    
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
        loadStage("ShakeHub.fxml");
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

    @FXML
    private void Monprofil(MouseEvent event) {
        loadStage("profilUser.fxml");
    }
}

