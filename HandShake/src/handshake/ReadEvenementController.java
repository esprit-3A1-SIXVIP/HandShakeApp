/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Evenement;
import Services.ServiceEvenement;
import Services.ServiceUser;
import Utils.EvenementSession;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import static java.util.Optional.empty;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Soreilla
 */
public class ReadEvenementController implements Initializable {
    
     @FXML
    private TableColumn<Evenement, Integer> id;

    @FXML
    private TableColumn<Evenement, String> txtnom;
    @FXML
    private TableColumn<Evenement, String> txtlieu;
    @FXML
    private TableColumn<Evenement, LocalTime> timeP;
    @FXML
    private TableColumn<Evenement, Float> txtprix;
    @FXML
    private TableColumn<Evenement, LocalDate> dateP;
    @FXML
    private TableColumn<Evenement, String> txtportee;
    @FXML
    private TableColumn txtAction;
    @FXML
    private TableView<Evenement> table;
     public ObservableList<Evenement> data =FXCollections.observableArrayList();
    @FXML
    private TableColumn txtAction2;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private AnchorPane rootPane;
   
    public int iiiii;
    
   
    /**
     ** Initializes the controller class.
     * @param url     
     * @param rb     
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ServiceUser SU = new ServiceUser();
        int us = UserSession.getInstance().getId();
            System.out.println(us);
         ServiceEvenement ser = new ServiceEvenement();
         
        try {
           data=ser.readEvenement(us);
        } catch(SQLException ex){
            System.out.println(ex);
        }
          id.setCellValueFactory(new PropertyValueFactory<>("evenementId"));
             txtnom.setCellValueFactory(new PropertyValueFactory<>("descriptionEvenement"));
                  txtlieu.setCellValueFactory(new PropertyValueFactory<>("lieuEvenement"));
                       dateP.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
                           timeP.setCellValueFactory(new PropertyValueFactory<>("heureEvenement"));
                                 txtportee.setCellValueFactory(new PropertyValueFactory<>("porteeEvenement"));
                                      txtprix.setCellValueFactory(new PropertyValueFactory<>("prixEvenement"));
                                      
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
                                                      final  JFXButton editButton= new  JFXButton("Modifier");
                                                      final  JFXButton deleteButton= new  JFXButton("Supprimer");
                                                      editButton.setOnAction(event ->{
                                                          
                                                       Evenement   e=getTableView().getItems().get(getIndex());
                                                        
                                                       EvenementSession.getInstace(e.getEvenementId(), e.getDescriptionEvenement(), e.getLieuEvenement(), e.getDateEvenement(), e.getHeureEvenement(), e.getPorteeEvenement(), e.getPrixEvenement());
                                                          
                                                          loadStage("modifierEvenement.fxml");
                                                          Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                                                          alert.setContentText("Vous êtes entrain de vouloir modifier l'évènement :"+e.getDescriptionEvenement());
                                                          alert.show();
                                                          
                                                          
                                                          
                                                          
                                                          
                                                          
                                                          });
                                                      setGraphic(editButton);
                                                      setText(null);
                                                  
                                                  }
                                              }
                                              ;
                                            
                                          };
                                         
                                               return cell;
                                          
                                      };
                                      Callback<TableColumn<Evenement,String>,TableCell<Evenement,String>> cellFactory1 =(param)->{
                                           final TableCell<Evenement,String>cell1=new TableCell<Evenement,String>(){
                                              public void updateItem(String item, boolean empty){
                                                  super.updateItem(item,empty);
                                                  if(empty){
                                                      setGraphic(null);
                                                      setText(null);
                                                  }
                                                  else
                                                  {
                                                  
                                                      final  JFXButton deleteButton= new  JFXButton("Supprimer");
                                                      deleteButton.setOnAction(event ->{
                                                          
                                                        
                                                            Evenement    e=getTableView().getItems().get(getIndex());
                                                           System.out.println(e.getEvenementId());
                                                             
                                                              Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                                                              alert.setContentText("Voulez vous vraiment supprimer l'évènement"+e.getDescriptionEvenement());
                                                              alert.show();
                                                             // Optional<ButtonType> action= alert.showAndWait();
                                                            //  if(action.get()==ButtonType.OK)
                                                              try {
                                                                  ser.delete(e.getEvenementId());
                                                          } catch (SQLException ex) {
                                                              Logger.getLogger(ReadEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
                                                         
                                                          });
                                                      setGraphic(deleteButton);
                                                      setText(null);
                                                    
                                                     
                                                  }
                                              }
                                              ;
                                            
                                          };
                                          
                                          return cell1;
                                      };
                                    txtAction.setCellFactory(cellFactory);
                                      txtAction2.setCellFactory(cellFactory1);
                                      table.setItems(data);

           
    }    

    @FXML
    private void goToEvent(ActionEvent event) {
        try {
                    FXMLLoader top=new FXMLLoader(getClass().getResource("evenement.fxml"));

            Parent root =top.load();
                  EvenementController dpc=top.getController();
                    
                    
                    btnEvent.getScene().setRoot(root);

        } catch (IOException ex) {
        System.out.println(ex.getMessage());
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
    
             
    
}
