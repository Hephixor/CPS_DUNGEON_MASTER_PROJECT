package impl;

import services.EnvironmentService;
import services.MobService;
import utils.Dir;

public class MobImpl implements MobService{

	int x;
	int y;
	int hp;
	Dir orientation;
	EnvironmentService env;
	
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
		hp=1; // ??
		
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
	public void takeHit() {
		System.out.println("MobImpl- I have "+hp+" HP");
		hp--;
		System.out.println("MobImpl- now "+hp+" HP");
		if(hp==0) {
			//retirer entité
		}
		
	}

}
