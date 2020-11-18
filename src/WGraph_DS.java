import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class implements the weighted_graph interface that represents a mathematical weighted graph
 * it implements two classes internally:
 * 1 - NodeInfo which implements node_info interface which includes the information and methods each node stores
 * 2 - EdgeInfo which stores all the data and methods for all the edges in the graph
 * Each graph consists of two HashMap data structures. One for the node and the other for the edges.
 * Each graph also has an integers that count the edges and the mode count (internal changes count) of the graph
 *
 * @author shmuel.lavian
 */
public class WGraph_DS implements weighted_graph, Serializable {
    private HashMap<Integer,node_info> _g_nodes;
    private HashMap<Integer,EdgeInfo> _g_edges;
    private int _e_size;
    private int _mc;
    private static int _ncount = 0;

    /**
     * This internal class implements the node_info interface that represents a single vertex (node) in a graph,
     * Each vertex implemented with a unique integer key for access, a double tag for algorithmic usage
     * and a String for metadata. For each of those fields this class has a method to get or set them accordingly.
     * This class is implemented internally to avoid wrong usage of the graph.
     */
    private class NodeInfo implements node_info, Comparable<node_info>, Serializable {
        private int _key;
        private double _tag;
        private String _str;

        /**
         * The main node_info constructor. Creates a new node with the received ID
         *
         * @param k - The ID to be set for the new node
         */
        private NodeInfo(int k) {
            this._key = k;
            this._tag = -1;
            this._str = null;
        }

        /**
         * Return the key (ID) associated with this node.
         * Each node_data should has a unique ID.
         *
         * @return INT - The key ID
         */
        @Override
        public int getKey() {
            return this._key;
        }

        /**
         * return the remark (meta data) associated with this node.
         *
         * @return String - This nodes meta data
         */
        @Override
        public String getInfo() {
            return this._str;
        }

        /**
         * Allows changing the meta data associated with this node.
         *
         * @param s - The new nodes String meta data
         */
        @Override
        public void setInfo(String s) {
            this._str = s;
        }

        /**
         * Returns the temporal data (aka distance, color, or state)
         * The tag is used for algorithmic purposes.
         *
         * @return double - The nodes tag values
         */
        @Override
        public double getTag() {
            return this._tag;
        }

        /**
         * Sets the nodes tag value.
         *
         * @param t - the new value of the nodes tag
         */
        @Override
        public void setTag(double t) {
            this._tag = t;
        }

        /**
         * Overrides the compareTo() method implemented by Comparable interface
         * This method is used to compare the tag value of two nodes and used in this project solely
         * for comparing the minimal distance in the shortestPath method based on Dijkstra's algorithm
         * This method prevents NullPointerException and ClassCastException bt catching them before comparing.
         * For more information about the comparable interface:
         * https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         */
        @Override
        public int compareTo(node_info o) {
            if (this._tag > o.getTag()) return 1;
            else if (this._tag < o.getTag()) return -1;
            return 0;
        }
    }

    /**
     * This internal class implements the edges in the graph. It was added on top of the
     * given interfaces for improved performance and usability. Each node on the graph has and EdgeInfo
     * that contains all of it's edges and their weights accordingly. This class also implements more
     * outside of the node_info interface for greater flexibility in the project.
     */
    private class EdgeInfo implements Serializable {
        private HashMap<node_info,Double> _n_edges;

        private EdgeInfo() {
            this._n_edges = new HashMap<>();
        }

        private void setWeight(int dest, double w) {
            this._n_edges.put(_g_nodes.get(dest),w);
        }

        private void connectE(int n, double w) {
            this._n_edges.put(_g_nodes.get(n),w);
        }

        private boolean hasNi(int n) {
            return this._n_edges.containsKey(_g_nodes.get(n));
        }

        private Collection<node_info> getNi() {
            return new ArrayList<>(this._n_edges.keySet());
        }

        private double getW(int dest_key) {
            return this._n_edges.get(_g_nodes.get(dest_key));
        }

        private void removeSrc() {
            this._n_edges.clear();
        }

        private int getNiSize() {
            return this._n_edges.size();
        }

        private void removeEd(int e) {
            this._n_edges.remove(_g_nodes.get(e));
        }
    }

    public WGraph_DS() {
        this._g_nodes = new HashMap<>();
        this._g_edges = new HashMap<>();
        _e_size = 0;
        _mc = 0;
    }

    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        return this._g_nodes.get(key);
    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1 - The first node to be checked
     * @param node2 - The second node to be checked
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2) return true;
        if (!this._g_nodes.containsKey(node1) || !this._g_nodes.containsKey(node2)) return false;
        return this._g_edges.get(node1)._n_edges.containsKey(this.getNode(node2));
    }

    /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (node1 == node2) return 0;
        if (this._g_edges.get(node1)._n_edges.containsKey(this.getNode(node2))) {
            return this._g_edges.get(node1).getW(node2);
        }
        else return -1;
    }

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     *
     * @param key - The key of the new node to be added to the graph
     */
    @Override
    public void addNode(int key) {
        if (!this._g_nodes.containsKey(key)) {
            node_info n = new NodeInfo(key);
            EdgeInfo e = new EdgeInfo();
            this._g_nodes.put(key,n);
            this._g_edges.put(key,e);
            _mc++;
        }
    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     *
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (node1 == node2) return;
        if (this._g_edges.containsKey(node1) && this._g_edges.containsKey(node2)) {
            if (this._g_edges.containsKey(node1) && !this._g_edges.get(node1).hasNi(node2)) {
                this._g_edges.get(node1).connectE(node2,w);
                this._g_edges.get(node2).connectE(node1,w);
                _e_size++;
                _mc++;
                return;
            }
            if (this._g_edges.get(node1).hasNi(node2)) {
                if (this._g_edges.get(node1).getW(node2) == w) {
                    return;
                }
                else {
                    this._g_edges.get(node1).setWeight(node2,w);
                    _mc++;
                }
            }
        }
    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV() {
        return this._g_nodes.values();
    }

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Note: this method can run in O(k) time, k - being the degree of node_id.
     *
     * @param node_id
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        return this._g_edges.get(node_id).getNi();
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_info removeNode(int key) {
        if (!this._g_nodes.containsKey(key)) return null;
        node_info tmp_n = this._g_nodes.get(key);
        for (node_info n : this.getV(key)) {
            this.removeEdge(n.getKey(),key);
        }
        this._g_nodes.remove(key);
        _mc = _mc+this._g_edges.get(key).getNiSize();
        _e_size = _e_size-this._g_edges.get(key).getNiSize();
        this._g_edges.get(key).removeSrc();
        this._g_edges.remove(key);
        return tmp_n;
    }

    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        this._g_edges.get(node1).removeEd(node2);
        this._g_edges.get(node2).removeEd(node1);
        _mc++;
        _e_size--;
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return this._g_nodes.size();
    }

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return this._e_size;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return
     */
    @Override
    public int getMC() {
        return this._mc;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        weighted_graph g_obj = (WGraph_DS) obj;
        if (this._e_size != g_obj.edgeSize() || this.nodeSize() != g_obj.nodeSize()) return false;
        return Objects.equals(this.toString(), g_obj.toString());
    }

    /**
     * Returns a string representation of the graph.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Total Nodes: ").append(this.nodeSize()).append(" Total edges: ").append(this._e_size).append("\n");
        for (node_info n : this.getV()) {
            result.append("Node: ").append(n.getKey()).append(" Ni count: ").append(this._g_edges.get(n.getKey()).getNiSize());
            result.append("\n");
        }
        return result.toString();
    }
}

