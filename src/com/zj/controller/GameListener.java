package com.zj.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zj.element.ElementObj;
import com.zj.manager.ElementManager;
import com.zj.manager.GameElement;

/**
 * @˵�� �����࣬���ڼ����û��Ĳ���KeyLISTENER
 * @author lenovo
 * @����Ӧ��ֻ�������ı��ɫ״̬���������ƶ�Ӧ������Ϸ��������ʵ��
 */

public class GameListener implements KeyListener{
	private ElementManager em=ElementManager.getManager();
	
	/**
	 * @˵�� ͨ��һ��SET�������ж����жϰ��µĽ��Ƿ��ظ�����������ظ���ֱ�ӽ�����ֻ���ڵ�һ�ΰ��°����ǲŻ��ֵ���뵽����֮��
	 */
	private Set<Integer> set=new HashSet<Integer>();

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(set.contains(key)) { //�ж��������Ƿ�����ظ����Ľ�
			return;
		}
		set.add(key);
		List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play) {
			obj.keyClick(true,e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		if(!set.contains(key)) { //�ж��������Ƿ�����ظ����Ľ�
			return;
		}
		set.remove(key); //�Ƴ�����
		List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play) {
			obj.keyClick(false,e.getKeyCode());
		}	
	}
	
	
}
