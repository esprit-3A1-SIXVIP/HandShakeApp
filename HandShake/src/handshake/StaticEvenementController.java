/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Evenement;
import Services.ServiceEvenement;
import Services.ServiceUser;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Soreilla
 */
public class StaticEvenementController implements Initializable {

    @FXML
    private BarChart<?, ?> EventBar;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private JFXButton btnNewEvent;
    @FXML
    private JFXButton btnStat;
    @FXML
    private JFXButton btnOurEvents;
    int eventId=17;
         public ObservableList<Evenement> data =FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          ServiceUser SU = new ServiceUser();
        int us = UserSession.getInstance().getId();
            System.out.println(us);
            
         ServiceEvenement ser = new ServiceEvenement();
     
         
         
        try {
         
            data=ser.searchEventByIdUser(us);
            
            for(int i=0; i<data.size();i++){
                
                  XYChart.Series set1 = new XYChart.Series<>();
        
        set1.getData().add(new XYChart.Data(data.get(i).getDescriptionEvenement(),
                ser.NombreParticpantParEvenement(data.get(i).getEvenementId())
                
                
        ));
        EventBar.getData().addAll(set1);
                
                System.out.println(data.get(i).getDescriptionEvenement());  
                System.out.println( ser.NombreParticpantParEvenement(eventId));
                
                
                
            }
        } catch (SQLException ex) {
         System.out.println(ex);     
        }
        
        
      /*  XYChart.Series set1 = new XYChart.Series<>();
        
        set1.getData().add(new XYChart.Data("James",50));
        EventBar.getData().addAll(set1);*/
    }    

    @FXML
    private void goToEvent(ActionEvent event) {
         try {
                    FXMLLoader top=new FXMLLoader(getClass().getResource("evenement.fxml"));

            Parent root =top.load();
                  EvenementController dpc=top.getController();
                    
                    
                    btnNewEvent.getScene().setRoot(root);

        } catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToOurEvent(ActionEvent event) {
         try {
                    FXMLLoader top=new FXMLLoader(getClass().getResource("readEvenement.fxml"));

            Parent root =top.load();
                  ReadEvenementController dpc=top.getController();
                    
                    
                    btnNewEvent.getScene().setRoot(root);

        } catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToStat(ActionEvent event) {
    }
    
}
