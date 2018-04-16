package services;

import utils.Dir;

public interface CowService extends EntityService{
	
	/* Observators */
	
	/* Constructor */
	
	/**
	 * @pre 4 >= hp >= 3
	 */
	public void init(EnvironmentService env, int x,int y, Dir d, int hp);
	
	/* Operators */
	
	/**
	 * @post Col(M)-1 <= Col(step(M)) <= Col(M)+1
	 * @post Row(M)-1 <= Row(step(M)) <= Row(M)+1
	 */
	public void step();
}
