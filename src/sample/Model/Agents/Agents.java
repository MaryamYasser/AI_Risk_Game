package sample.Model.Agents;

import sample.Model.territories;

import java.util.ArrayList;

public interface Agents {


    public void attack(ArrayList<territories> TerritoryToAttack,int bonusArmies);
    public void placeArmy(ArrayList<territories> owned,int bonusArmies);
    public String getName();
}
