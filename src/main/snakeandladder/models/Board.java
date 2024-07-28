package main.snakeandladder.models;

import java.util.List;

public class Board {

    private int size;
    private List<Snake> snakeList;
    private List<Ladder> ladderList;

    public Board(int size, List<Snake> snakeList, List<Ladder> ladderList) {
        this.size = size;
        this.snakeList = snakeList;
        this.ladderList = ladderList;
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakeList() {
        return snakeList;
    }

    public List<Ladder> getLadderList() {
        return ladderList;
    }

    public int movePlayer(Player player, int position) {
        int newPosition = player.getPosition() + position;

        if (newPosition > size){
            return player.getPosition();
        }

        else{
            for (Snake snake: snakeList){
                if (snake.getEnd() == newPosition){
                    return snake.getStart();
                }
            }

            for (Ladder ladder: ladderList){
                if (ladder.getStart() == newPosition){
                    return ladder.getEnd();
                }
            }
        }

        return newPosition;
    }
}
