package com.zj.element;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;

import com.zj.manager.ElementManager;
import com.zj.manager.GameElement;

/**
 * �������������࣬�����û���ȡ�����ļ��Ĺ��ߣ����������д���ṩ����static��������������ͼƬ����Դ
 * @author lenovo
 *
 */
public class GameLoad {
	private static ElementManager em=ElementManager.getManager();
	
	public static Map<String, ImageIcon> imagMap;
	public static Map<String,ImageIcon> imagPlay;
	private static Properties pro=new Properties();

	
	static {
		loadImg();
	}
	
	
	/**
	 * @˵�� �����ͼid�ɼ��ط���һ���ļ������Զ�������ͼ�ļ����ƣ������ļ�
	 * @param mapId �ļ���ţ��ļ�ID
	 */
	public static void MapLoad(int mapId) {
		String mapNameString="com/zj/text/"+mapId+".map";
		//ʹ��io������ȡ�ļ�����
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream mapStream=classLoader.getResourceAsStream(mapNameString);
		if(mapStream==null) {
			System.out.println("�����ļ���ȡ�쳣�������°�װ");
			return;
		}
		pro.clear();
		try {
			pro.load(mapStream);
			Enumeration<?> names=pro.propertyNames();
			//����ֱ�Ӷ�̬�ػ�ȡkey,��key�Ϳ��Ի�ȡvalue
			while(names.hasMoreElements()) {
				String keyString=names.nextElement().toString();
				String[] arrs=pro.getProperty(keyString).split(";");
				for(int i=0;i<arrs.length;i++) {
					em.addElement(new MapObj().createElement(keyString+","+arrs[i]), GameElement.MAPS);
				}
				
 			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @˵�� ����ͼƬ����
	 * 
	 */
	public static void loadImg() { //���Դ�����
		imagPlay=new HashMap<>();
		pro=new Properties();
		String gameData="com/zj/text/GameData.pro";
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream mapStream=classLoader.getResourceAsStream(gameData);
		if(mapStream==null) {
			System.out.println("�����ļ���ȡ�쳣�������°�װ");
			return;
		}
		pro.clear();
		try {
			pro.load(mapStream);
			Enumeration<?> names=pro.propertyNames();
			//����ֱ�Ӷ�̬�ػ�ȡkey,��key�Ϳ��Ի�ȡvalue
			while(names.hasMoreElements()) {
				String keyString=names.nextElement().toString();
				String arr=pro.getProperty(keyString);
				imagPlay.put(keyString, new ImageIcon(arr));
	 		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
