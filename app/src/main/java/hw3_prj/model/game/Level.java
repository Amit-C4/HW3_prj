package hw3_prj.model.game;

import hw3_prj.control.InputController;
import hw3_prj.control.init.LevelInitializor;
import hw3_prj.model.tiles.Units.Enemies.Enemy;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.BoardHelper;
import hw3_prj.utils.Callbacks.MSG_Callback;

public class Level {
    private final String[] paths = {
        "app\\src\\main\\resources\\levels_dir\\level1.txt",
        "app\\src\\main\\resources\\levels_dir\\level2.txt",
        "app\\src\\main\\resources\\levels_dir\\level3.txt",
        "app\\src\\main\\resources\\levels_dir\\level4.txt"

        
    };
    private String path;
    private Board board;
    private InputController inputController;
    private MSG_Callback msg_Callback; 

    public Level(int level, Player player, MSG_Callback msg_Callback) {
        this.path = paths[level-1];
        BoardHelper boardHelper = new BoardHelper();
        LevelInitializor levelInit = new LevelInitializor(msg_Callback);
        
        this.board = new Board(levelInit.initLevel(path, player,  boardHelper), levelInit.getWidth(),levelInit.getEnemies(), player);
        this.msg_Callback = msg_Callback;
        boardHelper.setBoard(board);
        this.inputController = new InputController(msg_Callback);
    }

    public void startLevel() {
        msg_Callback.send(board.toString());

        while (board.getPlayer().isAlive() && board.getEnemies().size() > 0) {
            Tick();
            msg_Callback.send(board.toString());
        }
        if (!(board.getPlayer().isAlive())) {
            msg_Callback.send(board.getPlayer().toString());
            msg_Callback.send("Game Over");
        } else {
            msg_Callback.send("You won!");
        }
    }

    

    private void Tick() {
        Player player = board.getPlayer();
        msg_Callback.send(board.getPlayer().toString());
        player.onTick(inputController.getAction());
        for (Enemy enemy : board.getEnemies()) {
            enemy.onTick();
        }
    }

    
}