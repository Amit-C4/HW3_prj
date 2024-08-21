package hw3_prj.model.tiles.Units.players.Classes;

import java.util.List;

import hw3_prj.control.InputType;
import hw3_prj.model.tiles.Units.Enemies.Enemy;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.Callbacks.MSG_Callback;

public class Mage extends Player {
    private static final int MANA_POOL_GAIN = 25;
    private static final int SPELL_POWER_GAIN = 10;

    private int manaPool;
    private int mana;
    private int manaCost;
    private int spellPower;
    private int hitCount;
    private int range;

    public Mage(int playerid, String name, int health, int attack, int defense, int manaPool, int manaCost, int spellPower, int hitCount, int range, MSG_Callback m) {
        super(playerid,name, health, attack, defense, m);
        this.manaPool = manaPool;
        this.mana = manaPool;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.range = range;
    }

    public void levelUp() {
        super.levelUp();
        manaPool += mana_pool_gain();
        mana = Math.min(manaPool, mana + manaPool/4);
        spellPower += spell_power_gain();

        msg.send(name + " reached level" + level + ": +" + healthGain() + " HP, +" + attackGain() + " Attack, +" + defenseGain() + " Defense, +" + mana_pool_gain() + " Maximum Mana, +" + spell_power_gain() + " Spell Power");
    }

    private int mana_pool_gain() {
        return MANA_POOL_GAIN * level;
    }

    private int spell_power_gain() {
        return SPELL_POWER_GAIN * level;
    }

    @Override
    public String toString() {
        return super.toString() + "    Mana: " + mana + "/" + manaPool + "    Spell Power: " + spellPower;
    }

    public void castAbility() {
        if (mana >= manaCost) {
            List<Enemy> enemies = helper.getEnemiesInRange(range, this.position);
            if (enemies.isEmpty()) {
                msg.send("No enemies in range");
                return;
            }
            else {
                msg.send(name + " cast Blizzard");
                mana -= manaCost;
                
                int hits = 0;
                while (hits < hitCount && enemies.size() > 0) {
                    int damage = spellPower;
    
                    Enemy oponent = enemies.get(generator.generate(enemies.size()));
                    battle(oponent, damage);
                    hits++;
                    if(!(oponent.isAlive())){
                        gainExperience(oponent.experienceValue());
                        oponent.onDeath(this);
                    }
                }
            }
        }
    }

    public void onTick(InputType input) {
        super.onTick(input);
        mana = Math.min(manaPool, mana + level);
    }
}
