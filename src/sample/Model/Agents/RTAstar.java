package sample.Model.Agents;

import sample.Model.CurrentGame;
import sample.Model.territories;

import java.util.*;

public class RTAstar extends BasicAgent implements Agents  {
    String name = "RTA-star";
   // int numOfExpansions = 0;
    @Override
    public void attack(ArrayList<territories> PlayersTerritories, int bonusArmies) {
        placeArmy(PlayersTerritories,bonusArmies);

        territories toAttack =RTA_Star_Heuristics(getSortedAttackingNeighbours(PlayersTerritories));
        territories AttackWith = GetMaximumAttacker(PlayersTerritories,toAttack);

        Attacking(toAttack,AttackWith); //Throw Dice, if we win, we add it to current player territories and remove it from opposing




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

    public territories RTA_Star_Heuristics(PriorityQueue<territories> Current_Neighbours)
    {
        territories chosenTerritory = null;
        territories secondbest = null;

        float alpha= (float) 9999999999.99999999;   //instead of using infinity and using alpha for prunning
        HashMap<Integer,Float> TH = new HashMap<>(Current_Neighbours.size());
        for(territories current : Current_Neighbours) {

            float NumberOfAttackingNeighbours = getAttackingNeighbours(current.neighbours).size();
            TH.put(current.id,(current.noOfSoldiers+NumberOfAttackingNeighbours)/(NumberOfAttackingNeighbours+1));
        }
        SortHashMap(TH); // sort the hashmap
        float secondval = 0;
        int secondkey=0;
        Map.Entry<Integer,Float> entry = TH.entrySet().iterator().next();
        int key= entry.getKey();
        int id_Of_chosen = key;
        float chosen_fn= TH.get(id_Of_chosen);
        for(territories getTerritory :Current_Neighbours ){
            if(getTerritory.id ==id_Of_chosen)
                chosenTerritory = getTerritory;
        }
        Iterator itr = TH.entrySet().iterator();
        if(TH.size()==1)
            return chosenTerritory;
        for(int i=0;i<2;i++) {
            Map.Entry mapElement = (Map.Entry)itr.next();
            secondval= (float) mapElement.getValue();
            secondkey= (int) mapElement.getKey();}
        TH.put(key,secondval);
        System.out.println("the first choice heuristic is:"+chosen_fn);
        System.out.println("the second choice heuristic is:"+secondval);
        if(chosen_fn>secondval)
        {
            for(territories getTerritory :Current_Neighbours ){
                if(getTerritory.id ==secondkey)
                    chosenTerritory = getTerritory;
            }
        }

        return chosenTerritory;
    }











}
