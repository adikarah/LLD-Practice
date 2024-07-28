package main.snakeandladder;

import main.snakeandladder.models.*;
import main.snakeandladder.services.GameService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeLadderGameApplication {

    public static void main(String[] args) {
        List<Snake> snakes = Arrays.asList(new Snake(6, 16), new Snake(26, 47), new Snake(11, 49), new Snake(53, 56), new Snake(19, 62), new Snake(60, 64), new Snake(24, 87), new Snake(73, 93), new Snake(75, 95), new Snake(78, 98));
        List<Ladder> ladders = Arrays.asList(new Ladder(1, 38), new Ladder(4, 14), new Ladder(9, 31), new Ladder(21, 42), new Ladder(28, 84), new Ladder(36, 44), new Ladder(51, 67), new Ladder(71, 91), new Ladder(80, 100));

        Board board = new Board(100, snakes, ladders);

        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Queue<Player> players = new LinkedList<>(Arrays.asList(player1, player2));

        Dice dice = new Dice();
        Game game = new Game(board, players, dice);

        GameService gameService = new GameService(game);

        gameService.start();
    }
}
