/*
 * Authors: Zayaan and Brandon
 * Date: Jan 17 2023
 * Program Name: PVZ Main Program
 * Program Description: Main JFrame and cards layout
 */

import javax.swing.*;
import java.awt.*;

public class MyJava extends JFrame{
	static CardLayout cardsL;
	static Container c;


	public MyJava(){    //constructor
		c=getContentPane();
		cardsL=new CardLayout();
		c.setLayout(cardsL);

		TitleScreen titleP = new TitleScreen();
		MainMenu menuP = new MainMenu();
		GameOver lose = new GameOver();
		LevelPassed win = new LevelPassed();
		Instructions instruct = new Instructions();

		c.add("Title Screen", titleP);
		c.add("Main Menu", menuP);
		c.add("Game Over", lose);
		c.add("Level Completed", win);
		c.add("Instructions", instruct);
	}

	public static void main(String[] args){
		MyJava screen = new MyJava();
		screen.setSize(1012, 785);
		screen.setVisible(true);
		screen.setResizable(false);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			MusicPlayer music = new MusicPlayer();
			music.resetAudioStream();
		} catch (Exception ex) {
			System.out.println("Error with playing sound. (check for correct path)");
			ex.printStackTrace();

		}
	}
}// end class