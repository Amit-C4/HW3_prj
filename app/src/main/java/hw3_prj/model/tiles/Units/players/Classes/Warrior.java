package hw3_prj.model.tiles.Units.players.Classes;

import java.util.List;

import hw3_prj.control.InputType;
import hw3_prj.model.tiles.Units.Enemies.Enemy;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.Callbacks.MSG_Callback;

public class Warrior extends Player {
    private int cooldown;
    private int currCooldown;

    public Warrior(int playerID, String name, int health, int attack, int defense, int cooldown, MSG_Callback m) {
        super(playerID, name, health, attack, defense, m);
        this.cooldown = cooldown;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        currCooldown = 0;
        msg.send(name + " reached level" + level + ": +" + healthGain() + " HP, +" + attackGain() + " Attack, +" + defenseGain() + " Defense");
    }

    @Override
    public int attack() {
        return (super.attack() + 2) * level;
    }

    @Override
    public int healthGain() {
        return (super.healthGain() + 5) * level;
    }

    @Override
    public int defenseGain() {
        return (super.defenseGain() + 1) * level;
    }


    @Override
    public String toString() {
        return super.toString() + "    Cooldown: " + currCooldown + "/" + cooldown;
    }

    public void castAbility() {
        if (currCooldown == 0) {
            currCooldown = cooldown;
            hp.heal(10 * dp);
            msg.send(name + " used Avenger's Shield, healing for" + (10*dp));
            List<Enemy> enemies = helper.getEnemiesInRange(3, this.position);
            if (enemies.isEmpty()) {
                msg.send("No enemies in range");
                return;
            }
            else {
                int damage = hp.getMax() / 10;
                Enemy oponent = enemies.get(generator.generate(enemies.size()));
                battle(oponent, damage);
                if(!(oponent.isAlive())){
                    gainExperience(oponent.experienceValue());
                    oponent.onDeath(this);
                }
            }
        }
    }

    public void onTick(InputType input) {
        super.onTick(input);
        if(currCooldown > 0){
            currCooldown--;
        }
    }
}
