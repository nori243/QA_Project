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
	private static final int width = 1100;
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
		
		/*ImageIcon[] img = new ImageIcon[2];
		img[0] = new ImageIcon(System.getProperty("user.dir")+"//cardPic//back.gif");
		img[1] = new ImageIcon(System.getProperty("user.dir")+"//cardPic//back.gif");
		user = new JLabel(img[0]);
		
		c.add(user,0);*/
	//	c.add(user,1);
		/*ImageIcon img[] = new ImageIcon[2];
		img[0] = new ImageIcon(System.getProperty("user.dir")+"//cardPic//back.gif");

		img[1] = new ImageIcon(System.getProperty("user.dir")+"//cardPic//back.gif");
		
		user[] = new JLabel(img[]);*/
		
		

		setPlayerPic(user,0);
	
		//setLeftLayout()
		setPlayerPic(player_1,1);
		setPlayerPic(player_2,2);
		setPlayerPic(player_3,3);
		
		//TODO ±Æª©/¦r«¬
	}
	
	private void setLeftLayout()
	{
		
	}
	
	private void setPlayerPic(JPanel user,int playerIndex) 
	{
		setPic(user,controller.getPlayerPileInfo(playerIndex));
	}

	private void setPic(JPanel user,String[] fileName)
	{
		/*ImageIcon img = new ImageIcon(System.getProperty("user.dir")+"//cardPic//" + fileName[0]+".gif");
		JLabel j = new JLabel(img);
		user.add(j);*/
		JLabel[] container = new JLabel[fileName.length];
		ImageIcon img[] = new ImageIcon[fileName.length];
		
		for(int i=0; i<fileName.length; i++)
		{
			img[i] = new ImageIcon(System.getProperty("user.dir")+"//cardPic//" + "back" + ".gif");
			System.out.println(fileName[i]);
			container[i] = new JLabel(img[i]);
			user.add(container[i]);
		}
		
	}
}
