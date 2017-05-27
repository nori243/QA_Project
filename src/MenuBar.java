

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

//http://www.programgo.com/article/57851754685/;jsessionid=B60650312179CDB8732892F88E9492C9

public class MenuBar extends JMenuBar
{
	//TODO 排版/字型/listener
	
	private int height;
	private int width;

	JFrame frame = null;
	
	public MenuBar(int height,int width)
	{
		this.height = height;
		this.width = width;

        final JMenu start = new JMenu("開始新遊戲");
        final JMenu end = new JMenu("離開遊戲");     
                 
        add(start);
        add(end);
        
        start.addMenuListener(new MenuListener(){

			@Override
			public void menuSelected(MenuEvent e)
			{
				if(frame != null)
					frame.setVisible(false);
				
				frame = new RestartFrame(400,150);
			}

			@Override
			public void menuCanceled(MenuEvent e) 
			{			
			}

			@Override
			public void menuDeselected(MenuEvent e) 
			{		
			}
        });
        
        end.addMenuListener(new MenuListener(){
        	@Override
			public void menuSelected(MenuEvent e) 
        	{
        		if(frame != null)
					frame.setVisible(false);
        		
        		frame = new EndFrame(400,150);
        	}
			@Override
			public void menuCanceled(MenuEvent e) 
			{
			}

			@Override
			public void menuDeselected(MenuEvent e) 
			{
			}

		});
	}
}


