package de.henrikrueping.reversi;

public enum Direction {
 NW(-1,-1),N(0,-1),NE(1,-1),E(1,0),SE(1,1),S(0,1),SW(-1,1),W(-1,0);
	
	private final int dx;	
	private final int dy;
	
	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public Position apply(Position p) {
		return new Position(p.x()+dx,p.y()+dy);		
	}
}
