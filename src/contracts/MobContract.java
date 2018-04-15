package contracts;

import java.util.Arrays;

import decorators.MobDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EnvironmentService;
import services.MobService;
import sun.security.action.GetBooleanAction;
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
		
		Cell o = getEnv().getCellNature(getCol(), getRow());
		if( !(Arrays.asList(Cell.WLL, Cell.DNC, Cell.DWC).contains(o)) )
			throw new InvariantError("@inv Environment::CellNature(Envi(M),Col(M),Row(M)) not in {WLL, DNC, DWC}");
	}

	@Override
	public EnvironmentService getEnv() {
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
	public void init(EnvironmentService e, int x, int y, Dir d) {
		//pre
		if( !(0<=x && x<e.getWidth() && 0<=y && y<e.getHeight()) )
			throw new PreconditionError("@pre 0 <= x < Environment::Width(E) and 0 <= y < Environment::Height(E)");
		
		//inv pre
//		checkInvariant();
		
		//capture
		
		//run
		super.init(e, x, y, d);
		
		//inv post
//		checkInvariant();
		
		//post
		if( !(getCol()==x) )
			throw new PostconditionError("@post Col(init(E,x,y,D)) = x");
		
		if( !(getRow()==y) )
			throw new PostconditionError("@post Row(init(E,x,y,D)) = y");
		
		if( !(getFace()==d) )
			throw new PostconditionError(" @post Face(init(E,x,y,D)) = D");
		
		if( !(getEnv()==e) )
			throw new PostconditionError("@post Envi(init(E,x,y,D)) = E");
	}

	@Override
	public void forward() {
		//TODO 
		//pre
		
		//inv pre
		checkInvariant();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		Cell cellnatN = getEnv().getCellNature(col_atpre, row_atpre+1);
		MobService cellcontN = getEnv().getCellContent(col_atpre, row_atpre+1);
		Cell cellnatE = getEnv().getCellNature(col_atpre+1, row_atpre);
		MobService cellcontE = getEnv().getCellContent(col_atpre+1, row_atpre);
		Cell cellnatS = getEnv().getCellNature(col_atpre, row_atpre-1);
		MobService cellcontS = getEnv().getCellContent(col_atpre, row_atpre-1);
		Cell cellnatW = getEnv().getCellNature(col_atpre-1, row_atpre);
		MobService cellcontW = getEnv().getCellContent(col_atpre-1, row_atpre);
		
		//run
		super.forward();
		
		//inv post
		checkInvariant();
		
		//post
		switch(face_atpre) {
		
			case N :
				if(row_atpre+1 < envHeight
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatN)
				&& cellcontN == null) {
					if(!( getRow()==row_atpre+1 && getCol()==col_atpre ))
						throw new PostconditionError("Moved forward North incorrectly.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("Moved forward North incorrectly.");
				}
				break;
				
			case E : 
				if(col_atpre+1 < envWidth
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatE)
				&& cellcontE == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre+1 ))
						throw new PostconditionError("Moved forward East incorrectly.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("Moved forward East incorrectly.");
				}
				break;
					
			case S :
				if(row_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatS)
				&& cellcontS == null) {
					if(!( getRow()==row_atpre-1 && getCol()==col_atpre ))
						throw new PostconditionError("Moved forward South incorrectly.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("Moved forward South incorrectly.");
				}
				break;
				
			case W : 
				if(col_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatW)
				&& cellcontW == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
						throw new PostconditionError("Moved forward South incorrectly.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("Moved forward South incorrectly.");
				}
				break;
							
		}
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
