import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame extends JFrame
{
	private String title = "Joker";
	private static final int width = 1200;
	private static final int height = 700;
	private CenterController controller = CenterController.getInstance();
	
	private MenuBar menu;
	
	private JPanel container;
	private JPanel center;
	private JPanel player_1;
	private JPanel player_2;
	private JPanel player_3;
	private JPanel user;

	
	public GameFrame()
	{
		this.setTitle(title);
		this.setSize(width, height);
		menu = new MenuBar(height/25,width);
		this.setJMenuBar(menu);
		
		this.setMainLayout();
		this.setResizable(false);
		//controller.DoSomething()
	}
	
	
	private void setMainLayout()
	{
		container = new JPanel();
		container.setSize(width-(width/25), height);
		
		initPanel();
		
		container.setLayout(new BorderLayout());
		container.add(player_1, BorderLayout.WEST);
		container.add(player_2, BorderLayout.NORTH);
		container.add(player_3, BorderLayout.EAST);
		container.add(user, BorderLayout.SOUTH);
		container.add(center, BorderLayout.CENTER);
		
		this.add(container);
	}

	private void initPanel()
	{
		player_1 = new JPanel(new GridLayout(14,1));
		player_2 = new JPanel();
		player_3 = new JPanel(new GridLayout(14,1));
		user = new JPanel();
		center = new JPanel();


		setPlayerPic(user,0);
		setPlayerPic(player_1,1);
		setPlayerPic(player_2,2);
		setPlayerPic(player_3,3);
		
		//TODO ±Æª©/¦r«¬/listener/observer
	}

	private void setPlayerPic(JPanel user,int playerIndex) 
	{
		setPic(user,controller.getPlayerPileInfo(playerIndex),playerIndex);
	}

	private void setPic(JPanel user,String[] fileName,int playerIndex)
	{
		CardButton[] container = new CardButton[fileName.length];
		ImageIcon img[] = new ImageIcon[fileName.length];
		
		for(int i=0; i<fileName.length; i++)
		{
			String name;
			if(user == this.user)
				name = fileName[i];
			else
				name = "back";
			
			img[i] = new ImageIcon(System.getProperty("user.dir")+"//cardPic//" + name + ".gif");
			System.out.println(fileName[i]);
			container[i] = new CardButton(img[i],i);
			if(playerIndex >= 0)
				container[i].setPlayerIndex(playerIndex);
			user.add(container[i]);
		}
		
	}
}
