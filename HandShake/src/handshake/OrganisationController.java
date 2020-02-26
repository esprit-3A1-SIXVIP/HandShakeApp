/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Evenement;
import Entities.User;
import Services.ServiceUser;
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
public class OrganisationController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<User> table;
   ObservableList<User> data =FXCollections.observableArrayList();
       
    @FXML
    private TableColumn<User, String> txtnom;
    @FXML
    private TableColumn<User, String> txtpays;
    @FXML
    private TableColumn<User, String> txtville;
    @FXML
    private TableColumn<User, String> txtdomaine;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn actionSendMail;
    @FXML
    private JFXButton btnEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
           ServiceUser ser = new ServiceUser();
        try {
            data=ser.readOrganisation();
            
        } catch (SQLException ex) {
            Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         txtnom.setCellValueFactory(new PropertyValueFactory<>("nomOrganisation"));
                  txtpays.setCellValueFactory(new PropertyValueFactory<>("pays"));
                       txtville.setCellValueFactory(new PropertyValueFactory<>("ville"));
                           txtdomaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
                                 email.setCellValueFactory(new PropertyValueFactory<>("email"));
                                       table.setItems(data);
                                    Callback<TableColumn<User,String>,TableCell<User,String>> cellFactory =(param)->{
          
                                          final TableCell<User,String>cell=new TableCell<User,String>(){
                                              public void updateItem(String item, boolean empty){
                                                  super.updateItem(item,empty);
                                                  if(empty){
                                                      setGraphic(null);
                                                      setText(null);
                                                  }
                                                  else
                                                  {
                                                      final  JFXButton sendButton= new  JFXButton("Envoyer mail");
                                                  User   u=getTableView().getItems().get(getIndex());
                                                      setGraphic(sendButton);
                                                      setText(null);
                                                  
                                                  }
                                              }
                                              ;
                                            
                                          };
                                         
                                               return cell;
                                          
                                      };
         actionSendMail.setCellFactory(cellFactory);
          table.setItems(data);
          
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
    private void goToEvent(ActionEvent event) {
        loadStage("evenement.fxml");
    }
    
}
