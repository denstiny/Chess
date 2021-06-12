package com.jxrj.aero;

import java.awt.*;

public class Chess {
	private boolean canMove = false;
	private boolean isRead;
	private String name;
	private Image icon;
	private int x;
	private int y;
	public Chess(boolean isRead, String name, int x, int y) {
		this.isRead = isRead;
		this.name = name;
		this.x = x;
		this.y = y;
		icon = Toolkit.getDefaultToolkit().getImage("/home/aero/IdeaProjects/chess/src/com/jxrj/aero/image/" + name + ".gif");
		System.out.println("创建棋子" + name);
	}
	public boolean getisread() {
		return this.isRead;
	}
	public boolean setIsRead(boolean isRead) {
		this.isRead = isRead;
		return this.isRead;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public int getX() {
		return (int)GameChess.chessMap[x][y].getX();
	}

	public int getY() {
		return (int)GameChess.chessMap[x][y].getY();
	}
	public int get_X() {
		return x;
	}
	public int get_Y() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public Image GetIcon() {
		return this.icon;
	}
	public Image SetIcon(Image icon) {
		this.icon = icon;
		return this.icon;
	}
	public void setCanMove() {
		this.canMove = !this.canMove;
	}
	public boolean getCanMove() {
		return this.canMove;
	}
}
