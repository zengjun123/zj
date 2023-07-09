package com.zj.controller;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.swing.ImageIcon;

import com.zj.element.ElementObj;
import com.zj.element.Enemy;
import com.zj.element.GameLoad;
import com.zj.element.Play;
import com.zj.manager.ElementManager;
import com.zj.manager.GameElement;

/**
 * @˵�� ��Ϸ�����̣߳����ڿ�����Ϸ�ļ��أ���Ϸ�ؿ�����Ϸ����ʱ���Զ�����������Ϸ�ж�����������Ϸ��ͼ�л� ��Դ�ͷź����¶�ȡ
 * @author lenovo
 *
 */
public class GameThread extends Thread{
	private ElementManager em;
	
	public GameThread() {
		init();
	}
	
	public void init(){
		em=ElementManager.getManager();
	}
	
	@Override
	public void run() { //��Ϸ��run���� ���߳�
		super.run();
		while(true) {
		//��Ϸ��ʼǰ ����������������Ϸ��Դ�򳡾���Դ
			gameLoad();
		//��Ϸ����ʱ ��Ϸ������
			gameRun();
		//��Ϸ�ؿ�����  ��Ϸ��Դ���գ�������Դ��
			gameOver();
			try {
				sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��Ϸ����
	 */
	private void gameLoad() {
		GameLoad.MapLoad(5);
		load();
	}
	
	/**
	 * @˵�� ��Ϸ����ʱ
	 * @����˵�� 1.�Զ�����ҵ��ƶ�����ײ������ 2.��Ԫ�ص����ӣ�NPC��������ֵ��ߣ�3.��Ϸ��ͣ�ȵ�
	 */
	private void gameRun() {
		long gameTime=0L;
		while(true) { //Ԥ����չ��true����Ϊ�������ڿ��ƹؿ��Ľ�����
			Map<GameElement, List<ElementObj>> all=em.getGameElements();
//			GameElement.values(); //ö�����͵����ط���������ֵ��һ�����飬��������˳����Ƕ������ö�ٵ�˳��
			List<ElementObj> enemy=em.getElementsByKey(GameElement.ENEMY);
			List<ElementObj> file=em.getElementsByKey(GameElement.PLAYFILE);
			
			moveAndUpdate(all, gameTime);
			
			ElementPK(GameElement.ENEMY,GameElement.PLAYFILE);
			ElementPK(GameElement.PLAYFILE, GameElement.MAPS);
			
			gameTime++;
			try {
				sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ElementPK(GameElement gm1,GameElement gm2) {
		List<ElementObj> list1=em.getElementsByKey(gm1);
		List<ElementObj> list2=em.getElementsByKey(gm2);
		for(int i=0;i<list1.size();i++) {
			ElementObj e=list1.get(i);
			for(int j=0;j<list2.size();j++) {
				ElementObj f=list2.get(j);
				if(e.pk(f)) {
					e.setLive(false);
					f.setLive(false);
				}
			}
		}
	}
	
	//��ϷԪ���Զ���
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all,long gameTime) {
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list=all.get(ge);
			//��д����ֱ�Ӳ����������ݵĴ��뽨�鲻Ҫʹ�õ���������Ϊ�ڵײ����ݷ����仯ʱ�������ᱨ��
			//��������һ�������ݱȽ��ȶ��������ʹ��
			for(int i=list.size()-1;i>=0;i--) {
				ElementObj obj=list.get(i);
				if(!obj.isLive()) {
					//������Ч,����һ�����������������п������������磺������������װ����
					obj.die();
					list.remove(i);
					continue;
				}
				obj.model(gameTime);
			}
		}
	}
	
	/**
	 * ��Ϸ�л��ؿ�
	 */
	private void gameOver() {
		// TODO Auto-generated method stub
		
	}
	
	public void load() {
		//ͼƬ����
		ImageIcon icon=new ImageIcon("image/̹�˴�ս/image/tank/play1/player1_up.png");
		ElementObj obj=new Play(100, 100, 50, 50, icon); //ʵ��������
		em.getElementsByKey(GameElement.PLAY).add(obj); 
		//em.addElement(obj,GameElement.PLAY);
		
		//��������
		for(int i=0;i<10;i++) {

			em.addElement(new Enemy().creatElementObj(""),GameElement.ENEMY);

		}
	}

}
