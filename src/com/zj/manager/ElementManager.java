package com.zj.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.element.ElementObj;

/**
 * @说明 本类是元素管理器，专门存储所有的元素，同时，提供方法给予视图和控制获取数据
 * @author lenovo
 * @说明 管理器只有一个，单例模式
 */


public class ElementManager {
//	private List<Object> listMap;
//	private List<Object> listPlayList;
	/**
	 * String作为key匹配所有的元素 例如Map -> listMap;
	 * 枚举类型当做Map的key来用来区分不一样的资源，用于资源获取
	 * 所有的元素都存放在map集合中，显示模块只需要获取这个map就可以获得界面所需要的所有元素
	 */
	private Map<GameElement, List<ElementObj>> gameElements;
	
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
	
	private static ElementManager EM;
	
	//synchronized线程锁->保证本方法只有一个线程在调用，属于饱汉模式
	public static synchronized ElementManager getManager() {
		if(EM==null) {
			EM=new ElementManager();
		}
		return EM;
	}
	
	//添加元素（由加载器调用）
	public void addElement(ElementObj obj,GameElement ge) {
		gameElements.get(ge).add(obj);
	}
	//依据key返回list集合返回某一类元素
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	private ElementManager() {
		init(); //实例化方法以防止类被继承时构造方法无法被继承因而导致扩展性低下
	}
	
	public void init() {  //本方法是为将来可能实现的功能扩展重写的初始化方法，可以改变这个对象的实例化
		gameElements=new HashMap<GameElement,List<ElementObj>>();
//		gameElements.put(GameElement.PLAY,new ArrayList<ElementObj>());
//		gameElements.put(GameElement.MAPS,new ArrayList<ElementObj>());
//		gameElements.put(GameElement.ENEMY,new ArrayList<ElementObj>());
//		gameElements.put(GameElement.BOSS,new ArrayList<ElementObj>());
		for(GameElement ge:GameElement.values()) {
			gameElements.put(ge,new ArrayList<ElementObj>()); //当我们想要添加类型时可以只修改枚举类型的值，而不需要修改这里的值
		}
	}
	
//	static {  饿汉模式实例化静态对象 static语句块在类被加载的时候直接执行
//		EM=new ElementManager(); 
//	}
}
