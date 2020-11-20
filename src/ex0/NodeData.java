package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class implements the node_data interface that represents a single vertex in a graph, Each vertex was
 * implemented with a unique integer key for access, an integer tag for algorithmic usage, a Strine for metadata
 * and a Map data structure that contains all the nodes neighbors for efficient access to key and value
 *
 * @author shmuel.lavian
 */
public class NodeData implements node_data {
    private int _key;
    private int _tag;
    private String _str;
    private static int _ncount = 0;
    private HashMap<Integer,node_data> _neighbors;

    /**
     * Default NodeData constructor
     * defines each node with a key,tag,string and HashMap data structure
     */
    public NodeData() {
        _key = _ncount;
        _ncount++;
        _str=null;
        _tag=0;
        _neighbors = new HashMap<>();
    }

    /**
     * Return the node ID, each node_data have a unique key.
     *
     * @return The int key (id) associated with this node.
     */
    @Override
    public int getKey() {
        return this._key;
    }

    /**
     * Returns a collection with all the Neighbor nodes of this node_data
     */
    @Override
    public Collection<node_data> getNi() {
        return this._neighbors.values();
    }

    /**
     * Checks if there is an edge between this and received node
     *
     * @param key - received node
     * @return boolean result
     */
    @Override
    public boolean hasNi(int key) {
        return this._neighbors.containsKey(key);
    }

    /**
     * This method adds the node_data (t) to the neighbors of this node_data's Map
     * Prevents connecting a node to itself
     *
     * @param t - the node_data to be added
     */
    @Override
    public void addNi(node_data t) {
        if (t.getKey() == this.getKey()) return;
        else {
            this._neighbors.put(t.getKey(),t);
        }
    }

    /**
     * Removes the edge, if existed, between this node and the received node
     * Implemented by removing the node_data object from the neighbors Map
     *
     * @param node - the node to be removed
     */
    @Override
    public void removeNode(node_data node) {
        this._neighbors.remove(node.getKey());
    }

    /**
     * Returns the metadata of this vertex
     *
     * @return a String ovject with the vertex's metadata
     */
    @Override
    public String getInfo() {
        return this._str;
    }

    /**
     * Sets the String metadata assigned to this vertex
     *
     * @param s - the new value of the metadata
     */
    @Override
    public void setInfo(String s) {
        this._str=s;
    }

    /**
     * Temporal tagging of the node, used commonly in graph algorithms
     * Used in this projects BFS algorithm
     *
     * @return Int - this vertex's tag
     */
    @Override
    public int getTag() {
        return this._tag;
    }

    /**
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this._tag=t;
    }
}
