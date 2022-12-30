import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JLayeredPane implements MouseMotionListener {

    private Image bgImage;
    private ArrayList<ArrayList<Pea>> peaLanes;

    private Collider[] colliders;

    private Timer redrawTimer;

    private Image peashooterImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/peashooterIdle/peashooterIdle.gif"))).getImage();
    private Image peaImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/pea.png"))).getImage();

    private int mouseX, mouseY;

    public GamePanel(JLabel sunScoreboard) {
        redrawTimer = new Timer(25, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

        peaLanes = new ArrayList<>();
        peaLanes.add(new ArrayList<>()); //lane 1
        peaLanes.add(new ArrayList<>()); //lane 2
        peaLanes.add(new ArrayList<>()); //lane 3
        peaLanes.add(new ArrayList<>()); //lane 4
        peaLanes.add(new ArrayList<>()); //lane 5

        setSize(1000, 752);
        setLayout(null);
        addMouseMotionListener(this);

        colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider a = new Collider();
            a.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            a.setAction(new PlantActionListener((i % 9), (i / 9)));
            colliders[i] = a;
            add(a, new Integer(0));
        }

        bgImage = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();
    }


    private class PlantActionListener implements ActionListener {

        int x, y;

        public PlantActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            colliders[x + y * 9].setPlant(new Peashooter(GamePanel.this, x, y));
        }
    }

    private void advance() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < peaLanes.get(i).size(); j++) {
                Pea p = peaLanes.get(i).get(j);
                p.advance();
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);

        //Draw Plants
        for (int i = 0; i < 45; i++) {
            Collider c = colliders[i];
            if (c.assignedPlant != null) {
                Plant p = c.assignedPlant;
                if (p instanceof Peashooter) {
                    g.drawImage(peashooterImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
            }
        }

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < peaLanes.get(i).size(); j++) {
                Pea pea = peaLanes.get(i).get(j);
                g.drawImage(peaImage, pea.getPosX(), 130 + (i * 120), null);
            }

        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public ArrayList<ArrayList<Pea>> getPeaLanes() {
        return peaLanes;
    }

}
