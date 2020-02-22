/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Question;
import Entities.User;
import Services.CommentaireService;
import Services.QuestionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ghost
 */
public class AfficheShakeHubController implements Initializable {
    @FXML
    private ImageView handshake;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ListView<Question> list;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Button AddQuestion;
    @FXML
    private Label logintext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            logintext.setText("Connected as ");
            logintext.setVisible(true);
      
        QuestionService QS=new QuestionService();
        CommentaireService CS= new CommentaireService();
        try {
            List<Question> LQ= QS.readAll();
            LQ.stream().forEach((Q) -> {
                list.getItems().add(Q);
                
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
    }
    @FXML
    private void addQuestion(ActionEvent event) {
        
    }
    private void loadStage(String fxml) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));
            rootPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

  

   

    public ImageView getHandshake() {
        return handshake;
    }

    public void setHandshake(ImageView handshake) {
        this.handshake = handshake;
    }

    public ListView<Question> getList() {
        return list;
    }

    public void setList(ListView<Question> list) {
        this.list = list;
    }

    public ScrollPane getScrollpane() {
        return scrollpane;
    }

    public void setScrollpane(ScrollPane scrollpane) {
        this.scrollpane = scrollpane;
    }

    public Label getLogintext() {
        return logintext;
    }
    
}
