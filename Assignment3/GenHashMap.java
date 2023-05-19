package assignment3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings("hiding")
public class GenHashMap <Integer, T>
{
	private HashMap<Integer, T> hm;
	public GenHashMap()
	{
		hm = new HashMap<Integer, T>();
	}
	
	public void put( Integer key, T o)
	{
		hm.put(key, o);
	}
	
	public T get(int i)
	{
		return hm.get(i);
	}
	
	String printPlayers() //put in generic programming
	{
		String s = "";
		
		for (Map.Entry<Integer, T> entry : hm.entrySet())
        {
			s += "Player"+ entry.getKey()+ " " ;
        }
		return s;

	}

	public int size() 
	{
		return hm.size();
	}

	
	public boolean containsKey(int id) 
	{
		return hm.containsKey(id);
	}

	public Set<Entry<Integer, T>> entrySet() 
	{
		return hm.entrySet();
	}

	public void remove(int test) 
	{
		hm.remove(test);
	}

	public Collection<T> values() 
	{
		return hm.values();
	}
	
	int countMember(String s)
	{
		int count = 0;
		if (s.equalsIgnoreCase("Mafia"))
		{
			for (Map.Entry<Integer, T> entry : this.hm.entrySet())
	        {
				if ((entry.getValue()instanceof Mafia) && ((Mafia) entry.getValue()).getHP() > 0)
				{
					count++;
				}
	        }
		}
		
		else if (s.equalsIgnoreCase("Detective"))
		{
			for (Map.Entry<Integer, T> entry : this.hm.entrySet())
	        {
				if (entry.getValue()instanceof Detective)
				{
					count++;
				}
	        }
			
		}
		else if (s.equalsIgnoreCase("Healer"))
		{
			for (Map.Entry<Integer, T> entry : this.hm.entrySet())
	        {
				if (entry.getValue()instanceof Healer)
				{
					count++;
				}
	        }
			
		}
		else if (s.equalsIgnoreCase("Commoner"))
		{
			for (Map.Entry<Integer, T> entry : this.hm.entrySet())
	        {
				if (entry.getValue() instanceof Commoner )
				{
					count++;
				}
	        }
			
		}
		return count;
		
	}
	
	double reduceDamage(double targetsHP, int count)
	{
		
		double reduce = targetsHP/count;
		double damage = 0;
		
		for (Map.Entry<Integer, T> entry : this.hm.entrySet())
        {
			if ((entry.getValue()instanceof Mafia) && ((Mafia) entry.getValue()).getHP() > 0)
			{
				damage += ((Mafia) entry.getValue()).reduceHP(reduce);
			}
        }
		
		
		return damage;
		
	}
	
	int getCombinedHP()
	{
		int totalHP = 0;
        for (Map.Entry<Integer, T> entry : this.hm.entrySet()) //wrong
        {
        	if ((entry.getValue()instanceof Mafia) && ((Mafia) entry.getValue()).getHP() > 0)
			{
        		totalHP += ((Mafia)entry.getValue()).getHP();
                
			}
        }

		return totalHP;
	}
	
	
	int countMafia()
	{
		int count = 0;
		for (Map.Entry<Integer, T> entry : this.hm.entrySet())
        {
			if (entry.getValue() instanceof Mafia )
			{
				count++;
			}
        }
		
		return count;
	
	}
	


}
