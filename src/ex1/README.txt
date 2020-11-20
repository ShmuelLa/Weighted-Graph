![alt text](WikiPictures/redgraph.gif)

# Undirected & Unweighted Graph Java Implementation
  
An implementation of an undirected unweighted graph in java.  
This project implements three different interfaces introduced in our assignment:  
  
- **node_data** (implemented by **NodeData**) - an object representing each individual node/vertex in the graph  
- **graph** (implemented by **Graph_DS**) - an object representing a graph tha points to all of its containing nodes  
- **graph_algorithms** (implemented by **Graph_Algo**) - an object that implements some basic graph algorithms  
  
- Our main data structure of choice is a **HashMap** that is used to store every nodes neighbors in the **NodeData** object  
and every graph's nodes in **Graph_DS** object.  
  
- The main reasons we chose HashMap is because the efficiency for our projects needed methods.  
For example the efficient `put()`, `get()` and `contains()` are all O(1) and most importantly the values() method that  
returns a **Collection** view of all the values contained in this map. The `values()` is perfect for the implementation  
of our `getV()` method which returns the nodes or neighbors of a selected node in a graph.  
The `getV()` method is used almost in every algorithm and iteration in this project.  
  
## Main Classes and Methods  
### NodeData  
> By definition this class should not be accessible directly and is implemented this way only in the first project. So we will explain the needed methods in the Graph_DS class. 
  
### Graph_DS
- `Graph_DS()` - Default constructor
- `getNode()` - Returns a node by the nodeKey
- `hasEdge()` - Checks is two nodes are connected
  > O(1) Complexity
- `addNode()` - Adds a new node to the graph
  > O(1) Complexity
- `connect()` - Connects two nodes in the graph
  > O(1) Complexity
- `getV()` - Returns a collection view of the graph
  > if given a specific nodeKey this method will return a collection view of it's connected neighbors
  > O(1) Complexity by using values() method of HashMap
- `removeNode` - Removed a node from the graph
  > O(n) Complexity
- `removeEdge()` - Remove an edge between two nodes in the graph
  > O(1) Complexity
- `nodeSize()` - Returns the number of the nodes in the graph
  > O(1) Complexity
- `edgeSize()` - Returns the number of the edges in the graph
  > O(1) Complexity
- `getMC()` - Returns the number of mode counts in the graph
  > Every change in the internal state of the graph counts as a mode count
  > O(1) Complexity

### Graph_Algo
- `init` - Initialize the graph
- `copy()` - Creates a deep copy of the graph
- `isConnected()` - Checks if the graph is connected
- `shortestPathDist()` - Returns the length of te shortest path between two nodes
  > if non existent returns -1
- `shortestPath()` - Returns a List<node_data> of the shortest path between two nodes
  > if non existent returns null

## External articles and links used for the planning this project  
  
### HashMap intel and efficiency:
- https://javatutorial.net/java-iterate-hashmap-example
- https://stackoverflow.com/questions/1757363/java-hashmap-performance-optimization-alternative
- https://dzone.com/articles/how-to-use-java-hashmap-effectively
- https://dzone.com/articles/hashmap-performance
- https://stackoverflow.com/questions/55263115/detail-the-big-o-of-hashmap-put-method-by-real-code-in-java-8
  
### HashSet intel and efficiency:
- https://stackoverflow.com/questions/3267572/fastest-data-structure-for-contains-in-java
- https://www.baeldung.com/java-hashset-arraylist-contains-performance
  
### Dijkstra's Algorithm
- https://www.coursera.org/lecture/advanced-data-structures/core-dijkstras-algorithm-2ctyF
- https://en.wikipedia.org/wiki/Shortest_path_problem

### PriorityQueue and Comparable Usage
- https://www.youtube.com/watch?v=c4ES6jGxqEw

### Serialization
- https://www.youtube.com/watch?v=6B6vp0jZnb0
