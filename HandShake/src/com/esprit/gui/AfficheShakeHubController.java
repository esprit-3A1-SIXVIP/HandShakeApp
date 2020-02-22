/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ghost
 */
public class AfficheShakeHubController implements Initializable {

    @FXML
    private ImageView handshake;
    @FXML
    private Button AddQuestion;
    @FXML
    private Label logintext;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<?> tableQuestions;
    @FXML
    private TableColumn<?, ?> login;
    @FXML
    private TableColumn<?, ?> texteQuestion;
    @FXML
    private TableColumn<?, ?> dateQuestion;
    @FXML
    private JFXTextField rechercheQuestion;
    @FXML
    private TableColumn<?, ?> viewComments;
    @FXML
    private TableColumn<?, ?> modifierQuestion;
    @FXML
    private TableColumn<?, ?> supprimerQuestion;
    @FXML
    private JFXButton homeButton;

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
