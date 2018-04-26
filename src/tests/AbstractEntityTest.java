package tests;

import org.junit.After;
import org.junit.Before;

import services.EntityService;

public abstract class AbstractEntityTest {

	private EntityService entity;

	protected AbstractEntityTest() {
		entity = null;
	}

	protected final EntityService getEntity() {
		return entity;
	}

	protected final void setEntity(EntityService entity) {
		this.entity = entity;
	}
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		entity = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */

	//TODO
}
