
/**
 *
 * @author al278
 */
public class NearestTransporter extends Transporter {

    public NearestTransporter(Grid grid, int x, int y, int carryLimit) {
        super.grid = grid;
        super.xCoordinate = x;
        super.yCoordinate = y;
        super.carryLimit = carryLimit;
        super.isWorking = false;
        super.isInitialised = false;
        super.name = "NT";
        super.grid.registerItem(xCoordinate, yCoordinate, this);

    }

    /**
     * Checks existence of and records a route between farmer and a consumer.
     * Checks in both directions of the transporter. Because we check from #
     * 'in-to-out' we only ever pick the closest pair.
     *
     */
    @Override
    public void findCarryRoute() {

        // start by assuming there is no link between transport and consumer
        Boolean foundFarmer = false;
        Boolean foundConsumer = false;

        // an upper bound for distance. See java doc for this method for the
        // underlying reasons it was chosen
        double upperBound = this.straightLineDistanceSquared(0, 0, super.grid.getWidth(), super.grid.getHeight());

        double closestConsumerDist = upperBound;
        double closestFarmerDist = upperBound;
        double secondClosestConsumerDist = upperBound;
        double secondClosestFarmerDist = upperBound;

        // we are going to check every cell in our the grid. If there are any 
        // farmers and consumers, then we are going to record a distance measure
        // and make a note of the smallest.
        for (int x = 0; x < super.grid.getHeight(); x++) {

            // we only care about non-null/non-empty values
            for (int y = 0; y < super.grid.getWidth(); y++) {

                // renaming so easier to read
                Agent item = (Agent) super.grid.getItem(x, y);

                // we only care about non-null/non-empty values
                if (item != null) {

                    // Check if item is a consumer 
                    if (super.foundConsumer(item, x, y, false)) {

                        // if it's the first time a consumer is found, update 
                        // the details in this loop. Next time, it will be ignored
                        if (!foundConsumer) {

                            super.toX = x;
                            super.toY = y;
                            closestConsumerDist = this.straightLineDistanceSquared(x, y, super.xCoordinate, super.yCoordinate);
                        }

                        // If it the first time a consumer is found, ignore this statement.
                        // Otherwise, compare the two distances, and update if required
                        if (foundConsumer && (closestConsumerDist >= this.straightLineDistanceSquared(x, y, super.xCoordinate, super.yCoordinate))) {
                            super.toX = x;
                            super.toY = y;
                            secondClosestConsumerDist = closestConsumerDist;
                            closestConsumerDist = this.straightLineDistanceSquared(x, y, super.xCoordinate, super.yCoordinate);
                        }

                        // changed this flag, to seperate between the first and 
                        // subsequent flows
                        foundConsumer = true;

                    } // exactly same logic as above, but we are looking for farmers 
                    // instead of consumers
                    else if (super.foundFarmer(item, x, y, false)) {

                        if (!foundFarmer) {
                            super.fromX = x;
                            super.fromY = y;
                            closestFarmerDist = this.straightLineDistanceSquared(x, y, super.xCoordinate, super.yCoordinate);
                        }

                        if (foundFarmer && (closestFarmerDist >= this.straightLineDistanceSquared(x, y, super.xCoordinate, super.yCoordinate))) {
                            super.fromX = x;
                            super.fromY = y;
                            secondClosestFarmerDist = closestFarmerDist;
                            closestFarmerDist = this.straightLineDistanceSquared(x, y, super.xCoordinate, super.yCoordinate);
                        }

                        foundFarmer = true;

                    }
                }
            }
        }

        // boolean check for equidistance
        boolean isAnEqualDist = (closestFarmerDist == secondClosestFarmerDist)
                || (closestConsumerDist == secondClosestConsumerDist);

        // check farming conditions - i.e. found a farm/consumer pair, and also 
        // no equidistance
        if (foundConsumer && foundFarmer && !(isAnEqualDist)) {
            super.isWorking = true;
        }

        // we don't want to repeated these nested for-loops, so we change this flag
        super.isInitialised = true;
    }

    /**
     * Takes two points and returns straight line distance squared. I chose to
     * not compute the square-root as it is not required to order according to
     * distance ordering and sqrts can be computationally expensive to
     * calculate.
     */
    public double straightLineDistanceSquared(int x1, int y1, int x2, int y2) {

        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

}
