package de.henrikrueping.reversi;

public enum Disc {
	
	B{public Disc getOpponent() {
		return W;
	} },W{public Disc getOpponent() {
		return B;
	} },EMPTY;
	
	public Disc getOpponent() {
		throw new UnsupportedOperationException();
	}
}
