package assignment3;

//import java.util.HashMap;
import java.util.Random;

public abstract class Members //check abstract
{
	private int ID;
	private double HP;
	
	 
	int vote(GenHashMap<Integer, Members> players, int n)
	{
		Random random = new Random(); 
		int chosen = 0;
		
		while (chosen == 0)
		{
			chosen = random.nextInt(n) + 1;
	        if (!players.containsKey(chosen))
	        {
	        	chosen = 0;
	        }
	        
		}
        return players.get(chosen).getID();
	}
	
	public int getID()
	{
		
		return this.ID;
	}
	
	public double getHP()
	{
		return this.HP;
	}
	
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
	
	public void heal()
	{
		this.HP += 500;
	}
	
	public double reduceHP(double reduction)
	{
		if (this.HP >= reduction)
		{
			this.HP -= reduction;
			return 0;
		}
		
		
		return reduction - this.HP;
		
		
	}
	


}
