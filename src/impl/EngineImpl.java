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
	public ArrayList<EntityService> getEntities() {
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
	}

	@Override
	public void removeEntity(int idx) {
		ents.remove(idx);
	}

	@Override
	public void addEntity(EntityService ent) {
		env.setCellContent(ent.getCol(), ent.getRow(), ent);
		ents.add(ent);
	}

	@Override
	public void step() {
		EntityService enttoremove=null;
		
		for (EntityService entityService : ents) {
			entityService.step();
		}
		for (EntityService entityService : ents) {

			if(entityService.getHP()==0) {
				System.out.println(entityService + " is dead");
				enttoremove = entityService;
				env.setCellContent(enttoremove.getCol(), enttoremove.getRow(), null);
			}
		}
		if(enttoremove!=null)
			ents.remove(enttoremove);		
		
	}

}
