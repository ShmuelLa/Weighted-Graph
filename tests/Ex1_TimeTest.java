import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * This is a test class containing the time efficiency test of the project
 * This is separated from all other tests for time efficiency debugging the project
 */
public class Ex1_TimeTest {

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
        weighted_graph g1 = Ex1_Tests.graph_creator(1000000,10000000);
        Long end = new Date().getTime();
        if ((end-start)/1000.0 > 20) fail();
    }
}


