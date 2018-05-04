package decorators;

import services.MapService;
import utils.Cell;

public class MapDecorator implements MapService {
	private final MapService delegate;
	
	public MapDecorator(MapService delegate) {
		this.delegate = delegate;
	}
	
	
	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
	return delegate.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return delegate.getCellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		delegate.init(w, h);
	}

	@Override
	public void openDoor(int x, int y) {
		delegate.openDoor(x, y);
	}

	@Override
	public void closeDoor(int x, int y) {
		delegate.closeDoor(x, y);
	}

}
