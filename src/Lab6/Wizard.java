package Lab6;

public class Wizard extends GameCharacter {
    private int mana;
    private final int initialMana;

    public Wizard(String name, int health, int mana) {
        super(name, health);
        this.initialMana = mana;
        setMana(mana);
    }

    public int getMana() { return mana; }
    public void setMana(int m) { this.mana = Math.min(100, Math.max(0, m)); }

    @Override
    public void attack(GameCharacter target) {
        super.attack(target);
        int reduction = rand.nextInt(initialMana + 1);
        setMana(this.mana - reduction);
        System.out.println("[" + getName() + " mana -" + reduction + ", now " + mana + "]");
    }

    @Override
    public String toString() {
        return super.toString() + ", Mana: " + mana;
    }
}