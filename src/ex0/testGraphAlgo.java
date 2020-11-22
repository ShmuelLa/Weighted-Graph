package ex0;
import java.util.Collection;
import java.util.List;
/*
 * Created by Moti Dahari and Eyal Levy
 */
public class testGraphAlgo {
    public static void main(String[] args) {

        /*
         * Each test should be run separately
         * */
        //test1();
        test2();
        //test3();
        //test4();
    }
    private static void test1() {
        System.out.println("test 1 ");

        graph graph = new Graph_DS();
        System.out.println("After adding another graph");

        for (int i = 0; i <= 4; i++) {
            node_data node = new NodeData();
            graph.addNode(node);
        }

        for (node_data n : graph.getV()){
            System.out.println("key "+n.getKey()+" Ni --> " + n.getNi());
        }

        System.out.println();
        System.out.println();

        graph.connect(0,1);
        graph.connect(1,4);
        graph.connect(1,0);
        graph.connect(1,4);
        graph.connect(1,3);
        graph.connect(2,1);
        graph.connect(2,3);
        graph.connect(3,1);
        graph.connect(3,4);
        graph.connect(3,2);
        graph.connect(4,0);
        graph.connect(4,1);
        graph.connect(4,3);
        graph.connect(0,1);
        graph.connect(1,4);
        graph.connect(1,0);
        graph.connect(1,4);
        graph.connect(1,3);
        graph.connect(2,1);
        graph.connect(2,3);
        graph.connect(3,1);
        graph.connect(3,4);
        graph.connect(3,2);
        graph.connect(4,0);
        graph.connect(4,1);
        graph.connect(4,3);
        System.out.println("after connent the nodes in the graph");
        for (node_data n : graph.getV()){
            System.out.println("key "+n.getKey()+" Ni --> " + printNi(n));
        }
        graph_algorithms algo = new Graph_Algo();
        System.out.println( "isConnected the answer should be: true = " + algo.isConnected());

        System.out.println();
        System.out.println("The test created by Moti Dahari && Eyal Levy");
    }
    private static void test2() {
        System.out.println("test 2 ");
        graph graph = new Graph_DS();
        graph_algorithms algo;
        //NodeData.countIndex = 0;

        for (int i = 0; i <= 9; i++) {
            node_data newNode = new NodeData();
            graph.addNode(newNode);
        }
        System.out.println("create a graph");
        for (node_data n : graph.getV()){
            System.out.println("key "+n.getKey()+" Ni --> " + n.getNi());
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
        System.out.println("after connent the nodes in the graph");
        for (node_data n : graph.getV()){
            System.out.println("key "+n.getKey()+" Ni --> " + n.getNi());
        }
        algo = new Graph_Algo();
        algo.init(graph);
        String[] s = {
                "[]",
                "[2 , 3 , 4]",
                "[1 , 5 , 6 , 7]",
                "[1 , 4 , 5 , 8]",
                "[1 , 3 , 9]",
                "[2 , 3 , 7 , 8]",
                "[2]",
                "[2 , 5]",
                "[3 , 5]",
                "[4]"
        };
        int index = 0;
        for (node_data n : graph.getV()){
            System.out.println("key "+n.getKey()+"  Ni--> " + printNi2(n.getNi() , s[index++]));
        }
        System.out.println( "Shortest way 4 -> 9 should be 1 =  " + "the answer is : " + algo.shortestPathDist(4,9));
        System.out.println( "Shortest way 1 -> 8 should be 2 =  " + "the answer is : " +algo.shortestPathDist(1,8));
        System.out.println( "Shortest way 9 -> 6 should be 4 =  " + "the answer is : " +algo.shortestPathDist(9,6));
        System.out.println( "Shortest way 1 -> 0 should be -1 =  " + "the answer is : " +algo.shortestPathDist(1,0));
        System.out.println( "Shortest way 0 -> 0 should be 0 =  " + "the answer is : " +algo.shortestPathDist(0,0));
        System.out.println( "Shortest way 4 -> 6 should be 3 =  " + "the answer is : " +algo.shortestPathDist(4,6));
        System.out.println( "Shortest way 4 -> 7 should be 3 =  " + "the answer is : " +algo.shortestPathDist(4,7));
        System.out.println( "Shortest way 8 -> 1 should be 2 =  " + "the answer is : " +algo.shortestPathDist(8,1));
        System.out.println( "Shortest way 1 -> 5 should be 2 =  " + "the answer is : " +algo.shortestPathDist(1,5));
        System.out.println( "Shortest way 1 -> 20 should be -1 =  " + "the answer is : " +algo.shortestPathDist(1,20));
        System.out.println();

        String path = "";
        List<node_data> list;
        int a,b;

        a = 1;
        b = 0;
        list = algo.shortestPath(a,b);
        printListOfPath( list,  a,  b);
        a = 0;
        b = 0;
        list = algo.shortestPath(a,b);
        printListOfPath( list,  a,  b);
        a = 4;
        b = 6;
        list = algo.shortestPath(a,b);
        printListOfPath( list,  a,  b);
        a = 4;
        b = 7;
        list = algo.shortestPath(a,b);
        printListOfPath( list,  a,  b);
        a = 8;
        b = 1;
        list = algo.shortestPath(a,b);
        printListOfPath( list,  a,  b);
        a = 1;
        b = 5;
        list = algo.shortestPath(a,b);
        printListOfPath( list,  a,  b);
        a = 1;
        b = 20;
        list = algo.shortestPath(a,b);
        printListOfPath( list,  a,  b);
        a = 4;
        b = 9;
        list = algo.shortestPath(a,b);
        printListOfPath( list,  a,  b);
        System.out.println("sdvsvsdvsdvsdv");
        for (node_data n : graph.getV()){
            System.out.println("key "+n.getKey()+"  Ni--> " + printNi(n));
        }
        System.out.println( "isConnected the answer should be: false = " + algo.isConnected());

        System.out.println("del key : " + graph.removeNode(0).getKey());
        algo.init(graph);
        System.out.println( "isConnected the answer should be: true = " + algo.isConnected());
        System.out.println();
        System.out.println("The test created by Moti Dahari && Eyal Levy");

    }
    private static void test3() {
        System.out.println("test 3 ");

        graph graph = new Graph_DS();
        for (int i = 0 ; i < 6 ; i++){
            graph.addNode(new NodeData());
        }

        System.out.println( "nodeSize should be 6 = " + graph.nodeSize());

        for (node_data node : graph.getV()) {
            System.out.println("node.getKey() = " + node.getKey()+ "   node.getTag() = " + node.getTag() + "  node.getInfo() = " + node.getInfo() + "  node.getNi() = " + printNi(node));
        }

        graph.connect(0, 1);
        graph.connect(0, 4);
        graph.connect(0, 5);
        graph.connect(1, 0);
        graph.connect(1, 4);
        graph.connect(1, 2);
        graph.connect(1, 3);
        graph.connect(1, 5);
        graph.connect(2, 1);
        graph.connect(2, 3);
        graph.connect(2, 5);
        graph.connect(3, 1);
        graph.connect(3, 4);
        graph.connect(3, 2);
        graph.connect(3, 5);
        graph.connect(4, 3);
        graph.connect(4, 0);
        graph.connect(4, 1);
        graph.connect(4, 5);
        graph.connect(0, 1);
        graph.connect(0, 4);
        graph.connect(0, 5);
        graph.connect(1, 0);
        graph.connect(1, 4);
        graph.connect(0, 1);
        graph.connect(0, 4);
        graph.connect(0, 5);
        graph.connect(1, 0);
        graph.connect(1, 4);
        graph.connect(1, 2);
        graph.connect(1, 3);
        graph.connect(1, 5);
        graph.connect(2, 1);
        graph.connect(2, 3);
        graph.connect(2, 5);
        graph.connect(3, 1);
        graph.connect(3, 4);
        graph.connect(3, 2);
        graph.connect(3, 5);
        graph.connect(4, 3);
        graph.connect(4, 0);
        graph.connect(4, 1);
        graph.connect(4, 5);
        graph.connect(0, 1);
        graph.connect(0, 4);
        graph.connect(0, 5);
        graph.connect(1, 0);
        graph.connect(1, 4);

        System.out.println("after connent the nodes in the graph");
        for (node_data n : graph.getV()){
            System.out.println("key "+n.getKey()+" Ni --> " + printNi(n));
        }
        System.out.println();


        System.out.println();
        System.out.println();

        System.out.println( "0 =  " + (graph.getNode(0)).getKey());
        System.out.println( "1 =  " + (graph.getNode(1)).getKey());
        System.out.println( "2 =  " + (graph.getNode(2)).getKey());
        System.out.println( "3 =  " + (graph.getNode(3)).getKey());
        System.out.println( "4 =  " + (graph.getNode(4)).getKey());
        System.out.println( "5 =  " + (graph.getNode(5)).getKey());
        System.out.println();

        System.out.println("true = " + graph.hasEdge(0 , 1));
        System.out.println("true = " + graph.hasEdge(1 , 0));
        System.out.println("true = " + graph.hasEdge(1 , 5));
        System.out.println();

        System.out.println("n0 --> " + printNi2(graph.getV(0) , "[1 , 4 , 5]"));
        System.out.println("n1 --> " + printNi2(graph.getV(1), "[0 , 2 , 3 , 4 , 5]"));
        System.out.println("n2 --> " + printNi2(graph.getV(2), "[1 , 3 , 5]"));
        System.out.println("n3 --> " + printNi2(graph.getV(3), "[1 , 2 , 4 , 5]"));
        System.out.println("n4 --> " + printNi2(graph.getV(4), "[0 , 1 , 3 , 5]"));
        System.out.println();

        System.out.println("delete node:" + graph.removeNode(5).getKey());
        System.out.println("after deleted key 5");

        System.out.println("n0 --> " + printNi2(graph.getV(0) , "[1 , 4]"));
        System.out.println("n1 --> " + printNi2(graph.getV(1), "[0 , 2 , 3 , 4]"));
        System.out.println("n2 --> " + printNi2(graph.getV(2), "[1 , 3]"));
        System.out.println("n3 --> " + printNi2(graph.getV(3), "[1 , 2 , 4]"));
        System.out.println("n4 --> " + printNi2(graph.getV(4), "[0 , 1 , 3]"));
        System.out.println();

        System.out.println( "nodeSize should be 5 = " + graph.nodeSize());
        System.out.println( "edgeSize should be 7 = " + graph.edgeSize());
        System.out.println();
        System.out.println("The test created by Moti Dahari && Eyal Levy");
    }
    private static void test4() {
        System.out.println("test 4 ");

        int a,b;
        String path = "";
        List<node_data> list;
        graph g = new Graph_DS();
        for (int i = 0; i < 13; i++)
            g.addNode(new NodeData());

        for (node_data n : g.getV()){
            System.out.println("n"+n.getKey()+" --> " + printNi(n));
        }
        System.out.println();

        g.connect(0, 1);
        g.connect(1, 2);
        g.connect(2, 3);
        g.connect(4, 0);
        g.connect(5, 1);
        g.connect(5, 6);
        g.connect(6, 2);
        g.connect(7, 3);
        g.connect(8, 4);
        g.connect(9, 8);
        g.connect(9, 10);
        g.connect(10, 5);
        g.connect(10, 11);
        g.connect(11, 6);
        g.connect(11, 12);
        g.connect(12, 7);
        for (node_data n : g.getV()){
            System.out.println("n"+n.getKey()+" --> " + printNi(n));
        }
        System.out.println();

        graph_algorithms gra = new Graph_Algo();
        gra.init(g);
        //System.out.println(g);
        System.out.println("isConnected: " + gra.isConnected());
        System.out.println("Shortest way 0->12 has: " + gra.shortestPathDist(0, 12) + " nodes");
        System.out.println("Shortest way 10->6 has: " + gra.shortestPathDist(10, 6) + " nodes");
        System.out.println("Shortest way 8->5 has: " + gra.shortestPathDist(8, 5) + " nodes");
        System.out.println("Shortest way 3->9 has: " + gra.shortestPathDist(3, 9) + " nodes");
        System.out.println("Shortest way 11->1 has: " + gra.shortestPathDist(11, 1) + " nodes");
        System.out.println("Shortest way 4->7 has: " + gra.shortestPathDist(4, 7) + " nodes");
        System.out.println("Shortest way 2->10 has: " + gra.shortestPathDist(2, 10) + " nodes");
        System.out.println("Shortest way 12->7 has: " + gra.shortestPathDist(12, 7) + " nodes");
        System.out.println();

        graph gr = gra.copy();
        gra.init(gr);
        gr.removeNode(5);

        gr.removeNode(6);

        System.out.println("after remove 6");
        System.out.println("after remove 5");
        for (node_data n : gr.getV()){
            System.out.println("n"+n.getKey()+" --> " + printNi(n));
        }

        System.out.println("\nisConnected: " + gra.isConnected());
        System.out.println("Shortest way 0->12 has: " + gra.shortestPathDist(0, 12) + " nodes");
        System.out.println("Shortest way 10->6 has: " + gra.shortestPathDist(10, 6) + " nodes");
        System.out.println("Shortest way 8->5 has: " + gra.shortestPathDist(8, 5) + " nodes");
        System.out.println("Shortest way 3->9 has: " + gra.shortestPathDist(3, 9) + " nodes");
        System.out.println("Shortest way 11->1 has: " + gra.shortestPathDist(11, 1) + " nodes");
        System.out.println("Shortest way 4->7 has: " + gra.shortestPathDist(4, 7) + " nodes");
        System.out.println("Shortest way 2->10 has: " + gra.shortestPathDist(2, 10) + " nodes");
        System.out.println("Shortest way 12->7 has: " + gra.shortestPathDist(12, 7) + " nodes");
        System.out.println("Shortest way 8->11 has: " + gra.shortestPathDist(8, 11) + " nodes");
        System.out.println("Shortest way 3->8 has: " + gra.shortestPathDist(3, 8) + " nodes");
        System.out.println();

        a = 0;
        b = 12;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 0->12 : ");
        printListOfPath( list,  a,  b);
        a = 10;
        b = 6;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 10->6 : ");
        printListOfPath( list,  a,  b);
        a = 8;
        b = 5;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 8->5 : ");
        printListOfPath( list,  a,  b);
        a = 3;
        b = 9;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 3->9 : ");
        printListOfPath( list,  a,  b);
        a = 11;
        b = 1;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 11->1 : ");
        printListOfPath( list,  a,  b);
        a = 4;
        b = 7;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 4->7 : ");
        printListOfPath( list,  a,  b);
        a = 2;
        b = 10;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 2->10 : ");
        printListOfPath( list,  a,  b);
        a = 12;
        b = 7;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 12->7 : ");
        printListOfPath( list,  a,  b);
        a = 8;
        b = 11;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 8->11 : ");
        printListOfPath( list,  a,  b);
        a = 3;
        b = 8;
        list = gra.shortestPath(a, b);
        System.out.println("Shortest way 3->8 : ");
        printListOfPath( list,  a,  b);
        System.out.println();
        System.out.println("The test created by Moti Dahari && Eyal Levy");
    }
    public static String printNi(node_data node) {
        String str = "[";
        int a = 0;
        for (node_data n : node.getNi()) {
            if (a == 0) {
                str += n.getKey();
            } else {
                str += " , " + n.getKey();
            }
            a++;
        }
        str += "]";
        return str;
    }
    public static String printNi2(Collection<node_data> node, String result) {

        String str = "[";
        int a = 0;
        for (node_data n : node) {
            if (a == 0) {
                str += n.getKey();
            } else {
                str += " , " + n.getKey();
            }
            a++;
        }
        str += "]";
        String result2 = str + "\n the answer is: " + checkVal(result,str) + "\n";
        return result2;
    }
    public static void printListOfPath(List<node_data> list, int a, int b){
        System.out.println("the path from "+a+" -> "+b+" is : ");
        String path = "";
        if(list.size() != 0){
            for (node_data n : list){
                if(list.indexOf(n) == list.size()-1){
                    path += n.getKey();
                }else{
                    path += n.getKey() + " -> ";
                }
            }
            System.out.println(path);
            path = "";
        }else{
            System.out.println("no path from "+a+" -> "+b );
        }
        System.out.println();


    }
    public static boolean checkVal(String result, String result3){
        if(result.equals(result3))return true;
        return false;
    }
}
