package services;

import utils.Dir;

public interface MobService {

	/*observators*/
	
	public EnvironmentService getEnv();
	
	public int getCol();
	
	public int getRow();
	
	public Dir getFace();
	
	/*invariants*/
	
	/**
	 * @inv 0 <= Col(M) < Environment::Width(Envi(M))
	 * @inv 0 <= Row(M) < Environment::Height(Envi(M))
	 * @inv Environment::CellNature(Envi(M),Col(M),Row(M)) not in {WLL, DNC, DWC}
	 */
	
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
	
	/**
	 * @post Face(M)=N =>
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) in {EMP, DWO}
	 * 			and Row(M)+1 < Environment::Width(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)+1) = No
	 * 			=> Row(Forward(M)) = Row(M) + 1
	 *			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=N =>
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) not in {EMP, DWO}
	 * 			or Row(M)+1 >= Environment::Width(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)+1) != No
	 * 			=> Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=E =>
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)+1 < Environment::Height(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M)+1,Row(M)) = No
	 * 			=> Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) + 1
	 * @post Face(M)=E =>
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) not in {EMP, DNO}
	 * 			or Row(M) >= Environment::Width(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M)+1,Row(M)) != No
	 * 			=> Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=S =>
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) in {EMP, DWO}
	 * 			and Col(M)-1>= 0
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)+1) = No
	 * 			=> Row(Forward(M)) = Row(M) - 1
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=S =>
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) not in {EMP, DWO}
	 * 			or Col(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)-1) != No
	 * 			=> Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=W =>
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) in {EMP, DNO}
	 * 			and Row(M)-1 >= 0
	 * 			and Environment::CellContent(Envi(M),Col(M)-1,Row(M)) = No
	 * 			=> Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) - 1
	 * @post Face(M)=W =>
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) not in {EMP, DNO}
	 * 			or Row(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)-1) != No
	 * 			=> Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 */
	public void forward();
	
	public void backward();
	
	public void turnL();
	
	public void turnR();
	
	public void strafeL();
	
	public void strafeR();
	
}
