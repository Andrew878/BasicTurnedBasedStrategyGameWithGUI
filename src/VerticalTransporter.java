
/**
 **Vertical transporter class.
 */
public class VerticalTransporter extends Transporter {

    public VerticalTransporter(Grid grid, int x, int y, int carryLimit) {
        super.grid = grid;
        super.xCoordinate = x;
        super.yCoordinate = y;
        super.carryLimit = carryLimit;
        super.isWorking = false;
        super.isInitialised = false;
        super.name = "VT";
        super.grid.registerItem(xCoordinate, yCoordinate, this);

    }

    /**
     * Checks existence of and records a Vertical route between farmer and a
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
        // First check looking above
        for (int x = super.xCoordinate; x < super.grid.getHeight(); x++) {

            // we only care about non-null/non-empty values
            if (super.grid.getItem(x, super.yCoordinate) != null) {

                // renaming so easier to read
                Agent item = (Agent) super.grid.getItem(x, super.yCoordinate);

                // break loops if either found 
                if (super.foundConsumer(item, x, super.yCoordinate, true)) {
                    foundConsumer = true;
                    break;
                } else if (super.foundFarmer(item, x, super.yCoordinate, true)) {
                    foundFarmer = true;
                    break;
                }
            }
        }

        //  Check looking below
        for (int x = super.xCoordinate; x >= 0; x--) {

            // we only care about non-null/non-empty values
            if (super.grid.getItem(x, super.yCoordinate) != null) {

                // renaming so easier to read
                Agent item = (Agent) super.grid.getItem(x, super.yCoordinate);

                // break loops if either found 
                if (super.foundConsumer(item, x, super.yCoordinate, true)) {
                    foundConsumer = true;
                    break;
                } else if (super.foundFarmer(item, x, super.yCoordinate, true)) {
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
