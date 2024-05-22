package com.example.quizzicalapp.application;



import com.example.quizzicalapp.entity.Player;
import com.example.quizzicalapp.entity.SortPlayersPlace;
import com.example.quizzicalapp.persistence.SerializedPlayerRepo;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerFactory {

    SerializedPlayerRepo repo = SerializedPlayerRepo.singleton();
    public static PlayerFactory exemplar = null;

    public static PlayerFactory singleton(){
        if(exemplar==null)
            exemplar = new PlayerFactory();
        return exemplar;
    }
    public Boolean newPlayer(String name){
        repo.read();
        if(repo.getPlayer(name)==null) {
            repo.addPlayer(new Player(name));
            repo.save();
            return true;
        } else
            return false;
    }
    public Boolean newCategory(String name,int order){
        return false;
    }
    public ArrayList<Player> getAllPlayers(){
        repo.read();
        return repo.getPlayers();
    }
    public boolean deletePlayer(String name){
        repo.read();
        boolean isDeleted = repo.removePlayer(name);
        repo.save();
        return isDeleted;
    }
    public Player getPlayer(String name){
        repo.read();
        return repo.getPlayer(name);
    }
    public void deleteAllPlayers() {
        repo.read();
        repo.players.removeAll(repo.players);
        repo.save();
    }

    public void addPointsToPlayer(String playerName, int points){
        repo.read();
        int newPoints = getPlayer(playerName).getTotalPoints() + points;
        getPlayer(playerName).setTotalPoints(newPoints);
        repo.save();

        repo.read();
        Collections.sort(repo.players,new SortPlayersPlace());
        int counter = 0;
        for (int i = 1; i < repo.players.size()+1; i++) {
            if(i>1&&repo.players.get(i-1).getTotalPoints()==repo.players.get(i-2).getTotalPoints()){
                repo.players.get(i-1).setPlace(repo.players.get(i-2).getPlace());
                counter++;
            }
            else{
                repo.players.get(i-1).setPlace(i-counter);
            }
        }
        repo.save();

    }


}
