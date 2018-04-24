package tests;

import contracts.EditMapContract;
import impl.EditMapImpl;

public class EditMap1Test extends AbstractEditMapTest {

	@Override
	public void beforeTests() {
		setEditMap(new EditMapContract(new EditMapImpl()));
		//setEditMap(new EditMapImpl());
	}

}
