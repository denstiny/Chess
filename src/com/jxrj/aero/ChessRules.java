package com.jxrj.aero;

import java.awt.*;

public class ChessRules {
	private Point[][] chessMap;
	private Chess[] chess;
	private Point after;
	private Point now;

	// now 棋子移动之前的坐标 after 棋子移动到的坐标 chess 所有棋子 chessMap 地图的所有坐标
	public boolean CanMove(Point now, Point after, Chess[] chess) {
		// 设置属性
		this.chess = chess;
		this.after = after;
		this.now = now;
		System.out.println(" 移动前坐标 ==> "
		                   + now.getX() + " "
		                   + now.getY()
		                   + "移动后坐标 ==> "
		                   + after.getX()
		                   + " "
		                   + after.getY()
		                   + "当前棋子 "
		                   + chess[0].getName());
		// 根据棋子判断是否可以移动
		if (chess[0].getName().contains("卒"))
			return chess[0].getisread() ? readZu() : blackZu();
		if (chess[0].getName().contains("炮"))
			return chessPao();
		if (chess[0].getName().contains("车"))
			return chessChe();
		if (chess[0].getName().contains("士"))
			return chess[0].getisread() ? readShi() : blackShi();
		if (chess[0].getName().contains("马"))
			return chessMa();
		if (chess[0].getName().contains("象"))
			return chess[0].getisread() ? readXiang() : blackXiang();
		if (chess[0].getName().contains("将"))
			return chessJiang();
		return false;
	}

	public ChessRules(Point[][] chessMap) {
		this.chessMap = chessMap;
	}

	// todo list
	/*
	 * 完成 马 车 将 的移动规则 完成 游戏开始界面 完成 游戏胜利界面 添加 游戏音效
	 */
	// 红卒 规则
	public boolean readZu() {
		System.out.println("红卒 规则 判断");
		// 没过河 只能向前移动一格
		if (now.getX() >= 5) {
			return (now.getX() - after.getX()) == 1;
		} else {
			// 如果已经过河 则可以左右 和向前移动一格
			return (int)(now.getX() - after.getX()) == 1 || Math.abs(now.getY() - after.getY()) == 1;
		}
	}

	// 黑卒 规则
	public boolean blackZu() {
		System.out.println("黑卒 规则 判断");
		if (now.getX() < 5) {
			return (after.getX() - now.getX()) == 1;
		} else {
			// 如果已经过河
			return (int)(after.getX() - now.getX()) == 1 || Math.abs(now.getY() - after.getY()) == 1;
		}
	}

	// 炮 规则
	public boolean chessPao() {
		System.out.println("炮 规则 判断");
		int x = (int) now.getX();
		int y = (int) now.getY();
		int len = 0;
		Chess tempchess = null;
		if (x == after.getX() && y != after.getY()) {
			//System.out.println("y");
			for (int i = 1; i < Math.abs(after.getY() - y); i++) {
				int _i = after.getY() < y ? i * -1 : i;
				for (int n = 0; n < chess.length; n++) {
					if (chess[n] != null && chess[n].get_X() == x && chess[n].get_Y() == y + _i) {
						System.out.println(chess[n].getName());
						len++;
					}
				}
			}
		}
		if (x != after.getX() && y == after.getY()) {
			//System.out.println("x");
			for (int i = 1; i < Math.abs(after.getX() - x); i++) {
				int _i = after.getX() < x ? i * -1 : i;
				for (int n = 0; n < chess.length; n++) {
					if (chess[n] != null && chess[n].get_X() == x + _i && chess[n].get_Y() == y) {
						if (chess[i] != null && chess[i].get_X() == after.getX() && chess[i].get_Y() == after.getY())
							tempchess = chess[n];
						System.out.println(chess[n].getName());
						len++;
					}
				}
			}
		}
		if (x != after.getX() && y != after.getY()) {
			return false;
		}
		System.out.println("炮 越过 " + len + "棋子");
		switch (len) {
		case 0:
			// 判断 移动后的位置是否存在 棋子
			for (int i = 0; i < chess.length; i++) {
				if (chess[i] != null && chess[i].get_X() == after.getX() && chess[i].get_Y() == after.getY()) {
					return false;
				}
			}
			return true;
		case 1:
			// 判断 跳过的位置是否存在棋子
			for (int i = 0; i < chess.length; i++) {
				if (chess[i] != null && chess[i].get_X() == after.getX() && chess[i].get_Y() == after.getY()) {
					return true;
				}
			}
			return false;
		default:
			return false;
		}
	}

	// 红士 规则
	public boolean readShi() {
		System.out.println("红士 规则 判断");
		// 判断是否走斜线 且 不能出 宫殿
		if (Math.abs(now.getX() - after.getX()) == 1 && Math.abs(now.getY() - after.getY()) == 1) {
			if (after.getY() >= 3 && after.getY() <= 5 && after.getX() >= 7)
				return true;
		}
		return false;
	}

