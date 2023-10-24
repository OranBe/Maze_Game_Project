package algorithms.search;

import java.io.Serializable;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm , Serializable {
    protected int numberOfNodes;
    protected String name;

    /**
     * Constructor
     */
    public ASearchingAlgorithm() {
        this.numberOfNodes = 0;
        this.name = null;
    }

    /**
     * solve a problem according to the searching algorithm.
     * @param domain
     * @return Solution
     */
    @Override
    public abstract Solution solve(ISearchable domain);

    /**
     * get number of nodes evaluated while solving the searching algorithm.
     * @return int
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodes;
    }

    /**
     * get the name of the searching algorithm
     * @return String
     */
    @Override
    public String getName() {
        return name;
    }
}
