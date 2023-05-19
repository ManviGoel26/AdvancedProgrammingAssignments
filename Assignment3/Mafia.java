package assignment3;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Mafia extends Members
{
	private double HP;
	private final int ID;
	
	
	public Mafia(int id)
	{
		this.ID = id;
		this.HP = 2500;
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
	public void heal()
	{
		this.HP += 500;
	}
	
	protected static int VoteTokill(GenHashMap<Integer, Members> players)
	{
		ArrayList<Members> non_mafias = new ArrayList<Members>();
		for (Map.Entry<Integer, Members> entry : players.entrySet())
        {
			if (!(entry.getValue() instanceof Mafia))
			{
				non_mafias.add(entry.getValue());
				
			}
        }
		
		
		Random random = new Random(); 
        int chosen = random.nextInt(non_mafias.size());
        return non_mafias.get(chosen).getID();
	}
	
	@Override
	public double reduceHP(double reduction)
	{
		if (this.HP >= reduction)
		{
			this.HP -= reduction;
			return 0;
		}
		
		double ret = reduction -this.HP;
		this.HP = 0;
		return ret;
		
		
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
	
	
	
	
	
	
	
}
