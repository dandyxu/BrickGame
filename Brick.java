package com.brick;

//本类用于控制砖块

import javax.swing.JLabel;

public class Brick extends JLabel {

	// 定义一个砖块的坐标，方向
	private int bx, by;

	private int direction;

	private int speed;

	public Brick() {

	}

	public Brick(int x, int y) {
		this.bx = x;
		this.by = y;

	}

	public Brick(int x, int y, int direction, int speed) {
		this.bx = x;
		this.by = y;
		this.direction = direction;
		this.speed = speed;

	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBx() {
		return bx;
	}

	public void setBx(int bx) {
		this.bx = bx;
	}

	public int getBy() {
		return by;
	}

	public void setBy(int by) {
		this.by = by;
	}

}
