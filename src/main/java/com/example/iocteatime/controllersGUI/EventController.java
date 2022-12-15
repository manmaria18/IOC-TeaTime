package com.example.iocteatime.controllersGUI;

import com.example.iocteatime.controller.MainController;
import com.example.iocteatime.domain.Event;
import javafx.event.ActionEvent;
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
    public TextField hourField;
    public TextArea urlField;
    private MainController mainController;

    public void Initialize(MainController mainController){
        this.mainController  = mainController;
    }
    public void handleAdd(ActionEvent actionEvent) throws IOException {
        String title=titleField.getText();
        String location=locationField.getText();
        LocalDate date =dateField.getValue();
        String url =urlField.getText();
        String startTime = hourField.getText();
        String endTime = hourField.getText();
        String description = descriptionField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //String date2 = formatter.format(date);
        //String time = date2 + " " + hour;
        //DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //LocalDateTime dateTime = LocalDateTime.parse(time,formatter2);
        List<String> names = new ArrayList<>();
        if(!(title.equals("")&&location.equals("")&&startTime.equals("")&&endTime.equals("")&&description.equals("")&&url.equals(""))){
            this.mainController.addEvent(0,title,description,location,date,startTime,endTime,url,names);
            titleField.setText("");
            locationField.setText("");
            dateField.setValue(LocalDate.now());
            urlField.setText("");
            hourField.setText("");
            descriptionField.setText("");

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
        mainViewController.Initialise(mainController);
        stage.show();
        Stage stage2 = (Stage) backButton.getScene().getWindow();
        stage2.close();
    }
}
