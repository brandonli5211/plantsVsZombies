/*
 *Name: Zayaan Bhanwadia
 *Date: Jan 17 2023
 *Program name: Title Screen of PVZ
 *Description: Simple game titlescreen with a "click to continue"
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TitleScreen extends JPanel implements MouseListener {
	  // initalizing components and images 
      private ImageIcon bgImg = new ImageIcon("images/titleScreen.png");
      private JLabel text = new JLabel("Click Anywhere to Continue");
	  	
      // constructing titlescreen
	  public TitleScreen() {	    
	    addMouseListener(this);
	    setBackground(Color.BLACK);
	    
		JPanel pSouth= new JPanel();
		setLayout(new BorderLayout());
		add(pSouth, BorderLayout.SOUTH);
		pSouth.setOpaque(false);
		text.setFont(new Font("Serif", Font.PLAIN, 36));
	    pSouth.add(text);
	    
	  }
	
	  // painting onto panel
	  public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawImage(bgImg.getImage(), 0, 0, getWidth(), getHeight(), null);
	  }
	  
	  // going to main menu when user clicks
	  public void mouseClicked(MouseEvent e) {
	      	MyJava.cardsL.next(MyJava.c);
	  }
	
	  public void mousePressed( MouseEvent e ){   }
	  public void mouseReleased( MouseEvent e ){   }
	  public void mouseEntered( MouseEvent e ) {   }
	  public void mouseExited( MouseEvent e )  {   }

}