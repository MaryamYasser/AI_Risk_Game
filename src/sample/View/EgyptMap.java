package sample.View;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sample.Controller.Controller;
import sample.Model.CurrentGame;
import sample.Model.allTerritories;
import sample.Model.territories;
import sample.PopUp;

import java.util.ArrayList;
import java.util.Collections;

public class EgyptMap implements Map{

    ArrayList<Button> buttons= new ArrayList<>();
    Pane EgyptPane = new Pane();



    public Scene EgyptScene() {

        MainView mainView = new MainView();

        ArrayList<territories> EgyptMap = mainView.controller.getEgyptTerritories();


        for(int i=0;i<EgyptMap.size();i++){

            Button egypt = mainView.createButton(EgyptMap.get(i));
            EgyptPane.getChildren().add(egypt);
            buttons.add(egypt);
        }

        Button Start = new Button("Start!");
        mainView.controller.territoryButtonClicked(buttons,"Egypt",6,mainView,EgyptPane,Start);


        Start.setTranslateX(202);
        Start.setTranslateY(689);
        EgyptPane.getChildren().add(Start);








        EgyptPane.setId("Egypt");
        Scene EgyptScene = new Scene(EgyptPane, 848, 768);
        EgyptScene.getStylesheets().add("Stylesheet.css");
        mainView.UpdateColours(buttons);






        return EgyptScene;
    }


    public int showSoldierOptions(int Available,Boolean Human){


        ComboBox available = new ComboBox();
        for(int i = 1;i<=Available;i++)
            available.getItems().add(i);
        available.setTranslateX(201);
        available.setTranslateY(611);

        if(Human){
        EgyptPane.getChildren().add(available);

        String val = available.getValue().toString();


        return Integer.parseInt(val);}
        return 0;



    }
}
