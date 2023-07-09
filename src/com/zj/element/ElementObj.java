package com.zj.element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * 
 * @˵��������Ԫ�صĻ���
 * @author lenovo
 *
 */

public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	private boolean live=true; //����״̬��true������ڣ�false�������������Բ���ö��ֵ���������
	
	public ElementObj() { //���������ʵû�����ã�ֻ��Ϊ�˼̳�ʱ���ᱨ��
		
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	/**
	 * @˵�� �������Ĺ��췽�������������ഫ�ݲ���������
	 * @param x ���Ͻǵ�x����
	 * @param y ���Ͻǵ�y����
	 * @param w ���
	 * @param h �߶�
	 * @param icon ͼƬ
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
	 * @˵�� �������գ�ֻ����Ҫ���̼�����������������������д���������
	 * @˵�� Ҳ����ͨ���ӿڵķ�ʽ��ʱ����̼���
	 * @���⻰ Լ����������
	 * @param boolean ��������ͣ�true�����£�false�����ɿ�
	 * @param key���������̵�codeֵ
	 * @��չ �������Ƿ���Է�Ϊ����������1�����ܰ��£�1�������ɿ�
	 */
	public void keyClick(boolean bl,int key) {	//Լ���������������ǿ��Ҫ��ʵ�ֵ�
		
	}
	
	/**
	 * @˵�� �ƶ���������Ҫ�ƶ������࣬��ʵ���������
	 */
	protected void move() {	//Լ���������������ǿ��Ҫ��ʵ�ֵ�
		
	}
	
	/**
	 * @���ģʽ ģ��ģʽ����ģ��ģʽ�ж������ִ�з������Ⱥ�˳��������ѡ������д����
	 * 	1.�ƶ� 2.��װ 3.�����ӵ�
	 */
	public final void model(long gameTime) { //ʹ��ģ��ĺô��������̲߳���Ҫ�ٽ��й�����޸�
		//�Ȼ�װ
		updateImage(gameTime);
		//���ƶ�
		move();
		//�����ӵ�
		add(gameTime);
	}
	
	//long ... gameTimes��ʾgametime��һ��������������
	protected void updateImage(long gameTime) {}
	protected void add(long gameTime) {}
	
	public ElementObj createElement(String str) {
		
		return null;
	}
	
	/**
	 * @˵�� ���󷽷�����ʾԪ��
	 * @param g ���� ���ڽ��л滭
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
	
	//�������� ������̳�
	public void die() { //����Ҳ��һ������
		
	}
	
	/**
	 * ֻҪ��VO���ҪΪ�����������get��Set����
	 */
	
	/**
	 * @˵�� ����������Ԫ�ص���ײ���ζ���ʵʱ���أ�
	 * @return
	 */
	public Rectangle getRectangle() {
		//���Խ�������ݽ��д���
		return new Rectangle(x,y,w,h);
	}
	
	/**
	 * @˵�� ��ײ����
	 * @param obj
	 * @return ����true˵������ײ����֮
	 */
	public boolean pk(ElementObj obj) {
		return this.getRectangle().intersects(obj.getRectangle());
	}
}
