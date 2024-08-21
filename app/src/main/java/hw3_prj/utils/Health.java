package hw3_prj.utils;
public class Health {
    private int maxHP;
    private int currentHP;

    public Health(int maxHP) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
    }
    
    public void increaseMax(int amount) {
        this.maxHP += amount;
    }

    public void heal() {
        this.currentHP = this.maxHP;
    }

    public void heal(int amount) {
        this.currentHP = Math.min(currentHP + amount, maxHP);
    }

    public void takeDamage(int amount) {
        if (amount > 0) {
            this.currentHP = Math.max(0,  currentHP-amount);
        }
    }

    public int getCurrent() {
        return this.currentHP;
    }

    public int getMax() {
        return this.maxHP;
    }

    public boolean isAlive() {
        return this.currentHP > 0;
    }
}

