package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The main test class for ex1.src.WGraph_Algo
 *
 * External apps used for testing:
 * https://app.diagrams.net/
 * https://graphonline.ru/en/
 * https://app.diagrams.net
 */
class WGraph_AlgoTest {

    /**
     * Creates a graph with the given node and edge size. The graph is created and connected
     * by randomizing the connected nodes and the connection weight for each and every connection
     *
     * @param n_size - Node size
     * @param e_size - Edge size
     * @return ex1.src.WGraph_DS - Graph with the given nodes and edges
     */
    public static weighted_graph graph_creator(int n_size, int e_size){
        weighted_graph result = new WGraph_DS();
        for (int i=0; i<n_size; i++) {
            result.addNode(i);
        }
        Random rnd_dbl = new Random();
        while (result.edgeSize() < e_size) {
            for (node_info n : result.getV()) {
                result.connect(n.getKey(),rndInt(n.getKey(),n_size),rnd_dbl.nextDouble());
                if (result.edgeSize() >= e_size) break;
            }
            if (result.edgeSize() >= e_size) break;
        }
        return result;
    }

    /**
     * This method was created for randomising the connections for the graph creator
     * if prevents the connected node ID to be equal or outside of graph bounds
     *
     * @param n_id - The node to be connected
     * @param n_size - The node size of the graph
     * @return INT - The node ID chosen to be connected
     */
    private static int rndInt(int n_id, int n_size) {
        Random rnd_int = new Random();
        int result = rnd_int.nextInt(n_size);
        while (result==0 || result > n_size || result==n_id) {
            result = rnd_int.nextInt(n_size);
        }
        return result;
    }

    /**
     * Creates this graph for testing:
     * WikiPictures/testgraph.jpg
     *
     * @return ex1.src.weighted_graph_algorithms - The graph initialized for testing
     */
    private weighted_graph_algorithms mainTestGraphAlg(){
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
        wg.connect(7,8,15);
        wg.connect(8,15,3);
        wg.connect(7,9,2);
        wg.connect(9,16,33);
        wg.connect(9,13,4);
        wg.connect(12,7,2);
        wg.connect(12,11,3);
        wg.connect(12,13,4);
        wg.connect(13,14,2);
        wg.connect(10,11,2);
        wg.connect(14,16,3);
        weighted_graph_algorithms wga = new WGraph_Algo();
        wga.init(wg);
        return wga;
    }

    /**
     * Simple init test
     */
    @Test
    void init() {
        weighted_graph_algorithms wga2 = mainTestGraphAlg();
        weighted_graph wg1 = mainTestGraphAlg().copy();
        weighted_graph_algorithms wga1 = new WGraph_Algo();
        wga1.init(wg1);
        assertEquals(wga1.copy(),wga2.copy());
        wg1.removeNode(2);
        assertNotEquals(wga1.copy(),wga2.copy());
    }

    /**
     * Tests saving and loading a graph.
     * Also tests the copy method
     */
    @Test
    @DisplayName("Save/Load & Copy test")
    void save_load_copy() {
        weighted_graph_algorithms wga = mainTestGraphAlg();
        weighted_graph wg = WGraph_DSTest.mainTestGraph();
        weighted_graph_algorithms wga2 = new WGraph_Algo();
        wga2.init(wg);
        wga2.save("testname");
        wga2.load("testname");
        assertEquals(wga2.getGraph(),wg);
        assertEquals(wga2.copy(),wg);
        assertEquals(wga2.getGraph(),wga.getGraph());
        assertEquals(wga2.copy(),wga.getGraph());
        wga.load("testname");
        assertEquals(wga.getGraph(),wga2.getGraph());
        wg.removeNode(1);
        wga2.save("testname2");
        wga2.load("testname2");
        assertNotEquals(wga.getGraph(),wg);
        assertNotEquals(wga.copy(),wg);
        weighted_graph gr = graph_creator(5,10);
        weighted_graph_algorithms gra = new WGraph_Algo();
        gra.init(gr);
        weighted_graph gr2 = gra.copy();
        assertEquals(gr,gr2);
        gr.connect(1,2,5000);
        assertNotEquals(gr,gr2);
        weighted_graph_algorithms gra2 = new WGraph_Algo();
        gra2.init(gr2);
        assertNotEquals(gra.copy(),gra2.copy());
    }

