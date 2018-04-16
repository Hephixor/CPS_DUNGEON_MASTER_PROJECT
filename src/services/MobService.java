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
	 * @post Face(M)=N implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) in {EMP, DWO}
	 * 			and Row(M)+1 < Environment::Height(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)+1) = No
	 * 			implies Row(Forward(M)) = Row(M) + 1
	 *			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=N implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) not in {EMP, DWO}
	 * 			or Row(M)+1 >= Environment::Height(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)+1) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=E implies
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)+1 < Environment::Width(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M)+1,Row(M)) = No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) + 1
	 * @post Face(M)=E implies
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) not in {EMP, DNO}
	 * 			or Col(M) >= Environment::Width(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M)+1,Row(M)) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=S implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) in {EMP, DWO}
	 * 			and Row(M)-1>= 0
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)-1) = No
	 * 			implies Row(Forward(M)) = Row(M) - 1
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=S implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) not in {EMP, DWO}
	 * 			or Row(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)-1) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=W implies
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)-1 >= 0
	 * 			and Environment::CellContent(Envi(M),Col(M)-1,Row(M)) = No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) - 1
	 * @post Face(M)=W implies
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) not in {EMP, DNO}
	 * 			or Col(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M)-1,Row(M)) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 */
	public void forward();
	
	/**
	 * @post Face(M)=S implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) in {EMP, DWO}
	 * 			and Row(M)+1 < Environment::Height(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)+1) = No
	 * 			implies Row(Forward(M)) = Row(M) + 1
	 *			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=S implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) not in {EMP, DWO}
	 * 			or Row(M)+1 >= Environment::Height(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)+1) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=W implies
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)+1 < Environment::Width(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M)+1,Row(M)) = No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) + 1
	 * @post Face(M)=W implies
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) not in {EMP, DNO}
	 * 			or Col(M) >= Environment::Width(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M)+1,Row(M)) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=N implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) in {EMP, DWO}
	 * 			and Row(M)-1>= 0
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)-1) = No
	 * 			implies Row(Forward(M)) = Row(M) - 1
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=N implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) not in {EMP, DWO}
	 * 			or Row(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)-1) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=E implies
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)-1 >= 0
	 * 			and Environment::CellContent(Envi(M),Col(M)-1,Row(M)) = No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) - 1
	 * @post Face(M)=E implies
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) not in {EMP, DNO}
	 * 			or Col(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M)-1,Row(M)) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 */
	public void backward();
	
	/**
	 * @post Face(M)=N implies Face(TurnLeft(M))=W
	 * @post Face(M)=W implies Face(TurnLeft(M))=S
	 * @post Face(M)=S implies Face(TurnLeft(M))=E
	 * @post Face(M)=E implies Face(TurnLeft(M))=N
	 */
	public void turnL();
	
	/**
	 * @post Face(M)=N implies Face(TurnLeft(M))=E
	 * @post Face(M)=W implies Face(TurnLeft(M))=N
	 * @post Face(M)=S implies Face(TurnLeft(M))=W
	 * @post Face(M)=E implies Face(TurnLeft(M))=S
	 */
	public void turnR();
	
	/**
	 * @post Face(M)=E implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) in {EMP, DWO}
	 * 			and Row(M)+1 < Environment::Height(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)+1) = No
	 * 			implies Row(Forward(M)) = Row(M) + 1
	 *			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=E implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) not in {EMP, DWO}
	 * 			or Row(M)+1 >= Environment::Height(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)+1) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=S implies
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)+1 < Environment::Width(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M)+1,Row(M)) = No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) + 1
	 * @post Face(M)=S implies
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) not in {EMP, DNO}
	 * 			or Col(M) >= Environment::Width(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M)+1,Row(M)) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=W implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) in {EMP, DWO}
	 * 			and Row(M)-1>= 0
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)-1) = No
	 * 			implies Row(Forward(M)) = Row(M) - 1
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=W implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) not in {EMP, DWO}
	 * 			or Row(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)-1) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=N implies
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)-1 >= 0
	 * 			and Environment::CellContent(Envi(M),Col(M)-1,Row(M)) = No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) - 1
	 * @post Face(M)=N implies
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) not in {EMP, DNO}
	 * 			or Col(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M)-1,Row(M)) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 */
	public void strafeL();
	
	/**
	 * @post Face(M)=W implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) in {EMP, DWO}
	 * 			and Row(M)+1 < Environment::Height(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)+1) = No
	 * 			implies Row(Forward(M)) = Row(M) + 1
	 *			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=W implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)+1) not in {EMP, DWO}
	 * 			or Row(M)+1 >= Environment::Height(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)+1) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=N implies
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)+1 < Environment::Width(Envi(M))
	 * 			and Environment::CellContent(Envi(M),Col(M)+1,Row(M)) = No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) + 1
	 * @post Face(M)=N implies
	 * 			Environment::CellNature(Envi(M),Col(M)+1,Row(M)) not in {EMP, DNO}
	 * 			or Col(M) >= Environment::Width(Envi(M))
	 * 			or Environment::CellContent(Envi(M),Col(M)+1,Row(M)) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=E implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) in {EMP, DWO}
	 * 			and Row(M)-1>= 0
	 * 			and Environment::CellContent(Envi(M),Col(M),Row(M)-1) = No
	 * 			implies Row(Forward(M)) = Row(M) - 1
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=E implies
	 * 			Environment::CellNature(Envi(M),Col(M),Row(M)-1) not in {EMP, DWO}
	 * 			or Row(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M),Row(M)-1) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 * @post Face(M)=S implies
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) in {EMP, DNO}
	 * 			and Col(M)-1 >= 0
	 * 			and Environment::CellContent(Envi(M),Col(M)-1,Row(M)) = No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M) - 1
	 * @post Face(M)=S implies
	 * 			Environment::CellNature(Envi(M),Col(M)-1,Row(M)) not in {EMP, DNO}
	 * 			or Col(M)-1 < 0
	 * 			or Environment::CellContent(Envi(M),Col(M)-1,Row(M)) != No
	 * 			implies Row(Forward(M)) = Row(M)
	 * 			   and Col(Forward(M)) = Col(M)
	 */
	public void strafeR();
	
}
