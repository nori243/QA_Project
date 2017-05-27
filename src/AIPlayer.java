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
		
		return cardIndex;
	}

	private int chooseCard(int num) 
	{
		Random r = new Random();
			
		return r.nextInt(num);
	}
}
