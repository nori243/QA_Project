
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
	
	/*----player actions----*/
	public void addCard(Card card)
	{
		pile.addCard(card);
	}
	
	public boolean removeCard(Card card)
	{
		return pile.removeCard(card);
	}
	
	public int findCard(Card card)
	{
		return pile.findCard(card);
	}
	
	public int findPair(Card card)
	{
		return pile.findPair(card);
	}
	
	/**/
	public int checkJoker()
	{
		int index = -1;

		index = pile.findPair(new Card(0,0));
					
		return index;
	}
	public String showCard()
	{
		return pile.ShowCards();
	}
	
}
