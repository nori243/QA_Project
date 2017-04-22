
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
