import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The main test class for WGraph_Algo
 *
 * External apps used for testing:
 * https://app.diagrams.net/
 * https://graphonline.ru/en/
 * https://app.diagrams.net/
 */
class WGraph_AlgoTest {

    /**
     * Creates a graph with the given node and edge size. The graph is created and connected
     * by randomizing the connected nodes and the connection weight for each and every connection
     *
     * @param n_size - Node size
     * @param e_size - Edge size
     * @return WGraph_DS - Graph with the given nodes and edges
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

    private weighted_graph_algorithms mainTestGraph(){
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

    @Test
    void init() {
        weighted_graph_algorithms wga2 = mainTestGraph();
        weighted_graph wg1 = mainTestGraph().copy();
        weighted_graph_algorithms wga1 = new WGraph_Algo();
        wga1.init(wg1);
        assertEquals(wga1.copy(),wga2.copy());
        wg1.removeNode(2);
        assertNotEquals(wga1.copy(),wga2.copy());
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
        weighted_graph_algorithms wga2 = mainTestGraph();
        weighted_graph wg1 = mainTestGraph().copy();
        weighted_graph_algorithms wga1 = new WGraph_Algo();
        wga1.init(wg1);
        assertEquals(wga1.copy(),wga2.copy());
        wg1.removeNode(2);
        assertNotEquals(wga1.copy(),wga2.copy());
        wg1.addNode(2);
        wg1.connect(1,2,3);
        assertEquals(wga1.copy(),wga2.copy());
        wg1.connect(1,2,2);
        assertNotEquals(wga1.copy(),wga2.copy());
        wg1.addNode(44);
        assertNotEquals(wga1.copy(),wga2.copy());


        weighted_graph gr = graph_creator(5,10);
        weighted_graph_algorithms gra = new WGraph_Algo();
        gra.init(gr);
        weighted_graph gr2 = gra.copy();
        assertEquals(gr,gr2);
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }

    @Test
    void reset() {
    }

    @Test
    @DisplayName("Connectivity check before and after changes")
    void isConnected() {
        WGraph_DS graph = new WGraph_DS();
        weighted_graph_algorithms algo = new WGraph_Algo();
        algo.init(graph);
        for (int i = 0; i <= 5; i++) {
            graph.addNode(i);
        }
        graph.connect(0,2,2);
        graph.connect(0,1,2);
        graph.connect(1,3,2);
        graph.connect(2,3,2);
        graph.connect(3,4,2);
        graph.connect(4,5,2);
        graph.connect(0,5,2);
        algo.init(graph);
        assertTrue(algo.isConnected());
        graph.removeNode(5);
        assertTrue(algo.isConnected());
        graph.removeNode(0);
        assertTrue(algo.isConnected());
        graph.removeNode(3);
        assertFalse(algo.isConnected());


        weighted_graph_algorithms wga = mainTestGraph();
        assertTrue(wga.isConnected());
        weighted_graph wg = mainTestGraph().copy();
        wg.removeNode(2);
        weighted_graph_algorithms wga2 = new WGraph_Algo();
        wga2.init(wg);
        assertTrue(wga2.isConnected());
        wg.removeNode(14);
        assertTrue(wga2.isConnected());
        wg.removeNode(15);
        assertTrue(wga2.isConnected());
        wg.removeNode(3);
        assertTrue(wga2.isConnected());
        wg.removeNode(7);
        assertFalse(wga2.isConnected());
        wg.connect(6,8,1);
        wga2.isConnected();
        wg.connect(8,6,1);
        assertTrue(wga2.isConnected());
    }

    @Test
    void shortestPathDist() {
        WGraph_DS g1 = new WGraph_DS();
        for (int i=1; i<=12; i++) {
            g1.addNode(i);
        }
        g1.connect(1,2,4);
        g1.connect(1,3,3);
        g1.connect(1,4,2);
        g1.connect(6,2,1);
        g1.connect(3,6,5);
        g1.connect(3,5,7);
        g1.connect(4,5,8);
        g1.connect(6,8,2);
        g1.connect(5,8,3);
        g1.connect(8,11,8);
        g1.connect(8,10,2);
        g1.connect(6,9,2);
        g1.connect(9,10,7);
        g1.connect(10,12,14);
        g1.connect(7,11,9);
        g1.connect(11,12,1);
        WGraph_Algo ga1 = new WGraph_Algo();
        ga1.init(g1);
        assertEquals(16,ga1.shortestPathDist(1,12));
    }

    @Test
    @DisplayName("Check Node Size")
    void nodeSize_test() {
        WGraph_DS g1 = new WGraph_DS();
        for (int i=0; i<100; i++) {
            g1.addNode(i);
        }
        assertEquals(100,g1.nodeSize());
        g1.removeNode(99);
        assertEquals(100-1,g1.nodeSize());
        for (int i=0; i<25; i++) {
            g1.removeNode(i);
        }
        assertEquals(100-1-25,g1.nodeSize());
    }

    @Test
    @DisplayName("Shortest Path List<> Test")
    void shortestPath() {
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