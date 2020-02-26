/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Evenement;
import Services.ServiceEvenement;
import Utils.EvenementSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.FloatStringConverter;

/**
 * FXML Controller class
 *
 * @author Soreilla
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private JFXTextField txtnom;
    @FXML
    private JFXTextField txtlieu;
    @FXML
    private JFXTextField txtportee;
    @FXML
    private JFXTextField txtprix;
    @FXML
    private JFXDatePicker dateP;
    @FXML
    private JFXTimePicker timeheure;
    @FXML
    private JFXButton modifierEvenement;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btnStat;
    @FXML
    private AnchorPane rootPane;
  public ObservableList<Evenement> rep =FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
            
           txtnom.setText(EvenementSession.getInstance().getDescriptionEvenement());
            txtlieu.setText(EvenementSession.getInstance().getLieuEvenement());
            txtportee.setText(EvenementSession.getInstance().getPorteeEvenement());
            String txtprix1 =""+EvenementSession.getInstance().getPrixEvenement();
            txtprix.setText(txtprix1);
            dateP.setValue(EvenementSession.getInstance().getDateEvenement());
            timeheure.setValue(EvenementSession.getInstance().getHeureEvenement());
        
    }    

    @FXML
    private void goToMesEvenements(ActionEvent event) {
    }

    @FXML
    private void goToSatisticsE(ActionEvent event) {
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
    private void updateEvenement(ActionEvent event) throws SQLException {
        
        
        
      
       int evenementId = EvenementSession.getInstance().getEvenementId();
         
            FloatStringConverter quant = new FloatStringConverter();
            float prix = quant.fromString(txtprix.getText());
         
          System.out.println( txtnom.getText());
          System.out.println(evenementId);
    
         
            ServiceEvenement ser = new ServiceEvenement();
            ser.update(evenementId, txtnom.getText(), txtlieu.getText(), dateP.getValue(), timeheure.getValue(), txtportee.getText(), prix);
         
          loadStage("readEvenement.fxml");
           
      
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
