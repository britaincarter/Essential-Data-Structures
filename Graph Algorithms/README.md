

In this program I attempted to implement several graph algorithms and run them on a graph representing 
train connections between North American cities. The graph is visualized by Java's Swing GUI toolkit. The result is similar to Google Maps.
The graph contains Edge objects and Vertex objects in an adjacency lists in Graph. The file `ttrvertices.txt` contains definitions for a set of cities. Each line consists of three comma-separated fields:  the city name, 
the city's horizontal coordinate, and the city's vertical coordinate.
For instance,  the  line  
`NewYork,700,165` defines  the  city NewYork at  position  700:165. 
The  file `ttredges.txt` contains defnitions for train connections. When the program is called using

`$ java  MapReader  ttrvertices.txt  ttredges.txt`,
it should read in a map and store it as a Graph.  It should then display the map, 
using the same mechanism as in the main method for the `Graph` class.

 The algorithms in use are  doBfs(String s)` to Graph, that uses Breadth First Search 
(BFS) to find the unweighted shortest paths (i.e. the paths with the lowest number of 
edges),
doDijkstra(String s)` that uses Dijkstra's 
algorithm to  find  the  weighted shortest  paths  from  the  start  vertex  with 
 the  name *s*, and finally, doPrim(String s)` that uses Prim's algorithm 
to find a *minimum spanning tree* rooted in *s*. 


Example stdin to run:
java  TestPrim  ttrvertices.txt ttredges.txt  KansasCity`



or
java  TestBfs  ttrvertices.txt  ttredges.txt  NewYork  LosAngeles`