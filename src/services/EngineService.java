package services;

import java.util.List;

public interface EngineService {
	
	/* Observators */
	
	public EnvironmentService envi();
	
	public List<EntityService> entities(); 
	
	public EntityService getEntity(int idx);
	
	/* Constructor */
	
	public void init(EnvironmentService env);
	
	/* Operators */
	
	public void removeEntity(int idx);
	
	public void addEntity(EntityService ent);
	
	/**
	 * pre step() requires forall i in [0;size(Entities(E))-1], Entity::Hp(getEntity(E,i))>0
	 */
	public void step();
	
	
	
		

}
