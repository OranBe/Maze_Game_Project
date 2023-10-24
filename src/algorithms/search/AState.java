package algorithms.search;

import java.io.Serializable;

public abstract class AState implements Serializable {
    private AState cameFrom;
    private int cost;

    /**
     * Constructor
     * @param cameFrom
     * @param cost
     */
    public AState(AState cameFrom, int cost) {
        this.cameFrom = cameFrom;
        this.cost = cost;
    }

    /**
     * Which is my father??
     * @return father is you!
     */
    public AState getCameFrom() {
        return cameFrom;
    }

    /**
     * Found my father :)
     * @param cameFrom
     */
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * How much is cost?
     * @return cost price
     */
    public int getCost() { return cost; }

    /**
     *
     * @param cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * equals that Override from Object
     * @param other
     * @return boolean
     */
    @Override
    public abstract boolean equals(Object other);

    /**
     *  hashCode that Override from Object
     * @return int
     */
    @Override
    public abstract int hashCode();
}