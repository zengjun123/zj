package com.zj.game;

import com.zj.controller.GameListener;
import com.zj.controller.GameThread;
import com.zj.show.GameJFrame;
import com.zj.show.GameMainJPanel;

public class GameStart {
	/**
	 * ����Ψһ���
	 */
	public static void main(String[] args) {
		GameJFrame gj=new GameJFrame();
		
		//���̼���ע��
		GameListener listener=new GameListener();
		gj.setKeyListener(listener);
		//���ע��
		GameMainJPanel gp=new GameMainJPanel();
		gj.setjPanel(gp);
		//���߳�ע��
		GameThread gt=new GameThread();
		gj.setThread(gt);
		
		gj.start();
	}

}
