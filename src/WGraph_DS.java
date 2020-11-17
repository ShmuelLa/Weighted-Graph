import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class WGraph_DS implements weighted_graph {
    private HashMap<Integer,node_info> _g_nodes;
    private HashMap<Integer,EdgeInfo> _g_edges;
    private int _e_size;
    private int _mc;
    private static int _ncount = 0;

    private class NodeInfo implements node_info, Comparable<node_info> {
        private int _key;
        private double _tag;
        private String _str;

        private NodeInfo() {
            this._key = _ncount;
            _ncount++;
            this._tag = -1;
            this._str = null;
        }

        private NodeInfo(int k) {
            this._key = k;
            this._tag = -1;
            this._str = null;
        }
        /**
         * Return the key (id) associated with this node.
         * Note: each node_data should have a unique key.
         *
         * @return
         */
        @Override
        public int getKey() {
            return this._key;
        }

        /**
         * return the remark (meta data) associated with this node.
         *
         * @return
         */
        @Override
        public String getInfo() {
            return this._str;
        }

        /**
         * Allows changing the remark (meta data) associated with this node.
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this._str = s;
        }

        /**
         * Temporal data (aka distance, color, or state)
         * which can be used be algorithms
         *
         * @return
         */
        @Override
        public double getTag() {
            return this._tag;
        }

        /**
         * Allow setting the "tag" value for temporal marking an node - common
         * practice for marking by algorithms.
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this._tag = t;
        }

        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         *
         * <p>The implementor must ensure
         * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
         * for all {@code x} and {@code y}.  (This
         * implies that {@code x.compareTo(y)} must throw an exception iff
         * {@code y.compareTo(x)} throws an exception.)
         *
         * <p>The implementor must also ensure that the relation is transitive:
         * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
         * {@code x.compareTo(z) > 0}.
         *
         * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
         * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
         * all {@code z}.
         *
         * <p>It is strongly recommended, but <i>not</i> strictly required that
         * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
         * class that implements the {@code Comparable} interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         *
         * <p>In the foregoing description, the notation
         * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
         * <i>signum</i> function, which is defined to return one of {@code -1},
         * {@code 0}, or {@code 1} according to whether the value of
         * <i>expression</i> is negative, zero, or positive, respectively.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(node_info o) {
            if (this._tag > o.getTag()) return 1;
            else if (this._tag < o.getTag()) return -1;
            return 0;
        }
    }

    private class EdgeInfo {
        private HashMap<Integer,Double> _n_edges;

        private EdgeInfo() {
            this._n_edges = new HashMap<>();
        }

        private void setWeight(int dest, double w) {
            this._n_edges.put(dest,w);
        }

        private void connectE(int n, double w) {
            this._n_edges.put(n,w);
        }

        private boolean hasNi(int n) {
            return this._n_edges.containsKey(n);
        }

        private Collection<node_info> getNi() {
            Collection<node_info> ni_list = new ArrayList<>();
            for (Integer k : this._n_edges.keySet()) {
                ni_list.add(_g_nodes.get(k));
            }
            return ni_list;
        }

        private double getW(int dest_key) {
            return this._n_edges.get(dest_key);
        }

        private void removeSrc() {
            this._n_edges.clear();
        }

        private int getNiSize() {
            return this._n_edges.size();
        }

        private void removeEd(int e) {
            this._n_edges.remove(e);
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
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2) return false;
        if (!this._g_nodes.containsKey(node1) || !this._g_nodes.containsKey(node2)) return false;
        return this._g_edges.get(node1)._n_edges.containsKey(node2);
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
        if (this._g_edges.get(node1)._n_edges.containsKey(node2)) {
            return this._g_edges.get(node1).getW(node2);
        }
        else return -1;
    }

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     *
     * @param key
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
        node_info tmo_n = this._g_nodes.get(key);
        this._g_nodes.remove(key);
        _mc = _mc+this._g_edges.get(key).getNiSize();
        _e_size = _e_size-this._g_edges.get(key).getNiSize();
        this._g_edges.get(key).removeSrc();
        this._g_edges.remove(key);
        return tmo_n;
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
}
