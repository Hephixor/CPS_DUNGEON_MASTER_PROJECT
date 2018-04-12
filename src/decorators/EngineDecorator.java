package decorators;

import services.EngineService;
import services.EntityService;
import services.EnvironmentService;

public class EngineDecorator implements EngineService{
	private final EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public EnvironmentService envi() {
		return delegate.envi();
	}

	@Override
	public EntityService[] entities() {
		return delegate.entities();
	}

	@Override
	public EntityService getEntity(int idx) {
		return delegate.getEntity(idx);
	}

	@Override
	public void init(EnvironmentService env) {
		delegate.init(env);
	}

	@Override
	public EngineService removeEntity(int idx) {
		return delegate.removeEntity(idx);
	}

	@Override
	public EngineService addEntity(EntityService ent) {
		return delegate.addEntity(ent);
	}

	@Override
	public EngineService step() {
		return delegate.step();
	}
	

}
