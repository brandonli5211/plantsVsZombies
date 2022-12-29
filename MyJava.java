/*
 *Name: Zayaan Bhanwadia
 *Date: 
 *Program name: 
 *Description:
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyJava extends JFrame{
	static CardLayout cardsL;
	static Container c;
	
	TitleScreen  menuP;  // object of my customized class MyMenuPanel
	MainMenu gameP;   // object of my customized class MyGamePanel
	
	public MyJava(){    //constructor
	   c=getContentPane();
	   cardsL=new CardLayout();
	   c.setLayout(cardsL);
	   
	   menuP = new TitleScreen();
	   gameP = new MainMenu();
	  
  	   c.add("Title Screen", menuP);
  	   c.add("Main Menu", gameP);
  	  
	}

	public static void main(String[] args) {
		MyJava screen = new MyJava();
		screen.setSize(500, 500);
		screen.setVisible(true);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}// end class