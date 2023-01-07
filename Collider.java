import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Collider extends JPanel implements MouseListener {

    private ActionListener listener;

    public Collider() {
        setOpaque(false);
        addMouseListener(this);
        setSize(100, 120);
    }

    public Collider(int h, int w) {
        setOpaque(false);
        setSize(h, w);
    }

    public Plant assignedPlant;

    public void setPlant(Plant p) {
        assignedPlant = p;
    }
    public Plant getPlant() {
        return this.assignedPlant;
    }

    public void removePlant() {
        assignedPlant.stop();
        assignedPlant = null;
    }

    public boolean isInsideCollider(int jx) {
        return (jx > getLocation().x) && (jx < getLocation().x + 75);
    }

    public void setAction(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (listener != null) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
