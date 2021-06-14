package com.jxrj.aero;

/**
* Game
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
public class Game extends Frame {

	private  int  windowWidth = 700;
	private int   windowHeight = 800;
	Process process = null;
	private ArrayList<ChessElement> Element = new ArrayList<>();

	private Image background = Toolkit.getDefaultToolkit().getImage(
	                               "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/background.png");
	private Image Start = Toolkit.getDefaultToolkit().getImage(
	                          "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/Menu1_1.png");
	private Image Exit = Toolkit.getDefaultToolkit().getImage(
	                         "/mnt/home/code/java/Chess/src/com/jxrj/aero/Image/Menu4_1.png");
	public Game() {}
	public Game(GameChess gameChess) {
		// 设置窗口大小
		setSize(windowWidth, windowHeight);
		// 创建标题
		setTitle("test");
		// 设置显示
		setVisible(true);
		// 设置窗口缩放
		setResizable(false);
		// 开始界面
		GameStartUi();
		// 播放背景音乐
		try {
			process = Runtime.getRuntime().exec(
			              "mpg123 --loop -1 /mnt/home/code/java/Chess/src/com/jxrj/aero/music/bgm.mp3 &");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("音频播放");
		// 监听退出
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
				if(e.getX() > max_x && e.getX() < max_x + 500 && e.getY() > max_y && e.getY() < max_y + 100) {
//					setVisible(false);
					dispose();
					GameStart(gameChess);
				}
				if(e.getX() > max_x && e.getX() < max_x + 500 && e.getY() > max_y + 100 && e.getY() < max_y + 200) {
					System.exit(0);
				}
			}
		});
	}
	// 开始界面
	public void GameStartUi() {
		Element.clear();
		Element.add(new ChessElement(Start, windowWidth / 2 - 500 / 2, windowHeight / 2));
		Element.add(new ChessElement(Exit, windowWidth / 2 - 500 / 2, windowHeight / 2 + 100));
		repaint();
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, windowWidth, windowHeight, this);
		for (ChessElement te : Element) {
			g.drawImage(te.getImage(), te.getX(), te.getY(), 500, 100, this);
		}
	}
	public void GameStart(GameChess gameChess) {
		ChessVictory();
		gameChess.GameChessStart();
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
}
