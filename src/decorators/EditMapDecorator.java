package decorators;

import java.util.List;

import services.EditMapService;
import utils.Cell;
import utils.Node;

public class EditMapDecorator extends MapDecorator implements EditMapService{
	private final EditMapService delegate=null;
	
	public EditMapDecorator(EditMapService delegate) {
		super(delegate);
	}
	
	@Override
	public boolean isReachable(int px, int py, int ox, int oy) {
		return delegate.isReachable(px, py, ox, oy);
	}

	@Override
	public boolean isReady() {
		return delegate.isReady();
	}

	@Override
	public EditMapService setNature(int x, int y, Cell c) {
		return delegate.setNature(x, y, c);
	}

	@Override
	public List<Node> getPath() {
		return delegate.getPath();
	}

	
}
