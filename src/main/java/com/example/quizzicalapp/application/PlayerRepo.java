package com.example.quizzicalapp.application;


import com.example.quizzicalapp.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;

;

public abstract class PlayerRepo implements Serializable {
    static final long serialVersionUID = 102L;


     public ArrayList<Player> players = new ArrayList<>();

    abstract protected void save();
    abstract protected void read();
    
    public void addPlayer(Player p){
        this.players.add(p);
    }



    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String name){
        for (Player p:players
             ) {
            if(p.getName().equals(name))
                return p;


        }
        return null;
    }

    public boolean removePlayer(String name){
        int count = 0;
        for (Player p:players
        ) {
            if(p.getName().equals(name)){
                players.remove(count);
                return true;
            }
                count++;
        }
     return false;
    }


}
