package sample.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{
        MainView mainView = new MainView(primaryStage);

        primaryStage.setTitle("Risk Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(mainView.createMainScene(primaryStage));




        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

