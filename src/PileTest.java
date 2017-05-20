import static org.junit.Assert.*;

import org.junit.Test;


public class PileTest {

	@Test
	public void test() 
	{
		Pile pile = new Pile();

		pile.init();
		System.out.println(pile.showCards());
		
		System.out.println("shuffling");
		pile.shufflingCard();
		System.out.println(pile.showCards());
		
		pile.removeAll();
		System.out.println(pile.showCards());
	
	}
	

}
