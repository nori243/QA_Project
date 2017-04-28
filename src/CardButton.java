import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class CardButton extends JButton
{
	private int playerIndex = -1;
	private int cardIndex ;
	private boolean isPick = false;
	
	public CardButton(ImageIcon img,int cardIndex)
	{
		super(img);
		this.cardIndex = cardIndex;
		
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(isPick)
				{
					CardButton.this.setBorder(BorderFactory.createEmptyBorder());
					isPick = false;
				}
					
				else
				{
					CardButton.this.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
					isPick = true;
				}
					
			}
		});
		
	}
	
	public void setPlayerIndex(int index)
	{
		this.playerIndex = index;
	}
	
	public int getCardIndex()
	{
		return this.cardIndex;
	}
	
	public int getPlayerIndex()
	{
		return this.playerIndex;
	}

//animate : http://www.cnblogs.com/xiaowenji/archive/2011/02/04/1949165.html
}
