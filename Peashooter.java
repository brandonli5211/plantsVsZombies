import javax.swing.*;
import java.awt.event.ActionEvent;

public class Peashooter extends Plant {

    public Timer shootTimer;
    private boolean isShooting;


    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y, 200);
        shootTimer = new Timer(2250, (ActionEvent e) -> {
            if(getGp().getActiveZombies().get(y).size() > 0){
                this.isShooting = true;
                getGp().getPeaLanes().get(y).add(new Pea(getGp(), y, 103 + this.getX() * 100));
            }else{
                this.isShooting = false;
            }
        });
        shootTimer.start();
    }

    public boolean isShooting(){
        return this.isShooting;
    }
    @Override
    public void stop() {
        shootTimer.stop();
    }

}
