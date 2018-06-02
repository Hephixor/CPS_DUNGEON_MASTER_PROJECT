package tests;

import impl.EditMapImpl;

import org.junit.Test;

import services.EditMapService;
import contracts.MapContract;


public class Map1Test extends AbstractMapTest {
	
	@Override
	public void beforeTest() {
		EditMapService editmap = new EditMapImpl();
		setEditMap(editmap);
		setMap(new MapContract(editmap));
		//setMap(new MapImpl());
		
		//System.out.println(this.getEditMap().getClass());
		//System.out.println(this.getMap().getClass());
	}
	
	@Test
	public void testInvoke(){
	assertTrue(true);
	}
}
