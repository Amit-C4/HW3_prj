package hw3_prj.model.tiles.Units.Enemies;

import hw3_prj.utils.Position;
import hw3_prj.utils.Callbacks.MSG_Callback;

public class Monster extends Enemy {
    protected int visionRange;

    public Monster(char symbol, String name, int health, int attack, int defense, int visionRange, int xp, MSG_Callback m) {
        super(symbol, name, health, attack, defense, xp, m);
        this.visionRange = visionRange;
    }

    @Override
    public String toString() {
        return super.toString() + "    Vision Range: " + visionRange;
    }

    public void onTick() {
        Position playerPos = helper.getPlayerPosition();
        if (this.position.range(playerPos) < visionRange) {
            int dx = this.position.getX() - playerPos.getX();
            int dy = this.position.getY() - playerPos.getY();
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    this.moveLeft();
                } else {
                    this.moveRight();
                }
            } else {
                if (dy > 0) {
                    this.moveUp();
                } else {
                    this.moveDown();
                }
            }
        }
        else {
            switch(generator.generate(4)) {
                case 0:
                    this.moveUp();
                    break;
                case 1:
                    this.moveDown();
                    break;
                case 2:
                    this.moveLeft();
                    break;
                case 3:
                    this.moveRight();
                    break;
            }
        }
    }
}
