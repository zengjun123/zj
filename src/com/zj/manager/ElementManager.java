package com.zj.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.element.ElementObj;

/**
 * @˵�� ������Ԫ�ع�������ר�Ŵ洢���е�Ԫ�أ�ͬʱ���ṩ����������ͼ�Ϳ��ƻ�ȡ����
 * @author lenovo
 * @˵�� ������ֻ��һ��������ģʽ
 */


public class ElementManager {
//	private List<Object> listMap;
//	private List<Object> listPlayList;
	/**
	 * String��Ϊkeyƥ�����е�Ԫ�� ����Map -> listMap;
	 * ö�����͵���Map��key���������ֲ�һ������Դ��������Դ��ȡ
	 * ���е�Ԫ�ض������map�����У���ʾģ��ֻ��Ҫ��ȡ���map�Ϳ��Ի�ý�������Ҫ������Ԫ��
	 */
	private Map<GameElement, List<ElementObj>> gameElements;
	
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
	
	private static ElementManager EM;
	
	//synchronized�߳���->��֤������ֻ��һ���߳��ڵ��ã����ڱ���ģʽ
	public static synchronized ElementManager getManager() {
		if(EM==null) {
			EM=new ElementManager();
		}
		return EM;
	}
	
	//���Ԫ�أ��ɼ��������ã�
	public void addElement(ElementObj obj,GameElement ge) {
		gameElements.get(ge).add(obj);
	}
	//����key����list���Ϸ���ĳһ��Ԫ��
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	private ElementManager() {
		init(); //ʵ���������Է�ֹ�౻�̳�ʱ���췽���޷����̳����������չ�Ե���
	}
	
	public void init() {  //��������Ϊ��������ʵ�ֵĹ�����չ��д�ĳ�ʼ�����������Ըı���������ʵ����
		gameElements=new HashMap<GameElement,List<ElementObj>>();
//		gameElements.put(GameElement.PLAY,new ArrayList<ElementObj>());
//		gameElements.put(GameElement.MAPS,new ArrayList<ElementObj>());
//		gameElements.put(GameElement.ENEMY,new ArrayList<ElementObj>());
//		gameElements.put(GameElement.BOSS,new ArrayList<ElementObj>());
		for(GameElement ge:GameElement.values()) {
			gameElements.put(ge,new ArrayList<ElementObj>()); //��������Ҫ�������ʱ����ֻ�޸�ö�����͵�ֵ��������Ҫ�޸������ֵ
		}
	}
	
//	static {  ����ģʽʵ������̬���� static�������౻���ص�ʱ��ֱ��ִ��
//		EM=new ElementManager(); 
//	}
}
