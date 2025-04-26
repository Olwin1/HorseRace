/** A basic wrapper class for boolean values to maintain state across context gaps. 
 * 
 */
public class BooleanWrapper {
    private boolean flag;

    public BooleanWrapper(boolean flag) {
        this.flag = flag;
    }

    /// Accessor method returns the boolean flag
    public boolean getFlag() {
        return this.flag;
    }
    /// Mutator method - sets the flag
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
