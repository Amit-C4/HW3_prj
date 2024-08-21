package hw3_prj.model.tiles.Units;

import hw3_prj.model.tiles.Empty;
import hw3_prj.model.tiles.Tile;
import hw3_prj.model.tiles.Wall;
import hw3_prj.model.tiles.Units.Enemies.Enemy;
import hw3_prj.model.tiles.Units.players.Player;
import hw3_prj.utils.BoardHelper;
import hw3_prj.utils.Health;
import hw3_prj.utils.Position;
import hw3_prj.utils.Callbacks.DEATH_Callback;
import hw3_prj.utils.Callbacks.MSG_Callback;
import hw3_prj.utils.Callbacks.TICK_Callback;
import hw3_prj.utils.Generators.Generator;
import hw3_prj.utils.Generators.RandomGenerator;

public abstract class Unit extends Tile implements TICK_Callback , DEATH_Callback {
    protected String name;
    protected Health hp;
    protected int att;
    protected int dp;
    protected MSG_Callback msg;

    protected Generator generator = new RandomGenerator();
    protected MSG_Callback msgCallback;
    protected BoardHelper helper;

    public Unit(char symbol, String name, int health, int attack, int defense, MSG_Callback m) {
        super(symbol);
        this.name = name;
        this.msg = m;
        this.hp = new Health(health);
        this.att = attack;
        this.dp = defense;
    }

    public Unit init (Position position, BoardHelper helper) {
        super.init(position);
        this.helper = helper;
        return this;
    }

    public int attack(){
        return generator.generate(att);
    }
    public int defend(){
        return generator.generate(dp);
    }

    public boolean isAlive(){
        return hp.isAlive();
    }

    public void battle(Unit oponent) {
        int attack = this.attack();
        int defense = oponent.defend();
        msg.send(name + " engaged in combat with " + oponent.name);
        msg.send(toString());
        msg.send(oponent.toString());
        msg.send(name + " rolled for " + attack + " attack points");
        msg.send(oponent.name + " rolled " + defense + " defense points");
        msg.send(name + " dealt " + Math.max(0, attack - defense) + " damage to " + oponent.name);
        msg.send("\n");
        oponent.hp.takeDamage(attack - defense);
    }

    public void battle(Unit oponent, int damage) {
        int defense = oponent.defend();
        msg.send(oponent.name + " rolled " + defense + " defense points");
        msg.send(name + " hit " + oponent.name + " for " + Math.max(0, damage - defense) + " ability damage");
        msg.send("\n");
        oponent.hp.takeDamage(damage - defense);
    }

    public void interact(Tile t){
        t.accept(this);
    }

    public void visit(Empty e){

        helper.swapPositions(this, e);
    }

    public void visit(Wall w){
        // Do nothing
    }

    @Override
    public String toString() {
        return name + "    Health: " + hp.getCurrent() + "/" + hp.getMax() + "    Attack: " + att + "   Defense: " + dp;
    }

    public String getName(){
        return name;
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public void moveUp() {
        this.interact(helper.getTile(new Position(position.getX(), position.getY() - 1)));
    };

    public void moveDown() {
        this.interact(helper.getTile(new Position(position.getX(), position.getY() + 1)));
    };

    public void moveLeft() {
        this.interact(helper.getTile(new Position(position.getX()-1, position.getY())));
    };

    public void moveRight() {
        this.interact(helper.getTile(new Position(position.getX()+1, position.getY())));
    };

    public void onTick() {
        // Do nothing
    }
}