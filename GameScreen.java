import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
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

        JLabel sun = new JLabel();
        sun.setForeground(Color.white);
        sun.setFont(new Font("Futura", Font.BOLD, 13));
        sun.setLocation(50, 80);
        sun.setSize(60, 20);


        GamePanel gp = new GamePanel(sun);
        gp.setLocation(0, 0);
        getLayeredPane().add(gp, 0);


        PlantCard sunflowerCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/sunflowerCard.png"))).getImage());
        sunflowerCard.setAction((ActionEvent e) -> gp.setCurrentPlantingBrush(PlantType.Sunflower));
        sunflowerCard.setLocation(110, 8);
        getLayeredPane().add(sunflowerCard, 0);

        PlantCard peashooterCard = new PlantCard(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/cards/peashooterCard.png"))).getImage());
        peashooterCard.setAction((ActionEvent e) -> gp.setCurrentPlantingBrush(PlantType.Peashooter));
        peashooterCard.setLocation(175, 8);
        getLayeredPane().add(peashooterCard, 0);


        getLayeredPane().add(sun, 0);
        setResizable(false);
        setVisible(true);
    }

    static GameScreen gw;

    public static void begin() {
        gw.dispose();
        gw = new GameScreen();
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        try
        {
            MusicPlayer music = new MusicPlayer("music/grassWalk.wav");
            music.resetAudioStream();
        }

        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }

        gw = new GameScreen();
    }

}
