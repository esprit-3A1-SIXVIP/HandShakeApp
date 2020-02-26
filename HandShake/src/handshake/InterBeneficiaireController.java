
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.converter.IntegerStringConverter;
import Utils.DataBase;
import Entities.Beneficiaire;
import Entities.Necessiteux;
import Entities.Refugie;
import Entities.SendMail;
import Services.ServiceBeneficiaire;
import Services.ServiceNecessiteux;
import Services.ServiceRefugie;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class InterBeneficiaireController implements Initializable {

    private Statement ste;
    private Connection con;

    @FXML
    private TableColumn<Refugie, Integer> idtab;
    @FXML
    private TableColumn<Refugie, Integer> idaidetab;

    @FXML
    private TableColumn<Refugie, String> nomtab;
    @FXML
    private TableColumn<Refugie, String> prenomtab;
    @FXML
    private TableColumn<Refugie, String> emailtab;
    @FXML
    private TableColumn<Refugie, Integer> numteltab;
    @FXML
    private TableColumn<Refugie, String> villetab;
    @FXML
    private TableColumn<Refugie, java.sql.Date> datenaisstab;
    @FXML
    private TableColumn<Refugie, String> adressegpstab;
    @FXML
    private TableColumn<Refugie, String> roletab;
    @FXML
    private TableColumn<Refugie, String> nationalitetab;

    @FXML
    private TextField recherche;
    ServiceRefugie stb = new ServiceRefugie();
    ServiceNecessiteux sn = new ServiceNecessiteux();
    
    private final ObservableList<Refugie> cls = FXCollections.observableArrayList();
            ObservableList<Necessiteux> clss = FXCollections.observableArrayList();

    private final ObservableList<Necessiteux> data1 = FXCollections.observableArrayList();
    @FXML
    private TableView<Refugie> AffichageBeneficiaire;
    @FXML
    private Circle cercledon;
    @FXML
    private Button Supprimer;

    @FXML
    private TableView<Necessiteux> AffichageBeneficiaire1;
    @FXML
    private TableColumn<Necessiteux, Integer> idtab1;
    @FXML
    private TableColumn<Necessiteux, Integer> idaidetab1;
    @FXML
    private TableColumn<Necessiteux, String> nomtab1;
    @FXML
    private TableColumn<Necessiteux, String> prenomtab1;
    @FXML
    private TableColumn<Necessiteux, String> emailtab1;
    @FXML
    private TableColumn<Necessiteux, Integer> numteltab1;
    @FXML
    private TableColumn<Necessiteux, String> villetab1;
    @FXML
    private TableColumn<Necessiteux, java.sql.Date> datenaisstab1;
    @FXML
    private TableColumn<Necessiteux, String> adressegpstab1;
    @FXML
    private TableColumn<Necessiteux, String> roletab1;
    @FXML
    private TableColumn<Necessiteux, String> besointab;
    @FXML
    private TextField recherche1;
    @FXML
    private Circle cercledon1;
    @FXML
    private Button Supprimer1;
    @FXML
    PieChart bookChart;
    @FXML
    private ImageView home1;
    @FXML
    private AnchorPane rootPane;
      public void Aff(){
                        try {
                        cls.clear();
                        
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();

        ServiceRefugie ser = new ServiceRefugie();
        List<Refugie> list = ser.readr();
        for (Refugie aux : list)
        {
          cls.add(new Refugie(aux.getNationalite(),aux.getBeneficiaireId(), aux.getAideId(), aux.getNomBeneficiaire(),aux.getPrenomBeneficiaire(),aux.getEmail(),aux.getDateNaissance(),aux.getVille(),aux.getTelephone(),aux.getAdresseGPS(),aux.getRole()));  
        }
            idtab.setCellValueFactory(new PropertyValueFactory<>("beneficiaireId"));
            idaidetab.setCellValueFactory(new PropertyValueFactory<>("aideId"));
            nomtab.setCellValueFactory(new PropertyValueFactory<>("nomBeneficiaire"));
            prenomtab.setCellValueFactory(new PropertyValueFactory<>("prenomBeneficiaire"));
            emailtab.setCellValueFactory(new PropertyValueFactory<>("email"));
            datenaisstab.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
            villetab.setCellValueFactory(new PropertyValueFactory<>("ville"));
            numteltab.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            adressegpstab.setCellValueFactory(new PropertyValueFactory<>("adresseGPS"));
            roletab.setCellValueFactory(new PropertyValueFactory<>("role"));
            nationalitetab.setCellValueFactory(new PropertyValueFactory<>("nationalite"));

            AffichageBeneficiaire.setItems(cls);
            AffichageBeneficiaire.setEditable(true);
            nomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            prenomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            emailtab.setCellFactory(TextFieldTableCell.forTableColumn());
            numteltab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            villetab.setCellFactory(TextFieldTableCell.forTableColumn());
            adressegpstab.setCellFactory(TextFieldTableCell.forTableColumn());
            nationalitetab.setCellFactory(TextFieldTableCell.forTableColumn());
        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               


    }
            public void Aff1(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        clss.clear();

        ServiceNecessiteux ser = new ServiceNecessiteux();
        List<Necessiteux> list = ser.readn();
        for (Necessiteux aux : list)
        {
          clss.add(new Necessiteux(aux.getBesoin(),aux.getBeneficiaireId(), aux.getAideId(), aux.getNomBeneficiaire(),aux.getPrenomBeneficiaire(),aux.getEmail(),aux.getDateNaissance(),aux.getVille(),aux.getTelephone(),aux.getAdresseGPS(),aux.getRole()));  
        }
             
            idtab1.setCellValueFactory(new PropertyValueFactory<>("beneficiaireId"));
            idaidetab1.setCellValueFactory(new PropertyValueFactory<>("aideId"));
            nomtab1.setCellValueFactory(new PropertyValueFactory<>("nomBeneficiaire"));
            prenomtab1.setCellValueFactory(new PropertyValueFactory<>("prenomBeneficiaire"));
            emailtab1.setCellValueFactory(new PropertyValueFactory<>("email"));
            datenaisstab1.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
            villetab1.setCellValueFactory(new PropertyValueFactory<>("ville"));
            numteltab1.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            adressegpstab1.setCellValueFactory(new PropertyValueFactory<>("adresseGPS"));
            roletab1.setCellValueFactory(new PropertyValueFactory<>("role"));
            besointab.setCellValueFactory(new PropertyValueFactory<>("besoin"));

            AffichageBeneficiaire1.setItems(clss);
            AffichageBeneficiaire1.setEditable(true);
            nomtab1.setCellFactory(TextFieldTableCell.forTableColumn());
            prenomtab1.setCellFactory(TextFieldTableCell.forTableColumn());
            emailtab1.setCellFactory(TextFieldTableCell.forTableColumn());
            numteltab1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            villetab1.setCellFactory(TextFieldTableCell.forTableColumn());
            adressegpstab1.setCellFactory(TextFieldTableCell.forTableColumn());
            besointab.setCellFactory(TextFieldTableCell.forTableColumn());

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
          

    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        Aff1();
       RechercheAV();
       RechercheAV1();
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Necessiteux", sn.calculer()),
                new PieChart.Data("Refugie", stb.calculer())
        );
        bookChart.setData(pieChartData);
        bookChart.setClockwise(false);

    }
    
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Refugie> filteredData = new FilteredList<>(cls, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Refugie -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Refugie.getNomBeneficiaire().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Refugie.getPrenomBeneficiaire().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Refugie.getBeneficiaireId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredLisat in a SortedList. 
		SortedList<Refugie> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(AffichageBeneficiaire.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AffichageBeneficiaire.setItems(sortedData);
    }

    public void RechercheAV1(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Necessiteux> filteredData1 = new FilteredList<>(clss, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche1.textProperty().addListener((observable1, oldValue1, newValue1) -> {
			filteredData1.setPredicate(Necessiteux -> {
				// If filter text is empty, display all persons.
								
				if (newValue1 == null || newValue1.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter1 = newValue1.toLowerCase();
				
				if (Necessiteux.getNomBeneficiaire().toLowerCase().indexOf(lowerCaseFilter1) != -1 ) {
					return true; // Filter matches first name.
				} else if (Necessiteux.getPrenomBeneficiaire().toLowerCase().indexOf(lowerCaseFilter1) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Necessiteux.getBeneficiaireId()).indexOf(lowerCaseFilter1)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredLisat in a SortedList. 
		SortedList<Necessiteux> sortedData1 = new SortedList<>(filteredData1);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(AffichageBeneficiaire1.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AffichageBeneficiaire1.setItems(sortedData1);
    }


    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                 AffichageBeneficiaire.setItems(cls);

             ObservableList<Refugie> allDemandes,SingleDemandes ;
             allDemandes=AffichageBeneficiaire.getItems();
             SingleDemandes=AffichageBeneficiaire.getSelectionModel().getSelectedItems();
             Refugie A = SingleDemandes.get(0);
             ServiceRefugie STD = new ServiceRefugie(); // STD = Service TAB DEMANDE
             STD.delete(A.getBeneficiaireId());
             SingleDemandes.forEach(allDemandes::remove);
             Aff();
             RechercheAV();
    }

    @FXML
    private void Change_NomR(TableColumn.CellEditEvent bb) throws SQLException{
     Refugie beneficiaireselected = AffichageBeneficiaire.getSelectionModel().getSelectedItem();
     beneficiaireselected.setNomBeneficiaire(bb.getNewValue().toString());
     stb.updatetab(beneficiaireselected);
 }

    @FXML
    private void Change_PrenomR(TableColumn.CellEditEvent bb) throws SQLException{
     Refugie beneficiaireselected = AffichageBeneficiaire.getSelectionModel().getSelectedItem();
     beneficiaireselected.setPrenomBeneficiaire(bb.getNewValue().toString());
     stb.updatetab(beneficiaireselected);
 }


    @FXML
    private void Change_EmailR(TableColumn.CellEditEvent bb) throws SQLException{
     Refugie beneficiaireselected = AffichageBeneficiaire.getSelectionModel().getSelectedItem();
     beneficiaireselected.setEmail(bb.getNewValue().toString());
     stb.updatetab(beneficiaireselected);
 }

    @FXML
    private void Change_NumtelR(TableColumn.CellEditEvent bb) throws SQLException{
     Refugie beneficiaireselected = AffichageBeneficiaire.getSelectionModel().getSelectedItem();
     beneficiaireselected.setTelephone(Integer.parseInt(bb.getNewValue().toString()));
     stb.updatetab(beneficiaireselected);
 }


    @FXML
    private void Change_VilleR(TableColumn.CellEditEvent bb) throws SQLException{
     Refugie beneficiaireselected = AffichageBeneficiaire.getSelectionModel().getSelectedItem();
     beneficiaireselected.setVille(bb.getNewValue().toString());
     stb.updatetab(beneficiaireselected);
 }

    @FXML
    private void Change_AdressGPSR(TableColumn.CellEditEvent bb) throws SQLException{
     Refugie beneficiaireselected = AffichageBeneficiaire.getSelectionModel().getSelectedItem();
     beneficiaireselected.setAdresseGPS(bb.getNewValue().toString());
     stb.updatetab(beneficiaireselected);
 }


    @FXML
    private void Change_NationaliteR(TableColumn.CellEditEvent bb) throws SQLException{
     Refugie beneficiaireselected = AffichageBeneficiaire.getSelectionModel().getSelectedItem();
     beneficiaireselected.setNationalite(bb.getNewValue().toString());
     stb.updatetab(beneficiaireselected);
 }

    @FXML
    private void Change_NomN(TableColumn.CellEditEvent bb) throws SQLException{
     Necessiteux beneficiaireselected = AffichageBeneficiaire1.getSelectionModel().getSelectedItem();
     beneficiaireselected.setNomBeneficiaire(bb.getNewValue().toString());
     sn.updatetab(beneficiaireselected);
 }

    @FXML
    private void Change_PrenomN(TableColumn.CellEditEvent bb) throws SQLException{
     Necessiteux beneficiaireselected = AffichageBeneficiaire1.getSelectionModel().getSelectedItem();
     beneficiaireselected.setPrenomBeneficiaire(bb.getNewValue().toString());
     sn.updatetab(beneficiaireselected);
 }

    @FXML
    private void Change_EmailN(TableColumn.CellEditEvent bb) throws SQLException{
     Necessiteux beneficiaireselected = AffichageBeneficiaire1.getSelectionModel().getSelectedItem();
     beneficiaireselected.setEmail(bb.getNewValue().toString());
     sn.updatetab(beneficiaireselected);
 }


    @FXML
    private void Change_NumtelN(TableColumn.CellEditEvent bb) throws SQLException{
     Necessiteux beneficiaireselected = AffichageBeneficiaire1.getSelectionModel().getSelectedItem();
     beneficiaireselected.setTelephone(Integer.parseInt(bb.getNewValue().toString()));
     sn.updatetab(beneficiaireselected);
 }

    @FXML
    private void Change_VilleN(TableColumn.CellEditEvent bb) throws SQLException{
     Necessiteux beneficiaireselected = AffichageBeneficiaire1.getSelectionModel().getSelectedItem();
     beneficiaireselected.setVille(bb.getNewValue().toString());
     sn.updatetab(beneficiaireselected);
 }


    @FXML
    private void Change_AdressGPSN(TableColumn.CellEditEvent bb) throws SQLException{
     Necessiteux beneficiaireselected = AffichageBeneficiaire1.getSelectionModel().getSelectedItem();
     beneficiaireselected.setAdresseGPS(bb.getNewValue().toString());
     sn.updatetab(beneficiaireselected);
 }


    @FXML
    private void Change_BesoinN(TableColumn.CellEditEvent bb) throws SQLException{
     Necessiteux beneficiaireselected = AffichageBeneficiaire1.getSelectionModel().getSelectedItem();
     beneficiaireselected.setBesoin(bb.getNewValue().toString());
     sn.updatetab(beneficiaireselected);
 }

    @FXML
    private void ButtonSupprimer1(ActionEvent event) throws SQLException {
                         AffichageBeneficiaire1.setItems(clss);

             ObservableList<Necessiteux> allDemandes,SingleDemandes ;
             allDemandes=AffichageBeneficiaire1.getItems();
             SingleDemandes=AffichageBeneficiaire1.getSelectionModel().getSelectedItems();
             Necessiteux A = SingleDemandes.get(0);
             ServiceNecessiteux STD = new ServiceNecessiteux(); // STD = Service TAB DEMANDE
             STD.delete(A.getBeneficiaireId());
             SingleDemandes.forEach(allDemandes::remove);
             Aff1();
             RechercheAV1();
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
        loadStage("gestionnaire.fxml");
    }



   
    
}
