package de.henrikrueping.reversi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ReversiBoardTest {

	@SuppressWarnings("unused")
	private static Stream<Position> insideParams(){
		return IntStream.range(0, 8).mapToObj(x->IntStream.range(0, 8).mapToObj(y->new Position(x,y))).flatMap(x->x);
	}
	
	@SuppressWarnings("unused")
	private static Stream<Position> outsideParams(){
		return Stream.of(new Position(-1,4),new Position(4,-1),new Position(8,4),new Position(4,8));
	}
	
	ReversiBoard cut=  ReversiBoard.createEmptyBoard();
	
	@ParameterizedTest
	@MethodSource("de.henrikrueping.reversi.ReversiBoardTest#insideParams")
	@DisplayName("folgende Felder sind im Board")
	void squaresAreInside(Position p) {
		assertTrue(cut.inside(p));
	}
	
	@ParameterizedTest
	@MethodSource("de.henrikrueping.reversi.ReversiBoardTest#outsideParams")
	@DisplayName("folgende Felder sind nicht im Board")
	void squaresAreOutside(Position p) {
		assertFalse(cut.inside(p));
	}
	
	@ParameterizedTest
	@MethodSource("de.henrikrueping.reversi.ReversiBoardTest#insideParams")
	@DisplayName("Feld kann auf BLACK gesetzt werden")
	void verifySetGetInteractionAsBlack(Position p) {
		cut.set( p,Disc.B);
		assertEquals (Disc.B, cut.get(p));
	}
	
	@ParameterizedTest
	@MethodSource("de.henrikrueping.reversi.ReversiBoardTest#insideParams")
	@DisplayName("Feld kann auf WHITE gesetzt werden")
	void verifySetGetInteractionsAsWhite(Position p) {
		cut.set( p,Disc.W);
		assertEquals (Disc.W, cut.get(p));
	}
	
	@ParameterizedTest
	@MethodSource("de.henrikrueping.reversi.ReversiBoardTest#insideParams")
	@DisplayName("Feld kann auf BLACK gesetzt werden")
	void verifySetGetInteractionEmptyCell(Position p) {
		cut.set( p,Disc.EMPTY);
		assertEquals (Disc.EMPTY, cut.get(p));
	}
}
