package assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


class Customer implements User {
	private final String name;
	private float wallet = 1000;
	private float reward = 0;
	private final String residence;
	private HashMap<Integer, FoodItems> cart;
	private ArrayList<String> recentOrders;
	private final Scanner scan = new Scanner(System.in);
	private int deliveryCharge;
	private Restaurant selected_res = null;
	private final int customer_discount = 0;
	
	Customer(String n, String r)
	{
		this.name = n;
		this.residence = r;
		this.cart = new HashMap<Integer, FoodItems>();
		this.recentOrders = new ArrayList<String>();
		this.deliveryCharge = 40;
		this.selected_res = null;
	}
	
	
	public  void newCart()
	{
		this.cart = new HashMap<Integer, FoodItems>(); 
	}
	public String getName()
	{
		return this.name;
	}
	
	public float getWallet() 
	{
		return this.wallet;
	}
	
	public float getReward()
	{
		return this.reward;
	}
	
	public String getResidence() 
	{
		return this.residence;
	}
	
	public HashMap<Integer, FoodItems> getCart()
	{
		return this.cart;
	}
	
	public ArrayList<String> getRecentOrders()
	{
		return this.recentOrders;
	}
	
	public int getDeliveryCharge()
	{
		return this.deliveryCharge;
	}
	
	public Restaurant getRes()
	{
		return this.selected_res;
	}
	
	public int getDiscount()
	{
		return this.customer_discount;
	}
	
	
	public void printCart()
	{
		if (this.getCart().isEmpty())
		{
			System.out.println("Your Cart is empty. Start adding new dishes :)");
		}
		
		else
		{
			System.out.println("The Items in cart are : " );// change this also !!!
			for ( Map.Entry<Integer, FoodItems> set : this.getCart().entrySet())
			{
				System.out.println(set.getValue().toString() + ", Quantiy - " + set.getKey());
			}
		}
	}
	
	public int totalQuantity()
	{
		if (this.getCart().isEmpty())
		{
			return 0;
		}
		
		int q = 0;
		for ( Map.Entry<Integer, FoodItems> set : this.getCart().entrySet())
		{
			q += set.getKey();
		}
		return q;
	}
	
	//setdElivery Rate()
	public void setDeliveryRate(int newDeliveryrate)
	{
		this.deliveryCharge = newDeliveryrate;
	}
	
	public void setWallet(float f)
	{
		this.wallet -= f;	
	}
	
	public float calculateDiscount()
	{
		return this.calculateRestaurantDiscount();
	}
	
	public void setReward(float f)
	{
		this.reward = f;
	}
	
	public void addReward(float f)
	{
		this.reward += f;
	}
	
	public void AddToCart(FoodItems f, int q)
	{
		this.cart.put(q, f);
	}
	
	public void AddtoRecent(String s)
	{
		this.recentOrders.add(s);
	}
	
