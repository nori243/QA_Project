
public class MainTest 
{

	public static void main(String[] args) 
	{		
		Pile pile = new Pile();
		
		Player player = new Player("May",pile);
		
		System.out.println(player.showCard());
		System.out.println(player.checkJoker());
		
		player.removeCard(new Card(0,0));
		System.out.println(player.checkJoker());
		System.out.println(player.showCard());
		
		player.init("may", new Pile());
		System.out.println(player.showCard());
		
		
	}

}
