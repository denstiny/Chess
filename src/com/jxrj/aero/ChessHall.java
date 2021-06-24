package com.jxrj.aero;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
* ChessHall
*/
public class ChessHall extends JFrame {
	// 游戏大厅
	private  int  windowWidth = 700;
	private int   windowHeight = 800;

	// 按钮
	JButton jbutton, jbutton1;
	// 控件
	JLabel label = new JLabel("用户名");
	//创建JTextField，16表示16列，用于JTextField的宽度显示而不是限制字符个数
	JTextField textField = new JTextField(16);
	JButton button = new JButton("确定");


	public ChessHall() {
		setSize(windowWidth, windowHeight);
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
		//设置窗体布局　
		setLayout(new FlowLayout());
		add(label);
		add(textField);
		add(button);
		button.addActionListener(this::actionPerformed);
	}
	public void onButtonOk() throws IOException {
		if(textField.getText().isEmpty()) {
			Object[] options = { "OK ", "CANCEL " };
			JOptionPane.showOptionDialog(null, "不能输入空字符", "提示", JOptionPane.DEFAULT_OPTION,
			                             JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			return;
		} else {
			// 向服务器发起连接
			client sockeClient = new client();
		}
	}

	private void actionPerformed(ActionEvent e) {
		try {
			onButtonOk();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
