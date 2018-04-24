package tests;

import contracts.EnvironmentContract;
import impl.EnvironmentImpl;

public class Environment1Test extends AbstractEnvironmentTest {

	@Override
	public void beforeTests() {
		setEnvironment(new EnvironmentContract(new EnvironmentImpl()));
		//setEnvironment(new EnvironmentImpl());
	}
}
