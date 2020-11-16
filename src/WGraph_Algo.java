import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {
    weighted_graph _g = new WGraph_DS();

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        this._g = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return this._g;
    }

    /**
     * Compute a deep copy of this weighted graph.
     *
     * @return
     */
    @Override
    public weighted_graph copy() {
        return null;
    }

    /**
     * Returns true if graph is connected which means there is a path between each and every two nodes on the graph.
     * We use a basic BFS algorithm to mark each and every connected node on the graph.
     * This method uses a LinkedList as a queue for the next node (add() and poll() both O(1)).
     * A HashSet for the visited nodes (efficient contains() method O(1)).
     * After scanning we true if the amount of the visited nodes == the number of the nodes in the graph
     *
     * @return True if the graph is connected false otherwise
     */
    @Override
    public boolean isConnected() {
        if (this._g.nodeSize() <= 1 || this._g == null) return true;
        Queue<Integer> queue = new LinkedList<>();
        int tmp_key=this._g.getV().iterator().next().getKey();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(tmp_key);
        visited.add(tmp_key);
        while (!queue.isEmpty()) {
            tmp_key=queue.poll();
            for (node_info node : this._g.getV(tmp_key)) {
                if (!visited.contains(node.getKey())) {
                    visited.add(node.getKey());
                    queue.add(node.getKey());
                }
            }
        }
        return visited.size() == this._g.nodeSize();
    }

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        PriorityQueue<node_info> pq = new PriorityQueue<node_info>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer,Integer> parent = new HashMap<>();
        List<node_info> result = new ArrayList<>();
        int cur = src;
        this._g.getNode(src).setTag(0);
        visited.add(src);
        pq.add(this._g.getNode(src));
        while (!pq.isEmpty() && cur!=dest) {
            cur = pq.poll().getKey();
            for (node_info n : this._g.getV(cur)) {
                if (!visited.contains(cur)) {
                    n.setTag(n.getTag()+this._g.getEdge(cur,n.getKey()));
                    visited.add(n.getKey());
                    parent.put(n.getKey(),cur);
                    pq.add(n);
                    if (n.getKey() == dest) break;
                }
            }
        }
        if (!visited.contains(dest)) return null;
        while (cur != src) {
            pq.addAll(this._g.getV(cur));
            result.add(0,pq.poll());
            pq.clear();
            cur = parent.get(result.get(0).getKey());
        }
        result.add(this._g.getNode(src));
        return result;
    }

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        return false;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        return false;
    }
}
