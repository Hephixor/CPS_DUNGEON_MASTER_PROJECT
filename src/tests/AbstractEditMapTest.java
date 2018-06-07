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
		editmap.setNature(5, 4, Cell.EMP);
		editmap.setNature(5, 5, Cell.IN);
		editmap.setNature(5, 6, Cell.EMP);
		editmap.setNature(6, 6, Cell.DNC);
		editmap.setNature(7, 6, Cell.EMP);
		editmap.setNature(8, 6, Cell.DNO);
		editmap.setNature(9, 6, Cell.EMP);
		editmap.setNature(9, 5, Cell.DWC);
		editmap.setNature(9, 4, Cell.EMP);
		editmap.setNature(9, 3, Cell.DWO);
		editmap.setNature(9, 2, Cell.EMP);
		editmap.setNature(9, 1, Cell.OUT);
		editmap.setNature(10, 1, Cell.EMP);
		editmap.openDoor(9, 5);
		editmap.closeDoor(8, 6);
		
		Tools.printMap(editmap);
		//oracle
		//Tools.printMap(editmap);
		assertTrue(editmap.isReachable(5,4, 9,3) == true);
		assertTrue(editmap.isReady() == true);
		
		for(int x=0; x<14; x++) {
			for(int y=0; y<35; y++) {
				if(x==5 && y==4) assertTrue(editmap.getCellNature(x, y)==Cell.EMP);
				else if(x==5 && y==5) assertTrue(editmap.getCellNature(x, y)==Cell.IN);
				else if(x==5 && y==6) assertTrue(editmap.getCellNature(x, y)==Cell.EMP);
				else if(x==6 && y==6) assertTrue(editmap.getCellNature(x, y)==Cell.DNC);
				else if(x==7 && y==6) assertTrue(editmap.getCellNature(x, y)==Cell.EMP);
				else if(x==8 && y==6) assertTrue(editmap.getCellNature(x, y)==Cell.DNC);
				else if(x==9 && y==6) assertTrue(editmap.getCellNature(x, y)==Cell.EMP);
				else if(x==9 && y==5) assertTrue(editmap.getCellNature(x, y)==Cell.DWO);
				else if(x==9 && y==4) assertTrue(editmap.getCellNature(x, y)==Cell.EMP);
				else if(x==9 && y==3) assertTrue(editmap.getCellNature(x, y)==Cell.DWO);
				else if(x==9 && y==2) assertTrue(editmap.getCellNature(x, y)==Cell.EMP);
				else if(x==9 && y==1) assertTrue(editmap.getCellNature(x, y)==Cell.OUT);
				else if(x==10 && y==1) assertTrue(editmap.getCellNature(x, y)==Cell.EMP);
				else assertTrue(editmap.getCellNature(x, y)==cells_atpre[y][x]);
			}
		}
	}
}



