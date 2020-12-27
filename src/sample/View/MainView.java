package sample.View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Controller.Controller;
import sample.Model.territories;

import java.util.ArrayList;


public class MainView extends Application {


    Controller controller = new Controller();
    public Stage thisStage;
    USAMap usa = new USAMap();
    EgyptMap egypt = new EgyptMap();

    public  MainView(Stage stage){
        this.thisStage = stage;
    }

    public MainView(){}

    @Override
    public void start(Stage MainMenu) throws Exception {
    }


    public Scene createMainScene(Stage stage) {


        Label intro = new Label("Welcome To The Game of Risk!");
        Button Simulation = new Button("Simulation Mode");
        Button Playing = new Button("Playing Mode");
        HBox playingModes = new HBox(10, Simulation, Playing);
        VBox intoVBox = new VBox(10, intro, playingModes);


        Scene mainScene = new Scene(intoVBox, 700, 350);
        mainScene.getStylesheets().add("Stylesheet.css");

        controller.SimulationButtonClicked(this,stage,Simulation);
        controller.PlayerModeButtonClicked(this,stage,Playing);

        return mainScene;
    }

    public Scene AgentScene(){
        Label title = new Label("Choose Your Players");
        Label player1 = new Label("Player 1");
        Label player2 = new Label("Player 2");
        HBox outline = new HBox(10, player1, player2);
        ComboBox players1 = new ComboBox(FXCollections.observableArrayList(controller.agentsFactoryData.GetAgents()));

        ComboBox players2 = new ComboBox(FXCollections.observableArrayList(controller.agentsFactoryData.GetAgents()));
        HBox playerDropBox = new HBox(10, players1, players2);


        Button go = new Button("GO");


        controller.getOpponents(players1,players2);
        controller.ProceedButtonClicked(this,thisStage,go);

        Button back = new Button("BACK");
        HBox goback = new HBox(10,go,back);
        VBox AgentBox = new VBox(10, title, outline, playerDropBox, goback);
        Scene AgentScene = new Scene(AgentBox, 700, 350);

        AgentScene.getStylesheets().add("Stylesheet.css");
        return AgentScene;
    }


    public Scene HumanScene(){
        Label player = new Label("Choose Your Opponent ");

        ComboBox players = new ComboBox(FXCollections.observableArrayList(controller.agentsFactoryData.GetAgents()));

        Button go = new Button("GO");

        controller.getOpponent(players);
        controller.ProceedButtonClicked(this,thisStage,go);
        VBox AgentBox = new VBox(10, player, players, go);


        Scene AgentScene = new Scene(AgentBox, 700, 350);
        AgentScene.getStylesheets().add("Stylesheet.css");
        return AgentScene;
    }

    public Scene mapOptionsScene(){
        Label choice = new Label("Choose Your Game Map");
        Button Egypt = new Button("Egypt");
        Button US = new Button("USA");
        HBox countries = new HBox(10,Egypt,US);



        controller.EgyptButtonClicked(egypt,thisStage,Egypt);
        controller.USAButtonClicked(usa,thisStage,US);

        VBox mapBox = new VBox(10,choice,countries);
        Scene mapScene = new Scene(mapBox, 700, 350);
        mapScene.getStylesheets().add("Stylesheet.css");

        return mapScene;

    }



String style;
    public Button createButton(territories sample){ //



        Button egypt = new Button(String.valueOf(sample.id));
        egypt.setTranslateX(sample.getXpos());
        egypt.setId(String.valueOf(sample.id));
        egypt.setTranslateY(sample.getYpos());


        style = "-fx-background-radius: 30em; -fx-min-width: 40px;" +
               " -fx-min-height: 40px; -fx-max-width: 40px; -fx-max-height: 40px; " +
               "-fx-background-insets: 0px; -fx-padding: 0px;";

       egypt.setStyle(style);

         return egypt;
    }

    public void UpdateColours(ArrayList<Button> buttons){
        for(Button button:buttons){
       if(controller.checkTerritoryColour(button)==2)
           button.setStyle(style+"-fx-normal-background: linear-gradient(#4b6cb7, #182848);" +
                   "-fx-hovered-background: linear-gradient(#182848, #4b6cb7)");

       else  if(controller.checkTerritoryColour(button)==1)
           button.setStyle(style+"-fx-hovered-background: linear-gradient(#900d0d, #cf1b1b);"+
               "-fx-normal-background: linear-gradient(#cf1b1b, #900d0d)");

        else if (controller.checkTerritoryColour(button)==0)
            button.setStyle(style+"-fx-hovered-background: linear-gradient(#414345, #232526);"+
                    "-fx-normal-background: linear-gradient(#232526, #414345)");
    }
    }
}













