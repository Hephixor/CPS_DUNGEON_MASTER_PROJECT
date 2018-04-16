package contracts;

import decorators.PlayerDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.MobService;
import services.PlayerService;
import utils.Cell;
import utils.Command;
import utils.Dir;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}

	public void CheckInvariants() {
		Dir d = this.getFace();
		int x = 1;
		int y = 1;

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
		switch(d) {
		case N :
			if(getContent(x, y)!=this.getEnv().getCellContent(this.getCol()+x, this.getRow()+y)) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(getNature(x,y)!=this.getEnv().getCellNature(this.getCol()+x,this.getRow()+y)) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case S :
			if(getContent(x, y)!=this.getEnv().getCellContent(this.getCol()-x, this.getRow()-y)) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(getNature(x,y)!=this.getEnv().getCellNature(this.getCol()-x,this.getRow()-y)) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case E :
			if(getContent(x, y)!=this.getEnv().getCellContent(this.getCol()+y, this.getRow()-x)) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(getNature(x,y)!=this.getEnv().getCellNature(this.getCol()+y,this.getRow()-x)) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case W :
			if(getContent(x, y)!=this.getEnv().getCellContent(this.getCol()-y, this.getRow()+x)) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(getNature(x,y)!=this.getEnv().getCellNature(this.getCol()-y,this.getRow()+x)) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;
		default:
			break;
		}

		/**
		 * forall u,v in [-1,1] × [-1,1], not Viewable(P,u,v)
		 */
		for(int xi =-1;xi<1;xi++) {
			for(int yi=-1; yi < 1 ; yi++) {
				if(getViewable(xi,yi)) {
					throw new InvariantError("FOV error you can't see this cell");
				}
			}
		}

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
	public void step() {
		//pre

		//inv pre
		CheckInvariants();

		//capture
		Dir currDir = getFace();
		int posx = getCol();
		int posy = getRow();
		
		//run
		super.step();

		//inv post
		CheckInvariants();

		//post
		switch(getLastCom()) {
		case FF:
			switch(getFace()) {
			case N:
				if(getCol()!=posx+1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case S:
				if(getCol()!=posx-1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case E:
				if(getRow()!=posy+1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case W:
				if(getRow()!=posy-1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			}

		case BB:
			switch(getFace()) {
			case N:
				if(getCol()!=posx-1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case S:
				if(getCol()!=posx+1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case E:
				if(getRow()!=posy-1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case W:
				if(getRow()!=posy+1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			}

		case LL:
			switch(getFace()) {
			case N:
				if(getRow()!=posy-1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case S:
				if(getRow()!=posy+1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case E:
				if(getCol()!=posx+1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case W:
				if(getCol()!=posx-1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			}

		case RR:
			switch(getFace()) {
			case N:
				if(getRow()!=posy+1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case S:
				if(getRow()!=posy-1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case E:
				if(getCol()!=posx-1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			case W:
				if(getCol()!=posx+1) {
					throw new PostconditionError("Error didn't move properply");
				}
				break;
			}

		case TL:
			switch(currDir) {
			case N:
				if(getFace()!=Dir.W) {
					throw new PostconditionError("Didn't turned properly");
				}
				break;

			case W:
				if(getFace()!=Dir.S) {
					throw new PostconditionError("Didn't turned properly");
				}
				break;

			case S:
				if(getFace()!=Dir.E) {
					throw new PostconditionError("Didn't turned properly");
				}
				break;

			case E:
				if(getFace()!=Dir.N) {
					throw new PostconditionError("Didn't turned properly");
				}
				break;

			default:
				break;
			}

		case TR:
			switch(currDir) {
			case N:
				if(getFace()!=Dir.E) {
					throw new PostconditionError("Didn't turned properly");
				}
				break;

			case E:
				if(getFace()!=Dir.S) {
					throw new PostconditionError("Didn't turned properly");
				}
				break;

			case S:
				if(getFace()!=Dir.W) {
					throw new PostconditionError("Didn't turned properly");
				}
				break;

			case W:
				if(getFace()!=Dir.N) {
					throw new PostconditionError("Didn't turned properly");
				}
				break;

			default:
				break;
			}
		default:
			break;

		}
	}
	
	@Override
	public MobService getContent(int x, int y) {
		//pre
		//pre Content(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
		if(x!=-1 || x != 0 || x!=1) {
			throw new PreconditionError("Error invalid x coordinates");
		}
		if(y!=-1 || y != 0 || y!=1) {
			throw new PreconditionError("Error invalid y coordinates");
		}
		
		//inv pre
		CheckInvariants();
		
		//run
		return super.getContent(x, y);
	}

	@Override
	public Cell getNature(int x, int y) {
		//pre 
		//Nature(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
		if(x!=-1 || x != 0 || x!=1) {
			throw new PreconditionError("Error invalid x coordinates");
		}
		if(y!=-1 || y != 0 || y!=1) {
			throw new PreconditionError("Error invalid y coordinates");
		}
		
		//inv pre
		CheckInvariants();
		
		//run
		return super.getNature(x, y);
		
	}

	@Override
	public boolean getViewable(int x, int y) {
		//pre
		//pre Viewable(P,x,y) requires x ∈ {0, width(M)}and y ∈ {0,heigth(M)}
		if(x<0 || x > getEnv().getWidth()) {
			throw new PreconditionError("Error invalid x coordinates");
		}
		
		if(y<0 || y > getEnv().getHeight()) {
			throw new PreconditionError("Error invalid y coordinates");
		}
		
		//inv pre
		CheckInvariants();
		
		//run
		return super.getViewable(x, y);
	}

}
