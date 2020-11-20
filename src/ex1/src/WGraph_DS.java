package ex1.src;

import java.io.Serializable;
import java.util.*;

/**
 * This class implements the ex1.src.weighted_graph interface that represents a mathematical weighted graph
 * it implements two classes internally:
 * 1 - NodeInfo which implements ex1.src.node_info interface which includes the information and methods each node stores
 * 2 - EdgeInfo which stores all the data and methods for all the edges in the graph
 * Each graph consists of two HashMap data structures. One for the node and the other for the edges.
 * Each graph also has an integers that count the edges and the mode count (internal changes count) of the graph
 *
 * @author shmuel.lavian
 */
public class WGraph_DS implements weighted_graph, Serializable {
    private static final long serialVersionUID = 8597480894519396439L;
    private HashMap<Integer,node_info> _g_nodes;
    private HashMap<Integer,EdgeInfo> _g_edges;
    private int _e_size;
    private int _mc;

    /**
     * This internal class implements the ex1.src.node_info interface that represents a single vertex (node) in a graph,
     * Each vertex implemented with a unique integer key for access, a double tag for algorithmic usage
     * and a String for metadata. For each of those fields this class has a method to get or set them accordingly.
     * This class is implemented internally to avoid wrong usage of the graph.
     */
    private class NodeInfo implements node_info, Comparable<node_info>, Serializable {
        private static final long serialVersionUID = 515225244672992607L;
        private int _key;
        private double _tag;
        private String _str;

        /**
         * The main ex1.src.node_info constructor. Creates a new node with the received ID
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

        /**
         * Returns a string representation of the node
         * The string contains the node ID and info.
         *
         * @return a string representation the node.
         */
        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("Node Key: "+this._key+"\n");
            result.append("Node Tag: "+this._tag+"\n");
            result.append("Node MetaData: "+this._str+"\n");
            return result.toString();
        }

