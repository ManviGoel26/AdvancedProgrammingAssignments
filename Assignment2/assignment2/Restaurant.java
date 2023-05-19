package assignment2;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//import java.util.Scanner;

public class Restaurant implements User
{
	private final String name;
	private final String address;
	private int reward_points;
	private HashMap<Integer, FoodItems> menu;
	private float discount;
	private int total_orders;
	private static Scanner scan = new Scanner(System.in);
	
	
	public Restaurant(String n, String a)
	{
		this.name = n;
		this.address = a;
		this.reward_points = 0;
		this.menu = new HashMap<Integer, FoodItems>();
		this.discount = 0;
	}
	
	public float getDiscount()
	{
		return this.discount;
	}
	
	public void getDiscountQuery4()
	{
		System.out.println("Not valid");
		return;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getRewardPoints()
	{
		return this.reward_points;
	}
	
	public HashMap<Integer, FoodItems> getMenu()
	{
		return this.menu;
	}
	
	public int getOrders()
	{
		return this.total_orders;
	}
	
	protected void setDiscount(float discount)
	{
		this.discount = discount;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	public void PrintMenu()
	{
		if (this.getMenu().isEmpty())
		{
			System.out.println(" The menu is still empty");
		}
		
		else
		{
			System.out.println("Menu : ");
			for ( Map.Entry<Integer, FoodItems> set : this.getMenu().entrySet())
			{
				System.out.println(this.getName() + " - " + set.getValue().toString());
			}
			
		}
	}
	
	public void addOrders()
	{
		this.total_orders += 1;
	}
	
	public void AddFoodItems(FoodItems f, int q)
	{
		this.getMenu().put(q, f);
	}
	
	public float calculateCompanyEarning(float TotalBill)
	{
		return (TotalBill/100);
	}
	
	public float addRewardPoints(float TotalBill)
	{
		int points = 5*((int) TotalBill/100);
		return points;
	}
// Make a funtion calculate total bill
	
	public void enter()
	{
		this.displayFunctions();
	}
	
	public void displayFunctions()
	{
		System.out.println("Welcome " + this.getName());
		System.out.println("Restaurant Menu");
		System.out.println("1) Add item");
		System.out.println("2) Edit item");
		System.out.println("3) Print Rewards");
		System.out.println("4) Discount on Bill Value");
		System.out.println("5) Exit");
		
		int second_command = scan.nextInt();
		
		if (second_command == 1)
		{
			System.out.println(" Enter food items details");
			System.out.println("Food Name: ");
			String food_name = scan.next();
			System.out.println("Item price");
			float food_price = scan.nextFloat();
			System.out.println("Item Quantity");
			int food_quantity = scan.nextInt();
			System.out.println("Item category");
			String food_type = scan.next();
			System.out.println("Offer ");
			float food_offer = scan.nextFloat();
			
			FoodItems f1 = new FoodItems(food_name, food_price, food_offer, food_type, food_quantity, this);
			this.AddFoodItems(f1, f1.getID());
			System.out.println(this.name + " - " + f1.toString());
			
			this.displayFunctions();
			return;
			
		}
		
		else if (second_command == 2)
		{
			System.out.println("Choose item by code");
			
			for ( Map.Entry<Integer, FoodItems> set : this.getMenu().entrySet())
			{
				System.out.println(set.getValue().toString());
			}
			
			int choose_dish = scan.nextInt();
			this.getMenu().get(choose_dish).edit();
			this.displayFunctions();
			return;
		}
		
		else if (second_command == 3)
		{
			System.out.println("Reward points : " + this.getRewardPoints());
			this.displayFunctions();
			return;
		}
		
		else if (second_command == 4)
		{
			this.getDiscountQuery4();
			this.displayFunctions();
			return;
		}
		
		else if (second_command == 5)
		{
			return;
		}
		
		System.out.println("Wrong command");
		this.displayFunctions();
		return;
		
	}
	
	public String getTheClass()
	{
		return "";
	}
	
	public void DisplayDetails()
	{
		System.out.println(this.getName() + this.getTheClass() + ", " + this.getAddress() + ", " + this.getOrders());
	}

}
