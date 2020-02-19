/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.DonNature;
import Services.ServiceDonNature;
import Utils.ModifSession;
import Utils.SendEmailTLS;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author steph
 */
public class FormulaireModifDonNController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField cibleNature;

    @FXML
    private JFXTextField libelleNature;

    @FXML
    private JFXComboBox categorieNature;

    @FXML
    private JFXTextField quantiteNature;

    @FXML
    private JFXButton modifdonNature;

    @FXML
    private JFXButton annulerD;

    @FXML
    private AnchorPane rootPane;

    private IntegerStringConverter quant = new IntegerStringConverter();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categorieNature.getItems().addAll("Alimentaire", "Vestimentaire", "Autre");
        categorieNature.setValue(ModifSession.getInstance().getCategorie());
        cibleNature.setText(ModifSession.getInstance().getCible());
        libelleNature.setText(ModifSession.getInstance().getLibelle());
        String s = "" + ModifSession.getInstance().getQuantite();
        quantiteNature.setText(s);

    }

    @FXML
    private void handleButtonModifierdonNature(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == modifdonNature) {

            try {
                String cible = cibleNature.getText();
                String libelle = libelleNature.getText();
                String categorie = (String) categorieNature.getValue();
                int quantite = quant.fromString(quantiteNature.getText());
                //int us = UserSession.getInstance().getId();
                //String email = UserSession.getInstance().getEmail();

                ServiceDonNature SN = new ServiceDonNature();
                int us = ModifSession.getInstance().getId();
                System.out.println(us);

                if (us != -1) {
                    SN.update(us, cible, libelle, categorie, quantite);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setContentText("Votre don a été Modifier.");
                    ButtonType buttonTypeOne = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeOne);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {
//                SendEmailTLS S = new SendEmailTLS();
//                S.sendMail(email);
                        loadStage("User.fxml");

                    }
                }

            } catch (SQLException ex) {
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
    private void annulerDon() {
        loadStage("User.fxml");
    }

}
