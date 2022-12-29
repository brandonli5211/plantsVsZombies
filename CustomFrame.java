/*
 *Name: Zayaan Bhanwadia
 *Date: 
 *Program name: 
 *Description:
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TitleScreen extends JPanel implements ActionListener {
	  private JButton b1, b2, b3;
      private ImageIcon bgImg = new ImageIcon("src/images/titleScreen.png");
	  
	  //private static ImageIcon buttonPic = new ImageIcon("src/play.png");
	
	  public TitleScreen() {	    
	    
	    b1 = new JButton("Play");
	    b1.setBorderPainted(true);
	    b1.setFocusPainted(false);	    	    
	    b1.addActionListener(this);
	    
	    setBackground(Color.BLACK);
	    
		JPanel pSouth= new JPanel();
		setLayout(new BorderLayout());
		add(pSouth, BorderLayout.SOUTH);
		pSouth.setLayout(new FlowLayout());
		pSouth.setOpaque(false);
	    
	    pSouth.add(b1);
	    
	  }
	
	  public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawImage(bgImg.getImage(), 0, 0, getWidth(), getHeight(), null);
	  }
	
	  public void actionPerformed(ActionEvent e) {
		  if(e.getSource()==b1){
		      	MyJava.cardsL.next(MyJava.c);
		  }
	  }
	  
	 /*
	 public void mouseClicked(MouseEvent me){
		 int mouseX = me.getX();
		 int mouseY = me.getY();
		  
		 int x = mouseX;
		 int y = mouseY;
		 repaint();		  
	  }

	public void mousePressed(MouseEvent e) {
	}
	
	public void mouseReleased(MouseEvent e) {		
	}
	
	public void mouseEntered(MouseEvent e) {		
	}
	
	public void mouseExited(MouseEvent e) {		
	}
	
	public void keyPressed(KeyEvent ke) {
		ke.getKeyCode();
		ke.getKeyChar();
	}
	public void keyReleased(KeyEvent ke) {}
	public void keyTyped(KeyEvent ke) {}
	
	
	myTimer = new Timer(120, this);
	myTimer.start();
	myTimer.reset();
	myTimer.stop();
	*/
	

}