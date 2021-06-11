package com.jxrj.aero;

import java.awt.*;

public class ChessRules {
	private Point[][] chessMap;
	private Chess[] chess;
	private Point after;
	private Point now;
	
	//  now 棋子移动之前的坐标   after 棋子移动到的坐标 chess 所有棋子  chessMap 地图的所有坐标
	public boolean CanMove(Point now, Point after, Chess[] chess) {
		// 设置属性
		this.chess = chess;
		this.after = after;
		this.now = now;

		System.out.println(" 移动前坐标 ==> " + now.getX() + " " + now.getY() +
				"移动后坐标 ==> " + after.getX() + " " + after.getY() +
				"当前棋子 " + chess[0].getName());


		// 根据棋子判断是否可以移动
		if(chess[0].getName().contains("卒")) {
			 return chess[0].getisread() ? readZu() : blackZu();
		}

		if(chess[0].getName().contains("炮")) {
			return chessPao();
		}
		
		if(chess[0].getName().contains("车"))
			return chessChe();

		if(chess[0].getName().contains("士")) 
			return chess[0].getisread() ? readShi() : blackShi();

		if(chess[0].getName().contains("马"))
			return chessMa();

		if(chess[0].getName().contains("象"))
			return chess[0].getisread() ? readXiang() : blackXiang();

		if(chess[0].getName().contains("将"))
			return chessJiang();

		return false;
	}
	public ChessRules(Point[][] chessMap) {
		this.chessMap = chessMap;
	}
	// 红卒 规则
	public boolean readZu() {
		System.out.println("红卒 规则 判断");
		// 没过河 只能向前移动一格
		if(now.getX() >= 5 ) {
			return (now.getX() - after.getX()) == 1;
		}else {
			// 如果已经过河 则可以左右 和向前移动一格
			return (now.getX() - after.getX() ) == 1 || Math.abs(now.getY() - after.getY()) == 1;
		}
	}
	// 黑卒 规则
	public boolean blackZu() {
		System.out.println("黑卒 规则 判断");
		if(now.getX() < 5) {
			return (after.getX() - now.getX()) == 1;
		}else {
			// 如果已经过河
			return (after.getX() - now.getX()) == 1 || Math.abs(now.getY() - after.getY()) == 1;
		}
	}
	// 炮 规则
	public boolean chessPao() {
		return true;
	}
	// 红士
	public boolean readShi() {
		return true;
	}
	// 黑士
	public boolean blackShi() {
		return true;
	}
	// 红象
	public boolean readXiang() {
		return true;
	}
	// 黑象
	public boolean blackXiang() {
		return true;
	}
	// 马
    public boolean chessMa() {
		return true;
	}
	// 车
	public boolean chessChe() {
		return true;
	}
	// 将
	public boolean chessJiang() {
		return true;
	}
}
