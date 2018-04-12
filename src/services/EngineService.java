package services;

public interface EngineService {
	
	/* Observators */
	
	public EnvironmentService envi();
	
	public EntityService[] entities(); 
	
	public EntityService getEntity(int idx);
	
	/* Constructor */
	
	public void init(EnvironmentService env);
	
	/* Operators */
	
	public EngineService removeEntity(int idx);
	
	public EngineService addEntity(EntityService ent);
	
	/**
	 * pre step() requires forall i in [0;size(Entities(E))-1], Entity::Hp(getEntity(E,i))>0
	 */
	public EngineService step();
	
	
	
		

}
