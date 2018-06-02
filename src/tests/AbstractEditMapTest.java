package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import errors.PreconditionError;
import services.EditMapService;
import utils.Cell;
import utils.Tools;

public abstract class AbstractEditMapTest {

	private EditMapService editmap;
	
	protected AbstractEditMapTest() {
		editmap = null;
	}

	protected final EditMapService getEditMap() {
		return editmap;
	}

	protected final void setEditMap(EditMapService editmap) {
		this.editmap = editmap;
	}
	
	@Before
	public abstract void beforeTest();
	
	@After
	public final void afterTest() {
		editmap = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	//TODO
	
	/* isReachable, setNature*/
	
	/* isReachable*/
	
	@Test
	public void preIsReachablePositif() {
		//init
		editmap.init(14, 35);
		editmap.setNature(5, 5, Cell.OUT);
		editmap.setNature(7, 5, Cell.DWC);			
		
		//operation
		try {
			editmap.isReachable(5,5, 7,5);
		}
		//oracle
		catch(PreconditionError e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void preIsReachableNegatif() {
		//init
		editmap.init(14, 35);
		editmap.setNature(5, 5, Cell.OUT);
		editmap.setNature(7, 5, Cell.DWC);			
		
		//operation
		try {
			editmap.isReachable(5, 5, 15, 5);

			//probleme
			fail("preIsReachableNegatif");
		}
		//oracle
		catch(PreconditionError e) {
			//ok
		}
	}
	
	/* setNature */
	
	@Test
	public void preSetNaturePositif() {
		//init
		editmap.init(14, 35);
		
		//operation
		try {
			editmap.setNature(3, 29, Cell.EMP);
		}
		//oracle
		catch(PreconditionError e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void preSetNatureNegatif() {
		//init
		editmap.init(14, 35);
		
		//operation
		try {
			editmap.setNature(15, -4, Cell.EMP);

			//probleme
			fail("preSetNatureNegatif");
		}
		//oracle
		catch(PreconditionError e) {
			//ok
		}
	}


	/* ========== COUVERTURE TRANSITIONS ========== */
	
	/* init, setNature */
	
	/* impossible de rendre deterministe sans changer l'etat post transition de init()*/
	//public void transInit() {
	//}
	
	/* impossible de rendre deterministe sans appeler plusieurs fois setNature()*/
	//public void transSetNature() {
	//}
	
	/* ========== SCENARIOS ========== */
	
	@Test
	public void scenario1() {
		//init
		editmap.init(14, 35);
		
		for(int i=0; i<14; i++) {
			for(int j=0; j<35; j++) {
				editmap.setNature(i, j, Cell.EMP);
			}
		}

		for(int i=0; i<14; i++) {
			for(int j=0; j<35; j++) {
				editmap.setNature(i, j, Cell.WLL);
			}
		}
		
		
		//captures
		Cell[][] cells_atpre = new Cell[35][14];
		for(int x=0; x<14; x++) {
			for(int y=0; y<35; y++) {
				cells_atpre[y][x] = editmap.getCellNature(x, y);
			}
		}
				
		
		//operateurs

		editmap.setNature(5, 5, Cell.IN);
		editmap.setNature(5, 6, Cell.EMP);
		editmap.setNature(6, 6, Cell.DNC);
		editmap.setNature(7, 6, Cell.DNO);
		editmap.setNature(8, 6, Cell.EMP);
		editmap.setNature(8, 5, Cell.DWC);
		editmap.setNature(8, 4, Cell.DWO);
		editmap.setNature(8, 3, Cell.OUT);
		editmap.setNature(9, 3, Cell.EMP);
		
		//oracle
		Tools.printMap(editmap);
		assertTrue(editmap.isReachable(5,5, 9,3) == true);
	}
}



