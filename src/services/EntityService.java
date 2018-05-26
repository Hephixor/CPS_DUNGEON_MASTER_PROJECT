package services;

import utils.Dir;

public interface EntityService extends MobService, Cloneable{
	
	/* Observators */
	public int getHP();
	
	/* Constructors */
	
	/**
	 * @pre hp > 0
	 * @post Hp(init(E,x,y,D,hp)) = hp
	 */
	public void init(EnvironmentService env, int x, int y, Dir d, int hp);
	
	/* Operators */
	
	public void step();
	public void hit();
	public void takeHit();

}
