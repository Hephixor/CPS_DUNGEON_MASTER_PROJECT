package utils;

import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {
	private Cell[][] map;
	private int width;
	private int heigth;

	public MapGenerator(int width, int heigth, int maxTunnels, int maxLength) {
		//	System.out.println("MaxTun: "+maxTunnels+" MaxLen: "+maxLength);
		this.width = width;
		this.heigth = heigth;
		map = new Cell[heigth][width];
		createMap(maxTunnels,maxLength);
		placeDoors();
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
	public void createMap(int maxT, int maxL) {
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
			Direction randDir = null;
			boolean validDir;

			do {
				validDir = true;
				randDir = Tools.randomElement(Direction.values());

				if(!(lastDir==null)){
					//90° turns only
					//					switch(lastDir) {
					//
					//					case EAST:
					//						if(randDir == Direction.NORTH || randDir == Direction.SOUTH) {
					//							validDir = true;
					//						}
					//						break;
					//					case WEST:
					//						if(randDir == Direction.NORTH || randDir == Direction.SOUTH) {
					//							validDir = true;
					//						}
					//						break;
					//					case NORTH:
					//						if(randDir == Direction.EAST || randDir == Direction.WEST) {
					//							validDir = true;
					//						}
					//						break;
					//
					//					case SOUTH:
					//						if(randDir == Direction.EAST || randDir == Direction.WEST) {
					//							validDir = true;
					//						}
					//						break;
					//					default:
					//						break;
					//					}

					//System.out.println("LastDir : "+lastDir+" | NewDir : "+randDir);
					if(randDir == lastDir) {
						validDir = false;
					}
				}

			}while(!validDir);


			int randomLength = (int) Math.ceil(Math.random() * maxLength); //length the next tunnel will be (max of maxLength)
			int tunnelLength = 0; //current length of tunnel being created
			// lets loop until our tunnel is long enough or until we hit an edge
			boolean validMove = false;		
			while (tunnelLength < randomLength) {
				validMove = false;

								// Arena mode (bigger rooms)
//								if(
//										lastDir==Direction.EAST && randDir == Direction.EAST 
//										|| lastDir==Direction.EAST && randDir == Direction.WEST 
//										|| lastDir==Direction.WEST && randDir == Direction.EAST 
//										|| lastDir==Direction.WEST && randDir == Direction.WEST 
//										|| lastDir==Direction.NORTH && randDir == Direction.NORTH 
//										|| lastDir==Direction.NORTH && randDir == Direction.SOUTH 
//										|| lastDir==Direction.SOUTH && randDir == Direction.NORTH 
//										|| lastDir==Direction.SOUTH && randDir == Direction.SOUTH) {
//									break;
//								}

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



			}

			if (validMove) { // update our variables unless our last loop broke before we made any part of a tunnel
				lastDir = randDir;
				maxTunnels--; // we created a whole tunnel so lets decrement how many we have left to create
			}

		}
		map[currentRow][currentColumn]=Cell.OUT;

	}

	public Cell[][] getMap(){
		return map;
	}

	public void placeDoors() {
		//Calcul nombre porte à placer
		int nbCells = width*heigth;
		int nbDoors = (nbCells  / 200);

		//utils
		ArrayList<Node> emp = Tools.getEmp(map);
		Random numberGenerator = new Random();
		Node randNode;

		//Placement portes
		do {
			randNode=Tools.randomElement(emp);
			//Choix aléatoire porte DN ou DW
			int rand = numberGenerator.nextInt(2);
			if(rand == 0) {
				if(
					map[randNode.y][randNode.x-1]==Cell.EMP && 
					map[randNode.y][randNode.x+1]==Cell.EMP &&
					map[randNode.y-1][randNode.x]==Cell.WLL && 
					map[randNode.y+1][randNode.x]==Cell.WLL
				) {
					map[randNode.y][randNode.x]=Cell.DNC;
			//		System.out.println("placing DNC at ["+randNode.y+","+randNode.x+"]");
					nbDoors--;
				}
			}
			else {
				if(
					map[randNode.y][randNode.x-1]==Cell.WLL && 
					map[randNode.y][randNode.x+1]==Cell.WLL &&
					map[randNode.y-1][randNode.x]==Cell.EMP && 
					map[randNode.y+1][randNode.x]==Cell.EMP
				) {
					map[randNode.y][randNode.x]=Cell.DWC;
			//		System.out.println("placing DWC at ["+randNode.y+","+randNode.x+"]");
					nbDoors--;
				}
			}
		}while(nbDoors>0);

	}
}
