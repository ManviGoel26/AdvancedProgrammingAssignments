package assignment2;

import java.util.Scanner;

class FoodItems 
{
	private String name;
	private float cost;
	private float discount;
	private final int ID;
	private int quantity;
	private final Restaurant restaurant;
	private String type; // Add final if required. // Make subclasses if use of the type arises.
	private static int counter = 0;
	private static Scanner scan = new Scanner(System.in);
	
	public FoodItems(String n, float c, float d, String t, int q, Restaurant r)
	{
		this.name = n;
		this.cost = c;
		this.discount = d;
		this.type = t;
		this.quantity = q;
		this.restaurant = r;
		counter++;
		this.ID = counter;
		
	}

	public String getName()
	{
		return this.name;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public float getCost()
	{
		return this.cost;
	}
	
	public float getDiscount()
	{
		return this.discount; // Multiply by cost if required.
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public Restaurant getRestaurant()
	{
		return this.restaurant;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public void setCost(float newCost)
	{
		this.cost = newCost;
	}
	
	public void setDiscount(float newDiscount)
	{
		this.discount = newDiscount;
	}
	
	public void setType(String newType)
	{
		this.type = newType;
	}
	
	public void setQuantity(int Ordered)
	{
		this.quantity -= Ordered;
	}
	
	public String toString()
	{
		return this.getID() + " " + this.getName() + " " + this.getType()  + " " + this.getCost() + " " + this.getQuantity() + " " + this.getDiscount() + "% off";  
	}
	
	public void edit()
	{
		System.out.println("Choose an attribute to edit: ");
		System.out.println(" 1) Name ");
		System.out.println(" 2) Price");
		System.out.println(" 3) Quantity");
		System.out.println(" 4) Category");
		System.out.println(" 5) Offer");
		
		int choose_attribute = scan.nextInt();
		
		if (choose_attribute == 1)
		{
			System.out.println("Enter the new name: ");
			this.setName(scan.next());
		}
		
		else if (choose_attribute == 2)
		{
			System.out.println("Enter the new price: ");
			this.setCost(scan.nextFloat());
		}
		
		else if (choose_attribute == 3)
		{
			System.out.println("Enter the new quantity: ");
			this.setQuantity(scan.nextInt());
		}
		
		else if (choose_attribute == 4)
		{
			System.out.println("Enter the new category: ");
			this.setType(scan.next());
		}
		
		else if (choose_attribute == 5)
		{
			System.out.println("Enter the new discount: ");
			this.setDiscount(scan.nextFloat());
		}
		
		System.out.println(this.toString());
		return;
		
		
	}
}
