package ex1.tests;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Other Apps used for testing:
 * https://graphonline.ru/en/
 * https://app.diagrams.net/
 */
class AlgoTest {

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
    }

    @Test
    void isConnected() {
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
    }

    @Test
    void shortestPath() {
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
}