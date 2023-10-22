package de.henrikrueping.reversi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ReversiGameSpecialSituationsTest {
 
	@ParameterizedTest
	@EnumSource(Direction.class)
	void testSingleDirection(Direction d) {
		ReversiBoard board= ReversiBoard.createEmptyBoard();
		Position center = new Position(2,2);
		board.set(d.apply(center), Disc.W.getOpponent());
		board.set(d.apply(d.apply(center)), Disc.W);
		ReversiGame cut = new ReversiGame(board, Disc.W);
		cut.placeDisc(center);
		assertEquals(Map.of(Disc.W,Set.of(center,d.apply(center),d.apply(d.apply(center)))),cut.getPositionsByPlayer());
	}
	
	@Test
	void testAllDirectionsAtOnce() {
		ReversiBoard board= ReversiBoard.createEmptyBoard();
		Position center = new Position(2,2);
		 
		for(Direction d:Direction.values()) {
			board.set(d.apply(center), Disc.W.getOpponent());
			board.set(d.apply(d.apply(center)), Disc.W);
		}
		ReversiGame cut = new ReversiGame(board, Disc.W);
		cut.placeDisc(center);
		Set<Position>expected = Set.of(new Position(0,0),new Position(0,2),new Position(0,4),new Position(2,4),new Position(4,4),new Position(4,2),new Position(4,0),new Position(2,0),
				new Position(1,1),new Position(1,2),new Position(1,3),new Position(2,3),new Position(3,3),new Position(3,2),new Position(3,1),new Position(2,1),new Position(2,2)
				
				);
				
		assertEquals(17,cut.getPositionsByPlayer().get(Disc.W).size());
		assertEquals(Map.of(Disc.W,expected),cut.getPositionsByPlayer());
	}

}
