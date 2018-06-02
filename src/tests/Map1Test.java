package tests;

import impl.EditMapImpl;

import services.EditMapService;
import contracts.EditMapContract;
import contracts.MapContract;


public class Map1Test extends AbstractMapTest {
	
	@Override
	public void beforeTest() {
		System.out.println("asas");
		EditMapService editmap = new EditMapContract(new EditMapImpl());
		setEditMap(editmap);
		setMap(new MapContract(editmap));
	}
}
