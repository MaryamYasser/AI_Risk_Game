package sample.Controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.View.EgyptMap;
import sample.View.MainView;
import sample.View.Map;
import sample.View.USAMap;


public class SceneController extends Application {



    public void start(Stage MainMenu) throws Exception {
    }

    public Scene createAgentScene(MainView mainView) {

        return mainView.AgentScene();

    }

    public Scene createHumanScene(MainView mainView) {
        return mainView.HumanScene();
    }




    public Scene createMapOptionsScene(MainView mainView){
        return mainView.mapOptionsScene();
    }

    public Scene createEgyptScene(EgyptMap egypt){

        return egypt.EgyptScene();
    }

    public Scene createUSAScene(USAMap usa){
        return usa.USAScene();
    }






}

