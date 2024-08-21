package hw3_prj.model.tiles.Units.Enemies;

import hw3_prj.utils.Visibility;
import hw3_prj.utils.Callbacks.MSG_Callback;

public class Trap extends Enemy {
    private Visibility visibility;

    public Trap(char symbol, String name, int health, int attack, int defense, int xp, int visibility, int invisibility, MSG_Callback m) {
        super(symbol, name, health, attack, defense, xp, m);
        this.visibility = new Visibility(visibility, invisibility);
    }

    public void onTick() {
        visibility.tick();
    }

    

    @Override
    public String view() {
        return visibility.getCurrent() ? super.view() : ".";
    }
}
