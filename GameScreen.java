import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


public class GameScreen extends JLayeredPane {

    enum PlantType {
        None,
        Sunflower,
        Peashooter,
        Wallnut,
    }

    public GameScreen() {
        setSize(1012, 785);

        JLabel sun = new JLabel();
        sun.setForeground(Color.white);
        sun.setFont(new Font("Futura", Font.BOLD, 13));
        sun.setLocation(50, 80);
        sun.setSize(60, 20);


        GamePanel gp = new GamePanel(sun);
        gp.setLocation(0, 0);
        this.add(gp, 0);


        add(sun, 0);
    }

}