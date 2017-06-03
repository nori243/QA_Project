import java.util.ArrayList;
import java.util.Observable;


public class GameController extends Observable
{
	private static final int USERINDEX = 0;
	Card[] centerCard = new Card[2];
	public static int playerIndexNow;
	private static GameController uniqueGC;
	private ArrayList<Player> player;
	
	private String gameState = "";
	private int cardIndex = -1;

	public GameController(ArrayList<Player> player)
	{
		this.player = player;
		playerIndexNow = GameInitial.startPlayerIndex;
		gameState = "";
		
	} 
	
	public static GameController getInstance(ArrayList<Player> player)
	{
		if(uniqueGC == null)
		{
			uniqueGC = new GameController(player);
		}
		
		return uniqueGC;
	}
	
	
	public String getState()
	{
		return gameState;
	}
	
	public String turn(int cardIndex)
	{
		if(cardIndex !=  -1)
		{			
			chooseCard(cardIndex);
			removePair(playerIndexNow);			
		}
		
		changeGameState();
		changePlayer();		
		
		this.setChanged();
		this.notifyObservers();
		
		return gameState;
	}
	
	public int AIChooseCard()
	{		
		//TODO TEST
		int index = ((AIPlayer)player.get(playerIndexNow)).autoPlay(player.get(getPlayerNextIndex()).getAmountOfCard());
		
		return index;
		
		
	}
	
	public int getChooseIndex()
	{
		return cardIndex;
	}
	
	private void chooseCard(int index) 
	{
		cardIndex = index;
		
		//Âà²¾¥d
		Card card = player.get(getPlayerNextIndex()).getCard(index);
		player.get(getPlayerNextIndex()).removeCard(card);
		player.get(playerIndexNow).addCard(card);
		
	}
	
	public int getPlayerNextIndex()
	{
		int clock,index;
		
		if(GameInitial.clockWise == true)
			clock = 1;
		else
			clock = -1;
		
		index = (playerIndexNow + clock)% CenterController.playerNumber + CenterController.playerNumber;
		index = index % CenterController.playerNumber;
		
		
		return index;
	}

	public void removePair(int playerIndex)
	{
		Player playerNow = this.player.get(playerIndex);
		int playerCardNum = playerNow.getAmountOfCard();
		System.out.println(playerCardNum);
		int i = 0;
		int size = playerNow.getAmountOfCard();
		while(playerNow.hasPair() && size > 0)
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
			
			this.player.set(playerIndex, playerNow);
		}
	}	

	
	private void changeGameState()
	{
		if(player.get(USERINDEX).getAmountOfCard() > 0 && (player.get(1).getAmountOfCard() <= 0 || player.get(2).getAmountOfCard() <= 0 
				|| player.get(3).getAmountOfCard() <= 0 ))
		{
			gameState =  "Game Over";
		}
		else
		{
			if(player.get(USERINDEX).getAmountOfCard() <= 0)
				gameState = "Win";
			else
				gameState = "";

		}
		
		this.setChanged();
		this.notifyObservers();
	}
	

	
	public void changePlayer()
	{
		System.out.println("change");
		playerIndexNow = getPlayerNextIndex();
	}
	

}
