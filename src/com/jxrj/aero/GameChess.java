package com.jxrj.aero;
import java.awt.*;
import java.awt.event.*;

public class GameChess extends Frame {

	public volatile boolean isRead;
	private Chess chess[] = new Chess[32];
	public static final Point[][] chessMap = new Point[10][9];
	private  int  windowWidth = 700;
	private int   windowHeight = 800;
	private Image background = Toolkit.getDefaultToolkit().getImage("/home/aero/IdeaProjects/chess/src/com/jxrj/aero/image/main.gif");
	// 游戏特效
	private Chessvictory gameviVictory;
	// 规则
	public ChessRules chessRules;
	public GameChess() {
		Game game = new Game(this);
		gameviVictory = new Chessvictory();
	}
	public void GameChessStart() {
		// isRead
		isRead = true;
		// 设置窗口大小
		setSize(windowWidth, windowHeight);
		// 创建标题
		setTitle("test");
		// 设置显示
		setVisible(true);
		// 设置窗口缩放
		setResizable(false);
		// 初始化界面坐标
		initChessMap();
		// 创建规则
		chessRules = new ChessRules(chessMap);
		// 监听事件
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// 监听鼠标
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 判断是否为 鼠标左键
				if(MouseEvent.BUTTON1 != e.getButton() ) {
					return;
				}
				Point p = locationChess(e.getX(), e.getY());
				if(p == null) {
					return;
				}
				if(!chessToPoint(p)) {
					System.out.println(chess[0].getName());
					// 判断选中的棋子是自己的棋子 且 该棋子可以移动
					if(chess[0].getCanMove() && isRead == chess[0].getisread() ) {
						// 判断棋子移动是否符合规则
						if(chessRules.CanMove(new Point(chess[0].get_X(), chess[0].get_Y()), p, chess)) {
							// 音效
							gameviVictory.moveTo();
							for (int i = 0; i < chess.length; i++) {
								// 判断 移动到的位置是否存在 棋子,存在就删除
								if(chess[i] != null && chess[i].get_X() == (int)p.getX() && chess[i].get_Y() == (int)p.getY()) {
									System.out.println("吃掉棋子" + chess[i].getName());
									if(chess[i].getName().contains("将")) {
										ChessWin(chess[i]);
									}
									chess[i] = null;
									break;
								}
							}
							chess[0].setX((int)p.getX());
							chess[0].setY((int)p.getY());
							chess[0].setCanMove();
							isRead = !isRead;
							repaint();
						}
					}
				} else {
					if(chess[0].getCanMove())
						System.out.println("鼠标点击" + p.getX() + " " + p.getY());
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		//System.out.println("绘制背景");
		g.drawImage(background, 0, 0, windowWidth, windowHeight, this);
		// 绘制棋子
		for (int i = 0; i < chess.length; i++) {
			if (chess[i] != null) {
				g.drawImage(chess[i].GetIcon(), chess[i].getX(), chess[i].getY(), 70, 70, this);
			}
		}
	}

	public void initChessMap() {
		// 初始化棋盘可移动坐标
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				chessMap[i][j] = new Point(25 + 72 * j, 25 + 76 * i);
			}
			System.out.println();
		}
		for (int i = 0; i < chess.length; i++) {
			chess[i] = null;
		}
		// 创建棋子
		chess[0] = new Chess(false, "黑车", 0, 0);
		chess[1] = new Chess(false, "黑马", 0, 1);
		chess[2] = new Chess(false, "黑象", 0, 2);
		chess[3] = new Chess(false, "黑士", 0, 3);
		chess[4] = new Chess(false, "黑将", 0, 4);
		chess[5] = new Chess(false, "黑士", 0, 5);
		chess[6] = new Chess(false, "黑象", 0, 6);
		chess[7] = new Chess(false, "黑马", 0, 7);
		chess[8] = new Chess(false, "黑车", 0, 8);
		chess[9] = new Chess(false, "黑炮", 2, 1);
		chess[10] = new Chess(false, "黑炮", 2, 7);
		chess[11] = new Chess(false, "黑卒", 3, 0);
		chess[12] = new Chess(false, "黑卒", 3, 2);
		chess[13] = new Chess(false, "黑卒", 3, 4);
		chess[14] = new Chess(false, "黑卒", 3, 6);
		chess[31] = new Chess(false, "黑卒", 3, 8);
		chess[15] = new Chess(true, "红卒", 6, 8);
		chess[16] = new Chess(true, "红卒", 6, 4);
		chess[17] = new Chess(true, "红卒", 6, 6);
		chess[18] = new Chess(true, "红卒", 6, 2);
		chess[19] = new Chess(true, "红卒", 6, 0);
		chess[20] = new Chess(true, "红炮", 7, 1);
		chess[21] = new Chess(true, "红炮", 7, 7);
		chess[22] = new Chess(true, "红车", 9, 0);
		chess[23] = new Chess(true, "红马", 9, 1);
		chess[24] = new Chess(true, "红象", 9, 2);
		chess[25] = new Chess(true, "红士", 9, 3);
		chess[26] = new Chess(true, "红将", 9, 4);
		chess[27] = new Chess(true, "红士", 9, 5);
		chess[28] = new Chess(true, "红象", 9, 6);
		chess[29] = new Chess(true, "红马", 9, 7);
		chess[30] = new Chess(true, "红车", 9, 8);
	}

	// 如果红色方选中的黑色棋子，且符合移动规则，则删除黑色方的棋子 红色棋子移动到黑色方棋子位置
	public boolean chessToPoint(Point point) {
		System.out.print(isRead ? "红色方 " : "黑色方 ");
		for (int i = 0; i < chess.length; i++)
			// 判断该坐标是否有棋子
			if (chess[i] != null && chess[i].get_X() == point.getX() && chess[i].get_Y() == point.getY()) {
				// 如果为敌方棋子
				if(chess[i].getisread() != isRead)  {
					return false;
				}
				chess[i].setCanMove();
				Chess temp = chess[i];
				chess[i] = chess[0];
				chess[0] = temp;
				return true;
			}
		return false;
	}
	public Point locationChess(int x, int y) {
		for (int i = 0; i < chessMap.length; i++) {
			for (int j = 0; j < chessMap[i].length; j++) {
				Point p = chessMap[i][j];
				if(p.getX() < x && p.getY() < y && p.getX() + 70 > x && p.getY() + 70 > y) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}
	public void ChessWin(Chess chess) {
		// 结束音效
		gameviVictory.vicator(chess, this);
	}
}
