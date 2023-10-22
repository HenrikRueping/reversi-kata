package de.henrikrueping.reversi;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class DiscTest {

	@Test
	void test() {
		assertThrows(UnsupportedOperationException.class, ()->Disc.EMPTY.getOpponent());
	}

}
