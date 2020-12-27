package sample.Model;

import java.util.ArrayList;

public  class allTerritories extends territories{

    static ArrayList<territories> egyptTerritories = new ArrayList<>();
    static ArrayList<territories> USTerritories = new ArrayList<>();





    public allTerritories() {

    }

    public allTerritories(Integer id, int[] neighbours,int xpos,int ypos) {
        super(id, neighbours);
        this.xpos=xpos;
        this.ypos=ypos;
    }
    static {egyptTerritories.add(new allTerritories(1,new int[] {2,3,4,13,14,15,16},220,480));
        egyptTerritories.add(new allTerritories(2,new int[] {1,3,7},175,100));
        egyptTerritories.add(new allTerritories(3,new int[] {1,2,4,6,7,8},252,256));
        egyptTerritories.add(new allTerritories(4,new int[] {1,3,5,8,13},347,270));
        egyptTerritories.add(new allTerritories(5,new int[] {6,8,4,13,14,15,16},550,340));
        egyptTerritories.add(new allTerritories(6,new int[] {5,9,10,11,8},465,158));
        egyptTerritories.add(new allTerritories(7,new int[] {2,3,12},374,99));
        egyptTerritories.add(new allTerritories(8,new int[] {5,3,4,6},397,195));
        egyptTerritories.add(new allTerritories(9,new int[] {11,6,10},610,98));
        egyptTerritories.add(new allTerritories(10,new int[] {9},627,203));
        egyptTerritories.add(new allTerritories(11,new int[] {12,6,9},482,90));
        egyptTerritories.add(new allTerritories(12,new int[] {7,11},439,50));
        egyptTerritories.add(new allTerritories(13,new int[] {1,4,5,14},437,335));
        egyptTerritories.add(new allTerritories(14,new int[] {13,5,15,1},493,405));
        egyptTerritories.add(new allTerritories(15,new int[] {1,5,14,16},553,491));
        egyptTerritories.add(new allTerritories(16,new int[] {1,5,15},548,636));}

    private void  createEgyptTerritories(){

    }

    public ArrayList<territories> getEgyptTerritories(){

        return egyptTerritories;
    }

   static {    USTerritories.add(new allTerritories(1,new int [] {2,16,17,33},374,398));
        USTerritories.add(new allTerritories(2,new int [] {3,7,1,16},266,341));
        USTerritories.add(new allTerritories(3,new int [] {4,5,6,2,7},172,323));
        USTerritories.add(new allTerritories(4,new int [] {10,5,3},65,268));
        USTerritories.add(new allTerritories(5,new int [] {10,4,9,6,3},106,215));
        USTerritories.add(new allTerritories(6,new int [] {5,9,8,3,7,2},182,234));
        USTerritories.add(new allTerritories(7,new int [] {2,3,6,8,14,15,16},282,243));


        USTerritories.add(new allTerritories(8,new int [] {6,7,9,11,13,14},261,159));
        USTerritories.add(new allTerritories(9,new int [] {5,6,8,11,10,34},155,133));
        USTerritories.add(new allTerritories(10,new int [] {9,4,5,34},60,106));
        USTerritories.add(new allTerritories(11,new int [] {8,9,12,13,34},236,72));
        USTerritories.add(new allTerritories(12,new int [] {11,13,20},365,73));


        USTerritories.add(new allTerritories(13,new int [] {8,11,12,14,19,20},359,134));
        USTerritories.add(new allTerritories(14,new int [] {7,8,13,19,18,15},374,199));
        USTerritories.add(new allTerritories(15,new int [] {7,14,18,16},382,255));
        USTerritories.add(new allTerritories(16,new int [] {15,17,18,1,2,7},416,324));

        USTerritories.add(new allTerritories(17,new int [] {16,18,32,33,1,30},470,336));
        USTerritories.add(new allTerritories(18,new int []{14,15,16,17,19,22,30},480,260));
        USTerritories.add(new allTerritories(19,new int [] {13,14,15,18,22,21,20},464,187));
        USTerritories.add(new allTerritories(20,new int [] {12,13,19,21},457,118));


        USTerritories.add(new allTerritories(21,new int [] {19,20,22},525,145));
        USTerritories.add(new allTerritories(22,new int [] {18,19,21,27,30,32},530,225));
        USTerritories.add(new allTerritories(23,new int []{24,31},697,465));
        USTerritories.add(new allTerritories(24,new int [] {23,31,35,30,36},652,365));
        USTerritories.add(new allTerritories(25,new int []{28},806,76));


        USTerritories.add(new allTerritories(26,new int [] {22,27},591,149));
        USTerritories.add(new allTerritories(27,new int [] {26,22,30,37,29},621,208));
        USTerritories.add(new allTerritories(28,new int []{25,29},726,141));
        USTerritories.add(new allTerritories(29,new int [] {27,37,28,30},691,186));
        USTerritories.add(new allTerritories(30,new int []{29,37,36,35,24,31,32,17,18,22,27 },595,260));


        USTerritories.add(new allTerritories(31,new int [] {32,24,30,23},583,351));
        USTerritories.add(new allTerritories(32,new int [] {33,17,31,22,30},525,364));
        USTerritories.add(new allTerritories(33,new int []{1,17,32},495,412));
        USTerritories.add(new allTerritories(34,new int [] {10,9},107,41));
        USTerritories.add(new allTerritories(35,new int []{24,36},690,330));

        USTerritories.add(new allTerritories(36,new int [] {30,35,37},700,290));
        USTerritories.add(new allTerritories(37,new int []{29,27,30,36},704,245));}

    private void  createUSTerritories(){






    }


    public ArrayList<territories> getUSTerritories(){

        return USTerritories;
    }


}
