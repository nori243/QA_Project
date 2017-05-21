import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements Observer
{
	private String title = "Joker";
	private static final int width = 1200;
	private static final int height = 700;
	private CenterController controller ;
	
	private MenuBar menu;
	
	private JPanel container;
	private JPanel center;
	private JPanel player_1;
	private JPanel player_2;
	private JPanel player_3;
	private JPanel user;
	
	private String userName ;

	private JPanel centerContent;
	private JPanel[] content = new JPanel[4];
	
	public GameFrame(String name)
	{
		this.setTitle(title);
		this.setSize(width, height);
		menu = new MenuBar(height/25,width);
		this.setJMenuBar(menu);
		
		this.userName = name;
		
		this.setResizable(false);
		//controller.DoSomething()
	}
		
	public void initSetting()
	{
		controller = CenterController.getInstance();
		this.setMainLayout();		
	}
	
	private void setMainLayout()
	{
		container = new JPanel();
		container.setSize(width-(width/25), height);
		initPanel();
		
		container.setLayout(new BorderLayout());

		container.add(content[0], BorderLayout.SOUTH);
		container.add(content[1], BorderLayout.WEST);
		container.add(content[2], BorderLayout.NORTH);
		container.add(content[3], BorderLayout.EAST);
		container.add(centerContent, BorderLayout.CENTER);
		
		this.add(container);
	}

	private void initPanel()
	{
		player_1 = new JPanel(new GridLayout(14,1));
		player_2 = new JPanel();
		player_3 = new JPanel(new GridLayout(14,1));
		user = new JPanel();
		
		
		center = new JPanel(new GridLayout(2,14));
		fillCenter();
		
		centerContent = new JPanel();
		centerContent.add(center);
		
		for(int i=0;i<4;i++)
		{			
			content[i] = new JPanel();
			
		}
		content[1] = new JPanel(new GridLayout(1,14));
		content[3] = new JPanel(new GridLayout(1,14));
		
		content[0].add(user);
		content[1].add(player_1);
		content[2].add(player_2);
		content[3].add(player_3);
		
		setAllPic();
		
		//TODO ±Æª©/¦r«¬/listener/observer
	}
	
	private void fillCenter() 
	{
		for(int i = 0;i <= 14;i++)
		{
			center.add(new JPanel());
		}
		
	}

	private void setAllPic()
	{
		setPlayerPic(user,0);
		setPlayerPic(player_1,1);
		setPlayerPic(player_2,2);
		setPlayerPic(player_3,3);
		
		player_1.removeAll();
		setPlayerPic(center,1);
		
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
	
	private void removeAllPic()
	{
		user.removeAll();
		player_1.removeAll();
		player_2.removeAll();
		player_3.removeAll();
	}

	@Override
	public void update(Observable obs, Object arg) 
	{
		if(obs instanceof RestartObservable)
		{
			GameFrame.this.dispose();
			StartFrame start = new StartFrame();
			start.setVisible(true);			
		}
		else if(obs instanceof CardObservable)
		{
			CardObservable c = (CardObservable)obs;
			int index = c.getCardIndex();
			
			controller.turn(index);
		}
		else if(obs instanceof GameController)
		{
			removeAllPic();
			setAllPic();
			int index = GameController.playerIndexNow;
			//if()
			//refrash
			this.validate();
			this.repaint();
		}
		
	}
}
