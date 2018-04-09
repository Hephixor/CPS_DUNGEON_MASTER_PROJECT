package contracts;

import decorators.MobDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.MobService;
import utils.Cell;
import utils.Dir;

public class MobContract extends MobDecorator{

	public MobContract(MobService delegate) {
		super(delegate);
	}

	/**
	 * @inv 0 <= Col(M) < Environment::Width(Envi(M))
	 * @inv 0 <= Row(M) < Environment::Height(Envi(M))
	 * @inv Environment::CellNature(Envi(M),Col(M),Row(M)) not in {WLL, DNC, DWC}
	 */
	public void checkInvariant() {
		
		if( !(0<=getCol() && getCol()<getEnv().getWidth()) )
			throw new InvariantError("@inv 0 <= Col(M) < Environment::Width(Envi(M))");
		
		if( !(0<=getRow() && getRow()<getEnv().getHeight()) )
			throw new InvariantError("@inv 0 <= Row(M) < Environment::Height(Envi(M))");
		
		if( !(getEnv().getCellNature(getCol(), getRow()) != Cell.WLL && 
				getEnv().getCellNature(getCol(), getRow()) != Cell.DNC && 
				getEnv().getCellNature(getCol(), getRow()) != Cell.DWC) )
			throw new InvariantError("@inv Environment::CellNature(Envi(M),Col(M),Row(M)) not in {WLL, DNC, DWC}");
	}

	@Override
	public Environment getEnv() {
		return super.getEnv();
	}
	
	@Override
	public int getCol() {
		return super.getCol();
	}

	@Override
	public int getRow() {
		return super.getRow();
	}

	@Override
	public Dir getFace() {
		return super.getFace();
	}

	/**
	 * @pre 0 <= x < Environment::Width(E) and 0 <= y < Environment::Height(E)
	 * @post Col(init(E,x,y,D)) = x
	 * @post Row(init(E,x,y,D)) = y
	 * @post Face(init(E,x,y,D)) = D
	 * @post Envi(init(E,x,y,D)) = E
	 */
	@Override
	public void init(Environment e, int x, int y, Dir d) {
		// TODO Auto-generated method stub
		
		//pre
		if( !(0<=x && x<) )
			throw new PreconditionError("@pre 0 <= x < Environment::Width(E) and 0 <= y < Environment::Height(E)");
		
		//inv pre
		checkInvariant();
		
		//capture
		
		//run
		super.init(x, y, d);
		
		//inv post
		checkInvariant();
		
		//post
		if( !() )
			throw new PostconditionError("@post Col(init(E,x,y,D)) = x");
		
		if( !() )
			throw new PostconditionError("@post Row(init(E,x,y,D)) = y");
		
		if( !() )
			throw new PostconditionError(" @post Face(init(E,x,y,D)) = D");
		
		if( !() )
			throw new PostconditionError("@post Envi(init(E,x,y,D)) = E");
	}

	@Override
	public void forward() {
		// TODO Auto-generated method stub
		super.forward();
	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub
		super.backward();
	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub
		super.turnL();
	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub
		super.turnR();
	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub
		super.strafeL();
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		super.strafeR();
	}

	


}
