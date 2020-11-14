public class playground {

    public static void main(String[] args) {
        WGraph_DS g1 = new WGraph_DS();
        for (int i=0; i <=24; i++) {
            g1.addNode(i);
        }
        g1.connect(2,3,40);
        System.out.println("edges  " + g1.edgeSize());
        System.out.println("mode count  " + g1.getMC());
        System.out.println("nodes  " + g1.nodeSize());
        System.out.println(g1.getEdge(2,3));
        g1.removeEdge(2,3);
        g1.connect(2,3,45);
        g1.connect(2,3,89);
        System.out.println(" ");
        System.out.println("edges  " + g1.edgeSize());
        System.out.println("mode count  " + g1.getMC());
        System.out.println("nodes  " + g1.nodeSize());
        System.out.println(g1.getEdge(2,3));
    }
}
