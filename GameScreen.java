import javax.swing.*;


public class GameScreen extends JFrame {

    enum PlantType {
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter
    }

    //PlantType activePlantingBrush = PlantType.None;

    public GameScreen() {
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = new JLabel("SUN");
        sun.setLocation(37, 80);
        sun.setSize(60, 20);

        GamePanel gp = new GamePanel(sun);
        gp.setLocation(0, 0);
        getLayeredPane().add(gp, new Integer(0));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/peashooterCard.png")).getImage());
        peashooter.setLocation(100, 8);
        getLayeredPane().add(peashooter, new Integer(3));


        getLayeredPane().add(sun, new Integer(2));
        setResizable(false);
        setVisible(true);
    }

    static GameScreen gw;

    public static void begin() {
        gw.dispose();
        gw = new GameScreen();
    }

    public static void main(String[] args) {
        gw = new GameScreen();
    }

}
