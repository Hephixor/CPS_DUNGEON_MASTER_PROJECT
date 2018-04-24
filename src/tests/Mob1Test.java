package tests;

import contracts.MobContract;
import impl.MobImpl;

public class Mob1Test extends AbstractMobTest {
	
	@Override
	public void beforeTests() {
		setMob(new MobContract(new MobImpl()));
		//setMob(new MobImpl());
	}
}
