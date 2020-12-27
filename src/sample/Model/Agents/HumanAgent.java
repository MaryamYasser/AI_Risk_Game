package sample.Model.Agents;

import sample.Model.CurrentGame;
import sample.Model.territories;
import sample.View.USAMap;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanAgent extends BasicAgent implements Agents {
     String name = "Human";
    @Override
    public void attack(ArrayList<territories> TerritoryToAttack,int bonusArmies) {

    }

    public void HumanAttack(territories chosenTerritoryToAttack){

      if(chosenTerritoryToAttack==null){
          System.out.println("You Didn't Choose Territory To Attack\n");
          UpdateTurn();
      return;}

         if(getAttackingNeighbours(chosenTerritoryToAttack.neighbours).size()==0){
            System.out.println("You Can't Attack This Territory\n");
            UpdateTurn();
            return;}


              territories AttackWith;
          if(getAttackingNeighbours(chosenTerritoryToAttack.neighbours).size()==1)
              AttackWith = getTerritory(getAttackingNeighbours(chosenTerritoryToAttack.neighbours).get(0));




           else{ AttackWith = getMostTerritory(getAttackingNeighbours(chosenTerritoryToAttack.neighbours),0);}


          Attacking(chosenTerritoryToAttack,AttackWith);
        System.out.println("New Owner is Player " +chosenTerritoryToAttack.getOwner());
      }
        //territories AttackWith = getMostTerritory(getAttackingNeighbours(chosenTerritoryToAttack.neighbours),0);



       //Throw Dice, if we win, we add it to current player territories and remove it from opposing



        //Attacking(chosenTerritoryToAttack,0);



       //CurrentGame.currentTurn=2;


    @Override
    public void placeArmy(ArrayList<territories> owned, int bonusArmies) {
        // System.out.println("Enter Number Of Armies To Put:\n");


    }

    public void HumanPlaceArmy(territories chosenTerritory) {
       System.out.println("Enter Number Of Armies To Put:\n");
        Scanner scanner = new Scanner(System.in);
        int BonusArmies = scanner.nextInt();
        moveArmy(BonusArmies,chosenTerritory);




    }

    @Override
    public String getName() {
        return name;
    }
}
