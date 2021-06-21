package com.jxrj.aero;

import java.net.*;
import java.io.*;
/**
* server
*/
public class server {
	private ServerSocket serverSocket;
	public String serverName;
	public int Prot;
	public Thread thread;
	// 服务器
	public server() {
	}

	// 监控新用户连接
	public void serverInsert(int Prot) throws IOException {
		serverSocket = new ServerSocket(Prot);
	}
	public void run() throws IOException {
		while (true) {
			Socket socket = serverSocket.accept();
			if (socket.isConnected()) {
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						addNode();
					}
				});
			}
		}
	}
	public void addNode() {
	}
}

// 用户类
class userNode {
	private volatile long id; // 用户id
	private volatile Chess chess; // 用户移动的棋子
	public userNode(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setChess(Chess chess) {
		this.chess  = chess;
	}
	public Chess getChess() {
		return this.chess;
	}
}
