package com.zj.element;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * @˵�� ����ӵ��࣬�����ʵ�����������Ҷ�����úʹ���
 * @author lenovo
 *
 */

public class PlayFile extends ElementObj{
	private int attack;
	private int moveNum=5;
	private String fx;
	
	public PlayFile() {
		
	}
	
	//�Դ������̽��з�װ�����ֻ��Ҫ�����Ҫ��Լ������������ֵ�����������ʵ�壬����Ҳ��Ϊ��С������
	//С�����к��д������裬Ϊ�˼����Ƿ����ӵ�ʱ�����ӵ��Ĺ�������Ҫ�Ĵ�������
	@Override
	public ElementObj createElement(String str) {  //�Լ������ַ����Ĺ���
		String[] split=str.split(",");
		for(String str1:split) {
			String[] split2=str1.split(":");
			switch (split2[0]) {
			case "x":this.setX(Integer.parseInt(split2[1]));break;
			case "y":this.setY(Integer.parseInt(split2[1]));break;
			case "f":
				this.fx=split2[1];
				switch (this.fx) {
				case "up":
					this.setX(this.getX()+20);
					break;
				case "left":
					this.setY(this.getY()+20);
					break;
				case "right":
					this.setX(this.getX()+40);
					this.setY(this.getY()+20);
					break;
				case "down":
					this.setY(this.getY()+40);
					this.setX(this.getX()+20);
					break;
				default:
					break;
				}
				break;
			}
		}
		this.setW(10);
		this.setH(10);
		return this;
	}
	
	@Override
	public void showElement(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(this.getX(), this.getY(), this.getW(), this.getH());
	}
	
	@Override
	protected void move() {
		switch (this.fx) {
		case "up":this.setY(this.getY()-this.moveNum);break;
		case "down":this.setY(this.getY()+this.moveNum);break;
		case "left":this.setX(this.getX()-this.moveNum);break;
		case "right":this.setX(this.getX()+this.moveNum);break;
		default:
			break;
		}
		if(this.getX()<0||this.getX()>500||this.getY()<0||this.getY()>600) {
			this.setLive(false);
			return;
		}
	}
	
	/**
	 * �����ӵ���˵���ӵ�����������У�1.���߽磻2.��ײ��3.��ҷű���
	 * ����ʽ�����ﵽ����������ʱ��ֻ��������״̬�ĸı䣬���ӵ�����ʧӦ�������߳��д���
	 */
	
	public void die() {
		
	}
}
