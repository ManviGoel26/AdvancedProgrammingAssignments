package assignment3;

import java.util.Collections;
//import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

class Gameplay 
{
	private GenHashMap<Integer, Members> players;
	private GenHashMap<Integer, Mafia> mafias;
	private GenHashMap<Integer, Healer> healers;
	private GenHashMap<Integer, Detective> detectives;
	private GenHashMap<Integer, Commoner> commoners;
	
	
	private int n;
	private Members User;
	private int[] number = new int[4];
	public Gameplay()
	{
		players = new GenHashMap<Integer, Members>();
		mafias = new GenHashMap<Integer, Mafia>();
		healers = new GenHashMap<Integer, Healer>();
		detectives = new GenHashMap<Integer, Detective>();
		commoners = new GenHashMap<Integer, Commoner>();
		
		
		n = 0;
		start(); //starting the function
		after_win();
	}
	
	
	private void after_win()
	{
		System.out.println("The Mafias were " + mafias.printPlayers());
		System.out.println("The Detectives were " + detectives.printPlayers());
		System.out.println("The Healers were " + healers.printPlayers());
		System.out.println("The Commoners were " + commoners.printPlayers());
//		printCommoners();
	}
	
	
	private boolean setN(String num)
	{
		try 
		{
			int j = Integer.valueOf(num);
			if (j >= 6)
			{
				this.n = j;
				return true;
			}
			
			else
			{
				System.out.println("The minimum number of players is 6. Please enter a bigger number");
				return false;
			}
		}
		
		catch (Exception e)
		{
			System.out.println("Please provide an integer input");
			return false;
		}
		
		
	}
	
	private void printPartner()
	{
		if (this.User instanceof Mafia)
		{
			System.out.println("You are a Mafia. All Mafias are " + this.mafias.printPlayers());
		}
		
		else if (this.User instanceof Detective)
		{
			System.out.println("You are a Detective. All Detectives are " + this.detectives.printPlayers());
		}
		
		else if (this.User instanceof Healer)
		{
			System.out.println("You are a Healer. All Healers are " + this.healers.printPlayers());
		}
		
		else if (this.User instanceof Commoner)
		{
			System.out.println("You are a Commoner");
		}
	}
	
	
	private void start()
	{
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("Welcome to Mafia"); //Starting of the game
		System.out.println("Enter the number of players: ");
		
		while (this.n == 0)
		{
			String n = scan.next();
			this.setN(n);
		}
		
		this.setCharacter(); //setting up the user
		
		this.setPlayers(); //setting up the other players
		
		printPartner(); //printing the partners
//		this.players_all = this.players;
		
		int round = 0;
		while (!(this.win()))
		{
			//Game
			round++;
			
			//print players
			System.out.println(players.size() + " Players are remaining: " + players.printPlayers());
			
			
			
			//Mafia kill
			int target = 0;
			boolean kill = false;
			
			System.out.println("Round" + round);
			
			if (User instanceof Mafia && players.containsKey(User.getID()))
			{
				System.out.println("Choose a target");
				while (target == 0)
				{
					String t = scan.next();
					target = scanInt(t, this.n, 1);
					
					if (!players.containsKey(target))
					{
						System.out.println("The player is not in the game. Please pick another player to target");
						target = 0;
					}
					
					else if (mafias.containsKey(target))
					{
						System.out.println("You cannot target a mafia, Pick another player");
						target = 0;
					}
					
					
				}
				double initialHP = players.get(target).getHP();
				
				players.get(target).die(players.getCombinedHP());
				
				reduceHPs(initialHP);
				System.out.println("Mafias have chosen their target");
				
			}
			
			else if (players.countMember("Mafia") > 0)
			{
				target = Mafia.VoteTokill(players);
				double initialHP = players.get(target).getHP();
				
				players.get(target).die(players.getCombinedHP());
				
				reduceHPs(initialHP);
				System.out.println("Mafias have chosen their target");
				
			}
			
			else
			{
				System.out.println("Mafias have chosen their target");
			}
			
			
			//Detective test
			int test = 0;
			boolean voted = false;
			
			if (User instanceof Detective && players.containsKey(User.getID()))
			{
				System.out.println("Choose a Player to test");
				while (test == 0)
				{
					String t = scan.next();
					test = scanInt(t, this.n, 1);
					
					if (!players.containsKey(test))
					{
						System.out.println("The player is not in the game. Please pick another player to test");
						test = 0;
					}
				
					else if (detectives.containsKey(test))
					{
						System.out.println("You cannot test a detective, Pick another player");
						test = 0;
					}
					
				}
				
				if (User.equals(players.get(test)))
				{
					voted = true;
					System.out.println("Player"+ test+ " is a Mafia");
					players.remove(test);
				}
				
				else
				{
					System.out.println("Player"+ test + " is not a Mafia");
				}
			}
			
			else if (players.countMember("Detective") > 0)
			{
				test = Detective.VoteToTest(players);
				if (test != 0)
				{
					if ((detectives.values().toArray()[0]).equals(players.get(test)))//some 
					{
						voted = true;
						players.remove(test);
					}
					System.out.println("Detectives have chosen a player to test");
				}
				
			}
			
			else
			{
				System.out.println("Detectives have chosen a player to test");
			}
			
			
			//Healer heal
			int heal = 0;
			if (User instanceof Healer && players.containsKey(User.getID()))
			{
				System.out.println("Choose a Player to heal");
				while (heal == 0)
				{
					String t = scan.next();
					heal = scanInt(t, this.n, 1);
					
					if (!players.containsKey(heal))
					{
						System.out.println("The player is not in the game. Please pick another player to heal");
						heal = 0;
					}
				}
				players.get(heal).heal();
				
				
			}
			
			else if (players.countMember("Healer") > 0)
			{
				heal = Healer.toHeal(players, this.n);
				players.get(heal).heal();
				System.out.println("Healers have chosen someone to heal");
			}
			
			else 
			{
				System.out.println("Healers have chosen someone to heal");
			}
			
			System.out.println("--End of actions--");
			
			//person dying
			if (players.containsKey(target) && players.get(target).getHP() == 0)
			{
				System.out.println("Player"+ target + " has died");
				players.remove(target);
			}
			
			else 
			{
				System.out.println("No one has died");
			}
			
			//Voting
			int voted_out = 0;
			
			if (!voted && players.containsKey(User.getID()))
			{
				System.out.println("Select a player to vote out");
				while (voted_out == 0)
				{
					String t = scan.next();
					voted_out = scanInt(t, this.n, 1);
					
					if (!players.containsKey(voted_out))
					{
						System.out.println("The player is not in the game. Please pick another player to vote out");
						voted_out = 0;
					}
				}
				
				
				//voting system
				int voted_out2 = voting(voted_out);
				players.remove(voted_out2);
				
				
				System.out.println("Player" + voted_out2 + " has been voted out");
				
			}
			
			else if (!voted)
			{
				int voted_out2 = voting(voted_out);
				players.remove(voted_out2);
				
				
				System.out.println("Player" + voted_out2 + " has been voted out");
				
			}
			
			else
			{
				System.out.println("Player" + test + " has been voted out");
			}
			
			System.out.println("--End of Round " + round + " --");
			System.out.println("                                ");
			
			 
			
		}
		
		
	}
	
	
	
