package com.zj.show;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.zj.element.ElementObj;
import com.zj.element.Play;
import com.zj.manager.ElementManager;
import com.zj.manager.GameElement;

/**
 * @说明 游戏的主要面板
 * @author lenovo
 * @功能说明 进行元素的显示，同时进行界面的刷新（多线程）
 * @java开发首先实现思考的应该是：做继承或者是接口实现
 * 
 * @多线程刷新 1.本类实现线程接口
 * 			  2.本类中定义一个内部类俩实现
 */

public class GameMainJPanel extends JPanel implements Runnable{
	private ElementManager em;
	
	public GameMainJPanel() {
		init();
	}
	
	public void init(){
		em=ElementManager.getManager();
	}
	
	@Override
	//用于绘画
	public void paint(Graphics g) {
		super.paint(g);
		
		Map<GameElement, List<ElementObj>> all=em.getGameElements();
//		GameElement.values(); //枚举类型的隐藏方法，返回值是一个数组，这个数组的顺序就是定义这个枚举的顺序
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list=all.get(ge);
			for(int i=0;i<list.size();i++) {
				ElementObj obj=list.get(i);
				obj.showElement(g);
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			this.repaint();
			//一般多线程都会实现休眠
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
