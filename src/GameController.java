import java.util.ArrayList;


public class GameController 
{
	private static final int USERINDEX = 0;
	Card[] centerCard = new Card[2];
	public static int playerIndexNow;
	private static GameController uniqueGC;
	private ArrayList<Player> player;
	
	private String gameState = "";

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
	
	public String turn(int cardIndex)
	{
		//TODO 測試
		removePair();
		chooseCard(cardIndex);
		removePair();
		changeGameState();
		changePlayer();
		return gameState;
	}
	
	
	private void chooseCard(int index) 
	{
		int playerIndex = getPlayerNextIndex();
		if(playerIndexNow != 0)
		{			
			index = ((AIPlayer)player.get(playerIndexNow)).autoPlay(player.get(playerIndex).getAmountOfCard());
		}
		
		//轉移卡
		Card card = player.get(playerIndex).getCard(index);
		player.get(playerIndex).removeCard(card);
		player.get(playerIndexNow).addCard(card);
		
	}
	
	private int getPlayerNextIndex()
	{
		int clock;
		if(GameInitial.clockWise == true)
			clock = 1;
		else
			clock = -1;
		
		return (playerIndexNow + clock)% CenterController.playerNumber;
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

	
	/*測試*/

	private void changeGameState()
	{
		if(player.get(USERINDEX).getAmountOfCard() > 0 && player.get(1).getAmountOfCard() <= 0 && player.get(2).getAmountOfCard() <= 0 
				&& player.get(3).getAmountOfCard() <= 0 )
		{
			gameState =  "Game Over";
		}
		else
		{
			if(player.get(USERINDEX).getAmountOfCard() <= 0)
				gameState = "Win";

		}
	}
	
	public void changePlayer()
	{
		playerIndexNow = (playerIndexNow + 1) % CenterController.playerNumber;
	}
	

}
