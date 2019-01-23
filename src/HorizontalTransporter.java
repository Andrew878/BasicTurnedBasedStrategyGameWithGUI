
/**
 *Horizontal transporter class.
 */
public class HorizontalTransporter extends Transporter {

    public HorizontalTransporter(Grid grid, int x, int y, int carryLimit) {
        super.grid = grid;
        super.xCoordinate = x;
        super.yCoordinate = y;
        super.carryLimit = carryLimit;
        super.isWorking = false;
        super.isInitialised = false;
        super.name = "HT";
        super.grid.registerItem(xCoordinate, yCoordinate, this);

    }

    /**
     * Checks existence of and records a Horizontal route between farmer and a
     * consumer. Checks in both directions of the transporter. Because we check
     * from 'inside-to-out' from transporter, we only ever pick the closest
     * pair.
     *
     */
    @Override
    public void findCarryRoute() {

        // start by assuming there is no link between transport and consumer
        Boolean foundFarmer = false;
        Boolean foundConsumer = false;

        // We need to in both directions.
        // First check looking ahead
        for (int y = super.yCoordinate; y < super.grid.getWidth(); y++) {

            // we only care about non-null/non-empty values
            if (super.grid.getItem(super.xCoordinate, y) != null) {

                // renaming so easier to read
                Agent item = (Agent) super.grid.getItem(super.xCoordinate, y);

                // break loops if either a consumer or famer found 
                if (super.foundConsumer(item, super.xCoordinate, y, true)) {
                    foundConsumer = true;
                    break;
                } else if (super.foundFarmer(item, super.xCoordinate, y, true)) {
                    foundFarmer = true;
                    break;
                }
            }
        }

        // Then check looking behind
        for (int y = super.yCoordinate; y >= 0; y--) {

            // we only care about non-null/non-empty values
            if (super.grid.getItem(super.xCoordinate, y) != null) {

                // renaming so easier to read
                Agent item = (Agent) super.grid.getItem(super.xCoordinate, y);

                // break loops if either a consumer or famer found 
                if (super.foundConsumer(item, super.xCoordinate, y, true)) {
                    foundConsumer = true;
                    break;
                } else if (super.foundFarmer(item, super.xCoordinate, y, true)) {
                    foundFarmer = true;
                    break;
                }
            }
        }

        // if both are found, then where are able to move nutrition in the process
        // method
        if (foundConsumer && foundFarmer) {
            super.isWorking = true;
        }

        // we don't want to do this test every process step, so this lets us avoid it
        super.isInitialised = true;
    }

}
