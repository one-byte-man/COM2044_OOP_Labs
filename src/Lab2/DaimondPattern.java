package Lab2;
import java.util.Scanner;

public class DaimondPattern {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int num;
		boolean isCorrectInput = false;
		
		do {
			System.out.println("Please enter an odd number:");
			num = input.nextInt();
			if (num % 2 != 0 && num > 0) isCorrectInput = true;
			else System.out.println("Your number can not be even or negative, try again!");
		} while (!isCorrectInput);
		
		
		//---------------------------------
		int len = 2*num-1;
		int i = 0;
		
		for(int a = 1; a < len+1; a++) {
			
			if(a <= num) {
				i = a;
				for (int l = num-i; l > 0; l--)
					System.out.print(" ");
			}
			else {
				i = len - a + 1;
				for (int l = num; l > i; l--)
					System.out.print(" ");
			}
			
			
			if(i % 2 != 0) {
				for (int k = 1; k < i+1; k++)
					System.out.print(k);
				for (int m = i-1; m > 0; m--)
					System.out.print(m);
				System.out.println();
			}
			else {
				for(int n = 0; n < (2*i-1); n++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		
		// The Long Solution
		/*
		for(int i = 0; i < num+1; i++) {
			
			for (int l = num-i; l > 0; l--)
				System.out.print(" ");
			
			if(i % 2 != 0) {
				for (int k = 1; k < i+1; k++)
					System.out.print(k);
				for (int m = i-1; m > 0; m--)
					System.out.print(m);
				System.out.println();
			}
			else {
				for(int n = 0; n < (2*i-1); n++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		
		
		for(int i = num-1; i > 0; i--) {
			for (int l = num; l > i; l--)
				System.out.print(" ");
			
			if(i % 2 != 0) {
				for (int k = 1; k < i+1; k++)
					System.out.print(k);
				for (int m = i-1; m > 0; m--)
					System.out.print(m);
				System.out.println();
			}
			else {
				for(int n = 0; n < (2*i-1); n++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		 */
		
	}

}
