package decorators;

import services.EditMapService;
import utils.Cell;

public class EditMapDecorator extends MapDecorator implements EditMapService{
	private final EditMapService delegate;
	
	public EditMapDecorator(EditMapService delegate) {
		super(delegate);
		this.delegate = delegate;
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
}
