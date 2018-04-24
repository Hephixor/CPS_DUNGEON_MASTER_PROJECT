package tests;

import contracts.EnvironmentContract;
import contracts.MobContract;
import impl.EnvironmentImpl;
import impl.MobImpl;

public class Mob1Test extends AbstractMobTest {
	
	@Override
	public void beforeTest() {
		setMob(new MobContract(new MobImpl()));
		setEnvironment(new EnvironmentContract(new EnvironmentImpl()));
		//setMob(new MobImpl());
	}
}
