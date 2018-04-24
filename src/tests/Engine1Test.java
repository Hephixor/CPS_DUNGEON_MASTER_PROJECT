package tests;

import contracts.EngineContract;
import impl.EngineImpl;

public class Engine1Test extends AbstractEngineTest {

	@Override
	public void beforeTests() {
		setEngine(new EngineContract(new EngineImpl()));
		//setEngine(new EngineImpl());
	}

}
