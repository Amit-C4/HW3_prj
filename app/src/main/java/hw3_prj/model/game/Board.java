package hw3_prj.model.game;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import hw3_prj.model.tiles.Empty;
import hw3_prj.model.tiles.Tile;
import hw3_prj.model.tiles.Units.Enemies.Enemy;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.Position;



public class Board {
    private Map<Position, Tile> board;
    private final int width;
    private Player player;
    private Set<Enemy> enemies;

    public Board(List<Tile> tiles, int width, Set<Enemy> enemies, Player player) {
        this.width = width;
        this.enemies = enemies;
        this.player = player;
        this.board = new TreeMap<>();
        for(Tile t : tiles) {
            if (t.getPosition() == null) {
                System.out.println("null position" + t.getClass());
            }
                
            else
                board.put(t.getPosition(), t);
        }
    }

    public Set<Enemy> getEnemies() {
        return enemies;
    }

    public int getWidth() {
        return width;
    }

    public Player getPlayer() {
        return player;
    }

    public Tile getTile(Position position) {
        return board.get(position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Position, Tile> entry : board.entrySet()){
            sb.append(entry.getValue().view());
            if(entry.getKey().getX() == width-1){
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void swapPositions(Tile a, Tile b) {
        board.put(a.getPosition(), a);
        board.put(b.getPosition(), b);
    }

    public void removeUnit(Tile unit) {
        board.remove(unit.getPosition());
        enemies.remove(unit);

        Tile empty = new Empty();
        empty.init(unit.getPosition());
        board.put(unit.getPosition(), empty);
    }
}
