package sample.Model.Agents;

import sample.Model.CurrentGame;
import sample.Model.territories;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PacifistAgent extends BasicAgent implements Agents {
    public String name = "Pacifist";



    public void placeArmy (ArrayList<territories> owned, int bonusArmies )
    {
        territories toPutOn = getMinTerritory(owned,0);
        moveArmy(bonusArmies,toPutOn);
    }
    public void attack (ArrayList<territories> Player_Territories,int bonusArmies)
    {


       /* placeArmy(Player_Territories,bonusArmies);

        PriorityQueue<territories> opponentNeighbours = getSortedAttackingNeighbours(Player_Territories);
        territories Attack = opponentNeighbours.poll();
        if(Attack==null){
            System.out.println("I can't attack\n");
            UpdateTurn();
            return;
        }


               if(getAttackingNeighbours(Attack.neighbours).size()==0){
                   System.out.println("I can't attack\n");
                   UpdateTurn();
                   return;
                 }

        territories AttackWith = getMostTerritory(getAttackingNeighbours(Attack.neighbours),0);

        if(AttackWith==null){
            System.out.println("I can't attack\n");
            UpdateTurn();
            return;
        }

        Attacking(Attack,AttackWith);*/


       placeArmy(Player_Territories,bonusArmies);

        territories AttackWith = GetMaximum(Player_Territories,0);

        territories Attack;

        if(getAttackingNeighbours(AttackWith.neighbours).size()==0) {
            System.out.println("I can't attack\n");
            UpdateTurn();
            return;
        }


        if(getAttackingNeighbours(AttackWith.neighbours).size()==1)
            Attack = getTerritory(getAttackingNeighbours(AttackWith.neighbours).get(0));

        else{
         Attack = getTerritory(getMinTerritory(getAttackingNeighbours(AttackWith.neighbours),0));}



        System.out.println("TARGETED TERRITORY: "+Attack.id);
        Attacking(Attack, AttackWith); //Throw Dice, if we win, we add it to current player territories and remove it from opposing*/


    }

    public String getName() {
        return name;
    }
}
