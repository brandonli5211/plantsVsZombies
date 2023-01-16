/*
 * Authors: Zayaan and Brandon
 * Date: Jan 17 2023
 * Program Name: Plantcard class
 * Program Description: Creating plant card image on game 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlantCard extends JPanel implements MouseListener {

    private Image img;
    private ActionListener listener;

    public PlantCard(Image img) {
        this.img = img;
        setSize(img.getWidth(getFocusCycleRootAncestor()), img.getHeight(getFocusCycleRootAncestor()));
        addMouseListener(this);
    }

    public void setAction(ActionListener listener) {
        this.listener = listener;
    }
    public void setType(String name) {
    	
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    public void mouseReleased(MouseEvent e) {
        if (listener != null) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
        }
    }

    public void mouseEntered(MouseEvent e) {   }
    public void mouseClicked(MouseEvent e) {   }
    public void mousePressed(MouseEvent e) {    }   
    public void mouseExited(MouseEvent e) {   }
    
}
