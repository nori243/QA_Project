import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class StartInfoFrame extends JFrame 
{
	private CenterController c;
	
	private JPanel center;
	
	private FormatLabel user;
	private FormatLabel userName;
	
	private FormatLabel firstPlayer;
	private FormatLabel turn;
	
	private FormatLabel firstPlayerText;
	private FormatLabel turnText;
	
	private JTextArea gameInfo;
	private FormatLabel gameInfoText;
	
	private JButton ok;
	private JButton cancel;
	
	private String name;
	
	public StartInfoFrame(String name)
	{
		this.c = CenterController.getInstance();
		c.init();
		this.name = name;
		
		setPanel();
		this.setSize(600, 600);
		this.setVisible(true);
		
		//置中
		this.setLocationRelativeTo(null);
	}
	
	private void setPanel()
	{
		setLabel();
		setButton();
		
		center = new JPanel(new GridLayout(5,2));
		
		center.add(user);
		center.add(userName);
		
		center.add(firstPlayerText);
		center.add(firstPlayer);
		
		center.add(turnText);
		center.add(turn);
		
		center.add(gameInfoText);
		center.add(gameInfo);
		
		center.add(ok);
		center.add(cancel);
		

		setColor();
		
		this.add(center);
	}
	
	private void setColor()
	{
		center.setBackground(GameFrame.BACKGROUND_LIGHT);
		
		user.setBackground(GameFrame.BACKGROUND_LIGHT);
		userName.setBackground(GameFrame.BACKGROUND_LIGHT);		
		
		firstPlayer.setBackground(GameFrame.BACKGROUND_LIGHT);
		turn.setBackground(GameFrame.BACKGROUND_LIGHT);	
		
		firstPlayerText.setBackground(GameFrame.BACKGROUND_LIGHT);
		turnText.setBackground(GameFrame.BACKGROUND_LIGHT);		
		
		gameInfo.setBackground(GameFrame.BACKGROUND_LIGHT);
		gameInfoText.setBackground(GameFrame.BACKGROUND_LIGHT);	
		
		ok.setBackground(GameFrame.WHITE);
		cancel.setBackground(GameFrame.WHITE);
		
		setTextColor();
	}
	
	private void setTextColor()
	{
		user.setForeground(GameFrame.TEXT);
		userName.setForeground(GameFrame.TEXT);
		
		firstPlayer.setForeground(GameFrame.TEXT);
		turn.setForeground(GameFrame.TEXT);
		
		firstPlayerText.setForeground(GameFrame.TEXT);
		turnText.setForeground(GameFrame.TEXT);	
		
		gameInfo.setForeground(GameFrame.TEXT);
		gameInfoText.setForeground(GameFrame.TEXT);	
		
		ok.setForeground(GameFrame.TEXT);
		cancel.setForeground(GameFrame.WARN);
	}
	
	private void setLabel()
	{
		String firstName = c.player.get(c.getPlayerIndex()).getName();
		String info = "按下'end turn'可換下一個玩家\n"
					+ "玩家回合時，點選牌後不可再更換\n"
					+ "當其中一方玩家手中沒有牌時，遊戲則結束\n"
					+ "試著先把牌丟完來贏得勝利吧!";
		
		user = new FormatLabel("玩家名稱 :",16);
		userName = new FormatLabel(name,16);
		
		firstPlayerText = new FormatLabel("起始玩家 :",16);
		turnText = new FormatLabel("回合順序 :",16);

		firstPlayer = new FormatLabel(firstName,16);
		turn = new FormatLabel(c.getTurn(),16);
		
		gameInfoText = new FormatLabel("遊戲資訊 : ",16);
		gameInfo = new JTextArea(info);
		gameInfo.setEditable(false);
		gameInfo.setFont(new Font("Dialog", Font.BOLD , 14));
		gameInfo.setLineWrap(true);
		gameInfo.setWrapStyleWord(true);

	}
	
	private void setButton()
	{
		ok = new JButton("開始遊戲");
		cancel = new JButton("離開遊戲");
		
		ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				StartFrame.gameFrame.initSetting();
				StartFrame.gameFrame.setVisible(true);
				StartInfoFrame.this.dispose();
			}
		});
		
		cancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				EndFrame f = new EndFrame(400,150);
			}
		});
		
		ok.setBorder(BorderFactory.createRaisedBevelBorder());
		ok.setBackground(Color.white);
		ok.setFont(new Font("Dialog", Font.BOLD , 12));
		
		cancel.setBorder(BorderFactory.createRaisedBevelBorder());
		cancel.setBackground(Color.white);
		cancel.setFont(new Font("Dialog", Font.BOLD , 12));
	}
	
}
