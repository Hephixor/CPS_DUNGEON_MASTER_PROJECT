package decorators;

import services.EnvironmentService;
import services.MobService;
import services.PlayerService;
import utils.Cell;
import utils.Command;
import utils.Dir;

public class PlayerDecorator implements PlayerService{
	
	private final PlayerService delegate;
	
	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}

	@Override
	public int getHP() {
		return delegate.getHP();
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir d, int hp) {
		delegate.init(env, x, y, d, hp);
		
	}

	@Override
	public void step() {
		delegate.step();
	}

	@Override
	public EnvironmentService getEnv() {
		return delegate.getEnv();
	}

	@Override
	public int getCol() {
		return delegate.getCol();
	}

	@Override
	public int getRow() {
		return delegate.getRow();
	}

	@Override
	public Dir getFace() {
		return delegate.getFace();
	}

	@Override
	public void init(EnvironmentService e, int x, int y, Dir d) {
		delegate.init(e, x, y, d);		
	}

	@Override
	public void forward() {
		delegate.forward();
	}

	@Override
	public void backward() {
		delegate.backward();
	}

	@Override
	public void turnL() {
		delegate.turnL();
	}

	@Override
	public void turnR() {
		delegate.turnR();
	}

	@Override
	public void strafeL() {
		delegate.strafeL();
	}

	@Override
	public void strafeR() {
		delegate.strafeR();
	}

	@Override
	public Command getLastCom() {
		return delegate.getLastCom();
	}

	@Override
	public MobService getContent(int x, int y) {
		return delegate.getContent(x, y);
	}

	@Override
	public Cell getNature(int x, int y) {
		return delegate.getNature(x, y);
	}

	@Override
	public boolean getViewable(int x, int y) {
		return delegate.getViewable(x, y);
	}

}
