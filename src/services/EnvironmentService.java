package services;

public interface EnvironmentService extends MapService {

	/* Observators */
	public MobService getCellContent(int x, int y);
	
	/**
	 * pre CloseDoor(M,x,y) requires CellContent(M,x,y) = No
	 */
	public EnvironmentService closeDoor(int x, int y);
	
	public void setCellContent(int x, int y, MobService mob);
	
	public void init(MapService map);
	
}
