package assignment3;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Detective extends Members
{
	private double HP;
	private int ID;
	
	public Detective(int id)
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
	public void heal()
	{
		this.HP += 500;
	}
	
	
	protected static int VoteToTest(GenHashMap<Integer, Members> players) 
	{
		ArrayList<Members> non_detectives = new ArrayList<Members>();
		for (Map.Entry<Integer,Members> entry : players.entrySet())
        {
			if (!(entry.getValue() instanceof Detective))
			{
				non_detectives.add(entry.getValue());
			}
        }
		
		Random random = new Random(); 
        int chosen = random.nextInt(non_detectives.size());
        
        return non_detectives.get(chosen).getID();
	}
	
    @Override
    public boolean equals(Object pX){
        if (pX !=null && pX instanceof Mafia){
            return true;
        }
        return false;
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
