package hw3_prj.control;

import java.util.Map;
import java.util.Scanner;

import hw3_prj.control.init.TileFactory;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.Position;
import hw3_prj.utils.Callbacks.MSG_Callback;





public class InputController {

    public InputController(MSG_Callback msg_Callback) {
        this.msg_Callback = msg_Callback;
    }



    private MSG_Callback msg_Callback;

    private static final Map<Character, InputType> inputTypes = Map.ofEntries(
            Map.entry('w', InputType.UP),
            Map.entry('a', InputType.LEFT),
            Map.entry('s', InputType.DOWN),
            Map.entry('d', InputType.RIGHT),
            Map.entry('e', InputType.ABILITY),
            Map.entry('q', InputType.NOTHING)
            );

    

    public Player choosePlayer() {
        Scanner scanner = new Scanner(System.in);
        msg_Callback.send("Select Player:");
        TileFactory tileFactory = TileFactory.getInstance(msg_Callback);

        for (int i = 1; i <= 7; i++)
            msg_Callback.send(i + ". " + tileFactory.producePlayer(i, new Position(0, 0)).getName());

        int id = scanner.nextInt();
        while (id < 1 || id > 7) {
            msg_Callback.send("Invalid Player ID. Please select a valid Player ID: ");
            id = scanner.nextInt();
        }

        Player player = tileFactory.producePlayer(id, new Position(0, 0));
        msg_Callback.send("You have selected:\n " + player.getName());
        return player;
    }

    public InputType getAction() {
        Scanner scanner = new Scanner(System.in);

        msg_Callback.send("Enter movement (W,A,S,D,E,Q): ");

        char inputChar = scanner.next().charAt(0);

        inputChar = Character.toLowerCase(inputChar);
        InputType res = inputTypes.get(inputChar);

        while (res == null) {
            msg_Callback.send("Invalid Input. Please enter a valid input.");
            inputChar = scanner.next().charAt(0);
            inputChar = Character.toLowerCase(inputChar);
            res = inputTypes.get(inputChar);
        }
        return res;
    }

    public InputType getAltAction(){
        return getAction();
    }
}
