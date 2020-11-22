package ex0;

import java.util.*;

/**
 * This class implements the graph_algorithms interface that represents a algorithms to run on a Graph_DS object.
 * This object implements a deep copy method for a Grapth_Ds, isConnected to check graph connectivity
 * and shortestPath method between two nodes. The main algorithm used in this graph is a basic BFS which "walks"
 * over the graph and tags all the connected nodes accordingly.
 * The BFS is changed and tweaked accordingly in each method for efficiency.
 *
 * @author shmuel.lavian
 */
public class Graph_Algo implements graph_algorithms {
    private graph _graph = new Graph_DS();

    /**
     * Initialize the the graph on which this set of algorithms operates on by pointing a Graph_DS object.
     *
     * @param g - The graph to initialize
     */
    @Override
    public void init(graph g) {
        _graph = g;
    }

    /**
     * Computes a deep copy of the received graph.
     *
     * @return returned_g - the copied graph
     */
    @Override
    public graph copy() {
        graph returned_g = new Graph_DS();
        for (node_data Nodedata : this._graph.getV()) {
            returned_g.addNode(Nodedata);
        }
        return returned_g;
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
        if (this._graph.nodeSize() <= 1 || this._graph == null) return true;
        Queue<Integer> queue = new LinkedList<>();
        int tmp_key=this._graph.getV().iterator().next().getKey();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(tmp_key);
        visited.add(tmp_key);
        while (!queue.isEmpty()) {
            tmp_key=queue.poll();
            for (node_data node : this._graph.getV(tmp_key)) {
                if (!visited.contains(node.getKey())) {
                    visited.add(node.getKey());
                    queue.add(node.getKey());
                }
            }
        }
        return visited.size()==this._graph.nodeSize();
    }

    /**
     * Returns an int representing the length of the shortest path between two nodes.
     * We implement this simply by returning the size of the ArrayList returned by this classes shortestPath method
     *
     * @param src  - start node
     * @param dest - target node
     * @return int representing the legth of the shortest path, -1 otherwise
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        if (src == dest) return 0;
        List<node_data> path = shortestPath(src,dest);
        if (path == null) return -1;
        else return path.size()-1;
    }

    /**
     * Returns an ordered list of the shortest path between two nodes, null otherwise,
     * We scan the graph with a BFS algorithm and tag all the visited node with their "parent" nodes key, according
     * to the direction of the scan. Uses a LinkedList as a queue for the next node (add() and poll() both O(1)).
     * A HashSet for the visited nodes (efficient contains() method O(1)). And an ArrayList to store the path.
     *
     * @param src  - start node id
     * @param dest - target node id
     * @return ordered ArrayList<node_data> containing the path
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if (this._graph.getNode(src) == null || this._graph.getNode(dest) == null) return null;
        if (src == dest) return null;
        List<node_data> rt_list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int tmp_key=src;
        int tmp_dest=dest;
        queue.add(tmp_key);
        visited.add(tmp_key);
        while (!queue.isEmpty() && !visited.contains(dest)) {
            tmp_key=queue.poll();
            for (node_data node : this._graph.getV(tmp_key)) {
                if (!visited.contains(node.getKey())) {
                    node.setTag(tmp_key);
                    visited.add(node.getKey());
                    queue.add(node.getKey());
                }
            }
        }
        if (!visited.contains(dest)) return null;
        while (tmp_dest != src) {
            rt_list.add(0,this._graph.getNode(tmp_dest));
            tmp_dest = this._graph.getNode(tmp_dest).getTag();
        }
        rt_list.add(0,this._graph.getNode(src));
        return rt_list;
    }
}
