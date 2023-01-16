
public class Shovel extends Plant{
    public Shovel(GamePanel parent, int x, int y) {
        super(parent, x, y, 0);
        getGp().getColliders()[x * y].removePlant();
    }
}
