package decorators;

import services.CowService;
import services.EntityService;
import services.EnvironmentService;
import utils.Dir;

public class CowImpl implements CowService{

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EntityService step() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentService getEnv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Dir getFace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(EnvironmentService e, int x, int y, Dir d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		// TODO Auto-generated method stub
		
	}

}
