import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//TODO 翻牌 自動 顯示 紅線
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
	
	private JLabel c = new JLabel(" ");
	
	private JButton end;
	
	private String userName ;

	private JPanel centerContent;
	private JPanel[] content = new JPanel[4];
	
	private JLabel player3;
	
	public CardButton[][] card = new CardButton[4][] ;
	
	private int cardIndex = -1;
	private boolean isChoose = false;
	
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
		setCenterPanel(controller.getPlayerIndexNext());

	//	AIControl();
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
		
		//
		this.add(container);
	}

	private void initPanel()
	{
		player_1 = new JPanel(new GridLayout(14,1));
		player_2 = new JPanel();
		player_3 = new JPanel(new GridLayout(14,1));
		user = new JPanel();
		
		
		center = new JPanel(new GridLayout(1,14));

		centerContent = new JPanel();
		centerContent.add(center);

		buttonSetting();
		
		content[0] = new JPanel(new BorderLayout());	
		content[1] = new JPanel(new GridLayout(1,1));
		content[2] = new JPanel();
		content[3] = new JPanel(new GridLayout(1,1));
		
		
		
		content[0].add(user,BorderLayout.CENTER);
		content[0].add(end, BorderLayout.EAST);
		JLabel userNameLabel = new JLabel(userName);
		userNameLabel.setHorizontalAlignment(JLabel.CENTER);
		content[0].add(userNameLabel, BorderLayout.NORTH);
		
		content[1].add(player_1);
		content[2].add(player_2);
		content[3].add(player_3);
		
		setAllPic();
		
		player3 = new JLabel("player3");
		//content[3].add(player3);
		
		//TODO 排版/字型/listener/observer
	}
	
	private void AIControl()
	{
		if(controller.getPlayerIndex() != 0)
		{
			System.out.println("player index " + controller.getPlayerIndex());
			
			cardIndex = controller.AIChooseCard();
				//	player3.setText("player index " + controller.getPlayerIndex());
			
			try 
			{	
				card[controller.getPlayerIndexNext()][cardIndex].setBorder(BorderFactory.createLineBorder(Color.RED, 1));

				validate();
				repaint();
				Thread.currentThread();
				Thread.sleep(500);	
				//TODO animate
				controller.turn(cardIndex);
				
			//	end.setEnabled(false);			
				
			
			} catch (InterruptedException e) 
			{

			}
			cardIndex = -1;
			
			
		}
		end.setEnabled(true);
	}
	
	private void buttonSetting()
	{
		end = new JButton("end turn");
		end.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{			
					
				
				if(controller.getPlayerIndex() == 0)
				{
					if(cardIndex != -1)
					{
						controller.turn(cardIndex);
						validate();
						repaint();
						//AIControl();
					}
				}				
				else
					AIControl();					
				
				isChoose = false;
			}});
	}
	
	
	
	private void fillCenter() 
	{
		for(int i = 0;i <= 14;i++)
		{
			center.add(new JPanel());
		}
		
	}
	
	private void setCenterPanel(int playerIndex)
	{
		center.removeAll();
		
		switch(playerIndex)
		{
			case 0:
				setPlayerPic(center,0);
				user.setVisible(false);
				
				content[0].setPreferredSize(new Dimension(150,120));
				
				break;
				
			case 1:
				System.out.println("eee");
				setPlayerPic(center,1);
				player_1.setVisible(false);
				content[1].setPreferredSize(new Dimension(120,150));
				
				break;
				
			case 2:
				setPlayerPic(center,2);
				player_2.setVisible(false);
				content[2].setPreferredSize(new Dimension(150,120));
				break;
				
			case 3:
				setPlayerPic(center,3);
				player_3.setVisible(false);
				content[3].setPreferredSize(new Dimension(120,150));
				break;
		} 
	}

	private void setAllPic()
	{
		
		setPlayerPic(user,0);
		
		setPlayerPic(player_1,1);
		setPlayerPic(player_2,2);
		setPlayerPic(player_3,3);
		
		user.setVisible(true);
		player_1.setVisible(true);
		player_2.setVisible(true);
		player_3.setVisible(true);
	}
	
	private void setPlayerPic(JPanel user,int playerIndex) 
	{
		setPic(user,controller.getPlayerPileInfo(playerIndex),playerIndex,playerIndex);
	}

	private void setPic(JPanel user,String[] fileName,int playerIndex,int index)
	{
		card[index] = new CardButton[fileName.length];
		ImageIcon img[] = new ImageIcon[fileName.length];
		
		for(int i=0; i<fileName.length; i++)
		{
			String name;
			
				name = fileName[i];
			
			img[i] = new ImageIcon(System.getProperty("user.dir")+"//cardPic//" + name + ".gif");
			card[index][i] = new CardButton(img[i],i);
			setCardListener(card[index][i]);
			
			if(playerIndex >= 0)
			{
				card[index][i].setPlayerIndex(playerIndex);
			}
			if(playerIndex != 0)
			{
				card[index][i].turnBack(true);
			}	
				
			user.add(card[index][i]);
			
		}
		
	}
	
	private void setCardListener(final CardButton card)
	{
		card.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(isChoose && (card.getCardIndex() == cardIndex) && card.getPlayerIndex() == controller.getPlayerIndexNext())
				{				
					card.setBorder(BorderFactory.createEmptyBorder());
					isChoose = false;
				}
				else if(!isChoose && GameController.playerIndexNow == 0 && card.getPlayerIndex() == controller.getPlayerIndexNext())
				{
					cardIndex = card.getCardIndex();
					card.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				//	card.turnBack(false);
					isChoose = true;
				}
			
			}

			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				
				if(!isChoose && GameController.playerIndexNow == 0 && card.getPlayerIndex() == controller.getPlayerIndexNext())
					card.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(!isChoose && GameController.playerIndexNow == 0&& card.getPlayerIndex() == controller.getPlayerIndexNext())
					card.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}});
	}
	
	private void removeAllPic()
	{
		user.removeAll();
		player_1.removeAll();
		player_2.removeAll();
		player_3.removeAll();
		center.removeAll();
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
	/*	else if(obs instanceof CardObservable)
		{
			CardObservable c = (CardObservable)obs;
			int index = c.getCardIndex();
			
			controller.turn(index);
		}*/
		else if(obs instanceof GameController)
		{
			
			removeAllPic();
			setAllPic();

			setCenterPanel(controller.getPlayerIndexNext());
	
			if(!controller.getState().equals(""))
			{
				end.setText(controller.getState());
				end.setEnabled(false);
			}
				
			
			this.validate();
			this.repaint();
		}
		
	}
}
