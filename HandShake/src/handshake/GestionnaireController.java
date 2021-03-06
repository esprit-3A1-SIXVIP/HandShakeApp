/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;
import Utils.DataBase;
import Entities.Admin;
import Entities.User;
import Services.ServiceAdmin;
import Services.ServiceUser;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author amisa
 */
public class GestionnaireController implements Initializable {
    private Connection con;
    private Statement ste;
      ObservableList<User> listU = FXCollections.observableArrayList();
    private AnchorPane intUser;
    @FXML
    private BorderPane cadre2;
    @FXML
    private ImageView lireprofil;
    @FXML
    private AnchorPane tablebord;
    @FXML
    private JFXButton btnmaguser;
    @FXML
    private Circle profile_admin;
    @FXML
    private TableView<User> tableau;
    @FXML
    private TableColumn<User, String> tabnom;
    @FXML
    private TableColumn<User, String> tabprenom;
    @FXML
    private TableColumn<User, String> tabusername;
    @FXML
    private TableColumn<User, String> tabemail;
    @FXML
    private TableColumn<User, String> tabroles;
    @FXML
    private TableColumn<User, String> tabtel;
    @FXML
    private TableColumn<User, String> tabprofil;
    @FXML
    private TableColumn<User, String> tabrue;
    @FXML
    private TableColumn<User, String> tabville;
    @FXML
    private TableColumn<User, String> tpays;
    @FXML
    private TableColumn<User, String> taborg;
    @FXML
    private TableColumn<User, String> tabdom;
    @FXML
    private FontAwesomeIconView search;
    @FXML
    private JFXTextField tabRecherche;
    @FXML
    private ToggleButton btnlistOrg1;
    @FXML
    private JFXButton btnsupp;
    @FXML
    private JFXButton btnbloquer;
    @FXML
    private JFXTextField lireNon;
    @FXML
    private JFXTextField lirePrenom;
    @FXML
    private JFXTextField lireusername;
    @FXML
    private JFXTextField lireEmail;
    @FXML
    private JFXTextField lireTel;
    @FXML
    private JFXTextField lireRue;
    @FXML
    private JFXTextField lireVille;
    @FXML
    private JFXTextField lirePays;
    @FXML
    private JFXButton btnmdon;
    @FXML
    private Label btnlogout;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton btnshakehub;
    @FXML
    private JFXButton btnArticles;
    @FXML
    private JFXButton benef;
    @FXML
    private JFXButton even;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              // TODO
              displayUser();
              setcellvaluefromTabletoTextfield();
                con = DataBase.getInstance().getConnection();
               ServiceAdmin sa = new ServiceAdmin();
                int us = UserSession.getInstance().getId();
              Admin a = sa.chercherAdmin(us);
              Image I=null;
              chargerimagecircle(I, profile_admin ,a.getProfil());   
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }
    }    
  void chargerimagecircle(Image I, Circle c, String x) {
        try {
            URL urlp;
            urlp = new URL(x);
            URLConnection connection = urlp.openConnection();
            InputStream inputStream = connection.getInputStream();
            c.setStroke(Color.GOLDENROD);
            I = new Image(inputStream);
            c.setFill(new ImagePattern(I));
            c.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void displayUser() {
        try {
            ServiceUser service = new ServiceUser();

            listU = (ObservableList<User>) service.afficherUser();
            tabnom.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
            tabprenom.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
            tabusername.setCellValueFactory(new PropertyValueFactory<>("username"));
            tabprofil.setCellValueFactory(new PropertyValueFactory<>("profil"));
            tabemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            tabroles.setCellValueFactory(new PropertyValueFactory<>("roles"));
            tabtel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            tabrue.setCellValueFactory(new PropertyValueFactory<>("rue"));
            tabville.setCellValueFactory(new PropertyValueFactory<>("ville"));
           
            tableau.setItems(listU);
            scrollbar(tableau);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void scrollbar(TableView table) {
        ScrollPane sp = new ScrollPane(table);
        sp.setContent(table);
        sp.setPrefSize(600, 200);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setFitToHeight(true);
        sp.setHmax(3);
        sp.setHvalue(0);
        sp.setDisable(true);

    }

    private void setcellvaluefromTabletoTextfield() {

        tableau.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {

                User p1 = tableau.getSelectionModel().getSelectedItem();
                lireNon.setText(p1.getNomUser());
                lirePrenom.setText(p1.getPrenomUser());
                lireEmail.setText(p1.getEmail());
                lireRue.setText(p1.getRue());
                lireusername.setText(p1.getusername());
                lireVille.setText(p1.getVille());
                lirePays.setText(p1.getPays());
               
                String savedValue = String.valueOf(p1.getTelephone());
                lireTel.setText(savedValue);
                Image I = new Image(p1.getProfil(), 100, 200, true, true);
                lireprofil = new ImageView(I);
                lireprofil.setFitWidth(100);
                lireprofil.setFitHeight(100);
                cadre2.setCenter(lireprofil);
                BorderPane.setAlignment(lireprofil, Pos.TOP_LEFT);

            }

        });
    }

// public void SupprimerDonU(ActionEvent action) throws SQLException {
//
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("Suppression");
//        alert.setContentText("Vous allez Supprimer un Don .\n\n Voulez vous vraiment effectuer cette action?  ");
//        ButtonType buttonTypeOne = new ButtonType("Confirm");
//        ButtonType buttonTypeOne1 = new ButtonType("Cancel");
//
//        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeOne1);
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == buttonTypeOne) {
//
////            ObservableList<Dons> donSelected, allDon;
////            allDon = tableDon.getItems();
////            donSelected = tableDon.getSelectionModel().getSelectedItems();
////            donSelected.forEach(allDon::remove);
//            int index = listU.getSelectionModel().getSelectedIndex();
//            String type = lireNon.getCellData(index);
//            System.out.println(type);
//            if (type.equals("Nature")) {
//                int id = donId.getCellData(index);
//
//                ServiceDonNature SN = new ServiceDonNature();
//                SN.delete(id);
//                donList.removeAll(tableDon.getSelectionModel().getSelectedItems());
//                tableDon.getSelectionModel().clearSelection();
//            } else {
//                int id = donId.getCellData(index);
//
//                ServiceDonEspeces SE = new ServiceDonEspeces();
//                SE.delete(id);
//                donList.removeAll(tableDon.getSelectionModel().getSelectedItems());
//                tableDon.getSelectionModel().clearSelection();
//            }
//
//        } else {
//            alert.close();
//        }
//
//    }
    private void statistique() {

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
    private void btnsupuser(MouseEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression");
        alert.setContentText("Vous allez Supprimer un Don .\n\n Voulez vous vraiment effectuer cette action?  ");
        ButtonType buttonTypeOne = new ButtonType("Confirm");
        ButtonType buttonTypeOne1 = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeOne1);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            int index = tableau.getSelectionModel().getSelectedIndex();
            String username = tabusername.getCellData(index);
            ServiceUser so = new ServiceUser();
            User x = so.chercherUser(username);
            so.supprimer(x);
            listU.removeAll(tableau.getSelectionModel().getSelectedItems());
            tableau.getSelectionModel().clearSelection();

        } else {
            alert.close();
        }

    }

    @FXML
    private void btnmdon(ActionEvent event) {
        loadStage("Admin.fxml");
    }
    @FXML
    private void btnshake(ActionEvent event) {
        loadStage("ShakeHub.fxml");
    }

    @FXML
    private void logout(MouseEvent event) {
       UserSession.getInstance().cleanUserSession();
        loadStage("login2.fxml");
    }
    @FXML
    private void beneficiaire(ActionEvent event) {
        loadStage("InterBeneficiaire.fxml");
    }

    @FXML
    private void btnlistOrg1(ActionEvent event) {
        loadStage("gestionOrg.fxml");
    }

    @FXML
    private void btnlistOrg1(MouseEvent event) {
    }

    @FXML
    private void articles(ActionEvent event) {
        loadStage("articleAdmin.fxml");
    }

    @FXML
    private void evenements(ActionEvent event) {
        loadStage("evenement.fxml");
    }


    
}
