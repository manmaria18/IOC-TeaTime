package com.example.iocteatime;

import com.example.iocteatime.controller.MainController;
import com.example.iocteatime.controllersGUI.LoginController;
import com.example.iocteatime.repository.IRepositoryEvents;
import com.example.iocteatime.repository.IRepositoryUser;
import com.example.iocteatime.repository.RepositoryEvents;
import com.example.iocteatime.repository.RepositoryUser;
import com.example.iocteatime.service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {
    String mariaBdConfigPath="C:\\Users\\manma\\IdeaProjects\\IOC-TeaTime\\bd.config.properties";
    String aygeanBdConfigPath="C:\\IOC-TeaTime\\bd.config.properties";
    String maraBdConfigPath="C:\\Mara\\materiale facultate\\An3\\SEM1\\IOC\\IOC-TeaTime\\bd.config.properties";


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 650);
        stage.setTitle("Tea-Time!");
        stage.setScene(scene);
        Properties props = new Properties();

        try {
            props.load(new FileReader(mariaBdConfigPath));
        } catch (Exception var13) {
            System.out.println(var13);
        }
        IRepositoryUser repoUser = new RepositoryUser(props);
        IRepositoryEvents repoEvents = new RepositoryEvents(props);
        IServiceUsers serviceUser = new ServiceUsers((RepositoryUser) repoUser);
        IServiceEvents serviceEvents = new ServiceEvents((RepositoryEvents)repoEvents);
        IServiceGuests serviceGuests = new ServiceGuests((RepositoryEvents)repoEvents,(RepositoryUser) repoUser);
        MainController mainController = new MainController((ServiceEvents)serviceEvents,(ServiceUsers)serviceUser,(ServiceGuests)serviceGuests);
        LoginController logInController = (LoginController)fxmlLoader.getController();
        logInController.Initialize(mainController);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}