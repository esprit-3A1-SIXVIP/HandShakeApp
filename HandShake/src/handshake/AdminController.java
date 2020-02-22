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
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

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
    public static final String chemin="C:\\Users\\steph\\OneDrive\\Documents\\TableDon.pdf";

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        con = DataBase.getInstance().getConnection();
        ServiceUser SU = new ServiceUser();
        int us = UserSession.getInstance().getId();
        String email = UserSession.getInstance().getEmail();
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
        
        FilteredList<Dons> filteredData = new FilteredList<>(donList, b -> true);
        rechercheD.textProperty().addListener((observable,oldValue,newValue) ->{
            
            filteredData.setPredicate( (Dons don) -> {
                
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                    
                    if(don.getCibleDon().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }else if(don.getTypeDon().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }else
                        return false;
                
            });
            
        });
        
        SortedList<Dons> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableDon.comparatorProperty());
        
        tableDon.setItems(sortedData);
        
        

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

        alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeOne1);
        

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {

//            ObservableList<Dons> donSelected, allDon;
//            allDon = tableDon.getItems();
//            donSelected = tableDon.getSelectionModel().getSelectedItems();
//            donSelected.forEach(allDon::remove);

            
            
            int index = tableDon.getSelectionModel().getSelectedIndex();
            String type = typeD.getCellData(index);
            System.out.println(type);
            if(type.equals("Nature"))
            {
                int id = donId.getCellData(index);
                
                ServiceDonNature SN = new ServiceDonNature();
                SN.delete(id);
                donList.removeAll(tableDon.getSelectionModel().getSelectedItems());
            tableDon.getSelectionModel().clearSelection();
            }
            else
            {
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

    
    
    public void Imprimer(ActionEvent action) 
    {
        
        
        Document document = new Document();
    try 
    {
      PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(chemin));
      document.open();
      
      document.add(new Paragraph("Historique de Don\n\n"));
      document.add(premierTableau());

    } catch (DocumentException | IOException de) {
      de.printStackTrace();
    }
   
    document.close();
         
        
    }
    
    public static PdfPTable premierTableau()
  {
      //On créer un objet table dans lequel on intialise ça taille.
      PdfPTable table = new PdfPTable(7);
      
      //On créer l'objet cellule.
      table.addCell("Type");
      table.addCell("Cible");
      table.addCell("Montant");
      table.addCell("Libelle");
      table.addCell("Categorie");
      table.addCell("Quantité");
      table.addCell("Date");
      
      
      
      
//      cell = new PdfPCell(new Phrase("Fusion de chaque première cellule de chaque colonne"));
//      cell.setColspan(3);
//      table.addCell(cell);
// 
//      cell = new PdfPCell(new Phrase("Fusion de 2 cellules de la première colonne"));
//      cell.setRowspan(2);
//      table.addCell(cell);
// 
//      //contenu du tableau.
//      table.addCell("Colonne 1; Cellule 1");
//      table.addCell("Colonne 1; Cellule 2");
//      table.addCell("Colonne 2; Cellule 1");
//      table.addCell("Colonne 2; Cellule 2");
//      
      return table;  
  }
    
}
