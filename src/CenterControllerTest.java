import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CenterControllerTest {


	static CenterController controller ;
	
	@Before
	public void init()
	{		
		controller = CenterController.getInstance();
	}
	
	@Test
	public void setNameTest()
	{
		String name = "May";
		controller.setName(name);
		assertEquals(name,controller.player.get(0).getName());
	}

}