    /**
     * Cheks the connectivity of a graph while changing
     */
    @Test
    @DisplayName("Connectivity check before and after changes")
    void isConnected() {
        weighted_graph wg = WGraph_DSTest.mainTestGraph();
        weighted_graph_algorithms wga = new WGraph_Algo();
        wga.init(wg);
        assertTrue(wga.isConnected());
        wg.removeNode(5);
        assertTrue(wga.isConnected());
        wg.removeNode(13);
        assertTrue(wga.isConnected());
        wg.removeNode(14);
        assertTrue(wga.isConnected());
        wg.removeNode(15);
        assertTrue(wga.isConnected());
        wg.removeNode(7);
        assertFalse(wga.isConnected());
        wg.connect(6,8,0);
        wg.connect(6,9,0);
        assertTrue(wga.isConnected());
        weighted_graph wg2 = WGraph_DSTest.mainTestGraph();
        weighted_graph_algorithms wga2 = new WGraph_Algo();
        wga.init(wg2);
        assertTrue(wga2.isConnected());
        wg2.addNode(2);
        assertTrue(wga2.isConnected());
    }

    /**
     * Cheבks the shortest path distance in a set complex graph while changing it
     */
    @Test
    @DisplayName("Shortest path distance test")
    void shortestPathDist() {
        weighted_graph wg = WGraph_DSTest.mainTestGraph();
        weighted_graph_algorithms wga = new WGraph_Algo();
        wga.init(wg);
        assertEquals(0,wga.shortestPathDist(1,1));
        assertEquals(7,wga.shortestPathDist(1,6));
        assertEquals(-1,wga.shortestPathDist(1,0));
        assertEquals(23,wga.shortestPathDist(1,16));
        wg.removeNode(14);
        assertEquals(23-9+4+33,wga.shortestPathDist(1,16));
    }

    /**
     * Tests the shotest path List method algorithm
     */
    @Test
    @DisplayName("Shortest Path List<> Test")
    void shortestPath() {
        weighted_graph wg = WGraph_DSTest.mainTestGraph();
        weighted_graph_algorithms wga = new WGraph_Algo();
        wga.init(wg);
        assertEquals(0,wga.shortestPath(1,1).size());
        assertEquals(1,wga.shortestPath(1,6).get(0).getKey());
        assertEquals(6,wga.shortestPath(1,6).get(1).getKey());
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(4);
        test.add(10);
        test.add(11);
        test.add(12);
        test.add(13);
        test.add(14);
        test.add(16);
        int index = 0;
        for (node_info n : wga.shortestPath(1,16)) {
            assertEquals(n.getKey(),test.get(index));
            index++;
        }
        index = 0;
        wg.removeNode(14);
        test.remove(7);
        test.clear();
        test.add(1);
        test.add(4);
        test.add(10);
        test.add(11);
        test.add(12);
        test.add(7);
        test.add(9);
        test.add(16);
        for (node_info n : wga.shortestPath(1,16)) {
            assertEquals(n.getKey(),test.get(index));
            index++;
        }
        assertNull(wga.shortestPath(1,55));
        assertNull(wga.shortestPath(-22,0));
    }

    /**
     * Build a graph with 1,000,000 nodes and 10,000,000 edges
     * Creates them with randomized modifiers. Checks if the total process takes less than 20 seconds.
     * The 20 time limitation is calculated by and average of comparing between the
     * majority of students in the courses whatsapp group
     */
    @Test
    @DisplayName("1M v, 10M e graph time test")
    void Time() {
        Long start = new Date().getTime();
        weighted_graph g1 = graph_creator(1000000,10000000);
        Long end = new Date().getTime();
        System.out.println("Time test run in: "+(end-start)/1000.0);
        if ((end-start)/1000.0 > 20) fail();
    }
}