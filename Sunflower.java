import javax.swing.*;
import java.awt.event.ActionEvent;

public class Sunflower extends Plant {

    private Timer sunProduceTimer;

    public Sunflower(GamePanel parent, int x, int y) {
        super(parent, x, y);
        sunProduceTimer = new Timer(10000, (ActionEvent e) -> {
            Sun sun = new Sun(getGp(), 60 + x * 100, 110 + y * 120, 130 + y * 120);
            getGp().getActiveSuns().add(sun);
            getGp().add(sun, 1);
        });
        sunProduceTimer.start();
    }

}
