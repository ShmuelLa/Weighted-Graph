package ex1.src;

import java.io.*;
import java.util.*;

/**
 * This class implements the ex1.src.weighted_graph_algorithms interface that represents algorithms to run on a
 * ex1.src.weighted_graph object. This class implements a few main methods including a deep copy method,
 * isConnected to check graph connectivity (implemented via BFS algorithm), shortest path and
 * shortest length method between two nodes (implemented via Dijkstra's algorithm)
 * and file save and load method via serialization.
 *
 * @author shmuel.lavian
 */
public class WGraph_Algo implements weighted_graph_algorithms {
    weighted_graph _g = new WGraph_DS();


    /**
     * Initialize the the graph on which this set of algorithms operates on by pointing a Graph_DS object.
     *
     * @param g - The graph to initialize
     */
    @Override
    public void init(weighted_graph g) {
        this._g = g;
    }

    /**
     * Returns a pointer to the underlying graph of which this class works on.
     *
     * @return this ex1.src.WGraph_DS Object
     */
    @Override
    public weighted_graph getGraph() {
        return this._g;
    }

    /**
     * Computes a deep copy of the received graph.
     *
     * @return returned_g - the copied graph
     */
    @Override
    public weighted_graph copy() {
        weighted_graph result = new WGraph_DS();
        if (this._g.nodeSize() == 0) return result;
        for (node_info n : this._g.getV()) {
            result.addNode(n.getKey());
        }
        if (this._g.edgeSize() == 0) return result;
        for (node_info n : this._g.getV()) {
            for (node_info inner : this._g.getV(n.getKey())) {
                result.connect(n.getKey(),inner.getKey(),this._g.getEdge(n.getKey(),inner.getKey()));
            }
        }
        return result;
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
        Queue<node_info> queue = new LinkedList<>();
        HashSet<node_info> visited = new HashSet<>();
        node_info tmp_n = this._g.getV().iterator().next();
        queue.add(tmp_n);
        visited.add(tmp_n);
        while (!queue.isEmpty()) {
            tmp_n=queue.poll();
            for (node_info node : this._g.getV(tmp_n.getKey())) {
                if (!visited.contains(node)) {
                    visited.add(node);
                    queue.add(node);
                }
                if (visited.size() == this._g.nodeSize()) return true;
            }
            if (visited.size() == this._g.nodeSize()) return true;
        }
        return visited.size() == this._g.nodeSize();
    }

    /**
     * Returns the length of the shortest path between two nodes based on Dijkstra's Algorithm
     * We scan the graph using a PriorityQueue which compares based on the minimal node tag double
     * This way we can tag each node we reach with the shortest length from the last visited node to it.
     * This works by enqueuing the node with the smallest tag (path length) from the queue and checking it's neighbors
     * After we finish the graph scan and only if we reached the destination we return the destination nodes tag.
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return double which represents the shortest path from src to dest, -1 otherwise
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (this._g.nodeSize()<=1 || this._g.getNode(src)==null || this._g.getNode(dest)==null) return -1;
        if (src == dest) return 0.0;
        PriorityQueue<node_info> pq = new PriorityQueue<>();
        double result;
        node_info cur = this._g.getNode(src);
        pq.add(cur);
        cur.setTag(0);
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (!Objects.equals(cur.getInfo(), "y")) {
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
        if (!Objects.equals(cur.getInfo(), "y")) return -1;
        this.reset();
        return result;
    }

    /**
     * returns an ordered List of ex1.src.node_info objects representing the shortest path between src to dest.
     * In here we use the same mechanism and algorithm used in the shortestPathDist() method
     * But the main difference is we store each nodes parent (depends on the the scanning direction of the graph)
     * and after we finished the scan, if we reached the destination, we build the path List by getting each nodes
     * parent node from the parents Map.
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return ordered List of ex1.src.node_info objects containing the shortest path from src to dest ,null otherwise
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        if (this._g.nodeSize()<=1 || this._g.getNode(src)==null || this._g.getNode(dest)==null) return null;
        if (src == dest) return new ArrayList<>();
        PriorityQueue<node_info> pq = new PriorityQueue<>();
        HashMap<node_info,node_info> parent = new HashMap<>();
        List<node_info> result = new ArrayList<>();
        node_info cur = this._g.getNode(src);
        pq.add(cur);
        cur.setTag(0);
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (!Objects.equals(cur.getInfo(),"y")) {
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
        if (!Objects.equals(cur.getInfo(), "y")) return null;
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
        boolean flag = false;
        try {
            FileOutputStream f = new FileOutputStream (file, true);
            ObjectOutputStream  graph = new ObjectOutputStream (f);
            graph.writeObject(this._g);
            f.close();
            graph.close();
            flag = true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Loads a graph from a file to this graph_algo. Overrides the underlying graph.
     *
     * @param file - file name or path
     * @return true - if the graph was successfully loaded. Changes the underlying graph
     */
    @Override
    public boolean load(String file) {
        boolean flag = false;
        try {
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream gr = new ObjectInputStream(fi);
            this._g = (weighted_graph) gr.readObject();
            fi.close();
            gr.close();
            flag = true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Used to reset the tags and metadata of each node after finishing
     * BFS and Dijkstra's algorithms
     */
    public void reset() {
        for (node_info n : this._g.getV()) {
            if (n.getTag() != 0 || n.getInfo() != null) {
                n.setTag(-1);
                n.setInfo(null);
            }
        }
    }

    /**
     * This method overrides the equals method from Object interface.
     * It is used for graph comparing. Used vastly in testing and debugging.
     * It uses the toString method which is override as well for this project.
     *
     * For more information and for the full doc:
     * https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html
     *
     * @param obj the reference object with which to compare.
     * @return boolean - if this object is the same as the obj
     */
    @Override
    public boolean equals(Object obj) {
        weighted_graph g_obj = (WGraph_DS) obj;
        if (_g.edgeSize() != g_obj.edgeSize() || _g.nodeSize() != g_obj.nodeSize()) return false;
        return Objects.equals(_g.toString(), g_obj.toString());
    }
}
