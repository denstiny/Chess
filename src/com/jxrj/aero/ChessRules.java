package com.jxrj.aero;

import java.awt.*;

public class ChessRules {
	private Point[][] chessMap;
	private Chess[] chess;
	
	public boolean CanMove(Point now, Point after, Chess[] chess) {
	//  now 棋子移动之前的坐标   after 棋子移动到的坐标 chess 所有棋子  chessMap 地图的所有坐标

		System.out.println(" 移动前坐标 ==> " + now.getX() + " " + now.getY() +
				"移动后坐标 ==> " + after.getX() + " " + after.getY());
		this.chess = chess;
		return true;
	}
	public ChessRules(Point[][] chessMap) {
		this.chessMap = chessMap;
	}
}
