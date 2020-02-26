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
import com.itextpdf.text.Image;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.swing.text.html.ImageView;






/**
 * FXML Controller class
 *
 * @author Soreilla
 */
public class FrontController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> txtnom;
    @FXML
    private TableColumn<Evenement, String> txtlieu;
    @FXML
    private TableColumn<Evenement, LocalTime > timeP;
    @FXML
    private TableColumn<Evenement, String> txtportee;
    @FXML
    private TableColumn<Evenement, Float> txtprix;
    @FXML
    private TableColumn<Evenement, LocalDate> dateP;
    @FXML
    private TableColumn<Evenement, String> image;
    @FXML
    private TableColumn txtAction;
    @FXML
    private TableColumn<Evenement, Integer> id;
    @FXML
    private JFXButton btnEvent;
     public ObservableList<Evenement> data =FXCollections.observableArrayList();
     
ImageView imageview;
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
           data=ser.readAll();
           
        } catch(SQLException ex){
            System.out.println(ex);
        }
            id.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("evenementId"));
             txtnom.setCellValueFactory(new PropertyValueFactory<Evenement,String>("descriptionEvenement"));
                  txtlieu.setCellValueFactory(new PropertyValueFactory<Evenement,String>("lieuEvenement"));
                       dateP.setCellValueFactory(new PropertyValueFactory<Evenement,LocalDate>("dateEvenement"));
                           timeP.setCellValueFactory(new PropertyValueFactory<Evenement,LocalTime>("heureEvenement"));
                                 txtportee.setCellValueFactory(new PropertyValueFactory<Evenement,String>("porteeEvenement"));
                                      txtprix.setCellValueFactory(new PropertyValueFactory<Evenement,Float>("prixEvenement"));
                                 //     image.setCellValueFactory(new PropertyValueFactory<Evenement, String>("image"));
                         
                                 
 /*image.setCellValueFactory(new PropertyValueFactory<Evenement, String>("image"));
     javafx.scene.image.Image Ie=null;
                                      Ie=new javafx.scene.image.Image("image");
                                      */
                                        table.setItems(data);
                                    Callback<TableColumn<Evenement,String>,TableCell<Evenement,String>> cellFactory =(param)->{
          
                                          final TableCell<Evenement,String>cell=new TableCell<Evenement,String>(){
                                              public void updateItem(String item, boolean empty){
                                                  super.updateItem(item,empty);
                                                  if(empty){
                                                      setGraphic(null);
                                                      setText(null);
                                                  }
                                                  else
                                                  {
                                                      final  JFXButton takeButton= new  JFXButton("Participer");
                                               
                                                     takeButton.setOnAction(event ->{
                                                          Evenement e=getTableView().getItems().get(getIndex());
                                                          try {
                                                              ser.particper(e, us);
                                                          } catch (SQLException ex) {
                                                              Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
                                                         
                                                          Alert alert=new Alert(Alert.AlertType.INFORMATION);
                                                          alert.setContentText("Vous participer maintenant à l'évènement "+e.getDescriptionEvenement());
                                                          alert.show();
                                                          });
                                                      setGraphic(takeButton);
                                                      setText(null);
                                                     /* setGraphic(deleteButton);
                                                      setText(null);*/
                                                     
                                                  }
                                              }
                                              ;
                                            
                                          };
                                         
                                               return cell;
                                          
                                      };
                                      
                                    txtAction.setCellFactory(cellFactory);
                                     
                                      table.setItems(data);

    }    

    @FXML
    private void goToEvent(ActionEvent event) {
        
        
        
        
        
        
        
        
    }
    
}
