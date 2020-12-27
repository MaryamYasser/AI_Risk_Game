package sample.Model.Agents;


import sample.Model.*;

import java.util.*;

public class BasicAgent implements Agents{

    CurrentGame currentGame = CurrentGame.getInstance();
    public int numOfExpansions=0;

    public territories getMinTerritory(ArrayList<territories> ownedTerritories,int position)


    {

        ownedTerritories.sort(new TerritoryComparison());

        return ownedTerritories.get(position);



    }


    public territories getMostTerritory(ArrayList<territories> ownedTerritories,int position)
    {

       ownedTerritories.sort(new TerritoryComparison());
       Collections.reverse(ownedTerritories);


      // return GetMaximum(ownedTerritories);
        return ownedTerritories.get(position);
    }








    public int Attacking(territories territoryToAttack, territories AttackWith) {

        int toBe = AttackWith.noOfSoldiers/2;
        int soldeirsToBe = AttackWith.noOfSoldiers - toBe;



        if(!checkAdjacency(territoryToAttack)){
            System.out.println("You Can't Attack! You Can Only Attack Bordering Territories");
            UpdateTurn();
            return 0;

        }

        if (territoryToAttack.getOwner() == (CurrentGame.currentTurn)) {
                System.out.println("You Are Trying To Attack Your Own Territory!");
                UpdateTurn();
                return 0;

            }




        else {
            Dice firstAtt = new Dice();
          // int oneatt = firstAtt.getValue();
            int oneatt = 7;
            Dice secondAtt = new Dice();
          // int twoatt = secondAtt.getValue();
           int twoatt = 7;
            Dice firstDef = new Dice();
            int onedef = firstDef.getValue();
            Dice secondDef = new Dice();
            int twodef = secondDef.getValue();

            if (oneatt + twoatt > onedef + twodef || territoryToAttack.getOwner()==0) {

                System.out.println("TARGETED TERRITORY: "+territoryToAttack.id);
                System.out.println("ATTACKING TERRITORY: "+AttackWith.id);

                TerritoryTakeOver(territoryToAttack);
                territoryToAttack.setOwner((CurrentGame.currentTurn));

                AttackWith.noOfSoldiers = toBe;
                territoryToAttack.noOfSoldiers = soldeirsToBe;
                UpdateTurn();

            }
            else {
                System.out.println("ATTACK FAILED\n");
                UpdateTurn();
                return 0;
            }
        }

        return 1;




    }


    public void moveArmy (int solidersPlaced,territories toPutOn)
    {

        toPutOn.setNoOfSoldiers(toPutOn.getNoOfSoldiers()+solidersPlaced);
        System.out.println("NEW NUMBER OF SOLDIERS in Territory "+ toPutOn.id +"\n");
        System.out.println(toPutOn.getNoOfSoldiers());
    }

    public void UpdateTurn(){
        if (CurrentGame.currentTurn == 1) CurrentGame.currentTurn = 2;
        else CurrentGame.currentTurn = 1;
    }







    public void TerritoryTakeOver(territories exchange){


        if (CurrentGame.currentTurn==1) {

            currentGame.PlayerTwoTerritories.remove(exchange);
            currentGame.PlayerOneTerritories.add(exchange);
        }

        if (CurrentGame.currentTurn==2) {


            currentGame.PlayerOneTerritories.remove(exchange);
            currentGame.PlayerTwoTerritories.add(exchange);
        }

    }


    public boolean checkAdjacency(territories toAttack){ //sees if my attackingGoal contains me as a neighbour
        for(territories neighbouring: toAttack.neighbours){
            if(getTerritory(neighbouring).getOwner() == CurrentGame.currentTurn)
                return true;
        }
        return false;
    }

    public boolean checkNeighbourOwnership(territories toAttack){ //sees if one my neighbours could be attacked
        for(territories neighbouring: toAttack.neighbours){
            if(getTerritory(neighbouring).getOwner() != CurrentGame.currentTurn)
                return true;
        }
        return false;
    }






    public territories getTerritory(territories neighbouring){
        for(territories current: currentGame.getTerritories()){
            if(current.id == neighbouring.id)
                return current;}
        return null;
    }

    public ArrayList<territories> getNeighbouringTerritories(ArrayList<territories> notCompleteNeighbours){ //get Complete Neighbours

        ArrayList<territories> completeNeighbours = new ArrayList<>();
        for(territories current: notCompleteNeighbours)
            completeNeighbours.add(getTerritory(current));
        return completeNeighbours;
    }

    public ArrayList<territories> getAttackingNeighbours(ArrayList<territories> notCompleteNeighbours){ //get Complete Attacking  Neighbours

        ArrayList<territories> completeNeighbours = new ArrayList<>();
        territories test;
        for(territories current: notCompleteNeighbours){
             test = getTerritory(current);
             if (test.getOwner()!=CurrentGame.currentTurn)
            completeNeighbours.add(getTerritory(current));}
        return completeNeighbours;
    }

