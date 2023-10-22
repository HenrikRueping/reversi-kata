package de.henrikrueping.reversi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

	
	private static Stream<Arguments> applyParams() {
		//@formatter:off
		return Stream.of(
				Arguments.of(new Position(-1,-1),Direction.NW),
				Arguments.of(new Position(0,-1),Direction.N),
				Arguments.of(new Position(1,-1),Direction.NE),
				Arguments.of(new Position(1,0),Direction.E),
				Arguments.of(new Position(1,1),Direction.SE),
				Arguments.of(new Position(0,1),Direction.S),
				Arguments.of(new Position(-1,1),Direction.SW),
				Arguments.of(new Position(-1,0),Direction.W)
				);
		//@formatter:on
		
	}
	
	@ParameterizedTest
	@MethodSource("applyParams")
	void applyMovesoriginCorrectly(Position expected, Direction d) {
		assertEquals(expected,d.apply(new Position(0,0)));
	}

}
