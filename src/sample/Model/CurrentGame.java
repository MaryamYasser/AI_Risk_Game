package sample.Model;

import sample.Model.Agents.AgentsFactory;

import java.util.ArrayList;
import java.util.Random;

 public class CurrentGame {
     public static CurrentGame currentGame=new CurrentGame();
     public static boolean GameOver = false;
     public  Players Winner;

     private CurrentGame(){
         territories = new ArrayList<>();
     }


     public static CurrentGame getInstance(){
         return currentGame;
     }
    String location;
    ArrayList<territories> territories;

  public static int currentTurn=1; //PLAYER 1/PLAYER 2
  public static String currentMode; //ATTACK // PLACE ARMIES
     public int numOfMoves = 0;
     public int numOfMoves1 = 0;
     public int numOfMoves2 = 0;

     public territories human_Clicked;




   public  ArrayList<territories> PlayerOneTerritories = new ArrayList<>();
    public  ArrayList<territories> PlayerTwoTerritories = new ArrayList<>();


   public String [] availableLocations = {"Egypt","USA"};



  public  Players playerOne ;
  public  Players playersTwo;

    static AgentsFactory agentsFactory = new AgentsFactory();



   ArrayList<territories> UnusedTerritories = new ArrayList<>();

    public  Players getPlayerOne() {
        return playerOne;
    }

    public  void setPlayerOne(String one) {


        playerOne = new Players(agentsFactory.getAgent(one),1);
         playerOne.setPlayersTerritories(PlayerOneTerritories);

    }

    public  Players getPlayersTwo() {
        return playersTwo;
    }

    public  void setPlayersTwo(String two) {
        playersTwo = new Players(agentsFactory.getAgent(two),2);
       playersTwo.setPlayersTerritories(PlayerTwoTerritories);

    }




    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }



    public void setTerritories(ArrayList<sample.Model.territories> territories) {
        this.territories = territories;
    }

    public ArrayList<sample.Model.territories> getTerritories() {
        return this.territories;
    }


    int i =0;
    public void randomizeArmiesPlacement(int bound){

        Random randomNumber = new Random();
        Random random = new Random();
        int initialArmies = 20;
        while(initialArmies>0){
           int ran = randomNumber.nextInt(bound)+1;
            i = random.nextInt(territories.size());
             if(ran-initialArmies<=0 && !PlayerOneTerritories.contains(territories.get(i))){

                territories.get(i).noOfSoldiers=ran;
                PlayerOneTerritories.add(territories.get(i));
                territories.get(i).setOwner(1);
                initialArmies=initialArmies-territories.get(i).noOfSoldiers;
            }



        }

    }

    public void randomizeSecondArmiesPlacement(int bound){
        i=0;
        Random randomNumber = new Random ();
        Random random = new Random();
        int initialArmies = 20;
        while(initialArmies>0) {
            i = random.nextInt(territories.size());
            int ran = randomNumber.nextInt(bound)+1;
            if(ran-initialArmies<=0 && !PlayerOneTerritories.contains(territories.get(i))
                    && !PlayerTwoTerritories.contains(territories.get(i))){

                territories.get(i).noOfSoldiers=ran;
                PlayerTwoTerritories.add(territories.get(i));
                territories.get(i).setOwner(2);
                initialArmies=initialArmies-territories.get(i).noOfSoldiers;

            }


           /* UnusedTerritories = territories;
            ArrayList used = PlayerOneTerritories;
            used.addAll(PlayerTwoTerritories);
            UnusedTerritories.removeAll(used); // Get Unused Territories*/


        }}


        public void GameOn(){





        if (currentTurn ==1) {
            numOfMoves1++;

            if (playerOne.getPlayingAgent().getName().equalsIgnoreCase("Human")) {
                System.out.println("Waiting...\n");
            }

            //  playerOne.HumanAttack(human_Clicked);


            else playerOne.attack();


        }
        else
            playersTwo.attack();
        }











}
