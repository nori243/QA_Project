import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class ControllerTest {

	static GameInitial gi;
	static ArrayList<Player> player;
	static CenterController controller ;
	static GameController gc;
	
	@BeforeClass
	public static void init()
	{		
		controller = CenterController.getInstance();
		player = controller.player;
		gi = GameInitial.getInstance(player);
		gc = GameController.getInstance(player);
		
		System.out.println("init, start player is "+GameController.playerIndexNow);
		
	//	for(int i=0;i<CenterController.playerNumber;i++)
	//	System.out.println(gi.player.get(i).getName() + " : \n" + gi.player.get(i).showCard());
		
		assertEquals(14,gi.player.get(GameInitial.startPlayerIndex).getAmountOfCard());

	}
	
	/*CenterController*/
	@Ignore
	@Test
	public void setNameTest()
	{
		String name = "May";
		controller.setName(name);
		assertEquals(name,gi.player.get(0).getName());
		assertEquals(name,controller.player.get(0).getName());
	}
	
	
	
	/*GameInitial*/


	/*GameController*/
	//@Ignore
	@Test
	public void removePairTest()
	{
		
		System.out.println(gi.player.get(GameController.playerIndexNow).getName() + " : \n" + gi.player.get(GameController.playerIndexNow).showCard());
		assertEquals(true,controller.player.get(gc.playerIndexNow).hasPair());
		
		gc.removePair();
		System.out.println(gi.player.get(GameController.playerIndexNow).getName() + " : \n" + player.get(GameController.playerIndexNow).showCard());
		System.out.println(player.get(GameController.playerIndexNow).getAmountOfCard());
		assertEquals(false,controller.player.get(gc.playerIndexNow).hasPair());
	}
	

	@AfterClass
	public static void end()
	{
		gi = null;
		gc = null;
		controller = null;
	}
}
