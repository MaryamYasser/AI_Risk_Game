package sample.Model.Agents;

import sample.Model.CurrentGame;
import sample.Model.territories;

import java.util.ArrayList;
import java.util.List;

public class AgressiveAgent extends BasicAgent implements Agents {
    public String name = "Aggressive";


    public void placeArmy (ArrayList<territories> owned, int bonusArmies )
    {
        territories Placing = getMostTerritory(owned,0);
        moveArmy(bonusArmies,Placing);
    }

    public void attack (ArrayList<territories> Player_Territories,int bonusArmies)
    {

        //  placeArmy(Player_Territories,bonusArmies);
        territories Attack;
        territories AttackWith = GetMaximum(Player_Territories,0); //get maximum territory to attack with


        if(getAttackingNeighbours(AttackWith.neighbours).size()==1)
            Attack = getTerritory(getAttackingNeighbours(AttackWith.neighbours).get(0));




        else {
         Attack = getTerritory(getMostTerritory(getAttackingNeighbours(AttackWith.neighbours),0));}
        //get neighbour with least no of soldiers







        System.out.println("TARGETED TERRITORY: "+Attack.id);



      Attacking(Attack,AttackWith); //Throw Dice, if we win, we add it to current player territories and remove it from opposing


        //Attacking(Attack); //Throw Dice, if we win, we add it to current player territories and remove it from opposing
    }

    public String getName() {
        return name;
    }
}
