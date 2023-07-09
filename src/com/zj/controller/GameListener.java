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
 * @说明 监听类，用于监听用户的操作KeyLISTENER
 * @author lenovo
 * @监听应该只是用来改变角色状态，而真正移动应该又游戏的主程来实现
 */

public class GameListener implements KeyListener{
	private ElementManager em=ElementManager.getManager();
	
	/**
	 * @说明 通过一个SET集合来判断来判断按下的健是否重复触发，如果重复就直接结束，只有在第一次按下按键是才会把值加入到集合之中
	 */
	private Set<Integer> set=new HashSet<Integer>();

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(set.contains(key)) { //判定集合中是否包含重复按的健
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
		if(!set.contains(key)) { //判定集合中是否包含重复按的健
			return;
		}
		set.remove(key); //移除数据
		List<ElementObj> play=em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play) {
			obj.keyClick(false,e.getKeyCode());
		}	
	}
	
	
}
