/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.DonEspeces;
import Entities.DonNature;
import Services.ServiceDonEspeces;
import Services.ServiceDonNature;
import Utils.SendEmailTLS;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;





/**
 * FXML Controller class
 *
 * @author steph
 */

 

public class FormulaireDonEspeceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField cibleE;
    
    @FXML
    private JFXTextField montantE;
    private IntegerStringConverter quant = new IntegerStringConverter();
    
    @FXML
    private JFXButton fairedonE;
    
    @FXML
    private JFXButton annulerD;
    
     @FXML
    private AnchorPane rootPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
   


    
    @FXML
    private void handleButtonFairedonEspece(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == fairedonE) {
            
            try
            {
                String cible = cibleE.getText();
                int montant = quant.fromString(montantE.getText());
                int us = UserSession.getInstance().getId();
                String email = UserSession.getInstance().getEmail();
                
                DonEspeces de = new DonEspeces(montant,cible, us);
                System.out.println(us);
                ServiceDonEspeces SE = new ServiceDonEspeces();
                
                SE.ajouter(de);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Votre don a été effectuer avec succès.");
            ButtonType buttonTypeOne = new ButtonType("OK");

            alert.getButtonTypes().setAll(buttonTypeOne);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                SendEmailTLS S = new SendEmailTLS();
                S.sendMail(email);
                loadStage("Home.fxml");
                
            } 
                
                
            }
            catch (SQLException ex) {
                    System.out.println(ex);
                }

            

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
    private void annulerDon()
    {
        loadStage("Home.fxml");
    }
    
}
