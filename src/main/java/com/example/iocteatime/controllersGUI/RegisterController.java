package com.example.iocteatime.controllersGUI;

import com.example.iocteatime.controller.MainController;
import com.example.iocteatime.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {
    public TextField usernameRegister;
    public PasswordField passwordRegister1;
    public PasswordField passwordRegister2;
    public Button registerButton;
    private MainController mainController;
    private User currentUser;

    public void Initialize(MainController mainController){
        this.mainController  = mainController;
    }

    public void handleRegister(ActionEvent actionEvent) throws IOException {
        String username = usernameRegister.getText();
        String password1 = passwordRegister1.getText();
        String password2 = passwordRegister2.getText();
        Alert alert;

        if(password1.equals(password2)){
            List<Integer> events = new ArrayList<>();
            this.mainController.addUser(username,password1,events);
            currentUser = this.mainController.getUser(username);
            this.showMainView();
        } else{
            alert = new Alert(Alert.AlertType.WARNING, "Incorrect password!", new ButtonType[0]);
            alert.show();
        }

    }
    public void showMainView() throws IOException {
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
        Stage stage2 = (Stage) registerButton.getScene().getWindow();
        stage2.close();
    }
}
