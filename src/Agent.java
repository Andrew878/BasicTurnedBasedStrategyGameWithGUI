
/**
 *An abstract class that provides more inbuilt methods and variables that are
 * applicable to all of farmers/consumers/transporters that handles stock-related,
 * and print methods. I would normally not have this class and include these methods
 * in AbstractItem, but given we were told not to alter the provided code, I
 * created this extra layer.
 *
 */
abstract class Agent extends AbstractItem {

    // descriptrion of agent
    protected String name;

    /**
     * Process an item. For a farmer this means (potentially, depending on the
     * time-step) producing products, for a transporter this means carrying
     * products between a farmer and a consumer, for a consumer this means
     * consuming.
     *
     * @param timeStep The current time-step
     */
    @Override
    public abstract void process(TimeStep timeStep);

    /**
     * Retrieve the stock that is stored at this item's location in nutrition
     * units.
     *
     * @return The stock
     */
    @Override
    protected int getStock() {
        return super.grid.getStockAt(super.xCoordinate, super.yCoordinate);
    }

    /**
     * Adds to the stock that is stored at this item's location in nutrition
     * units.
     */
    @Override
    protected void addToStock(int nutrition) {
        super.grid.addToStockAt(super.xCoordinate, super.yCoordinate, nutrition);
    }

    /**
     * Reduces the stock that is stored at this item's location in nutrition
     * units.
     */
    @Override
    protected void reduceStock(int nutrition) {
        super.grid.reduceStockAt(super.xCoordinate, super.yCoordinate, nutrition);
    }

    /**
     * Provides a string of name and nutrition stored at this item's location.
     *
     * @return String used to display grid
     */
    @Override
    public String toString() {
        return this.name + "(" + this.getStock() + ")";
    }

    /**
     * Helper method that gives an items position in a string.
     *
     * @return String of location
     */
    public String printLoc() {
        return this.toString() + " location is (x= " + super.xCoordinate + ", y=" + super.yCoordinate + ")";
    }

}
