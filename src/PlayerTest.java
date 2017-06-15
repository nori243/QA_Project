import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PlayerTest 
{

	Player user ;
	AIPlayer ai;
	Pile p ;
	
	@Before
	public void playreInit()
	{
		p = new Pile();
		
		user = new Player("user",p);
		ai = new AIPlayer("ai",p);

	}

	@Test
	public void testInit() 
	{
		String name = "ABC";
		Pile p = new Pile();
		
		assertEquals("user",user.getName());
		assertEquals(this.p,user.getPile());
		
		user.init(name, p);
		assertEquals(name,user.getName());
		assertEquals(p,user.getPile());
	}

	@Test
	public void testSetPile() 
	{
		Pile p = new Pile();
		assertEquals(this.p,user.getPile());

		user.setPile(p);
		assertEquals(p,user.getPile());
	}
	
	@Test
	public void testGetPile()
	{
		assertEquals(this.p,user.getPile());
	}

	@Test
	public void testSetAndGetName() 
	{
		String name = "New Name";
		assertEquals("user",user.getName());
		user.setName(name);
		assertEquals(name,user.getName());
		
	}

	@Test
	public void testGetAmountOfCard() 
	{
		assertEquals(0,p.getAmountOfCard());
		p.addCard(new Card(1,1));
		user.setPile(p);
		assertEquals(1,p.getAmountOfCard());
	}

	@Test
	public void testAddCard() 
	{
		assertEquals(0,p.getAmountOfCard());
		p.addCard(new Card(1,1));
		assertEquals(1,p.getAmountOfCard());
	}

	@Test
	public void testRemoveCard() 
	{
		Card card = new Card(1,1);
		p.addCard(card);
		user.setPile(p);
		assertEquals(1,user.getAmountOfCard());
		user.removeCard(card);
		assertEquals(0,user.getAmountOfCard());
	}

	@Test
	public void testRemovePair() 
	{
		Card card = new Card(1,1);
		Card card_2 = new Card(2,1);
		p.addCard(card);
		p.addCard(card_2);
		
		assertEquals(2,user.getAmountOfCard());
		user.removePair(card);
		assertEquals(0,user.getAmountOfCard());
	}

	@Test
	public void testGetPairIndex() 
	{
		Card card = new Card(1,1);
		Card card_2 = new Card(2,1);
		p.addCard(card);
		p.addCard(card_2);
		
		assertEquals(2,user.getAmountOfCard());
		assertEquals(1,user.getPairIndex(card));
	}

	@Test
	public void testGetCard() 
	{
		Card card = new Card(1,1);
		assertEquals(null,user.getCard(0));
		p.addCard(card);
		
		assertEquals(card,user.getCard(0));
	}

	@Test
	public void testGetCardIndex() 
	{
		Card card = new Card(1,1);
		p.addCard(card);
		
		assertEquals(0,user.getCardIndex(card));
	}




	@Test
	public void testHasPair() 
	{
		Card card = new Card(1,1);
		Card card_2 = new Card(2,1);
		p.removeAll();
		user.removeAllCard();
		assertFalse(user.hasPair());		
		
		p.addCard(card);
		p.addCard(card_2);	
		
		user.setPile(p);
		assertTrue(user.hasPair());
		
	}

	@Test
	public void testHasJoker() 
	{
		Card card = new Card(0,1);
		
		assertFalse(user.hasJoker());
		p.addCard(card);
		
		assertTrue(user.hasJoker());
	}

	@Test
	public void testShowCard() 
	{		
		user.getPile().init();
		
		System.out.println("---user card---\n" + user.showCard());
		
	}

	@Test
	public void testRemoveAllCard() 
	{
		user.getPile().init();
		assertEquals(53,user.getAmountOfCard());
		user.removeAllCard();
		assertEquals(0,user.getAmountOfCard());
	}
	
	@Test 
	public void testAutoPlay()
	{
		int cardNum = 5;
		assertTrue( cardNum > ai.autoPlay(cardNum));
	}

}
