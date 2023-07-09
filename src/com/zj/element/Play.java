package com.zj.element;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.zj.manager.ElementManager;
import com.zj.manager.GameElement;

public class Play extends ElementObj{
	/*
	 * �ƶ����ԣ�1.�����ԣ�������Ƿ����ö������ʹ�ã���һ��ֻ���ƶ�һ������
	 * 2.˫���ԣ����º����� ����ʵ��б���ߣ��������booleanֵ��ʹ�ã�true������,false�����£���Ҫ��һ��ֵ��ȷ���Ƿ��·����
	 * 3.4���ԣ��������Ҷ����� boolean���ʹ��true�����ƶ���false�����ᶮ��ͬʱ���º���ʱ���󰴵Ļ������Ȱ���
	 * @˵��������Ϸ�ж�״̬ʱ������ʹ��map<���ͣ�Boolean>���ж���������ʱ��
	 */
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	private boolean right=false;
	private int moveNum=3;
	
	//����ר��������¼��ǰ��������ķ���Ĭ��Ϊup
	private String fx="up";
	private boolean pkType=false; //����״̬ trueΪ������falseΪֹͣ
	
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
	 * @��д���� 1.��д�ķ����������ͷ���ֵ����͸���һ��
	 * 			2.��д�ķ����Ĵ�������������У�����͸���һ��
	 * 			3.��д�ķ����������η�ֻ�ܱȸ�����ӿ�
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
	//filefitme�ʹ����ʱ��gameTime���бȽϣ���ֵ�Ȳ������㣬�����ӵ��ļ��
	@Override
	protected void add(long gameTime) { //�����ӵ�
		if(!this.pkType||this.filetime+4>=gameTime) {
			return;
		}
		//this.pkType=false; //��һ��ֻ�ܹ�����һ��
		//������һ������Ҫ���Ƚ϶�Ĺ���ʱ����ѡ��С�������������Ķ��������з�װ��Ϊһ������������ֱֵ�����������
		//json��ʽ {x:3,y:5,f:up}
		ElementObj element=new PlayFile().createElement(this.toString());
		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
		this.filetime=gameTime;
	}
	
	@Override
	public String toString() {
		//json��ʽ{x:3,y:5,f:up}
		return "x:"+this.getX()+",y:"+this.getY()+",f:"+this.fx;
	}
}