	// 黑士 规则
	public boolean blackShi() {
		System.out.println("黑士 规则 判断");
		// 判断是否走斜线 且 不能出 宫殿
		if (Math.abs(now.getX() - after.getX()) == 1 && Math.abs(now.getY() - after.getY()) == 1) {
			if (after.getY() >= 3 && after.getY() <= 5 && after.getX() <= 3)
				return true;
		}
		return false;
	}

	// 红象 规则
	public boolean readXiang() {
		// System.out.println("红象 规则 判断");
		if (after.getX() < 5)
			return false;
		// 判断是否走田字
		if (Math.abs(now.getX() - after.getX()) == 2 && Math.abs(now.getY() - after.getY()) == 2) {
			for (int i = 0; i < chess.length; i++) {
				// 判断是否 被蹩脚
				if (chess[i] != null && (now.getX() + after.getX()) / 2 == chess[i].get_X()
				        && (now.getY() + after.getY()) / 2 == chess[i].get_Y()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	// 黑象 规则
	public boolean blackXiang() {
		if (after.getX() > 4)
			return false;
		if (Math.abs(now.getX() - after.getX()) == 2 && Math.abs(now.getY() - after.getY()) == 2) {
			for (int i = 0; i < chess.length; i++) {
				// 判断是否 被蹩脚
				if (chess[i] != null && (now.getX() + after.getX()) / 2 == chess[i].get_X()
				        && (now.getY() + after.getY()) / 2 == chess[i].get_Y()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	// 马
	public boolean chessMa() {
		int x = (int)Math.abs(now.getX() - after.getX());
		int y = (int)Math.abs(now.getY() - after.getY());
		// 上下移动
		int n = after.getY() > now.getY() ? -1 : 1;
		int l = after.getX() > now.getX() ? -1 : 1;
		for (int i = 0; i < chess.length; i++) {
			// 上下移动 是否憋马脚
			System.out.println(now.getX() - l);
			System.out.println(now.getY() - n);
			if (chess[i] != null && x == 1 && y == 2 &&
			        chess[i].get_Y() == now.getY() - n &&
			        chess[i].get_X() == now.getX())
				return false;
			// 左右移动 是否憋马脚
			if (chess[i] != null && x == 2 && y == 1 &&
			        chess[i].get_Y() == now.getY() &&
			        chess[i].get_X() == now.getX() - l)
				return false;
		}
		// 如果是走日字
		if ((x == 1 && y == 2) || x == 2 && y == 1) {
			return true;
		}
		return false;
	}

	// 车
	public boolean chessChe() {
		for (int i = 0; i < chess.length; i++) {
			if (chess[i] != null) {
				// Y 轴移动
				if (now.getX() == after.getX() && now.getY() != after.getY())
					for (int n = 1; n < Math.abs(now.getY() - after.getY()); n++) {
						int _n = now.getY() > after.getY() ? n * -1 : n;
						if (chess[i].get_X() == now.getX()  && chess[i].get_Y() == now.getY() + _n)
							return false;
					}
				// x 轴移动
				if (now.getX() != after.getX() && now.getY() == after.getY()) {
					for (int n = 1; n < Math.abs(now.getX() - after.getX()); n++) {
						int _n = now.getX() > after.getX() ? n * -1 : n;
						if (chess[i].get_X() == now.getX() + _n && chess[i].get_Y() == now.getY())
							return false;
					}
				}
			}
		}
		return (now.getX() == after.getX() || now.getY() == after.getY());
	}

	// 将
	public boolean chessJiang() {
		Chess tempchess = null;
		int len = 0;
		boolean Thisismove = false;
		// 如果要吃掉敌方将
		for (int i = 0; i < chess.length; i++) {
			if (chess[i] != null) {
				// 查看目标棋子是否为将军
				if (chess[i].get_X() == (int)after.getX() && chess[i].get_Y() == (int)after.getY()) {
					Thisismove = true;
				}
				// 查找路途中的棋子数量
				for (int x = 1; x < Math.abs(now.getX() - after.getX()); x++) {
					int _x = now.getX() > 5 ? x * -1 : x;
					System.out.println("---" + chess[i].getName() + ((int)now.getX() + _x));
					if (chess[i] != null && chess[i].get_X() == (int)now.getX() + _x && chess[i].get_Y() == (int)now.getY())
						len++;
				}
				// 当前棋子
				if (chess[i].get_X() == (int)now.getX() && chess[i].get_Y() == (int)now.getY()) {
					tempchess = chess[i];
				}
			}
		}
		if (Thisismove && len == 0) {
			return true;
		}
		// 判断是否行走一格 且不出皇宫
		if (
		    tempchess != null &&
		    (tempchess.getisread() ? after.getX() >= 7 : after.getX() <= 3) &&
		    after.getY() >= 3 && after.getY() <= 5 &&
		    ((Math.abs(now.getX() - after.getX()) == 1) && now.getY() == after.getY() ||
		     (Math.abs(now.getY() - after.getY()) == 1) && now.getX() == after.getX()))
			rjturn true;
		return false;
	}
}
