package tests;

import contracts.MapContract;
import impl.MapImpl;

public class Map1Test extends AbstractMapTest {
	
	@Override
	public void beforeTests() {
		setMap(new MapContract(new MapImpl()));
		//setMap(new MapImpl());
	}
}
