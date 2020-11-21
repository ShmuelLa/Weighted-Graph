import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The main WGraph_DS testing class,
 * will test all the main logical methods of the class
 *
 * Other Apps used for testing:
 * https://graphonline.ru/en/
 * https://app.diagrams.net
 */
public class WGraph_DSTest {

    /**
     * Creates this graph for testing:
     * (WikiPictures/testgraph.jpg)
     * @return WGraph_DS - an initialized graph with the above settings
     */
    private weighted_graph mainTestGraph(){
        weighted_graph wg = new WGraph_DS();
        for (int i=1; i<=16; i++) {
            wg.addNode(i);
        }
        wg.connect(1,2,3);
        wg.connect(1,3,3);
        wg.connect(1,5,5);
        wg.connect(1,4,7);
        wg.connect(1,6,7);
        wg.connect(6,7,22);
        wg.connect(4,6,2);
        wg.connect(4,10,2);
        wg.connect(10,11,2);
        wg.connect(7,8,15);
        wg.connect(8,15,3);
        wg.connect(7,9,2);
        wg.connect(9,16,33);
        wg.connect(9,13,4);
        wg.connect(12,7,2);
        wg.connect(12,11,3);
        wg.connect(12,13,4);
        wg.connect(13,14,2);
        wg.connect(14,16,3);
        return wg;
    }

    /**
     * Simple getNode test
     */
    @Test
    void getNode() {
        weighted_graph wg = mainTestGraph();
        assertEquals(wg.getNode(1),wg.getNode(1));
        assertEquals(1,wg.getNode(1).getKey());
        assertNotEquals(wg.getNode(1),wg.getNode(2));
        assertNull(wg.getNode(44));
    }

    /**
     * Checks edge methods in the graph while connects, removing
     * and counting edges in the graph
     */
    @Test
    @DisplayName("hasEdge, connect, removeNode, removeEdge")
    void EdgeTesting() {
        weighted_graph wg = mainTestGraph();
        assertTrue(wg.hasEdge(1,2));
        assertTrue(wg.hasEdge(2,1));
        assertFalse(wg.hasEdge(1,16));
        assertFalse(wg.hasEdge(1,44));
        assertTrue(wg.hasEdge(9,16));
        assertTrue(wg.hasEdge(9,9));
        wg.removeNode(9);
        assertFalse(wg.hasEdge(9,9));
        assertFalse(wg.hasEdge(9,16));
        wg.removeEdge(1,2);
        assertFalse(wg.hasEdge(2,1));
        assertFalse(wg.hasEdge(1,2));
        wg.connect(1,2,2);
        assertTrue(wg.hasEdge(1,2));
        assertTrue(wg.hasEdge(2,1));
        wg.removeEdge(1,1);
        assertTrue(wg.hasEdge(1,1));
        wg.removeEdge(44,35);
    }

    /**
     * Test the weighted graph main component the edge wights
     * by setting  new edges and retesting them
     */
    @Test
    @DisplayName("getEdge value testing")
    void getEdge() {
        weighted_graph wg = mainTestGraph();
        assertEquals(3,wg.getEdge(1,2));
        assertEquals(3,wg.getEdge(2,1));
        wg.connect(5,6,0);
        assertEquals(0,wg.getEdge(5,6));
        assertEquals(0,wg.getEdge(6,5));
        wg.connect(5,6,2);
        assertEquals(2,wg.getEdge(5,6));
        assertEquals(2,wg.getEdge(6,5));
        wg.removeEdge(5,6);
        assertEquals(-1,wg.getEdge(5,6));
        assertEquals(-1,wg.getEdge(6,5));
        wg.removeNode(1);
        assertEquals(-1,wg.getEdge(1,2));
        assertEquals(-1,wg.getEdge(2,1));
        wg.addNode(1);
        assertEquals(-1,wg.getEdge(1,2));
        assertEquals(-1,wg.getEdge(2,1));
    }

    /**
     * Adds & removes nodes while testing total node size
     */
    @Test
    @DisplayName("nodeSize & addNode")
    void NodeSizenAdd() {
        weighted_graph wg = mainTestGraph();
        assertEquals(16,wg.nodeSize());
        int size = wg.nodeSize();
        wg.addNode(1);
        assertEquals(size,wg.nodeSize());
        wg.addNode(77);
        assertEquals(size+1,wg.nodeSize());
    }

    @Test
    @DisplayName("removeNode, nodeSize, edgeSize Test")
    void g_change_t1() {
        weighted_graph wg = mainTestGraph();
        assertEquals(19,wg.edgeSize());
        assertTrue(wg.hasEdge(1,5));
        assertTrue(wg.hasEdge(5,1));
        wg.removeNode(1);
        assertEquals(19-5,wg.edgeSize());
        assertFalse(wg.hasEdge(1,5));
        assertFalse(wg.hasEdge(5,1));
        assertFalse(wg.hasEdge(1,2));
        assertFalse(wg.hasEdge(1,3));
        assertFalse(wg.hasEdge(1,6));
        assertFalse(wg.hasEdge(1,4));
        wg.connect(2,5,4);
        assertEquals(19-4,wg.edgeSize());
        for (int i=2; i<=16; i++) {
            wg.removeNode(i);
        }
        assertEquals(0,wg.edgeSize());
        assertEquals(0,wg.nodeSize());
        for (int i=1; i<=16; i++) {
            assertNull(wg.removeNode(i));
        }
    }

    /**
     * Tests the getV method by checking the received Collection view of the whole graph
     */
    @Test
    @DisplayName("getV whole graph test")
    void getV() {
        weighted_graph wg = mainTestGraph();
        Collection<node_info> test_c = new ArrayList<>(wg.getV());
        assertEquals(test_c.size(),wg.getV().size());
        int[] arr1 = new int[test_c.size()];
        int[] arr2 = new int[wg.getV().size()];
        for (int i=0; i<wg.getV().size(); i++) {
            arr1[i] = wg.getNode(i+1).getKey();
        }
        for (node_info n : wg.getV()) {
            arr2[n.getKey()-1] = n.getKey();
        }
        assertEquals(Arrays.toString(arr1),Arrays.toString(arr2));
    }

    /**
     * Tests the getV method by checking the received Collection view of a specific node's neighbors
     */
    @Test
    @DisplayName("getV neighbor test")
    void testGetV() {
        weighted_graph wg = mainTestGraph();
        wg.removeNode(1);
        assertEquals(2,wg.getV(6).size());
        Collection<node_info> temp = wg.getV(1);
        assertEquals(0,temp.size());
    }

    /**
     * Tests mode count changes in the graph
     */
    @Test
    @DisplayName("mode count test")
    void getMC() {
        weighted_graph wg = new WGraph_DS();
        for (int i=0; i<10; i++) {
            wg.addNode(i);
        }
        assertEquals(10,wg.getMC());
        wg.connect(1,2,2);
        wg.connect(1,3,2);
        wg.connect(1,4,2);
        wg.connect(1,5,2);
        assertEquals(10+4,wg.getMC());
        wg.connect(1,2,444);
        wg.removeEdge(1,2);
        assertEquals(10+4+2,wg.getMC());
        wg.removeNode(1);
        assertEquals(10+4+2+4,wg.getMC());
    }
}
