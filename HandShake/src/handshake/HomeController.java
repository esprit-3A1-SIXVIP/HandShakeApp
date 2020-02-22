/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Services.ServiceUser;
import Utils.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steph
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Hyperlink fairedon;

    @FXML
    private ImageView usericon;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView shakehub;
    @FXML
    private Circle cercledon;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
           
            
           
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                loadStage("FormulaireDonEspece.fxml");
                
            } else if (result.get() == buttonTypeTwo) {
                
                loadStage("FormulaireDonNature.fxml");
            }

        }
    }
    
    @FXML
    private void handleImageUser() {
        loadStage("User.fxml");
    }
    @FXML
    private void handleShakeHub() {
        loadStage("AfficheShakeHub.fxml");
    }

    private void loadStage(String fxml) {
        try {
             AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));
                
               rootPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
