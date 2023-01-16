/*
 * Authors: Zayaan Brandon
 * Date: Jan 17 2023
 * Program Name: Repeater Plant Class
 * Program Description: Create a repeater and control shooting
 */

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Repeater extends Plant {

	// peashooter variables
    public Timer shootTimer;
    public Timer secondPea;
    private boolean isShooting;

    // creating peashooters
    public Repeater(GamePanel parent, int x, int y) {
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
                        getGp().getActivePeas().get(getY()).add(new Pea(getGp(), getY(), 110 + this.getX() * 100));
                        
                        // shooting second pea slightly behind first
                        getGp().getActivePeas().get(getY()).add(new Pea(getGp(), getY(), 90 + this.getX() * 100));

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
