package tests;

import contracts.CowContract;
import impl.CowImpl;

public class Cow1Test extends AbstractCowTest {
	
	@Override
	public void beforeTests() {
		setCow(new CowContract(new CowImpl()));
		//setCow(new CowImpl());
	}
}
