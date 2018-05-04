package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import errors.PreconditionError;
import services.EditMapService;
import services.MapService;
import utils.Cell;

public abstract class AbstractMapTest {
	
	private MapService map;
	private EditMapService editmap;
	
	protected AbstractMapTest() {
		map = null;
		editmap = null;
	}

	protected final MapService getMap() {
		return map;
	}

	protected final void setMap(MapService map) {
		this.map = map;
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
		map = null;
		editmap = null;
		this.toString();
	}
	
	/* ========== COUVERTURE PRECONDITIONS ========== */
	
	/*getcellnature, init, opendoor, closedoor*/
	
	/*getCellNature*/
	
	@Test
	public void preGetCellNaturePositif() {
		//init
		map.init(14, 35);
		
		//operation
		try {
			map.getCellNature(3, 29);
		}
		//oracle
		catch(PreconditionError e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void preGetCellNatureNegatif() {
		//init
		map.init(14, 35);
		
		//operation
		try {
			map.getCellNature(15, -4);

			//probleme
			fail("preGetCellNatureNegatif");
		}
		//oracle
		catch(PreconditionError e) {
			//ok
		}
	}
	
	/*init*/
	
	@Test
	public void preInitPositif() {
		//init
		
		//operation
		try {
			map.init(14, 35);
		}
		//oracle
		catch(PreconditionError e) {
			fail(e.toString());
		}
		
	}
	
	@Test
	public void preInitNegatif() {
		//init
		
		//operation
		try {
			map.init(0, 35);
			
			//probleme
			fail("preInitNegatif");
		}
		//oracle
		catch(PreconditionError e) {
			//ok
		}
	}
	
	@Test
	public void preOpenDoorPositif() {
		/*
		//init
		map.init(14, 35);
		
		boolean found = false;
		int xDoor = -1;
		int yDoor = -1;
		
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				if(Arrays.asList(Cell.DNC, Cell.DWC).contains(map.getCellNature(x, y))) {
					xDoor = x;
					yDoor = y;
					found = true;
					break;
				}
				if(Arrays.asList(Cell.DNO, Cell.DWO).contains(map.getCellNature(x, y))) {
					map.closeDoor(x, y);
					xDoor = x;
					yDoor = y;
					found = true;
					break;
				}
			}
			if(found) break;
		}

		if(!found) throw new Error("no existing Door in the map");
		*/
		
		//init
		editmap.init(14, 35);
		editmap.setNature(10, 10, Cell.DNC);
		editmap.setNature(5, 5, Cell.DWC);
		
		//operation
		try {
			map.openDoor(10, 10);
			//map.openDoor(5, 5);
		}
		//oracle
		catch(PreconditionError e) {
			fail(e.toString());
		}
		
		
	}
	
	@Test
	public void preOpenDoorNegatif() {
		//init 
		editmap.init(14, 35);
		editmap.setNature(10, 10, Cell.IN);
		
		
		//operation
		try {
			map.openDoor(10, 10);
			
			//probleme
			fail("preOpenDoorNegatif");
		}
		//oracle
		catch(PreconditionError e) {
			//ok
		}
	}
	
	/*closeDoor*/
	
	@Test
	public void preCloseDoorPositif() {
		//init
		editmap.init(14, 35);
		editmap.setNature(10, 10, Cell.DNO);
		editmap.setNature(5, 5, Cell.DWO);

		
		//operation
		try {
			map.closeDoor(10, 10);
			map.closeDoor(5, 5);
		}
		//oracle
		catch(PreconditionError e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void preCloseDoorNegatif() {
		//init 
		editmap.init(14, 35);
		editmap.setNature(10, 10, Cell.IN);
		
		
		//operation
		try {
			map.closeDoor(10, 10);

			//probleme
			fail("preCloseDoorNegatif");
		}
		//oracle
		catch(PreconditionError e) {
			//ok
		}
	}
	
	/* ========== COUVERTURE TRANSITIONS ========== */

	private void assertInv() {
		assertTrue(true);
	}
	
	//TODO
	
	/*init*/
	
	@Test
	public void transInit() {
		//init
		
		//operation
		map.init(14, 35);
		
		//oracle
		assertInv();
		assertTrue(map.getWidth()==14);
		assertTrue(map.getHeight()==35);
	}
	
	@Test
	public void transOpenDoor() {
		//init
		map.init(14, 35);
		
		boolean found = false;
		int xDoor = -1;
		int yDoor = -1;
		
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				if(Arrays.asList(Cell.DNC, Cell.DWC).contains(map.getCellNature(x, y))) {
					xDoor = x;
					yDoor = y;
					found = true;
					break;
				}
				if(Arrays.asList(Cell.DNO, Cell.DWO).contains(map.getCellNature(x, y))) {
					map.closeDoor(x, y);
					xDoor = x;
					yDoor = y;
					found = true;
					break;
				}
			}
			if(found) break;
		}

		if(!found) throw new Error("no existing Door in the map");
		
		//capture
		Cell cellNat_atpre = map.getCellNature(xDoor, yDoor);
		Cell[][] cells_atpre = new Cell[35][14];
		
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				cells_atpre[y][x] = map.getCellNature(x, y);
			}
		}
		
		//operation
		map.openDoor(xDoor, yDoor);
		
		//oracle
		assertInv();
		
		if(cellNat_atpre == Cell.DWC)
			assertTrue(map.getCellNature(xDoor, yDoor) == Cell.DWO);
		
		if(cellNat_atpre == Cell.DNC)
			assertTrue(map.getCellNature(xDoor, yDoor) == Cell.DNO);	
		
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				if(x!=xDoor && y!=yDoor) {
					assertTrue(map.getCellNature(x, y) == cells_atpre[y][x]);
				}
			}
		}
	}
	
	@Test
	public void transCloseDoor() {
		//init
		map.init(14, 35);
		
		boolean found = false;
		int xDoor = -1;
		int yDoor = -1;
		
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				if(Arrays.asList(Cell.DNC, Cell.DWC).contains(map.getCellNature(x, y))) {
					map.openDoor(x, y);
					xDoor = x;
					yDoor = y;
					found = true;
					break;
				}
				if(Arrays.asList(Cell.DNO, Cell.DWO).contains(map.getCellNature(x, y))) {
					xDoor = x;
					yDoor = y;
					found = true;
					break;
				}
			}
			if(found) break;
		}

		if(!found) throw new Error("no existing Door in the map");
		
		//capture
		Cell cellNat_atpre = map.getCellNature(xDoor, yDoor);
		Cell[][] cells_atpre = new Cell[35][14];
		
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				cells_atpre[y][x] = map.getCellNature(x, y);
			}
		}
		
		//operation
		map.closeDoor(xDoor, yDoor);
		
		//oracle
		assertInv();
		
		if(cellNat_atpre == Cell.DWC)
			assertTrue(map.getCellNature(xDoor, yDoor) == Cell.DWO);
		
		if(cellNat_atpre == Cell.DNC)
			assertTrue(map.getCellNature(xDoor, yDoor) == Cell.DNO);	
		
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				if(x!=xDoor && y!=yDoor) {
					assertTrue(map.getCellNature(x, y) == cells_atpre[y][x]);
				}
			}
		}
	}
	
}























