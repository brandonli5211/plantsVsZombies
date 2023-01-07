import java.awt.*;

public class Pea {

    private int posX;
    protected GamePanel gp;
    private int myLane;

    public Pea(GamePanel parent, int lane, int startX) {
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void move() {
        Rectangle peaRect = new Rectangle(posX, 130 + myLane * 120, 28, 28);
        for (int i = 0; i < gp.getActiveZombies().get(myLane).size(); i++) {
            Zombie z = gp.getActiveZombies().get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.getXCord(), 109 + myLane * 120, 400, 120);
            if (peaRect.intersects(zRect)) {
                z.setHealth(z.getHealth() - 50);
                boolean exit = false;
                gp.getPeaLanes().get(myLane).remove(this);
                if (z.getHealth() < 0) {
                    System.out.println("ZOMBIE DIED");

                    gp.getActiveZombies().get(myLane).remove(i);
                    exit = true;
                    gp.getActiveZombies().get(myLane).remove(this);
                }
                if (exit) break;
            }
        }
        posX += 15;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
}
