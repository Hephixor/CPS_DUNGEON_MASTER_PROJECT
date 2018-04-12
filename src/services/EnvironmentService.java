package services;

public interface EnvironmentService extends MapService {

	/* Observators */
	public MobService CellContent(int x, int y);
	
	/**
	 * pre CloseDoor(M,x,y) requires CellContent(M,x,y) = No
	 */
	public EnvironmentService closeDoor(int x, int y);
	
}
