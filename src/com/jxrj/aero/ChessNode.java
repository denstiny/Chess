package com.jxrj.aero;


import java.awt.*;

/**
* ChessNode
*/
public class ChessNode implements java.io.Serializable {
	private volatile Point point;
	private volatile Chess chess;
	public ChessNode(Point point, Chess chess) {
		this.point = point;
		this.chess = chess;
	}
	public Point getPoint() {
		return this.point;
	}
	public Chess getChess() {
		return this.chess;
	}
}
