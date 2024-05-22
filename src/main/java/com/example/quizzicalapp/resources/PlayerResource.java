package com.example.quizzicalapp.resources;

import com.example.quizzicalapp.application.PlayerFactory;
import com.example.quizzicalapp.entity.Player;
import com.example.quizzicalapp.persistence.SerializedPlayerRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/player")
public class PlayerResource {
    private PlayerFactory facade = PlayerFactory.singleton();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Player> getAllPlayers(){
        return facade.getAllPlayers();
    }

    @GET
    @Path("/{playername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player getPlayer(@PathParam("playername")String name){
        if(facade.getPlayer(name)==null)
            facade.newPlayer(name);

        return facade.getPlayer(name);

    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playername}")
    public Player addCurrentAnswer(@PathParam("playername")String name,String answer){
        SerializedPlayerRepo.singleton().read();
        facade.getPlayer(name).setCurrentAnswer(answer);
        SerializedPlayerRepo.singleton().save();

        return facade.getPlayer(name);
    }



}
