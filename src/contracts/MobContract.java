package contracts;

import java.util.Arrays;

import decorators.MobDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EnvironmentService;
import services.MobService;
import utils.Cell;
import utils.Dir;

public class MobContract extends MobDecorator{

	public MobContract(MobService delegate) {
		super(delegate);
	}

	/*========== invariants ==========*/
	
	public void checkInvariant() {
		if( !(0<=getCol() && getCol()<getEnv().getWidth()) )
			throw new InvariantError("@inv 0 <= Col(M) < Environment::Width(Envi(M))");
		
		if( !(0<=getRow() && getRow()<getEnv().getHeight()) )
			throw new InvariantError("@inv 0 <= Row(M) < Environment::Height(Envi(M))");
		
		Cell o = getEnv().getCellNature(getCol(), getRow());
		if( Arrays.asList(Cell.WLL, Cell.DNC, Cell.DWC).contains(o) )
			throw new InvariantError("@inv Environment::CellNature(Envi(M),Col(M),Row(M)) in {WLL, DNC, DWC}");
	}
	
	/*========== preconditions ==========*/
	
	public void preInit(EnvironmentService e, int x, int y) {
		//dependances
		MapContract map = new MapContract(e);
		map.preGetCellNature(x, y);
		
		//pre
		if( !(e.getCellNature(x, y) != Cell.IN && e.getCellContent(x, y) == null) )
			throw new PreconditionError("@pre init mob: CellNature = IN or CellContent not null");
	}
	
	
	/*========== postconditions ==========*/
	
	public void postInit(EnvironmentService e, int x, int y, Dir d) {
		if( !(getCol()==x) )
			throw new PostconditionError("@post Col(init(E,x,y,D)) = x");
		
		if( !(getRow()==y) )
			throw new PostconditionError("@post Row(init(E,x,y,D)) = y");
		
		if( !(getFace()==d) )
			throw new PostconditionError(" @post Face(init(E,x,y,D)) = D");
		
		if( !(getEnv()==e) )
			throw new PostconditionError("@post Envi(init(E,x,y,D)) = E");
	}
	
	public void postForward(Dir face_atpre, int col_atpre, int row_atpre, int envWidth, int envHeight,
			Cell cellnatN, MobService cellcontN, Cell cellnatS, MobService cellcontS, 
			Cell cellnatW, MobService cellcontW, Cell cellnatE, MobService cellcontE) {
		
		switch(face_atpre) {
		
			case N :
				if(row_atpre+1 < envHeight
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatN)
				&& cellcontN == null) {
					if(!( getRow()==row_atpre+1 && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Forward while looking at North.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Forward while looking at North.");
				}
				break;
				
			case E : 
				if(col_atpre+1 < envWidth
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatE)
				&& cellcontE == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre+1 ))
						throw new PostconditionError("incorrectly Moved Forward while looking at East.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Forward while looking at East.");
				}
				break;
					
