
/*
 * Wrapper class for ability to compare vertexes costs in priority queue in 
 * Prim/Dijkstra algorthm.
 */

public class Pair implements Comparable<Pair> {
  public Vertex vertex;
  public double cost;

  public Pair(Vertex vertex, double cost) {
    this.vertex = vertex;
    this.cost = cost;
  }
  public int compareTo(Pair other) {
      if (this.cost > other.cost) return 1;
	  if (this.cost < other.cost) return -1;
	return 0;
  }

public String getName() {
	return vertex.name;
  }
}
