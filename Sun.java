/*
 * Authors: Zayaan and Brandon
 * Date: Jan 17 2023
 * Program Name: Sun Class
 * Program Description: Create falling sun object and control movement and despawn
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Sun extends JPanel implements MouseListener {

	// sun variables
    private GamePanel gp;
    private Image sunImage;
    private int myX;
    private int myY;
    private int endY;
    private int stop = 200;
    private Timer destruct;

    // constructing sun
    public Sun(GamePanel parent, int startX, int startY, int endY) {
        this.gp = parent;
        this.endY = endY;
        setSize(80, 80);
        setOpaque(false);
        myX = startX;
        myY = startY;
        setLocation(myX, myY);
        sunImage = new ImageIcon("images/sun.png").getImage();
        addMouseListener(this);
        
        destruct = new Timer (8000, (ActionEvent e) -> {
            gp.getActiveSuns().remove(this);
            gp.remove(this);
        
        });
        destruct.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sunImage, 0, 0, null);
    }

    // sun falling
    public void create() {
        if (myY < endY + 20) {
            myY += 4;
        } 
        setLocation(myX + 20, myY);
    }

    public void mouseClicked(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {   }

    public void mouseReleased(MouseEvent e) {
        gp.setSunScore(gp.getSunScore() + 25);
        gp.getActiveSuns().remove(this);
        gp.remove(this);
    }
}
