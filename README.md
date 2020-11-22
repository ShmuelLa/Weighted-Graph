![alt text](WikiPictures/redgraph.gif)

# :mortar_board: Weighted Graph Java Implementation

An implementation of a Weighted graph in java.  
This project implements three different interfaces introduced in our assignment:  
  
- **weighted_graph** (implemented by **WGraph_DS**) - an object representing a graph tha points to all of its containing nodes  
    - **node_info** (implemented by **NodeInfo**) - an object representing each individual node/vertex in the graph, in this assignment it will be implemented internally  
- **weighted_graph_algorithms** (implemented by **WGraph_Algo**) - an object that implements some basic graph algorithms  
  
- Our main data structure of choice is a **HashMap** that is used to store every node in the graph and also used to 
implement weighted graph main mechanism, the weighted edges via the **EdgeInfo** object. 
  
- The main reasons we chose HashMap is because of the high efficiency for our project.  
For example the efficient `put()`, `get()` and `contains()` are all O(1) and most importantly the `values()` and `keyset()` methods that  
returns a **Collection** view of all the values/keys contained in this map accordingly. The `values()` and `keyset()` are perfect for the implementation  
of our `getV()` methods which are used in almost every algorithm and iteration in this project.  
  
## Main Classes and Methods  
  
### WGraph_DS
This class implements a mathematical weighted graph by implements two classes internally:
 - **NodeInfo** which implements the basic information and methods each node stores
 - **EdgeInfo** which stores all the data and methods for all the edges in the graph. This internal class 
 is implemented on top of the received interface's for higher efficiency and complexity of the project.
 Each graph consists of two HashMap data structures. One for the node and the other for the edges.
 Each graph also has an integer that count the edges and the mode count (internal changes count) of the graph

| **Method**      |    **Details**        | **Complexity** |
|-----------------|-----------------------|----------------|
| `WGraph_DS()` | Default constructor     |
| `getNode()` | Returns a node by the nodeKey |
| `hasEdge()` | Checks is two nodes are connected | O(1) |
| `getEdge()` | Returns the weight of an edge between two nodes | O(1) |
| `addNode()` | Adds a new node to the graph | O(1) |
| `connect()` | Connects two nodes in the graph | O(1) |
| `getV()` | Returns a collection view of the graph | O(1) |
| `getV(int node_id)` | Returns a collection view of the graph | O(1) |
| `removeNode` | Removed a node from the graph | O(n) |
| `removeEdge()` | Remove an edge between two nodes in the graph | O(1) |
| `nodeSize()` | Returns the number of the nodes in the graph | O(1) |
| `edgeSize()` | Returns the number of the edges in the graph | O(1) |
| `getMC()` | Returns the number of mode counts in the graph, Every change in the internal state of the graph counts as a mode count | O(1) |
| `equals()` | Compares two graphs and cheks if they are equal |
| `toString()` | Creates a String representing the graph, adds each and every connection |

 > NodeInfo and EdgeInfo classes are internal and cannot be accessed directly, 
>used only for developing
>
##### NodeInfo

| **Method**      |    **Details**        |
|-----------------|-----------------------|
| `NodeInfo()` | Constructs a new node with the given key |
| `getKey()` | Returns the nodes key |
| `getInfo()` | Returns the nodes String metadata |
| `setInfo()` | Sets the nodes String metadata |
| `getTag()` | Returns the nodes double tag |
| `setTag()` | Sets the nodes double tag |
| `compareTo()` | Compares two nodes by the tag, chooses lowest |
| `toString()` | creates a String representing the node's detail, used for comparison |
| `equals()` | Compares two nodes and checks if are equal |

##### EdgeInfo

| **Method**      |    **Details**        |
| `EdgeInfo()` | The EdgeInfo constructor |
| `setWeight()` | Sets the weight between two nodes in a single direction |
| `connectE()` | Connects an edge between two nodes in a single direction |
| `hasNi()` | Checks if a selected node has the received neighbor node |
| `getNi()` | Returns a Collection representing the neighbors of a node |
| `getW()` | Returns the weight of an edge between two nodes |
| `removeSrc()` | Clears the data structure containing all the nodes connections |
| `getNiSize()` | Returns the neighbor count of a specific node |
| `removeEd()` | Removes and edge between two nodes in a single direction |
 
### Graph_Algo

| **Method**      |    **Details** |
|-----------------|--------------|
| `init()`         | Initialize the graph |
| `copy()`        | Creates a deep copy of the graph |
| `getGraph()` | Returns a pointer to the initialized graph |
| `isConnected()` | Checks if the graph is connected |
| `shortestPathDist()` | Returns the length of te shortest path between two node, if non existent returns -1 |
| `shortestPath()` | Returns a List<node_data> of the shortest path between two nodes, if non existent returns null |
| `save()` | Saves a graph to a file via Serialization |
| `load()` | Loads a graph from a file via Deserialization |
| `reset()` | Rests the graph's tag and metadata after running an algorithm |

## Tests
### Main Graph Built Used for Testing
[!alt text](WikiPictures/testgraph.jpg)

## :memo: External articles and links used for the planning this project  
  
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
- https://stackoverflow.com/questions/10378855/java-io-invalidclassexception-local-class-incompatible
