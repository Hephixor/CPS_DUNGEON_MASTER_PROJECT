package decorators;

import services.EnvironmentService;
import services.MobService;
import utils.Dir;

public class MobDecorator implements MobService {
	private final MobService delegate;

	public MobDecorator(MobService delegate) {
		this.delegate = delegate;
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
}
