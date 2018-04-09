package services;

import utils.Dir;

public interface MobService {

	/*observators*/
	
	public EnvironmentService getEnv();
	
	public int getCol();
	
	public int getRow();
	
	public Dir getFace();
	
	
	/*constructors*/
	
	/**
	 * @pre s 0 <= x < Environment::Width(E) and 0 <= y < Environment::Height(E)
	 * @post Col(init(E,x,y,D)) = x
	 * @post Row(init(E,x,y,D)) = y
	 * @post Face(init(E,x,y,D)) = D
	 * @post Envi(init(E,x,y,D)) = E
	 */
	public void init(EnvironmentService e, int x, int y, Dir d);
	
	
	/*operators*/
	/* TODO VERSION PARTIELLE DES POSTCONDITIONS, A COMPLETER*/
	
	public void forward();
	
	public void backward();
	
	public void turnL();
	
	public void turnR();
	
	public void strafeL();
	
	public void strafeR();
	
	
	/*invariants*/
	
	/**
	 * @inv 0 <= Col(M) < Environment::Width(Envi(M))
	 * @inv 0 <= Row(M) < Environment::Height(Envi(M))
	 * @inv Environment::CellNature(Envi(M),Col(M),Row(M)) not in {WLL, DNC, DWC}
	 */
	
}
