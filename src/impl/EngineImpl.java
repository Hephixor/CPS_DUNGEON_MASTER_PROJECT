package impl;

import services.EngineService;
import services.EntityService;
import services.EnvironmentService;

public class EngineImpl implements EngineService{

	EnvironmentService env;
	EntityService[] ents;
	
	
	@Override
	public EnvironmentService envi() {
		return env;
	}

	@Override
	public EntityService[] entities() {
		return ents;
	}

	@Override
	public EntityService getEntity(int idx) {
		return ents[idx];
	}

	@Override
	public void init(EnvironmentService env) {
		this.env = env;
		//parametrer le nombre d'entities
	}

	@Override
	public EngineService removeEntity(int idx) {
		return null;
	}

	@Override
	public EngineService addEntity(EntityService ent) {
		return null;
	}

	@Override
	public EngineService step() {
		return null;
	}

}
