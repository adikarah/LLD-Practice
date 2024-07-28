package main.snakeandladder.models;

import java.util.UUID;

public class Player {

    private UUID playerId;
    private String playerName;
    private int position;

    public Player(String playerName){
        this.playerId = UUID.randomUUID();
        this.playerName = playerName;
        this.position = 0;
    }

    public int getPosition(){
        return position;
    }

    public void movePosition(int position){
        this.position = position;
    }

    public String getPlayerName(){
        return playerName;
    }

}
