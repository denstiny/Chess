package com.jxrj.aero;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
* client
*/
public class client {
	ObjectInputStream input;
	ObjectOutputStream writer;
	ChessNode chessNode;
	Socket socket;
	public client() throws IOException {
		socket = new Socket("127.0.0.1", 10900);
		//OutputStream os = socket.getOutputStream();
		System.out.println("请求连接");
		input = new ObjectInputStream(socket.getInputStream());
		writer = new ObjectOutputStream(socket.getOutputStream());
	}
	public ChessNode read() throws IOException, ClassNotFoundException {
		this.chessNode = (ChessNode)input.readObject();
		return this.chessNode;
	}
	public void writer(ChessNode chessNode) throws IOException {
		writer.writeObject(chessNode);
	}
}
