/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Services.ServiceUser;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steph
 */
public class HandshakeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton Connexion;
    
    @FXML
    private JFXTextField loEmail;
    
    @FXML
    private JFXPasswordField loPassword;
    
    @FXML
    private JFXButton singup;
    
    @FXML
    private Hyperlink loForgotP;
    
    @FXML
    private AnchorPane rootPane;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }  
    
     @FXML
        private void handleButtonClicks(javafx.event.ActionEvent mouseEvent)
        {
            if(mouseEvent.getSource() == Connexion)
            {
                String email = loEmail.getText();
                String Password = loPassword.getText();
                ServiceUser SU = new ServiceUser();
                
                try
                 {
                     int id = SU.getIdUser1(email, Password);
                     String role = SU.getRole(id);
                     if(id != -1)
                     {
                         if(role.equals("admin"))
                         {
                             UserSession.getInstance(email, id,role);
                         
                         loadStage("Admin.fxml");
                         }
                         else
                         {
                             UserSession.getInstance(email, id,role);
                         
                         loadStage("Home.fxml");
                         }
                         
                         
                         
                         
                     }
                     
                 }catch (SQLException ex) {
                    System.out.println(ex);
                }
                
                
            }
        }
        
        private void loadStage(String fxml)
        {
            try
            {
                AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));
                
               rootPane.getChildren().setAll(pane);
                
            } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    
}
