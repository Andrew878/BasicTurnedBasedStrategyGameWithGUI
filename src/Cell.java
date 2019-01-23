
import javax.swing.JTextField;

/**
 * Records and stores the contents of a GUI JTextField cell.
 *
 * @author al278
 */
public class Cell {

    private JTextField field;
    private int x;
    private int y;
    private boolean isBlank;
    private AbstractItem item;

    public Cell(JTextField field, int x, int y) {
        this.field = field;
        this.x = x;
        this.y = y;
        this.isBlank = false;
    }

    public JTextField getField() {
        return this.field;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isBlank() {
        return this.isBlank;
    }

    public void setIsBlank(boolean is) {
        this.isBlank = is;
    }

    public void setItem(AbstractItem item) {
        this.item = item;
    }

    public AbstractItem getItem() {
        return this.item;
    }

    /**
     * Helper function for debugging program.
     */
    public void printCell() {
        System.out.println("field= " + field.getText());
        System.out.println("x= " + x);
        System.out.println("y= " + y);
        System.out.println("isBlank= " + isBlank);
        System.out.println("item= " + item.toString());
    }

}
