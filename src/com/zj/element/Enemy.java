package com.zj.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy extends ElementObj{
	
	public Enemy() {
		
	}

	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(),this.getX(),this.getY(),this.getW(),this.getH(), null);
	}
	
	public ElementObj creatElementObj(String str) {
		Random random=new Random();
		int x=random.nextInt(500);
		int y=random.nextInt(600);
		this.setX(x);
		this.setY(y);
		this.setW(50);
		this.setH(50);
		this.setIcon(new ImageIcon("image/image/tank/bot/bot_up.png"));
		return this;
	}
	
	@Override
	protected void move() {
		// TODO Auto-generated method stub
		super.move();
	}
}
