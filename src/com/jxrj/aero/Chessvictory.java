package com.jxrj.aero;
/**
* victory
* 游戏结束时候显示
*/

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Painter;

public class Chessvictory extends Frame {
	private Image background = Toolkit.getDefaultToolkit().getImage(
	                               "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/background.png");
	private Image black_win = Toolkit.getDefaultToolkit().getImage(
	                              "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/black-win.png");
	private Image read_win = Toolkit.getDefaultToolkit().getImage(
	                             "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/red-win.png");
	private Image AllReset = Toolkit.getDefaultToolkit().getImage(
	                             "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/ButtonAllReset_1.png");
	private Image Start = Toolkit.getDefaultToolkit().getImage(
	                          "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/Menu1_1.png");
	private Image Exit = Toolkit.getDefaultToolkit().getImage(
	                         "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/Menu4_1.png");


	private ArrayList<ChessElement> Element = new ArrayList<>();


	private  int  windowWidth = 700;
	private int   windowHeight = 800;

	private Chess chess;
	private GameChess gameChess;
	Process process = null;
	public  Chessvictory() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("中断当前线程");
				process.toHandle();
				System.exit(0);
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 判断是否为 鼠标左键
				if(MouseEvent.BUTTON1 != e.getButton()) {
					return;
				}
				System.out.println(e.getX() + " " + e.getY());
				int max_x = windowWidth / 2 - 500 / 2 ;
				int max_y = windowHeight / 2;
				if(e.getX() > 160 && e.getX() < 170 && e.getY() > 534 && e.getY() < 570) {
					GameStart(gameChess);
				}
			}
		});
	}
	public void vicator(Chess chess, GameChess gameChess) {
		this.chess = chess;
		this.gameChess = gameChess;
		// 设置窗口大小
		setSize(windowWidth, windowHeight);
		// 创建标题
		setTitle("test");
		// 设置显示
		setVisible(true);
		// 设置窗口缩放
		setResizable(false);
		// chess 被吃掉的棋子
		if (chess.getName().contains("将") )
			if (chess.getisread()) {
				// 当被吃掉的是红将
				readVicator();
			} else {
				// 当被吃掉的是黑将
				blackVicator();
			}
	}
	// 红将胜利界面
	public void blackVicator() {
		gameChess.GameChessStart();
		gameChess.repaint();
		Element.clear();
		Element.add(new ChessElement(read_win, windowWidth / 2 - 500 / 2, windowHeight / 2));
		Element.add(new ChessElement(AllReset, windowWidth / 2 - 500 / 2, windowHeight / 2 + 100));
		repaint();
	}
	// 黑将胜利界面
	public void readVicator() {
		Element.clear();
		Element.add(new ChessElement(black_win, windowWidth / 2 - 500 / 2, windowHeight / 2));
		Element.add(new ChessElement(AllReset, windowWidth / 2 - 500 / 2, windowHeight / 2 + 100));
		repaint();
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, windowWidth, windowHeight, this);
		for (ChessElement te : Element) {
			g.drawImage(te.getImage(), te.getX(), te.getY(), 500, 100, this);
		}
	}
	// 棋子移动音效
	public void moveTo() {
		try {
			process = Runtime.getRuntime().exec(
			              "paplay /mnt/home/code/java/Chess/src/com/jxrj/aero/music/dopiece.wav");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 开始和结束音效
	public void ChessVictory() {
		try {
			process = Runtime.getRuntime().exec(
			              "paplay /mnt/home/code/java/Chess/src/com/jxrj/aero/music/win.wav");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void GameStart(GameChess gameChess) {
		gameChess.GameChessStart();
	}
}
