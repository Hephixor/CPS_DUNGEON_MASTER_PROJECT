package decorators;

import services.CowService;
import services.EnvironmentService;
import services.MapService;
import services.MobService;

public class EnvironmentDecorator extends MapDecorator implements EnvironmentService {
	private final EnvironmentService delegate;
	
	public EnvironmentDecorator(EnvironmentService delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public EnvironmentService closeDoor(int x, int y) {
		return delegate.closeDoor(x, y);
	}

	@Override
	public MobService getCellContent(int x, int y) {
		return delegate.getCellContent(x, y);
	}

	@Override
	public void init(MapService map) {
		delegate.init(map);
	}
	
	


}
