package hw3_prj.utils;

import java.util.ArrayList;
import java.util.List;

import hw3_prj.model.game.Board;
import hw3_prj.model.tiles.Tile;
import hw3_prj.model.tiles.Units.Enemies.Enemy;
import hw3_prj.model.tiles.Units.players.Player;

public class BoardHelper {
    private static BoardHelper instance = null;

    public BoardHelper getInstance() {
        if (instance == null)
            instance = new BoardHelper();
        return instance;
    }

    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void swapPositions (Tile a, Tile b) {
        Position temp = a.getPosition();
        a.setPosition(b.getPosition());
        b.setPosition(temp);
        
        board.swapPositions(a, b);
    }

    public List<Enemy> getEnemiesInRange(int range, Position position) {
        List<Enemy> inRange = new ArrayList<>();
        for (Enemy e : board.getEnemies()) {
            if (e.getPosition().range(position) <= range) {
                inRange.add(e);
            }
        }
        inRange.sort((a, b) -> (int)(a.getPosition().range(position) - b.getPosition().range(position)));
        return inRange;
    }

    public Position getPlayerPosition() {
        return board.getPlayer().getPosition();
    }

    public Tile getTile(Position position) {
        return board.getTile(position);
    }

    public void removeUnit(Tile unit) {
        board.removeUnit(unit);
    }

    public Player getPlayer() {
        return board.getPlayer();
    }
}
