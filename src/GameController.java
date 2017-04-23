import java.util.ArrayList;


public class GameController 
{
	Card[] centerCard = new Card[2];
	public static int playerIndexNow;
	private static GameController uniqueGC;
	private ArrayList<Player> player;

	private GameController(ArrayList<Player> player)
	{
		this.player = player;
		playerIndexNow = GameInitial.startPlayerIndex;
	}
	
	public static GameController getInstance(ArrayList<Player> player)
	{
		if(uniqueGC == null)
		{
			uniqueGC = new GameController(player);
		}
		
		return uniqueGC;
	}
	
	public void removePair()
	{
		Player playerNow = this.player.get(playerIndexNow);
		int playerCardNum = playerNow.getAmountOfCard();
		System.out.println(playerCardNum);
		int i = 0;
		int size = playerNow.getAmountOfCard();
		while(playerNow.hasPair())
		{
			if(playerNow.getPairIndex(playerNow.getCard(i)) >= 0)
			{
				if(playerNow.removePair(playerNow.getCard(i)))
				{
					i = 0;
					assert size > playerNow.getAmountOfCard():"not remove";
				}			
				i = i+1;
			}				
			else	
				i = i + 1;
			if(i >= playerNow.getAmountOfCard())
			{
				i=0;
			}
			this.player.set(playerIndexNow, playerNow);
		}
	}	

	private boolean hasJoker()
	{
		return false;
	}
	
	public void turn()
	{
		
	}
	
	public void changePlayer()
	{
		
	}
}
