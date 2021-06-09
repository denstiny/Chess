package com.jxrj.aero;

import java.awt.*;
import java.util.ArrayList;

public class Chess {
    // 名字
    public String name;
    // 坐标
    private Point point = new Point();
    // icon
    private Image icon;
    // 可以移动
    public boolean ismove = false;
    // 设置名字
    public void setName(String name) {
        this.name = name;
    }
    // 设置坐标
    public void setPoint(Point point) {
        this.point = point;
    }
    // 设置图标
    public void setIcon(Image icon) {
        this.icon = icon;
    }
    // 移动
    public boolean moveTo(ArrayList<Point> list,Point point) {
        System.out.println("移动棋子" + name );
        for (int i = 0;i < list.size();i++) {
           if(
                   (list.get(i).getX() < point.getX() && list.get(i).getY() < point.getY())
                   &&
                   (list.get(i).getX()+70 > point.getX() && list.get(i).getY() + 70 > point.getY())
           ) {
               setPoint(list.get(i));
               ismove = false;
           }
       }
       return false;
    }
    public void moveTo(double x, double  y) {
        point.setLocation(x,y);
    }
    // 获取坐标
    public Point getPoint() {
        return this.point;
    }
    // 获取图标
    public Image getIcon() {
        return this.icon;
    }
}
