package contracts;

import java.util.Arrays;
import java.util.Objects;

import services.EntityService;
import services.MobService;
import services.PlayerService;
import utils.Cell;
import utils.Command;
import utils.Dir;
import decorators.PlayerDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}

	public void checkInvariants() {
		Dir d = this.getFace();
		int x = 0;
		int y = 0;

		/**
		 * Face(P) = N implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)+u,Row(P)+v)
		 * Face(P) = N implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)+u,Row(P)+v)
		 * Face(P) = S implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)-u,Row(P)-v)
		 * Face(P) = S implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)-u,Row(P)-v)
		 * Face(P) = E implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)+v,Row(P)-u)
		 * Face(P) = E implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)+v,Row(P)-u)
		 * Face(P) = W implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)-v,Row(P)+u)
		 * Face(P) = W implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)-v,Row(P)+u)
		 */

		//TODO 
		switch(d) {
		case N :
			if(!(Objects.equals(getContent(x, y), getEnv().getCellContent(getCol()+x, getRow()+y)))) {		
				throw new InvariantError("Cell Content in fov error");
			}

			if(!(Objects.equals(getNature(x, y), this.getEnv().getCellNature(this.getCol()+x, this.getRow()+y)))) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case S :
			if(!(Objects.equals(getContent(x, y), this.getEnv().getCellContent(this.getCol()-x, this.getRow()-y)))) {
				throw new InvariantError("Cell Content in fov error");
			}
			if(!(Objects.equals(getNature(x, y), this.getEnv().getCellNature(this.getCol()-x, this.getRow()-y)))) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case E :
			if(!(Objects.equals(getContent(x, y), this.getEnv().getCellContent(this.getCol()+y, this.getRow()-x)))) {
				throw new InvariantError("Cell Content in fov error");
			}
			if(!(Objects.equals(getNature(x, y), this.getEnv().getCellNature(this.getCol()+y, this.getRow()-x)))) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case W :
			if(!(Objects.equals(getContent(x, y), this.getEnv().getCellContent(this.getCol()-y, this.getRow()+x)))) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(!(Objects.equals(getNature(x, y), this.getEnv().getCellNature(this.getCol()-y, this.getRow()+x)))) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;
		default:
			break;
		}

		/**
		 * forall u,v in [-1,1] × [-1,1], not Viewable(P,u,v)
		 */
		//		for(int xi =-1;xi<1;xi++) {
		//			for(int yi=-1; yi < 1; yi++) {
		//				if(!getViewable(xi,yi)) {
		//					throw new InvariantError("FOV error you can't see this cell");
		//				}
		//			}
		//		}

		/**
		 * Viewable(P,-1,2) = Nature(P,-1,1) ∈/ {WALL, DWC, DNC }
		 * Viewable(P,0,2) = Nature(P,0,1) ∈/ {WALL, DWC, DNC }
		 * Viewable(P,1,2) = Nature(P,1,1) ∈/ {WALL, DWC, DNC }
		 * Viewable(P,-1,3) = Nature(P,-1,2) ∈/ {WALL, DWC, DNC } and Viewable(P,-1,2)
		 * Viewable(P,0,3) = Nature(P,0,2) ∈/ {WALL, DWC, DNC } and Viewable(P,0,2)
		 * Viewable(P,1,3) = Nature(P,1,2) ∈/ {WALL, DWC, DNC } and Viewable(P,1,2)
		 */

		if(getViewable(-1,2)) {
			if(getNature(-1,1)==Cell.WLL || getNature(-1,1)==Cell.DWC || getNature(-1,1)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(getViewable(0,2)) {
			if(getNature(0,1)==Cell.WLL || getNature(0,1)==Cell.DWC || getNature(0,1)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(getViewable(1,2)) {
			if(getNature(1,1)==Cell.WLL || getNature(1,1)==Cell.DWC || getNature(1,1)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(getViewable(-1,3)) {
			if(getNature(-1,2)==Cell.WLL || getNature(-1,2)==Cell.DWC || getNature(-1,2)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}

			if(!getViewable(-1,2)) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(getViewable(0,3)) {
			if(getNature(0,2)==Cell.WLL || getNature(0,2)==Cell.DWC || getNature(0,2)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}

			if(!getViewable(0,2)) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(getViewable(1,3)) {
			if(getNature(1,2)==Cell.WLL || getNature(1,2)==Cell.DWC || getNature(1,2)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}

			if(!getViewable(1,2)) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

	}

	@Override
	public MobService getContent(int x, int y) {
		//pre
		//pre Content(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
		//		System.out.println("getContent x " + x );
		//		System.out.println("getContent y " + y );
		if(x<-1 || x > 1) {
			throw new PreconditionError("Error invalid x coordinates");
		}
		if(y<-1 || y>3) {
			throw new PreconditionError("Error invalid y coordinates");
		}

		//inv pre
		//checkInvariants();

		//run
		return super.getContent(x, y);
	}

	@Override
	public Cell getNature(int x, int y) {
		//pre 
		//Nature(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
		if(x<-1 || x >1) {
			throw new PreconditionError("Error invalid x coordinates");
		}
		if(y<-1 || y>3) {
			throw new PreconditionError("Error invalid y coordinates");
		}

		//inv pre
		//checkInvariants();

		//run
		return super.getNature(getCol()+x, getRow()+y);

	}

	@Override
	public boolean getViewable(int x, int y) {
		//pre
		//pre Viewable(P,x,y) requires x ∈ {0, width(M)}and y ∈ {0,heigth(M)}
		//		System.out.println("getViewable x "  +x );
		//		System.out.println("getViewable y"  +y );
		if(x<-1 || x > 1) {
			throw new PreconditionError("Error invalid x coordinates");
		}

		if(y<-1|| y > 3) {
			throw new PreconditionError("Error invalid y coordinates");
		}

		//inv pre
		//checkInvariants();

		//run
		return super.getViewable(x, y);
	}

	@Override
	public void setLastCom(Command com){
		//pre

		//checkInv pre
		checkInvariants();

		//run
		super.setLastCom(com);

		//inv post
		checkInvariants();

		//post
	}

	@Override
	public void takeHit() {
		//pre
		// takeHit(E) implies HP(E)>0
		if(getHP()<=0){
			throw new PreconditionError("Error cannot kill the dead");
		}

		//inv pre
		checkInvariants();

		//capture
		int hp_atpre = super.getHP();
		//run
		super.takeHit();

		//inv post
		checkInvariants();

		//post 
		//takeHit(E) implies HP(E) = HP@pre - 1
		if(super.getHP()!=hp_atpre-1){
			throw new PostconditionError("Error hit didn't take 1 damage");
		}


	}


	public void hit(){

		//pre

		//inv pre
		checkInvariants();

		//capture
		Cell cell_atpre = null;
		EntityService mob_atpre = null;
		
		if(super.getFace()==Dir.N){
			//Si on agit sur une porte
			if(Arrays.asList(Cell.DNC, Cell.DWC,Cell.DNO,Cell.DWO).contains(super.getNature(super.getCol(), super.getRow()+1))){
				cell_atpre = super.getNature(super.getCol(), super.getRow()+1);
			}
			//Si on agit sur un mob
			else{
				mob_atpre = (EntityService) super.getContent(super.getCol(), super.getRow()+1);
			}
		}
		else if(super.getFace()==Dir.S){
			//Si on agit sur une porte
			if(Arrays.asList(Cell.DNC, Cell.DWC,Cell.DNO,Cell.DWO).contains(super.getNature(super.getCol(), super.getRow()-1))){
				cell_atpre = super.getNature(super.getCol(), super.getRow()-1);
			}
			//Si on agit sur un mob
			else{
				mob_atpre = (EntityService) super.getContent(super.getCol(), super.getRow()-1);
			}
		}

		else if(super.getFace()==Dir.W){
			//Si on agit sur une porte
			if(Arrays.asList(Cell.DNC, Cell.DWC,Cell.DNO,Cell.DWO).contains(super.getNature(super.getCol()-1, super.getRow()))){
				cell_atpre = super.getNature(super.getCol()-1, super.getRow());
			}
			//Si on agit sur un mob
			else{
				mob_atpre = (EntityService) super.getContent(super.getCol()-1, super.getRow());
			}
		}
		else if(super.getFace()==Dir.E){
			//Si on agit sur une porte
			if(Arrays.asList(Cell.DNC, Cell.DWC,Cell.DNO,Cell.DWO).contains(super.getNature(super.getCol()+1, super.getRow()))){
				cell_atpre = super.getNature(super.getCol()+1, super.getRow());
			}
			//Si on agit sur un mob
			else{
				mob_atpre = (EntityService) super.getContent(super.getCol()+1, super.getRow());
			}
		}
		else{
			//impossible
		}

		//run
		super.hit();

		//Inv post
		checkInvariants();

		//post
		//Si on a agit sur une case vide, on sort;
		if(!(cell_atpre==null && mob_atpre==null)){
			if(super.getFace()==Dir.N){
				//Si on a agit sur une porte
				if(cell_atpre!=null){
					switch(cell_atpre){
					case DNO:
						if(super.getNature(super.getCol(), super.getRow()+1)!=Cell.DNC){
							throw new PostconditionError("Error closing door N");
						}
						break;
					case DWO:
						if(super.getNature(super.getCol(), super.getRow()+1)!=Cell.DWC){
							throw new PostconditionError("Error closing door W");
						}
						break;
					case DNC:
						if(super.getNature(super.getCol(), super.getRow()+1)!=Cell.DNO){
							throw new PostconditionError("Error opening door N");
						}
						break;
					case DWC:
						if(super.getNature(super.getCol(), super.getRow()+1)!=Cell.DWO){
							throw new PostconditionError("Error opening door W");
						}
						break;
					default:
						break;
					}
				}
				//Sinon on a agit sur un ennemi
				else{
					if(((EntityService) super.getContent(super.getCol(), super.getRow()+1)).getHP() != (mob_atpre.getHP()-1)){
					}
				}
			}
			else if(super.getFace()==Dir.S){
				//Si on a agit sur une porte
				if(cell_atpre!=null){
					switch(cell_atpre){
					case DNO:
						if(super.getNature(super.getCol(), super.getRow()-1)!=Cell.DNC){
							throw new PostconditionError("Error closing door N");
						}
						break;
					case DWO:
						if(super.getNature(super.getCol(), super.getRow()-1)!=Cell.DWC){
							throw new PostconditionError("Error closing door W");
						}
						break;
					case DNC:
						if(super.getNature(super.getCol(), super.getRow()-1)!=Cell.DNO){
							throw new PostconditionError("Error opening door N");
						}
						break;
					case DWC:
						if(super.getNature(super.getCol(), super.getRow()-1)!=Cell.DWO){
							throw new PostconditionError("Error opening door W");
						}
						break;
					default:
						break;
					}
				}
				//Sinon on a agit sur un ennemi
				else{
					if(((EntityService) super.getContent(super.getCol(), super.getRow()-1)).getHP() != (mob_atpre.getHP()-1)){
					}
				}
			}

			else if(super.getFace()==Dir.W){
				//Si on a agit sur une porte
				if(cell_atpre!=null){
					switch(cell_atpre){
					case DNO:
						if(super.getNature(super.getCol()-1, super.getRow())!=Cell.DNC){
							throw new PostconditionError("Error closing door N");
						}
						break;
					case DWO:
						if(super.getNature(super.getCol()-1, super.getRow())!=Cell.DWC){
							throw new PostconditionError("Error closing door W");
						}
						break;
					case DNC:
						if(super.getNature(super.getCol()-1, super.getRow())!=Cell.DNO){
							throw new PostconditionError("Error opening door N");
						}
						break;
					case DWC:
						if(super.getNature(super.getCol()-1, super.getRow())!=Cell.DWO){
							throw new PostconditionError("Error opening door W");
						}
						break;
					default:
						break;
					}
				}
				//Sinon on a agit sur un ennemi
				else{
					if(((EntityService) super.getContent(super.getCol()-1, super.getRow())).getHP() != (mob_atpre.getHP()-1)){
					}
				}
			}

			else if(super.getFace()==Dir.E){
				//Si on a agit sur une porte
				if(cell_atpre!=null){
					switch(cell_atpre){
					case DNO:
						if(super.getNature(super.getCol()+1, super.getRow())!=Cell.DNC){
							throw new PostconditionError("Error closing door N");
						}
						break;
					case DWO:
						if(super.getNature(super.getCol()+1, super.getRow())!=Cell.DWC){
							throw new PostconditionError("Error closing door W");
						}
						break;
					case DNC:
						if(super.getNature(super.getCol()+1, super.getRow())!=Cell.DNO){
							throw new PostconditionError("Error opening door N");
						}
						break;
					case DWC:
						if(super.getNature(super.getCol()+1, super.getRow())!=Cell.DWO){
							throw new PostconditionError("Error opening door W");
						}
						break;
					default:
						break;
					}
				}
				//Sinon on a agit sur un ennemi
				else{
					if(((EntityService) super.getContent(super.getCol()+1, super.getRow())).getHP() != (mob_atpre.getHP()-1)){
					}
				}
			}

			else{
				//impossible
			}
		}

	}
	
	/* =================================================================== */
	
	@Override
	public void step() {
		//pre

		//inv pre
		checkInvariants();

		//capture
		Command lastCom_atpre = getLastCom();
		
		/*captures des methodes Forward Strafe Turn ....*/
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
		
		/*captures de hit*/
			Cell cell_atpre = null;
			EntityService mob_atpre = null;
			
			if(super.getFace()==Dir.N){
				//Si on agit sur une porte
				if(Arrays.asList(Cell.DNC, Cell.DWC,Cell.DNO,Cell.DWO).contains(super.getNature(super.getCol(), super.getRow()+1))){
					cell_atpre = super.getNature(super.getCol(), super.getRow()+1);
				}
				//Si on agit sur un mob
				else{
					mob_atpre = (EntityService) super.getContent(super.getCol(), super.getRow()+1);
				}
			}
			else if(super.getFace()==Dir.S){
				//Si on agit sur une porte
				if(Arrays.asList(Cell.DNC, Cell.DWC,Cell.DNO,Cell.DWO).contains(super.getNature(super.getCol(), super.getRow()-1))){
					cell_atpre = super.getNature(super.getCol(), super.getRow()-1);
				}
				//Si on agit sur un mob
				else{
					mob_atpre = (EntityService) super.getContent(super.getCol(), super.getRow()-1);
				}
			}
	
			else if(super.getFace()==Dir.W){
				//Si on agit sur une porte
				if(Arrays.asList(Cell.DNC, Cell.DWC,Cell.DNO,Cell.DWO).contains(super.getNature(super.getCol()-1, super.getRow()))){
					cell_atpre = super.getNature(super.getCol()-1, super.getRow());
				}
				//Si on agit sur un mob
				else{
					mob_atpre = (EntityService) super.getContent(super.getCol()-1, super.getRow());
				}
			}
			else if(super.getFace()==Dir.E){
				//Si on agit sur une porte
				if(Arrays.asList(Cell.DNC, Cell.DWC,Cell.DNO,Cell.DWO).contains(super.getNature(super.getCol()+1, super.getRow()))){
					cell_atpre = super.getNature(super.getCol()+1, super.getRow());
				}
				//Si on agit sur un mob
				else{
					mob_atpre = (EntityService) super.getContent(super.getCol()+1, super.getRow());
				}
			}
			else{
				//impossible
			}
		
		//run
		super.step();
		
		//		System.out.println("position af step x:" + getCol() + " y:"+getRow());
		
		//inv post
		checkInvariants();
		
		//post
		if(lastCom_atpre!=null) {
			switch(lastCom_atpre) {
			case FF:
				//post forward
				switch(face_atpre) {
				
					case N :
						if(row_atpre+1 < envHeight
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DNO).contains(cellnatN)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DWO).contains(cellnatE)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DNO).contains(cellnatS)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DWO).contains(cellnatW)
						&& cellcontW == null) {
							if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
								throw new PostconditionError("incorrectly Moved Forward while looking at West.");
						}else {
							if(!( getRow()==row_atpre && getCol()==col_atpre ))
								throw new PostconditionError("incorrectly Moved Forward while looking at West.");
						}
						break;
									
				}
				break;

			case BB:
				//post backward
				switch(face_atpre) {
				
					case S :
						if(row_atpre+1 < envHeight
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DNO).contains(cellnatN)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DWO).contains(cellnatE)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DNO).contains(cellnatS)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DWO).contains(cellnatW)
						&& cellcontW == null) {
							if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
								throw new PostconditionError("incorrectly Moved Backward while looking at East.");
						}else {
							if(!( getRow()==row_atpre && getCol()==col_atpre ))
								throw new PostconditionError("incorrectly Moved Backward while looking at East.");
						}
						break;
									
				}
				break;

			case LL:
				//post strafeL
				switch(face_atpre) {
				
					case E :
						if(row_atpre+1 < envHeight
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DNO).contains(cellnatN)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DWO).contains(cellnatE)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DNO).contains(cellnatS)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DWO).contains(cellnatW)
						&& cellcontW == null) {
							if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
								throw new PostconditionError("incorrectly Strafed Left while looking at North.");
						}else {
							if(!( getRow()==row_atpre && getCol()==col_atpre ))
								throw new PostconditionError("incorrectly Strafed Left while looking at North.");
						}
						break;
								
			
				}
				break;

			case RR:
				//post strafeR
				switch(face_atpre) {
				
					case W :
						if(row_atpre+1 < envHeight
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DNO).contains(cellnatN)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DWO).contains(cellnatE)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DNO).contains(cellnatS)
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
						&& Arrays.asList(Cell.IN, Cell.OUT, Cell.EMP, Cell.DWO).contains(cellnatW)
						&& cellcontW == null) {
							if(!( getRow()==row_atpre && getCol()==col_atpre-1 ))
								throw new PostconditionError("incorrectly Strafed Right while looking at South.");
						}else {
							if(!( getRow()==row_atpre && getCol()==col_atpre ))
								throw new PostconditionError("incorrectly Strafed Right while looking at South.");
						}
						break;					
				}
				break;

			case TL:
				//post TurnL
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
				break;

			case TR:
				//post TurnR
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
				break;
				
			case HIT:
				//post Hit
				//Si on a agit sur une case vide, on sort;
				if(!(cell_atpre==null && mob_atpre==null)){
					if(super.getFace()==Dir.N){
						//Si on a agit sur une porte
						if(cell_atpre!=null){
							switch(cell_atpre){
							case DNO:
								if(super.getNature(super.getCol(), super.getRow()+1)!=Cell.DNC){
									throw new PostconditionError("Error closing door N");
								}
								break;
							case DWO:
								if(super.getNature(super.getCol(), super.getRow()+1)!=Cell.DWC){
									throw new PostconditionError("Error closing door W");
								}
								break;
							case DNC:
								if(super.getNature(super.getCol(), super.getRow()+1)!=Cell.DNO){
									throw new PostconditionError("Error opening door N");
								}
								break;
							case DWC:
								if(super.getNature(super.getCol(), super.getRow()+1)!=Cell.DWO){
									throw new PostconditionError("Error opening door W");
								}
								break;
							default:
								break;
							}
						}
						//Sinon on a agit sur un ennemi
						else{
							if(((EntityService) super.getContent(super.getCol(), super.getRow()+1)).getHP() != (mob_atpre.getHP()-1)){
							}
						}
					}
					else if(super.getFace()==Dir.S){
						//Si on a agit sur une porte
						if(cell_atpre!=null){
							switch(cell_atpre){
							case DNO:
								if(super.getNature(super.getCol(), super.getRow()-1)!=Cell.DNC){
									throw new PostconditionError("Error closing door N");
								}
								break;
							case DWO:
								if(super.getNature(super.getCol(), super.getRow()-1)!=Cell.DWC){
									throw new PostconditionError("Error closing door W");
								}
								break;
							case DNC:
								if(super.getNature(super.getCol(), super.getRow()-1)!=Cell.DNO){
									throw new PostconditionError("Error opening door N");
								}
								break;
							case DWC:
								if(super.getNature(super.getCol(), super.getRow()-1)!=Cell.DWO){
									throw new PostconditionError("Error opening door W");
								}
								break;
							default:
								break;
							}
						}
						//Sinon on a agit sur un ennemi
						else{
							if(((EntityService) super.getContent(super.getCol(), super.getRow()-1)).getHP() != (mob_atpre.getHP()-1)){
							}
						}
					}

					else if(super.getFace()==Dir.W){
						//Si on a agit sur une porte
						if(cell_atpre!=null){
							switch(cell_atpre){
							case DNO:
								if(super.getNature(super.getCol()-1, super.getRow())!=Cell.DNC){
									throw new PostconditionError("Error closing door N");
								}
								break;
							case DWO:
								if(super.getNature(super.getCol()-1, super.getRow())!=Cell.DWC){
									throw new PostconditionError("Error closing door W");
								}
								break;
							case DNC:
								if(super.getNature(super.getCol()-1, super.getRow())!=Cell.DNO){
									throw new PostconditionError("Error opening door N");
								}
								break;
							case DWC:
								if(super.getNature(super.getCol()-1, super.getRow())!=Cell.DWO){
									throw new PostconditionError("Error opening door W");
								}
								break;
							default:
								break;
							}
						}
						//Sinon on a agit sur un ennemi
						else{
							if(((EntityService) super.getContent(super.getCol()-1, super.getRow())).getHP() != (mob_atpre.getHP()-1)){
							}
						}
					}

					else if(super.getFace()==Dir.E){
						//Si on a agit sur une porte
						if(cell_atpre!=null){
							switch(cell_atpre){
							case DNO:
								if(super.getNature(super.getCol()+1, super.getRow())!=Cell.DNC){
									throw new PostconditionError("Error closing door N");
								}
								break;
							case DWO:
								if(super.getNature(super.getCol()+1, super.getRow())!=Cell.DWC){
									throw new PostconditionError("Error closing door W");
								}
								break;
							case DNC:
								if(super.getNature(super.getCol()+1, super.getRow())!=Cell.DNO){
									throw new PostconditionError("Error opening door N");
								}
								break;
							case DWC:
								if(super.getNature(super.getCol()+1, super.getRow())!=Cell.DWO){
									throw new PostconditionError("Error opening door W");
								}
								break;
							default:
								break;
							}
						}
						//Sinon on a agit sur un ennemi
						else{
							if(((EntityService) super.getContent(super.getCol()+1, super.getRow())).getHP() != (mob_atpre.getHP()-1)){
							}
						}
					}

					else{
						//impossible
					}
				}
				break;
				
			default:
				break;

			}
		}
	}

}
