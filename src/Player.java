
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
		pile.shufflingCard();
	}
	
	public boolean removeCard(Card card)
	{
		boolean r = pile.removeCard(card);
		
		pile.shufflingCard();
		return r ;
	}
	public boolean removePair(Card card)
	{	
		boolean r =  pile.removePair(card);
		
		pile.shufflingCard();
		return r ;
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
			find = null;
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
	
	public Pile getPile()
	{
		//TODO
		return this.pile;
	}
	
	
	/**/
	
	public boolean hasPair()
	{
		return pile.hasPair();
	}
	
	public boolean hasJoker()
	{
		int index = pile.getCardIndex(new Card(0,0));
		if(index >= 0)
			return true;
					
		return false;
	}
	
	public String showCard()
	{
		return pile.showCards();
	}
	
	public void removeAllCard()
	{
		pile.removeAll();
	}
	
	
}