	private void setPlayers()
	{
		Random random = new Random();
		int id = random.nextInt(n) + 1;
//		System.out.println("players");
			

		while (this.number[0] < (int)(n/5))
		{
			while (players.containsKey(id))
			{
				id = random.nextInt(n) + 1;
			}
//			System.out.println("players");
			
			players.put(id, new Mafia(id));
			number[0] += 1;
			mafias.put(id, (Mafia) players.get(id));
//			System.out.println(this.mafias.printPlayers());
			
		}
		
		while (this.number[1] < (int)(n/5))
		{
//			System.out.println("de");
			while (players.containsKey(id))
			{
				id = random.nextInt(n) + 1;
			}
			
			players.put(id, new Detective(id));
			number[1] += 1;
			detectives.put(id, (Detective) players.get(id));
		}
		
		while (this.number[2] < Math.max(1, (int)(n/10)))
		{
//			System.out.println("heal");
			while (players.containsKey(id))
			{
				id = random.nextInt(n) + 1;
			}
			
			players.put(id, new Healer(id));
			number[2] += 1;
			healers.put(id, (Healer) players.get(id));
		}
		int commoners = 0;
		if (User instanceof Commoner)
		{
			commoners = this.n - players.size() + 1;
		}
		else
		{
			commoners = this.n - players.size();
		}
		while (this.number[3] < commoners)
		{
//			System.out.println("onfesri");
			while (players.containsKey(id))
			{
				id = random.nextInt(n) + 1;
			}
			
			players.put(id, new Commoner(id));
			number[3] += 1;
			this.commoners.put(id, (Commoner) players.get(id));
			
		}
		
		
		
	}
	
	
	
	private void setCharacter()// add the other people 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose a character");
		System.out.println("1) Mafia");
		System.out.println("2) Detective");
		System.out.println("3) Healer");
		System.out.println("4) Commoner");
		System.out.println("5) Assign Randomly");
		
		int chose = 0;
		while (chose == 0)
		{
			String k = scan.next();
			chose = scanInt(k, 5, 1);
		}
		
		Random random = new Random(); 
        int id = random.nextInt(this.n) + 1;
//        System.out.println(id);
        
