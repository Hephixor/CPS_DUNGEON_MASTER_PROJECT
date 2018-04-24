package tests;

import org.junit.After;
import org.junit.Before;

import services.EditMapService;

public abstract class AbstractEditMapTest {

	private EditMapService editmap;
	
	protected AbstractEditMapTest() {
		editmap = null;
	}

	protected final EditMapService getEditMap() {
		return editmap;
	}

	protected final void setEditMap(EditMapService editmap) {
		this.editmap = editmap;
	}
	
	@Before
	public abstract void beforeTests();
	
	@After
	public final void afterTests() {
		editmap = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	//TODO
}
