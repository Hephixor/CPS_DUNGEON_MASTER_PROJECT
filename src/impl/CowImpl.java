package impl;

import java.util.Arrays;
import java.util.Random;

import services.CowService;
import services.EnvironmentService;
import services.MobService;
import utils.Cell;
import utils.Dir;

public class CowImpl implements CowService{
	private 
	int hp;
	int x;
	int y;
	Dir orientation;
	EnvironmentService env;
	Random rnd = new Random();

	public CowImpl() {

	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public void step() {
		//Tools.randomElement(Dir.values());
		/*TODO  ca appelle quelle méthode step() ? super ? cow.step() doit faire
		 * aleatoirement une des 6 commandes possibles d'un mob (forward backward
		 * turnL turnR strafeL strafeR
		 */

		int nombre = rnd.nextInt(7);
		switch(nombre){
		case 0:
			forward();
			break;
		case 1:
			backward();
			break;
		case 2:
			turnL();
			break;
		case 3:
			turnR();
			break;
		case 4:
			strafeR();
			break;
		case 5:
			strafeL();
			break;
		case 6:
			hit();
			break;
		}

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
		return this.orientation;
	}

	@Override
	public void init(EnvironmentService e, int x, int y, Dir d) {
		env = e;
		this.x = x;
		this.y = y;
		orientation = d;
		hp = 3;
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
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		this.env = env ;
		this.x = x;
		this.y = y;
		orientation = d;
		this.hp = hp;
	}

	@Override
	public void hit() {
		
	}

	@Override
	public void takeHit() {
		hp--;
	}

}
