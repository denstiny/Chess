package com.jxrj.aero;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
* server
*/
public class server {
	ObjectInputStream input;
	ObjectOutputStream writer;
	ChessNode chessNode;
	ServerSocket socket;
	Socket accept;
	public  server() throws IOException {
		socket = new ServerSocket(10900);
		accept = socket.accept();
		System.out.println("连接成功");
		input = new ObjectInputStream(accept.getInputStream());
		writer = new ObjectOutputStream(accept.getOutputStream());
	}
	public ChessNode read() throws IOException, ClassNotFoundException {
		Object object = input.readObject();
		this.chessNode = (ChessNode) object;
		return this.chessNode;
	}
	public void writer(ChessNode node) throws IOException {
		writer.writeObject(node);
	}
	public void close() throws IOException {
		accept.close();
		socket.close();
		input.close();
		writer.close();
	}
}
