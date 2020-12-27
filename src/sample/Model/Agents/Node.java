package sample.Model.Agents;

import sample.Model.territories;

import java.util.ArrayList;

public class Node {
    int to_attack=0;
    ArrayList<territories> player1;
    ArrayList<territories> player2;
    Node parent;
    int her = 0;
    ArrayList<Node> children = new ArrayList<>();
    public Node(ArrayList<territories> player1, ArrayList<territories> player2, Node parent,int to_attack) {
        this.player1 = player1;
        this.player2 = player2;
        this.parent  = parent;
        this.to_attack = to_attack;
    }
    public void addChildren(ArrayList<territories> player1, ArrayList<territories> player2, Node parent, int to_attack){
        Node newChild = new Node(player1,player2,parent,to_attack);
        children.add(newChild);
    }



}