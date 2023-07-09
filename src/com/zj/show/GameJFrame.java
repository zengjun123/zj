package com.zj.show;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @˵������Ϸ���壬��Ҫʵ�ֵĹ��ܣ���ʾ���رպ������С��
 * @author lenovo
 * @��ҪǶ����壬�������߳�
 * @����˵�� ����Ĵ�С�Լ���ʽ��Ҫ���������ļ�����
 * @���� 1.���󶨵����� 2.������ 3.��Ϸ���߳����� 4.��ʾ����
 */

public class GameJFrame extends JFrame{
	public static int GameX=750;  //�ȸ������С����һ����ʼֵ,����Ϊpublic��Ϊ���ṩ�������ļ��Ĵ洢��ȡ����
	public static int GameY=600;
	private JPanel jPanel=null;  //������ʾ�����
	private KeyListener keyListener=null;  //���̼���
	private MouseMotionListener motionListener=null;
	private MouseListener mouseListener=null;
	private Thread thread=null;  //���߳�
	
	/*setע�룺��ssm�У�ͨ��set����ע�������ļ��ж�ȡ�����ݣ��������ļ��е�ֵ��ֵΪ������ԣ�����һ���ӹ���ע�룬ֻ����Ҫ��Ϲ��췽��
	 * Դ��spring��ioc���ж�����Զ����ɣ�����
	 */
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}

	public void setMotionListener(MouseMotionListener motionListener) {
		this.motionListener = motionListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public GameJFrame() {
		init();
	}
	
	private void init() {
		this.setSize(GameX,GameY);
		this.setTitle("�ɻ���ս");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�����˳����ҹر�
		this.setLocationRelativeTo(null); //��Ļ����
	}
	
	public void start() {
		if(jPanel!=null) {
			this.add(jPanel);
		}
		if(keyListener!=null) {
			this.addKeyListener(keyListener);
		}
		if(thread!=null) {
			this.thread.start();		//�߳�����
		}
		if(this.jPanel instanceof Runnable) {
			new Thread((Runnable)this.jPanel).start();
		}
		this.setVisible(true);
	}
	
	/*���岼��:���Դ浵������*/
	public void addButton() {
		
	}
}
