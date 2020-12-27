package sample.Model.Agents;


import sample.Model.CurrentGame;
import sample.Model.territories;

import java.util.ArrayList;
import java.util.List;

public class PassiveAgent extends BasicAgent implements Agents {

    public PassiveAgent(){
        super();
    }
    public String name = "Passive";


    public void placeArmy (ArrayList<territories> owned, int bonusArmies )
    {
        territories toPutOn = getMinTerritory(owned,0);
        moveArmy(bonusArmies,toPutOn);
    }

    public void attack(ArrayList<territories> owned,int bonusArmies){
        placeArmy(owned,bonusArmies);
        System.out.println("I Don't Attack");
       UpdateTurn();
    }

    public String getName() {
        return name;
    }
}
