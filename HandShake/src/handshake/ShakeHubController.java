/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ghost
 */
public class ShakeHubController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label username;
    @FXML
    private JFXButton Statistique;
    @FXML
    private JFXButton homeButton;
    @FXML
    private TableView<?> tableDon;
    @FXML
    private JFXButton AddQuestion;
    @FXML
    private JFXTextField rechercheD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addQuestion(ActionEvent event) {
    }
    
}
