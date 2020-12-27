package sample.Model;

import sample.Model.Agents.Agents;
import sample.Model.Agents.BasicAgent;
import sample.Model.Agents.HumanAgent;

import java.util.ArrayList;

public class Players {

    int  Soldiers_On_Ground;
    int  Soldiers_In_Hand;


   // public ArrayList<territories> aroundPlayer;
    BasicAgent playingAgent;
    HumanAgent HumanAgent = new HumanAgent();
    int TurnNumber;


    CurrentGame cg = CurrentGame.getInstance();

    public ArrayList<territories> getPlayersTerritories() {
        return playersTerritories;
    }

    public void setPlayersTerritories(ArrayList<territories> playersTerritories) {
        this.playersTerritories = playersTerritories;
    }

    public ArrayList<territories> playersTerritories;

    public int soliders;





    public Players(BasicAgent playingAgent,int TurnNumber) {
        this.playingAgent = playingAgent;
        this.TurnNumber = TurnNumber;
    }



    public void newTurn()
    {
        if(CurrentGame.currentTurn == this.TurnNumber)
        {
            this.Soldiers_In_Hand=this.playersTerritories.size()/3;
        }
    }



    public void attack()
    {
        if(playersTerritories.size()==0){

            CurrentGame.GameOver=true;
            if(CurrentGame.currentTurn==1){
                cg.Winner = cg.playersTwo;
            } else {cg.Winner = cg.playerOne;}

            System.out.println("Game Over!!");
            return;}
        newTurn();
        playingAgent.attack(playersTerritories,Soldiers_In_Hand); //to be used in controller
    }

    public void HumanAttack(territories attacking){ //used inn controller when player is human
        HumanAgent.HumanAttack(attacking);
    }

    public void HumanPlaceNewSoldiers(territories chosenTerritory){
        if(chosenTerritory.getOwner() == CurrentGame.currentTurn)
            HumanAgent.HumanPlaceArmy(chosenTerritory);

        else System.out.println("You Can't Add Soldiers To A Territory You Don't Own!");
    }



    public void placeNewSoldiers(){

        playingAgent.placeArmy(playersTerritories,Soldiers_In_Hand);  //to be used in controller for all agents
    }

    public BasicAgent getPlayingAgent() {
        return playingAgent;
    }












}
