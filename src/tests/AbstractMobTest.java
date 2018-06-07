package tests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import errors.PreconditionError;
import services.EditMapService;
import services.EnvironmentService;
import services.MobService;
import utils.Cell;
import utils.Dir;

public abstract class AbstractMobTest {
	
	private MobService mob;
	private EnvironmentService env;
	private EditMapService editmap;

	protected AbstractMobTest() {
		mob = null;
		env = null;
		editmap = null;
	}

	protected final MobService getMob() {
		return mob;
	}

	protected final void setMob(MobService mob) {
		this.mob = mob;
	}
	
	protected final EnvironmentService getEnvironment() {
		return env;
	}

	protected final void setEnvironment(EnvironmentService env) {
		this.env = env;
	}
	
	public EditMapService getEditMap() {
		return editmap;
	}

	public void setEditMap(EditMapService editmap) {
		this.editmap = editmap;
	}
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		mob = null;
		env = null;
		editmap = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	/* init */
	
	@Test
	public void preInitPositif() {
		//init
		editmap.init(14,35);
		editmap.setNature(7, 23, Cell.OUT);
		env.init(editmap);

		//operation
		try {
			mob.init(env, 7, 23, Dir.E);
		}
		//oracle
		catch(PreconditionError e) {
			fail(e.toString());
		}
		
	}
	
	@Test
	public void preInitNegatif() {
		//init
		editmap.init(14,35);
		editmap.setNature(7, 23, Cell.IN);
		env.init(editmap);
		
		//operation
		try {
			mob.init(env, 7, 23, Dir.E);
			
			//probleme
			fail("preInitNegatif");
		}
		//oracle
		catch(PreconditionError e) {
			//ok
		}
	}

	/* ========== COUVERTURE TRANSITIONS ========== */

	//TODO
}
