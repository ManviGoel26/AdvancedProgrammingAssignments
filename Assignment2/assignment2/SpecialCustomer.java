package assignment2;

public class SpecialCustomer extends Customer
{
	public SpecialCustomer(String n, String s)
	{
		super(n, s);
		this.setDeliveryRate(20);
	}
	
	@Override
	public String getTheClass()
	{
		return "(Special)";
	}

	@Override
	public float calculateDiscount()
	{
		float afterResDiscount = this.calculateRestaurantDiscount();
		if (afterResDiscount > 200)
		{
			return afterResDiscount*75/100;
		}
		return afterResDiscount;
	}
}
