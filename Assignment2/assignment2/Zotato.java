package assignment2;

import java.util.ArrayList;
import java.util.Scanner;

public final class Zotato {
	
	private static ArrayList<Restaurant> Restaurants;
	private static ArrayList<Customer> Customers;
	private static float CompanyBalence;
	private static float DeliveryCharges;
	
	
	
	public Zotato()
	{
		//Hard coding Restaurant in Zotato;
		//Change to the user interface;
		CompanyBalence = 0;
		DeliveryCharges = 0;
		
		Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(new AuthenticRestaurant("Shah", "Delhi"));
		Restaurants.add(new Restaurant("Ravi's", "Pune"));
		Restaurants.add(new AuthenticRestaurant("The Chinese", "Mumbai"));
		Restaurants.add(new FastFoodRestaraunt("Wang's", "Bangalore"));
		Restaurants.add(new Restaurant("Paradise", "Kolkata"));
		
		Customers = new ArrayList<Customer>();
		Customers.add(new EliteCustomer("Ram", "Delhi"));
		Customers.add(new EliteCustomer("Sam", "Pune"));
		Customers.add(new SpecialCustomer("Tim", "Bangalore"));
		Customers.add(new Customer("Kim", "Mumbai"));
		Customers.add(new Customer("Jim", "Delhi"));
		
		this.MainApplication();
		System.out.println("Program Terminated");
		
	}
	
	private void MainApplication()
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to Zotato: ");
		System.out.println("1) Enter as Restaurant Owner");
		System.out.println("2) Enter as Customer");
		System.out.println("3) Check User Details");
		System.out.println("4) Comapany Account Details");
		System.out.println("5) Exit");
		
		int first_command = scan.nextInt();
		
		if (first_command == 1)
		{
			System.out.println("Choose Restaurant");
		
			for (int i = 0; i < Zotato.Restaurants.size(); i++)
			{
				System.out.println((i+1) + ") " + Zotato.Restaurants.get(i).getName() + " " + Zotato.Restaurants.get(i).getTheClass());
			}
			
			int choose_restautrant = scan.nextInt();
			Zotato.Restaurants.get(choose_restautrant-1).enter();
			this.MainApplication();
			scan.close();
			return;
		}
		
		else if (first_command == 2)
		{
			System.out.println("Choose Customer");
			
			for (int i = 0; i < Zotato.Customers.size(); i++)
			{
				System.out.println((i+1) + ") " + Zotato.Customers.get(i).getName() + " " + Zotato.Customers.get(i).getTheClass());
			}
			
			int choose_customer = scan.nextInt();
			Zotato.Customers.get(choose_customer-1).enter();
			this.MainApplication();
			scan.close();
			return;
		}
		
		else if (first_command == 3)
		{
			System.out.println("1) Customer List");
			System.out.println("2) Restaurant List");
			
			int second_command = scan.nextInt();
			
			if (second_command == 1)
			{
				for (int i = 0; i < Zotato.Customers.size(); i++)
				{
					System.out.println((i+1) + ") " + Zotato.Customers.get(i).getName());
				}
				
				int choose_customer = scan.nextInt();
				Zotato.Customers.get(choose_customer-1).DisplayDetails(); 
				this.MainApplication();
				scan.close();
				return;
				
			}
			
			else if (second_command == 2)
			{
				for (int i = 0; i < Zotato.Restaurants.size(); i++)
				{
					System.out.println((i+1) + ") " + Zotato.Restaurants.get(i).getName());
				}
				
				int choose_restaurant = scan.nextInt();
				Zotato.Restaurants.get(choose_restaurant-1).DisplayDetails(); 
				this.MainApplication();
				scan.close();
				return;
			}
			
		}
		
		else if (first_command == 4) 
		{
			System.out.println("Total Comapany Balence - INR " + this.getCompanyBalence() + "/-");
			System.out.println(" Total Delivery Charges Collected - INR " + this.getDeliveryCharges() + "/-");
			this.MainApplication();
			scan.close();
			return;
		}
		
		else if (first_command == 5)
		{
			scan.close();
			return;
		}
		
		System.out.println("Wrong Command");
		this.MainApplication();
		scan.close();
		return;
		
	}
	
	private float getCompanyBalence()
	{
		return Zotato.CompanyBalence;
	}
	
	private float getDeliveryCharges()
	{
		return Zotato.DeliveryCharges;
	}
	
	protected static void setCompanyBalence(float newPrice)
	{
		CompanyBalence += newPrice;
	}
	
	protected static void setDeliveryCharges(float newCharges)
	{
		DeliveryCharges += newCharges;
	}
	
	protected static ArrayList<Restaurant> getRestaurants()
	{
		return Restaurants;
	}

}