    public ArrayList<territories> get_NonAttackingNeighbours(ArrayList<territories> notCompleteNeighbours){
        //get Complete Non-Attacking  Neighbours

        ArrayList<territories> completeNeighbours = new ArrayList<>();
        territories test;
        for(territories current: notCompleteNeighbours){
            this.numOfExpansions++;
            test = getTerritory(current);
            if (test.getOwner()==CurrentGame.currentTurn)
                completeNeighbours.add(getTerritory(current));

        }
        return completeNeighbours;
    }






    public territories GetMaximum(ArrayList<territories> Player_Territories,int position){ //get maximum territory with attackable neighbour


        if(Player_Territories.size()==1)
            return Player_Territories.get(0);

        territories AttackWith = getMostTerritory(Player_Territories,position); //get territory with most Armies

        int i = 1;
        int j = 1;

        while(getAttackingNeighbours(AttackWith.neighbours).size()==0 && Player_Territories.size()>j){
            AttackWith = getMostTerritory(Player_Territories,j);
            j+=1;
        }

        while(!checkNeighbourOwnership(AttackWith) && Player_Territories.size()>i){
            AttackWith = getMostTerritory(Player_Territories,i); //get territory with most Armies
            i++;}


        System.out.println("ATTACKING TERRITORY: "+AttackWith.id);

        return AttackWith;


    }





    public territories GetMaximumAttacker(ArrayList<territories> owned,territories toAttack){


       // toAttack =getTerritoryGreedyAttack(owned,0);
        ArrayList<territories> maxAttacker = get_NonAttackingNeighbours(toAttack.neighbours);


        int i = 1;

        while (maxAttacker.size()==0 && owned.size()>i) {
            toAttack = getTerritoryGreedyAttack(owned, i);
            maxAttacker = get_NonAttackingNeighbours(toAttack.neighbours);
            i+=1;
        }


        territories max = getMostTerritory(maxAttacker,0);
        return max;
    }

    public territories GetMinimumAttacker(ArrayList<territories> owned){
       territories minNeighbour = getTerritoryGreedyAttack(owned,0);
        //Place armies in territory with
        // least noOfSoldiers which has a neighbour with has small noOfSoldiers (better possibility for attack)
        ArrayList<territories> minAttacker = get_NonAttackingNeighbours(minNeighbour.neighbours);

        int i = 1;

        while (minAttacker.size()==0 && owned.size()>i) {
            minNeighbour = getTerritoryGreedyAttack(owned, i);
            minAttacker = get_NonAttackingNeighbours(minNeighbour.neighbours);
            i+=1;
        }

        territories min = getMinTerritory(minAttacker,0);
        return min;
    }


    public PriorityQueue<territories> getSortedAttackingNeighbours (ArrayList<territories> ownedTerritories) {

        PriorityQueue<territories> minNeighbours=new PriorityQueue<>((o1, o2)->o1.getNoOfSoldiers()-o2.getNoOfSoldiers());
        ArrayList<territories> neighbours_current;

        for(territories playerTerritory:ownedTerritories){
            neighbours_current=getAttackingNeighbours(playerTerritory.neighbours); //GET ATTACKING NEIGHBOURS
            for(territories each: neighbours_current) //FOR EACH ATTACKING NEIGHBOUR CHECK IF ADDED TO PRIORITY OF ALL NEIGHBOURS QUEUE
            {    this.numOfExpansions++;
                if(!minNeighbours.contains(each))
                    minNeighbours.add(each); }//ADD
        }

        return minNeighbours;
    }


    public territories getTerritoryGreedyAttack (ArrayList<territories> ownedTerritories,int poss) {
        //gets all attacking neighbours sorted in ascending order from their number of soldiers
        // least border security heuristic
        PriorityQueue<territories> minNeighbours = getSortedAttackingNeighbours(ownedTerritories);
        territories minNeighbour=minNeighbours.poll();

        for(int i = 0;i<poss;i++){
         minNeighbour=  minNeighbours.poll();}
        return minNeighbour;
    }





    public void SortHashMap(HashMap<Integer,Float> TH){
        TreeMap<Integer, Float> sortedMap = new TreeMap<>();
        sortedMap.putAll(TH);
    }


    @Override
    public void attack(ArrayList<territories> TerritoryToAttack, int bonusArmies) {

    }

    @Override
    public void placeArmy(ArrayList<territories> owned, int bonusArmies) {

    }

    @Override
    public String getName() {
        return null;
    }

    public ArrayList<territories> getTerritoryToAttackFromOwned (ArrayList<territories> ownedTerritories)
    {   ArrayList<territories> allneighbours= new ArrayList<>();
        for(territories each:ownedTerritories)
        {
            ArrayList<territories> now = getAttackingNeighbours(each.neighbours);
            for( territories one: now)
            {
                if(!allneighbours.contains(one))
                {
                    if(one.id!=0)
                    allneighbours.add(one);
                }

            }
        }
        return allneighbours;

    }
}
