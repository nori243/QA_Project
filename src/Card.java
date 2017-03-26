
public class Card 
{
	private String suit;
	private int num;
	
	public Card(int suit,int num) 
	{
		
		
		
		if(suit == 1)
		{
			this.suit = "spades";
		}
		else if(suit == 2)
		{
			this.suit = "hearts";
		}
		else if(suit == 3)
		{
			this.suit = "diamonds";
		}
		else if(suit == 4)
		{
			this.suit = "clubs";
		}
		else if(suit == 0)
		{
			this.suit = "joker";
		}
		else
		{
			suit = 0;
		}
		
		if(num > 0 && num <= 13)
		{
			this.num = num;
		}
		else
		{
			num = 0;
			this.suit = "joker";			
		}
		
	}
	
	/*---compare----*/
	public boolean IsSuitSame(Card card)
	{
		if(card.getSuit().equals(this.getSuit()))
			return true;
		else
			return false;
	}
	
	public boolean IsPair(Card card)
	{
		if(card.getNum() == this.getNum())
			return true;
		else
			return false;
	}

	
	/*---getter---*/
	public String getSuit() 
	{
		return suit;
	}

	public int getNum() 
	{
		return num;
	}
	
	

}
