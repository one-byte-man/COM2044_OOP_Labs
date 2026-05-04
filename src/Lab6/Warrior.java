package Lab6;

public class Warrior extends GameCharacter {
    private int strength;
    private final int initialStrength;

    public Warrior(String name, int health, int strength) {
        super(name, health);
        this.initialStrength = strength;
        setStrength(strength);
    }

    public int getStrength() { return strength; }
    public void setStrength(int s) { this.strength = Math.max(0, s); }

    @Override
    public void attack(GameCharacter target) {
        super.attack(target);
        int reduction = rand.nextInt(initialStrength + 1);
        setStrength(this.strength - reduction);
        System.out.println("[" + getName() + " strength -" + reduction + ", now " + strength + "]");
    }

    @Override
    public String toString() {
        return super.toString() + ", Strength: " + strength;
    }
}
