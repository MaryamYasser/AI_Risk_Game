package sample.Model.Agents;


import sample.Model.territories;

import java.util.ArrayList;
import java.util.PriorityQueue;



public class MiniMax extends BasicAgent implements Agents {
    String name = "Minimax player";
    sample.Model.Agents.Node root;
    int isGameOver = 0;
    ArrayList<ArrayList<territories>> twos = new ArrayList<>();

    @Override
    public void attack(ArrayList<territories> playerterritories, int bonusArmies) {
        ArrayList<territories> opponentTerritory = new ArrayList<>();
        opponentTerritory = getTerritoryToAttackFromOwned(playerterritories);
        createTree(playerterritories, opponentTerritory);
        Node toAttackit = MiniMax(root,2,1000,-1000,true);

        int attack_this_now = toAttackit.parent.to_attack; // id of chossen move y'all
        territories toAttack = null;
        for(territories each: opponentTerritory)
        {
            if(each.id ==attack_this_now)
            {
                toAttack=each;
            }
        }
        territories Attacking = GetMaximum(playerterritories,0);
        Attacking(toAttack,Attacking);
    }

    @Override
    public void placeArmy(ArrayList<territories> owned, int bonusArmies) {

        territories Placing = getMostTerritory(owned, 0);
        moveArmy(bonusArmies, Placing);
    }

    @Override
    public String getName() {
        return name;
    }


    Node MiniMax(Node position, int depth, int alpha, int beta, boolean maximizingPlayer) {

        if (depth == 0 || isGameOver == 1 || position.children.size() == 0)
            return position;
        if (maximizingPlayer) {
            Node Max = new Node(null, null, null,-9);
            Max.her = -1000;
            for (int i = 0; i < position.children.size(); i++) {
                Node newNode = MiniMax(position.children.get(i), depth - 1, alpha, beta, false);
                Max = maxher(Max, newNode);
                alpha = Math.max(alpha, newNode.her);
                if (beta <= alpha)
                    break;
            }
            return Max;
        } else {
            Node Min = new Node(null, null, null,-9);
            Min.her = 1000;
            for (int i = 0; i < position.children.size(); i++) {
                Node newNode = MiniMax(position.children.get(i), depth - 1, alpha, beta, true);
                Min = minher(Min, newNode);
                beta = Math.min(beta, newNode.her);
                if (beta <= alpha)
                    break;
            }
            return Min;
        }
    }

    private Node minher(Node her, Node her1) {
        her.her = her.player1.size();
        her1.her = her1.player1.size();
        if (her.her > her1.her)
            return her1;
        else return her;

    }

    private Node maxher(Node her, Node her1) {
        her.her = her.player1.size();
        her1.her = her1.player1.size();
        if (her.her < her1.her)
            return her1;
        else return her;
    }


    void createTree(ArrayList<territories> playerterritories, ArrayList<territories> opponantterritories) {

        root = new Node(playerterritories, opponantterritories, null,-1);

        PriorityQueue<territories> tobefaked = getSortedAttackingNeighbours(playerterritories);
        int size = tobefaked.size();
        for (int i = 0; i < size; i++) {
            territories temp = tobefaked.poll();
            ArrayList<ArrayList<territories>> BothPlayers = fakingit(playerterritories,opponantterritories,temp);
            root.addChildren(BothPlayers.get(0), BothPlayers.get(1), root,temp.id);
        }
        for (int i = 0; i < root.children.size(); i++) {
            ArrayList<ArrayList<territories>> BothPlayers = fakeAttack(playerterritories, opponantterritories);
            root.children.get(i).addChildren(BothPlayers.get(0), BothPlayers.get(1), root.children.get(i),-2);
        }
    }


    public ArrayList<ArrayList<territories>> fakeAttack(ArrayList<territories> PlayerTerritory, ArrayList<territories> OpponentTerritory) {
        territories Attack;
        territories AttackWith = GetMaximum(PlayerTerritory, 0); //get maximum territory to attack with
        if (getAttackingNeighbours(AttackWith.neighbours).size() == 1)
            Attack = getTerritory(getAttackingNeighbours(AttackWith.neighbours).get(0));
        else {
            Attack = getTerritory(getMostTerritory(getAttackingNeighbours(AttackWith.neighbours), 0));
        }
        if (Attacking(Attack, AttackWith) == 1)//Throw Dice, if we win, we add it to current player territories and remove it from opposing
        {
            OpponentTerritory.remove(Attack);
            PlayerTerritory.add(Attack);
        }
        ArrayList<ArrayList<territories>> tobereturned = new ArrayList<ArrayList<territories>>();
        tobereturned.add(PlayerTerritory);
        tobereturned.add(OpponentTerritory);
        return tobereturned;

    }

    // receives a territory that he can attack then fake attacks
    private ArrayList<ArrayList<territories>> fakingit(ArrayList<territories> playerterritories, ArrayList<territories> opponantterritories, territories peek) {
        playerterritories.add(peek);
        opponantterritories.remove(peek);

        ArrayList<ArrayList<territories>> tobereturned = new ArrayList<ArrayList<territories>>();
        tobereturned.add(playerterritories);
        tobereturned.add(opponantterritories);

        return tobereturned;
    }

}
