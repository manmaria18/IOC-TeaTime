package com.example.iocteatime.controllersGUI;

import com.example.iocteatime.controller.MainController;
import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MainViewController {

    public Button logoutButton;
    public TextField searchField1;
    public Button searchButton1;
    public CheckBox ByName1;
    public CheckBox ByDate1;
    public TextField searchField2;
    public Button searchButton2;
    //public ListView<Event> myEventListView;
    public CheckBox ByName2;
    public CheckBox ByDate2;
    @FXML
    ListView<Event> eventListView2;
    public Button addEventButton;
    // public Button eventPlannerButton;
    private MainController mainController;
    @FXML
    ListView<Event> eventListView;
    @FXML
    ListView<Event> eventListView1;
    @FXML
    TextField searchField;
    @FXML
    Button searchButton;

    @FXML
    CheckBox ByName;
    @FXML
    CheckBox ByDate;
    private User currentUser;
   // private Button join;

    ObservableList<Event> myEvents = FXCollections.observableArrayList();
    ObservableList<Event> events = FXCollections.observableArrayList();
    ObservableList<Event> createdEvents = FXCollections.observableArrayList();
    private final Image IMAGE_RUBY  = new Image("https://upload.wikimedia.org/wikipedia/commons/f/f1/Ruby_logo_64x64.png");
    public void Initialise(MainController mainController,User currentUser){
        this.mainController = mainController;
        this.currentUser = currentUser;
        initializeEvents();

    }

    class XCell extends ListCell<Event> {
        private ImageView imageView2 = new ImageView();
        HBox hBox = new HBox();
        VBox vbox = new VBox();
        Label title = new Label();
        Label description = new Label();
        Label location = new Label();
        Label date = new Label();
        Label startTime = new Label();
        Label endTime = new Label();
        Button join = new Button();
        public XCell(String buttonName) {
            super();
            hBox.setAlignment(Pos.CENTER);
            hBox.setStyle("-fx-background-color:  #97a7fc;" + "-fx-max-height: 50;");
            hBox.setSpacing(5);
            hBox.setPadding(new Insets(5, 5, 5, 5));
            join.setText(buttonName);
            hBox.getChildren().add(vbox);
            hBox.getChildren().add(imageView2);
            vbox.getChildren().add(title);
            vbox.getChildren().add(description);
            vbox.getChildren().add(location);
            vbox.getChildren().add(date);
            vbox.getChildren().add(startTime);
            vbox.getChildren().add(endTime);
            vbox.getChildren().add(join);
            join.setStyle("-fx-background-color: gold;" + "-fx-background-radius: 80;" + "-fx-border-radius:80;" + "-fx-border-color: #000031");
            join.setOnAction(e->{
                Event event = getListView().getItems().get(getIndex());

                if(buttonName.equals("JOIN")){
                    mainController.joinEvent(event.getId(), currentUser.getUsername(),"join");
                    init();
                    updateItem(event,false);
                }
                if(buttonName.equals("LEAVE")){
                    mainController.leaveEvent(event.getId(), currentUser.getUsername());
                    init();
                    updateItem(event,false);
                }
                if (buttonName.equals("UPDATE")){
                    FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("/com/example/iocteatime/EventPlannerView.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent)fxmlLoader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("Tea-Time!");
                    stage.setScene(new Scene(root1));
                    EventController eventController = fxmlLoader.getController();
                    eventController.Initialize(mainController,event,currentUser);
                    stage.show();
                    Stage stage2 = (Stage) addEventButton.getScene().getWindow();
                    updateItem(event,false);
                    stage2.close();
                    init();
                }
            });
        }

        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                title.setText("Title : " + event.getName());
                description.setText("Description : " + event.getDescription());
                location.setText("Location : " + event.getLocation());
                date.setText("Date: " + event.getDate());
                startTime.setText("Starts at:" + event.getStartTime());
                endTime.setText("Ends at:" + event.getEndTime());

                //////////
                hBox.setPadding(new Insets(5));
                title.setStyle("-fx-text-fill: #000031;" + "-fx-font-size: 20");
                description.setStyle("-fx-text-fill: #000031;" + "-fx-font-size: 15");
                location.setStyle("-fx-text-fill: #000031;" + "-fx-font-size: 15");
                date.setStyle("-fx-text-fill: #000031;" + "-fx-font-size: 15");
                startTime.setStyle("-fx-text-fill: #000031;" + "-fx-font-size: 15");
                endTime.setStyle("-fx-text-fill: #000031;" + "-fx-font-size: 15");
                imageView2.setImage(new Image(event.getImgURL()));
                imageView2.setFitHeight(150.0);
                imageView2.setFitWidth(250.0);
                setGraphic(hBox);
            }
        }
    }


    public void init(){
        events.setAll(mainController.getAllEventsWhereYouAreNotAdded(currentUser.getUsername()));
        myEvents.setAll(mainController.getAllEventsOfAGivenUser(currentUser.getUsername()));
        createdEvents.setAll(mainController.getAllEventsOfOwner(currentUser.getUsername()));
    }
    private void initializeEvents() {
        mainController.clearEventsByPeriod();
        init();
        eventListView.setCellFactory(param -> new XCell("JOIN"));
        eventListView1.setCellFactory(param -> new XCell("LEAVE"));
        eventListView2.setCellFactory(param -> new XCell("UPDATE"));
    }
    @FXML
    public void initialize(){
        eventListView.setItems(events);
        eventListView1.setItems(myEvents);
        eventListView2.setItems(createdEvents);
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
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("/com/example/iocteatime/EventPlannerView.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Tea-Time!");
        stage.setScene(new Scene(root1));
        EventController eventController = fxmlLoader.getController();
        eventController.Initialize(mainController,null,currentUser);
        stage.show();
        Stage stage2 = (Stage) searchField.getScene().getWindow();
        stage2.close();
    }

    public void handleSearchButtonClick(ActionEvent actionEvent) throws SQLException {
        if (ByName.isSelected()) {
            if (!searchField.getText().equals("")) {
                List<Event> eventsByName = mainController.getEventsByName(searchField.getText());
                if (eventsByName.size() != 0)
                    events.setAll(eventsByName);
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No results found!", ButtonType.OK);
                    alert.show();
                    events.setAll(mainController.getAllEvents());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please insert value for search!", ButtonType.OK);
                alert.show();
            }
        }
        else if(ByDate.isSelected()){
            if (!searchField.getText().equals("")) {
                List<Event> eventsByName = mainController.getEventsByDate(searchField.getText());
                if (eventsByName.size() != 0)
                    events.setAll(eventsByName);
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No results found!", ButtonType.OK);
                    alert.show();
                    events.setAll(mainController.getAllEvents());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please insert value for search!", ButtonType.OK);
                alert.show();
            }
        }
    }

    public void handleSearchButtonClick1(ActionEvent actionEvent) throws SQLException {
        if (ByName1.isSelected()) {
            if (!searchField1.getText().equals("")) {
                List<Event> eventsByName = mainController.getEventsByName(searchField1.getText());
                if (eventsByName.size() != 0)
                    myEvents.setAll(eventsByName);
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No results found!", ButtonType.OK);
                    alert.show();
                    myEvents.setAll(mainController.getAllEvents());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please insert value for search!", ButtonType.OK);
                alert.show();
            }
        }
        else if(ByDate1.isSelected()){
            if (!searchField1.getText().equals("")) {
                List<Event> eventsByName = mainController.getEventsByDate(searchField1.getText());
                if (eventsByName.size() != 0)
                    myEvents.setAll(eventsByName);
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No results found!", ButtonType.OK);
                    alert.show();
                    myEvents.setAll(mainController.getAllEvents());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please insert value for search!", ButtonType.OK);
                alert.show();
            }
        }
    }


    public void handleSearchButtonClick2(ActionEvent actionEvent) throws SQLException {
        if (ByName2.isSelected()) {
            if (!searchField2.getText().equals("")) {
                List<Event> eventsByName = mainController.getEventsByName(searchField1.getText());
                if (eventsByName.size() != 0)
                    createdEvents.setAll(eventsByName);
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No results found!", ButtonType.OK);
                    alert.show();
                    createdEvents.setAll(mainController.getAllEvents());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please insert value for search!", ButtonType.OK);
                alert.show();
            }
        }
        else if(ByDate2.isSelected()){
            if (!searchField2.getText().equals("")) {
                List<Event> eventsByName = mainController.getEventsByDate(searchField1.getText());
                if (eventsByName.size() != 0)
                    createdEvents.setAll(eventsByName);
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No results found!", ButtonType.OK);
                    alert.show();
                    createdEvents.setAll(mainController.getAllEvents());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please insert value for search!", ButtonType.OK);
                alert.show();
            }
        }
    }

    public void addEventButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("/com/example/iocteatime/EventPlannerView.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Tea-Time!");
        stage.setScene(new Scene(root1));
        EventController eventController = fxmlLoader.getController();
        eventController.Initialize(mainController,null,currentUser);
        stage.show();
        Stage stage2 = (Stage) addEventButton.getScene().getWindow();
        stage2.close();
    }

    public void refresh2(ActionEvent actionEvent) {
        if(!ByName2.isSelected() && !ByDate2.isSelected()){
            List<Event> refreshList = mainController.getAllEvents();
            createdEvents.setAll(refreshList);
        }
    }

    public void refresh1(ActionEvent actionEvent) {
        if(!ByName1.isSelected() && !ByDate1.isSelected()){
            List<Event> refreshList = mainController.getAllEvents();
            myEvents.setAll(refreshList);
        }
    }

    public void refresh(ActionEvent actionEvent) {
        if(!ByName.isSelected() && !ByDate.isSelected()){
            List<Event> refreshList = mainController.getAllEvents();
            events.setAll(refreshList);
        }
    }

    /*public void updateEvent(MouseEvent mouseEvent) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("/com/example/iocteatime/EventPlannerView.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Tea-Time!");
        stage.setScene(new Scene(root1));
        EventController eventController = fxmlLoader.getController();
        Event event = eventListView.getSelectionModel().getSelectedItem();
        eventController.Initialize(mainController,event,currentUser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Reminder: if you want to make a private event, remember to only then add attenders to the list!!",new ButtonType[0]);
        //alert.wait(1000);
        stage.show();
        alert.show();
        Stage stage2 = (Stage) searchField.getScene().getWindow();
        stage2.close();
    }*/

    /*public void handleSwitchToEventPlanner(javafx.event.Event event) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("/com/example/iocteatime/EventPlannerView.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Tea-Time!");
        stage.setScene(new Scene(root1));
        EventController eventController = fxmlLoader.getController();
        eventController.Initialize(mainController,null,currentUser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Reminder: if you want to make a private event, remember to only then add attenders to the list!!",new ButtonType[0]);
        //alert.wait(1000);
        stage.show();
        alert.show();
        Stage stage2 = (Stage) searchField.getScene().getWindow();
        stage2.close();
    }


    public void handleSwitchToMyEvents(javafx.event.Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("/com/example/iocteatime/MyEventsView.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Tea-Time!");
        stage.setScene(new Scene(root1));
        MyEventsViewController eventController = fxmlLoader.getController();
        eventController.Initialise(mainController,currentUser);
        stage.show();
        Stage stage2 = (Stage) searchField.getScene().getWindow();
        stage2.close();
    }*/
}




