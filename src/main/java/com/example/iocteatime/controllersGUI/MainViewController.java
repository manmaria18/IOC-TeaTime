package com.example.iocteatime.controllersGUI;

import com.example.iocteatime.controller.MainController;
import com.example.iocteatime.domain.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

public class MainViewController {

    public Button logoutButton;
    public Button eventPlannerButton;
    private MainController mainController;
    @FXML
    ListView<Event> eventListView;
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
                    hBox.setStyle("-fx-background-color:  #97a7fc");
                    hBox.setSpacing(5);
                    hBox.setPadding(new Insets(5,5,5,5));
                    VBox vbox = new VBox();
                    Label title = new Label();
                    Label description= new Label();
                    Label location= new Label();
                    Label dateTime= new Label();
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
                    imageView.setImage(new Image(event.getImgURL()));
                    setGraphic(hBox);
                }
            }
        });
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
}
