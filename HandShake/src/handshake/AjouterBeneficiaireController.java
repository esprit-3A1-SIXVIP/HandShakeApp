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
import java.util.List;
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
import Entities.Aide;
import Entities.Beneficiaire;
import Entities.Necessiteux;
import Entities.Refugie;
import Entities.SendMail;
import Entities.Pdf;
import Services.ServiceAide;
import Services.ServiceBeneficiaire;
import Services.ServiceNecessiteux;
import Services.ServiceRefugie;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class AjouterBeneficiaireController implements Initializable {
    private Statement ste;
    private Connection con;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField numtel;
    @FXML
    private TextField ville;
    @FXML
    private DatePicker date_naiss;
    @FXML
    private TextField adresseGPS;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button AjouterBeneficiaire;
    private TableColumn<Beneficiaire, Integer> idtab;
    private TableColumn<Beneficiaire, String> nomtab;
    private TableColumn<Beneficiaire, String> prenomtab;
    private TableColumn<Beneficiaire, String> emailtab;
    private TableColumn<Beneficiaire, Integer> numteltab;
    private TableColumn<Beneficiaire, String> villetab;
    private TableColumn<Beneficiaire, java.sql.Date> datenaisstab;
    private TableColumn<Beneficiaire, String> adressegpstab;
    private TableColumn<Beneficiaire, String> roletab;
    ServiceBeneficiaire stb = new ServiceBeneficiaire();
    
    ObservableList<String> list = FXCollections.observableArrayList("Refugie","Necessiteux");
    private final ObservableList<Beneficiaire> data = FXCollections.observableArrayList();
    ObservableList<Integer> listid = FXCollections.observableArrayList();
    private TableView<Beneficiaire> AffichageBeneficiaire;
    @FXML
    private Circle cercledon;
    @FXML
    private ComboBox<Integer> comboaide;
    @FXML
    private TextField tfbesoin;
    @FXML
    private TextField tfnationalite;


    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBox.setItems(list);
            tfbesoin.setDisable(true);
            tfnationalite.setDisable(true);

        try {
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterBeneficiaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    public void fillcombo() throws SQLException{
                ServiceAide ser = new ServiceAide();
        List<Aide> list = ser.readAll();
        ObservableList<Aide> cls = FXCollections.observableArrayList();
        for (Aide aux : list)
        {
          cls.add(new Aide(aux.getAideID()));  
          listid.add(aux.getAideID());
        }
        comboaide.setItems(listid);
    }
    
    
    @FXML
    private void AjouterBeneficiaire(MouseEvent event) throws SQLException {
                               con = DataBase.getInstance().getConnection();
             ste = con.createStatement();
             if(Validchamp(nom)&&Validchamp(prenom)&&Validchamp(ville)&&Validchamp(numtel)&&Validchamp(adresseGPS)&&isEmail(email)){ //&&isEmail(email)

                try {
                 int nmt= (int) Integer.valueOf(this.numtel.getText());
                DatePicker tmpdate=(DatePicker) date_naiss;
                String date= (String) tmpdate.getValue().toString();
                date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);                
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                ComboBox tmpcmb = (ComboBox) comboBox;
                
                    String role = (String) tmpcmb.getValue().toString();
                    if(comboBox.getValue().toString()=="Refugie")
                    {
                        ServiceRefugie ser =new ServiceRefugie();
                        ser.ajouter(new Refugie(tfnationalite.getText(),comboaide.getValue(),nom.getText(), prenom.getText(),email.getText(),sqlDate,ville.getText(),Integer.valueOf(numtel.getText()),adresseGPS.getText(),role));
                    }
                    else
                    {
                        ServiceNecessiteux ser =new ServiceNecessiteux();
                        ser.ajouter(new Necessiteux(tfbesoin.getText(),comboaide.getValue(),nom.getText(), prenom.getText(),email.getText(),sqlDate,ville.getText(),Integer.valueOf(numtel.getText()),adresseGPS.getText(),role));

                    }
                    SendMail.sendMail(email.getText(),"Beneficiaire","vous avez été ajouté ");

                }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
             }
                
                    
    }

    @FXML
    private void rolechanged(ActionEvent event) {
        
        String v = comboBox.getValue().toString();
        if(v=="Refugie")
        {
            tfbesoin.setDisable(true);
            tfnationalite.setDisable(false);
        }
        else if(v=="Necessiteux"){
            tfbesoin.setDisable(false);
            tfnationalite.setDisable(true);
        }
    }
    
    
       public static boolean isEmail(TextField field)  { // KeyPressed
        boolean is = false;
        if (!field.getText().isEmpty()) {
            if (field.getText().contains("@") && field.getText().contains(".") && !field.getText().contains(" ")) {

                String user = field.getText().substring(0, field.getText().lastIndexOf('@'));
                String domain = field.getText().substring(field.getText().lastIndexOf('@') + 1, field.getText().length());
                String subdomain = field.getText().substring(field.getText().indexOf(".") + 1, field.getText().length());

                if ((user.length() >= 1) && (!user.contains("@")) &&
                        (domain.contains(".")) && (!domain.contains("@"))
                        && (domain.indexOf(".") >= 1)
                        && (domain.lastIndexOf(".") < domain.length() - 1)
                        && subdomain.length() >= 2) {
                    is = true;
                }
            }
        }
        return is;
    }
    
}
