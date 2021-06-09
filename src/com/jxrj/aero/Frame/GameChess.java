package com.jxrj.aero.Frame;
import com.jxrj.aero.Chess;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameChess  extends Frame {
    // 是否可以移动
    public volatile boolean ismove = false;
    // 上次移动的棋子
    public Chess upChess;
    // 是否胜利
    public boolean isvictory = false;
    // 地图中的所有棋子
    private ArrayList<Chess> chess = new ArrayList<Chess>();
    // 地图中的所有坐标
    private ArrayList<Point> points = new ArrayList<Point>();
    // 是否为红棋
    public boolean isread = true;
    // 棋盘的宽
    private int width = 700;
    // 棋盘的高
    private int height = 800;
    // 棋子的高
    private int Pwidth = 70;
    // 棋子的高
    private int Pheight = 70;

    private int pieces_x = 25;
    private int pieces_y = 25;
    private Image bg;

    public GameChess() {
    }
    public GameChess(boolean isread) {
        // 设置窗口大小
        setSize(width,height);
        // 创建标题
        setTitle("test");
        // 设置显示
        setVisible(true);
        // 设置窗口缩放
        setResizable(false);
        // 监听事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        bg = Toolkit.getDefaultToolkit().getImage("/home/aero/IdeaProjects/chess/src/com/jxrj/aero/image/main.gif");
        // 初始化棋盘
        this.isread = isread;
        initMap();
    }

    void initMap() {
        // 初始化坐标位置
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                //System.out.print(points.size() + "+" + pieces_x + 72*j + "-" + pieces_y + 76 *i + "   ");
                points.add(new Point(pieces_x + 72*j,pieces_y + 76*i));
            }
            System.out.println();
        }
        // 添加棋子
        if(!isread) {
            addPieces("黑车",points.get(0));
            addPieces("黑马",points.get(1));
            addPieces("黑象",points.get(2));
            addPieces("黑士",points.get(3));
            addPieces("黑将",points.get(4));
            addPieces("黑士",points.get(5));
            addPieces("黑象",points.get(6));
            addPieces("黑马",points.get(7));
            addPieces("黑车",points.get(8));

            addPieces("黑炮",points.get(19));
            addPieces("黑炮",points.get(25));

            addPieces("黑卒",points.get(27));
            addPieces("黑卒",points.get(29));
            addPieces("黑卒",points.get(31));
            addPieces("黑卒",points.get(33));
            addPieces("黑卒",points.get(35));

            addPieces("红卒",points.get(54));
            addPieces("红卒",points.get(56));
            addPieces("红卒",points.get(58));
            addPieces("红卒",points.get(60));
            addPieces("红卒",points.get(62));

            addPieces("红炮",points.get(64));
            addPieces("红炮",points.get(70));

            addPieces("红车",points.get(81));
            addPieces("红马",points.get(82));
            addPieces("红象",points.get(83));
            addPieces("红士",points.get(84));
            addPieces("红将",points.get(85));
            addPieces("红士",points.get(86));
            addPieces("红象",points.get(87));
            addPieces("红马",points.get(88));
            addPieces("红车",points.get(89));
        }
        else {
            addPieces("红车",points.get(0));
            addPieces("红马",points.get(1));
            addPieces("红象",points.get(2));
            addPieces("红士",points.get(3));
            addPieces("红将",points.get(4));
            addPieces("红士",points.get(5));
            addPieces("红象",points.get(6));
            addPieces("红马",points.get(7));
            addPieces("红车",points.get(8));

            addPieces("红炮",points.get(19));
            addPieces("红炮",points.get(25));

            addPieces("红卒",points.get(27));
            addPieces("红卒",points.get(29));
            addPieces("红卒",points.get(31));
            addPieces("红卒",points.get(33));
            addPieces("红卒",points.get(35));

            addPieces("黑卒",points.get(54));
            addPieces("黑卒",points.get(56));
            addPieces("黑卒",points.get(58));
            addPieces("黑卒",points.get(60));
            addPieces("黑卒",points.get(62));

            addPieces("黑炮",points.get(64));
            addPieces("黑炮",points.get(70));

            addPieces("黑车",points.get(81));
            addPieces("黑马",points.get(82));
            addPieces("黑象",points.get(83));
            addPieces("黑士",points.get(84));
            addPieces("黑将",points.get(85));
            addPieces("黑士",points.get(86));
            addPieces("黑象",points.get(87));
            addPieces("黑马",points.get(88));
            addPieces("黑车",points.get(89));
        }
        // 点击棋子
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1 ) {
                    return;
                }
                System.out.println(e.getX() + " " + e.getY());
                if(!chessToPoint(new Point(e.getX(), e.getY()))) {
                    for (int i = 0; i < chess.size(); i++) {
                        if(chess.get(i).ismove) {
                            if(ismove) {
                                chess.get(i).moveTo(points,new Point(e.getX(),e.getY()));
                                //ismove = false;
                                setIsmove(false);
                                upChess = chess.get(i);
                            }
                            else {
                                System.out.println("当前棋子不可移动");
                            }
                        }
                    }
                }
                repaint();
            }
        });
    }
    //  添加棋子
    public void addPieces(String text,Point point)  {
        Chess in = new Chess();
        in.setName(text);
        in.setIcon(Toolkit.getDefaultToolkit().getImage("/home/aero/IdeaProjects/chess/src/com/jxrj/aero/image/"+text+".gif"));
        in.setPoint(point);
        chess.add(in);
    }
    //  判断是否选中棋子
    public boolean chessToPoint(Point mousePoint) {
        // 判断点击位置是否存在棋子
        int i = 0;
        for(;i < chess.size();i++) {
            // 只能选中自己的棋子
            if(comparePoint(mousePoint,chess.get(i).getPoint())) {
                // 当前执子红
                if(!isread) {
                    // 选中的是红子 返回 true 否则 返回 false
                    if(chess.get(i).name.contains("红")) {
                        System.out.println("红");

                        if(ismove) {
                            System.out.println("可以移动");
                            chess.get(i).ismove = true;
                            return true;
                        }else {
                            System.out.println("不可移动");
                            return false;
                        }
                    }
                }
                // 当前执子是黑
                else  {
                    // 选中的黑字 返回true 否则 返回 false
                    if(chess.get(i).name.contains("黑")) {
                        System.out.println("黑");

                        if(ismove) {
                            System.out.println("可以移动");
                            chess.get(i).ismove = true;
                            return true;
                        }else {
                            System.out.println("不可移动");
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    // 判断鼠标选中位置是否存在棋子
    public boolean comparePoint(Point mousePoint,Point chessPoint) {
        // 如果鼠标坐标在棋子坐标的范围内
        if(mousePoint.equals(chessPoint)){
            // 如果坐标相等
            return true;
        }
        else return (chessPoint.x < mousePoint.x && chessPoint.y < mousePoint.y)
                &&
                (mousePoint.x < chessPoint.x + Pwidth && mousePoint.y < chessPoint.y + Pheight);
    }
    // 绘制棋盘
    @Override
    public void paint(Graphics g) {
        //System.out.println("绘制背景");
        g.drawImage(bg, 0, 0, width, height, this);
        for (Chess te:chess) {
            g.drawImage(te.getIcon(),te.getPoint().x,te.getPoint().y,Pwidth,Pheight,this);
            //System.out.println("绘制棋子=> " +G te.name);
        }
    }
    // 将当前棋盘的镜像给另一个棋盘
    public void GameChessTo(GameChess gamechess) {
        ArrayList<Chess> tempChess = new ArrayList<>();
        for (Chess ch:chess) {
            ch.moveTo(ch.getPoint().getY(),ch.getPoint().getX());
            tempChess.add(ch);
        }
        gamechess.chess = tempChess;
    }

    public void victory() {
        if(this.isread) {
            System.out.println("红方胜利");
        }else {
            System.out.println("黑方胜利");
        }
    }
    public void send(GameChess enemy) {
        //System.out.println(enemy.upChess.getPoint());
        if(enemy.upChess == null) {
            return;
        }
        // 接受 敌方 棋子移动的位置 并调整
        for (int i = 0; i < chess.size(); i++) {

            Point p = chess.get(i).getPoint();
            Point n = enemy.upChess.getPoint();
            //System.out.println("敌方棋子坐标" + n);
            p.setLocation(p.getY(),p.getX());

            if(p.getX() == n.getX() && p.getY() == n.getY()) {
                chess.get(i).setPoint(p);
            }
        }
    }
    public boolean setIsmove(boolean ismove) {
        Lock lock = new ReentrantLock();
        lock.lock();
        this.ismove = ismove;
        lock.unlock();
        return ismove;
    }
    public boolean getIsmove() {
        return this.ismove;
    }
}
