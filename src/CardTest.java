import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class CardTest 
{
	/*---initTest---*/
	 
	@Test //spades
	public void initTest_1()
	{
		Card card = new Card(1,1);
		assertEquals(1,card.getNum());		
		assertEquals("spades",card.getSuit());		
		
		card = new Card(1,5);
		assertEquals(5,card.getNum());		
		assertEquals("spades",card.getSuit());
		
		card = new Card(1,13);
		assertEquals(13,card.getNum());		
		assertEquals("spades",card.getSuit());
		
		card = new Card(1,14);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		card = new Card(1,-1);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
	}
	@Test //hearts
	public void initTest_2()
	{
		Card card = new Card(2,1);
		assertEquals(1,card.getNum());		
		assertEquals("hearts",card.getSuit());		
		
		card = new Card(2,5);
		assertEquals(5,card.getNum());		
		assertEquals("hearts",card.getSuit());
		
		card = new Card(2,13);
		assertEquals(13,card.getNum());		
		assertEquals("hearts",card.getSuit());
		
		card = new Card(2,14);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		card = new Card(2,-1);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
	}
	@Test //diamonds
	public void initTest_3()
	{
		Card card = new Card(3,1);
		assertEquals(1,card.getNum());		
		assertEquals("diamonds",card.getSuit());		
		
		card = new Card(3,5);
		assertEquals(5,card.getNum());		
		assertEquals("diamonds",card.getSuit());
		
		card = new Card(3,13);
		assertEquals(13,card.getNum());		
		assertEquals("diamonds",card.getSuit());
		
		card = new Card(3,14);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		card = new Card(3,-1);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
	}
	@Test //clubs 5-normal 
	public void initTest_4()
	{
		Card card = new Card(4,1);
		assertEquals(1,card.getNum());		
		assertEquals("clubs",card.getSuit());		
		
		card = new Card(4,5);
		assertEquals(5,card.getNum());		
		assertEquals("clubs",card.getSuit());
		
		card = new Card(4,13);
		assertEquals(13,card.getNum());		
		assertEquals("clubs",card.getSuit());
		
		card = new Card(4,14);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		card = new Card(4,-1);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
	}
	@Test //joker
	public void initTest_5()
	{
		Card card = new Card(0,0);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		card = new Card(0,13);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		card = new Card(0,14);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());		
		
		card = new Card(0,-1);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
	}
	@Test //else
	public void initTest_6()
	{			
		/*---suit out of range---*/
		Card card = new Card(5,1);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		card = new Card(-1,1);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		/*---num out of range---*/
		card = new Card(1,14);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());
		
		card = new Card(1,-1);
		assertEquals(0,card.getNum());		
		assertEquals("joker",card.getSuit());

	}
	
	/*---isSuitSameTest---*/
	
	@Test //spades
	public void isSuitSameTest_1() 
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(1,2);
		
		assertTrue(card_1.IsSuitSame(card_2));
	}
	
	@Test //hearts
	public void isSuitSameTest_2() 
	{
		Card card_1 = new Card(2,1);
		Card card_2 = new Card(2,2);
		
		assertTrue(card_1.IsSuitSame(card_2));
	}
	
	@Test //diamonds
	public void isSuitSameTest_3() 
	{
		Card card_1 = new Card(3,1);
		Card card_2 = new Card(3,2);
		
		assertTrue(card_1.IsSuitSame(card_2));
	}
	
	@Test //clubs
	public void isSuitSameTest_4() 
	{
		Card card_1 = new Card(4,1);
		Card card_2 = new Card(4,2);
		
		assertTrue(card_1.IsSuitSame(card_2));
	}
	
	@Test //joker
	public void isSuitSameTest_5() 
	{
		Card card_1 = new Card(0,1);
		Card card_2 = new Card(0,2);
		
		assertEquals(0,card_1.getNum());
		assertEquals(0,card_2.getNum());
		
		assertTrue(card_1.IsSuitSame(card_2));
	}
	
	@Test //diff
	public void isSuitSameTest_6() 
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
				
		assertFalse(card_1.IsSuitSame(card_2));
	}
	
	/*---isPairTest---*/
		
	@Test // diff num , same suit
	public void isPairTest_1()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(1,2);
		
		assertFalse(card_1.IsPair(card_2));
	}
	
	@Test // same num , same suit
	public void isPairTest_2()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(1,1);
		
		assertFalse(card_1.IsPair(card_2));
	}
	
	@Test // same num , diff suit
	public void isPairTest_3()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		
		assertTrue(card_1.IsPair(card_2));
	}
	
	@Test // diff num , diff suit
	public void isPairTest_4()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		
		assertFalse(card_1.IsPair(card_2));
	}
	
	
	/*---getSuitTest---*/
	
	@Test //spades
	public void getSuitTest_1() 
	{
		Card card = new Card(1,1);
		assertEquals("spades",card.getSuit());
		
		card = new Card(1,5);
		assertEquals("spades",card.getSuit());
		
		card = new Card(1,13);
		assertEquals("spades",card.getSuit());
	}
	
	@Test //hearts
	public void getSuitTest_2() 
	{
		Card card = new Card(2,1);
		assertEquals("hearts",card.getSuit());
		
		card = new Card(2,5);
		assertEquals("hearts",card.getSuit());
		
		card = new Card(2,13);
		assertEquals("hearts",card.getSuit());
	}
	
	@Test //diamonds
	public void getSuitTest_3() 
	{
		Card card = new Card(3,1);
		assertEquals("diamonds",card.getSuit());
		
		card = new Card(3,5);
		assertEquals("diamonds",card.getSuit());
		
		card = new Card(3,13);
		assertEquals("diamonds",card.getSuit());
	}
	
	@Test //clubs
	public void getSuitTest_4() 
	{
		Card card = new Card(4,1);
		assertEquals("clubs",card.getSuit());
		
		card = new Card(4,5);
		assertEquals("clubs",card.getSuit());
		
		card = new Card(4,13);
		assertEquals("clubs",card.getSuit());
	}
	
	@Test //joker
	public void getSuitTest_5() 
	{
		Card card = new Card(0,0);
		assertEquals("joker",card.getSuit());
	}

	/*---getNumTest---*/

	@Test //spades
	public void getNumTest_1() 
	{
		Card card = new Card(1,1);
		assertEquals(1,card.getNum());
		
		card = new Card(1,5);
		assertEquals(5,card.getNum());
		
		card = new Card(1,13);
		assertEquals(13,card.getNum());
	}
	
	@Test //hearts
	public void getNumTest_2() 
	{
		Card card = new Card(2,1);
		assertEquals(1,card.getNum());
		
		card = new Card(2,5);
		assertEquals(5,card.getNum());
		
		card = new Card(2,13);
		assertEquals(13,card.getNum());
	}
	
	@Test //diamonds
	public void getNumTest_3() 
	{
		Card card = new Card(3,1);
		assertEquals(1,card.getNum());
		
		card = new Card(3,5);
		assertEquals(5,card.getNum());
		
		card = new Card(3,13);
		assertEquals(13,card.getNum());
	}
	
	@Test //clubs
	public void getNumTest_4() 
	{
		Card card = new Card(4,1);
		assertEquals(1,card.getNum());
		
		card = new Card(4,5);
		assertEquals(5,card.getNum());
		
		card = new Card(4,13);
		assertEquals(13,card.getNum());
	}
	
	@Test //joker
	public void getNumTest_5() 
	{
		Card card = new Card(0,1);
		assertEquals(0,card.getNum());
		
		card = new Card(0,5);
		assertEquals(0,card.getNum());
		
		card = new Card(0,13);
		assertEquals(0,card.getNum());	
	}
}
