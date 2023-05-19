package assignment2;


import java.util.Scanner;

public class FastFoodRestaraunt extends Restaurant
{
	private static Scanner scan = new Scanner(System.in);
	
	FastFoodRestaraunt(String s, String a)
	{
		super(s, a);
		
	}

	public void getDiscountPoints()
	{
		
		System.out.println("Please specify the amount of overall discount");
		float Discount_value = scan.nextFloat();
		this.setDiscount(Discount_value);
		
	}
	
	@Override
	public float addRewardPoints(float TotalBill)
	{
		float points = 10*((int)TotalBill/150);
		return points;
	}
	
	@Override
	public String getTheClass()
	{
		return "(Fast Food)";
	}
	
	@Override
	public void getDiscountQuery4()
	{
		this.getDiscountPoints();
		return;
	}
}
