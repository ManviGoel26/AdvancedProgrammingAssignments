package assignment3;

//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Random;

public class Healer extends Members
{
	private final int ID;
	private double HP;
	
	
	public Healer(int id)
	{
		this.ID = id;
		this.HP = 800;
		
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
	
	protected static int toHeal(GenHashMap<Integer, Members> players, int n)
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
	
	
	@Override
	public void heal()
	{
		this.HP += 500;
	}
	
	
}
