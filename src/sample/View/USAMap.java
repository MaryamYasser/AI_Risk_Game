package sample.View;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sample.Controller.Controller;
import sample.Model.CurrentGame;
import sample.Model.territories;
import sample.View.PopUp;

import java.util.ArrayList;
import java.util.Collections;

public class USAMap implements Map{

    ArrayList<Button> buttons= new ArrayList<>();
    Pane USApane = new Pane();

    public Scene USAScene(){

        MainView mainView = new MainView();

        ArrayList<territories> USMap = mainView.controller.getUSTerritories();

        for(int i=0;i<USMap.size();i++){
            Button US = mainView.createButton(USMap.get(i));
            USApane.getChildren().add(US);
            buttons.add(US);
        }

        USApane.setId("USA");


        USApane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                if(arg0.getEventType() == MouseEvent.MOUSE_CLICKED){
                    System.out.println("Malak pressed no button");
                    System.out.println(arg0.getSceneX() + "," + arg0.getSceneY());
                }}
        });

        Button Start = new Button("Start!");
        mainView.controller.territoryButtonClicked(buttons,"USA",3,mainView,USApane,Start);

        Start.setTranslateX(86);
        Start.setTranslateY(465);
        USApane.getChildren().add(Start);

        if(CurrentGame.GameOver){
            PopUp.Error("Game Over!","The Game is Done");
        }



        Scene USAScene = new Scene(USApane, 848, 555);
        USAScene.getStylesheets().add("Stylesheet.css");

        mainView.UpdateColours(buttons);

        return USAScene;


    }


   public int showSoldierOptions(int Available,Boolean human){
       ComboBox available = new ComboBox();
       for(int i = 0;i<Available;i++)
           available.getItems().add(i);
       available.setTranslateX(110);
       available.setTranslateY(451);

       if(human){
       USApane.getChildren().add(available);
       String val = available.getValue().toString();
       return Integer.parseInt(val);}

       return 0;

   }




}
