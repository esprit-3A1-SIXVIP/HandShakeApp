
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Admin;
import Entities.Dons;
import Services.ServiceAdmin;
import Services.ServiceDonEspeces;
import Services.ServiceDonNature;
import Services.ServiceRefuge;
import Utils.DataBase;
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
import java.time.LocalDate;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.sql.ResultSet;
import javafx.application.Platform;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

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
    final static String refuge= "Refuge";


    public static final String chemin = "C:\\TableDon.pdf";

    @FXML
    private AnchorPane rootPane;


    @FXML
    private JFXButton supprimerD;

    @FXML
    private JFXButton buttonPdf;

    @FXML
    private JFXButton Statistique;
    
    
    private JFXButton Statistiqueref;

    @FXML
    private JFXTextField rechercheD;

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
    private TableColumn<Dons, String> cap;
    @FXML
    private TableColumn<Dons, String> ville;
    @FXML
    private TableColumn<Dons, String> pays;
    @FXML
    private TableColumn<Dons, LocalDate> date_debut;
    @FXML
    private TableColumn<Dons, LocalDate> date_fin;
    @FXML
    private TableColumn<Dons, Double> longitude;
    @FXML
    private TableColumn<Dons, Double> latitude;


    @FXML
    private TableView<Dons> tableDon;
  
    @FXML
    private JFXButton benef;

    ObservableList<Dons> donList = FXCollections.observableArrayList();
    

     private boolean isServer = true;
    
     @FXML
    private JFXTextArea messages = new JFXTextArea();
     
    private NetworkConnection connection = isServer ? createServer() : createClient();

    @FXML
    private Label btnlogout;
    @FXML
    private AnchorPane tablebord;
    @FXML
    private JFXButton btnmaguser;
    @FXML
    private JFXButton btnmdon;
    @FXML
    private JFXButton btnArticles;
    @FXML
    private JFXButton btnshakehub;
    private Circle profile_admin;
    @FXML
    private Circle profile_admin1;
    @FXML
    private JFXButton benef1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        con = DataBase.getInstance().getConnection();
        ServiceUser SU = new ServiceUser();
        int us = UserSession.getInstance().getId();
        String email = UserSession.getInstance().getEmail();
        String username = UserSession.getInstance().getusername();
        con = DataBase.getInstance().getConnection();
               ServiceAdmin sa = new ServiceAdmin();
                int id = UserSession.getInstance().getId();
              Admin a;
        try {
            a = sa.chercherAdmin(id);
            Image I=null;
            chargerimagecircle(I, profile_admin ,a.getProfil());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                 
        try {
            donList = (ObservableList<Dons>) SU.readAllDonAdmin();
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
        } catch (ParseException ex) {

            System.out.println(ex.getMessage());
        }
        
        donId.setCellValueFactory(new PropertyValueFactory<>("donId"));
        typeD.setCellValueFactory(new PropertyValueFactory<>("typeDon"));
        cibleD.setCellValueFactory(new PropertyValueFactory<>("cibleDon"));
        montantD.setCellValueFactory(new PropertyValueFactory<>("montantDon"));
        libelleD.setCellValueFactory(new PropertyValueFactory<>("libelleDonNature"));
        categorieD.setCellValueFactory(new PropertyValueFactory<>("categorieDonNature"));
        quantiteD.setCellValueFactory(new PropertyValueFactory<>("quantiteDonNature"));
        dateD.setCellValueFactory(new PropertyValueFactory<>("dateDon"));
        cap.setCellValueFactory(new PropertyValueFactory<>("capaciteRefuge"));
        pays.setCellValueFactory(new PropertyValueFactory<>("paysRefuge"));
        ville.setCellValueFactory(new PropertyValueFactory<>("villeRefuge"));
        longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        date_debut.setCellValueFactory(new PropertyValueFactory<Dons,LocalDate>("dateDebutRefuge"));
        date_fin.setCellValueFactory(new PropertyValueFactory<Dons,LocalDate>("dateFinRefuge"));

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
            String message = isServer ? ""+username+"(Admin) : " : "Client : ";
            message += input.getText();
            input.clear();
            
            messages.appendText(message + "\n");
            
            try{
                connection.send(message);
            }
            catch(Exception e){
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
       
    public void scrollbar(TableView table) {
        ScrollPane sp = new ScrollPane(table);
        sp.setContent(table);
        sp.setPrefSize(2000, 2000);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setFitToHeight(true);
        sp.setHmax(3);
        sp.setHvalue(0);
        sp.setDisable(true);

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


    @FXML
   public void Imprimer(ActionEvent action) throws SQLException{

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
        ServiceRefuge SDR=new ServiceRefuge();

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
         series1.getData().add(new XYChart.Data(refuge, SU.NombreDonRefuge()));


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
        

        XYChart.Series series6 = new XYChart.Series();
        series5.setName("NBR Refuge");
        series5.getData().add(new XYChart.Data(refuge, SDR.moyenneR()));
        
        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1, series2,series3,series4,series5,series6);
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
        return new Client("127.0.0.1",55555, data -> {
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }
    //* Fin Partie Chat *//
    @FXML
    private void beneficiaire(ActionEvent event) {
        loadStage("InterBeneficiaire.fxml");
    }
    
    private void Statistiqueref(ActionEvent event) {
        
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
    private void gestionnaire(ActionEvent event) {
        loadStage("gestionnaire.fxml");
    }

    @FXML
    private void articles(ActionEvent event) {
        loadStage("articleAdmin.fxml");
    }
    private void logout(MouseEvent event) {
       UserSession.getInstance().cleanUserSession();
        loadStage("login2.fxml");
    }

    @FXML
    private void evenements(ActionEvent event) {
        loadStage("evenement.fxml");
    }

}

