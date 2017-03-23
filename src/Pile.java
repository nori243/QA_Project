import java.util.ArrayList;


public class Pile 
{
	public static int CARD_NUM = 53;
	public static int CARD_NUM_PER_SUIT = 13;
	public static int SUIT_NUM = 4;
	
	private ArrayList<Card> pile ;
	
	
	public Pile()
	{		
		pile = new ArrayList<Card>();
		init();
	}
	
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
	
	public boolean removeCard(Card card)
	{
		if(pile.isEmpty())
			return false;
		
		for(int i=0;i<getAmountOfCard();i++)
		{
			if(pile.get(i).IsPair(card))
			{
				if(pile.get(i).IsSuitSame(card))
				{
					pile.remove(i);
					return true;
				}
			}
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

}
