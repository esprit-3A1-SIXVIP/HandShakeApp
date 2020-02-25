/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Commentaire;
import Entities.Evenement;
import Entities.Question;
import Entities.User;
import Services.CommentaireService;
import Services.QuestionService;
import Utils.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ghost
 */
public class ShakeHubController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label username;
    @FXML
    private JFXButton homeButton;
    @FXML
    private JFXButton AddQuestion;
    @FXML
    private JFXTextField rechercheD;
    @FXML
    private ListView<Question> tableQuestions;
    private QuestionService QS = new QuestionService();
    @FXML
    private ImageView userImage;
    @FXML
    private JFXButton shakestatsButton;
    @FXML
    private JFXTextArea questionTA;

    public class CCell extends ListCell<Commentaire> {
        @FXML
        private Label score;
        @FXML
        private JFXButton shakeup;
        @FXML
        private JFXButton shakedown;
        
        @FXML
        private Label TextLabel;

        @FXML
        private Label userLabel;

        @FXML
        private Label dateLabel;

        @FXML
        private JFXButton modifierCommentaire;
        @FXML
        private JFXButton supprimerCommentaire;
        private CommentaireService sc= new CommentaireService();

        public CCell() {
            loadFXML();
        }

        private void loadFXML() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CCell.fxml"));
                loader.setController(this);
                loader.setRoot(this);
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void updateItem(Commentaire item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            } else {
                score.setText(""+item.getScore());
                TextLabel.setText(item.getTexteCommentaire());
                userLabel.setText(" Par '" + item.getUser().getLogin());
                dateLabel.setText("' le '" + item.getDateCommentaire().toString() + "'");
                if (UserSession.getU().equals(item.getUser())) {
                    modifierCommentaire.setVisible(true);
                    supprimerCommentaire.setVisible(true);
                    modifierCommentaire.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                    supprimerCommentaire.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Alert A = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer", ButtonType.APPLY);
                            A.setContentText("Voulez vous vraiment supprimer votre commentaire?");
                            A.showAndWait();
                            if (A.getResult() == ButtonType.APPLY) {
                                try {
                                    sc.delete(item);
                                } catch (SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                loadStage("ShakeHub.fxml");
                            }
                        }
                    });
                } else {
                    modifierCommentaire.setVisible(false);
                    supprimerCommentaire.setVisible(false);

                }
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    public class QCell extends ListCell<Question> {
         @FXML
        private Label score;
        @FXML
        private Label TextLabel;

        @FXML
        private Label userLabel;
            
        @FXML
        private JFXButton shakeup;
        @FXML
        private JFXButton shakedown;
        
        @FXML
        private Label dateLabel;
        @FXML
        private JFXButton modifierQuestion;
        @FXML
        private JFXButton supprimerQuestion;
        @FXML
        private JFXButton postcomment;
        @FXML
        private JFXTextArea commentaireTA;
        @FXML
        private ListView list;
        ObservableList<Commentaire> commentlist = FXCollections.observableArrayList();
        CommentaireService sc = new CommentaireService();

        public QCell() {
            loadFXML();
        }

        private void loadFXML() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("QCell.fxml"));
                loader.setController(this);
                loader.setRoot(this);
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void updateItem(Question item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            } else {
                try {

                    this.commentlist = sc.readAll(item);

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                score.setText(""+item.getScore());
                this.TextLabel.setText(item.getTexteQuestion());
                this.userLabel.setText(" Par '" + item.getUser().getLogin());
                this.dateLabel.setText("' Le '" + item.getDateQuestion().toString() + "'");
                if (UserSession.getU().equals(item.getUser())) {
                    modifierQuestion.setVisible(true);

                    if (this.commentlist.isEmpty()) {
                        supprimerQuestion.setVisible(true);
                    } else {
                        supprimerQuestion.setVisible(false);
                    }
                    modifierQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                    supprimerQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Alert A = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer", ButtonType.APPLY);
                            A.setContentText("Voulez vous vraiment supprimer votre question?");
                            A.showAndWait();
                            if (A.getResult() == ButtonType.APPLY) {
                                try {
                                    QS.delete(item);
                                } catch (SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                loadStage("ShakeHub.fxml");
                            }
                        }
                    });

                } else {
                    modifierQuestion.setVisible(false);
                    supprimerQuestion.setVisible(false);

                }

                Callback<ListView<Commentaire>, ListCell<Commentaire>> cellFactoryC = (param) -> {
                    return new CCell();
                };

                list.setCellFactory(cellFactoryC);

                if (!this.commentlist.isEmpty()) {
                    list.setItems(commentlist);
                }
                postcomment.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            Alert A = new Alert(Alert.AlertType.CONFIRMATION, "Publier", ButtonType.APPLY);
            A.setContentText("Voulez vous vraiment publier cette question? Merci de vérifier que votre question respecte nos règles avant de la publier!");
            A.showAndWait();
            if (A.getResult() == ButtonType.APPLY) {
                            try {
                                sc.ajouter(new Commentaire(UserSession.getU(),item, commentaireTA.getText(), date ));
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                loadStage("ShakeHub.fxml");
            }
                    }
                });
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Callback<ListView<Question>, ListCell<Question>> cellFactory = (param) -> {
                return new QCell();
            };

            tableQuestions.setCellFactory(cellFactory);
            tableQuestions.setItems(QS.readAll());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void addQuestion(ActionEvent event) throws SQLException {
        if (questionTA.getText().length() >= 15) {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            Alert A = new Alert(Alert.AlertType.CONFIRMATION, "Publier", ButtonType.APPLY);
            A.setContentText("Voulez vous vraiment publier cette question? Merci de vérifier que votre question respecte nos règles avant de la publier!");
            A.showAndWait();
            if (A.getResult() == ButtonType.APPLY) {
                QS.ajouter(new Question(0, questionTA.getText(), date, UserSession.getU()));
                loadStage("ShakeHub.fxml");
            }
        } else {
            Alert A = new Alert(Alert.AlertType.ERROR);
            A.setContentText("Votre Question doit comporter 150 caractères minimum.");
            A.showAndWait();
        }
    }

    private void loadStage(String fxml) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));

            rootPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
