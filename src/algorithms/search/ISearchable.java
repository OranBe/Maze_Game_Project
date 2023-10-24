package algorithms.search;
import java.util.ArrayList;

public interface ISearchable {
    /**
     * The start position
     * @return AState
     */
    AState getStartState();

    /**
     * The end position
     * @return AState
     */
    AState getGoalState();

    /**
     * Check all the eight neighbors and if the current node can walk to this position,
     * add this position to the ArrayList.
     * @param s - the current node in the algorithm
     * @return ArrayLis AState
     */
    ArrayList<AState> getAllSuccessors(AState s);
}