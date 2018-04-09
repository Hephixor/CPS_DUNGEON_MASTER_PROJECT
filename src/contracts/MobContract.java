package contracts;

import decorators.MobDecorator;
import services.MobService;
import utils.Dir;

public class MobContract extends MobDecorator{

	public MobContract(MobService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		//TODO
		return 1;
	}

	@Override
	public Environment getEnv() {
		return super.getEnv();
	}
	
	@Override
	public int getCol() {
		return super.getCol();
	}

	@Override
	public int getRow() {
		return super.getRow();
	}

	@Override
	public Dir getFace() {
		return super.getFace();
	}

	@Override
	public void init(int x, int y, Dir d) {
		// TODO Auto-generated method stub
		super.init(x, y, d);
	}

	@Override
	public void forward() {
		// TODO Auto-generated method stub
		super.forward();
	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub
		super.backward();
	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub
		super.turnL();
	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub
		super.turnR();
	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub
		super.strafeL();
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		super.strafeR();
	}

	


}
