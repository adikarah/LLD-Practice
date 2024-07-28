package main.snakeandladder.services;

import main.snakeandladder.models.Game;
import main.snakeandladder.models.Player;

public class GameService {

    private Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void start() {
        while (!game.getPlayers().isEmpty()) {
            Player currentPlayer = game.getPlayers().poll();
            playTurn(currentPlayer);
            game.getPlayers().offer(currentPlayer);

            assert currentPlayer != null;
            if (currentPlayer.getPosition() == game.getBoard().getSize()) {
                System.out.println("Congratulations " + currentPlayer.getPlayerName() + "! You won the match");
                break;
            }
        }
    }

    private void playTurn(Player player) {
        int value = game.getDice().roll();

        int newPosition = game.getBoard().movePlayer(player, value);
        player.movePosition(newPosition);

        System.out.println("Player " + player.getPlayerName() + " moved to position: " + newPosition);
    }
}
