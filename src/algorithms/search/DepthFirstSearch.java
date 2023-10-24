package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    Stack<AState> stack;
    HashSet<String> visited;

    /**
     * Constructor of Depth First Search
     */
    public DepthFirstSearch() {
        // The iterative implementation from:  https://www.techiedelight.com/depth-first-search/
        super();
        this.name = "DepthFirstSearch";
        this.stack = new Stack<>();
        this.visited = new HashSet<>();
    }

    /**
     * solve a problem according to the searching algorithm (Depth First Search).
     * @param domain
     * @return Solution
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null) {return null;}
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();

        this.stack.push(start);
        boolean isSolved = false;

        // loop till stack is empty
        while (this.stack.size() > 0 && !isSolved) {
            AState currState = this.stack.pop();
            // if the vertex is already discovered yet, ignore it
            if (!visited.contains(currState.toString())) {
                this.numberOfNodes++;
                visited.add(currState.toString());
                if (goal.equals(currState)) {
                    goal.setCameFrom(currState.getCameFrom());
                    isSolved = true;
                }else {
                    ArrayList<AState> neighbors = domain.getAllSuccessors(currState);
                    for (int i = neighbors.size() - 1; i >= 0; i--) {
                        if (!visited.contains(neighbors.get(i).toString())) {
                            if (!this.stack.contains(neighbors.get(i))) {
                                neighbors.get(i).setCameFrom(currState);
                                this.stack.push(neighbors.get(i));
                            }
                        }
                    }
                }
            }
        }
        return new Solution(start, goal);
    }
}