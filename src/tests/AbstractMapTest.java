package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import errors.PreconditionError;
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
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	//TODO A REFAIRE
	
	@Test
	public void testInitPositif(){
		//init
		int w = 14;
		int h = 36;
		
		try {
			//run
			map.init(w, h);
			
			//oracle
			assertTrue("map.getHeight() != h", map.getHeight() == h);
			assertTrue("map.getWidth() != w", map.getWidth() == w);
		}
		catch(AssertionError e){
			//probleme contrat/spec
			fail(e.toString());
		}
	}
	
	@Test
	public void testInitNegatif(){
		//init
		int w = 0;
		int h = 36;
		
		try {
			//run
			map.init(w, h);
			
			//oracle
			fail("testInitNegatif");
		}
		catch(AssertionError e){
			//probleme contrat/spec
			fail(e.toString());	
		}catch(PreconditionError e) {
			//bon deroulement
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testOpenDoorPositif() {
		//TODO
	}
	
	@Test
	public void testOpenDoorNegatif() {
		//TODO
	}
	
	@Test
	public void testCloseDoorPositif() {
		//TODO
	}
	
	@Test
	public void testCloseDoorNegatif() {
		//TODO
	}
}
