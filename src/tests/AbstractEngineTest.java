package tests;

import org.junit.After;
import org.junit.Before;

import services.EngineService;

public abstract class AbstractEngineTest {
	
	private EngineService engine;

	protected AbstractEngineTest() {
		engine = null;
	}

	protected final EngineService getEngine() {
		return engine;
	}

	protected final void setEngine(EngineService engine) {
		this.engine = engine;
	}
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		engine = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	//TODO
	
}
