package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    ArrayList<AState> path;

    /**
     * Constructor
     * @param start
     * @param goal
     */
    public Solution(AState start, AState goal) {
        ArrayList<AState> inversePath = new ArrayList<AState>();
        inversePath.add(goal);
        AState tmp = goal.getCameFrom();
        while(tmp != null){
            inversePath.add(tmp);
            tmp = tmp.getCameFrom();
        }
        path = new ArrayList<>();
        for (int i = inversePath.size() - 1; i >= 0 ; i--) {
            path.add(inversePath.get(i));
        }
    }

    /**
     * Switch the order to start position to goal position
     * @return ArrayList
     */
    public ArrayList<AState> getSolutionPath() {
        return this.path;
    }
}
