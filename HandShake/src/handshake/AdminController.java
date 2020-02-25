/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Dons;
import Services.ServiceDonEspeces;
import Services.ServiceDonNature;
import Services.ServiceUser;
import Utils.DataBase;
import Utils.ModifSession;
import Utils.UserSession;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import handshake.NetworkConnection;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import javafx.application.Platform;

/**
 * FXML Controller class
 *
 * @author steph
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Connection con;
    private Statement ste;
    private FileChooser fc;

    @FXML
    private JFXTextField input;

    final static String nature = "Nature";
    final static String espece = "Espece";

    public static final String chemin = "C:\\Users\\steph\\OneDrive\\Documents\\TableDon.pdf";

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton supprimerD;

    @FXML
    private JFXButton buttonPdf;

    @FXML
    private JFXButton Statistique;

    @FXML
    private JFXTextField rechercheD;

    @FXML
    private Label emailU;

    @FXML
    private TableColumn<Dons, Integer> donId;

    @FXML
    private TableColumn<Dons, String> typeD;

    @FXML
    private TableColumn<Dons, String> cibleD;

    @FXML
    private TableColumn<Dons, Integer> montantD;

    @FXML
    private TableColumn<Dons, String> libelleD;

    @FXML
    private TableColumn<Dons, String> categorieD;
    @FXML
    private TableColumn<Dons, Integer> quantiteD;

    @FXML
    private TableColumn<Dons, Date> dateD;

    @FXML
    private TableView<Dons> tableDon;

    ObservableList<Dons> donList = FXCollections.observableArrayList();

    private boolean isServer = true;

    @FXML
    private JFXTextArea messages = new JFXTextArea();

    private NetworkConnection connection = isServer ? createServer() : createClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        con = DataBase.getInstance().getConnection();
        ServiceUser SU = new ServiceUser();
        int us = UserSession.getInstance().getId();
        String email = UserSession.getInstance().getEmail();
        String login = UserSession.getInstance().getLogin();
        emailU.setText(email);
        try {
            donList = (ObservableList<Dons>) SU.readAllDonAdmin();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        donId.setCellValueFactory(new PropertyValueFactory<>("donId"));
        typeD.setCellValueFactory(new PropertyValueFactory<>("typeDon"));
        cibleD.setCellValueFactory(new PropertyValueFactory<>("cibleDon"));
        montantD.setCellValueFactory(new PropertyValueFactory<>("montantDon"));
        libelleD.setCellValueFactory(new PropertyValueFactory<>("libelleDonNature"));
        categorieD.setCellValueFactory(new PropertyValueFactory<>("categorieDonNature"));
        quantiteD.setCellValueFactory(new PropertyValueFactory<>("quantiteDonNature"));
        dateD.setCellValueFactory(new PropertyValueFactory<>("dateDon"));

        tableDon.setItems(donList);

        //* Debut Partie Filtre *//
        FilteredList<Dons> filteredData = new FilteredList<>(donList, b -> true);
        rechercheD.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate((Dons don) -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (don.getCibleDon().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (don.getTypeDon().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });

        SortedList<Dons> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableDon.comparatorProperty());

        tableDon.setItems(sortedData);
        //* Debut Partie Filtre *//

        //* Debut Partie Chat *//
        input.setOnAction(event -> {
            String message = isServer ? "" + login + "(Admin) : " : "Client : ";
            message += input.getText();
            input.clear();

            messages.appendText(message + "\n");

            try {
                connection.send(message);
            } catch (Exception e) {
                messages.appendText("Failed to send\n");
            }
        });

        try {
            connection.startConnection();
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //* Fin Partie Chat *//

    }

    private void loadStage(String fxml) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));

            rootPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void SupprimerDonU(ActionEvent action) throws SQLException {

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
            int index = tableDon.getSelectionModel().getSelectedIndex();
            String type = typeD.getCellData(index);
            System.out.println(type);
            if (type.equals("Nature")) {
                int id = donId.getCellData(index);

                ServiceDonNature SN = new ServiceDonNature();
                SN.delete(id);
                donList.removeAll(tableDon.getSelectionModel().getSelectedItems());
                tableDon.getSelectionModel().clearSelection();
            } else {
                int id = donId.getCellData(index);

                ServiceDonEspeces SE = new ServiceDonEspeces();
                SE.delete(id);
                donList.removeAll(tableDon.getSelectionModel().getSelectedItems());
                tableDon.getSelectionModel().clearSelection();
            }

        } else {
            alert.close();
        }

    }

    public void Imprimer(ActionEvent action) throws SQLException, MalformedURLException {

        Document document = new Document();
        
        try {
            PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(chemin));
            document.open();
            
            document.addAuthor("HandShake");
            document.add(new Paragraph("                                                              Historique de Don\n\n\n\n\n\n"));
            document.add(new Paragraph("                                                              Don Nature\n\n"));
            document.add(premierTableau());
            document.add(new Paragraph("\n\n\n\n\n                                                              Don Espece\n\n"));
            document.add(premierTableau1());
            document.addCreationDate();

        } catch (DocumentException | IOException de) {
            de.printStackTrace();
        }

        document.close();

    }

    public PdfPTable premierTableau() throws SQLException {

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don ");
        //On créer un objet table dans lequel on intialise ça taille.
        PdfPTable table = new PdfPTable(5);
        
        //On créer l'objet cellule.
        table.addCell("Type");
        table.addCell("Cible");
        table.addCell("Libelle");
        table.addCell("Categorie");
        table.addCell("Quantité");

        PdfPCell table_cell;

        while (rs.next()) {

            if (rs.getString("typeDon").equals("Nature")) {
                String type = rs.getString("typeDon");
                table_cell = new PdfPCell(new Phrase(type));
                table.addCell(table_cell);
                String cible = rs.getString("cibleDon");
                table_cell = new PdfPCell(new Phrase(cible));
                table.addCell(table_cell);
                String libelle = rs.getString("libelleDonNature");
                table_cell = new PdfPCell(new Phrase(libelle));
                table.addCell(table_cell);
                String categorie = rs.getString("categorieDonNature");
                table_cell = new PdfPCell(new Phrase(categorie));
                table.addCell(table_cell);
                int quantite = rs.getInt("quantiteDonNature");
                String quant =""+quantite+"";
                table_cell = new PdfPCell(new Phrase(quant));
                table.addCell(table_cell);

                
            }
           
        }


        return table;
    }
    
    public PdfPTable premierTableau1() throws SQLException {

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from don ");
        //On créer un objet table dans lequel on intialise ça taille.
        PdfPTable table = new PdfPTable(3);

        //On créer l'objet cellule.
        table.addCell("Type");
        table.addCell("Cible");
        table.addCell("Montant");
        

        PdfPCell table_cell;

        while (rs.next()) {

            if (rs.getString("typeDon").equals("Especes")) {
               
                 String type = rs.getString("typeDon");
                table_cell = new PdfPCell(new Phrase(type));
                table.addCell(table_cell);
                String cible = rs.getString("cibleDon");
                table_cell = new PdfPCell(new Phrase(cible));
                table.addCell(table_cell);
                int montant = rs.getInt("montantDon");
                String mont = ""+montant+"";
                table_cell = new PdfPCell(new Phrase(mont));
                table.addCell(table_cell);
                
            }
           
        }

        return table;
    }

    @FXML
    public void Statistique(ActionEvent action) throws IOException, SQLException {
        ServiceUser SU = new ServiceUser();
        ServiceDonNature SDN = new ServiceDonNature();
        ServiceDonEspeces SDE = new ServiceDonEspeces();
        Stage stage = new Stage();
        stage.setTitle("Statistique Don");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc
                = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Don");
        xAxis.setLabel("Type");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Nombre Don");
        series1.getData().add(new XYChart.Data(nature, SU.NombreDonNature()));
        series1.getData().add(new XYChart.Data(espece, SU.NombreDonEspece()));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("MDN Alimentaire");
        series2.getData().add(new XYChart.Data(nature, SDN.moyenneA()));
        //series2.getData().add(new XYChart.Data(espece, 9));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("MDN Vestimentaire");
        series3.getData().add(new XYChart.Data(nature, SDN.moyenneV()));
        //series2.getData().add(new XYChart.Data(espece, 9));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("MDN Autres");
        series4.getData().add(new XYChart.Data(nature, SDN.moyenneAutre()));

        XYChart.Series series5 = new XYChart.Series();
        series5.setName("MDE Montant");
        series5.getData().add(new XYChart.Data(espece, SDE.moyenneM()));

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1, series2, series3, series4, series5);
        stage.setScene(scene);
        stage.show();

//           Parent root = FXMLLoader.load(getClass().getResource("StatistiqueDon.fxml"));
//           Stage stage = new Stage();
//           stage.setScene(new Scene(root));
//           stage.initModality(Modality.APPLICATION_MODAL);
//           stage.show();
    }

    //* Debut Partie Chat *//
    private Server createServer() {
        return new Server(55555, data -> {
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }

    private Client createClient() {
        return new Client("127.0.0.1", 55555, data -> {
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }
    //* Fin Partie Chat *//

}
