package com.brick;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrickJpanel extends JPanel implements MouseMotionListener,
		KeyListener, ActionListener {
	// 定义一个变量，用于存放游戏的分数和关数等级
	public int score = 0, level = 0;

	// 定义一个变量，用于初始化Brick
	Brick br = new Brick();

	// 定义一个变量，用于初始化Bar
	Bar b = new Bar();

	// 定义一个集合，用于存放砖块
	ArrayList<Brick> bricks = new ArrayList<Brick>();

	// 定义一个变量，用于初始化Ball
	Ball ba = new Ball();

	// 定义一个标签，用于显示游戏结束
	JLabel jl = new JLabel("Game Over!");

	// 定义一个布尔类型变量，用于结束游戏，并输出所得分数及关数
	public boolean pause = true;

	// // 定义一个标签，用于显示倒计时器
	// JLabel ctime = new JLabel();
	// int hours = 0, minutes = 0, seconds = 0;

	// 定义一个变量，用于存放剩余的生命次数
	private int heart = 3;

	// 定义一个标签，用于显示生命次数
	JLabel life = new JLabel("生命次数：" + heart);

	JLabel jl3 = new JLabel("游戏结束,您得到" + score + "分，共闯过" + level + "关");

	// 定义一个运动小球的速度
	private int speed = 1;

	// 定义一个运动小球的四个方向
	public static final int left_up = 0;
	public static final int left_down = 1;
	public static final int right_up = 2;
	public static final int right_down = 3;

	// 定义一个运动小球的方向
	private static int direction = left_up;

	// 定义一个构造方法，用于面板的初始化
	public BrickJpanel() {
		this.setBackground(Color.BLACK);

		// JLabel jl2 = new JLabel("按空格键开始游戏");
		// jl2.setHorizontalAlignment(JLabel.CENTER);
		// jl2.setVerticalAlignment(JLabel.CENTER);
		// jl2.setVisible(false);
		// this.add(jl2);

		life.setForeground(Color.WHITE);
		life.setHorizontalAlignment(JLabel.CENTER);
		life.setVerticalAlignment(JLabel.CENTER);
		life.setVisible(true);
		this.add(life);

		// 加载jl3，用于控制输出分数及关数标签
		jl3.setForeground(Color.WHITE);
		jl3.setVisible(false);
		this.add(jl3);

		addKeyListener(this);
		this.setFocusable(true);

		Brickinit();

		// 加载jl，用于控制输出“游戏结束”标签
		jl.setLocation(300, 300);
		jl.setFont(new Font("宋体", 50, 50));
		jl.setVisible(false);
		jl.setForeground(Color.WHITE);
		this.add(jl);
		this.addMouseMotionListener(this);

	}

	// 定义一个画笔，用于画出三排砖块以及运动的小球
	public void paint(Graphics g) {
		super.paint(g);
		Random rd = new Random();
		// 直线加粗
		Graphics2D g2 = (Graphics2D) g;
		// ctime.setText(gettimes(hours, minutes, seconds));
		g2.setStroke(new BasicStroke(10));
		g2.drawLine(b.getX(), b.getY(), b.getX() + b.getLength(), b.getY());
		g.setColor(Color.WHITE);
		g.drawString("Level:" + level, 50, 50);
		g.setColor(Color.WHITE);
		g.drawString("Score：" + score, 450, 50);
		for (int i = 0; i < bricks.size(); i++) {
			g.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd
					.nextInt(255)));
			g.fillRoundRect(bricks.get(i).getBx(), bricks.get(i).getBy(), 20,
					20, 5, 5);
		}
		g.setColor(Color.BLUE);
		g.fillArc(ba.getBax(), ba.getBay(), 20, 20, 0, 360);
	}

	// 定义初始的三排砖块
	public void Brickinit() {
		for (int i = 0; i < 16; i++) {
			Brick temp = new Brick(100 + 22 * i, 125);
			bricks.add(temp);
		}
		for (int i = 0; i < 16; i++) {
			Brick temp = new Brick(100 + 22 * i, 150);
			bricks.add(temp);
		}
		for (int i = 0; i < 16; i++) {
			Brick temp = new Brick(100 + 22 * i, 175);
			bricks.add(temp);
		}
	}

	// 定义一个方法，用于画出一行砖块
	public void addBrick() {
		int k = 100;
		for (int i = 0; i < 16; i++) {
			Brick temp = new Brick(100 + 22 * i, k);
			bricks.add(temp);
		}
		k = k - 30;
	}

	// 定义一个方法，用于判断砖块碰撞情况
	public int BrickChekd() {
		int tag = 0;
		for (int i = 0; i < bricks.size(); i++) {
			// if (bricks.get(i).getX() <= ba.getBax() + 20
			// && bricks.get(i).getY() + 20 >= ba.getBay()) {
			// tag = 1;
			// } else if (bricks.get(i).getY() <= ba.getBay() + 20
			// && bricks.get(i).getY() + 20 >= ba.getBay()) {
			// tag = 1;
			// } else if (bricks.get(i).getX() <= ba.getBax() + 20
			// && bricks.get(i).getX() + 20 >= ba.getBax()) {
			// tag = 1;
			// } else if (bricks.get(i).getX() + 20 >= ba.getBax()
			// && bricks.get(i).getX() <= ba.getBax() + 20) {
			// tag = 1;
			// } else
			if ((((bricks.get(i).getBx() - ba.getBax())
					* (bricks.get(i).getBx() - ba.getBax()) + (bricks.get(i)
					.getBy() - ba.getBay())
					* (bricks.get(i).getBy() - ba.getBay())) <= 400)
					|| ba.getBay() > 600) {
				bricks.remove(i);
				score = score + 10;
				if (score % 100 == 0) {
					addBrick();
					b.setLength(b.getLength() * 9 / 10);
					level++;
				}

				jl3.setBounds(300, 300, 300, 300);
				jl3.setFont(new Font("宋体", 20, 20));
				jl3.setText("游戏结束,您得到" + score + "分，共闯过" + level + "关");
				tag = 1;
			}
			if (tag == 1)
				break;
		}
		return tag;
	}

	// 定义一个方法，用于判断砖块运动的方向
	public int BrickDirection() {
		int tag = 0;
		int tag1 = 1;
		switch (direction) {
		case left_down: {
			tag = BrickChekd();
			if (tag == 1) {
				direction = left_up;
			} else {
				if (ba.getBay() + 25 < 450 && ba.getBax() > 0) {
					ba.setBax(ba.getBax() - speed);
					ba.setBay(ba.getBay() + speed);
				} else if (ba.getBay() + 25 < 450 && ba.getBax() == 0)
					direction = right_down;
				else if (ba.getBay() + 25 >= 450 && ba.getBay() + 25 < 600) {
					if (ba.getBax() + 25 > b.getX()
							&& ba.getBax() + 25 < b.getX() + b.getLength())
						direction = left_up;
					else {
						heart--;
						life.setText("生命次数：" + heart);
						tag1 = 0;
					}
				}
			}
			break;
		}

		case left_up: {
			tag = BrickChekd();
			if (tag == 1) {
				direction = left_down;
			} else {
				if (ba.getBay() > 0 && ba.getBax() > 0) {
					ba.setBax(ba.getBax() - speed);
					ba.setBay(ba.getBay() - speed);
				} else if (ba.getBax() == 0)
					direction = right_up;
				else if (ba.getBay() == 0)
					direction = left_down;
			}
			break;
		}

		case right_up: {
			tag = BrickChekd();
			if (tag == 1) {
				direction = right_down;
			} else {
				if (ba.getBay() > 0 && ba.getBax() + 20 < 600) {
					ba.setBax(ba.getBax() + speed);
					ba.setBay(ba.getBay() - speed);
				} else if (ba.getBax() + 20 == 600)
					direction = left_up;
				else if (ba.getBay() == 0)
					direction = right_down;
			}
			break;
		}

		case right_down: {
			tag = BrickChekd();
			if (tag == 1) {
				direction = right_up;
			} else {
				if (ba.getBay() + 25 < 450 && ba.getBax() + 25 < 600) {
					ba.setBax(ba.getBax() + speed);
					ba.setBay(ba.getBay() + speed);
				} else if (ba.getBay() + 25 < 450 && ba.getBax() + 25 == 600)
					direction = left_down;
				else if (ba.getBay() + 25 >= 450 && ba.getBay() + 25 < 600) {
					if (ba.getBax() + 25 > b.getX()
							&& ba.getBax() + 25 < b.getX() + b.getLength())
						direction = right_up;
					else {
						heart--;
						life.setText("生命次数：" + heart);
						tag1 = 0;
					}

				}
			}
			break;
		}
		}
		return tag1;
	}

	// 定义一个方法，启动线程，用于小球（砖块）的运动
	public void start_Run() {
		new Thread() {
			public void run() {
				while (pause) {
					if (heart != 0) {
						int tag;
						tag = BrickDirection();

						if (tag == 0) {
							if (heart > 0) {
								ba = new Ball();
							}
						}
					} else {
						jl.setVisible(true);
						break;
					}
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}// endwhile
			}
		}.start();

	}

	public void setVisible(boolean flag) {
		super.setVisible(flag);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getX() + b.getLength() < 600) {
			b.setX(e.getX());
			repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == e.VK_SPACE) {
			pause = !pause;
			jl3.setVisible(true);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
