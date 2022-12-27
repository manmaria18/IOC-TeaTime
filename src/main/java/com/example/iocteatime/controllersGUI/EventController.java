package com.example.iocteatime.controllersGUI;

import com.example.iocteatime.controller.MainController;
import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventController {

    public TextField titleField;
    public TextField locationField;
    public DatePicker dateField;
    public Button eventButton;
    public TextField descriptionField;
    public Button backButton;
    public TextField startTimeField;
    public TextField endTimeField;
    public TextArea urlField;
    public TextField maxNrOfAttendantsField;
    public ComboBox typeOfEventCombo;
    public Button eventButton2;
    public TextArea guestsField;
    private MainController mainController;

    private TextField eventTypeField;
    private TextField nrMaxOfAttendersField;
    private User currentUser;
    private Event currentEvent;

    public void Initialize(MainController mainController,Event currentEvent,User currentUser){
        typeOfEventCombo.getItems().addAll("Private","Pub");
        this.mainController  = mainController;
        this.currentEvent=currentEvent;
        this.currentUser = currentUser;
        if(currentEvent!=null){
            titleField.setText(this.currentEvent.getName());
            locationField.setText(this.currentEvent.getLocation());
            dateField.setValue(this.currentEvent.getDate());
            urlField.setText(this.currentEvent.getImgURL());
            startTimeField.setText(this.currentEvent.getStartTime());
            endTimeField.setText(this.currentEvent.getEndTime());
            descriptionField.setText(this.currentEvent.getDescription());
            typeOfEventCombo.setValue(this.currentEvent.getEventType());
            if(this.currentEvent.getEventType().equals("Pub")){
                maxNrOfAttendantsField.setText("No limit!");
                guestsField.setText("None");
            }else{
                maxNrOfAttendantsField.setText(this.currentEvent.getMaxNumberOfAttenders()+"");
                guestsField.setText(this.currentEvent.getGuests().toString());
            }

        }
    }
    public void handleAdd(ActionEvent actionEvent) throws IOException {
        String title=titleField.getText();
        String location=locationField.getText();
        LocalDate date =dateField.getValue();
        String url =urlField.getText();
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String description = descriptionField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String eventType = typeOfEventCombo.getValue().toString();
        String admin = currentUser.getUsername();
        int maxNr;
        String guests;
        if(eventType.equals("Pub")){
            maxNr= Integer.MAX_VALUE;
            guests="";
        }else{
            maxNr= Integer.valueOf(maxNrOfAttendantsField.getText());
            guests= guestsField.getText();
        }
        List<String> names = new ArrayList<>();
        if(!(title.equals("")&&location.equals("")&&startTime.equals("")&&endTime.equals("")&&description.equals("")&&url.equals(""))){
            String[] myGuests = guests.split(",");
            for(String guest : myGuests){
                names.add(guest);
            }
            this.mainController.addEvent(0,title,description,location,date,startTime,endTime,url,names,maxNr,eventType,admin);

            titleField.setText("");
            locationField.setText("");
            dateField.setValue(LocalDate.now());
            urlField.setText("");
            startTimeField.setText("");
            endTimeField.setText("");
            descriptionField.setText("");
            maxNrOfAttendantsField.setText("");
            guestsField.setText("");
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Empty fields!", new ButtonType[0]);
            alert.show();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event succesfully created!", new ButtonType[0]);
        alert.show();
    }

    public void handleGoBack(ActionEvent actionEvent) throws IOException {
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
        Stage stage2 = (Stage) backButton.getScene().getWindow();
        stage2.close();
    }

    public void handleUpdate(ActionEvent actionEvent) {
        String title = titleField.getText();
        String location = locationField.getText();
        LocalDate date = dateField.getValue();
        String url = urlField.getText();
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String description = descriptionField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String eventType = typeOfEventCombo.getValue().toString();
        String admin = currentUser.getUsername();
        String guests = guestsField.getText();
        int maxNr;
        if (eventType.equals("Pub")) {
            maxNr = Integer.MAX_VALUE;
        } else {
            maxNr = Integer.valueOf(maxNrOfAttendantsField.getText());
        }
        List<String> names = new ArrayList<>();
        if (currentUser.getUsername().equals(admin)) {
            if (!(title.equals("") && location.equals("") && startTime.equals("") && endTime.equals("") && description.equals("") && url.equals(""))) {
                String[] myGuests = guests.split(",");
                for(String guest : myGuests){
                    names.add(guest);
                }
                this.mainController.updateEvent(currentEvent.getId(), title, description, location, date, startTime, endTime, url, names, maxNr, eventType, admin);
                titleField.setText("");
                locationField.setText("");
                dateField.setValue(LocalDate.now());
                urlField.setText("");
                startTimeField.setText("");
                endTimeField.setText("");
                descriptionField.setText("");
                maxNrOfAttendantsField.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Empty fields!", new ButtonType[0]);
                alert.show();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event successfully updated!", new ButtonType[0]);
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "You are not the admin of the event, you can't update it!!", new ButtonType[0]);
            alert.show();
        }
    }
}
