package de.henrikrueping.reversi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import de.henrikrueping.reversi.exception.GameException;

class ReversiGameTest {

	ReversiGame cut =  ReversiGame.createDefaultGame();
	
	private static Stream<Position> validMovesInInitialPosition(){
		return Stream.of(new Position(3,5),new Position(5,3),new Position(4,2),new Position(2,4));
	}
	
		@Test
		void whiteStartsTheCame() {
			assertEquals(Disc.W,cut.getCurrentPlayer());
		}
		
		@Test
		void blacksTurnAfterLegalMove() {
			cut.placeDisc(new Position(5,3));
			assertEquals(Disc.B,cut.getCurrentPlayer());
		}
		
		@Test
		void whitesTurnAfterTwoLegalMoves() {
			cut.placeDisc(new Position(5,3));
			cut.placeDisc(new Position(5,4));
			assertEquals(Disc.W,cut.getCurrentPlayer());
		}
		
		@Test
		void initialStateIsCorrect() {
			assertEquals(Map.of(Disc.W,Set.of(new Position(3,3),new Position(4,4)),Disc.B,Set.of(new Position(3,4),new Position(4,3))),cut.getPositionsByPlayer());
		}
		
		@Test
		void firstMoveResultsInCorrectBoardState() {
			cut.placeDisc(new Position(5,3));
			assertEquals(Map.of(Disc.W,Set.of(new Position(3,3),new Position(4,4),new Position(5,3),new Position(4,3)),Disc.B,Set.of(new Position(3,4))),cut.getPositionsByPlayer());
		}
		
		@ParameterizedTest
		@DisplayName("erlaubte Züge werden so erkannt")
		@MethodSource("validMovesInInitialPosition")		
		void allowedMovesAreValid(Position p) {
			assertTrue(cut.isValidMove(p));
		}
		
		@Test
		void invalidMoveThrowsException() {
			assertThrows(GameException.class,()->cut.placeDisc(new Position(0,0)));
		}
}
