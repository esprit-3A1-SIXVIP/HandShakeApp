/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author steph
 */
public class HandShake extends Application{
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login2.fxml"));
            Scene scene = new Scene(root);
           
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HandShake.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
//        ServiceDonEspeces ser = new ServiceDonEspeces();
//        ServiceDonNature sN = new ServiceDonNature();
//        ServiceUser Su = new ServiceUser();
//        
//        User u1 = new User("lelouch", "1234", "lelouch", "lelouch", "lelouchlelouch9@gmail.com", 56150020, "Ariana","tete1", "Tunisie", "User");
//        
//       
//        
//       // DonEspeces de1 = new DonEspeces(10,  "kkartier",);
////       
////        
//        
//        
//        try
//        {
//            int u = Su.getIdUser(u1);
//            List<Dons> list = Su.readAllDon(u);
//            System.out.println(list);
//           // DonNature dn2 = new DonNature("Organisation", "blablabla3", "Sac de riz", 30,u);
//           //  DonEspeces de2 = new DonEspeces(20,"lieuA",u);
//            //Su.ajouter(u1);
////            List<Dons> list =  Su.recherche("Nature","et");
////            System.out.println(list);
//           
////            ser.ajouter(de1);
////            ser.ajouter(de2);
////           List<DonEspeces> list = ser.readAll();
////            System.out.println(list);
//            
////             sN.ajouter(dn1);
////             sN.ajouter(dn2);
////           sN.update(27,"Patebi","okokokokok","Patate", 100);
//           
//            
//    
////            List<DonNature> listN = sN.readAll();
////            System.out.println(listN);
//            
////            //ser.update(1,"modifier",100);
////            
////             List<DonEspeces> list1 = ser.readAll();
////            System.out.println(list1);
//            
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
    }

   
    
}
