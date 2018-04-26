package tests;

import contracts.EnvironmentContract;
import impl.EnvironmentImpl;

public class Environment1Test extends AbstractEnvironmentTest {

	@Override
	public void beforeTest() {
		setEnvironment(new EnvironmentContract(new EnvironmentImpl()));
		//setEnvironment(new EnvironmentImpl());
	}
}
