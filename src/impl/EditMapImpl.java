package impl;

import services.EditMapService;
import utils.Cell;
import utils.Node;
import utils.Pathfinder;

public class EditMapImpl extends MapImpl implements EditMapService{
	//List<Node> path;
	
	@Override
	public void init(int w, int h) {
		super.init(w,h);
	}

	@Override
	public boolean isReachable(int px, int py, int ox, int oy) {
		Pathfinder pf = new Pathfinder(this,px,py,ox,oy);
		return pf.hasPath();
	}

	@Override
	public boolean isReady() {
		boolean inExists = false;
		boolean outExists = false;
		boolean doorOK = true;
		int xin=-1, yin=-1, xout=-1, yout=-1;
		
		for(int i=0; i<getWidth(); i++) {
			for(int j=0; j<getHeight(); j++) {
				Cell na = getCellNature(i, j);
				
				if(na==Cell.IN) {
					inExists = true;
					xin = i;
					yin = j;
				}
				if(na==Cell.OUT) {
					outExists = true;
					xout = i;
					yout = j;
				}
				
				if(na== Cell.DNC || na==Cell.DNO) {
					if( getCellNature(i+1,j)!=Cell.EMP || getCellNature(i-1,j)!=Cell.EMP) doorOK = false;
					if( getCellNature(i,j+1)!=Cell.WLL || getCellNature(i,j-1)!=Cell.WLL) doorOK = false;
				}
				
				if(na== Cell.DWC || na==Cell.DWO) {
					if( getCellNature(i+1,j)!=Cell.WLL || getCellNature(i-1,j)!=Cell.WLL) doorOK = false;
					if( getCellNature(i,j+1)!=Cell.EMP || getCellNature(i,j-1)!=Cell.EMP) doorOK = false;
				}
				
				if(!doorOK) break;
			}
		}
		
		Pathfinder pf = new Pathfinder(this, xin, yin, xout, yout);
		return inExists && outExists && doorOK && pf.hasPath();
	}

	@Override
	public EditMapService setNature(int x, int y, Cell c) {
		cells[y][x]=c;
		return this;
	}
	
	public Node getIn(){
		return in;
	}
	
	public Node getOut(){
		return out;
	}

}
