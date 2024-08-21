package hw3_prj.control.init;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import hw3_prj.model.tiles.Tile;
import hw3_prj.model.tiles.Units.Enemies.Enemy;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.BoardHelper;
import hw3_prj.utils.Position;
import hw3_prj.utils.Callbacks.MSG_Callback;

import java.util.ArrayList;

import java.util.HashSet;





public class LevelInitializor {
    private HashSet<Enemy> enemies;
    private int width;

    private TileFactory tf;

    public LevelInitializor( MSG_Callback msg_callback){
        this.enemies = new HashSet<Enemy>();
        this.tf = TileFactory.getInstance(msg_callback);
    }
    
    public HashSet<Enemy> getEnemies(){
        return this.enemies;
    }

    public int getWidth(){
        return this.width;
    }


    public Player getPlayer(){
        return tf.producePlayer();
    }

    public List<Tile> initLevel(String levelPath, Player player, BoardHelper helper){
        List<String> lines;
        List<Tile> tiles = new ArrayList<Tile>();
        try {
            lines = Files.readAllLines(Paths.get(levelPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.width = lines.get(0).length();

        int y = 0;
        for(String line : lines){
            int x = 0;
            for(char c : line.toCharArray()){
                switch(c) {
                    case '.':
                        Tile emptyTile = tf.produceEmpty(new Position(x, y));
                        tiles.add(emptyTile);
                        break;
                    case '#':
                        Tile wallTile = tf.produceWall(new Position(x, y));
                        tiles.add(wallTile);
                        break;
                    case '@':
                        player.init(new Position(x, y), helper);
                        tiles.add(player);
                        break;
                    default:
                        Enemy enemyTile = tf.produceEnemy(c, new Position(x, y), helper);
                        tiles.add(enemyTile);
                        enemies.add(enemyTile);
                        break;
                }
                x++;
            }
            y++;
        }
        return tiles;
    }
}
