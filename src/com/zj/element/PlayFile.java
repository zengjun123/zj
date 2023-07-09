package com.zj.element;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * @说明 玩家子弹类，本类的实体对象是由玩家对象调用和创建
 * @author lenovo
 *
 */

public class PlayFile extends ElementObj{
	private int attack;
	private int moveNum=5;
	private String fx;
	
	public PlayFile() {
		
	}
	
	//对创建过程进行封装，外界只需要传输必要的约定参数，返回值就是这个对象实体，这样也成为了小工厂，
	//小工厂中含有大量步骤，为了简化主角发射子弹时创建子弹的过程所需要的大量过程
	@Override
	public ElementObj createElement(String str) {  //自己定义字符串的规则
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
	 * 对于子弹来说，子弹死亡的情况有：1.出边界；2.碰撞；3.玩家放保险
	 * 处理方式：当达到死亡的条件时，只进行死亡状态的改变，而子弹的消失应该由主线程中处理
	 */
	
	public void die() {
		
	}
}
