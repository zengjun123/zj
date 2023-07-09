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
 * @说明 游戏的主线程，用于控制游戏的加载，游戏关卡，游戏运行时的自动化，还有游戏判定；还包括游戏地图切换 资源释放和重新读取
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
	public void run() { //游戏的run方法 主线程
		super.run();
		while(true) {
		//游戏开始前 读进度条，加载游戏资源或场景资源
			gameLoad();
		//游戏进行时 游戏过程中
			gameRun();
		//游戏关卡结束  游戏资源回收（场景资源）
			gameOver();
			try {
				sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 游戏加载
	 */
	private void gameLoad() {
		GameLoad.MapLoad(5);
		load();
	}
	
	/**
	 * @说明 游戏进行时
	 * @任务说明 1.自动化玩家的移动，碰撞，死亡 2.新元素的增加（NPC死亡后出现道具）3.游戏暂停等等
	 */
	private void gameRun() {
		long gameTime=0L;
		while(true) { //预留扩展，true可以为变量用于控制关卡的结束等
			Map<GameElement, List<ElementObj>> all=em.getGameElements();
//			GameElement.values(); //枚举类型的隐藏方法，返回值是一个数组，这个数组的顺序就是定义这个枚举的顺序
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
	
	//游戏元素自动化
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all,long gameTime) {
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list=all.get(ge);
			//编写这样直接操作集合数据的代码建议不要使用迭代器，因为在底层数据发生变化时迭代器会报错，
			//而迭代器一般在数据比较稳定的情况下使用
			for(int i=list.size()-1;i>=0;i--) {
				ElementObj obj=list.get(i);
				if(!obj.isLive()) {
					//死亡特效,启动一个死亡方法（方法中可以做事情例如：死亡动画，掉装备）
					obj.die();
					list.remove(i);
					continue;
				}
				obj.model(gameTime);
			}
		}
	}
	
	/**
	 * 游戏切换关卡
	 */
	private void gameOver() {
		// TODO Auto-generated method stub
		
	}
	
	public void load() {
		//图片导入
		ImageIcon icon=new ImageIcon("image/坦克大战/image/tank/play1/player1_up.png");
		ElementObj obj=new Play(100, 100, 50, 50, icon); //实例化对象
		em.getElementsByKey(GameElement.PLAY).add(obj); 
		//em.addElement(obj,GameElement.PLAY);
		
		//创建敌人
		for(int i=0;i<10;i++) {

			em.addElement(new Enemy().creatElementObj(""),GameElement.ENEMY);

		}
	}

}
