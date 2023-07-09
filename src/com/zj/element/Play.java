package com.zj.element;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.zj.manager.ElementManager;
import com.zj.manager.GameElement;

public class Play extends ElementObj{
	/*
	 * 移动属性：1.单属性，配合我们方向的枚举类型使用，但一次只能移动一个方向；
	 * 2.双属性：上下和左右 可以实现斜着走，可以配合boolean值来使用，true代表上,false代表下，需要另一个值来确定是否按下方向键
	 * 3.4属性：上下左右都可以 boolean配合使用true代表移动，false代表不提懂，同时按下和上时，后按的会重置先按的
	 * @说明：当游戏有多状态时，可以使用map<范型，Boolean>；判定对象中有时间
	 */
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	private boolean right=false;
	private int moveNum=3;
	
	//变量专门用来记录当前主角面向的方向，默认为up
	private String fx="up";
	private boolean pkType=false; //攻击状态 true为攻击，false为停止
	
	public Play(int x, int y, int w, int h, ImageIcon icon) {
		super(x, y, w, h, icon);
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(),this.getX(),this.getY(),this.getW(),this.getH(), null);
	}
	
	@Override
	public void keyClick(boolean bl,int key) {
		if(bl) {
			switch (key) {
			case 37:
				this.right=false;this.up=false;this.down=false;
				this.left=true;this.fx="left";
				break;
			case 38:
				this.down=false;this.right=false;this.left=false;
				this.up=true;this.fx="up";
				break;
			case 39:
				this.left=false;this.up=false;this.down=false;
				this.right=true;this.fx="right";
				break;
			case 40:
				this.up=false;this.right=false;this.left=false;
				this.down=true;this.fx="down";
				break;
			case 32:
				this.pkType=true;
				break;
			default:break;
			}
		}else {
			switch (key) {
			case 37:this.left=false;break;
			case 38:this.up=false;break;
			case 39:this.right=false;break;
			case 40:this.down=false;break;
			case 32:this.pkType=false;break; 
			default:break;
			}
		}
	}
	
	/**
	 * @重写规则 1.重写的方法方法名和返回值必须和父类一样
	 * 			2.重写的方法的传入参数类型序列，必须和父类一样
	 * 			3.重写的方法访问修饰符只能比父类更加宽泛
	 */
	@Override
	protected void move() {
		if(this.left && this.getX()>0) {
			this.setX(this.getX()-this.moveNum);
		}
		if(this.up && this.getY()>0) {
			this.setY(this.getY()-this.moveNum);
		}
		if(this.right && this.getX()<500-this.getW()) {
			this.setX(this.getX()+this.moveNum);
		}
		if(this.down && this.getY()<600-this.getH()) {
			this.setY(this.getY()+this.moveNum);
		}
	}
	
	@Override
	protected void updateImage(long gametime) {
		this.setIcon(GameLoad.imagPlay.get(fx));
	}
	
	private long filetime=0;
	//filefitme和传入的时间gameTime进行比较，赋值等操作运算，控制子弹的间隔
	@Override
	protected void add(long gameTime) { //发射子弹
		if(!this.pkType||this.filetime+4>=gameTime) {
			return;
		}
		//this.pkType=false; //按一下只能够发射一次
		//当构造一个类需要做比较多的工作时可以选择小工厂将构造对象的多个步骤进行封装成为一个方法，返回值直接是这个对象
		//json格式 {x:3,y:5,f:up}
		ElementObj element=new PlayFile().createElement(this.toString());
		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
		this.filetime=gameTime;
	}
	
	@Override
	public String toString() {
		//json格式{x:3,y:5,f:up}
		return "x:"+this.getX()+",y:"+this.getY()+",f:"+this.fx;
	}
}
