package hw3_prj.model.game;

import hw3_prj.control.InputController;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.Callbacks.MSG_Callback;
import hw3_prj.view.CLI;
import hw3_prj.view.View;

public class Game {
    private MSG_Callback msg_Callback;
    private final Player player;
    private final InputController ic;

    public Game() {
        View view = new CLI();
        this.msg_Callback = view.getCallback();
        this.ic = new InputController(msg_Callback);
        this.player = ic.choosePlayer();
    }

    public void startGame(){
        for(int i = 1; i <= 4 & player.isAlive(); i++){
            Level currentLevel = new Level(i, player, msg_Callback);
            currentLevel.startLevel();
        }
    }
}