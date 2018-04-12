package decorators;

import services.EnvironmentService;
import services.MobService;

public class EnvironmentDecorator extends MapDecorator implements EnvironmentService {
	private final EnvironmentService delegate = null;
	
	public EnvironmentDecorator(EnvironmentService delegate) {
		super(delegate);
	}
	
	public EnvironmentService closeDoor(int x, int y) {
		return delegate.closeDoor(x, y);
	}

	@Override
	public MobService CellContent(int x, int y) {
		return delegate.CellContent(x, y);
	}


}