        if (chose == 1)
        {
        	User = new Mafia(id);
//        	System.out.println(User.getID() == id);
        	number[0] += 1;
        	mafias.put(id, (Mafia) User);
        	System.out.println("You are Player" + User.getID());
        }
        else if (chose == 2)
        {
        	User = new Detective(id);
        	number[1] += 1;
        	detectives.put(id, (Detective) User);
        	System.out.println("You are Player" + User.getID());
//        	System.out.println("You are a Detective. All Detectives are " + this.detectives.printPlayers());
            
        }
        else if (chose == 3)
        {
        	User = new Healer(id);
        	number[2] += 1;
        	healers.put(id, (Healer) User);
        	System.out.println("You are Player" + User.getID());
//    		System.out.println("You are a Healer. All Healers are " + this.healers.printPlayers());
        }
        else if (chose == 4)
        {
        	User = new Commoner(id);
        	number[3] += 1;
        	System.out.println("You are Player" + User.getID());
//    		System.out.println("You are a Commoner.");
        	this.commoners.put(id, (Commoner) User);
			
        	
        }
        else if (chose == 5)
        {
        	
        	int rc = random.nextInt(4) + 1;
        	
        	if (rc == 1)
            {
            	User = new Mafia(id);
            	number[0] += 1;
            	mafias.put(id, (Mafia) User);
            	System.out.println("You are Player" + User.getID());
//        		System.out.println("You are a Mafia. All Mafias are " + this.mafias.printPlayers());
                
            }
            else if (rc == 2)
            {
            	User = new Detective(id);
            	number[1] += 1;
            	detectives.put(id, (Detective) User);
            	System.out.println("You are Player" + User.getID());
//        		System.out.println("You are a Detective. All Detectives are " + this.detectives.printPlayers());
            	
                
            }
            else if (rc == 3)
            {
            	User = new Healer(id);
            	number[2] += 1;
            	healers.put(id, (Healer) User);
            	System.out.println("You are Player" + User.getID());
//        		System.out.println("You are a Healer. All healers are " + this.healers.printPlayers());
            }
            else if (rc == 4)
            {
            	User = new Commoner(id);
            	number[3] += 1;
            	this.commoners.put(id, (Commoner) User);
            	System.out.println("You are Player" + User.getID());
//        		System.out.println("You are a Commoner");
                
            }
        }
        players.put(id, User);
        
        
            
		
		
	}
	
	private boolean win()
	{
		if (players.countMafia() == players.countMember("Detective") + players.countMember("Commoner") + players.countMember("Healer"))
		{
			System.out.println("Game Over");
			System.out.println("The Mafias have won");
			return true;
		}
		
		else if (players.countMafia() == 0)
		{
			System.out.println("Game Over");
			System.out.println("The Mafias have lost");
			return true;
		}
		
		return false;
	}
	
	private int scanInt(String num, int maximum, int minimum)
	{
		try 
		{
			int k  = Integer.valueOf(num);;
			
			if (k >= minimum && k <= maximum)
			{
//				System.out.println("erro");
				return k;
			}
			
			else if (k < minimum)
			{
				System.out.println("Please enter a bigger number");
				return 0;
			}
			
			else
			{
				System.out.println("Please enter a smaller number");
				return 0;
			}
		}
		
		catch (Exception e)
		{
			System.out.println("Please provide an integer input");
			return 0;
		}
		 
	}
	
		
	private void reduceHPs(double targetsHP)
	{
		double damage = players.reduceDamage(targetsHP, players.countMember("Mafia"));
		while (damage > 0 && players.countMember("Mafia") > 0)
		{
			System.out.println("keeps repeating");
			damage = players.reduceDamage(damage, players.countMember("Mafia"));
//			break;
		}
	}
	
	
	
	private <K, V extends Comparable<V>> K max(Map<K, V> map) {
		
	    Entry<K, V> maxEntry = Collections.max(map.entrySet(), (Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue()
	        .compareTo(e2.getValue()));
	    
		    
	    return maxEntry.getKey();
	}

	private int voting(int Users_vote)
	{
		HashMap<Integer, Integer> votes = new HashMap<Integer, Integer>();
		
		for (Map.Entry<Integer, Members> entry : this.players.entrySet())
        {
			int voteX = 0;
			if (entry.getValue().equals(this.User))
			{
				voteX = Users_vote;
			}
			else
			{
				voteX = entry.getValue().vote(players, this.n);
			}
			
			if (votes.containsKey(voteX))
			{
				votes.replace(voteX, votes.get(voteX)+1);
			}
			
			else
			{
				votes.put(voteX, 1);
			}
        }
		
		return max(votes);
	}
	
	@Override // Equals function
    public boolean equals(Object pX){
        if (pX !=null && pX.getClass() == this.User.getClass()){
        	Members copy = (Members) pX;
        	return copy.getID() == User.getID();
        }
        return false;
    }

}
