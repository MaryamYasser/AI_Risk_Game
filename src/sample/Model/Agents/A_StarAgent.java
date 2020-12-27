package sample.Model.Agents;

import sample.Model.CurrentGame;
import sample.Model.territories;

import java.util.*;

public class A_StarAgent extends BasicAgent implements Agents {

    public String name = "A_Star";
    territories minNeighbour;
   // public int numOfExpansions = 0;

    public void attack(ArrayList<territories> PlayersTerritories,int bonusArmies ) {

        placeArmy(PlayersTerritories,bonusArmies);

        territories toAttack =A_Star_Heuristics(getSortedAttackingNeighbours(PlayersTerritories));

        territories AttackWith = GetMaximumAttacker(PlayersTerritories,toAttack);
        Attacking(toAttack,AttackWith);





    }

    public void placeArmy(ArrayList<territories> owned, int bonusArmies) {



        territories Placing = GetMinimumAttacker(owned);
        moveArmy(bonusArmies,Placing);
    }

    @Override
    public String getName() {
        return name;
    }


    public territories A_Star_Heuristics(PriorityQueue<territories> Current_Neighbours){
        //current neighbours = my current attacking neighbours of all
        territories chosenTerritory = null;
        HashMap<Integer,Float> TH = new HashMap<>(Current_Neighbours.size());

        for(territories current : Current_Neighbours) {
            //hashmap with int = territory id and float = chosen heuristic
            //chosen heuristic = no0fSoldiers + noOf Attacking Neighbours / noOfAttacking Neighbours
            //example = attack possibility with 1 soldier but has only 2 possible attacking neighbours or
            // attack possibility with 3 soldiers but has 4 attacking possibilites



             float NumberOfAttackingNeighbours = getAttackingNeighbours(current.neighbours).size();
            TH.put(current.id,(current.noOfSoldiers+NumberOfAttackingNeighbours)/(NumberOfAttackingNeighbours+1));
        }




        SortHashMap(TH); // sort the hashmap
        Map.Entry<Integer,Float> entry = TH.entrySet().iterator().next();
        int key= entry.getKey();
        int id_Of_chosen = key;

        for(territories getTerritory :Current_Neighbours ){
            if(getTerritory.id ==id_Of_chosen)
               chosenTerritory = getTerritory;  }



        return chosenTerritory;
    }





        public void SortHashMap(HashMap<Integer,Float> TH){
            TreeMap<Integer, Float> sortedMap = new TreeMap<>();
            sortedMap.putAll(TH);
    }





}
