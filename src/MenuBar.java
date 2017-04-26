

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
	
	public MenuBar(int height,int width)
	{
		this.height = height;
		this.width = width;
		
		JMenuItem item1, item2, item3, item4;
        JMenu demo1 = new JMenu("開始新遊戲");
        item1 = new JMenuItem("one");
        item2 = new JMenuItem("two");
        demo1.add(item1);
        demo1.add(item2);
        JMenu demo2 = new JMenu("暫停");
        item3 = new JMenuItem("three");
        item4 = new JMenuItem("four");        
        demo2.add(item3);
        demo2.add(item4);
        JMenu demo3 = new JMenu("更改名稱");
        
        add(demo1);
        add(demo2);
        add(demo3);
        
        MenuAction menuAction = new MenuAction();
        demo1.addMenuListener(menuAction);
	}

}

class MenuAction implements MenuListener
{

	@Override
	public void menuCanceled(MenuEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e)
	{
		// TODO Auto-generated method stub		
	}

	@Override
	public void menuSelected(MenuEvent e) 
	{
		// TODO Auto-generated method stub
		//if(e.getSource() == menu1)
			//;
		
	}

}
