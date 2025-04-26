package Part1;
/** A basic wrapper class for boolean values to maintain state across context gaps. 
 * 
 */
public class BooleanWrapper {
    private boolean flag;

    BooleanWrapper(boolean flag) {
        this.flag = flag;
    }

    /// Accessor method returns the boolean flag
    boolean getFlag() {
        return this.flag;
    }
    /// Mutator method - sets the flag
    void setFlag(boolean flag) {
        this.flag = flag;
    }
}
