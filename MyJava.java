import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyJava extends JFrame{
	static CardLayout cardsL;
	static Container c;

	TitleScreen  titleP;  // object of my customized class MyMenuPanel
	MainMenu menuP;   // object of my customized class MyGamePanel
	GameScreen gameP; //

	public MyJava(){    //constructor
		c=getContentPane();
		cardsL=new CardLayout();
		c.setLayout(cardsL);

		titleP = new TitleScreen();
		menuP = new MainMenu();
		gameP = new GameScreen();

		c.add("Title Screen", titleP);
		c.add("Main Menu", menuP);
		c.add("Plants VS Zombies", gameP);
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