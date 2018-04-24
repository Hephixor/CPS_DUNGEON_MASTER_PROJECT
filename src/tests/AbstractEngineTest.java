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
	public abstract void beforeTests();
	
	@After
	public final void afterTests() {
		engine = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	//TODO
	
}
