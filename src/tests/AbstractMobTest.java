package tests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import errors.PreconditionError;
import services.EnvironmentService;
import services.MobService;
import utils.Dir;

public abstract class AbstractMobTest {
	
	private MobService mob;
	private EnvironmentService env;

	protected AbstractMobTest() {
		mob = null;
		env = null;
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
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		mob = null;
		env = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	/* init */
	
	@Test
	public void preInitPositif() {
		//init
		env.init(14, 35);
		
		//operation
		try {
			/*TODO rajouter une 
			precondition [case not in {WLL,DNC,DWC}]
			au constructeur init dans le contrat ?*/
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
		env.init(14, 35);
		
		//operation
		try {
			mob.init(env, 10, 35, Dir.E);
			
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
