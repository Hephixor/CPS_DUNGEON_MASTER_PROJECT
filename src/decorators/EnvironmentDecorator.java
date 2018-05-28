package decorators;

import services.EditMapService;
import services.EnvironmentService;
import services.MapService;
import services.MobService;

public class EnvironmentDecorator extends MapDecorator implements EnvironmentService {
	private final EnvironmentService delegate;
	
	public EnvironmentDecorator(EnvironmentService delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public void closeDoor(int x, int y) {
		delegate.closeDoor(x, y);
	}

	@Override
	public MobService getCellContent(int x, int y) {
		return delegate.getCellContent(x, y);
	}

	@Override
	public void init(EditMapService map) {
		delegate.init(map);
	}

	@Override
	public void setCellContent(int x, int y, MobService mob) {
		delegate.setCellContent(x, y, mob);
	}
	
	


}
