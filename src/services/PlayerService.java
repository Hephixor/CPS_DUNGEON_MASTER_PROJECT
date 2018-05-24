package services;

import utils.Cell;
import utils.Command;

public interface PlayerService extends EntityService{
	/* Observators */
	
	public Command getLastCom();
	
	//pre Content(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
	public MobService getContent(int x, int y);
	
	//pre Nature(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
	public Cell getNature(int x, int y);
	
	//pre Viewable(P,x,y) requires x ∈ {0, width(M)}and y ∈ {0,heigth(M)}
	public boolean getViewable(int x, int y);
	
	public void setLastCom(Command com);
	
}
