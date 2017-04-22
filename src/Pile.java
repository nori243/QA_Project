import java.util.ArrayList;
import java.util.Random;


public class Pile 
{
	public static int CARD_NUM = 53;
	public static int CARD_NUM_PER_SUIT = 13;
	public static int SUIT_NUM = 4;
	
	private ArrayList<Card> pile ;
	
	
	public Pile()
	{		
		pile = new ArrayList<Card>();
	
	}
	
	/*TODO any else method to do init?*/
	public void init()
	{
		try
		{
			for(int i=0;i<SUIT_NUM;i++)
			{
				for(int j=0;j<CARD_NUM_PER_SUIT;j++)
				{
					pile.add(new Card(i+1,j+1));		
				}
			}
			pile.add(new Card(0,0));
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void addCard(Card card)
	{
		pile.add(card);
	}
	
	public int getAmountOfCard()
	{
		return pile.size();
	}
	
	public int getCardIndex(Card card)
	{
		if(!pile.isEmpty())
		{
			for(int i=0;i<getAmountOfCard();i++)
			{
				if(pile.get(i).IsPair(card) && pile.get(i).IsSuitSame(card))
				{
					return i;					
				}
			}
		}
		
		return -1;
	}
	
	public int getPairIndex(Card card)
	{
		if(!pile.isEmpty())
		{
			for(int i=0;i<getAmountOfCard();i++)
			{
				if(pile.get(i).IsPair(card))
				{
					return i;					
				}
			}
		}
		
		return -1;
	}
	
	public boolean removeCard(Card card)
	{
		int index = getCardIndex(card);
		if(index >= 0)
		{
			pile.remove(index);
			return true;
		}
		
		return false;
	}
	
	public boolean removePair(Card card)
	{
		int index_1 = getPairIndex(card);
		int index_2 = getCardIndex(card);
		if(index_1 >= 0 && index_2 >= 0)
		{
			pile.remove(index_1);
			pile.remove(index_2);
			return true;
		}
		
		
		return false;
		
	}
	
	public String ShowCards()
	{
		String rt = "";
		for(int i=0;i<getAmountOfCard();i++)
		{
			rt = rt + pile.get(i).getSuit() + " " + pile.get(i).getNum() + "\n";
		}
		
		return rt;
	}
	
	public Card getCard(int index) throws Exception
	{
		if(index >= this.getAmountOfCard())
		{
			throw new Exception("index out of range");
		}
		
		return pile.get(index);
	}
	
	public void shufflingCard()
	{
		Random r = new Random();
		ArrayList<Card> newPile = new ArrayList<Card>();
		
		for(int i = 0; i < pile.size() ; i++)
		{
			newPile.add(null);			
		}
		
		for(int i = 0; i < pile.size() ; i++)
		{
			int index = r.nextInt(newPile.size());
			while(newPile.get(index) != null)
			{
				index = index + 1;
				if(index >= newPile.size())
					index = 0;
			}
				
			newPile.set(index, pile.get(i));
		}
		
		pile = newPile;
	}

}