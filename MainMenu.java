import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener{

		  private JButton b1, b2, b3;
	      private ImageIcon bgImg = new ImageIcon("src/images/titleScreen.png");

		  
		  //private static ImageIcon buttonPic = new ImageIcon("src/play.png");
		
		  public MainMenu() {
			
		    b1 = new JButton("Play");
		    b1.addActionListener(this);
		    
		    b2 = new JButton("How to play?");
		    b2.addActionListener(this);
		    
		    b3 = new JButton("Quit");
		    b3.addActionListener(this);
		    
		    JPanel pSouth= new JPanel();
			setLayout(new BorderLayout());
			add(pSouth, BorderLayout.CENTER);
			pSouth.setLayout(new BoxLayout(pSouth, BoxLayout.Y_AXIS));
			pSouth.setOpaque(false);
		    
		    pSouth.add(b1);
		    pSouth.add(b2);
		    pSouth.add(b3);
		    
		  }
		
		  public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(bgImg.getImage(), 0, 0, getWidth(), getHeight(), null);
		  }
		
		  public void actionPerformed(ActionEvent e) {
			  if(e.getSource()==b1){
			      	MyJava.cardsL.next(MyJava.c);
			  }
			  else if (e.getSource()==b2) {
				    this.setBackground(Color.gray);
					JOptionPane.showMessageDialog(null, "Instructions: ", "Instructions",JOptionPane.INFORMATION_MESSAGE ); 
			  }
			  else if (e.getSource()==b3){
				  JOptionPane.showMessageDialog(null, "Are you sure you want to exit?", "Exit message",JOptionPane.WARNING_MESSAGE ); 
				  System.exit(0);
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