			case S :
				if(row_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatS)
				&& cellcontS == null) {
					if(!( getRow()==row_atpre-1 && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Forward while looking at South.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Forward while looking at South.");
				}
				break;
				
			case W : 
				if(col_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatW)
				&& cellcontW == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
						throw new PostconditionError("incorrectly Moved Forward while looking at West.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Forward while looking at West.");
				}
				break;
						
		}
	}
	
	public void postBackward(Dir face_atpre, int col_atpre, int row_atpre, int envWidth, int envHeight,
			Cell cellnatN, MobService cellcontN, Cell cellnatS, MobService cellcontS, 
			Cell cellnatW, MobService cellcontW, Cell cellnatE, MobService cellcontE) {
		
		switch(face_atpre) {
		
			case S :
				if(row_atpre+1 < envHeight
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatN)
				&& cellcontN == null) {
					if(!( getRow()==row_atpre+1 && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Backward while looking at South.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Backward while looking at South.");
				}
				break;
				
			case W : 
				if(col_atpre+1 < envWidth
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatE)
				&& cellcontE == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre+1 ))
						throw new PostconditionError("incorrectly Moved Backward while looking at West.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Backward while looking at West.");
				}
				break;
					
			case N :
				if(row_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatS)
				&& cellcontS == null) {
					if(!( getRow()==row_atpre-1 && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Backward while looking at North.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Backward while looking at North.");
				}
				break;
				
			case E : 
				if(col_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatW)
				&& cellcontW == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
						throw new PostconditionError("incorrectly Moved Backward while looking at East.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Moved Backward while looking at East.");
				}
				break;
		}		
	}
	
	public void postStrafeL(Dir face_atpre, int col_atpre, int row_atpre, int envWidth, int envHeight,
			Cell cellnatN, MobService cellcontN, Cell cellnatS, MobService cellcontS, 
			Cell cellnatW, MobService cellcontW, Cell cellnatE, MobService cellcontE) {
		switch(face_atpre) {
		
			case E :
				if(row_atpre+1 < envHeight
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatN)
				&& cellcontN == null) {
					if(!( getRow()==row_atpre+1 && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Left while looking at East.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Left while looking at East.");
				}
				break;
				
			case S : 
				if(col_atpre+1 < envWidth
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatE)
				&& cellcontE == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre+1 ))
						throw new PostconditionError("incorrectly Strafed Left while looking at South.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Left while looking at South.");
				}
				break;
					
			case W :
				if(row_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatS)
				&& cellcontS == null) {
					if(!( getRow()==row_atpre-1 && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Left while looking at West.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Left while looking at West.");
				}
				break;
				
			case N : 
				if(col_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatW)
				&& cellcontW == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
						throw new PostconditionError("incorrectly Strafed Left while looking at North.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Left while looking at North.");
				}
				break;
							
		}
	}
	
	public void postStrafeR(Dir face_atpre, int col_atpre, int row_atpre, int envWidth, int envHeight,
			Cell cellnatN, MobService cellcontN, Cell cellnatS, MobService cellcontS, 
			Cell cellnatW, MobService cellcontW, Cell cellnatE, MobService cellcontE) {
		
		switch(face_atpre) {
		
			case W :
				if(row_atpre+1 < envHeight
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatN)
				&& cellcontN == null) {
					if(!( getRow()==row_atpre+1 && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Right while looking at West.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Right while looking at West.");
				}
				break;
				
			case N : 
				if(col_atpre+1 < envWidth
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatE)
				&& cellcontE == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre+1 ))
						throw new PostconditionError("incorrectly Strafed Right while looking at North.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Right while looking at North.");
				}
				break;
					
			case E :
				if(row_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DWO).contains(cellnatS)
				&& cellcontS == null) {
					if(!( getRow()==row_atpre-1 && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Right while looking at East.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Right while looking at East.");
				}
				break;
				
			case S : 
				if(col_atpre-1 >= 0
				&& Arrays.asList(Cell.EMP, Cell.DNO).contains(cellnatW)
				&& cellcontW == null) {
					if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
						throw new PostconditionError("incorrectly Strafed Right while looking at South.");
				}else {
					if(!( getRow()==row_atpre && getCol()==col_atpre ))
						throw new PostconditionError("incorrectly Strafed Right while looking at South.");
				}
				break;
							
		}
	}
	
	public void postTurnL(Dir face_atpre) {
		switch(face_atpre) {
			case N :
				if(!( getFace()==Dir.W ))
					throw new PostconditionError("incorrectly Turned Left while looking at North.");
				break;
			case W :
				if(!( getFace()==Dir.S ))
					throw new PostconditionError("incorrectly Turned Left while looking at West.");
				break;
			case S :
				if(!( getFace()==Dir.E ))
					throw new PostconditionError("incorrectly Turned Left while looking at South.");
				break;
			case E :
				if(!( getFace()==Dir.N ))
					throw new PostconditionError("incorrectly Turned Left while looking at East.");
				break;
		}
	}
	
	public void postTurnR(Dir face_atpre) {
		switch(face_atpre) {
			case N :
				if(!( getFace()==Dir.E ))
					throw new PostconditionError("incorrectly Turned Rigth while looking at North.");
				break;
			case W :
				if(!( getFace()==Dir.N ))
					throw new PostconditionError("incorrectly Turned Rigth while looking at West.");
				break;
			case S :
				if(!( getFace()==Dir.W ))
					throw new PostconditionError("incorrectly Turned Rigth while looking at South.");
				break;
			case E :
				if(!( getFace()==Dir.S ))
					throw new PostconditionError("incorrectly Turned Rigth while looking at East.");
				break;
		}
	}
	
	/*========== observateurs ==========*/
	
	
	
	/*========== constructeurs ==========*/
	
	@Override
	public void init(EnvironmentService e, int x, int y, Dir d) {
		//pre
		preInit(e, x, y);
		
		//run
		super.init(e, x, y, d);
		
		//inv post
		checkInvariant();
		
		//post
		postInit(e, x, y, d);
	}
	
	
	/*========== operateurs ==========*/

	@Override
	public void forward() { 
		//pre
		
		//inv pre
		checkInvariant();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		/*ci-dessous, les cases_atpre au Nord de la case_atpre du Mob,
		 * a l'Est, au Sud, a l'Ouest.
		 */
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
		postForward(face_atpre, col_atpre, row_atpre, envWidth, envHeight, 
				cellnatN, cellcontN, cellnatS, cellcontS, cellnatW, cellcontW, cellnatE, cellcontE);
		
	}

	@Override
	public void backward() {
		//pre

		//inv pre
		checkInvariant();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		/*ci-dessous, les cases_atpre au Nord de la case_atpre du Mob,
		 * a l'Est, au Sud, a l'Ouest.
		 */
		Cell cellnatN = getEnv().getCellNature(col_atpre, row_atpre+1);
		MobService cellcontN = getEnv().getCellContent(col_atpre, row_atpre+1);
		Cell cellnatE = getEnv().getCellNature(col_atpre+1, row_atpre);
		MobService cellcontE = getEnv().getCellContent(col_atpre+1, row_atpre);
		Cell cellnatS = getEnv().getCellNature(col_atpre, row_atpre-1);
		MobService cellcontS = getEnv().getCellContent(col_atpre, row_atpre-1);
		Cell cellnatW = getEnv().getCellNature(col_atpre-1, row_atpre);
		MobService cellcontW = getEnv().getCellContent(col_atpre-1, row_atpre);
		
		//run
		super.backward();
		
		//inv post
		checkInvariant();
		
		//post
		postBackward(face_atpre, col_atpre, row_atpre, envWidth, envHeight, 
				cellnatN, cellcontN, cellnatS, cellcontS, cellnatW, cellcontW, cellnatE, cellcontE);
	}

	@Override
	public void strafeL() {
		//pre

		//inv pre
		checkInvariant();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		/*ci-dessous, les cases_atpre au Nord de la case_atpre du Mob,
		 * a l'Est, au Sud, a l'Ouest.
		 */
		Cell cellnatN = getEnv().getCellNature(col_atpre, row_atpre+1);
		MobService cellcontN = getEnv().getCellContent(col_atpre, row_atpre+1);
		Cell cellnatE = getEnv().getCellNature(col_atpre+1, row_atpre);
		MobService cellcontE = getEnv().getCellContent(col_atpre+1, row_atpre);
		Cell cellnatS = getEnv().getCellNature(col_atpre, row_atpre-1);
		MobService cellcontS = getEnv().getCellContent(col_atpre, row_atpre-1);
		Cell cellnatW = getEnv().getCellNature(col_atpre-1, row_atpre);
		MobService cellcontW = getEnv().getCellContent(col_atpre-1, row_atpre);
		
		//run
		super.strafeL();
		
		//inv post
		checkInvariant();
		
		//post
		postStrafeL(face_atpre, col_atpre, row_atpre, envWidth, envHeight, 
				cellnatN, cellcontN, cellnatS, cellcontS, cellnatW, cellcontW, cellnatE, cellcontE);
	}

	@Override
	public void strafeR() {
		//pre

		//inv pre
		checkInvariant();
		
		//capture
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		int envHeight = getEnv().getHeight();
		int envWidth = getEnv().getWidth();
		
		/*ci-dessous, les cases_atpre au Nord de la case_atpre du Mob,
		 * a l'Est, au Sud, a l'Ouest.
		 */
		Cell cellnatN = getEnv().getCellNature(col_atpre, row_atpre+1);
		MobService cellcontN = getEnv().getCellContent(col_atpre, row_atpre+1);
		Cell cellnatE = getEnv().getCellNature(col_atpre+1, row_atpre);
		MobService cellcontE = getEnv().getCellContent(col_atpre+1, row_atpre);
		Cell cellnatS = getEnv().getCellNature(col_atpre, row_atpre-1);
		MobService cellcontS = getEnv().getCellContent(col_atpre, row_atpre-1);
		Cell cellnatW = getEnv().getCellNature(col_atpre-1, row_atpre);
		MobService cellcontW = getEnv().getCellContent(col_atpre-1, row_atpre);
		
		//run
		super.strafeR();
		
		//inv post
		checkInvariant();
		
		//post
		postStrafeR(face_atpre, col_atpre, row_atpre, envWidth, envHeight, 
				cellnatN, cellcontN, cellnatS, cellcontS, cellnatW, cellcontW, cellnatE, cellcontE);
	}
	
	@Override
	public void turnL() {
		//pre
		
		//inv pre
		checkInvariant();
		
		//capture
		Dir face_atpre = getFace();
		
		//run
		super.turnL();
		
		//inv post
		checkInvariant();
		
		//post
		postTurnL(face_atpre);
	}

	@Override
	public void turnR() {
		//pre

		//inv pre
		checkInvariant();
		
		//capture
		Dir face_atpre = getFace();
		
		//run
		super.turnR();
		
		//inv post
		checkInvariant();
		
		//post
		postTurnR(face_atpre);
	}

}
