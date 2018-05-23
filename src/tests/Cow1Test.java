package tests;

import contracts.CowContract;
import impl.CowImpl;

public class Cow1Test extends AbstractCowTest {
	
	@Override
	public void beforeTest() {
		setCow(new CowContract(new CowImpl()));
		//setCow(new CowImpl());
	}
}
