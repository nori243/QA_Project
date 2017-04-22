
public class Player 
{
	private Pile pile;
	private String name;
	
	public Player(String name,Pile p)
	{
		init(name,p);
	}
	
	public void init(String name,Pile p)
	{
		setPile(p);
		setName(name);
	}
	
	public void setPile(Pile p)
	{
		pile = p;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAmountOfCard()
	{
		return pile.getAmountOfCard();
	}

	/*----player actions----*/
	
	public void addCard(Card card)
	{
		pile.addCard(card);
	}
	
	public boolean removeCard(Card card)
	{
		return pile.removeCard(card);
	}
	public boolean removePair(int index)
	{
		Card pair = null;
		try 
		{
			pair = pile.getCard(index);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pile.removePair(pair);
	}
	
	public Card getCard(int i)
	{
		Card find = null;
		try 
		{
			find = pile.getCard(i);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return find;
	}
	
	public int getCardIndex(Card card)
	{
		return pile.getCardIndex(card);
	}
	
	public int getPairIndex(Card card)
	{
		return pile.getPairIndex(card);
	}
	
	
	/**/
	public boolean hasJoker()
	{
		int index = pile.getCardIndex(new Card(0,0));
		if(index >= 0)
			return true;
					
		return false;
	}
	
	public String showCard()
	{
		return pile.ShowCards();
	}
	
}
