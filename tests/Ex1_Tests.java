import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex1_Tests {

    @BeforeAll
    static void g_creator() {
    }

    @Test
    @DisplayName("Elad test")
    void elad() {
        WGraph_DS graph = new WGraph_DS();
        weighted_graph_algorithms algo = new WGraph_Algo();
        algo.init(graph);
        for (int i = 0; i <= 5; i++) {
            graph.addNode(i);
        }
        graph.connect(0,2,6);
        graph.connect(0,1,4);
        graph.connect(1,3,2);
        graph.connect(2,3,4/5);
        graph.connect(3,4,4.3);
        graph.connect(4,5,4);
        graph.connect(0,5,6);
        algo.init(graph);
        System.out.println( "edge" + graph.edgeSize());
        System.out.println("nodes"+graph.nodeSize());
        System.out.println(algo.isConnected());
        graph.removeNode(5);
        System.out.println(algo.isConnected());
        assertEquals(true,algo.isConnected());
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
        for (node_info n : ga1.shortestPath(1,12)) {
            System.out.print(n.getKey() + "  ");
        }
    }

    @Test
    void String() {
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
        System.out.println(g1.toString());
    }
}
