package de.henrikrueping.reversi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

public class ReversiBoard {
	
	final int sizeX;
	final int sizeY;
	final Map<Position,Disc> map;


	public ReversiBoard(int sizeX, int sizeY,final Map<Position,Disc> map) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.map = new HashMap<>();
		map.entrySet().stream().forEach(e->set(e.getKey(),e.getValue()));
	}


	public ReversiBoard(ReversiBoard b) {
		this(b.sizeX,b.sizeY,b.map);
	}


	public static ReversiBoard createDefaultBoard() {
		return new ReversiBoard(8,8,Map.of(new Position(3,3),Disc.W,new Position(4,4),Disc.W,new Position(3,4),Disc.B,new Position(4,3),Disc.B));
	}
	
	public static ReversiBoard createEmptyBoard() {
		return new ReversiBoard(8,8,Map.of());
	}



	public boolean inside(Position p) {
		return p.x()>=0 && p.x()<sizeX&&p.y()>=0&&p.y()<sizeY;
	}


	public void set(Position p, Disc disc) {
		map.put(p,disc);		
	}
	
	public Disc get(Position p)
	{
		return map.getOrDefault(p, Disc.EMPTY);
	}


	public Map<Disc,Set<Position>> getPositionsByPlayer() {
		return map.entrySet().stream().collect(Collectors.groupingBy(Map.Entry<Position,Disc>::getValue,Collectors.mapping(Map.Entry<Position,Disc>::getKey, Collectors.toSet())));
	}




}
