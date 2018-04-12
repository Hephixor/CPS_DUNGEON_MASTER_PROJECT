package services;

import utils.Dir;

public interface EntityService extends MobService{
	
	/* Observators */
	public int getHP();
	
	/* Constructors */
	/**
	 * pre init(E,x,y,D,h) requires h > 0
	 */
	public void init(EnvironmentService env, int x, int y, Dir d, int hp);
	
	/* Operators */
	public EntityService step();
}
