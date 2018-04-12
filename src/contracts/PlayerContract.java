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
			if(Content(x, y)!=this.getEnv().CellContent(this.getCol()+x, this.getRow()+y)) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(Nature(x,y)!=this.getEnv().getCellNature(this.getCol()+x,this.getRow()+y)) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case S :
			if(Content(x, y)!=this.getEnv().CellContent(this.getCol()-x, this.getRow()-y)) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(Nature(x,y)!=this.getEnv().getCellNature(this.getCol()-x,this.getRow()-y)) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case E :
			if(Content(x, y)!=this.getEnv().CellContent(this.getCol()+y, this.getRow()-x)) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(Nature(x,y)!=this.getEnv().getCellNature(this.getCol()+y,this.getRow()-x)) {
				throw new InvariantError("Cell nature in fov error");
			}
			break;

		case W :
			if(Content(x, y)!=this.getEnv().CellContent(this.getCol()-y, this.getRow()+x)) {
				throw new InvariantError("Cell Content in fov error");
			}

			if(Nature(x,y)!=this.getEnv().getCellNature(this.getCol()-y,this.getRow()+x)) {
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
				if(Viewable(xi,yi)) {
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

		if(Viewable(-1,2)) {
			if(Nature(-1,1)==Cell.WLL || Nature(-1,1)==Cell.DWC || Nature(-1,1)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(Viewable(0,2)) {
			if(Nature(0,1)==Cell.WLL || Nature(0,1)==Cell.DWC || Nature(0,1)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(Viewable(1,2)) {
			if(Nature(1,1)==Cell.WLL || Nature(1,1)==Cell.DWC || Nature(1,1)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(Viewable(-1,3)) {
			if(Nature(-1,2)==Cell.WLL || Nature(-1,2)==Cell.DWC || Nature(-1,2)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}

			if(!Viewable(-1,2)) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(Viewable(0,3)) {
			if(Nature(0,2)==Cell.WLL || Nature(0,2)==Cell.DWC || Nature(0,2)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}

			if(!Viewable(0,2)) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

		if(Viewable(1,3)) {
			if(Nature(1,2)==Cell.WLL || Nature(1,2)==Cell.DWC || Nature(1,2)==Cell.DNC) {
				throw new InvariantError("Error can't see this Cell");
			}

			if(!Viewable(1,2)) {
				throw new InvariantError("Error can't see this Cell");
			}
		}

	}

	@Override
	public PlayerService step() {
		//pre

		CheckInvariants();

		super.step();

		CheckInvariants();

		//capture
		Dir currDir = getFace();
		int posx = getCol();
		int posy = getRow();

		//post
		/**
		 * LastCom(P)=FF implies step(P) = Forward(P)
		 * LastCom(P)=BB implies step(P) = Backward(P)
		 * LastCom(P)=LL implies step(P) = StrafeLeft(P)
		 * LastCom(P)=RR implies step(P) = StrafeRight(P)
		 * LastCom(P)=TL implies step(P) = TurnLeft(P)
		 * LastCom(P)=TR implies step(P) = TurnRight(P)
		 */
		switch(Lastcom()) {
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
		
		return this;


	}

	@Override
	public Command Lastcom() {
		//pre
		
		CheckInvariants();
		
		return super.Lastcom();
	}

	//pre Content(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
	@Override
	public MobService Content(int x, int y) {
		//pre
		if(x!=-1 || x != 0 || x!=1) {
			throw new PreconditionError("Error invalid x coordinates");
		}
		if(y!=-1 || y != 0 || y!=1) {
			throw new PreconditionError("Error invalid y coordinates");
		}
		
		CheckInvariants();
		
		return super.Content(x, y);
	}

	//pre Nature(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
	@Override
	public Cell Nature(int x, int y) {
		if(x!=-1 || x != 0 || x!=1) {
			throw new PreconditionError("Error invalid x coordinates");
		}
		if(y!=-1 || y != 0 || y!=1) {
			throw new PreconditionError("Error invalid y coordinates");
		}
		
		CheckInvariants();
		
		return super.Nature(x, y);
		
	}

	
	//pre Viewable(P,x,y) requires x ∈ {0, width(M)}and y ∈ {0,heigth(M)}
	@Override
	public boolean Viewable(int x, int y) {
		//pre
		if(x<0 || x > getEnv().getWidth()) {
			throw new PreconditionError("Error invalid x coordinates");
		}
		
		if(y<0 || y > getEnv().getHeight()) {
			throw new PreconditionError("Error invalid y coordinates");
		}
		
		CheckInvariants();
		
		return super.Viewable(x, y);
	}

}
