import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest_Sizes {

    @BeforeAll
    static void g_creator() {
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
}
