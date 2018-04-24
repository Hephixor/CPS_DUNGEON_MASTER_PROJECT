package tests;

import org.junit.After;
import org.junit.Before;

import services.MobService;

public abstract class AbstractMobTest {
	
	private MobService mob;
	
	protected AbstractMobTest() {
		mob = null;
	}

	protected final MobService getMob() {
		return mob;
	}

	protected final void setMob(MobService mob) {
		this.mob = mob;
	}
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		mob = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	//TODO
	
}
