package Lab3;
import java.util.Scanner;

public class DeliveryCalculator {
	
	enum costumerType {premium, regular}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		double weight = 0;
		double distance = 0;
		boolean isExpress = false;
		costumerType type = null;

		boolean isOkey;
		do {
			isOkey = true;
			System.out.print("Enter weight (kg): ");
	        weight = input.nextDouble();
	        System.out.print("Enter distance (km): ");
	        distance = input.nextDouble();
	        System.out.print("Is express delivery (true/false): ");
	        isExpress = input.nextBoolean();
	        input.nextLine();
	        System.out.print("Enter customer type (regular/premium): ");
	        String typeString = input.nextLine();
			
			type = parseCostumerType(typeString);

			if(type == null || weight < 0 || distance <= 0) {
				isOkey = false;
				System.out.println("INVALID INPUT! Please try again.\n");
			}
			
		} while (!isOkey);
		
		double cost = calculateCost(weight,distance,isExpress,type);
		System.out.printf("Total Cost: %.2f\n",cost);
		System.out.println(findCheapestOption(weight,distance));
	}
	
	public static costumerType parseCostumerType(String input) {
		input = input.toLowerCase();
		if ("regular".equalsIgnoreCase(input)) return costumerType.regular;
		else if ("premium".equalsIgnoreCase(input)) return costumerType.premium;
		else return null;
	}
	
	public static double calculateCost(double weight, double distance) {
		return calculateCost(weight,distance, false);
	}
	
	public static double calculateCost(double weight, double distance, boolean isExpress) {
		return calculateCost(weight,distance, isExpress, costumerType.regular);
	}
	
	public static double calculateCost(double weight, double distance, boolean isExpress, costumerType type) {
		double base = weightCost(weight,0);
		base = distanceCost(distance,base);
		base = isExpressCost(isExpress,base);
		base = costumerTypeCost(type,base);
		
		/*
		if(weight >= 0 && weight <= 5) base += 10;
		else if(weight > 5 && weight <= 20) base += 20;
		else base += 40;
		
		if (distance > 50) base += ((distance-50) * 0.5f);
		
		if (isExpress) base += (base * 0.5f);
		
		if(type.equals(costumerType.premium)) base -= (base * 0.2f); 
		*/
		
		return base;
	}
	
	public static String findCheapestOption(double weight, double distance) {
		double base = calculateCost(weight,distance);
		double temp = 0;
		String option = "Normal Delivery for Regular Customer";
		
		temp = calculateCost(weight,distance,true);
		if (base > temp) {
			base = temp;
			option = "Express Delivery for Regular Customer";
		}
		temp = calculateCost(weight,distance,false,costumerType.premium);
		if (base > temp) {
			base = temp;
			option = "Normal Delivery for Premium Customer";
		}
		temp = calculateCost(weight,distance,true,costumerType.premium);
		if (base > temp) {
			base = temp;
			option = "Express Delivery for Premium Customer";
		}
		
		return option;
	}
	
	public static double weightCost(double weight, double base) {
		if(weight >= 0 && weight <= 5) base += 10;
		else if(weight > 5 && weight <= 20) base += 20;
		else base += 40;
		return base;
	}
	
	public static double distanceCost(double distance, double base) {
		if(distance > 50) base += ((distance-50) * 0.5f);
		return base;
	}
	
	public static double isExpressCost(boolean isExpress, double base) {
		if (isExpress) base = base * 1.5f;
		return base;
	} 
	
	public static double costumerTypeCost(costumerType type, double base) {
		if(type.equals(costumerType.premium)) base = base * 0.8f; 
		return base;
	} 

}
