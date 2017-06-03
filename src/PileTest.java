import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PileTest {
	Pile pile;
	
	/*-----init------*/
	@Before
	public void init()
	{
		pile = new Pile();
	}
	
	
	/*----getAmountOfCardTest---*/
	@Test
	public void getAmountOfCardTest()
	{
		Card card = new Card(1,1);
		assertEquals(0,pile.getAmountOfCard());
		pile.addCard(card);
		assertEquals(1,pile.getAmountOfCard());
	}
		
	/*---addCard & getCatd Test----*/
	
	@Test //add one card
	public void addCardTest_1()
	{
		Card card = new Card(1,1);
		assertEquals(0,pile.getAmountOfCard());
		pile.addCard(card);
		assertEquals(1,pile.getAmountOfCard());
		
		try 
		{
			assertTrue(pile.getCard(0).IsSuitSame(card));
			assertEquals(card.getNum(),pile.getCard(0).getNum());
		} 
		catch (Exception e) 
		{
			fail();
		}
		
	}
	
	@Test(expected = Exception.class) //add one card and get the card index = 2
	public void addCardTest_2() throws Exception
	{
		Card card = new Card(1,1);
		assertEquals(0,pile.getAmountOfCard());
		pile.addCard(card);
		assertEquals(1,pile.getAmountOfCard());
	
		pile.getCard(1);		
	}
	
	/*-----getCardIndexTest------*/
	
	@Test
	public void getCardIndexTest()
	{
		Card card = new Card(1,1);
		assertEquals(-1,pile.getCardIndex(card));
		pile.addCard(card);
		assertEquals(0,pile.getCardIndex(card));
		
	}
	
	/*-----hasPairTest------*/
	
	@Test // no pair
	public void hasPairTest_1()
	{
		Card card_1 = new Card(1,1);
		
		pile.addCard(card_1);
		assertFalse(pile.hasPair());
	}
	
	@Test // one pair
	public void hasPairTest_2()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);
		
		assertTrue(pile.hasPair());
	}
	
	@Test //one pair + one card same num
	public void hasPairTest_3()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		Card card_3 = new Card(3,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);
		
		assertTrue(pile.hasPair());
	}
	
	@Test //one pair + one card
	public void hasPairTest_4()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		Card card_3 = new Card(2,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);
		
		assertTrue(pile.hasPair());
	}
	
	@Test //two pair 
	public void hasPairTest_5()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		Card card_3 = new Card(2,2);
		Card card_4 = new Card(3,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		
		pile.addCard(card_4);
		
		assertTrue(pile.hasPair());
	}
	
	@Test //two same card
	public void hasPairTest_6()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(1,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);	

		assertFalse(pile.hasPair());		
	}
	
	/*-----getPairIndexTest------*/
	
	
	
	@Test // no pair
	public void getPairIndexTest_1()
	{
		Card card_1 = new Card(1,1);
		
		pile.addCard(card_1);
		assertEquals(-1,pile.getPairIndex(card_1));
	}
	
	@Test // one pair
	public void getPairIndexTest_2()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);

		assertEquals(1,pile.getPairIndex(card_1));
		assertEquals(0,pile.getPairIndex(card_2));
	}
	
	@Test //one pair + one card same num
	public void getPairIndexTest_3()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		Card card_3 = new Card(3,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);

		assertEquals(1,pile.getPairIndex(card_1));
		assertEquals(0,pile.getPairIndex(card_2));
		assertEquals(0,pile.getPairIndex(card_3));
	}
	
	@Test //one pair + one card
	public void getPairIndexTest_4()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		Card card_3 = new Card(2,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		

		assertEquals(1,pile.getPairIndex(card_1));
		assertEquals(0,pile.getPairIndex(card_2));
		assertEquals(-1,pile.getPairIndex(card_3));
	}
	
	@Test //one pair (index : 0 2) + one card(index : 1) 
	public void getPairIndexTest_5()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		

		assertEquals(2,pile.getPairIndex(card_1));
		assertEquals(-1,pile.getPairIndex(card_2));
		assertEquals(0,pile.getPairIndex(card_3));
	}
	
	@Test //two pair (index 0 2 / 1 3)
	public void getPairIndexTest_6()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		Card card_4 = new Card(3,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		
		pile.addCard(card_4);

		assertEquals(2,pile.getPairIndex(card_1));
		assertEquals(3,pile.getPairIndex(card_2));
		assertEquals(0,pile.getPairIndex(card_3));
		assertEquals(1,pile.getPairIndex(card_4));
	}
	
	@Test //two same card
	public void getPairIndexTest_7()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(1,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);	

		assertEquals(-1,pile.getPairIndex(card_1));	
		assertEquals(-1,pile.getPairIndex(card_2));	
	}
	
	@Test // empty
	public void getPairIndexTest_8()
	{
		Card card_1 = new Card(1,1);
		
		assertEquals(-1,pile.getPairIndex(card_1));
	}
	
	
	/*-----removePairTest------*/
	
	@Test //no pair
	public void removePairTest_1()
	{
		Card card_1 = new Card(1,1);
		
		pile.addCard(card_1);
		
		assertFalse(pile.removePair(card_1));
	}
	
	@Test //one pair + one card same num
	public void removePairTest_2()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,1);
		Card card_3 = new Card(3,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);

		assertTrue(pile.removePair(card_1));
		assertEquals(-1,pile.getCardIndex(card_1));
		assertEquals(-1,pile.getCardIndex(card_2));
		
		assertEquals(-1,pile.getPairIndex(card_3));
		assertFalse(pile.removePair(card_3));
		
		pile.addCard(card_1);				
		assertEquals(1,pile.getCardIndex(card_1));
		assertTrue(pile.removePair(card_3));
		assertEquals(-1,pile.getCardIndex(card_1));
		assertEquals(-1,pile.getCardIndex(card_3));
	}
	
	@Test //one pair (index : 0 2) + one card(index : 1) 
	public void removePairTest_3()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		

		assertTrue(pile.removePair(card_1));
		assertEquals(-1,pile.getCardIndex(card_1));
		assertEquals(-1,pile.getCardIndex(card_3));
		assertFalse(pile.removePair(card_2));
		
	}
	
	@Test //two pair(index 0 2 / 1 3)
	public void removePairTest_4()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		Card card_4 = new Card(3,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		
		pile.addCard(card_4);

		assertTrue(pile.removePair(card_1));
		assertEquals(-1,pile.getCardIndex(card_1));
		assertEquals(-1,pile.getCardIndex(card_3));
		
		assertTrue(pile.removePair(card_2));
		assertEquals(-1,pile.getCardIndex(card_2));
		assertEquals(-1,pile.getCardIndex(card_4));
	}
	
	/*----removeCardTest-----*/
	@Test //no card
	public void removeCardTest_1()
	{
		Card card = new Card(1,1);
		assertFalse(pile.removeCard(card));
	}
	
	@Test //one card
	public void removeCardTest_2()
	{
		Card card = new Card(1,1);
		
		pile.addCard(card);
		
		assertTrue(pile.removeCard(card));
		assertEquals(-1,pile.getCardIndex(card));	
	}
	
	@Test //more than one card , remove first card
	public void removeCardTest_3()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		Card card_4 = new Card(3,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		
		pile.addCard(card_4);
		
		assertTrue(pile.removeCard(card_1));
		assertEquals(-1,pile.getCardIndex(card_1));
		
	}
	
	@Test //more than one card , remove last card
	public void removeCardTest_4()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		Card card_4 = new Card(3,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		
		pile.addCard(card_4);

		assertTrue(pile.removeCard(card_4));
		assertEquals(-1,pile.getCardIndex(card_4));
	}
	
	@Test //more than one card , remove third card (normal case)
	public void removeCardTest_5()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		Card card_4 = new Card(3,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		
		pile.addCard(card_4);
		
		assertTrue(pile.removeCard(card_3));
		assertEquals(-1,pile.getCardIndex(card_3));
	}
	
	@Test //more than one card , remove two cards
	public void removeCardTest_6()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		Card card_4 = new Card(3,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		
		pile.addCard(card_4);
		
		assertTrue(pile.removeCard(card_2));
		assertTrue(pile.removeCard(card_3));
		
		assertEquals(-1,pile.getCardIndex(card_2));
		assertEquals(-1,pile.getCardIndex(card_3));
	}
	
	/*----removeAllTest------*/
	
	@Test //no card
	public void removeAllTest_1()
	{
		assertEquals(0,pile.getAmountOfCard());
		pile.removeAll();
		assertEquals(0,pile.getAmountOfCard());
	}
	
	@Test //one card
	public void removeAllTest_2()
	{
		Card card = new Card(1,1);
		pile.addCard(card);
		
		assertEquals(1,pile.getAmountOfCard());
		pile.removeAll();
		assertEquals(0,pile.getAmountOfCard());
	}
	
	@Test // more than one (normal case)
	public void removeAllTest_3()
	{
		Card card_1 = new Card(1,1);
		Card card_2 = new Card(2,2);
		Card card_3 = new Card(2,1);
		Card card_4 = new Card(3,2);
		
		pile.addCard(card_1);		
		pile.addCard(card_2);		
		pile.addCard(card_3);		
		pile.addCard(card_4);
		
		assertEquals(4,pile.getAmountOfCard());
		pile.removeAll();
		assertEquals(0,pile.getAmountOfCard());
	}

	
	/*-----------*/
	@Test
	public void shufflingCardTest()
	{
		
		pile.init();
		System.out.println(pile.showCards());
		
		System.out.println("shuffling");
		pile.shufflingCard();
		System.out.println(pile.showCards());
	}
	
	/*--Show Test--*/
	@Test
	public void  showCardTest()
	{
		Card card_1 = new Card(1,1);
		
		assertEquals("empty",pile.showCards());		
		pile.addCard(card_1);				
		assertEquals("spades 1\n",pile.showCards());		
	}
	
}
