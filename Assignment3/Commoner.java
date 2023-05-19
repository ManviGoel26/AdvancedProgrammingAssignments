package assignment3;


class Commoner extends Members
{
	private final int ID;
	private double HP;
	
	public Commoner(int id)
	{
		this.ID = id;
		this.HP = 1000;
	}
	
	@Override
	public int getID()
	{
		return this.ID;
	}
	
	@Override
	public double getHP()
	{
		return this.HP;
	}
	
	@Override
	public boolean die(int combinedHP)
	{
		if (combinedHP >= this.HP)
		{
			this.HP = 0;
			return true;
		}
		
		else
		{
			this.HP -= combinedHP;
			return false;
		}
	}
	
	@Override
	public void heal()
	{
		this.HP += 500;
	}
	
	
	
}

