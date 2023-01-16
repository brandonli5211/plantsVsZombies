/*
 * Authors: Zayaan and Brandon
 * Date: Jan 17 2023
 * Program Name: Zombie Class
 * Program Description: Blueprint for creating zombie object
 */

import javax.swing.*;

public class Zombie extends JPanel {
	// initalizing zombie variables
    private int xCord = 950;
    private int health = 1100;
    private final GamePanel gp;
    private int currLane;
    private String zombieType = "normal";

    // constructor
    public Zombie(GamePanel parent, int lane) {
        this.gp = parent;

        setSize(70, 100);
        setOpaque(false);
        currLane = lane;
    }

    // zombie movement and attack
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
             xCord -= 10;
        }
        else{
            plantInMyLane.assignedPlant.setHealth(plantInMyLane.assignedPlant.getHealth() - 3);
            if (plantInMyLane.assignedPlant.getHealth() < 0) {
                plantInMyLane.removePlant();
            }
        }
    }

    // getters and setters
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getXCord() {
        return xCord;
    }
	public String getZombieType() {
		return zombieType;
	}
	public void setZombieType(String z) {
		zombieType = z;
	}
}