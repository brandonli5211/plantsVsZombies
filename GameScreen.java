import javax.swing.*;
import java.util.Objects;


public class GameScreen extends JFrame {

    enum PlantType {
        None,
        Sunflower,
        Peashooter,
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
        getLayeredPane().add(gp, 0);

        PlantCard peashooterCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/peashooterCard.png"))).getImage());
        peashooterCard.setLocation(100, 8);
        getLayeredPane().add(peashooterCard, 0);

        PlantCard sunflowerCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/sunflowerCard.png"))).getImage());
        sunflowerCard.setLocation(170, 8);
        getLayeredPane().add(sunflowerCard, 0);



        getLayeredPane().add(sun, 0);
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
