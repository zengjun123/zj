package com.zj.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MapObj extends ElementObj{
	private int hp; //ǿ��Ѫ��
	private String name;//ǽ�����ͣ�Ҳ����ʹ��ö��

	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String []arr=str.split(",");
		switch (arr[0]) {
		case "GRASS":this.setIcon(new ImageIcon("image/image/wall/grass.png"));break;
		case "BRICK":this.setIcon(new ImageIcon("image/image/wall/brick.png"));break;
		case "RIVER":this.setIcon(new ImageIcon("image/image/wall/river.png"));break;
		case "IRON":this.setIcon(new ImageIcon("image/image/wall/iron.png"));
					this.hp=4;
					this.name="IRON";
					break;
		}
		this.setX(Integer.parseInt(arr[1]));
		this.setY(Integer.parseInt(arr[2]));
		this.setW(getIcon().getIconWidth());
		this.setH(getIcon().getIconHeight());
		return this;
	}
	
	@Override //˵��������ÿ�Ѫ�ȷ�����Ҫ�Լ���д
	public void setLive(boolean live) {
		//������һ�ξͼ���һ��Ѫ
		if("IRON".equals(name)) { //ˮ��ǽ��Ҫ4ǹ
			this.hp--;
			if(this.hp>0) {
				return ;
			}
		}
		super.setLive(live);
	}
}
