package utils;

enum Direction {
	NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

	private final int dx;
	private final int dy;

	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getX() { 
		return dx; 
	}

	public int getY() { 
		return dy;
	}
}
