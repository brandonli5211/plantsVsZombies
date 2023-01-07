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

    private final Image peashooterIdleAnim = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/animations/peashooterIdle.gif"))).getImage();
    private final Image peashooterShootingAnim = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/animations/peashooterShooting.gif"))).getImage();
    private final Image peaImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/pea.png"))).getImage();

    private final Image sunflowerIdleAnim = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/animations/sunflowerIdle.gif"))).getImage();

    private Image wallnutImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/animations/wallnutIdle.png"))).getImage();

    private JLabel sunScoreBoard;
    private ArrayList<Sun> activeSuns;
    private int sunScore;


    private ArrayList<ArrayList<Zombie>> activeZombies;
    private final Image zombieWalkingAnim = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/zombieWalking.gif"))).getImage();

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
        Timer sunProducer = new Timer(((int)(Math.random() * 3) + 1) * 4500, (ActionEvent e) -> {
            Random rand = new Random();
            Sun sun = new Sun(this, rand.nextInt(800) + 100, 0, rand.nextInt(300) + 200);
            activeSuns.add(sun);
            add(sun, 1);
        });
        sunProducer.start();

        Timer zombieSpawner = new Timer( (int)((Math.random() * 2) + 1) * 5000 , (ActionEvent e) -> {
            int lane = (int)(Math.random() * 5);
            Zombie zombie = new Zombie(this, lane);
            activeZombies.get(lane).add(zombie);
            add(zombie, 1);
        });
        zombieSpawner.start();



        peaLanes = new ArrayList<>();
        peaLanes.add(new ArrayList<>()); //lane 1
        peaLanes.add(new ArrayList<>()); //lane 2
        peaLanes.add(new ArrayList<>()); //lane 3
        peaLanes.add(new ArrayList<>()); //lane 4
        peaLanes.add(new ArrayList<>()); //lane 5


        activeZombies = new ArrayList<>();
        activeZombies.add(new ArrayList<>()); //lane 1
        activeZombies.add(new ArrayList<>()); //lane 2
        activeZombies.add(new ArrayList<>()); //lane 3
        activeZombies.add(new ArrayList<>()); //lane 4
        activeZombies.add(new ArrayList<>()); //lane 5


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
                if (getSunScore() >= 50  && colliders[x + y * 9].getPlant() == null) {
                    colliders[x + y * 9].setPlant(new Sunflower(GamePanel.this, x, y));
                    setSunScore(getSunScore() - 50);
                }
            }
            if (currentPlantingBrush == GameScreen.PlantType.Peashooter) {
                if (getSunScore() >= 100  && colliders[x + y * 9].getPlant() == null) {
                    colliders[x + y * 9].setPlant(new Peashooter(GamePanel.this, x, y));
                    setSunScore(getSunScore() - 100);
                }
            }

            if (currentPlantingBrush == GameScreen.PlantType.Wallnut) {
                if (getSunScore() >= 50) {
                    colliders[x + y * 9].setPlant(new Wallnut(GamePanel.this, x, y, 400));
                    setSunScore(getSunScore() - 50);
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

        //produce zombies
        for (int i = 0; i < activeZombies.size(); i++) {
            for (Zombie z: activeZombies.get(i)) {
                z.create();
            }
        }


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);

        PlantCard wallnutCard;
        if (this.getSunScore() < 50){
            wallnutCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/disabledWallnutCard.png"))).getImage());
        }else{
            wallnutCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/wallnutCard.png"))).getImage());
        }
        wallnutCard.setAction((ActionEvent e) -> this.setCurrentPlantingBrush(GameScreen.PlantType.Wallnut));
        wallnutCard.setLocation(255, 8);
        add(wallnutCard, 0);

        PlantCard sunflowerCard;
        if (this.getSunScore() < 50){
            sunflowerCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/disabledSunflowerCard.png"))).getImage());
        }else{
            sunflowerCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/sunflowerCard.png"))).getImage());
        }
        sunflowerCard.setAction((ActionEvent e) -> this.setCurrentPlantingBrush(GameScreen.PlantType.Sunflower));
        sunflowerCard.setLocation(115, 8);
        add(sunflowerCard, 0);

        PlantCard peashooterCard;
        if (this.getSunScore() < 100){
            peashooterCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/disabledPeashooterCard.png"))).getImage());
        }else{
            peashooterCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/peashooterCard.png"))).getImage());
        }
        peashooterCard.setAction((ActionEvent e) -> this.setCurrentPlantingBrush(GameScreen.PlantType.Peashooter));
        peashooterCard.setLocation(185, 8);
        add(peashooterCard, 0);

        //draw peas and zombies based on lane
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < peaLanes.get(i).size(); j++) {
                Pea p = peaLanes.get(i).get(j);
                p.move();
            }

            for(Zombie z : activeZombies.get(i)){
                g.drawImage(zombieWalkingAnim, z.getXCord(), 115 + (i * 120), null);
            }
        }

        //draw plants
        for (int i = 0; i < 45; i++) {
            Collider c = colliders[i];
            if (c.assignedPlant != null) {
                Plant p = c.assignedPlant;
                if (p instanceof Peashooter) {
                    if(((Peashooter) p).isShooting()){
                        g.drawImage(peashooterShootingAnim, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                    }else{
                        g.drawImage(peashooterIdleAnim, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                    }

                }
                if (p instanceof Sunflower) {
                    g.drawImage(sunflowerIdleAnim, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if(p instanceof Wallnut){
                    g.drawImage(wallnutImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
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
    public ArrayList<ArrayList<Zombie>> getActiveZombies() {
        return activeZombies;
    }

    public Collider[] getColliders() {
        return colliders;
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
