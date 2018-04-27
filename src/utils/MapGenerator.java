package utils;

import java.util.Random;

public class MapGenerator {
	public static int single = 0;
	private Cell[][] map;
	private int width;
	private int heigth;

	public MapGenerator(int width, int heigth, int maxTunnels, int maxLength) {
		System.out.println("MaxTun: "+maxTunnels+" MaxLen: "+maxLength);
		this.width = width;
		this.heigth = heigth;
		map = new Cell[heigth][width];
		createMap(maxTunnels,maxLength);
	}

	//Initializer to fill map with specific cell type
	private void createArray(Cell type) {
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j]=type;
			}
		}
	}

	//Walk the map to create corridors
	public Cell[][] createMap(int maxT, int maxL) {
		single++;
		int maxTunnels = maxT; 
		int maxLength = maxL; 
		createArray(Cell.WLL);
		int currentRow;
		int currentColumn;
		Direction lastDir = null;
		
		do {
		currentRow = (int) Math.floor(Math.random() * heigth); // our current row - start at a random spot
		currentColumn = (int) Math.floor(Math.random() * width); // our current column - start at a random spot
		}while(currentRow==0 || currentColumn==0 || currentRow== heigth-1 || currentColumn==width-1);
		
		map[currentRow][currentColumn]=Cell.IN;
		//System.out.println("In : " + currentColumn + ", " + currentRow);
		
	
		while (maxTunnels > 0  && maxLength>0) {

			// lets get a random direction - until it is a perpendicular to our lastDirection
			// if the last direction = left or right,
			// then our new direction has to be up or down,
			// and vice versa


			Direction randDir = Tools.randomElement(Direction.values());

			int randomLength = (int) Math.ceil(Math.random() * maxLength); //length the next tunnel will be (max of maxLength)
			int tunnelLength = 0; //current length of tunnel being created
			// lets loop until our tunnel is long enough or until we hit an edge
			while (tunnelLength < randomLength) {
				boolean validMove = false;				
				
				//Node U-turn mode
				if(
						lastDir==Direction.EAST && randDir == Direction.WEST 
						|| lastDir==Direction.WEST && randDir == Direction.EAST 
						|| lastDir==Direction.NORTH && randDir == Direction.SOUTH 
						|| lastDir==Direction.SOUTH && randDir == Direction.NORTH)
				{
					break;
				}
				
				
//				//No corridor mode -> Arena mode
//				if(
//						lastDir==Direction.EAST && randDir == Direction.EAST 
//						|| lastDir==Direction.EAST && randDir == Direction.WEST 
//						|| lastDir==Direction.WEST && randDir == Direction.EAST 
//						|| lastDir==Direction.WEST && randDir == Direction.WEST 
//						|| lastDir==Direction.NORTH && randDir == Direction.NORTH 
//						|| lastDir==Direction.NORTH && randDir == Direction.SOUTH 
//						|| lastDir==Direction.SOUTH && randDir == Direction.NORTH 
//						|| lastDir==Direction.SOUTH && randDir == Direction.SOUTH) {
//					break;
//				}
				
				//break the loop if it is going out of the map or the surrounding wall
				if (
						((currentRow == 1) && randDir==Direction.NORTH) 
						|| ((currentColumn == 1) && randDir==Direction.WEST) 
						|| ((currentRow == heigth-2 && randDir==Direction.SOUTH)) 
						|| ((currentColumn == width - 2) && randDir==Direction.EAST)) {
					break;
				} 
				
				//break if erasing entry
//				if(map[currentRow][currentColumn]==Cell.IN) {
//					break;
//				}
				
				else {
					if(!(map[currentRow][currentColumn]==Cell.IN)) {
					map[currentRow][currentColumn] = Cell.EMP; //set the value of the index in map to 0 (a tunnel, making it one longer)
					}
					currentRow += randDir.getX(); //add the value from randomDirection to row and col (-1, 0, or 1) to update our location
					currentColumn += randDir.getY();
					tunnelLength++; //the tunnel is now one longer, so lets increment that variable
					validMove=true;
				}


				if (validMove) { // update our variables unless our last loop broke before we made any part of a tunnel
					lastDir = randDir;
					maxTunnels--; // we created a whole tunnel so lets decrement how many we have left to create
				}
			}

		}
		map[currentRow][currentColumn]=Cell.OUT;
		return map; 
	}

	public Cell[][] getMap(){
		return map;
	}
}
