package com.brick;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 * 本类用于控制游戏窗体
 */

public class BrickJframe extends JFrame {

	// 定义一个方法，用于显示窗体
	public void showMe() {
		this.setTitle("打砖块游戏");
		this.setBounds(100, 100, 600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	// 定义一个方法，用于控制面板的加载
	public void addPanel() {
		BrickJpanel fjp = new BrickJpanel();
		fjp.start_Run();
		this.add(fjp);
	}

	public static void main(String[] args)
			throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {
		BrickJframe fjf = new BrickJframe();
		fjf.addPanel();
		fjf.showMe();
		
		//添加背景音乐
		File wavFile = new File("BgMusic.wav");
		AudioInputStream ais = AudioSystem.getAudioInputStream(wavFile);
		Clip clip = AudioSystem.getClip();
		clip.open(ais);
		clip.start();

	}

}
