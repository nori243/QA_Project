import java.awt.Font;

import javax.swing.JLabel;


public class FormatLabel extends JLabel
{
	public FormatLabel(String text)
	{
		super(text);
		this.setFont(new Font("Dialog", Font.BOLD , 12));
	}
	
	public FormatLabel(String text,int size)
	{
		super(text);
		this.setFont(new Font("Dialog", Font.BOLD , size));
	}

}
