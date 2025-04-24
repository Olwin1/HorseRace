package Utils;

/**
 * A generic class to hold a pair of objects of any type.
 *
 * @param <T1> the type of the first object
 * @param <T2> the type of the second object
 */
public class Pair<T1, T2> {
    private final T1 first;
    private final T2 second;

    /**
     * Constructs a Pair with the desired values.
     *
     * @param first  the first element
     * @param second the second element
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return first element of pair
     */
    public T1 getFirst() {
        return first;
    }

    /**
     * @return second element of pair
     */
    public T2 getSecond() {
        return second;
    }
}
