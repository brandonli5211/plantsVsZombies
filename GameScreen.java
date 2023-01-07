import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


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


        getLayeredPane().add(sun, 0);
        setResizable(false);
        setVisible(true);
    }

    static GameScreen gw;

    public static void main(String[] args){

        try {
            MusicPlayer music = new MusicPlayer();
            music.resetAudioStream();
        } catch (Exception ex) {
            System.out.println("Error with playing sound. (check for correct path)");
            ex.printStackTrace();

        }

        gw = new GameScreen();
    }

}
