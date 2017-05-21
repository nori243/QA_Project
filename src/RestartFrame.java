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


public class RestartFrame extends JFrame 
{
	private int width;
	private int height;
	
	private JButton yes;
	private JButton no;
	
	private JPanel mainPanel;
	private JPanel buttonPanel;
	
	private JLabel textLabel;
	
	public RestartFrame(int width,int height)
	{
		this.width = width;
		this.height = height;
		
		this.setSize(width, height);
		this.setTitle("RESTART");
		
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

	private void initPanel() 
	{
		mainPanel = new JPanel(new GridLayout(2,1));
		//mainPanel.setBackground(Color.WHITE);
		buttonPanel = new JPanel(new GridLayout(1,2));
	}	
	
	private void initLabel()
	{		
		textLabel = new JLabel("是否開始新遊戲?");
		textLabel.setFont(new Font("Dialog", Font.BOLD , 16));
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
				
				RestartObservable observer = new RestartObservable();
				observer.restart(true);
				RestartFrame.this.dispose();
			}
		});
		
		no.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				RestartFrame.this.dispose();				
			}
		});
	}

}