        /**
         * Indicates whether some other object is "equal to" this one.
         * This method is used mainly for testing.
         */
        @Override
        public boolean equals(Object obj) {
            node_info node = (node_info) obj;
            return this.toString().equals(node.toString());
        }
    }

    /**
     * This internal class implements the edges in the graph. It was added on top of the
     * given interfaces for improved performance and usability. Each node on the graph has and EdgeInfo
     * that contains all of it's edges and their weights accordingly. This class also implements more
     * outside of the ex1.src.node_info interface for greater flexibility in the project.
     */
    private class EdgeInfo implements Serializable {
        private static final long serialVersionUID = -1996414580609036360L;
        private HashMap<node_info,Double> _n_edges;

        /**
         * The methods default constructor, Creates a new EdgeInfo class
         * With a new Map
         */
        private EdgeInfo() {
            this._n_edges = new HashMap<>();
        }

        /**
         * Sets the weight of a specific node between this->destination
         * This is a one way change and is being used twice in the super classes
         * connect method in order to provide a bidirectional connection
         *
         * @param dest - The destination node
         * @param w - The weight to be set
         */
        private void setWeight(int dest, double w) {
            this._n_edges.put(_g_nodes.get(dest),w);
        }

        /**
         * Connects this to->destination node with an edge.
         * This is a one way change and is being used twice in the super classes
         * connect method in order to provide a bidirectional connection
         *
         * @param n - The destination node
         * @param w - The weight to be set
         */
        private void connectE(int n, double w) {
            this._n_edges.put(_g_nodes.get(n),w);
        }

        /**
         * Checks if this node contains a neighbor with the received ID
         *
         * @param n - The received node to check
         * @return Boolean - True the the connections exists, False otherwise
         */
        private boolean hasNi(int n) {
            return this._n_edges.containsKey(_g_nodes.get(n));
        }

        /**
         * Returns a Collections representing all the neighbors of a specific node
         * This method uses the HashMap keyset() method with O(1) complexity
         *
         * @return - Collection<ex1.src.node_info> of the nodes neighbors
         */
        private Collection<node_info> getNi() {
            return new ArrayList<>(this._n_edges.keySet());
        }

        /**
         * Returns the edge weight between this and -> dest node
         *
         * @param dest_key - The destination node ID
         * @return double - The edge weight value
         */
        private double getW(int dest_key) {
            return this._n_edges.get(_g_nodes.get(dest_key));
        }

        /**
         * Clears the HashMap containing all this nodes neighbors,
         * This method is implemented for the super classes RemoveNode method
         */
        private void removeSrc() {
            this._n_edges.clear();
        }

        /**
         * Returns the number of the connected nodes to this node ID
         *
         * @return int - The number of nodes connected to this node
         */
        private int getNiSize() {
            return this._n_edges.size();
        }

        /**
         * Removed the edge between this and -> dest node
         * This is a one way change and is being used twice in the super classes methods
         * in order to provide a bidirectional operation
         *
         * @param e - The received node ID to connect the edge to
         */
        private void removeEd(int e) {
            this._n_edges.remove(_g_nodes.get(e));
        }
    }

    /**
     * The default Graph_DS constructor. Creates a new graph with empty nodes and edges Map
     * alongside initialized edge counted and mod count
     */
    public WGraph_DS() {
        this._g_nodes = new HashMap<>();
        this._g_edges = new HashMap<>();
        _e_size = 0;
        _mc = 0;
    }

    /**
     * Returns a pointer to the specific the ex1.src.node_info by the node ID
     *
     * @param key - the node_id
     * @return ex1.src.node_info - The specified node info object
     */
    @Override
    public node_info getNode(int key) {
        return this._g_nodes.get(key);
    }

    /**
     * Checks if the is an edge between two nodes. Return true if and only if true.
     * Note: Each node by definition is connected to itself.
     * This method runs in a constant O(1) time.
     *
     * @param node1 - first node id to check
     * @param node2 - second node id to check
     * @return True if and only if there is and edge. False in any other case
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2 && this._g_nodes.containsKey(node1)) return true;
        if (!this._g_nodes.containsKey(node1) || !this._g_nodes.containsKey(node2)) return false;
        return this._g_edges.get(node1)._n_edges.containsKey(this.getNode(node2));
    }

    /**
     * Returns the weight value between two nodes.
     * This method runs in a constant O(1) time.
     *
     * @param node1 - First node
     * @param node2 - Second node
     * @return double - The value of the edge between the two node, -1 if the edge doesn't exist
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (node1 == node2) return 0;
        if (!this._g_nodes.containsKey(node1) || !this._g_nodes.containsKey(node2)) return -1;
        if (this._g_edges.get(node1)._n_edges.containsKey(this.getNode(node2))) {
            return this._g_edges.get(node1).getW(node2);
        }
        else return -1;
    }

    /**
     * Adds a new node to the graph with the given key ID.
     * This method runs in a constant O(1) time.
     *
     * @param key - The key of the new node to be added to the graph, if exists conducts no action
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
     * Connects an edge between node1 and node2 with the given weight.
     * Increments the edge and mode count accordingly. Does nothing if the edge with same weight already exists.
     * Otherwise just updates the weight.
     * This method runs in a constant O(1) time.
     *
     * @param node1 - First node
     * @param node2 - Second node
     * @param w - Given weight to be set between them
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (node1 == node2 || w < 0) return;
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
                    this._g_edges.get(node2).setWeight(node1,w);
                    _mc++;
                }
            }
        }
    }

    /**
     * Return a pointer to a collection representing all the nodes in the graph.
     * This method runs in a constant O(1) time by using the values() method implemented in HashMap.
     *
     * @return Collection<ex1.src.node_info> - shallow copy to the nodes Map
     */
    @Override
    public Collection<node_info> getV() {
        return this._g_nodes.values();
    }

    /**
     * This method returns a Collection containing all the nodes connected to the given node ID
     * by using the inner classes getNI method.
     * Run in O(k) time, k - being the degree of node_id.
     *
     * @param node_id - The received node to iterate on
     * @return Collection<ex1.src.node_info> containing this nodes connected neighbors
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        return this._g_edges.get(node_id).getNi();
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     *
     * @param key - Node ID to be deleted
     * @return ex1.src.node_info - of the deleted node, null if none exists
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
     * Removes an edge between two nodes in the graph.
     * Runs in a constant O(1) time.
     *
     * @param node1 - First node ID
     * @param node2 - Second node ID
     */
    @Override
    public void removeEdge(int node1, int node2) {
        this._g_edges.get(node1).removeEd(node2);
        this._g_edges.get(node2).removeEd(node1);
        _mc++;
        _e_size--;
    }

    /**
     * Returns the number of nodes in the graph.
     * Runs in a constant O(1) time.
     *
     * @return INT - number of nodes in the graph
     */
    @Override
    public int nodeSize() {
        return this._g_nodes.size();
    }

    /**
     * Return the number of edges in the graph.
     * Runs in a constant O(1) time.
     *
     * @return INT - number of edges in the graph
     */
    @Override
    public int edgeSize() {
        return this._e_size;
    }

    /**
     * Returns the Mode Count (inner changes counter) of the graph.
     * Any change in the inner state of the graph causes ModeCount incrementation
     *
     * @return INT - The graph's mode count
     */
    @Override
    public int getMC() {
        return this._mc;
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
        if (this._e_size != g_obj.edgeSize() || this.nodeSize() != g_obj.nodeSize()) return false;
        return Objects.equals(this.toString(), g_obj.toString());
    }

    /**
     * Returns a string representation of the graph. This method overrides Objects
     * toString() method. It represents each and every node Id in the graph alongside with it's neighbor count.
     * this method also records the node and edge size of the graph.
     *
     * For more information and for the full doc:
     * https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html
     *
     * @return String - representation of the graph.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        SortedSet<Integer> s_ni = new TreeSet<>();
        result.append("Total Nodes: ").append(this.nodeSize()).append(" ||  Total edges: ").append(this._e_size);
        result.append("\n");
        for (node_info n : this.getV()) {
            result.append("Node: ").append(n.getKey());
            result.append(" | Ni Count: ").append(this._g_edges.get(n.getKey()).getNiSize()).append(" | NiKey->Weight: ");
            for (node_info n1 : this.getV(n.getKey())) {
                s_ni.add(n1.getKey());
            }
            for (Integer in : s_ni) {
                result.append(this._g_nodes.get(in).getKey()).append("->");
                result.append(this.getEdge(n.getKey(), in)).append(" | ");
            }
            s_ni.clear();
            result.append("\n");
        }
        return result.toString();
    }
}



