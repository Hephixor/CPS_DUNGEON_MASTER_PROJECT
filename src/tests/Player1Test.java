package tests;

import contracts.PlayerContract;
import impl.PlayerImpl;

public class Player1Test extends AbstractPlayerTest {

	@Override
	public void beforeTests() {
		setPlayer(new PlayerContract(new PlayerImpl()));
		//setPlayer(new PlayerImpl());
	}
}
