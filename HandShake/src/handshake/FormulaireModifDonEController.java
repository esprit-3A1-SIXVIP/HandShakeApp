/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.DonEspeces;
import Services.ServiceDonEspeces;
import Utils.ModifSession;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author steph
 */
public class FormulaireModifDonEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField cibleE;
    
    @FXML
    private JFXTextField montantE;
    private IntegerStringConverter quant = new IntegerStringConverter();
    
    @FXML
    private JFXButton modifierdonE;
    
    @FXML
    private JFXButton annulerD;
    
     @FXML
    private AnchorPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cibleE.setText(ModifSession.getInstance().getCible());
        String s = "" + ModifSession.getInstance().getMontant();
        montantE.setText(s);
        
    } 
    
        @FXML
    private void handleButtonModifierdonEspece(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == modifierdonE) {
            
            try
            {
                String cible = cibleE.getText();
                int montant = quant.fromString(montantE.getText());
//                int us = UserSession.getInstance().getId();
//                String email = UserSession.getInstance().getEmail();
                int id = ModifSession.getInstance().getId();
                ServiceDonEspeces SE = new ServiceDonEspeces();
                
                SE.update(id, cible, montant);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Votre don a été Modifier.");
            ButtonType buttonTypeOne = new ButtonType("Ok");

            alert.getButtonTypes().setAll(buttonTypeOne);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
//                SendEmailTLS S = new SendEmailTLS();
//                S.sendMail(email);
                loadStage("User.fxml");
                
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
        loadStage("User.fxml");
    }
    
}
