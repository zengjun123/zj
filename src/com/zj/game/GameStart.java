package com.zj.game;

import com.zj.controller.GameListener;
import com.zj.controller.GameThread;
import com.zj.show.GameJFrame;
import com.zj.show.GameMainJPanel;

public class GameStart {
	/**
	 * 程序唯一入口
	 */
	public static void main(String[] args) {
		GameJFrame gj=new GameJFrame();
		
		//键盘监听注入
		GameListener listener=new GameListener();
		gj.setKeyListener(listener);
		//面板注入
		GameMainJPanel gp=new GameMainJPanel();
		gj.setjPanel(gp);
		//主线程注入
		GameThread gt=new GameThread();
		gj.setThread(gt);
		
		gj.start();
	}

}
