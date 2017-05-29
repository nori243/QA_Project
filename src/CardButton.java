import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class CardButton extends JButton
{
	private int playerIndex = -1;
	private int cardIndex ;
	private boolean isBack = false;
	private ImageIcon img;
	private ImageIcon back;
	
	public CardButton(ImageIcon img,int cardIndex)
	{
		super(img);
		this.img = img;
		this.cardIndex = cardIndex;
		back = new ImageIcon(System.getProperty("user.dir")+"//cardPic//back.gif");
		
		
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		
		
		
	}

	public void turnBack(boolean turn)
	{
		isBack = turn;
		
		if(turn)
		{
			this.setIcon(back);
		}
		else
		{
			this.setIcon(img);
		}
		
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
