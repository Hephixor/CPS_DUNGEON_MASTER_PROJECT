package services;

import utils.Option;

public interface EnvironmentService extends MapService {

	/* Observators */
	public Option CellContent(int x, int y);
	
	/**
	 * pre CloseDoor(M,x,y) requires CellContent(M,x,y) = No
	 */
	public EnvironmentService closeDoor(int x, int y);
	
}
