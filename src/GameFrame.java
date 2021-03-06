import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

//TODO 翻牌 自動 顯示 紅線 http://paletton.com/palette.php?uid=72U0-0kmjFlwZeAuav7eKRe34%2Bu
public class GameFrame extends JFrame implements Observer
{
	/*---Color---*/
	
	public static final Color BACKGROUND = new Color(64, 211, 103);
	public static final Color BACKGROUND_LIGHT = new Color(127, 235, 155);
	public static final Color TEXT = new Color(4, 50, 74);
	public static final Color WARN = new Color(248, 41, 14);
	public static final Color WHITE = new Color(230, 254, 236);
	public static final Color BLUE = new Color(65, 148, 192);
	
	/*------*/
	
	public static final int PLAYER_NUM = 4;
	
	private String title = "Joker";
	private static final int width = 1350;
	private static final int height = 700;
	public CenterController controller ;
	
	private MenuBar menu;
	
	private JPanel container;
	private JPanel center;
	private JPanel player_1;
	private JPanel player_2;
	private JPanel player_3;
	private JPanel user;
	private JPanel centerContent;
	private JPanel[] content = new JPanel[PLAYER_NUM];
	private JPanel cond ;
	
	/*--text--*/
	private FormatLabel gameStateText;
	private FormatLabel playerNowText;
	private FormatLabel nextPlayerText;
	private FormatLabel userCardNumText;	
	/*--value--*/
	private FormatLabel gameState;
	private FormatLabel playerNow;
	private FormatLabel nextPlayer;
	private FormatLabel userCardNum;	
	
	private JButton end;
	
	/*--name label--*/
	private String userName ;
	private FormatLabel userNameLabel ;
	private FormatLabel player1NameLabel ;
	private FormatLabel player2NameLabel ;
	private FormatLabel player3NameLabel ;
	
	
	public CardButton[][] card = new CardButton[4][] ;
	

