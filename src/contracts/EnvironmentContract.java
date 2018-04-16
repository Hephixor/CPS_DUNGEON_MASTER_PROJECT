package contracts;

import decorators.EnvironmentDecorator;
import errors.PreconditionError;
import services.EnvironmentService;

public class EnvironmentContract extends EnvironmentDecorator{

	public EnvironmentContract(EnvironmentService delegate) {
		super(delegate);
	}
	
	
	//include et non refine donc on n'hérite pas des invariants de MapService, il faut refaire des tests.
	public void checkInvariants() {

	}
	
	@Override
	public EnvironmentService closeDoor(int x, int y) {
		//pre
		if(getCellContent(x,y)!=null) {
			throw new PreconditionError("Can't close door on a mob");
		}
		
		//inv pre
		checkInvariants();
		
		//run
		super.closeDoor(x, y);
		
		//inv post
		checkInvariants();
		
		//post
		
		return this;
	}
}
