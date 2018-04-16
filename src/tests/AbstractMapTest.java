package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.MapService;

public abstract class AbstractMapTest {
	
	private MapService map;
	
	protected AbstractMapTest() {
		map = null;
	}

	protected final MapService getMap() {
		return map;
	}

	protected final void setMap(MapService map) {
		this.map = map;
	}
	
	@Before
	public abstract void beforeTests();
	
	@After
	public final void afterTests() {
		map = null;
		this.toString();
	}
	
	@Test
	public void testInitPositif(){
		int w = 14;
		int h = 36;
		
		try {
			//run
			map.init(w, h);
			
			//oracle
			assertTrue( map.getHeight() == h);
			assertTrue( map.getWidth() == w);
		}
		catch(Error e){
			e.printStackTrace();
			fail("Pre init");
		}
	}
}
