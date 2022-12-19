package com.example.iocteatime.controllersGUI;

import com.example.iocteatime.controller.MainController;
import com.example.iocteatime.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MyEventsViewController {

    private MainController mainController;

    @FXML
    ListView<com.example.iocteatime.domain.Event> eventListView1;

    @FXML
    TextField searchField1;
    @FXML
    Button searchButton1;

    @FXML
    CheckBox ByName1;
    @FXML
    CheckBox ByDate1;


    private User currentUser;

    ObservableList<com.example.iocteatime.domain.Event> events = FXCollections.observableArrayList();

    public void Initialise(MainController mainController, User currentUser){
        this.mainController = mainController;
        this.currentUser = currentUser;
        initializeEvents();
        eventListView1.setItems(events);
        eventListView1.setCellFactory(param -> new ListCell<com.example.iocteatime.domain.Event>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(com.example.iocteatime.domain.Event event, boolean empty) {
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
                    Label date= new Label();
                    Label startTime = new Label();
                    Label endTime = new Label();
                    //Button join = new Button();
                   // join.setText("JOIN");
                   // join.setStyle("-fx-background-color: gold;" + "-fx-background-radius: 80;" + "-fx-border-radius:80;" + "-fx-border-color: #000031");
                   //join.setOnAction(new EventHandler() {
                    //});
                    title.setText("Title : " + event.getName());
                    description.setText("Description : " + event.getDescription());
                    location.setText("Location : " + event.getLocation());
                    date.setText("Date: " + event.getDate());
                    startTime.setText("Starts at:" + event.getStartTime());
                    endTime.setText("Ends at:" + event.getEndTime());

                    //////////
                    hBox.setPadding(new Insets(5));
                    title.setStyle("-fx-text-fill: #000031;" + "-fx-font-size: 20");
                    description.setStyle("-fx-text-fill: #000031;"+ "-fx-font-size: 15");
                    location.setStyle("-fx-text-fill: #000031;"+ "-fx-font-size: 15");
                    date.setStyle("-fx-text-fill: #000031;"+ "-fx-font-size: 15");
                    startTime.setStyle("-fx-text-fill: #000031;"+ "-fx-font-size: 15");
                    endTime.setStyle("-fx-text-fill: #000031;"+ "-fx-font-size: 15");
                    //////

                    hBox.getChildren().add(vbox);
                    hBox.getChildren().add(imageView);
                    vbox.getChildren().add(title);
                    vbox.getChildren().add(description);
                    vbox.getChildren().add(location);
                    vbox.getChildren().add(date);
                    vbox.getChildren().add(startTime);
                    vbox.getChildren().add(endTime);
                    //vbox.getChildren().add(join);
                    //Image newImage = new Image(event.getImgURL());

                    imageView.setImage(new Image(event.getImgURL()));
                    imageView.setFitHeight(150.0);
                    imageView.setFitWidth(250.0);
                    setGraphic(hBox);
                }
            }
        });
    }

    private void initializeEvents() {
        events.setAll(mainController.getAllEventsOfAGivenUser(currentUser.getUsername()));
    }

    public void switchToEvents(javafx.event.Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/com/example/iocteatime/MainView.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("MainView");
        stage.setScene(new Scene(root1));
        MainViewController mainViewController = fxmlLoader.getController();
        mainViewController.Initialise(mainController,currentUser);
        stage.show();
        Stage stage2 = (Stage) searchField1.getScene().getWindow();
        stage2.close();
    }

    public void handleSearchButtonClick(ActionEvent actionEvent) {
    }

    public void refresh(ActionEvent actionEvent) {
    }

    public void updateEvent(MouseEvent mouseEvent) {
    }

    public void handleSwitchToEventPlanner(Event event) {
    }
}
