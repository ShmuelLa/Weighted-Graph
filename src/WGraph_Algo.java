import java.util.*;
import java.util.function.DoubleBinaryOperator;

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
        if (this._g == null || src == dest || this._g.getNode(src) == null || this._g.getNode(dest) == null) return -1;
        PriorityQueue<node_info> pq = new PriorityQueue<>();
        double result;
        node_info cur = this._g.getNode(src);
        pq.add(cur);
        cur.setTag(0);
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (cur.getInfo() != "y") {
                cur.setInfo("y");
                if (cur.getKey() == dest) break;
                for (node_info n : this._g.getV(cur.getKey())) {
                    if (n.getTag() == -1) {
                        n.setTag(Double.MAX_VALUE);
                    }
                    double tmp_tag = cur.getTag()+this._g.getEdge(cur.getKey(),n.getKey());
                    if (tmp_tag < n.getTag()) {
                        n.setTag(tmp_tag);
                        pq.add(n);
                    }
                }
            }
        }
        cur = this._g.getNode(dest);
        result = cur.getTag();
        if (cur.getInfo() != "y") return -1;
        this.reset();
        return result;
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
        if (this._g == null || src == dest || this._g.getNode(src) == null || this._g.getNode(dest) == null) return null;
        PriorityQueue<node_info> pq = new PriorityQueue<>();
        HashMap<node_info,node_info> parent = new HashMap<>();
        List<node_info> result = new ArrayList<>();
        node_info cur = this._g.getNode(src);
        pq.add(cur);
        cur.setTag(0);
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (cur.getInfo() != "y") {
                cur.setInfo("y");
                if (cur.getKey() == dest) break;
                for (node_info n : this._g.getV(cur.getKey())) {
                    if (n.getTag() == -1) {
                        n.setTag(Double.MAX_VALUE);
                    }
                    double tmp_tag = cur.getTag()+this._g.getEdge(cur.getKey(),n.getKey());
                    if (tmp_tag < n.getTag()) {
                        n.setTag(tmp_tag);
                        parent.put(n,cur);
                        pq.add(n);
                    }
                }
            }
        }
        cur = this._g.getNode(dest);
        if (cur.getInfo() != "y") return null;
        result.add(0,cur);
        while (cur.getKey() != src) {
            result.add(0,parent.get(cur));
            cur = parent.get(cur);
        }
        this.reset();
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

    public void reset() {
        for (node_info n : this._g.getV()) {
            if (n.getTag() != 0 || n.getInfo() != null) {
                n.setTag(-1);
                n.setInfo(null);
            }
        }
    }
}
