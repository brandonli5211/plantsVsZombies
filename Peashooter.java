import javax.swing.*;
import java.awt.event.ActionEvent;

public class Peashooter extends Plant {

    public Timer shootTimer;


    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            getGp().getPeaLanes().get(y).add(new Pea(getGp(), y, 103 + this.getX() * 100));
        });
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}
