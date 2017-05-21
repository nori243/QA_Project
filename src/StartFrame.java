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
	private JLabel errorMsg;
	private JLabel msg;
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
		setPanel();
	}
	
	private void setPanel() 
	{
		JLabel temp = new JLabel(" ");
		
		this.add(msg);
		this.add(temp);		
		this.add(enterName);
		this.add(enter);
		this.add(errorMsg);		
	}

	private void setEdit()
	{
		enterName = new JTextField(defaultName);
		setEditMouseListener();
		setEditKeyListener();
		

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
		msg = new JLabel(info);
		msg.setFont(new Font("Dialog", Font.BOLD , 14));
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.red);
		errorMsg.setFont(new Font("Dialog", Font.BOLD , 12));
		
	}	
	
	private void setButton()
	{
		final String enterName = "enter";
		enter = new JButton(enterName);
		enter.setBorder(BorderFactory.createRaisedBevelBorder());
		enter.setBackground(Color.white);
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
			gameFrame = new GameFrame();			
			gameFrame.setVisible(true);	
			StartFrame.this.dispose();
		}
		else
		{
			errorMsg.setText("請輸入名稱");
		}
		
	}
	
	public void setFrame(GameFrame g)
	{
		gameFrame = g;
	}
	
}
