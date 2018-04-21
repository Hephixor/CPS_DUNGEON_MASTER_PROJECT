package utils;

enum Direction {
	NORTH(-1, 0), WEST(0, -1), EAST(0, 1), SOUTH(1, 0);

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
