package contracts;

import decorators.CowDecorator;
import errors.PostconditionError;
import errors.PreconditionError;
import services.CowService;
import services.EnvironmentService;
import utils.Dir;

public class CowContract extends CowDecorator{

	public CowContract(CowService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		
	}
	/**
	 * pre init(E,x,y,D,h) requires 4 ≥ h ≥ 3
	 */
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		//pre
		if(hp>4 || hp <3) {
			throw new PreconditionError("Error illegal hp value for CowService init");
		}
		
		checkInvariants();
		
		super.init(env, x, y, d, hp);
	}
	
	@Override
	public void step() {
		//pre
		
		checkInvariants();
		
		//capture
		int col_atpre = super.getCol();
		int row_atpre = super.getRow();
		
		super.step();
		
		checkInvariants();
		
		//post Col(M) - 1 ≤ Col(step(M)) ≤ Col(M) + 1
		if(!(col_atpre - 1 <= super.getCol()) || !(col_atpre +1 >= super.getCol())) {
			throw new PostconditionError("Error while moving column");
		}
		
	
		//post Row(M) - 1 ≤ Row(step(M)) ≤ Row(M) + 1
		if(!(row_atpre - 1 <= super.getRow()) || !(row_atpre +1 >= super.getRow())) {
			throw new PostconditionError("Error while moving row");
		}
	}

}
