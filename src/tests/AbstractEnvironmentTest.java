package tests;

import org.junit.After;
import org.junit.Before;

import services.EnvironmentService;

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
	
	//TODO
}
