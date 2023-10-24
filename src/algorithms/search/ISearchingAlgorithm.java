package algorithms.search;

public interface ISearchingAlgorithm {
    /**
     * solve a problem according to the searching algorithm.
     * @param domain
     * @return Solution
     */
    Solution solve(ISearchable domain);
    /**
     * get the name of the searching algorithm
     * @return String
     */
    String getName();
    /**
     * get number of nodes evaluated while solving the searching algorithm.
     * @return int
     */
    int getNumberOfNodesEvaluated();
}
