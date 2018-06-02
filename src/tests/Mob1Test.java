package tests;

import contracts.EditMapContract;
import contracts.EnvironmentContract;
import contracts.MobContract;
import impl.EditMapImpl;
import impl.EnvironmentImpl;
import impl.MobImpl;

public class Mob1Test extends AbstractMobTest {
	
	@Override
	public void beforeTest() {
		setMob(new MobContract(new MobImpl()));
		setEditMap(new EditMapContract(new EditMapImpl()));
		setEnvironment(new EnvironmentContract(new EnvironmentImpl()));
		//setMob(new MobImpl());
	}
}
