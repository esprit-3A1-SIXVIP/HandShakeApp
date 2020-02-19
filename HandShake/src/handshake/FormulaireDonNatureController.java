/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.DonNature;
import Services.ServiceDonNature;
import Utils.SendEmailTLS;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import static java.sql.JDBCType.NULL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author steph
 */
public class FormulaireDonNatureController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField cibleNature;
    
    @FXML
    private JFXTextField libelleNature;
    
    @FXML
    private JFXComboBox categorieNature;
    
    @FXML
    private JFXTextField quantiteNature;
    
    @FXML
    private JFXButton fairedonNature;
    
     @FXML
    private JFXButton annulerD;
    
     @FXML
    private AnchorPane rootPane;
    
    private IntegerStringConverter quant = new IntegerStringConverter();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categorieNature.getItems().addAll("Alimentaire","Vestimentaire","Autre");
        categorieNature.setValue("Alimentaire");
        
        
       
        
    }    
    
    @FXML
    private void handleButtonFairedonNature(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == fairedonNature) {
            
            try
            {
                String cible = cibleNature.getText();
                String libelle = libelleNature.getText();
                String categorie = (String) categorieNature.getValue();
                int quantite = quant.fromString(quantiteNature.getText());
                int us = UserSession.getInstance().getId();
                String email = UserSession.getInstance().getEmail();
                
                
                DonNature dn = new DonNature(cible, libelle, categorie, quantite, us);
                System.out.println(us);
                ServiceDonNature SN = new ServiceDonNature();
                
                SN.ajouter(dn);
                
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
