package com.zj.show;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @说明：游戏窗体，主要实现的功能：显示、关闭和最大最小化
 * @author lenovo
 * @需要嵌入面板，启动主线程
 * @窗体说明 窗体的大小以及样式需要放在配置文件里面
 * @分析 1.面板绑定到窗体 2.监听绑定 3.游戏主线程启动 4.显示窗体
 */

public class GameJFrame extends JFrame{
	public static int GameX=750;  //先给窗体大小赋予一个初始值,设置为public是为了提供给配置文件的存储读取数据
	public static int GameY=600;
	private JPanel jPanel=null;  //正在显示的面板
	private KeyListener keyListener=null;  //键盘监听
	private MouseMotionListener motionListener=null;
	private MouseListener mouseListener=null;
	private Thread thread=null;  //主线程
	
	/*set注入：在ssm中，通过set方法注入配置文件中读取的数据；将配置文件中的值赋值为类的属性，还有一个加构造注入，只是需要配合构造方法
	 * 源于spring中ioc进行对象的自动生成，管理
	 */
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}

	public void setMotionListener(MouseMotionListener motionListener) {
		this.motionListener = motionListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public GameJFrame() {
		init();
	}
	
	private void init() {
		this.setSize(GameX,GameY);
		this.setTitle("飞机大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置退出并且关闭
		this.setLocationRelativeTo(null); //屏幕居中
	}
	
	public void start() {
		if(jPanel!=null) {
			this.add(jPanel);
		}
		if(keyListener!=null) {
			this.addKeyListener(keyListener);
		}
		if(thread!=null) {
			this.thread.start();		//线程启动
		}
		if(this.jPanel instanceof Runnable) {
			new Thread((Runnable)this.jPanel).start();
		}
		this.setVisible(true);
	}
	
	/*窗体布局:可以存档，读档*/
	public void addButton() {
		
	}
}
