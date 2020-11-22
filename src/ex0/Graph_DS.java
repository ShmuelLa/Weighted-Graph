package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class implements the graph interface that represents a mathematical graph
 * it is based on the NodeData class and serves as a pointer to its contained vertices
 * each graph has an integer that count the edges of the graph and a mode count that counts any change in
 * the inner state of the graph. Also we implemented a HashMap data structure
 * to point to each of the graph nodes.
 *
 * @author shmuel.lavian
 */
public class Graph_DS implements graph {
    private HashMap<Integer, node_data> _nodes;
    private int _e_size;
    private int _mc;

    /**
     * Default Graph_DS constructor. Creates a new graph with edge and modecount
     * together with a nodes Map
     */
    public Graph_DS() {
        _nodes = new HashMap<>();
        _e_size=0;
        _mc=0;
    }

    /**
     * Returns a pointer to a specific node is the graph by it's key/node id
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        return this._nodes.getOrDefault(key, null);
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
        if (node1 == node2) return true;
        if (!this._nodes.containsKey(node1) || !this._nodes.containsKey(node2)) return false;
        else return this._nodes.get(node1).hasNi(node2) && this._nodes.get(node2).hasNi(node1);
    }

    /**
     * Adds a new node to the nodes Map of the graph. Prevents adding an existing node.
     * This method runs in a constant O(1) time.
     *
     * @param n - The node_data to be added
     */
    @Override
    public void addNode(node_data n) {
        if (this._nodes.containsKey(n.getKey())) return;
        else {
            this._nodes.put(n.getKey(), n);
            _mc++;
        }
    }

    /**
     * Connects an edge between node1 and node2 and increment the edge and mode count ccordingly.
     * Does nothing if the edge already exists.
     * This method runs in a constant O(1) time.
     *
     * @param node1  - The first node to be connected
     * @param node2 - The second node to be connected
     */
    @Override
    public void connect(int node1, int node2) {
        if (!hasEdge(node1,node2) && this._nodes.containsKey(node1) && this._nodes.containsKey(node1)) {
            node_data n1 = this._nodes.get(node1);
            node_data n2 = this._nodes.get(node2);
            n1.addNi(n2);
            n2.addNi(n1);
            _e_size++;
            _mc++;
        }
    }

    /**
     * Return a pointer to a collection representing all the nodes in the graph.
     * This method runs in a constant O(1) time by using the values() method implemented in HashMap.
     *
     * @return Collection<node_data> - shallow copy to the nodes Map
     */
    @Override
    public Collection<node_data> getV() {
        return this._nodes.values();
    }

    /**
     * Return a pointer to a collection representing all the nodes connected to a specific node id in the graph.
     * Implemented by accessing the Map in the NodeData object.
     * This method runs in a constant O(1) time by using the values() method implemented in HashMap.
     *
     * @param node_id - the received node id
     * @return Collection<node_data> - shallow copy to the node_data.neighbors Map
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        if (this._nodes.containsKey(node_id)) return this._nodes.get(node_id).getNi();
        else return null;
    }

    /**
     * Deletes the received node from the graph and removes all edges which starts or ends at this node.
     * This method increment and decrement edge size and mode count accordingly and run in
     *  O(n) complexity when n represents the number of connected nodes/edges to be removed.
     *
     * @param key - the node id to be removed
     * @return The data of the removed node, null is not existent.
     */
    @Override
    public node_data removeNode(int key) {
        if (!this._nodes.containsKey(key)) {
            return null;
        }
        else {
            node_data tmp_node = this._nodes.get(key);
            for (node_data node : this.getV(key)) {
                node.removeNode(tmp_node);
                this._mc++;
                this._e_size--;
            }
            this._nodes.remove(key);
            this._mc++;
            return tmp_node;
        }
    }

    /**
     * Removes an edge between two nodes.
     * This method runs in a constant O(1) time.
     *
     * @param node1 - First node id
     * @param node2 - Second node id
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (this._nodes.containsKey(node1) && this._nodes.containsKey(node2)) {
            node_data n1 = this._nodes.get(node1);
            node_data n2 = this._nodes.get(node2);
            if (n1.hasNi(node2)) {
                n1.removeNode(n2);
                n2.removeNode(n1);
                _mc++;
                _e_size--;
            }
        }
    }

    /**
     * Returns the number of vertices (nodes) in the graph by accessing the node Map size.
     * This method runs in a constant O(1) time.
     *
     * @return - int representing the number of nodes
     */
    @Override
    public int nodeSize() {
        return this._nodes.size();
    }

    /**
     * Returns the number of edges in the graph.
     * This method runs in a constant O(1) time.
     *
     * @return - int representing the number of edges
     */
    @Override
    public int edgeSize() {
        return this._e_size;
    }

    /**
     * Returns the number of mode counts in the graph.
     * For each change in the inner state of the graph the mode count is incremented.
     * This method runs in a constant O(1) time.
     *
     * @return - int representing the number of mode counts
     */
    @Override
    public int getMC() {
        return this._mc;
    }
}
