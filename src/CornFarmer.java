
/**
 *Corn farmer class. All methods are in super-classes above.
 */
public class CornFarmer extends Farmer {

    public CornFarmer(Grid grid, int x, int y) {
        super.grid = grid;
        super.xCoordinate = x;
        super.yCoordinate = y;
        super.productionAmount = 5;
        super.productionRate = 4;
        super.productionToNutritionFactor = 5;
        super.verticalBoundaryNeeded = 1;
        super.horizontalBoundaryNeeded = 2;
        super.ableToFarm = true;
        super.name = "Corn";

        // upon initialisation, record item to grid array
        super.grid.registerItem(xCoordinate, yCoordinate, this);

        // set as false so we later test if farm is viable
        super.farmTested = false;
    }

}
