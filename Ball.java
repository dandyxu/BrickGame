package com.brick;

public class Ball {
	public int bax, bay, r;

	public Ball() {
		this.bax = (int) (Math.random() * 600) - 2 * r;
		this.bay = (int) (Math.random() * 450) - 2 * r;
	}

	public Ball(int x, int y, int r) {
		this.bax = (int) (Math.random() * 600) - 2 * r;
		this.bay = (int) (Math.random() * 450) - 2 * r;

	}

	public int getBax() {
		return bax;
	}

	public void setBax(int bax) {
		this.bax = bax;
	}

	public int getBay() {
		return bay;
	}

	public void setBay(int bay) {
		this.bay = bay;
	}

}
