package com.zj.element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * 
 * @说明：所有元素的基类
 * @author lenovo
 *
 */

public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	private boolean live=true; //生存状态，true代表存在，false代表死亡，可以采用枚举值来设置这个
	
	public ElementObj() { //这个构造其实没有作用，只是为了继承时不会报错
		
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	/**
	 * @说明 带参数的构造方法，可以由子类传递参数到父类
	 * @param x 左上角的x坐标
	 * @param y 左上角的y坐标
	 * @param w 宽度
	 * @param h 高度
	 * @param icon 图片
	 */
	public ElementObj(int x, int y, int w, int h, ImageIcon icon) {

		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
	}
	
	/**
	 * @说明 按键接收，只有需要键盘监听的子类才有这个方法，重写这个方法；
	 * @说明 也可以通过接口的方式来时间键盘监听
	 * @题外话 约定大于配置
	 * @param boolean 点击的类型，true代表按下，false代表松开
	 * @param key代表触发键盘的code值
	 * @扩展 本方法是否可以分为两个方法？1个接受按下，1个接受松开
	 */
	public void keyClick(boolean bl,int key) {	//约定：这个方法不是强制要求实现的
		
	}
	
	/**
	 * @说明 移动方法；需要移动的子类，请实现这个方法
	 */
	protected void move() {	//约定：这个方法不是强制要求实现的
		
	}
	
	/**
	 * @设计模式 模板模式；在模板模式中定义对象执行方法的先后顺序，由子类选择性重写方法
	 * 	1.移动 2.换装 3.发射子弹
	 */
	public final void model(long gameTime) { //使用模板的好处是让主线程不需要再进行过多的修改
		//先换装
		updateImage(gameTime);
		//再移动
		move();
		//发射子弹
		add(gameTime);
	}
	
	//long ... gameTimes表示gametime是一个不定长的数组
	protected void updateImage(long gameTime) {}
	protected void add(long gameTime) {}
	
	public ElementObj createElement(String str) {
		
		return null;
	}
	
	/**
	 * @说明 抽象方法，显示元素
	 * @param g 画笔 用于进行绘画
	 */
	public abstract void showElement(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	//死亡方法 给子类继承
	public void die() { //死亡也是一个对象
		
	}
	
	/**
	 * 只要是VO类就要为所有属性添加get和Set方法
	 */
	
	/**
	 * @说明 本方法返回元素的碰撞矩形对象（实时返回）
	 * @return
	 */
	public Rectangle getRectangle() {
		//可以将这个数据进行处理
		return new Rectangle(x,y,w,h);
	}
	
	/**
	 * @说明 碰撞方法
	 * @param obj
	 * @return 返回true说明有碰撞；反之
	 */
	public boolean pk(ElementObj obj) {
		return this.getRectangle().intersects(obj.getRectangle());
	}
}
