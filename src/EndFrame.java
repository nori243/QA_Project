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


public class EndFrame extends JFrame
{
	private int width;
	private int height;
	
	private JPanel mainPanel;	
	private JPanel buttonPanel;
	private FormatLabel textLabel;
	
	private JButton yes;
	private JButton no;
	
	public EndFrame(int width,int height)
	{
		this.width = width;
		this.height = height;
		
		this.setSize(width, height);
		this.setTitle("EXIT");
		
		this.setMainLayout();
		
		this.setResizable(false);	
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	private void setMainLayout() 
	{
		initLabel();
		initPanel();
		initButton();
		
		mainPanel.add(textLabel);
		mainPanel.add(buttonPanel);
		buttonPanel.add(yes);
		buttonPanel.add(no);
		
		this.add(mainPanel);
	}
	
	private void initLabel()
	{		
		textLabel = new FormatLabel("是否確定離開遊戲?",16);
	}
	
	private void initPanel()
	{
		mainPanel = new JPanel(new GridLayout(2,1));
		//mainPanel.setBackground(Color.WHITE);
		buttonPanel = new JPanel(new GridLayout(1,2));

		
	}
	
	private void initButton()
	{
		yes = new JButton("是");
		no = new JButton("否");
		
		yes.setBorder(BorderFactory.createRaisedBevelBorder());
		yes.setBackground(Color.white);
		yes.setFont(new Font("Dialog", Font.BOLD , 12));
		
		no.setBorder(BorderFactory.createRaisedBevelBorder());
		no.setBackground(Color.white);
		no.setFont(new Font("Dialog", Font.BOLD , 12));
		
		yes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);				
			}
		});
		
		no.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				EndFrame.this.dispose();				
			}
		});
	}
	

}
