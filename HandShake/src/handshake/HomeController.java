


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Services.ServiceUser;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    private Circle cercledon;
    @FXML
    private JFXButton btn;
     @FXML
    private JFXButton btn2;

    @FXML
    private JFXButton btn3;
     @FXML
    private Hyperlink appel_aide;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String login = UserSession.getInstance().getLogin();
        btn3.setText(login);
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
}

