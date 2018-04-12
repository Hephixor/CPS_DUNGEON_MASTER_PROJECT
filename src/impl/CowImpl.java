package impl;

import services.CowService;
import services.EntityService;
import services.EnvironmentService;
import utils.Dir;

public class CowImpl implements CowService{
	private 
	int hp;
	int x;
	int y;
	Dir orientation;
	EnvironmentService env;
	
	public CowImpl() {
		
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public EntityService step() {
		return step();
	}

	@Override
	public EnvironmentService getEnv() {
		return getEnv();
	}

	@Override
	public int getCol() {
		return y;
	}

	@Override
	public int getRow() {
		return x;
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
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		this.env = env ;
		this.x = x;
		this.y = y;
		orientation = d;
		this.hp = hp;
	}

}
