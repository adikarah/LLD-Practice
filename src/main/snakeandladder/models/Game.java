package main.snakeandladder.models;

import java.util.Queue;

public class Game {

    private Board board;
    private Queue<Player> players;
    private Dice dice;

    public Game(Board board, Queue<Player> players, Dice dice) {
        this.board = board;
        this.players = players;
        this.dice = dice;
    }

    public Board getBoard() {
        return board;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public Dice getDice() {
        return dice;
    }
}
