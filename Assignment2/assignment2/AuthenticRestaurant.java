package assignment2;

import java.util.Scanner;

public class AuthenticRestaurant extends Restaurant {
	
	private static Scanner scan = new Scanner(System.in);
	
	AuthenticRestaurant(String s, String a)
	{
		super(s, a);
	}
	
	public void getDiscountPoints()
	{
		
		System.out.println("Please specify the amount of overall discount");
		float Discount_value = scan.nextFloat();
		this.setDiscount(Discount_value);
	}

//Make interfaces for reward, total bill
	
	public float addRewardPoints(float TotalBill)
	{
		float points = 25*((int) TotalBill/200);
		return points;
	}
	
	@Override
	public String getTheClass()
	{
		return "(Authentic)";
	}

	@Override
	public void getDiscountQuery4()
	{
		this.getDiscountPoints();
		return;
	}

}
