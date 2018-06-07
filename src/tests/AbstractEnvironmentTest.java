package tests;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	
	//TODO
}
