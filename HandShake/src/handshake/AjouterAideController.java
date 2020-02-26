/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import Utils.DataBase;
import Entities.Aide;
import Entities.Pdf;
import Services.ServiceAide;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class AjouterAideController implements Initializable {
        private Statement ste;
    private Connection con;
    @FXML
    private TextField descriptionAide;
    @FXML
    private TextField localisationAide;
    @FXML
    private TextField categorieAide;
        @FXML
    private TableView<Aide> AffichageTabAide;
    @FXML
    private TableColumn<Aide, Integer> idtab;
    @FXML
    private TableColumn<Aide, String> descriptiontab;
    @FXML
    private TableColumn<Aide, String> localisationtab;
    @FXML
    private TableColumn<Aide, String> categorietab;
    @FXML
    private TextField recherche;
    private final ObservableList<Aide> data = FXCollections.observableArrayList();
        ServiceAide SA = new ServiceAide(); 
    @FXML
    private Button AjouterAide;
    @FXML
    private Circle cercledon;
    @FXML
    private Circle cercledon1;
    @FXML
    private Button AjouterAide1;
    @FXML
    private ImageView home1;
    @FXML
    private AnchorPane rootPane;
        private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                Aff();
        RechercheAV();

    }    
     public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from Aide");
            while(rs.next()){
                data.add(new Aide(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            idtab.setCellValueFactory(new PropertyValueFactory<>("aideID"));
            descriptiontab.setCellValueFactory(new PropertyValueFactory<>("descriptionAide"));
            localisationtab.setCellValueFactory(new PropertyValueFactory<>("localisationAide"));
            categorietab.setCellValueFactory(new PropertyValueFactory<>("categorieAide"));

            AffichageTabAide.setItems(data);
            AffichageTabAide.setEditable(true);
            descriptiontab.setCellFactory(TextFieldTableCell.forTableColumn());
            localisationtab.setCellFactory(TextFieldTableCell.forTableColumn());
            categorietab.setCellFactory(TextFieldTableCell.forTableColumn());

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Aide> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(aide -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (aide.getCategorieAide().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(aide.getAideID()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Aide> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(AffichageTabAide.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AffichageTabAide.setItems(sortedData);
    }
      
//      private void interbenif(ActionEvent event) {
//        if (event.getSource() == AjouterAide) {
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("signaler une aide");
//            alert.setHeaderText("Aide signalée");
//            alert.setContentText("Choisir votre option.");
//
//            ButtonType buttonTypeOne = new ButtonType("ok");
//            ButtonType buttonTypeTwo = new ButtonType("Beneficaire");
//           
//            
//           
//            alert.getButtonTypes().setAll(buttonTypeOne);
//
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == buttonTypeOne) {
//                loadStage("AjouterAide.fxml");
//                
//            } else if (result.get() == buttonTypeTwo) {
//                
//                loadStage("AjouterBeneficiaire.fxml");
//            }
//
//        }
//        
//    }
      
      
      
      
      
      
    @FXML
    private void AjouterAide(MouseEvent event) throws SQLException {
        
        
        
        
                                       con = DataBase.getInstance().getConnection();
             ste = con.createStatement();
                
         if(Validchamp(descriptionAide)&&Validchamp(localisationAide)&&Validchamp(categorieAide)){

                try {
                    ServiceAide ser =new ServiceAide();
                    ser.ajouter(new Aide(descriptionAide.getText(),localisationAide.getText(),categorieAide.getText()));
                    Aff();
                    RechercheAV();
                                            Pdf p = new Pdf();
            try {
                p.add("Categorie",descriptionAide.getText(),localisationAide.getText(),categorieAide.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjouterAideController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AjouterAideController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(AjouterAideController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }

         }
    }
    @FXML
    private void Change_Description(TableColumn.CellEditEvent bb) throws SQLException{
     Aide aideselected = AffichageTabAide.getSelectionModel().getSelectedItem();
     aideselected.setDescriptionAide(bb.getNewValue().toString());
     SA.updatetab(aideselected);
 }

    @FXML
    private void Change_Localisation(TableColumn.CellEditEvent bb) throws SQLException{
     Aide aideselected = AffichageTabAide.getSelectionModel().getSelectedItem();
     aideselected.setLocalisationAide(bb.getNewValue().toString());
     SA.updatetab(aideselected);
    }

    @FXML
    private void Change_Categorie(TableColumn.CellEditEvent bb) throws SQLException{
     Aide aideselected = AffichageTabAide.getSelectionModel().getSelectedItem();
     aideselected.setCategorieAide(bb.getNewValue().toString());
     SA.updatetab(aideselected);
    }

    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                         AffichageTabAide.setItems(data);

             ObservableList<Aide> allDemandes,SingleDemandes ;
             allDemandes=AffichageTabAide.getItems();
             SingleDemandes=AffichageTabAide.getSelectionModel().getSelectedItems();
             Aide A = SingleDemandes.get(0);
             ServiceAide STD = new ServiceAide(); // STD = Service TAB DEMANDE
             SA.delete(A.getAideID());
             SingleDemandes.forEach(allDemandes::remove);
             RechercheAV();

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
    private void home(MouseEvent event) {
        loadStage("Home.fxml");
    }

    
}
