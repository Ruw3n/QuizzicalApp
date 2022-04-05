package com.example.quizzicalapp.main;


import com.example.quizzicalapp.application.PlayerFactory;
import com.example.quizzicalapp.entity.Player;

public class Main {
    public static void main (String[] args){
        /*
        PlayerFactory.singleton().addPointsToPlayer("Lovebirds",69);
        PlayerFactory.singleton().addPointsToPlayer("Meerkats",23);
        PlayerFactory.singleton().addPointsToPlayer("Thanh My",20000);
        PlayerFactory.singleton().addPointsToPlayer("Ruwen",-1);
        */




        for (Player p: PlayerFactory.singleton().getAllPlayers()

             ) {
            System.out.println(p.getName() +"\n" +p.getPlace() +"\n" + p.getCurrentAnswer()+ "\n"+p.getTotalPoints()+"\n\n\n");
        }











    }
}
