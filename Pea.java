/*
 * Authors: Zayaan and Brandon
 * Date: Jan 17 2023
 * Program Name: Pea Class
 * Program Description: Creating Projectile Pea
 */

import java.awt.*;

public class Pea {

	// pea varibales
    private int posX;
    protected GamePanel gp;
    private int myLane;
    private int damage = 70;
    private String peaType = "peashooter";

    // creating a single pea
    public Pea(GamePanel parent, int lane, int startX) {
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void move() {
        Rectangle peaRect = new Rectangle(posX, 130 + myLane * 120, 28, 28);
        
        // going through every zombie in pea's lane
        for (int i = 0; i < gp.getActiveZombies().get(myLane).size(); i++) {
            Zombie z = gp.getActiveZombies().get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.getXCord(), 109 + myLane * 120, 400, 120);
            
            // checking if pea hit zombie
            if (peaRect.intersects(zRect)) {
                z.setHealth(z.getHealth() - damage);
                boolean exit = false;
                gp.getActivePeas().get(myLane).remove(this);
                
                //checking if zombie died
                if (z.getHealth() < 0) {
                	gp.zombieDied();

                    gp.getActiveZombies().get(myLane).remove(i);
                    exit = true;
                    gp.getActiveZombies().get(myLane).remove(this);
                }
                
                // stop loop after pea hits zombie
                if (exit) {
                	break;
                }
            }
        }
        
        // moving pea and delete when it is off screen
        posX += 15;
        if (posX > 1000) {
        	gp.getActivePeas().get(myLane).remove(this);
        }
    }

    // getters and setters
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
    public void setDamage(int dmg) {
    	damage = dmg;
    }
    public void setPeaType(String name) {
    	peaType = name;
    }
	public String getPeaType() {
		return peaType;
	}
}
