import java.util.ArrayList;
import java.util.Random;


public class GameInitial 
{
	private static GameInitial uniqueGI;
	
	private Pile orgCard = new Pile();
	public static int startPlayerIndex ;
	public static boolean clockWise ;
	private String name = "user";
	private ArrayList<Player> player;
	
	private GameInitial(ArrayList<Player> player)
	{
		this.player = player;
		this.initGame();			
	}
	
	public static GameInitial getInstance(ArrayList<Player> player)
	{
		if(uniqueGI == null)
		{
			uniqueGI = new GameInitial(player);
		}
		
		return uniqueGI;
	}
	
	
	public void initGame()
	{		
		for(int i = 0;i < CenterController.playerNumber ;i++)
		{
			player.get(i).removeAllCard();
		}
		
		orgCard.init();
		shufflingCard();
		chooseStartPlayer();
		chooseOrder();
		dealing();
	}
	


	private void chooseOrder()
	{
		Random r = new Random();
		this.clockWise = r.nextBoolean();		
	}

	private void chooseStartPlayer()
	{
		Random r = new Random();
		startPlayerIndex = r.nextInt(CenterController.playerNumber);		
	}
	
	public void shufflingCard()
	{
		orgCard.shufflingCard();
	}
	
	public void dealing()
	{
		int order;
		if(this.clockWise == false)
			order = -1;
		else
			order = 1;

		for(int i = 0 ;i < orgCard.getAmountOfCard() ; i++)
		{
			int playerIndex = ((startPlayerIndex + i*order)) % CenterController.playerNumber + CenterController.playerNumber;
			playerIndex = playerIndex % CenterController.playerNumber;
			
			try 
			{
				player.get(playerIndex).addCard(orgCard.getCard(i));
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
	}
	
}
