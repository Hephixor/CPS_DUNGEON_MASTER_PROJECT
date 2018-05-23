package tests;

import org.junit.After;
import org.junit.Before;

import services.CowService;

public abstract class AbstractCowTest {
	
	private CowService cow;
	
	protected AbstractCowTest() {
		cow = null;
	}
	
	protected final CowService getCow() {
		return cow;
	}
	
	protected final void setCow(CowService cow) {
		this.cow = cow;
	}
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		cow = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	//TODO
}
