
/**
 *Beaver class. All methods are in super-classes above.
 */
public class Beaver extends Consumer {

    public Beaver(Grid grid, int x, int y) {
        super.grid = grid;
        super.xCoordinate = x;
        super.yCoordinate = y;
        super.stomachCapacity = 5;
        super.storedLimit = 50;
        super.name = "Beaver";
        super.grid.registerItem(xCoordinate, yCoordinate, this);
    }

}
