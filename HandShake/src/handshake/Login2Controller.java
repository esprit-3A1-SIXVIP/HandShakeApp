/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Gui.*;
import Entities.Organisation;
import Entities.User;
import Services.ServiceAdmin;
import Services.ServiceOrganisation;
import Services.ServiceUser;
import Utils.SendEmailTLS;
import Utils.UserSession;
import static Utils.DataBase.db;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Login2Controller {

    private Connection con;
    private Statement ste;
    private AnchorPane interfaceprincipale;

    @FXML
    private VBox vboxconnexion;

    @FXML
    private Label tc1;
    @FXML
    private JFXButton btni;

    @FXML
    private Label tc2;

    @FXML
    private Pane interfaceconnexion;

    @FXML
    private ImageView lc1;

    @FXML
    private Label lc2;

    private Label lc3;

    @FXML
    private JFXTextField tcl;

    @FXML
    private JFXPasswordField tcp;

    @FXML
    private Label mdpforgot;

    @FXML
    private JFXButton btncx;


    @FXML
    private Pane paneinscription;

    @FXML
    private JFXPasswordField tipas;

    @FXML
    private JFXPasswordField tipas3;

    @FXML
    private JFXTextField tip;

    @FXML
    private JFXTextField tie;

    @FXML
    private JFXTextField tin;

    @FXML
    private JFXTextField til2;

    @FXML
    private JFXTextField tir;

    @FXML
    private JFXTextField tiv;

    @FXML
    private JFXTextField titel;

    @FXML
    private JFXTextField tipay;

    @FXML
    private Label tititre;

    @FXML
    private JFXTextField tinog;

    @FXML
    private JFXTextField tidog;
    @FXML
    private JFXTextField urlimage;
    private Image I;
    @FXML
    private ImageView profil;

    @FXML
    private JFXButton btni1;

    @FXML
    private JFXButton btni2;

    private ImageView close;

    @FXML
    private VBox vboxinscription;

    @FXML
    private Label ti1;

    @FXML
    private JFXButton btnc1;

    @FXML
    private Label ti2;
    private IntegerStringConverter quant = new IntegerStringConverter();
    @FXML
    private ToggleButton btnaddorg;
    @FXML
    private ToggleButton btnadduser;
    @FXML
    private BorderPane layout;
    @FXML
    private AnchorPane rootPane;

    public void initialize(URL url, ResourceBundle rb) {
        con = db.getInstance().getConnection();

        ServiceUser SU = new ServiceUser();
        int us = UserSession.getInstance().getId();
        String email = UserSession.getInstance().getEmail();
        String username = UserSession.getInstance().getusername();

        ti1.setVisible(false);
        ti2.setVisible(false);
        tidog.setVisible(false);
        tie.setVisible(false);
        til2.setVisible(false);
        tin.setVisible(false);
        tinog.setVisible(false);
        tip.setVisible(false);
        tipas.setVisible(false);
        tipas3.setVisible(false);
        tipay.setVisible(false);
        tir.setVisible(false);
        titel.setVisible(false);
        tititre.setVisible(false);
        tiv.setVisible(false);
        paneinscription.setVisible(false);
        btni.setVisible(false);
        btni1.setVisible(false);
        btni2.setVisible(false);
        vboxinscription.setVisible(false);
        paneinscription.setVisible(false);
        urlimage.setVisible(false);

    }

    private void btnusernameAction(ActionEvent event) {

    }

    @FXML
    private void btnc1(MouseEvent event) {

        TranslateTransition Slide = new TranslateTransition();
        Slide.setDuration(Duration.seconds(0.7));
        Slide.setNode(vboxconnexion);
        Slide.setToX(0);
        Slide.play();
        vboxconnexion.setVisible(true);
        vboxconnexion.setTranslateX(800);
        interfaceconnexion.setVisible(true);
        paneinscription.setVisible(false);
        vboxinscription.setVisible(false);
        ti1.setVisible(false);
        ti2.setVisible(false);
        tidog.setVisible(false);
        tie.setVisible(false);
        til2.setVisible(false);
        tin.setVisible(false);
        tinog.setVisible(false);
        tip.setVisible(false);
        tipas.setVisible(false);
        tipas3.setVisible(false);
        tipay.setVisible(false);
        tir.setVisible(false);
        titel.setVisible(false);
        tititre.setVisible(false);
        tiv.setVisible(false);
        paneinscription.setVisible(false);
        btni.setVisible(true);
        btni1.setVisible(false);
        btni2.setVisible(false);
        urlimage.setVisible(false);
        Slide.setOnFinished((e -> {
        }));

    }

    @FXML
    private void btni(MouseEvent event) {
        TranslateTransition Slide = new TranslateTransition();
        Slide.setDuration(Duration.seconds(0.7));
        Slide.setNode(vboxinscription);
        Slide.setToX(400);
        Slide.play();
        vboxinscription.setTranslateX(-30);
        vboxinscription.setVisible(true);
        interfaceconnexion.setVisible(false);
        vboxconnexion.setVisible(false);
        ti1.setVisible(true);
        ti2.setVisible(true);
        tidog.setVisible(true);
        tie.setVisible(true);
        til2.setVisible(true);
        tin.setVisible(true);
        tinog.setVisible(true);
        tip.setVisible(true);
        tipas.setVisible(true);
        tipas3.setVisible(true);
        tipay.setVisible(true);
        tir.setVisible(true);
        titel.setVisible(true);
        tititre.setVisible(true);
        tiv.setVisible(true);
        paneinscription.setVisible(true);
        btni.setVisible(true);
        btni1.setVisible(true);
        btni2.setVisible(true);
        urlimage.setVisible(true);
        Slide.setOnFinished((e -> {
        }));
    }

    private void closebtn(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void profil(MouseEvent event) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            urlimage.setText(selectedFile.toURI().toString());

            I = new Image(selectedFile.toURI().toString(), 100, 200, true, true);
            profil = new ImageView(I);
            profil.setFitWidth(100);
            profil.setFitHeight(100);
            layout.setCenter(profil);
            BorderPane.setAlignment(profil, Pos.TOP_LEFT);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Aucune image selectionner");
        }
    }

    @FXML
    private void btncx(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == btncx) {
            ServiceUser SU = new ServiceUser();
            String email = tcl.getText();
            String Password = tcp.getText();

                try {

                    int id = SU.getIdUser1(email, Password);
                    String roles = SU.getroles(id);
                    String username = SU.getusername(id);
                    UserSession.setU(new User(id, username, Password, email, roles, SU.getUser(id).isAccesShakeHub()));
                    if (id != -1) {
                        UserSession.getInstance(email, id, Password, roles, username);
                        if (roles.equals("admin")) {
                            loadStage("gestionnaire.fxml");
                        }
                        else
                        {
                            loadStage("Home.fxml");
                        }
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
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

    @FXML

    private void btnisave(MouseEvent event) {
        if (event.getSource() == btni1) {

            String nom = tin.getText();
            String prenom = tip.getText();
            String email = tie.getText();
            String rue = tir.getText();
            String ville = tiv.getText();
            String pays = tipay.getText();
            String username = til2.getText();
            String password1 = tipas.getText();
            String password2 = tipas3.getText();
            String prof = urlimage.getText();
            String org = tinog.getText();
            String domaine = tidog.getText();
            int tel = quant.fromString(titel.getText());
            if (btnadduser.isSelected()) {
                try {

                    User u = new User(username, password1, nom, prenom, email, tel, ville, rue, pays, prof);
                    ServiceUser SE = new ServiceUser();
                    SE.ajouter(u);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setContentText("Votre inscription a été effectuer avec succès.");
                    ButtonType buttonTypeOne = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeOne);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {
                        SendEmailTLS S = new SendEmailTLS();
                        S.sendMail(u.getEmail());
                        loadStage("formrefuge.fxml");

                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {

                try {
                    Organisation O = new Organisation(org, domaine, username, password1, nom, prenom, email, tel, ville, rue, pays, prof);
                    ServiceOrganisation so = new ServiceOrganisation();
                    so.ajouter(O);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setContentText("Votre inscription a été effectuer avec succès.");
                    ButtonType buttonTypeOne = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeOne);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {
                        SendEmailTLS S = new SendEmailTLS();
                        S.sendMail(O.getEmail());
                        loadStage("formrefuge.fxml");

                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    private void btnuserform(MouseEvent event) {
        lc3.setText("sign in to continue");
    }

    private void btnadminform(MouseEvent event) {
        lc3.setText("Administrator");
    }

    @FXML
    private void btnaddorg(MouseEvent event) {
        tinog.setVisible(true);
        tidog.setVisible(true);

    }

    @FXML
    private void btnadduser(MouseEvent event) {
        tinog.setVisible(false);
        tidog.setVisible(false);
    }

    private void btnminus1(MouseEvent event) {
        Stage s = (Stage) (interfaceprincipale).getScene().getWindow();
        s.setIconified(true);
    }

    private void btnfull1(MouseEvent event) {
        Stage s = (Stage) (interfaceprincipale).getScene().getWindow();
        s.setFullScreen(true);

    }
}
