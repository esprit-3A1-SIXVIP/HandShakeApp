/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Admin;
import Entities.Organisation;
import Entities.User;
import Services.ServiceAdmin;
import Services.ServiceOrganisation;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
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
public class GestionOrgController implements Initializable {


    @FXML
    private FontAwesomeIconView search;
    @FXML
    private JFXButton btnsupp;
    @FXML
    private JFXButton btnbloquer;
    ObservableList<Organisation> listU = FXCollections.observableArrayList();
    @FXML
    private TableView<Organisation> tableau1;
    @FXML
    private TableColumn<Organisation, ?> tabnom1;
    @FXML
    private TableColumn<Organisation, ?> tabprenom1;
    @FXML
    private TableColumn<Organisation, String> tablogin1;
    @FXML
    private TableColumn<Organisation, ?> tabemail1;
    @FXML
    private TableColumn<Organisation, ?> tabrole1;
    @FXML
    private TableColumn<Organisation, ?> tabtel1;
    @FXML
    private TableColumn<Organisation, ?> tabprofil1;
    @FXML
    private TableColumn<Organisation, ?> tabrue1;
    @FXML
    private TableColumn<Organisation, ?> tabville1;
    @FXML
    private TableColumn<Organisation, ?> tpays1;
    @FXML
    private TableColumn<Organisation, ?> taborg1;
    @FXML
    private TableColumn<Organisation, ?> tabdom1;
    @FXML
    private JFXTextField lireNon1;
    @FXML
    private JFXTextField lirePrenom1;
    @FXML
    private JFXTextField lireRue1;
    @FXML
    private JFXTextField lireTel1;
    @FXML
    private JFXTextField lireEmail1;
    @FXML
    private JFXTextField lireLogin1;
    @FXML
    private JFXTextField lireVille1;
    @FXML
    private JFXTextField lirePays1;
    @FXML
    private JFXTextField lireOrg1;
    @FXML
    private JFXTextField lireDomaine1;
    @FXML
    private JFXTextField tabRecherche1;
    @FXML
    private AnchorPane intOrg;
    @FXML
    private BorderPane cadre3;
    @FXML
    private ImageView lireprofil1;
    @FXML
    private AnchorPane tablebord;
    @FXML
    private JFXButton btnmaguser;
    @FXML
    private Circle profile_admin;
    @FXML
    private JFXButton btnmeDon;
    @FXML
    private Label btnlogout;
    @FXML
    private JFXButton btnshakehub;
    @FXML
    private JFXButton benef;
    @FXML
    private JFXButton even;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            // TODO
            displayOrG();
            setcellvaluefromTabletoTextfield();
            
            ServiceAdmin sa = new ServiceAdmin();
            int us = UserSession.getInstance().getId();
            String login = UserSession.getInstance().getLogin();
            Admin a = sa.chercherAdmin(us);
            Image I=null;
            chargerimagecircle(I, profile_admin ,a.getProfil());  
        } catch (SQLException ex) {
            Logger.getLogger(GestionOrgController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionOrgController.class.getName()).log(Level.SEVERE, null, ex);
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

private void displayOrG()
{
     try {
         ServiceOrganisation service = new ServiceOrganisation();
            listU = (ObservableList<Organisation>) service.afficherOrganisation();
            tabnom1.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
            tabprenom1.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
            tablogin1.setCellValueFactory(new PropertyValueFactory<>("login"));
            tabemail1.setCellValueFactory(new PropertyValueFactory<>("email"));
            tabprofil1.setCellValueFactory(new PropertyValueFactory<>("profil"));
            tabrole1.setCellValueFactory(new PropertyValueFactory<>("role"));
            tabtel1.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            tabrue1.setCellValueFactory(new PropertyValueFactory<>("rue"));
            tabville1.setCellValueFactory(new PropertyValueFactory<>("ville"));
            taborg1.setCellValueFactory(new PropertyValueFactory<>("nomOrganisation"));
            tabdom1.setCellValueFactory(new PropertyValueFactory<>("domaine"));
            tableau1.setItems(listU);
            scrollbar(tableau1);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(GestionOrgController.class.getName()).log(Level.SEVERE, null, ex);
        }

}
private void loadStage(String fxml) {
        try {
             AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));
                
               intOrg.getChildren().setAll(pane);

        } catch (IOException ex) {
            ex.printStackTrace();
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
    private void setcellvaluefromTabletoTextfield()
{

tableau1.setOnMouseClicked(new EventHandler<MouseEvent>(){

public void handle(MouseEvent event)
{
    

Organisation p1=tableau1.getSelectionModel().getSelectedItem();
lireNon1.setText(p1.getNomUser());
lirePrenom1.setText(p1.getPrenomUser());
lireEmail1.setText(p1.getEmail());
lireRue1.setText(p1.getRue());
lireLogin1.setText(p1.getLogin());
lireVille1.setText(p1.getVille());
lirePays1.setText(p1.getPays());
lireDomaine1.setText(p1.getDomaine());
lireOrg1.setText(p1.getNomOrganisation());
String savedValue =String.valueOf(p1.getTelephone())  ; 
lireTel1.setText(savedValue);
 Image I=new Image(p1.getProfil(),100,200,true,true);
            lireprofil1=new ImageView(I);
            lireprofil1.setFitWidth(100);
            lireprofil1.setFitHeight(100);
            cadre3.setCenter(lireprofil1);
            BorderPane.setAlignment(lireprofil1, Pos.TOP_LEFT);


}



});
}
 

    @FXML
    private void Supprimerorg1(MouseEvent event) throws SQLException {
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression");
        alert.setContentText("Vous allez Supprimer un Don .\n\n Voulez vous vraiment effectuer cette action?  ");
        ButtonType buttonTypeOne = new ButtonType("Confirm");
        ButtonType buttonTypeOne1 = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeOne1);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {

//            ObservableList<Dons> donSelected, allDon;
//            allDon = tableDon.getItems();
//            donSelected = tableDon.getSelectionModel().getSelectedItems();
//            donSelected.forEach(allDon::remove);
            int index = tableau1.getSelectionModel().getSelectedIndex();
         
          
          
              String login = tablogin1.getCellData(index);

                ServiceOrganisation so=new ServiceOrganisation();
                Organisation x=so.chercherlogin(login);
                so.supprimer(x);
                listU.removeAll(tableau1.getSelectionModel().getSelectedItems());
                tableau1.getSelectionModel().clearSelection();
                
         

        } 
        else
        
        {
            alert.close();
        }

    }

    @FXML
    private void btnmeDon(MouseEvent event) {
         loadStage("Admin.fxml");
    }

    @FXML
    private void logout(MouseEvent event) {
         UserSession.getInstance().cleanUserSession();
          loadStage("login2.fxml");
    }

    @FXML
    private void btnmaguser(MouseEvent event) {
                  loadStage("gestionnaire.fxml");
    }
    private void btnmdon(ActionEvent event) {
        loadStage("Admin.fxml");
    }
    @FXML
    private void btnshake(ActionEvent event) {
        loadStage("ShakeHub.fxml");
    }

 
    @FXML
    private void beneficiaire(ActionEvent event) {
        loadStage("InterBeneficiaire.fxml");
    }

    private void btnlistOrg1(ActionEvent event) {
        loadStage("gestionOrg.fxml");
    }


    private void articles(ActionEvent event) {
        loadStage("articleAdmin.fxml");
    }

    @FXML
    private void evenements(ActionEvent event) {
        loadStage("evenement.fxml");
    }



   
}
