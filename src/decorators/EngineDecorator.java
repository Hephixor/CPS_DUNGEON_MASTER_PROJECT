package decorators;

import java.util.ArrayList;
import java.util.List;

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
	public List<EntityService> entities() {
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
	public void removeEntity(int idx) {
		delegate.removeEntity(idx);
	}

	@Override
	public void addEntity(EntityService ent) {
		delegate.addEntity(ent);
	}

	@Override
	public void step() {
		delegate.step();
	}
	

}
