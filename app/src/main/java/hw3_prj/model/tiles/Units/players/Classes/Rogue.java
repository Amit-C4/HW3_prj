package hw3_prj.model.tiles.Units.players.Classes;

import java.util.List;

import hw3_prj.control.InputType;
import hw3_prj.model.tiles.Units.Enemies.Enemy;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.Callbacks.MSG_Callback;

public class Rogue extends Player {
    private static final int MAX_ENERGY = 100;
    private int cost;
    private int energy;

    public Rogue(int playerID, String name, int health, int attack, int defense, int cost, MSG_Callback m) {
        super(playerID, name, health, attack, defense, m);
        this.cost = cost;
        this.energy = 100;
    }
    
    public void levelUp() {
        super.levelUp();
        energy = MAX_ENERGY;
        msg.send(name + " reached level" + level + ": +" + healthGain() + " HP, +" + attackGain() + " Attack, +" + defenseGain() + " Defense");
    }

    @Override
    public int attack() {
        return (super.attack() + 3) * level;
    }

    @Override
    public String toString() {
        return super.toString() + "    Energy: " + energy + "/" + MAX_ENERGY;
    }

    public void castAbility() {
        if (energy >= cost) {
            msg.send(name + " cast Fan of Knives");
            energy -= cost;
            List<Enemy> enemies = helper.getEnemiesInRange(2, this.position);
            int damage = att;
            if (enemies.isEmpty()) {
                msg.send("No enemies in range");
                return;
            }
            else{
                for (Enemy enemy : enemies) {
                    battle(enemy, damage);
                    if(!(enemy.isAlive())){
                        gainExperience(enemy.experienceValue());
                        enemy.onDeath(this);
                    }
                }
            }
        }
    }

    public void onTick(InputType input) {
        super.onTick(input);
        energy = Math.min(energy + 10, MAX_ENERGY);
    }
}
