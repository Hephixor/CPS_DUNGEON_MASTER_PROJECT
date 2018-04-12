package impl;

import services.EntityService;
import services.EnvironmentService;
import services.MobService;
import services.PlayerService;
import utils.Cell;
import utils.Command;
import utils.Dir;

public class PlayerImpl implements PlayerService{

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		// TODO Auto-generated method stub
		
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
	public Command Lastcom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MobService Content(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell Nature(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Viewable(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
