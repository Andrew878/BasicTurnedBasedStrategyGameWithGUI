
/**
 *Radish farmer class. All methods are in super-classes above.
 */
public class RadishFarmer extends Farmer {

    public RadishFarmer(Grid grid, int x, int y) {
        super.grid = grid;
        super.xCoordinate = x;
        super.yCoordinate = y;
        super.productionAmount = 10;
        super.productionRate = 3;
        super.productionToNutritionFactor = 1;
        super.verticalBoundaryNeeded = 1;
        super.horizontalBoundaryNeeded = 1;
        super.ableToFarm = true;
        super.name = "Radish";

        // upon initialisation, record item to grid array
        super.grid.registerItem(xCoordinate, yCoordinate, this);

        // set as false so we later test if farm is viable
        super.farmTested = false;
    }

}
