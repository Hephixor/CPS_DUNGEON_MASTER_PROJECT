package impl;

import services.EntityService;
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
	public EntityService step() {
		return null;
	}

	@Override
	public EnvironmentService getEnv() {
		return null;
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
	}

	@Override
	public void backward() {
	}

	@Override
	public void turnL() {
	}

	@Override
	public void turnR() {	
	}

	@Override
	public void strafeL() {	
	}

	@Override
	public void strafeR() {
	}

	@Override
	public Command Lastcom() {
		return null;
	}

	@Override
	public MobService Content(int x, int y) {
		
		return env.CellContent(x, y);
	}

	@Override
	public Cell Nature(int x, int y) {
		return env.getCellNature(x, y);
	}

	@Override
	public boolean Viewable(int x, int y) {
		return this.Viewable(x, y);
	}

}
