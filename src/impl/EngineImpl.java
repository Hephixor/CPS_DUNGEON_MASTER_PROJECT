package impl;

import java.util.ArrayList;

import services.EngineService;
import services.EntityService;
import services.EnvironmentService;

public class EngineImpl implements EngineService{

	EnvironmentService env;
	ArrayList<EntityService> ents;
	
	
	@Override
	public EnvironmentService envi() {
		return env;
	}

	@Override
	public ArrayList<EntityService> entities() {
		return ents;
	}

	@Override
	public EntityService getEntity(int idx) {
		return ents.get(idx);
	}

	@Override
	public void init(EnvironmentService env) {
		this.env = env;
		this.ents = new ArrayList<EntityService>();
		//parametrer le nombre d'entities
	}

	@Override
	public void removeEntity(int idx) {
		ents.remove(idx);
	}

	@Override
	public void addEntity(EntityService ent) {
		ents.add(ent);
		env.setCellContent(ent.getCol(), ent.getRow(), ent);
	}

	@Override
	public void step() {
		
		for (EntityService entityService : ents) {
			entityService.step();
		}
	}

}
