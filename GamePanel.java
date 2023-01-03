import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GamePanel extends JLayeredPane implements MouseMotionListener {

    private final Image bgImage;
    private final ArrayList<ArrayList<Pea>> peaLanes;

    private final Collider[] colliders;

    private final Image peashooterImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/plantAnimations/peashooterIdle.gif"))).getImage();
    private final Image peaImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/pea.png"))).getImage();

    private final Image sunflowerImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/plantAnimations/sunflowerIdle.gif"))).getImage();

    private JLabel sunScoreBoard;
    private ArrayList<Sun> activeSuns;
    private int sunScore;

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreBoard.setText(String.valueOf(sunScore));
    }

    private GameScreen.PlantType currentPlantingBrush = GameScreen.PlantType.None;

    public GamePanel(JLabel sunScoreboard) {
        setSize(1000, 752);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunScoreBoard = sunScoreboard;
        setSunScore(150);



        Timer redrawTimer = new Timer(25, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

        Timer generateTimer = new Timer(60, (ActionEvent e) -> generate());
        generateTimer.start();

        activeSuns = new ArrayList<>();
        Timer sunProducer = new Timer((int)(Math.random() * 3) + 10000 , (ActionEvent e) -> {
            Random rand = new Random();
            Sun sun = new Sun(this, rand.nextInt(800) + 100, 0, rand.nextInt(300) + 200);
            activeSuns.add(sun);
            add(sun, 1);
        });
        sunProducer.start();

        peaLanes = new ArrayList<>();
        peaLanes.add(new ArrayList<>()); //lane 1
        peaLanes.add(new ArrayList<>()); //lane 2
        peaLanes.add(new ArrayList<>()); //lane 3
        peaLanes.add(new ArrayList<>()); //lane 4
        peaLanes.add(new ArrayList<>()); //lane 5



        colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider a = new Collider();
            a.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            a.setAction(new PlantActionListener((i % 9), (i / 9)));
            colliders[i] = a;
            add(a, 0);
        }

        bgImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/mainBG.png"))).getImage();
    }


    private class PlantActionListener implements ActionListener {

        int x, y;

        public PlantActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentPlantingBrush == GameScreen.PlantType.Sunflower) {
                if (getSunScore() >= 50) {
                    colliders[x + y * 9].setPlant(new Sunflower(GamePanel.this, x, y));
                    setSunScore(getSunScore() - 50);
                }
            }
            if (currentPlantingBrush == GameScreen.PlantType.Peashooter) {
                if (getSunScore() >= 100) {
                    colliders[x + y * 9].setPlant(new Peashooter(GamePanel.this, x, y));
                    setSunScore(getSunScore() - 100);
                }
            }

            currentPlantingBrush = GameScreen.PlantType.None;
        }
    }


    private void generate() {
        //produce sun
        for (int i = 0; i < activeSuns.size(); i++) {
            activeSuns.get(i).create();
        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);

        //draw peas
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < peaLanes.get(i).size(); j++) {
                Pea p = peaLanes.get(i).get(j);
                p.move();
            }
        }

        //draw plants
        for (int i = 0; i < 45; i++) {
            Collider c = colliders[i];
            if (c.assignedPlant != null) {
                Plant p = c.assignedPlant;
                if (p instanceof Peashooter) {
                    g.drawImage(peashooterImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof Sunflower) {
                    g.drawImage(sunflowerImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
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

    public void setCurrentPlantingBrush(GameScreen.PlantType currentPlantingBrush) {
        this.currentPlantingBrush = currentPlantingBrush;
    }

    public ArrayList<Sun> getActiveSuns() {
        return activeSuns;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public ArrayList<ArrayList<Pea>> getPeaLanes() {
        return peaLanes;
    }

}
