package contracts;

import decorators.EnvironmentDecorator;
import errors.PreconditionError;
import services.EnvironmentService;
import utils.Option;

public class EnvironmentContract extends EnvironmentDecorator{

	public EnvironmentContract(EnvironmentService delegate) {
		super(delegate);
	}
	
	
	//include et non refine donc on n'hérite pas des invariants de MapService, il faut refaire des tests.
	public void checkInvariant() {
		//void
	}
	
	/**
	 * pre CloseDoor(M,x,y) requires CellContent(M,x,y) = No
	 */
	public EnvironmentService closeDoor(int x, int y) {
		//pre
		if(CellContent(x,y)!=Option.NO) {
			throw new PreconditionError("Target cell is not NO");
		}
		
		//CheckInvariants
		
		checkInvariant();
		
		super.closeDoor(x, y);
		
		checkInvariant();
		
		//Post
		
		return this;
	}

	

	

}
