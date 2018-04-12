package services;

import utils.Cell;
import utils.Command;

public interface PlayerService extends EntityService{
	/* Observators */
	
	public Command Lastcom();
	
	//pre Content(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
	public MobService Content(int x, int y);
	
	//pre Nature(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
	public Cell Nature(int x, int y);
	
	
	//pre Viewable(P,x,y) requires x ∈ {0, width(M)}and y ∈ {0,heigth(M)}
	public boolean Viewable(int x, int y);

}
