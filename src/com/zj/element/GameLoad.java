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
 * 加载器（工具类，用于用户读取配置文件的工具），工具类中大多提供的是static方法，包括所有图片等资源
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
	 * @说明 传入地图id由加载方法一句文件规则自动产生地图文件名称，加载文件
	 * @param mapId 文件编号，文件ID
	 */
	public static void MapLoad(int mapId) {
		String mapNameString="com/zj/text/"+mapId+".map";
		//使用io流来获取文件对象
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream mapStream=classLoader.getResourceAsStream(mapNameString);
		if(mapStream==null) {
			System.out.println("配置文件读取异常，请重新安装");
			return;
		}
		pro.clear();
		try {
			pro.load(mapStream);
			Enumeration<?> names=pro.propertyNames();
			//可以直接动态地获取key,有key就可以获取value
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
	 * @说明 加载图片代码
	 * 
	 */
	public static void loadImg() { //可以带参数
		imagPlay=new HashMap<>();
		pro=new Properties();
		String gameData="com/zj/text/GameData.pro";
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream mapStream=classLoader.getResourceAsStream(gameData);
		if(mapStream==null) {
			System.out.println("配置文件读取异常，请重新安装");
			return;
		}
		pro.clear();
		try {
			pro.load(mapStream);
			Enumeration<?> names=pro.propertyNames();
			//可以直接动态地获取key,有key就可以获取value
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
