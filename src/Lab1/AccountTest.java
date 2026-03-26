package Lab1;

import java.util.Scanner;

public class AccountTest {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter account holder name : ");
        String name = input.nextLine();

        System.out.print("Enter initial balance : ");
        double balance = input.nextDouble();

        Account account = new Account(name, balance);

        int choice = 0;

        while(choice != 4) {

            System.out.println("\n1 - Deposit Money");
            System.out.println("2 - Withdraw Money");
            System.out.println("3 - Show Balance");
            System.out.println("4 - Exit");

            System.out.print("Select option : ");
            choice = input.nextInt();

            if(choice == 1) {

                System.out.print("Enter deposit amount : ");
                double amount = input.nextDouble();

                account.deposit(amount);

            }
            else if(choice == 2) {

                System.out.print("Enter withdrawal amount : ");
                double amount = input.nextDouble();

                account.withdraw(amount);

            }
            else if(choice == 3) {

                System.out.println("Account Holder : " + account.getName());
                System.out.println("Current Balance : " + account.getBalance());

            }
            else if(choice == 4) {

                System.out.println("Exiting program...");

            }
            else {

                System.out.println("Invalid option!");
            }
        }

        input.close();
    }
}

