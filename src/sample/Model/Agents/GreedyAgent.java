package sample.Model.Agents;

import sample.Model.Agents.Agents;
import sample.Model.Agents.BasicAgent;
import sample.Model.CurrentGame;
import sample.Model.territories;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class GreedyAgent extends BasicAgent implements Agents {
    public String name = "Greedy";
    territories minNeighbour;
   // int numOfExpansions = 0;

    @Override
    public void attack(ArrayList<territories> PlayersTerritories,int bonusArmies ) {

       // placeArmy(PlayersTerritories,bonusArmies);


        territories toAttack=getTerritoryGreedyAttack(PlayersTerritories,0);

        territories AttackWith = GetMaximumAttacker(PlayersTerritories,toAttack);

        Attacking(toAttack,AttackWith);



    }

    @Override
    public void placeArmy(ArrayList<territories> owned, int bonusArmies) {
        territories Placing = GetMinimumAttacker(owned);
        moveArmy(bonusArmies,Placing);
    }

    @Override
    public String getName() {
        return name;
    }















}