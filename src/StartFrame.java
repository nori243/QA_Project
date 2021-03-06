import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.TextUI;


public class StartFrame extends JFrame
{
	private static final int width = 600;
	private static final int height = 300;
	private static final String title = "Jocker";
	
	private JButton enter;
	private FormatLabel errorMsg;
	private FormatLabel msg;
	private JTextField enterName;
	
	private String defaultName = "user";
	

	public static GameFrame gameFrame;
	
	public StartFrame()
	{
		this.setTitle(title);
		this.setSize(width, height);
		this.setLayout(new GridLayout(3,2));
		
		setLabel();
		setEdit();
		setButton();
		setColor();
		setPanel();
		
	}
	
	private void setPanel() 
	{
		JLabel temp = new JLabel(" ");		
		temp.setOpaque(true);
		temp.setBackground(GameFrame.BACKGROUND_LIGHT);
		
		this.add(msg);
		this.add(temp);		
		this.add(enterName);
		this.add(enter);
		this.add(errorMsg);	
		
		temp = new JLabel(" ");		
		temp.setOpaque(true);
		temp.setBackground(GameFrame.BACKGROUND_LIGHT);	
		this.add(temp);		

	}

	private void setEdit()
	{
		enterName = new JTextField(defaultName);
		enterName.setFont(new Font("Dialog", Font.BOLD , 14));
		setEditMouseListener();
		setEditKeyListener();
	}
	
	private void setColor()
	{
		
		enter.setOpaque(true);
		
		enterName.setOpaque(true);
		errorMsg.setOpaque(true);
		msg.setOpaque(true);
		
		enter.setBackground(GameFrame.WHITE);	
		enterName.setBackground(GameFrame.WHITE);
		errorMsg.setBackground(GameFrame.BACKGROUND_LIGHT);
		msg.setBackground(GameFrame.BACKGROUND_LIGHT);
		this.setBackground(GameFrame.BACKGROUND_LIGHT);
		setTextColor();
	}
	
	private void setTextColor()
	{
		enter.setForeground(GameFrame.TEXT);
		errorMsg.setForeground(GameFrame.WARN);
		enterName.setForeground(GameFrame.TEXT);
		msg.setForeground(GameFrame.TEXT);
	}
	
	
	private void setEditKeyListener()
	{
		enterName.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					checkInput();				
			}

			@Override
			public void keyReleased(KeyEvent arg0) 
			{			
			}

			@Override
			public void keyTyped(KeyEvent arg0) 
			{				
			}
		});
	}
	
	private void setEditMouseListener()
	{
		enterName.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(enterName.getText().equals(defaultName))	
				{
					enterName.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
			}

			@Override
			public void mouseExited(MouseEvent arg0) 
			{
			}

			@Override
			public void mousePressed(MouseEvent arg0) 
			{
			}

			@Override
			public void mouseReleased(MouseEvent arg0) 
			{		
		}});
	}
	
	private void setLabel()
	{
		String info = "輸入玩家名稱";
		msg = new FormatLabel(info,14);
		
		errorMsg = new FormatLabel("");
		
	}	
	
	private void setButton()
	{
		final String enterName = "enter";
		enter = new JButton(enterName);
		enter.setBorder(BorderFactory.createRaisedBevelBorder());
		enter.setFont(new Font("Dialog", Font.BOLD , 16));
		
		enter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				checkInput();
			}		
		});
		
	}

	private void checkInput()
	{
		String enterString = enterName.getText();		
		
		if(!enterString.equals(""))
		{			
			gameFrame = new GameFrame(enterString);				
			
			StartInfoFrame frame = new StartInfoFrame(enterString);
			
			StartFrame.this.dispose();
		}
		else
		{
			errorMsg.setText("請輸入名稱");
		}
		
	}
}
