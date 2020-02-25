/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
