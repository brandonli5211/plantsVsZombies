/*
 * Authors: Zayaan and Brandon
 * Date: Jan 17 2023
 * Program Name: Sunflower Plant Class
 * Program Description: Create sunflower and control production
 */

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Sunflower extends Plant {

    private Timer sunProduceTimer;

    // sunflower producer
    public Sunflower(GamePanel parent, int x, int y) {
        super(parent, x, y, 150);
        
        // produce sun and add to gamepanel every time timer ticks
	        sunProduceTimer = new Timer(10000, (ActionEvent e) -> {
	            if (getHealth() > 0 ) {

		            Sun sun = new Sun(getGp(), 60 + x * 100, 110 + y * 120, 130 + y * 120);
		            getGp().getActiveSuns().add(sun);
		            getGp().add(sun, 1);
	            }
	            else {
	            	sunProduceTimer.stop();
	            }
	        });
        sunProduceTimer.start();
    }

}
