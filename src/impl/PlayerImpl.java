package impl;

import java.util.Arrays;

import services.EnvironmentService;
import services.MobService;
import services.PlayerService;
import utils.Cell;
import utils.Command;
import utils.Dir;

public class PlayerImpl implements PlayerService{
	int x;
	int y;
	int hp;
	Dir orientation;
	EnvironmentService env;
	Command lastCom;


	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		this.env = env;
		this.x=x;
		this.y=y;
		orientation = d;
		this.hp = hp;
	}

	@Override
	public void step() {

		switch(lastCom){
		case FF:
			forward();
			break;
		case BB:
			backward();
			break;
		case RR:
			strafeR();
			break;
		case LL:
			strafeL();
			break;
		case TL:
			turnL();
			break;
		case TR:
			turnR();
			break;

		}

		lastCom=null;
	}

	@Override
	public EnvironmentService getEnv() {
		return env;
	}

	@Override
	public int getCol() {
		return x;
	}

	@Override
	public int getRow() {
		return y;
	}

	@Override
	public Dir getFace() {
		return orientation;
	}

	@Override
	public void init(EnvironmentService e, int x, int y, Dir d) {
		env=e;
		this.x=x;
		this.y=y;
		orientation=d;
		hp=10;

	}

	@Override
	public void forward() {

		int xatpre = this.x;
		int yatpre = this.y;

		int xnew = -1;
		int ynew = -1;

		MobService entatpre = getEnv().getCellContent(this.x, this.y);

		switch(orientation){
		case N:
			ynew=yatpre+1;
			xnew=xatpre;
			break;
		case S:
			ynew=yatpre-1;
			xnew=xatpre;
			break;
		case E:
			xnew=xatpre+1;
			ynew=yatpre;
			break;
		case W:
			xnew=xatpre-1;
			ynew=yatpre;
			break;
		}

		if(!(Arrays.asList(Cell.DNC,Cell.DWC,Cell.WLL).contains(env.getCellNature(xnew,ynew))) && env.getCellContent(xnew, ynew)==null){
			System.out.println("Je suis " + entatpre +" je vais en x"+xnew+" y"+ynew+" et dans la case se trouve " + env.getCellContent(xnew, ynew));
			env.setCellContent(xatpre, yatpre, null);
			env.setCellContent(xnew, ynew, entatpre);
			this.x=xnew;
			this.y=ynew;
			env.setCellContent(xatpre, yatpre, null);
		}

	}

	@Override
	public void backward() {
		int xatpre = this.x;
		int yatpre = this.y;

		int xnew = -1;
		int ynew = -1;

		MobService entatpre = getEnv().getCellContent(this.x, this.y);

		switch(orientation){
		case N:
			ynew=yatpre-1;
			xnew=xatpre;
			break;
		case S:
			ynew=yatpre+1;
			xnew=xatpre;
			break;
		case E:
			xnew=xatpre-1;
			ynew=yatpre;
			break;
		case W:
			xnew=xatpre+1;
			ynew=yatpre;
			break;
		}

		if(!(Arrays.asList(Cell.DNC,Cell.DWC,Cell.WLL).contains(env.getCellNature(xnew,ynew))) && env.getCellContent(xnew, ynew)==null){
			env.setCellContent(xatpre, yatpre, null);
			env.setCellContent(xnew, ynew, entatpre);
			this.x=xnew;
			this.y=ynew;
			env.setCellContent(xatpre, yatpre, null);
		}

	}

	@Override
	public void turnL() {

		switch(orientation){
		case N:
			orientation = Dir.W;
			break;
		case S:
			orientation = Dir.E;
			break;
		case E:
			orientation = Dir.N;
			break;
		case W:
			orientation = Dir.S;
			break;
		}

	}

	@Override
	public void turnR() {

		switch(orientation){
		case N:
			orientation = Dir.E;
			break;
		case S:
			orientation = Dir.W;
			break;
		case E:
			orientation = Dir.S;
			break;
		case W:
			orientation = Dir.N;
			break;
		}

	}

	@Override
	public void strafeL() {
		int xatpre = this.x;
		int yatpre = this.y;

		int xnew = -1;
		int ynew = -1;

		MobService entatpre = getEnv().getCellContent(this.x, this.y);

		switch(orientation){
		case N:
			ynew=yatpre;
			xnew=xatpre-1;
			break;
		case S:
			ynew=yatpre;
			xnew=xatpre+1;
			break;
		case E:
			xnew=xatpre;
			ynew=yatpre+1;
			break;
		case W:
			xnew=xatpre;
			ynew=yatpre-1;
			break;
		}

		if(!(Arrays.asList(Cell.DNC,Cell.DWC,Cell.WLL).contains(env.getCellNature(xnew,ynew))) && env.getCellContent(xnew, ynew)==null){
			env.setCellContent(xatpre, yatpre, null);
			env.setCellContent(xnew, ynew, entatpre);
			this.x=xnew;
			this.y=ynew;
			env.setCellContent(xatpre, yatpre, null);
		}
	}

	@Override
	public void strafeR() {
		int xatpre = this.x;
		int yatpre = this.y;

		int xnew = -1;
		int ynew = -1;

		MobService entatpre = getEnv().getCellContent(this.x, this.y);

		switch(orientation){
		case N:
			ynew=yatpre;
			xnew=xatpre+1;
			break;
		case S:
			ynew=yatpre;
			xnew=xatpre-1;
			break;
		case E:
			xnew=xatpre;
			ynew=yatpre-1;
			break;
		case W:
			xnew=xatpre;
			ynew=yatpre+1;
			break;
		}

		if(!(Arrays.asList(Cell.DNC,Cell.DWC,Cell.WLL).contains(env.getCellNature(xnew,ynew))) && env.getCellContent(xnew, ynew)==null){
			env.setCellContent(xatpre, yatpre, null);
			env.setCellContent(xnew, ynew, entatpre);
			this.x=xnew;
			this.y=ynew;
			env.setCellContent(xatpre, yatpre, null);
		}

	}

	@Override
	public Command getLastCom() {
		return lastCom;
	}

	@Override
	public MobService getContent(int x, int y) {

		return env.getCellContent(x, y);
	}

	@Override
	public Cell getNature(int x, int y) {

		return env.getCellNature(x, y);
	}

	@Override
	public boolean getViewable(int x, int y) {
		//TODO

		if(x==0 && y<=0) return true;
		else if(x<0 && y==0)return true;
		else if(x>0 && y==0)return true;
		
		else if(x==0 && y>0){
			for(int i=1; i<y; i++){
				if(env.getCellNature(this.x+x, this.y+y)==Cell.WLL){
					return false;
				}
			}
			return true;
		}

		
		else if(x<0 && y<0){
			if(getViewable(x,y+1) && getViewable(x+1,y)){
				return true;
			}
		}
		
		else if(x<0 && y > 0){
			for(int i = 1 ;i<y;i++){
				if(env.getCellNature(this.x+x, this.y+i)==Cell.WLL){
					return false;
				}
			}
			return true;
		}
		
		else if(x>0 && y<0){
			if(getViewable(x-1,y) && getViewable(x,y+1)){
				return true;
			}
		}
		
		else if(x>0 && y>0){
			for(int i = 1 ;i<y;i++){
				if(env.getCellNature(this.x+x, this.y+i)==Cell.WLL){
					return false;
				}
			}
			return true;
		}
		
		return false;

	}

	@Override 
	public void setLastCom(Command com){
		this.lastCom = com ;
	}

}