	private JPanel condPanel;
	private JPanel outside;
	
	
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
	}

	public void initSetting()
	{
		controller = CenterController.getInstance();
		
		controller.removeAllPair();
		controller.setName(userName);
		
		cardIndex = -1;
		isChoose = false;
		
		this.setMainLayout();	
		setCenterPanel(controller.getPlayerIndexNext());
	}
	
	private void setMainLayout()
	{
		container = new JPanel();
		container.setSize(width-(width/25), height);
		container.setBackground(BACKGROUND);
		setPanel();
		setCondPanel();
		
		container.setLayout(new BorderLayout());

		container.add(content[0], BorderLayout.SOUTH);
		container.add(content[1], BorderLayout.WEST);
		container.add(content[2], BorderLayout.NORTH);
		container.add(content[3], BorderLayout.EAST);
		container.add(centerContent, BorderLayout.CENTER);	

		setOutsidePanel();
		setColor();
		changePlayerNameLabel();
	}

	private void setOutsidePanel()
	{
		outside = new JPanel(new BorderLayout());
		outside.add(container,BorderLayout.CENTER);
		outside.add(condPanel,BorderLayout.EAST);
		outside.setBackground(BACKGROUND);
		
		this.add(outside);
	}
	
	private void setCondPanel()
	{
		cond = new JPanel(new GridLayout(4,2));
		condPanel = new JPanel(new BorderLayout());
		condPanel.setPreferredSize(new Dimension(200,0));
		condPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		
		playerNowText = new FormatLabel("目前玩家:");
		nextPlayerText = new FormatLabel("下一個玩家:");
		userCardNumText = new FormatLabel("剩餘牌數:");
		gameStateText = new FormatLabel("");
		
		playerNow = new FormatLabel(controller.player.get(controller.getPlayerIndex()).getName());
		nextPlayer = new FormatLabel(controller.player.get(controller.getPlayerIndexNext()).getName());
		userCardNum = new FormatLabel(String.valueOf(controller.player.get(0).getAmountOfCard()));
		gameState = new FormatLabel("");
		
		setButton();		
		
		cond.add(gameState);
		cond.add(gameStateText);
		
		cond.add(playerNowText);
		cond.add(playerNow);
		
		cond.add(nextPlayerText);
		cond.add(nextPlayer);
		
		cond.add(userCardNumText);
		cond.add(userCardNum);
		
		condPanel.add(cond,BorderLayout.CENTER);
		condPanel.add(end,BorderLayout.SOUTH);	
	}
	
	private void setPanel()
	{
		player_1 = new JPanel(new GridLayout(14,1));
		player_2 = new JPanel();
		player_3 = new JPanel(new GridLayout(14,1));
		user = new JPanel();			
		center = new JPanel(new GridLayout(1,14));

		centerContent = new JPanel();
		centerContent.add(center);		
		
		for(int i = 0;i < PLAYER_NUM;i++)
		{
			content[i] = new JPanel(new BorderLayout());
		}
		
		setNameLabel();
		
		content[0].add(user,BorderLayout.CENTER);		
		content[0].add(userNameLabel, BorderLayout.NORTH);
		
		content[1].add(player1NameLabel,BorderLayout.NORTH);
		content[1].add(player_1,BorderLayout.CENTER);
		
		content[2].add(player2NameLabel,BorderLayout.NORTH);
		content[2].add(player_2,BorderLayout.CENTER);
		
		content[3].add(player3NameLabel,BorderLayout.NORTH);
		content[3].add(player_3,BorderLayout.CENTER);
		
		
		setAllPic();
		
		//TODO 字型/color
	}
	
	private void setColor()
	{
		content[0].setBackground(BACKGROUND);
		content[1].setBackground(BACKGROUND);
		content[2].setBackground(BACKGROUND);
		content[3].setBackground(BACKGROUND);

		userNameLabel.setBackground(BACKGROUND);
		player1NameLabel.setBackground(BACKGROUND);
		player2NameLabel.setBackground(BACKGROUND);
		player3NameLabel.setBackground(BACKGROUND);
		
		centerContent.setBackground(BACKGROUND);
		center.setBackground(BACKGROUND);
		player_1.setBackground(BACKGROUND); 
		player_2.setBackground(BACKGROUND); 
		player_3.setBackground(BACKGROUND);
		user.setBackground(BACKGROUND); 			
		
		condPanel.setBackground(BACKGROUND_LIGHT); 
		
		playerNowText.setBackground(BACKGROUND_LIGHT); 
		nextPlayerText.setBackground(BACKGROUND_LIGHT); 
		userCardNumText.setBackground(BACKGROUND_LIGHT); 
		gameStateText.setBackground(BACKGROUND_LIGHT); 
		
		playerNow.setBackground(BACKGROUND_LIGHT); 
		nextPlayer.setBackground(BACKGROUND_LIGHT); 
		userCardNum.setBackground(BACKGROUND_LIGHT); 
		gameState.setBackground(BACKGROUND_LIGHT); 
		cond.setBackground(BACKGROUND_LIGHT); 
		
		
		setTextColor();
	}
	
	private void setTextColor()
	{
		userNameLabel.setForeground(TEXT);
		player1NameLabel.setForeground(TEXT);
		player2NameLabel.setForeground(TEXT);
		player3NameLabel.setForeground(TEXT);			
		
		playerNowText.setForeground(TEXT); 
		nextPlayerText.setForeground(TEXT); 
		userCardNumText.setForeground(TEXT); 
		gameStateText.setForeground(TEXT); 
		
		playerNow.setForeground(TEXT); 
		nextPlayer.setForeground(TEXT); 
		userCardNum.setForeground(TEXT);  
		gameState.setForeground(TEXT); 
		
	}
	
	private void setNameLabel()
	{
		userNameLabel = new FormatLabel(userName);
		player1NameLabel = new FormatLabel(controller.player.get(1).getName());
		player2NameLabel = new FormatLabel(controller.player.get(2).getName());
		player3NameLabel = new FormatLabel(controller.player.get(3).getName());	
		
		userNameLabel.setHorizontalAlignment(JLabel.CENTER);
		player1NameLabel.setHorizontalAlignment(JLabel.CENTER);	
		player2NameLabel.setHorizontalAlignment(JLabel.CENTER);
		player3NameLabel.setHorizontalAlignment(JLabel.CENTER);
		
	}
	
	private void AIControl()
	{
		if(controller.getPlayerIndex() != 0)
		{
			System.out.println("player index " + controller.getPlayerIndex());
			
			cardIndex = controller.AIChooseCard();
			
			try 
			{	
				validate();
				repaint();
				Thread.currentThread();
				Thread.sleep(500);	

				controller.turn(cardIndex);
				cardIndex = -1;
	
			} catch (InterruptedException e) 
			{

			}

		}
	}
	
	private void setButton()
	{
		end = new JButton("end turn");
		if(controller.getPlayerIndex() == 0)
		{
			end.setForeground(WARN);
			end.setText("choose card");
		}
		else
		{
			end.setForeground(TEXT);
			end.setText("end turn");
		}
		
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
					}
				}				
				else
				{
					AIControl();	
				}			
								
				isChoose = false;
			}});
		
		end.setPreferredSize(new Dimension(0,100));
		
		end.setBorder(BorderFactory.createRaisedBevelBorder());
		end.setBackground(WHITE);
		end.setFont(new Font("Dialog", Font.BOLD , 16));
		
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
		if(fileName != null)
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
		else
		{
			
			user.removeAll();
			if(playerIndex % 2 == 0)//0 2
				content[playerIndex].setPreferredSize(new Dimension(150,120));
			else
				content[playerIndex].setPreferredSize(new Dimension(120,150));
			
			controller.getState();
		}
		
	}
	
	private void setCardListener(final CardButton card)
	{
		card.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) 
			{

				if(!isChoose && GameController.playerIndexNow == 0 && card.getPlayerIndex() == controller.getPlayerIndexNext())
				{
					cardIndex = card.getCardIndex();
					card.setBorder(BorderFactory.createLineBorder(WARN, 2));
					card.turnBack(false);
					isChoose = true;
				}
			
			}

			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				
				if(!isChoose && GameController.playerIndexNow == 0 && card.getPlayerIndex() == controller.getPlayerIndexNext())
					card.setBorder(BorderFactory.createLineBorder(WARN, 2));
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
	
	private void changePlayerNameLabel()
	{
		switch(controller.getPlayerIndex())
		{
			case 0:
				userNameLabel.setForeground(WARN);
				player1NameLabel.setForeground(TEXT);
				player2NameLabel.setForeground(TEXT);
				player3NameLabel.setForeground(TEXT);
				break;
				
			case 1:
				player1NameLabel.setForeground(WARN);
				userNameLabel.setForeground(TEXT);
				player2NameLabel.setForeground(TEXT);
				player3NameLabel.setForeground(TEXT);
				break;
				
			case 2:
				player2NameLabel.setForeground(WARN);
				userNameLabel.setForeground(TEXT);
				player1NameLabel.setForeground(TEXT);
				player3NameLabel.setForeground(TEXT);
				break;
				
			case 3:
				player3NameLabel.setForeground(WARN);
				userNameLabel.setForeground(TEXT);
				player1NameLabel.setForeground(TEXT);
				player2NameLabel.setForeground(TEXT);
				break;
		}	
		
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
		else if(obs instanceof GameController)
		{
			if(controller.getPlayerIndex() == 0)
			{
				end.setForeground(WARN);
				end.setText("choose card");
			}
			else
			{
				end.setForeground(TEXT);
				end.setText("end turn");
			}
				
			removeAllPic();
			
			controller.getState();
			
			playerNow.setText(controller.player.get(controller.getPlayerIndex()).getName());
			nextPlayer.setText(controller.player.get(controller.getPlayerIndexNext()).getName());
			userCardNum.setText(String.valueOf(controller.player.get(0).getAmountOfCard()));
			gameState.setText(controller.getState());			
			changePlayerNameLabel();
			
			if(controller.getState().equals("Game Over"))
			{
				end.setEnabled(false);
				gameState.setForeground(WARN);
				setAllPic();
			}
			else if(controller.getState().equals("Win"))
			{
				end.setEnabled(false);
				gameState.setForeground(BLUE);
				setAllPic();
			}
			else
			{				
				setAllPic();
				setCenterPanel(controller.getPlayerIndexNext());
			}				
			
			this.validate();
			this.repaint(); 
		}
		
	}
}
