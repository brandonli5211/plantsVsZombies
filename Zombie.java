import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Zombie extends JPanel {
    private int xCord = 950;
    private int health = 1000;
    private final GamePanel gp;
    private int currLane;


    public Zombie(GamePanel parent, int lane) {
        this.gp = parent;

        setSize(70, 100);
        setOpaque(false);
        currLane = lane;
    }

    public int getX() {
        return xCord;
    }

    public void create() {
        Collider plantInMyLane = null;
        boolean isColliding = false;
        for (int i = currLane * 9; i < (currLane + 1) * 9; i++) {
            if (gp.getColliders()[i].assignedPlant != null && gp.getColliders()[i].isInsideCollider(xCord)) {
                isColliding = true;
                plantInMyLane = gp.getColliders()[i];
            }
        }
        if(!isColliding){
            if (xCord < 10) {
                JOptionPane.showMessageDialog(gp,"The zombies ate your brain!! ggs");
            } else {
                xCord--;
            }
        }else{
            plantInMyLane.assignedPlant.setHealth(plantInMyLane.assignedPlant.getHealth() - 5);
            if (plantInMyLane.assignedPlant.getHealth() < 0) {
                plantInMyLane.removePlant();
            }
        }


        if (health == 0) {
            gp.remove(this);
            gp.getActiveZombies().remove(this);
        }
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getXCord() {
        return xCord;
    }
}