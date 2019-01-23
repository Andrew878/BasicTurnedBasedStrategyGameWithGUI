
/**
 *Abstract class that contains all the methods for farmers.
 */
abstract class Farmer extends Agent {

    // related to levels of production
    protected int productionAmount;
    protected int productionRate;
    protected int productionToNutritionFactor;

    // related to checking farming viability
    protected int verticalBoundaryNeeded;
    protected int horizontalBoundaryNeeded;
    protected Boolean ableToFarm;
    protected Boolean farmTested;

    /**
     * Process one step of farmer. Has an initialisation step that is checked
     * once, to see if the farm can actually produce.
     *
     * If it able to produce, and it is the correct timestep - it is stored.
     *
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {

        // need to test if we can farm. will only be tested on first process
        if (!(this.farmTested)) {
            this.checkifCanFarm();
        }

        // if we can farm and we have just made production, then we can add to stocks
        if (this.ableToFarm && ((timeStep.getValue() % this.productionRate) == 0)) {
            int nutritionProduced = this.productionAmount * this.productionToNutritionFactor;
            super.addToStock(nutritionProduced);
            super.grid.recordProduction(nutritionProduced);
        }
    }

    /**
     * An initial process that checks if the farm satisfies all location
     * conditions and can actually farm.
     */
    protected void checkifCanFarm() {

        // all our farmer classes are assumed to be able to farm at start of test
        // we are looking for breach cases
        // first we check along Y
        for (int tempY = super.yCoordinate - this.horizontalBoundaryNeeded; tempY <= (super.yCoordinate + this.horizontalBoundaryNeeded); tempY++) {

            // this ensures we skip the location tests for the farm itself 
            if (tempY == super.yCoordinate) {
                continue;
            }

            // if a space not farmable, then flag and exit method to avoid further tests
            if (!(isSpaceFarmable(super.xCoordinate, tempY))) {
                this.ableToFarm = false;
                return;
            }
        }

        // then we check along X
        for (int tempX = super.xCoordinate - this.verticalBoundaryNeeded; tempX <= (super.xCoordinate + this.verticalBoundaryNeeded); tempX++) {

            // this ensures we skip the location tests for the farm itself 
            if (tempX == super.xCoordinate) {
                continue;
            }

            // if a space not farmable, then flag and exit method to avoid further 
            // this method in later 'process' calls
            if (!(isSpaceFarmable(tempX, super.yCoordinate))) {
                this.ableToFarm = false;
                return;
            }
        }

    }

    /**
     * Takes a coordinate and checks if there are no farmers on it.
     *
     * @return boolean answer to isSpaceFarmable
     */
    protected Boolean isSpaceFarmable(int x, int y) {

        // if out of bounds for x, then its ok
        if (x >= super.grid.getHeight() || x < 0) {
            return true;
        }

        // if out of bounds for y, then its ok
        if (y >= super.grid.getWidth() || y < 0) {
            return true;
        }

        // check if empty (or null) 
        if (super.grid.getItem(x, y) == null) {
            return true;
        }

        // because non-empty, check if farmer
        if (!(super.grid.getItem(x, y) instanceof Farmer)) {
            return true;
        }

        // if fail all these conditions, farmer must be in the cell
        return false;

    }
}
