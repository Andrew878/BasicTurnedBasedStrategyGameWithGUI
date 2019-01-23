
/**
 *Abstract class that contains all the methods for consumers.
 */
abstract public class Consumer extends Agent {

    protected int stomachCapacity;
    protected int storedLimit;

    /**
     * Process one step of a Consumer. Takes into the account the consumption
     * and storage capacity of each animal.
     *
     * @param timeStep
     */
    @Override
    public void process(TimeStep timeStep) {

        int nutritionAvailable = super.getStock();
        int nutritionEaten;

        // checking how much consumer actually eats
        if (nutritionAvailable >= this.stomachCapacity) {
            nutritionEaten = this.stomachCapacity;
        } else {
            nutritionEaten = nutritionAvailable;
        }

        // adjust our records
        super.reduceStock(nutritionAvailable);
        super.grid.recordConsumption(nutritionEaten);

        // stores no more than consumers limit
        super.addToStock(Math.min(nutritionAvailable - nutritionEaten, this.storedLimit));

    }

}
