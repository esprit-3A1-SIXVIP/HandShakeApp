
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handshake;

import Entities.Commentaire;
import Entities.Evenement;
import Entities.Question;
import Entities.SendMail;
import Entities.User;
import Services.CommentaireService;
import Services.QuestionService;
import Services.ServiceUser;
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
import javafx.event.Event;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private void userinterface(MouseEvent event) {
    }

    @FXML
    private void recherche(KeyEvent event) {
        try {
            Callback<ListView<Question>, ListCell<Question>> cellFactory = (param) -> {
                return new QCell();
            };
            tableQuestions.setCellFactory(cellFactory);
            tableQuestions.setItems(QS.search(rechercheD.getText()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

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
        @FXML
        private JFXButton ban;
        int click = 0;
        private CommentaireService sc = new CommentaireService();
        private ServiceUser us = new ServiceUser();

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
                score.setText("" + item.getScore());
                TextLabel.setText(item.getTexteCommentaire());
                userLabel.setText(" Par '" + item.getUser().getusername() + "'");
                dateLabel.setText(" Le '" + item.getDateCommentaire().toString() + "'");
                if ((UserSession.getU().equals(item.getUser())) || (UserSession.getU().getroles().equals("admin"))) {
                    modifierCommentaire.setVisible(true);
                    supprimerCommentaire.setVisible(true);
                    TextArea TF = new TextArea(TextLabel.getText());
                    TF.setVisible(false);
                    modifierCommentaire.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (click == 1) {
                                item.setTexteCommentaire(TF.getText().replaceAll("'", "`"));
                                try {
                                    sc.update(item);
                                } catch (SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                TF.setVisible(false);
                                TextLabel.setText(item.getTexteCommentaire());
                                loadStage("ShakeHub.fxml");
                            } else {
                                click++;
                            }
                            TF.setVisible(true);
                            TF.autosize();
                            TextLabel.setGraphic(TF);
                            TextLabel.setText("");

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
                int init = item.getScore();
                shakeup.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        item.setScore(item.getScore() + 1);
                        if (init == item.getScore()) {
                            score.setText("" + item.getScore());
                        } else {
                            score.setText("^" + item.getScore());
                        }
                        if (init < item.getScore()) {
                            shakeup.setVisible(false);
                        }
                        shakedown.setVisible(true);
                        try {
                            sc.update(item);
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                shakedown.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        item.setScore(item.getScore() - 1);
                        if (init == item.getScore()) {
                            score.setText("" + item.getScore());
                        } else {
                            score.setText("v" + item.getScore());
                        }
                        shakeup.setVisible(true);
                        if (init > item.getScore()) {
                            shakedown.setVisible(false);
                        }
                        try {
                            sc.update(item);
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                if (UserSession.getInstance().getroles().equals("admin") && (!item.getUser().getroles().equals("admin")) && (item.getUser().isAccesShakeHub() == (1))) {
                    ban.setVisible(true);
                    ban.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Alert A = new Alert(Alert.AlertType.CONFIRMATION);
                            A.setContentText("Voulez vous vraiment bannir l'utilisateur '" + item.getUser().getusername() + "'?");
                            ButtonType buttonTypeOne = new ButtonType("Bannir");
                            ButtonType buttonTypeOne1 = new ButtonType("Annuler");
                            A.getButtonTypes().setAll(buttonTypeOne, buttonTypeOne1);
                            A.showAndWait();
                            if (A.getResult() == buttonTypeOne) {
                                item.getUser().setAccesShakeHub(0);
                                try {
                                    us.setAccessShakeHub(item.getUser());
                                } catch (SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                SendMail.sendMail(item.getUser().getEmail(),"HandShake - Accès au ShakeHub restreint",item.getUser().getusername()+"\n Vous avez été banni du ShakeHub car votre Commentaire '"+item.getTexteCommentaire()+"' publiée le "+item.getDateCommentaire()+" transgrèsse nos règles. \n Si vous pensez que vous avez été banni injustement, veuillez répondre à ce mail.");
                            
                            }
                        }
                    });
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
        @FXML
        private JFXButton ban;
        int click = 0;
        ObservableList<Commentaire> commentlist = FXCollections.observableArrayList();
        ServiceUser us = new ServiceUser();
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
                score.setText("" + item.getScore());
                this.TextLabel.setText(item.getTexteQuestion());
                this.userLabel.setText(" Par '" + item.getUser().getusername() + "'");
                this.dateLabel.setText(" Le '" + item.getDateQuestion().toString() + "'");
                if ((UserSession.getU().equals(item.getUser())) || (UserSession.getInstance().getroles().equals("admin"))) {
                    modifierQuestion.setVisible(true);

                    if ((this.commentlist.isEmpty()) || (UserSession.getInstance().getroles().equals("admin"))) {
                        supprimerQuestion.setVisible(true);
                    } else {
                        supprimerQuestion.setVisible(false);
                    }
                    TextArea TF = new TextArea(TextLabel.getText());
                    TF.setVisible(false);
                    modifierQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (click == 1) {
                                item.setTexteQuestion(TF.getText().replaceAll("'", "`"));
                                try {
                                    QS.update(item);
                                } catch (SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                TF.setVisible(false);
                                TextLabel.setText(item.getTexteQuestion());
                                loadStage("ShakeHub.fxml");
                            } else {
                                click++;
                            }
                            TF.setVisible(true);
                            TF.autosize();
                            TextLabel.setGraphic(TF);
                            TextLabel.setText("");

                        }
                    });
                    supprimerQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Alert A = new Alert(Alert.AlertType.CONFIRMATION);
                            A.setContentText("Voulez vous vraiment supprimer votre question?");
                            ButtonType buttonTypeOne = new ButtonType("Supprimer");
                            ButtonType buttonTypeOne1 = new ButtonType("Annuler");
                            A.getButtonTypes().setAll(buttonTypeOne, buttonTypeOne1);
                            A.showAndWait();
                            if (A.getResult() == buttonTypeOne) {
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
                if ((UserSession.getInstance().getroles().equals("admin")) && (!item.getUser().getroles().equals("admin")) && (item.getUser().isAccesShakeHub() == (1))) {
                    ban.setVisible(true);
                    ban.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Alert A = new Alert(Alert.AlertType.CONFIRMATION);
                            A.setContentText("Voulez vous vraiment bannir l'utilisateur '" + item.getUser().getusername() + "'?");
                            ButtonType buttonTypeOne = new ButtonType("Bannir");
                            ButtonType buttonTypeOne1 = new ButtonType("Annuler");
                            A.getButtonTypes().setAll(buttonTypeOne, buttonTypeOne1);
                            A.showAndWait();
                            if (A.getResult() == buttonTypeOne) {
                                item.getUser().setAccesShakeHub(0);
                                try {
                                    us.setAccessShakeHub(item.getUser());
                                } catch (SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                SendMail.sendMail(item.getUser().getEmail(),"HandShake - Accès au ShakeHub restreint",item.getUser().getusername()+"\n Vous avez été banni du ShakeHub car votre Question '"+item.getTexteQuestion()+"' publiée le "+item.getDateQuestion()+" transgrèsse nos règles. \n Si vous pensez que vous avez été banni injustement, veuillez répondre à ce mail.");
                            }
                        }
                    });

                }
                int init = item.getScore();
                shakeup.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        item.setScore(item.getScore() + 1);
                        if (init == item.getScore()) {
                            score.setText("" + item.getScore());
                        } else {
                            score.setText("^" + item.getScore());
                        }
                        if (init < item.getScore()) {
                            shakeup.setVisible(false);
                        }
                        shakedown.setVisible(true);
                        try {
                            QS.update(item);
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                shakedown.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        item.setScore(item.getScore() - 1);
                        if (init == item.getScore()) {
                            score.setText("" + item.getScore());
                        } else {
                            score.setText("v" + item.getScore());
                        }
                        shakeup.setVisible(true);
                        if (init > item.getScore()) {
                            shakedown.setVisible(false);
                        }
                        try {
                            QS.update(item);
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
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
                        Alert A = new Alert(Alert.AlertType.CONFIRMATION);
                        ButtonType buttonTypeOne = new ButtonType("Publier");
                        ButtonType buttonTypeOne1 = new ButtonType("Annuler");
                        A.getButtonTypes().setAll(buttonTypeOne, buttonTypeOne1);
                        A.setContentText("Voulez vous vraiment publier ce commentaire? Merci de vérifier que votre commentaire respecte nos règles avant de le publier!");
                        A.showAndWait();
                        if (A.getResult() == buttonTypeOne) {
                            try {
                                sc.ajouter(new Commentaire(UserSession.getU(), item, commentaireTA.getText().replaceAll("'", "`"), date));
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
        username.setText(UserSession.getU().getusername());
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
        if (questionTA.getText().length() >= 150) {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            Alert A = new Alert(Alert.AlertType.CONFIRMATION);
            A.setContentText("Voulez vous vraiment publier cette question? Merci de vérifier que votre question respecte nos règles avant de la publier!");
            ButtonType buttonTypeOne = new ButtonType("Publier");
            ButtonType buttonTypeOne1 = new ButtonType("Annuler");
            A.getButtonTypes().setAll(buttonTypeOne, buttonTypeOne1);
            A.showAndWait();
            if (A.getResult() == buttonTypeOne) {
                QS.ajouter(new Question(0, questionTA.getText().replaceAll("'", "`"), date, UserSession.getU()));
                loadStage("ShakeHub.fxml");
            }
        } else {
            Alert A = new Alert(Alert.AlertType.ERROR);
            A.setContentText("Votre Question doit comporter 150 caractères minimum.");
            A.showAndWait();
        }
    }
    @FXML
    private void home(ActionEvent event) {
        if (UserSession.getInstance().getroles().equals("admin")) {
            loadStage("gestionnaire.fxml");
        } else {
            loadStage("Home.fxml");
        }
    }

    private void userinterface(ActionEvent event) {
        loadStage("User.fxml");
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
