package ex0;

import java.awt.datatransfer.ClipboardOwner;
import java.sql.Array;
import java.util.*;

public class testbench {

    public static void main(String[] args) {
        Graph_DS graph = new Graph_DS();
        for (int i = 0; i <= 400; i++) {
            node_data node = new NodeData();
            graph.addNode(node);
        }
        graph.connect(1,2);
        graph.connect(1,3);
        graph.connect(1,4);
        graph.connect(2,1);
        graph.connect(2,5);
        graph.connect(2,6);
        graph.connect(2,7);
        graph.connect(3,5);
        graph.connect(3,8);
        graph.connect(3,4);
        graph.connect(3,1);
        graph.connect(4,3);
        graph.connect(4,1);
        graph.connect(4,9);
        graph.connect(5,8);
        graph.connect(5,3);
        graph.connect(5,7);
        graph.connect(5,2);
        graph.connect(6,2);
        graph.connect(7,5);
        graph.connect(7,2);
        graph.connect(8,3);
        graph.connect(8,5);
        graph.connect(9,4);
        graph.connect(1,2);
        graph.connect(1,3);
        graph.connect(1,4);
        graph.connect(2,1);
        graph.connect(2,5);
        graph.connect(2,6);
        graph.connect(2,7);
        graph.connect(3,5);
        graph.connect(3,8);
        graph.connect(3,4);
        graph.connect(3,1);
        graph.connect(4,3);
        graph.connect(4,1);
        graph.connect(4,9);
        graph.connect(5,8);
        graph.connect(5,3);
        graph.connect(5,7);
        graph.connect(5,2);
        graph.connect(6,2);
        graph.connect(7,5);
        graph.connect(7,2);
        graph.connect(8,3);
        graph.connect(8,5);
        graph.connect(9,4);

        Graph_Algo ga1 = new Graph_Algo();
        ga1.init(graph);

        System.out.println("edges  " + graph.edgeSize());
        System.out.println("nodes  " + graph.nodeSize());
        System.out.println("modecounts  " + graph.getMC());

        List<node_data> li1 = ga1.shortestPath(1,0);
        System.out.println(li1);

        ga1.shortestPath(1,0).forEach(node_data -> System.out.print(node_data.getKey()+" "));
    }
}