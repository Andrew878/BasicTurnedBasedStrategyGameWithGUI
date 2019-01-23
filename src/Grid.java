
/**
 * Holds the games information, including orientation on board.
 *
 * @author al278
 */
public class Grid extends AbstractGrid {

    private int height;
    private int width;
    private int totalProduction;
    private int totalConsumption;

    public Grid(int height, int width) {

        this.height = height;
        this.width = width;
        super.grid = new AbstractItem[this.height][this.width];
        super.stock = new int[this.height][this.width];

        this.totalProduction = 0;
        this.totalConsumption = 0;
    }

    /**
     * Returns the width of the grid.
     *
     * @return The width of the grid
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the grid.
     *
     * @return The height of the grid
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Registers an item on the grid at the specified location. Caution: this
     * will overwrite whatever was at this location before!
     *
     * @param xCoordinate
     * @param yCoordinate
     * @param item
     */
    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
        super.grid[xCoordinate][yCoordinate] = item;
    }

    /**
     * Return the item at row i and column j Returns null if there isn't an item
     * at the specified location Also returns null for out-of-bounds queries
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @return The item that is stored at the specified grid cell, null
     * otherwise
     */
    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {

        // return objects. If not object not present, will returns null.
        return super.grid[xCoordinate][yCoordinate];
    }

    /**
     * The stock (in terms of units of nutrition at the specified location).
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @return The stock at the specified location
     */
    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        return super.stock[xCoordinate][yCoordinate];
    }

    /**
     * Clear the stock at the specified location. i.e. set it to 0. This is
     * equivalent to setStockAt(x,y,0)
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     */
    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {
        super.stock[xCoordinate][yCoordinate] = 0;
    }

    /**
     * Add the specified amount to the stock value stored at the specified
     * location
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        super.stock[xCoordinate][yCoordinate] += nutrition;
    }

    /**
     * Reduce the stock at the specified location by the given amount
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        super.stock[xCoordinate][yCoordinate] -= nutrition;
    }

    /**
     * Update the stock value at the specified location. Methods like
     * emptyStockAt, addToStockAt and reduceStockAt may be easier to use for
     * specific use cases
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        super.stock[xCoordinate][yCoordinate] = nutrition;
    }

    /**
     * Process all items. First all farmers, then all transporters, then all
     * consumers.
     *
     * @param timeStep The time step we are at. This value may be used to
     * determine production frequency of farmers.
     */
    @Override
    public void processItems(TimeStep timeStep) {

        // process the Farmers, line by line
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {

                // if item correct class, then process item
                if (this.getItem(x, y) instanceof Farmer) {
                    this.getItem(x, y).process(timeStep);
                }
            }
        }

        // process the Transporters, line by line
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {

                // if item correct class, then process item
                if (this.getItem(x, y) instanceof Transporter) {
                    this.getItem(x, y).process(timeStep);
                }
            }
        }

        // process the Consumers, line by line
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {

                // if item correct class, then process item
                if (this.getItem(x, y) instanceof Consumer) {
                    this.getItem(x, y).process(timeStep);
                }
            }
        }

    }

    /**
     * After every production by every farmer we record the amount of nutrition
     * that is produced, so we can maintain statistics.
     *
     * @param nutrition the amount
     */
    @Override
    public void recordProduction(int nutrition) {
        this.totalProduction += nutrition;
    }

    /**
     * Retrieve the total production amount so far.
     *
     * @return amount of production
     */
    @Override
    public int getTotalProduction() {
        return this.totalProduction;
    }

    /**
     * After every consumption by every consumer we record the amoung of
     * nutrition that is consumed, so we can maintain statistics.
     *
     * @param nutrition the amount
     */
    @Override
    public void recordConsumption(int nutrition) {
        this.totalConsumption += nutrition;
    }

    /**
     * Retrieve the total consumption amount so far.
     *
     * @return amount of consumption
     */
    @Override
    public int getTotalConsumption() {
        return this.totalConsumption;
    }

    /**
     * Out of bounds boolean test for coordinate set.
     *
     * @param x coordinate
     * @param y coordinate
     * @return boolean variable, true/false
     */
    public Boolean isOutofBounds(int x, int y) {

        // if out of bounds for x, then its ok
        if ((x >= this.getHeight()) || (x < 0)) {
            return true;
        }

        // if out of bounds for y, then its ok
        if ((y >= this.getWidth()) || (y < 0)) {
            return true;
        }

        return false;
    }

    /**
     * Helper variable that scans coordinates, and returns exception if out of
     * bounds.
     *
     * @param boolean variable
     */
    private void checkerCoOrd(int x, int y) {
        if (this.isOutofBounds(x, y)) {
            throw new IllegalArgumentException("\n\nLocation is out of the Grid. Please re-location and start again\n");
        }
    }

}