	public boolean canAfford(float cost)
	{
		
		if (cost + this.getDeliveryCharge() <= this.getWallet() + this.getReward()) 
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public float calculateItemDiscount()
	{
		float totalCostAfterItemDiscount = 0;
		for ( Map.Entry<Integer, FoodItems> set : this.getCart().entrySet())
		{
			totalCostAfterItemDiscount += (set.getKey())*(set.getValue().getCost())*(1 - set.getValue().getDiscount()/100);
		}
		return totalCostAfterItemDiscount;
		
		
	}
	
	public float calculateRestaurantDiscount()
	{
		float afterItemDiscount = calculateItemDiscount();
		return afterItemDiscount - (afterItemDiscount*(this.getRes().getDiscount()/100));
	}
	
	
	public  void setRes(Restaurant r)
	{
		this.selected_res = r;
	}
	public float totalCost()
	{
		float cost = this.calculateDiscount();
		
		return cost + this.deliveryCharge;
	}
	
	public void CheckOut()
	{
		if (this.canAfford(this.calculateDiscount()))
		{
			this.printCart();
			System.out.println("The total order value - INR " + this.calculateDiscount() + "/-");
			System.out.println("The delivery charge - INR " + this.getDeliveryCharge() + "/-");
			System.out.println("1) Proceed to Checkout");
			@SuppressWarnings("unused")
			int choice = scan.nextInt();

			if (this.totalCost() >= this.getReward()) {
				this.setReward(0);
				this.setWallet(this.totalCost() - this.getReward());
				this.addReward(this.getRes().addRewardPoints(this.calculateDiscount()));
			}
			
			else
			{
				this.setReward(this.getReward() - this.totalCost() + this.getRes().addRewardPoints(this.calculateDiscount()));
			}
			String toPrint = this.totalQuantity() + " items successfully bought for INR " + this.totalCost() + "/-";
			this.AddtoRecent(toPrint + " From the Restaurant " + this.getRes().getName());
			System.out.println(toPrint);
			this.getRes().addRewardPoints(this.calculateDiscount());
			Zotato.setCompanyBalence(this.calculateDiscount()/100);
			Zotato.setDeliveryCharges(this.getDeliveryCharge());
			this.getRes().addOrders();
			this.setRes(null);
			this.newCart();
		}
		
		else
		{
			System.out.println("The amount in wallet is not sufficient to order");
			System.out.println("Choose a food item to delete: ");
			this.printCart();
			int chosen = scan.nextInt();
			
			Iterator<Map.Entry<Integer, FoodItems> > iterator = cart.entrySet().iterator(); 

			while (iterator.hasNext()) 
			{ 
				Map.Entry<Integer, FoodItems> entry = iterator.next(); 
				if (chosen == entry.getValue().getID())
				{ 
					iterator.remove(); 
				} 
			}
			if (this.getCart().isEmpty())
			{
				this.setRes(null);
			}
			this.printCart();
			return;
		}
	}
	
	public void PrintRecentOrders()
	{
		if (this.getRecentOrders().isEmpty())
		{
			System.out.println("No orders made yet");
		}
		
		else
		{
			System.out.println("Input the number of recent orders (<= 10)");
			int k = scan.nextInt();
			for (int i = 0; i < Math.min(k, 10); i++)
			{
				System.out.println(this.getRecentOrders().get(i));
			}
		}
	}
	
	public void enter()
	{
		this.displayFunctions();
	}
	
	public void displayFunctions()
	{
		System.out.println("Welcome " + this.getName());
		System.out.println("Customer Menu ");//add the menu statement in restaurant
		System.out.println("1) Select Restaurant");
		System.out.println("2) Checkout Cart");
		System.out.println("3) Reward Won");
		System.out.println("4) Print the recent orders");
		System.out.println("5) Exit");
		
		int second_command = scan.nextInt();
		
		if (second_command == 1)
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("Choose Restaurant");

			int choose_restaurant = 0;
			if (this.getRes() == null) {
				for (int i = 0; i < Zotato.getRestaurants().size(); i++)
				{
					System.out.println((i+1) + ") " + Zotato.getRestaurants().get(i).getName() + " " + Zotato.getRestaurants().get(i).getTheClass());
				}

				choose_restaurant = scan.nextInt();
				if (Zotato.getRestaurants().get(choose_restaurant - 1).getMenu().isEmpty()) {
					System.out.println("Please choose another Restaurant.");
					this.displayFunctions();
					return;
				}
				
				this.setRes(Zotato.getRestaurants().get(choose_restaurant - 1));
				Zotato.getRestaurants().get(choose_restaurant-1).PrintMenu();
				
			}
			
			else
			{
				System.out.println(" Selected Restaurant");
				System.out.println(" 1) " + this.getRes().getName());
				this.getRes().PrintMenu();
				
			}
			int choose_dish = scan.nextInt();
			System.out.println("Enter item quantity: ");
			int choose_q = scan.nextInt();
			this.AddToCart(this.getRes().getMenu().get(choose_dish), choose_q);
			System.out.println("Items added to the cart");
			this.displayFunctions();
			return;
		}
		
		else if (second_command == 2)
		{
			
			this.CheckOut();
			this.displayFunctions();
			return;
		}
		
		else if (second_command == 3)
		{
			System.out.println("Reward Points : " + this.getReward());
			this.displayFunctions();
			return;
		}
		 
		else if (second_command == 4)
		{
			this.PrintRecentOrders();
			this.displayFunctions();
			return;
		}
		
		else if (second_command == 5)
		{
			return;
		}
		
		System.out.println("Wrong Command");
		this.displayFunctions();
		return;
		
		
	}

	@Override
	public String getTheClass()
	{
		return "";
	}



	@Override
	public void DisplayDetails() {
		System.out.println(this.getName() + this.getTheClass() + ", " + this.getResidence() + ", " + this.getWallet());
	}


	
}
