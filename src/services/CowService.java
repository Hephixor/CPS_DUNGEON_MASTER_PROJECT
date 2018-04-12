package services;

import utils.Dir;

public interface CowService extends EntityService{
	
	/* Observators */
	
	/* Constructor */
	/**
	 * pre init(E,x,y,D,h) requires 4 ≥ h ≥ 3
	 */
	public void init(EnvironmentService env, int x,int y, Dir d, int hp);
	
	
}
