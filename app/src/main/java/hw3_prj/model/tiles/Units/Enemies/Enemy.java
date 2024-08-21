package hw3_prj.model.tiles.Units.Enemies;

import hw3_prj.model.tiles.Units.Unit;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.Callbacks.MSG_Callback;

public abstract class Enemy extends Unit {
    protected int xp;
    protected char character;

    public Enemy(char symbol,String name, int health, int attack, int defense, int xp, MSG_Callback m) {
        super(symbol ,name, health, attack, defense, m);
        this.xp = xp;
    }

    public int experienceValue() {
        return xp;
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void visit(Enemy e){
        // Do nothing
    }

    public void visit(Player p){
        battle(p);
        if (!p.isAlive()){
            p.onDeath(this);
        }
    }

    public void onDeath(Unit player){
        msg.send(name + " has been defeated. " + player.getName() + " gained " + xp + " experience");
        helper.removeUnit(this);
    }

    @Override
    public String toString() {
        return super.toString() + "    Experience Value: " + xp;
    }
}
