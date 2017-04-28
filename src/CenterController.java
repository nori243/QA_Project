import java.util.ArrayList;


public class CenterController 
{
	public static int playerNumber = 4;
	ArrayList<Player> player ;
	private String userName = "user";
	private static CenterController uniqueController;
	
	GameInitial initial ;
	GameController controller ;
	
	private CenterController()
	{
		playerInitial();
		initial = GameInitial.getInstance(player);
		controller = GameController.getInstance(player);		
	}
	
	public static CenterController getInstance()
	{
		if(uniqueController == null)
		{
			uniqueController = new CenterController();
		}
		
		return uniqueController;
	}
	
	public void playerInitial()
	{
		player = new ArrayList<Player>();
		
		player.add(new Player(userName,new Pile()));		
		for(int i=1; i<CenterController.playerNumber;i++)
		{
			player.add(new Player("player"+i,new Pile()));
		}
	}

	public void setName(String name)
	{
		userName = name;
		player.get(0).setName(name);
	}
	
	public int getPlayerNowIndex()
	{
		return controller.playerIndexNow;
	}
	
	public String[] getPileInfo(Pile pile)
	{
		String[] cardInfo = new String[pile.getAmountOfCard()];

		cardInfo = pile.showCards().split("\n");		
		
		return cardInfo;
	}
	
	public String[] getPlayerPileInfo(int playerIndex)
	{
		Player p = this.player.get(playerIndex);
		String[] cardInfo= getPileInfo(p.getPile());
				
		return cardInfo;
	}
}
