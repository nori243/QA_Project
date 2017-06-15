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
		StartFrame s = mock(StartFrame.class);
		
		
		StartFrame.gameFrame = new GameFrame("user");
				
		StartFrame.gameFrame.initSetting();
		
	}
	
	@Before
	public void before()
	{
		player = new ArrayList<Player>();
		
		player.add(new Player("user",new Pile()));		
		for(int i=1; i<CenterController.playerNumber;i++)
		{
			player.add(new AIPlayer("player"+i,new Pile()));
		}	
		
		gi = new GameInitial(player);	
		gi.initGame();
		gc = new GameController(player);
	//	System.out.println("init, start player is "+GameController.playerIndexNow);
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
			//System.out.println("player" + i + " " + player.get(i).getAmountOfCard());
		}
		
		gc.turn(-1);
		assertEquals("Game Over",gc.getState());
			
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
		
		gc.turn(-1);
		assertEquals("Win",gc.getState());	
		
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
		
		assertEquals("",gc.getState());	

		int indexBefore;
		if(GameInitial.clockWise == true)			
			indexBefore = 3;
		else
			indexBefore = 1;
		
		GameController.playerIndexNow = indexBefore;
		gc.turn(0);
		assertEquals("Win",gc.getState());

		
	} 
	
	/*--------State Test End----------*/

	@Test
	public void removePairTest_1()
	{	
		assertEquals(14,player.get(GameInitial.startPlayerIndex).getAmountOfCard());
		assertTrue(player.get(GameController.playerIndexNow).hasPair());
		
		gc.removePair(GameController.playerIndexNow);
		
		assertFalse(player.get(GameController.playerIndexNow).hasPair());
	}	
	
	@Test //no pair
	public void removePairTest_2()
	{	
		for(int i = 0;i < 4 ;i++)
		{
			for(int j = 0;j < player.get(i).getAmountOfCard()-1;)
			{
				Card card = player.get(i).getCard(j); 
				player.get(i).removeCard(card);
			}
			assertEquals(1,player.get(i).getAmountOfCard());
		}	
		
		for(int i = 0; i < 4 ; i++)
		{
			gc.removePair(i);
			assertEquals(1,player.get(i).getAmountOfCard());	
		}		
	} 
	
	@Test //is empty
	public void removePairTest_3()
	{	
		for(int i = 0;i < 4 ;i++)
		{
			for(int j = 0;j < player.get(i).getAmountOfCard();)
			{
				Card card = player.get(i).getCard(j); 
				player.get(i).removeCard(card);
			}
			assertEquals(0,player.get(i).getAmountOfCard());
		}	
		
		for(int i = 0; i < 4 ; i++)
		{
			gc.removePair(i);
			assertEquals(0,player.get(i).getAmountOfCard());	
		}		
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
	
	@Test
	public void AIChooseTest()
	{
		GameController.playerIndexNow = 1;		
		assertTrue(gc.AIChooseCard() < player.get(gc.getPlayerNextIndex()).getAmountOfCard());
		assertTrue(gc.AIChooseCard() >= 0);
	} 
	
	@Test //user
	public void getChooseIndexTest_1()
	{
		GameController.playerIndexNow = 0;
		gc.turn(1);
		assertEquals(1,gc.getChooseIndex());
	}
	
	@Test //AI
	public void getChooseIndexTest_2()
	{
		GameController.playerIndexNow = 1;
		int num = gc.AIChooseCard();
		gc.turn(num);
		assertEquals(num,gc.getChooseIndex());
	}
	
	@AfterClass
	public static void end()
	{
		gi = null;
		gc = null;
	}
}
