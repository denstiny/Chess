package com.jxrj.aero;

import java.awt.*;

/**
* ChessElement
*/
public  class ChessElement  {
	private int x;
	private int y;
	private Image image;
	public Image getImage() {
		return this.image;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public ChessElement () {}

	public ChessElement (Image icon, int x, int y) {
		this.image = icon;
		this.x = x;
		this.y = y;
	}
}
