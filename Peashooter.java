/*
 * Authors: Zayaan and Brandon
 * Date: Jan 17 2023
 * Program Name: Peashooter Plant Class
 * Program Description: Create a peashooter and control shooting
 */

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Peashooter extends Plant {

	// peashooter variables
    public Timer shootTimer;
    private boolean isShooting;

    // creating peashooters
    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y, 200);
        shoot();
    }

    // shoot method
    public void shoot() {
        shootTimer = new Timer(3000, (ActionEvent e) -> {
        	
        	if (getHealth() <= 0 ) {
            	shootTimer.stop();
            }
        	
        	// checking if zombie is in lane
            if(getGp().getActiveZombies().get(getY()).size() > 0){
            	// checking if zombie is infront of peashooter
            	for (int i = 0; i < getGp().getActiveZombies().get(getY()).size(); i++) {

            		if(getGp().getActiveZombies().get(getY()).get(i).getXCord() >= getXCord() && getHealth() > 0) {
            			this.isShooting = true; 
                        getGp().getActivePeas().get(getY()).add(new Pea(getGp(), getY(), 103 + this.getX() * 100));
                        break;
            		}
            	}              
            }
            else{ 
                this.isShooting = false;
            }
            
        });
        shootTimer.start();
    }
    
    // getters and timer stop
    public boolean isShooting(){
        return this.isShooting;
    }

}
