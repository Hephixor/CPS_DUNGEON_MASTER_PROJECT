package tests;

import org.junit.After;
import org.junit.Before;

import services.PlayerService;

public abstract class AbstractPlayerTest {

	private PlayerService player;

	protected AbstractPlayerTest() {
		player = null;
	}

	protected final PlayerService getPlayer() {
		return player;
	}

	protected final void setPlayer(PlayerService player) {
		this.player = player;
	}
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		player = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	//TODO
}
