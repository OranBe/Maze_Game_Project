package algorithms.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    /**
     * Constructor of Best First Search
     */
    public BestFirstSearch() {
        super();
        this.name = "BestFirstSearch";
        this.queue = new PriorityQueue<>((o1, o2) -> {
            int firstCost = o1.getCost();
            int secondCost = o2.getCost();
            // Assume that we need to evaluate the smaller cost path
            return (firstCost - secondCost); // return the max
            //return (secondCost - firstCost); // return the min
        });
    }

    /**
     *  Update the cost, from this current node + child node
     *  need to evaluate how much node we visited from the current node.
     * @param father
     * @param child
     */
    @Override
    public void updateCost(AState father, AState child){
        child.setCameFrom(father);
        child.setCost(father.getCost() + child.getCost());
    }

    /**
     * solve a problem according to the searching algorithm (Best First Search).
     * @param domain
     * @return Solution
     */
    @Override
    public Solution solve(ISearchable domain) {
        return super.solve(domain);
    }
}