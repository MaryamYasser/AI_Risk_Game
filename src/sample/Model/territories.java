package sample.Model;

import java.util.ArrayList;
import java.util.Comparator;

public class territories  {

    public int xpos;
    public int ypos;
    public  Integer id;

    int owner= 0;

    public int getNoOfSoldiers() {
        return noOfSoldiers;
    }

    public void setNoOfSoldiers(int noOfSoldiers) {
        this.noOfSoldiers = noOfSoldiers;
    }

    public int noOfSoldiers;
    public ArrayList<territories> neighbours;


    public territories(){}
    public territories( Integer id) {
        this.id = id;
    }

    public int getXpos() {
        return this.xpos;
    }

    public int getYpos() {
        return this.ypos;
    }


    public territories(Integer id, int [] neighbours){
        this.neighbours=new ArrayList<>();
        this.noOfSoldiers=0;

        this.id=id; //this could be removed?
        for(int i= 0;i<neighbours.length;i++){
            this.neighbours.add(new territories(neighbours[i]));
        }


    }


    public ArrayList<territories> getNeighbours() {
        return neighbours;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void PrintNeighbours(){
        for(int i =0;i<this.neighbours.size();i++){
            System.out.println(neighbours.get(i).id);
        }
    }

    public int getOwner() {
        return owner;
    }



}

