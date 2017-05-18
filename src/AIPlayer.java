import java.util.Random;


public class AIPlayer extends Player
{

	public AIPlayer(String name, Pile p) 
	{
		super(name, p);
	}

	public int autoPlay(int cardNum)
	{
		int cardIndex = chooseCard(cardNum);
		
		try 
		{
			Thread.currentThread();
			Thread.sleep(1000);
			
			return cardIndex;
		} catch (InterruptedException e) 
		{
			return -1;
		}
	}

	private int chooseCard(int num) 
	{
		Random r = new Random();
			
		return r.nextInt(num);
	}
}
