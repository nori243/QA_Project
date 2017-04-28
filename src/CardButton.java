import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		this.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				//	isPick = false;
				CardButton.this.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				CardButton.this.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
					
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
