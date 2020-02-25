/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;


import Entities.Refuge;
import Services.ServiceRefuge;
import Utils.SendEmailTLS;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author amisa
 */
public class FormRefugeController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Pane formrefuge;
    @FXML
    private JFXTextField FRrue1;
    @FXML
    private JFXTextField FRville1;
    @FXML
    private JFXTextField FRpays1;
    @FXML
    private JFXTextField FRcap1;
    @FXML
    private JFXDatePicker FRddeb1;
    @FXML
    private JFXDatePicker FRdfin1;
    @FXML
    private JFXButton ajouterRefuge;
    @FXML
    private JFXButton annulerRef;
    @FXML
    private JFXTextField longitude;
    @FXML
    private JFXTextField latitude;
 private DoubleStringConverter quant = new DoubleStringConverter();
  private IntegerStringConverter quant1 = new IntegerStringConverter();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


  

    @FXML
    private void ajouterRefuge(MouseEvent event) {
     if (event.getSource() == ajouterRefuge) {
            
         String rue = FRrue1.getText();
         String pays = FRpays1.getText();
         String ville = FRville1.getText();
         Double longi = quant.fromString(longitude.getText());
         Double lat = quant.fromString(latitude.getText());
         String type="Refuge";
         LocalDate datd=FRddeb1.getValue();
         LocalDate datf=FRdfin1.getValue();
         int cap=quant1.fromString(FRcap1.getText());
         int us = UserSession.getInstance().getId();
         String email = UserSession.getInstance().getEmail();
         Refuge de = new Refuge(rue, ville, pays, cap, datd, datf, longi, lat, us, type, us);
         System.out.println(us);
         ServiceRefuge SE = new ServiceRefuge();
         SE.ajouter(de,us);
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
    private void annuleref(MouseEvent event) {
        loadStage("Home.fxml");
    }

   

  
    
    
}
