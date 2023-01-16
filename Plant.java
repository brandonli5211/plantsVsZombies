/*
 * Authors: Zayaan and Brandon
 * Date: Jan 17 2023
 * Program Name: Blueprint for Plant Object
 * Program Description: Constructor and getters/setters for plants
 */

public abstract class Plant {

	// plant variables
    private int health;
    private int x;
    private int y;
    private GamePanel gp;

    // plant constructor
    public Plant(GamePanel parent, int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
        gp = parent;
    }

    // setters and getters
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public GamePanel getGp() {
        return gp;
    }
    public void setGp(GamePanel gp) {
        this.gp = gp;
    }     
    public int getXCord() {
    	return 60 + (x % 9) * 100;
    }
    
    // lane getters and setter
    public void setX(int x) {
        this.x = x;
    }    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) { 
        this.y = y;
    }
}
