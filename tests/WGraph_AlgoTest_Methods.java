import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest_Methods {

    @Test
    void init() {
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    @DisplayName("Shortest Path List<> Test")
    void shortestPath() {
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
        assertEquals(2,ga1.shortestPath(1,3).size());
        for (node_info n : ga1.shortestPath(1,12)) {
            System.out.print(n.getKey() + "  ");
        }
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}