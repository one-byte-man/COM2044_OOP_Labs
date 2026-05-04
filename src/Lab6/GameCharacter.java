package Lab6;

import java.util.Random;

public class GameCharacter {
    private String name;
    private int health;
    protected Random rand = new Random();

    public GameCharacter(String name, int health) {
        this.name = name;
        this.setHealth(health);
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public void setHealth(int h) { this.health = Math.max(0, h); }

    public void takeDamage(int amount) {
        setHealth(this.health - amount);
    }

    public void heal(int amount) {
        setHealth(this.health + amount);
    }

    public void attack(GameCharacter target) {
        int damage = rand.nextInt(11); // [0, 10]
        target.takeDamage(damage);
        System.out.print(this.name + " attacks " + target.getName() + " for " + damage + " damage. ");

        // Healing: 0, 5, 10
        if (damage == 0 || damage == 5 || damage == 10) {
            int healAmount = rand.nextInt(11);
            target.heal(healAmount);
            System.out.print("(Triggered heal: " + target.getName() + " heals " + healAmount + ") ");
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Health: " + health;
    }
}