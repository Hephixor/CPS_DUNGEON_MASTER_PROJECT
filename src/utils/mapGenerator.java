package utils;

import java.util.Random;

public class mapGenerator {

	private Cell[][] map;
	private int width;
	private int heigth;

	public mapGenerator(int width, int heigth, int maxTunnels, int maxLength) {
		this.width = width;
		this.heigth = heigth;
		map = new Cell[width][heigth];
		createMap(maxTunnels,maxLength);
	}

	//helper function to make a two dimentional array that takes a number and the dimentions of the array
	private void createArray(Cell type) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < heigth; j++) {
				map[i][j]=type;
			}
		}
	}

	//lets create a randomly generated map for our dungeon crawler
	public Cell[][] createMap(int maxT, int maxL) {
		// let dimensions = 5, // width and height of the map
		int maxTunnels = maxT; // max number of tunnels possible
		int maxLength = maxL; // max length each tunnel can have
		createArray(Cell.WLL); // create a 2d array full of 1's
		int currentRow = (int) Math.floor(Math.random() * heigth); // our current row - start at a random spot
		int currentColumn = (int) Math.floor(Math.random() * width); // our current column - start at a random spot
		Direction lastDirection; // save the last direction we went
		Direction randomDirection = null; // next turn/direction - holds a value from directions
		
		// lets create some tunnels - while maxTunnels, dimentions, and maxLength  is greater than 0.
		while (maxTunnels > 0 && heigth>0 && width >0 && maxLength>0) {

			// lets get a random direction - until it is a perpendicular to our lastDirection
			// if the last direction = left or right,
			// then our new direction has to be up or down,
			// and vice versa
	

			Direction randDir = randomElement(Direction.values());

			int randomLength = (int) Math.ceil(Math.random() * maxLength); //length the next tunnel will be (max of maxLength)
			int tunnelLength = 0; //current length of tunnel being created
			// lets loop until our tunnel is long enough or until we hit an edge
			while (tunnelLength < randomLength) {
				boolean validMove = false;
				//break the loop if it is going out of the map
				if (((currentRow == 0) && randDir==Direction.SOUTH) || ((currentColumn == 0) && randDir==Direction.EAST) ||
						((currentRow == heigth-1 && randDir==Direction.NORTH)) ||
						((currentColumn == width - 1) && randDir==Direction.WEST)) {
					break;
				} else {
					map[currentRow][currentColumn] = Cell.EMP; //set the value of the index in map to 0 (a tunnel, making it one longer)
					currentRow += randDir.getX(); //add the value from randomDirection to row and col (-1, 0, or 1) to update our location
					currentColumn += randDir.getY();
					tunnelLength++; //the tunnel is now one longer, so lets increment that variable
					validMove=true;
				}
			

			if (validMove) { // update our variables unless our last loop broke before we made any part of a tunnel
				lastDirection = randomDirection; //set lastDirection, so we can remember what way we went
				maxTunnels--; // we created a whole tunnel so lets decrement how many we have left to create
			}
		}
		
	}
		return map; // all our tunnels have been created and our map is complete, so lets return it to our render()
	}
	
	public Cell[][] getMap(){
		return map;
	}
	

	
	public <T> T randomElement(T[] elements) {
		Random numberGenerator = new Random();
		  return elements[numberGenerator.nextInt(elements.length)];
		}
}
