package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> queue;
    private final HashSet<String> visited;

    /**
     * Constructor of Breadth First Search
     */
    public BreadthFirstSearch() {
        super();
        this.name = "BreadthFirstSearch";
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
    }

    /**
     * solve a problem according to the searching algorithm (Breadth First Search).
     * @param domain
     * @return Solution
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null) {return null;}
        //A Queue to manage the nodes that have yet to be visited
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        //Adding the node to start from
        this.queue.add(start);
        boolean isSolved = false;
        //While there are nodes left to visit...
        while (queue.size() > 0 && !isSolved) {
            AState node = queue.remove();
            if (!visited.contains(node.toString())) {
                this.numberOfNodes++;
                visited.add(node.toString());
                if (goal.equals(node)) {
                    goal.setCameFrom(node.getCameFrom());
                    isSolved = true;
                } else {
                    ArrayList<AState> neighbors = domain.getAllSuccessors(node);
                    for (AState neighbor : neighbors) {
                        updateCost(node, neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return new Solution(start, goal);
    }

    /**
     *  Update the cost, from this current node + 1
     *  need to evaluate how much node (edge) we visited from the current node.
     * @param father
     * @param child
     */
    public void updateCost(AState father, AState child){
        child.setCameFrom(father);
        child.setCost(father.getCost() + 1);
    }
}
