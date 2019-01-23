
/**
 *Rabbit class. All methods are in super-classes above.
 */
public class Rabbit extends Consumer {

    public Rabbit(Grid grid, int x, int y) {
        super.grid = grid;
        super.xCoordinate = x;
        super.yCoordinate = y;
        super.stomachCapacity = 8;
        super.storedLimit = 0;
        super.name = "Rabbit";
        super.grid.registerItem(xCoordinate, yCoordinate, this);
    }

}
