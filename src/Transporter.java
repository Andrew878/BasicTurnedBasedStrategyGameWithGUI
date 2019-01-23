
/**
 *Abstract class that contains most of the methods/variables for Transporters.
 *
 */
abstract public class Transporter extends Agent {

    protected int carryLimit;
    protected int fromX;
    protected int fromY;
    protected int toX;
    protected int toY;

    // for our tests to see if a transporter is 'connected' 
    protected Boolean isWorking;
    protected Boolean isInitialised;

    /**
     * Process one step of Transporter. Has an initialisation step that is
     * checked once, to see the transporter is 'connected' (or has a 'carry
     * route'.)
     *
     * If it able to transport, it is moves the stock (taking in account its
     * carry limit) from one position to another.
     *
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {

        // this condition is met if the the transported has a valid connection or route
        if (this.isWorking) {

            // stock available to be transported
            int farmerStock = super.grid.getStockAt(this.fromX, this.fromY);

            // take the minimum of stock or limit
            int stockTaken = Math.min(farmerStock, this.carryLimit);

            // update records at origin
            super.grid.reduceStockAt(this.fromX, this.fromY, stockTaken);

            // update records at destination
            super.grid.addToStockAt(this.toX, this.toY, stockTaken);

            // if there is no connection, AND it is not initialised, this looks 
            // for the nearest route 
        } else if (!this.isInitialised) {
            this.findCarryRoute();
        }

    }

    /**
     * Find the carry route. Needs to be abstract, because each transporter has
     * different methods to connect
     */
    public abstract void findCarryRoute();

    /**
     * String for display graph.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Takes an item, its coordinates and returns a boolean value if its a
     * farmer.
     *
     * @param item the item on grid being checked
     * @param x x location
     * @param y y location
     * @param updateRecord a switch that will update the farmer/consumer
     * connection record if true
     * @return is a farmer?
     */
    protected boolean foundFarmer(Agent item, int x, int y, boolean updateRecord) {

        if (item instanceof Farmer) {

            if (updateRecord) {
                this.fromX = x;
                this.fromY = y;
            }
            return true;
        }
        return false;
    }

    /**
     * Takes an item, its coordinates and returns a boolean value if its a
     * consumer.
     *
     * @param item the item on grid being checked
     * @param x x location
     * @param y y location
     * @param updateRecord a switch that will update the farmer/consumer
     * connection record if true
     * @return is a consumer?
     */
    protected boolean foundConsumer(Agent item, int x, int y, boolean updateRecord) {

        if (item instanceof Consumer) {

            if (updateRecord) {
                this.toX = x;
                this.toY = y;
            }
            return true;
        }
        return false;
    }
}
