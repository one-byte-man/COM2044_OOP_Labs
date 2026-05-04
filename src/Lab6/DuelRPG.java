package Lab6;
import java.util.ArrayList;
import java.util.Scanner;

public class DuelRPG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<GameCharacter> players = new ArrayList<>();

        System.out.print("Enter Wizard name: ");
        String wdName = sc.nextLine();
        System.out.print("Enter Wizard health: ");
        int wdHealth = sc.nextInt();
        System.out.print("Enter Wizard mana (0-100): ");
        int wdMana = sc.nextInt();
        players.add(new Wizard(wdName, wdHealth, wdMana));

        sc.nextLine();

        System.out.print("Enter Warrior name: ");
        String wrName = sc.nextLine();
        System.out.print("Enter Warrior health: ");
        int wrHealth = sc.nextInt();
        System.out.print("Enter Warrior strength (>=0): ");
        int wrStrength = sc.nextInt();
        players.add(new Warrior(wrName, wrHealth, wrStrength));

        System.out.println("\nInitial Status");
        for (GameCharacter p : players) System.out.println(p);

        System.out.println("\nDuel Begins");
        boolean duelContinues = true;
        int turn = 0;

        while (duelContinues) {
            GameCharacter attacker = players.get(turn % 2);
            GameCharacter defender = players.get((turn + 1) % 2);

            attacker.attack(defender);
            for (GameCharacter p : players) System.out.println("Status -> " + p);

            if (isLoser(players.get(0)) || isLoser(players.get(1))) {
                duelContinues = false;
            }
            turn++;
        }

        System.out.println("\nDuel Ends ---");
        announceWinner(players.get(0), players.get(1));
    }

    private static boolean isLoser(GameCharacter p) {
        if (p.getHealth() <= 0) return true;
        if (p instanceof Wizard && ((Wizard) p).getMana() <= 0) return true;
        if (p instanceof Warrior && ((Warrior) p).getStrength() <= 0) return true;
        return false;
    }

    private static void announceWinner(GameCharacter p1, GameCharacter p2) {
        if (isLoser(p1)) System.out.println(p1.getName() + " loses (health or mana reached 0). " + p2.getName() + " wins.");
        else System.out.println(p2.getName() + " loses (health or strength reached 0). " + p1.getName() + " wins.");
    }
}