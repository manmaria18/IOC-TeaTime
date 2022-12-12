package com.example.iocteatime.controllersGUI;

import com.example.iocteatime.controller.MainController;
import com.example.iocteatime.domain.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainViewController {

    public Button logoutButton;
    public Button eventPlannerButton;
    private MainController mainController;
    @FXML
    ListView<Event> eventListView;

    @FXML
    TextField searchField;
    @FXML
    Button searchButton;

    @FXML
    RadioButton byEvent;
    @FXML
    RadioButton byUser;

    ObservableList<Event> events = FXCollections.observableArrayList();
    private final Image IMAGE_RUBY  = new Image("https://upload.wikimedia.org/wikipedia/commons/f/f1/Ruby_logo_64x64.png");
    public void Initialise(MainController mainController){
        this.mainController = mainController;
        initializeEvents();
        eventListView.setItems(events);
        eventListView.setCellFactory(param -> new ListCell<Event>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Event event, boolean empty) {
                super.updateItem(event, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setStyle("-fx-background-color:  #97a7fc;"+"-fx-max-height: 50;");
                    hBox.setSpacing(5);
                    hBox.setPadding(new Insets(5,5,5,5));
                    VBox vbox = new VBox();
                    Label title = new Label();
                    Label description= new Label();
                    Label location= new Label();
                    Label dateTime= new Label();
                    Button join = new Button();
                    join.setText("JOIN");
                    join.setStyle("-fx-background-color: gold;" + "-fx-background-radius: 80;" + "-fx-border-radius:80;" + "-fx-border-color: #000031");
                    join.setId(String.valueOf(event.getId()));
                    join.setOnAction(new EventHandler() {


                        @Override
                        public void handle(javafx.event.Event event) {
                            System.out.println("Hi there! You clicked me!I am lincked to event nr:"+ join.getId());
                        }



                    });
                    title.setText("Title : " + event.getName());
                    description.setText("Description : " + event.getDescription());
                    location.setText("Location : " + event.getLocation());
                    dateTime.setText("Date time : " + event.getDateTime());

                    //////////
                    hBox.setPadding(new Insets(5));
                    title.setStyle("-fx-text-fill: #000031;" + "-fx-font-size: 20");
                    description.setStyle("-fx-text-fill: #000031;"+ "-fx-font-size: 15");
                    location.setStyle("-fx-text-fill: #000031;"+ "-fx-font-size: 15");
                    dateTime.setStyle("-fx-text-fill: #000031;"+ "-fx-font-size: 15");

                    //////

                    hBox.getChildren().add(vbox);
                    hBox.getChildren().add(imageView);
                    vbox.getChildren().add(title);
                    vbox.getChildren().add(description);
                    vbox.getChildren().add(location);
                    vbox.getChildren().add(dateTime);
                    vbox.getChildren().add(join);
                    //Image newImage = new Image(event.getImgURL());

                    imageView.setImage(new Image(event.getImgURL()));
                    imageView.setFitHeight(150.0);
                    imageView.setFitWidth(250.0);
                    //imageView.fitHeightProperty();
                    //imageView.fitHeightProperty();
                    //imageView.setStyle("-fx-fit-width: 50;"+"-fx-fit-height: 50");
                    setGraphic(hBox);
                }
            }
        });
    }

    private void onJoinClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"JOIN TRIED",ButtonType.OK);
        alert.show();
    }

    private void initializeEvents() {
        events.setAll(mainController.getAllEvents());
    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/com/example/iocteatime/Login.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Tea-Time!");
        stage.setScene(new Scene(root1));
        LoginController logInController = (LoginController)fxmlLoader.getController();
        logInController.Initialize(mainController);
        stage.show();
        Stage stage2 = (Stage) logoutButton.getScene().getWindow();
        stage2.close();
    }

    public void handleEventPlanner(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/com/example/iocteatime/EventPlannerView.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Tea-Time!");
        stage.setScene(new Scene(root1));
        EventController eventController = fxmlLoader.getController();
        eventController.Initialize(mainController);
        stage.show();
        Stage stage2 = (Stage) eventPlannerButton.getScene().getWindow();
        stage2.close();
    }

    public void handleSearchButtonClick(ActionEvent actionEvent) throws SQLException {
        if(!searchField.getText().equals("")){
            List<Event> eventsByName = mainController.getEventsByName(searchField.getText());
            if(eventsByName.size()!=0)
               events.setAll(eventsByName);
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"No results found!",ButtonType.OK);
                alert.show();
                events.setAll(mainController.getAllEvents());
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please insert value for search!",ButtonType.OK);
            alert.show();
        }
    }
}
