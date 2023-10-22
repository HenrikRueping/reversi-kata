package de.henrikrueping.reversi;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import de.henrikrueping.reversi.exception.GameException;

public class ReversiGame {

	private Disc currentPlayer;
	private final ReversiBoard board;
	
	public ReversiGame(ReversiBoard B,Disc currentPlayer) {
		this.currentPlayer=currentPlayer;
		this.board= new ReversiBoard(B);
	}
	
	public static ReversiGame createDefaultGame() {
		return new ReversiGame(ReversiBoard.createDefaultBoard(),Disc.W);
	}
	

	public void placeDisc(Position position) {		
		if(!isValidMove(position))
			throw new GameException("move "+position+" invalid");
		// need to evaluate the entire stream
		// known to be a valid move
		Arrays.stream(Direction.values()).filter(d->flipAlongLine(d,d.apply(position),false,true)).count();
		board.set(position, currentPlayer);
		currentPlayer=currentPlayer.getOpponent();
	}
	
	private boolean flipAlongLine(Direction d,Position p, boolean hasFlipped,boolean doFlips) {
		if(!board.inside(p))
			return false;
		Disc discAtp = board.get(p);
		if(discAtp==Disc.EMPTY)
			return false;
		if(currentPlayer == discAtp)
			return hasFlipped;
		// else oppenent
		boolean result = flipAlongLine(d,d.apply(p),true,doFlips);
		if(result&& doFlips)
			board.set(p,currentPlayer);
		return result;			
	}
	public boolean isValidMove(Position p) {
		return Arrays.stream(Direction.values()).anyMatch(d->flipAlongLine(d,d.apply(p),false,false));
	}
	
	public Disc getCurrentPlayer() {
		return this.currentPlayer;
	}

	public Map<Disc,Set<Position>> getPositionsByPlayer() {
		return board.getPositionsByPlayer();
	}
}
