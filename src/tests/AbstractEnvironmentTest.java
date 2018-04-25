package tests;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;

import errors.InvariantError;
import errors.PreconditionError;
import services.EnvironmentService;
import utils.Cell;

public abstract class AbstractEnvironmentTest {

	private EnvironmentService env;

	protected AbstractEnvironmentTest() {
		env = null;
	}

	protected final EnvironmentService getEnvironment() {
		return env;
	}

	protected final void setEnvironment(EnvironmentService env) {
		this.env = env;
	}
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		env = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	/* closeDoor */
	
	@Test
	public void preCloseDoorPositif() {
		//init
		env.init(14, 35);
		
		boolean found = false;
		int xDoor = -1;
		int yDoor = -1;
		
		for(int x=0; x<env.getWidth(); x++) {
			for(int y=0; y<env.getHeight(); y++) {
				if(Arrays.asList(Cell.DNO, Cell.DWO).contains(map.getCellNature(x, y))) {
					xDoor = x;
					yDoor = y;
					found = true;
					break;
				}
				if(Arrays.asList(Cell.DNC, Cell.DWC).contains(map.getCellNature(x, y))) {
					env.openDoor(x, y);
					xDoor = x;
					yDoor = y;
					found = true;
					break;
				}
			}
			if(found) break;
		}
		
		//operation
		try {
			env.closeDoor(xDoor, yDoor);
		}
		//oracle
		catch(PreconditionError | InvariantError e) {
			fail(e.toString());
		}
	}
	
	//TODO
}
