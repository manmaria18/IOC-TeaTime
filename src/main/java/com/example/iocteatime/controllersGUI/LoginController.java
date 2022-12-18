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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginController {

    private MainController mainController;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button logInButton;

    @FXML
    private Button registerButton;

    @FXML
    private ImageView imagine= new ImageView();

    Image img = new Image("C:\\Users\\manma\\IdeaProjects\\IOC-TeaTime\\src\\main\\resources\\com\\example\\iocteatime\\Images\\tea_time_alice.jpeg");

    private User currentUser;
    public LoginController() {
    }

    public void Initialize(MainController mainController){

        this.mainController  = mainController;
        this.imagine.setImage(img);


    }

    public void handleLogin(ActionEvent actionEvent) {

        String username = usernameField.getText();
        String password = passwordField.getText();
        Alert alert;

        User user = this.mainController.getUser(username);
        System.out.println(user.toString());
        //List<Integer> ev = new ArrayList<>();
        this.currentUser = user;
        if(!username.isBlank() && !password.isBlank()){
            if(user!=null){
                if(user.getPassword().equals(password)){
                    try {
                        this.showMainView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    alert = new Alert(Alert.AlertType.WARNING, "Incorrect password!", new ButtonType[0]);
                    alert.show();
                }
            }
            else{
                alert = new Alert(Alert.AlertType.WARNING, "There is no user with the given username!", new ButtonType[0]);
                alert.show();
            }
        }
        else{
            alert = new Alert(Alert.AlertType.WARNING, "Please input your info!", new ButtonType[0]);
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
        Stage stage2 = (Stage) logInButton.getScene().getWindow();
        stage2.close();
    }

    public void handleRedirectToRegister(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/com/example/iocteatime/Register.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Register");
        stage.setScene(new Scene(root1));
        RegisterController registerController = fxmlLoader.getController();
        registerController.Initialize(mainController);
        stage.show();
        Stage stage2 = (Stage) registerButton.getScene().getWindow();
        stage2.close();
    }
}
