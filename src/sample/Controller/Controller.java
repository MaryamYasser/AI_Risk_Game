package sample.Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Model.*;
import sample.Model.Agents.AgentsFactory;
import sample.View.PopUp;
import sample.View.EgyptMap;
import sample.View.MainView;
import sample.View.Map;
import sample.View.USAMap;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public  class Controller extends Application {
    @Override
    public void start(Stage MainMenu) throws Exception {
    }


    SceneController sceneController = new SceneController();
    public AgentsFactory agentsFactoryData = new AgentsFactory();
    public CurrentGame currentGame = CurrentGame.getInstance();
    allTerritories allTerritories = new allTerritories();
    public  boolean HumanMode = false;


    territories clickedTerritory;





    public void SimulationButtonClicked(MainView mainView,Stage primaryStage, Button Simulation){
        Simulation.setOnAction(e -> {

            primaryStage.setScene(sceneController.createAgentScene(mainView));
        });

    }

    public void PlayerModeButtonClicked(MainView mainView,Stage primaryStage,Button Playing){
        Playing.setOnAction(e -> {
            primaryStage.setScene(sceneController.createHumanScene(mainView));
        });

        HumanMode = true;

    }

    public void ProceedButtonClicked(MainView mainView,Stage stage,Button go){
        go.setOnAction(e -> {
            stage.setScene(sceneController.createMapOptionsScene(mainView));

        });
    }



    public void EgyptButtonClicked(EgyptMap egypt, Stage stage, Button Egypt){


        Egypt.setOnAction(e -> {

            stage.setScene(sceneController.createEgyptScene(egypt));


        });


    }

    public void USAButtonClicked(USAMap usa, Stage stage, Button USA){
        USA.setOnAction(e -> {
            stage.setScene(sceneController.createUSAScene(usa));

        });


    }

    public void territoryButtonClicked(ArrayList<Button> buttons, String location, int bound, MainView mainView, Pane Egypt,Button Start){

        initializeGame(location,bound);

      for(int i=0;i<buttons.size();i++) {

         final int  j=i;
         buttons.get(i).setOnAction(e -> {





             if(currentGame.playerOne.getPlayingAgent().getName().equalsIgnoreCase("Human") && CurrentGame.currentTurn==1 ){
                HumanAttack_With(Egypt,buttons);

             }

             mainView.UpdateColours(buttons);

             });

      }

      Start.setOnAction(e->{


              Thread thread = new Thread(new Runnable() {

                  @Override
                  public void run() {
                      Runnable updater = new Runnable() {

                          @Override
                          public void run() {
                              mainView.UpdateColours(buttons);
                              if(CurrentGame.GameOver){
                                  Players winner = currentGame.Winner;
                                  PopUp.Error("Game Over",winner.getPlayingAgent().getName()+" HAS WON!!!");}
                          }
                      };

                      while (!CurrentGame.GameOver) {
                          try {
                              Thread.sleep(500);
                          } catch (InterruptedException ex) {
                          }

                          currentGame.GameOn();



                          // UI update is run on the Application thread
                          Platform.runLater(updater);
                      }

                      if(currentGame.playerOne.getPlayingAgent().getName().equalsIgnoreCase("A_Star") ||
                              currentGame.playerOne.getPlayingAgent().getName().equalsIgnoreCase("RTA-star") ||
                              currentGame.playerOne.getPlayingAgent().getName().equalsIgnoreCase("Greedy")) {

                          System.out.println(" Num Of Expansions: " + currentGame.playerOne.getPlayingAgent().numOfExpansions + "\n");
                          System.out.println("Num of Moves "+currentGame.numOfMoves1+"\n");


                      }

                  }

              });

          thread.setDaemon(true);
          thread.start();


             /* System.out.println("Player One Territories");
              printPlayerTerritories(currentGame.PlayerOneTerritories);
              System.out.println("Player Two Territories");
              printPlayerTerritories(currentGame.PlayerTwoTerritories);*/

             // i++;
          });




        }


   void HumanAttack_With(Pane CountryPane,ArrayList<Button> buttons){
        Label Attack = new Label("Choose Territory To Attack");
        CountryPane.getChildren().add(Attack);

        for(int i=0;i<buttons.size();i++) {

           final int  j=i;
           buttons.get(i).setOnAction(e -> {

               territories  currentTerritory = currentGame.getTerritories().get((Integer.parseInt(buttons.get(j).getId()))-1);
              // currentGame.human_Clicked=currentTerritory;
               currentGame.playerOne.HumanAttack(currentTerritory);

           });


       }



   }









    public void getOpponent(ComboBox players){ //get one opponent and a human player



        players.setOnAction(e ->{
            String opponent = players.getValue().toString();
            SetPlayers("Human",opponent);
            System.out.println("Player 1 is " +currentGame.getPlayerOne().getPlayingAgent().getName() +
                    " Player 2 is "+currentGame.getPlayersTwo().getPlayingAgent().getName());

        });


    }

    public void getOpponents(ComboBox players1,ComboBox players2){ //get two different new players




        players1.setOnAction(e ->{
            String player1 = players1.getValue().toString();
            currentGame.setPlayerOne(player1);
            if(player1.equalsIgnoreCase("Minimax player"))
                System.out.println("Player 1 is MiniMax");
           else  System.out.println("Player 1 is " +currentGame.getPlayerOne().getPlayingAgent().getName());


        });


        players2.setOnAction(e ->{
            String player2 = players2.getValue().toString();
            currentGame.setPlayersTwo(player2);
            System.out.println("Player 2 is "+currentGame.getPlayersTwo().getPlayingAgent().getName());

        });


    }



    public int checkTerritoryColour(Button button){

        if (lookFor(Integer.parseInt(button.getId()),currentGame.PlayerOneTerritories))
            return 1;


        if (lookFor(Integer.parseInt(button.getId()),currentGame.PlayerTwoTerritories)) return 2;

       else return 0;

    }


    public boolean lookFor(int id,ArrayList<territories> playerTerritories){
        for(territories sample:playerTerritories){
            if(sample.id ==id)
                return true;}
        return false;

    }

    public void SetPlayers(String one,String two){
        currentGame.setPlayerOne(one);
        //currentGame.getPlayerOne().playersTerritories=currentGame.PlayerOneTerritories;
        System.out.println();
        currentGame.setPlayersTwo(two);
        //currentGame.getPlayersTwo().playersTerritories=currentGame.PlayerTwoTerritories;
    }



    public void initializeGame(String location,int bound){
        currentGame.setLocation(location);
        if (location.equalsIgnoreCase(currentGame.availableLocations[0]))
        currentGame.setTerritories(getEgyptTerritories());
        else currentGame.setTerritories(getUSTerritories());


        RandomizeBeginning(bound);
        RandomizeBeginning2(bound);

       CurrentGame.currentTurn=1;
       CurrentGame.currentMode="A";




    }

    public void RandomizeBeginning(int bound){
        System.out.println("Player One Territories");
        currentGame.randomizeArmiesPlacement(bound);
       printPlayerTerritories(currentGame.PlayerOneTerritories);

    }

    public void RandomizeBeginning2(int bound){
        System.out.println("Player Two Territories");
        currentGame.randomizeSecondArmiesPlacement(bound);

        printPlayerTerritories(currentGame.PlayerTwoTerritories);
    }

    public void printPlayerTerritories(ArrayList<territories> playerTerritories){

        for(int i =0;i<playerTerritories.size();i++)
            System.out.println(playerTerritories.get(i).id + " No Of Soldiers: "
                    +playerTerritories.get(i).noOfSoldiers);
    }


    public ArrayList<territories> getEgyptTerritories(){
       return allTerritories.getEgyptTerritories();
    }
    public ArrayList<territories> getUSTerritories(){
        return allTerritories.getUSTerritories();
    }



}