/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.ServiceDonEspeces;
import Services.ServiceUser;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author amisa
 */
public class ProfilUserController implements Initializable {

    @FXML
    private AnchorPane pp;
    @FXML
    private Circle profil;
    @FXML
    private Label logout;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private JFXButton btnmodif;
    @FXML
    private JFXButton btnload;
    @FXML
    private TextField rue;
    @FXML
    private TextField ville;
    @FXML
    private TextField pays;
    @FXML
    private TextField username;
  
    private IntegerStringConverter quant = new IntegerStringConverter();
    @FXML
    private TextField image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ServiceUser sa = new ServiceUser();
            int us = UserSession.getInstance().getId();
            afficherProfil(us);
            String username = UserSession.getInstance().getusername();
            User a = sa.chercherUser(us);
            Image I = null;
            chargerimagecircle(I, profil, a.getProfil());
        } catch (SQLException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void chargerimagecircle(Image I, Circle c, String x) throws MalformedURLException, IOException {
        URL urlp;
        urlp = new URL(x);
        URLConnection connection = urlp.openConnection();
        InputStream inputStream = connection.getInputStream();
        c.setStroke(Color.GOLDENROD);
        I = new Image(inputStream);
        c.setFill(new ImagePattern(I));
        c.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }

    public void afficherProfil(int id) throws SQLException {
        ServiceUser ser = new ServiceUser();
        User a = ser.chercherUser(id);
        nom.setText(a.getNomUser());
        prenom.setText(a.getPrenomUser());
        email.setText(a.getEmail());
        rue.setText(a.getRue());
        ville.setText(a.getVille());
        pays.setText(a.getPays());
        username.setText(a.getusername());
        image.setText(a.getProfil());

    }

    @FXML
    private void modifierprofile(MouseEvent event) throws SQLException {
        ServiceUser SE = new ServiceUser();
        String n = nom.getText();
        String pre = prenom.getText();
        String em = email.getText();
        String r = rue.getText();
        String v = ville.getText();
        String p = pays.getText();
        String log = username.getText();
        String imG = image.getText();   
        int us = UserSession.getInstance().getId();
        SE.update(us, n, pre, em, r,v, imG, log, p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Votre don a été Modifier.");
        ButtonType buttonTypeOne = new ButtonType("OK");

        alert.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
//                SendEmailTLS S = new SendEmailTLS();
//                S.sendMail(email);
            loadStage("Home.fxml");

        }

    }
    private void loadStage(String fxml) {
        try {
             AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));
                
               pp.getChildren().setAll(pane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void LoadImage(MouseEvent event) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            image.setText(selectedFile.toURI().toString());

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Aucune image selectionner");
        }
    }

}
