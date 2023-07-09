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
 * @˵�� ��Ϸ����Ҫ���
 * @author lenovo
 * @����˵�� ����Ԫ�ص���ʾ��ͬʱ���н����ˢ�£����̣߳�
 * @java��������ʵ��˼����Ӧ���ǣ����̳л����ǽӿ�ʵ��
 * 
 * @���߳�ˢ�� 1.����ʵ���߳̽ӿ�
 * 			  2.�����ж���һ���ڲ�����ʵ��
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
	//���ڻ滭
	public void paint(Graphics g) {
		super.paint(g);
		
		Map<GameElement, List<ElementObj>> all=em.getGameElements();
//		GameElement.values(); //ö�����͵����ط���������ֵ��һ�����飬��������˳����Ƕ������ö�ٵ�˳��
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
			//һ����̶߳���ʵ������
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
