package assignment2;

public class EliteCustomer extends Customer
{
	public EliteCustomer(String n, String r)
	{
		super(n, r);
		this.setDeliveryRate(0);
		
		
	}
	
	@Override
	public String getTheClass()
	{
		return "(Elite)";
	}
	
	@Override
	public float calculateDiscount()
	{
		float afterResDiscount = this.calculateRestaurantDiscount();
		if (afterResDiscount > 200)
		{
			return afterResDiscount - 50;
		}
		return afterResDiscount;
	}
	
	
	
	
	
}


