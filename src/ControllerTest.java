import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ControllerTest {

	static GameInitial gi;
	static ArrayList<Player> player;
	static GameController gc;
	
	@BeforeClass
	public static void initClass()
	{
		CenterController c = mock(CenterController.class);
		player = new ArrayList<Player>();
		
		player.add(new Player("user",new Pile()));		
		for(int i=1; i<CenterController.playerNumber;i++)
		{
			player.add(new AIPlayer("player"+i,new Pile()));
		}	
		
		gi = GameInitial.getInstance(player);			
		gc = GameController.getInstance(player);
				
		System.out.println("init, start player is "+GameController.playerIndexNow);
	}

	
	/*GameInitial*/
	@Test
	public void cardNumTest()
	{
		int i = GameInitial.startPlayerIndex;
		assertEquals(14,player.get(i).getAmountOfCard());
		i++;
		for(int j = 0; j < 3;j++,i++)
		{
			assertEquals(13,player.get((i)%4).getAmountOfCard());
		}	
		
	}
	
	

	/*GameController*/
	/*--------State Test----------*/

	@Test
	public void stateLoseTest()
	{		
		assertEquals(14,player.get(GameInitial.startPlayerIndex).getAmountOfCard());

		System.out.println("user" + " " + player.get(0).getAmountOfCard());
		for(int i = 1;i < 4 ;i++)
		{
			for(int j = 0;j < player.get(i).getAmountOfCard();)
			{
				Card card = player.get(i).getCard(j); 
				player.get(i).removeCard(card);
			}
			System.out.println("player" + i + " " + player.get(i).getAmountOfCard());
		}
		
		
		assertEquals("Game Over",gc.turn(-1));	
		
	}	
	
	@Test
	public void stateWinTest()
	{		
		assertEquals(14,player.get(GameInitial.startPlayerIndex).getAmountOfCard());
		
		for(int j = 0;j < player.get(0).getAmountOfCard();)
		{
			Card card = player.get(0).getCard(j); 
			player.get(0).removeCard(card);
		}
		
		System.out.println("user" + " " + player.get(0).getAmountOfCard());
		for(int i = 1;i < 4 ;i++)
		{			
			System.out.println("player" + i + " " + player.get(i).getAmountOfCard());
		}
		
		
		assertEquals("Win",gc.turn(-1));	
		
	}	
	
	@Test //user ³Ñ¤@±i
	public void stateNormalTest_1()
	{
		assertEquals(14,player.get(GameInitial.startPlayerIndex).getAmountOfCard());
		
		for(int j = 0;j < player.get(0).getAmountOfCard() - 1;)
		{
			Card card = player.get(0).getCard(j); 
			player.get(0).removeCard(card);
		}
		
		System.out.println("user" + " " + player.get(0).getAmountOfCard());
		
		for(int i = 1;i < 4 ;i++)
		{			
			System.out.println("player" + i + " " + player.get(i).getAmountOfCard());
		}
		
		
		assertEquals("",gc.turn(-1));	
	}
	
	@Test // user Player1-3 = 1 0 0 1
	public void stateNormalTest_2()
	{		
		assertEquals(14,player.get(GameInitial.startPlayerIndex).getAmountOfCard());

		for(int j = 0;j < player.get(0).getAmountOfCard() - 1;)
		{
			Card card = player.get(0).getCard(j); 
			player.get(0).removeCard(card);
		}
		System.out.println("user" + " " + player.get(0).getAmountOfCard());
		
		for(int i = 1;i < 3 ;i++)
		{
			for(int j = 0;j < player.get(i).getAmountOfCard();)
			{
				Card card = player.get(i).getCard(j); 
				player.get(i).removeCard(card);
			}
			System.out.println("player" + i + " " + player.get(i).getAmountOfCard());
		}
		
		for(int j = 0;j < player.get(3).getAmountOfCard()-1;)
		{
			Card card = player.get(3).getCard(j); 
			player.get(3).removeCard(card);
		}
		
		System.out.println("player" + 3 + " " + player.get(3).getAmountOfCard());

		
		assertEquals("",gc.turn(-1));	
		
	}	
	
	@Test //user Player1-3 = 1 1 1 0
	public void stateNormalTest_3()
	{		
		assertEquals(14,player.get(GameInitial.startPlayerIndex).getAmountOfCard());

		for(int j = 0;j < player.get(0).getAmountOfCard() - 1;)
		{
			Card card = player.get(0).getCard(j); 
			player.get(0).removeCard(card);
		}
		System.out.println("user" + " " + player.get(0).getAmountOfCard());
		
		for(int i = 1;i < 3 ;i++)
		{
			for(int j = 0;j < player.get(i).getAmountOfCard()-1;)
			{
				Card card = player.get(i).getCard(j); 
				player.get(i).removeCard(card);
			}
			System.out.println("player" + i + " " + player.get(i).getAmountOfCard());
		}
		
		for(int j = 0;j < player.get(3).getAmountOfCard();)
		{
			Card card = player.get(3).getCard(j); 
			player.get(3).removeCard(card);
		}
		
		System.out.println("player" + 3 + " " + player.get(3).getAmountOfCard());

		
		assertEquals("",gc.turn(-1));	
		
	}	
	
	/*--------State Test End----------*/
	
	@Test
	public void removePairTest()
	{	
		assertEquals(14,player.get(GameInitial.startPlayerIndex).getAmountOfCard());
		System.out.println(player.get(GameController.playerIndexNow).getName() + " : \n" + player.get(GameController.playerIndexNow).showCard());
		assertTrue(player.get(GameController.playerIndexNow).hasPair());
		
		gc.removePair();
		
		System.out.println(player.get(GameController.playerIndexNow).getName() + " : \n" + player.get(GameController.playerIndexNow).showCard());
		System.out.println(player.get(GameController.playerIndexNow).getAmountOfCard());
		assertFalse(player.get(GameController.playerIndexNow).hasPair());
	}	

	@Test
	public void ChangePlayerTest()
	{
		int indexBefore = GameController.playerIndexNow;
		int indexAfter;
		
		if(GameInitial.clockWise == true)			
			indexAfter = (indexBefore + 1)% CenterController.playerNumber + CenterController.playerNumber;
		else
			indexAfter = (indexBefore - 1)% CenterController.playerNumber + CenterController.playerNumber;
		
		indexAfter = indexAfter % CenterController.playerNumber ;
		gc.changePlayer();			
		assertEquals(indexAfter,GameController.playerIndexNow);
		
	}
	
	@Test
	public void userTurnTest()
	{
		GameController.playerIndexNow = 0;
		Random r = new Random();
		
		int playerNextIndex = gc.getPlayerNextIndex();
		int cardIndex = r.nextInt(player.get(playerNextIndex).getAmountOfCard());
		boolean isPair = false;
		Card card = player.get(playerNextIndex).getCard(cardIndex);
		
		if(player.get(0).getPairIndex(card) != -1)
			isPair = true;
			
		gc.turn(cardIndex);
		
		if(!isPair)
		{
			assertTrue(player.get(0).getCardIndex(card) != -1);		
		}		
		assertTrue(player.get(playerNextIndex).getCardIndex(card) == -1);
	}
	
	@After
	public void funEnd()
	{		
		gi.initGame();
	}
	
	@AfterClass
	public static void end()
	{
		gi = null;
		gc = null;
	}
}
