/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Entities.Aide;
import Services.ServiceAide;

/**
 *
 * @author wajih
 */
public class Menu extends Application {
    private final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
    private BorderPane root;
    private PieChart pieChart;
    @Override
    public void start(Stage stage) throws IOException {
      //FXMLLoader Loader =  new FXMLLoader(getClass().getResource("InterBeneficiaire.fxml"));
      FXMLLoader Loader =  new FXMLLoader(getClass().getResource("Menu.fxml"));
       Parent root= Loader.load();
       /*
        details.addAll(new PieChart.Data("PROJECT",20),new PieChart.Data("NOUM",20));
        root=new BorderPane();
       */
                Scene scene = new Scene(root);
         /*    pieChart= new PieChart();
             pieChart.setData(details);
             pieChart.setLegendSide(Side.BOTTOM);
             pieChart.setLabelsVisible(true);
             root.setCenter(pieChart);
       */
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
