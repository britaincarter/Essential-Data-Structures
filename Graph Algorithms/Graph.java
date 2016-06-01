import java.awt.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {

  // Keep a fast index to nodes in the map
  protected Map<String, Vertex> vertices;
  private static final int INFINITY = Integer.MAX_VALUE;

  /**
   * Construct an empty Graph.
   */
  public Graph() {
    vertices = new HashMap<String, Vertex>();
  }

  public void addVertex(String name) {
    Vertex v = new Vertex(name);
    addVertex(v);
  }

  public void addVertex(Vertex v) {
    if (vertices.containsKey(v.name))
      throw new IllegalArgumentException(
          "Cannot create new vertex with existing name.");
    vertices.put(v.name, v);
  }

  public Collection<Vertex> getVertices() {
    return vertices.values();
  }

  public Vertex getVertex(String s) {
    return vertices.get(s);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          the source vertex.
   * @param w
   *          the target vertex.
   */
  public void addEdge(String nameU, String nameV, Double cost) {
    if (!vertices.containsKey(nameU))
      addVertex(nameU);
    if (!vertices.containsKey(nameV))
      addVertex(nameV);
    Vertex sourceVertex = vertices.get(nameU);
    Vertex targetVertex = vertices.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          unique name of the first vertex.
   * @param w
   *          unique name of the second vertex.
   */
  public void addEdge(String nameU, String nameV) {
    addEdge(nameU, nameV, 1.0);
  }


  /****************************
   * Your code follow here.   *
   ****************************/ 

  public void addUndirectedEdge(String s, String t, double cost) {
      if(!vertices.containsKey(s))
    	  addVertex(s);
      if(!vertices.containsKey(t))
    	  addVertex(t);
      
      Vertex sourceVertex = vertices.get(s);
      Vertex targetVertex = vertices.get(t);
      
      Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
      Edge reverseNewEdge = new Edge(targetVertex, sourceVertex, cost);
      
      sourceVertex.addEdge(newEdge);
      targetVertex.addEdge(reverseNewEdge);
	  
	  return; 
  }

  public double computeEuclideanCost(double ux, double uy, double vx, double vy) {
	  double value;
      
      value = Math.sqrt(Math.pow((ux-vx),2)+Math.pow((uy-vy),2));
      //System.out.println(value);
	  
      return value;
  }

  public void computeAllEuclideanCosts() {
	  System.out.println("Compute All Euclidean Costs");
	  
	  Object[] set = vertices.keySet().toArray();
	  
	  
	  for(int i =0; i<set.length;i++){
		  Vertex v = vertices.get(set[i]);
		  if(v==null){
			  continue;
		  }
		  
		  for(int k = 0; k<v.getEdges().size();k++){
			  Vertex w = v.getEdges().get(k).targetVertex;
			  Edge e = v.getEdges().get(k);
			  
			  if(v.equals(w)){
				  //System.out.println("Not sourceVertex");
				  continue;
			  }
			  //System.out.println(v.name+" "+v.posX+":"+v.posY+" "+w.name+" "+w.posX+":"+w.posY+" ");
			  double tmpcost = computeEuclideanCost(v.posX,v.posY,w.posX,w.posY);
			  //System.out.println("Cost: "+tmpcost);
			  e.cost = tmpcost;
			  //System.out.println(e.cost);
		  }
	  }
	  
	  System.out.println("-------------------------");
	  return; 
  }

  /** BFS */
  public void doBfs(String s) {
	  
	  System.out.println("doBfs...");
	  Queue<Vertex> q = new LinkedList<Vertex>();
	  
	  Vertex v = vertices.get(s);
	  //System.out.println(v.name);
	  
	  q.add(v);
	  
	  v.visited=true;
	 
	  while(!q.isEmpty()){
		  Vertex z = q.remove();
		  //System.out.print(z.name +" --> ");
		  Vertex child = null;
		  //System.out.print(" [");
		  for(int i = 0; i<z.getEdges().size(); i++){
			  child=z.getEdges().get(i).targetVertex;
			  if(child.visited==false){
			  		child.visited=true;
			  		child.backpointer=z;
			  		child.cost = z.cost+1;
			  		//System.out.print(" "+child.name+"("+child.cost+")"+" ");
			  		q.add(child);
			  }
		  }
		  //System.out.println("]");
	  }
	  
	  return;
  }
  
  public Graph getUnweightedShortestPath(String s, String t) {
	  Vertex T = vertices.get(t);
	  Vertex S = vertices.get(s);
	  //System.out.println(T.name);
	  doBfs(s);
	  
	  
	  //System.out.println(T.name);
	  Stack<Vertex> path = new Stack<Vertex>();
	 
	  while(T!=S){
		  //System.out.println(T.name);
		  path.add(T);
		  T = T.backpointer;
		  
	  }
	  path.add(S);
	  
	  
	  Graph g = new Graph();
	  double value;
	  Vertex v = path.pop();
	  v = new Vertex(v.name, v.posX, v.posY);
	  g.addVertex(v);
	  while(!path.empty()){
		  Vertex w = path.pop();
		  w = new Vertex(w.name, w.posX, w.posY);
		 
		  g.addVertex(w);
		  value = g.computeEuclideanCost(v.posX, v.posY, w.posX, w.posY); 
		  g.addUndirectedEdge(v.name, w.name, value);
		 
		  
		  v = w;	
		  
	  }
	  
	  
	  for(Vertex x: vertices.values()){
    	  if(g.vertices.containsKey(x.name)){
    		  System.out.println(x.name+": ");
    		  for(int i = 0; i<x.getEdges().size();i++){
    			  Vertex z = x.getEdges().get(i).targetVertex;
    			  
    			  //System.out.print(" "+z.name+" ");
    			  
    			  if(!g.vertices.containsKey(z.name)){
    				  z = new Vertex(z.name, z.posX, z.posY);
    				  g.addVertex(z);
    			  }
    		  }
    		  System.out.println();
    		
    	  }
      }
	  
	  return g;
  }
  
  
  /** Dijkstra's */
  /*
   * From Portland to ElPaso you get two different paths for Bfs and Dijkstra. 
   * With Bfs you hit only 4 nodes but its a longer distance then the 5 nodes Dijkstra hits
   * to get to ElPaso.
   * 
   * Bfs: [Portland-->SanFrancisco-->LosAngeles-->ElPaso]
   * Dijkstra: [Portland-->SaltLakeCity-->Denver-->SanteFe-->ElPaso]
   */
  public void doDijkstra(String s) {
	  
	  System.out.println("-------------------");
	  System.out.println("doDijkstra...");
	  PriorityQueue<Pair> q = new PriorityQueue<Pair>();
	  //Queue<Vertex> q = new LinkedList<Vertex>();
	  
	  Object[] set = vertices.keySet().toArray();  
	  
	  for(int i = 0; i<vertices.size();i++){
		  Vertex v = vertices.get(set[i]);
		  v.visited = false;
		  v.cost = INFINITY;
		  Pair p = new Pair(v, v.cost);
		  //System.out.println(p.vertex.name);
		  q.add(p);  
	  }
	  
	  Vertex S = vertices.get(s);
	  //System.out.println(S.name);
	  S.cost = 0;
	  S.visited = true;
	  Pair p = new Pair(S, S.cost);
	  
	  
	  q.add(p);
	  
	  while(!q.isEmpty()){
		  Pair min = q.remove();
		  Vertex u = min.vertex;
		  u.visited=true;
		  //System.out.print(min.vertex.name +" --> ");
		  //System.out.print(" [");
		  for(Edge e : u.getEdges()){
			  //System.out.print(neighbor.cost+" "+adjacentDistance);
			  Vertex v = e.targetVertex;
			  
			  if(!v.visited){
				  //System.out.print(" false ");
				  double adjacentDistance = u.cost+e.cost;
				  
				  if(v.cost>adjacentDistance){
					  v.cost=adjacentDistance;
					  v.backpointer = u;
					  //System.out.print(" "+v.name+"("+v.cost+")"+" ");
					  Pair neighbor = new Pair(v, v.cost);
					  q.add(neighbor);
				  }
			  }
		  }
		  //System.out.println("]");
	  }
	  
	  
	  return;
  
  }

  public Graph getWeightedShortestPath(String s, String t) {
    
	  Vertex S = vertices.get(s);
	  Vertex T = vertices.get(t);
	  
	  doDijkstra(s);
	  
	  Stack<Vertex> path = new Stack<Vertex>();
	  
	 
	  while(T!=S){
		  //System.out.println(T.name);
		  path.add(T);
		  T = T.backpointer;
		  
	  }
	  path.add(S);
	  
	  Graph g = new Graph();
	  
	  Vertex v = path.pop();
	  v = new Vertex(v.name, v.posX, v.posY);
	  g.addVertex(v);
	  while(!path.empty()){
		  Vertex w = path.pop();
		  w = new Vertex(w.name, w.posX, w.posY);
		  g.addVertex(w);
		  g.addUndirectedEdge(v.name, w.name, w.cost);//Figure out cost
		  //System.out.println(v.name + " to " + w.name);
		  v = w;		  
	  }
	  
      
	  for(Vertex x: vertices.values()){
    	  if(g.vertices.containsKey(x.name)){
    		  //System.out.println(x.name+": ");
    		  for(int i = 0; i<x.getEdges().size();i++){
    			  Vertex z = x.getEdges().get(i).targetVertex;
    			  
    			  //System.out.print(" "+z.name+" ");
    			  
    			  if(!g.vertices.containsKey(z.name)){
    				  z = new Vertex(z.name, z.posX, z.posY);
    				  g.addVertex(z);
    			  }
    		  }
    		  //System.out.println();
    		
    	  }
      }
      
      
	  return g;
  }

  
  private class AgendaItem implements Comparable<AgendaItem> {
	    double cost;
	    Vertex vertex;
	    public AgendaItem(double cost, Vertex vertex) {
	      this.cost = cost;
	      this.vertex = vertex;
	    }
	    public int compareTo(AgendaItem o) {
	      if (o.cost > this.cost)
	        return -1;
	      if (o.cost < this.cost)
	        return 1;
	      return 0;
	    }
	  }
  
  
  /** Prim's */
  public void doPrim(String s) {

	    PriorityQueue<AgendaItem> queue = new PriorityQueue<>();
	    for (Vertex u : getVertices()) {
	      u.visited = false;
	      u.cost = Double.POSITIVE_INFINITY;
	      u.backpointer = null;
	    }


	    Vertex v;
	    Vertex u = vertices.get(s);
	    u.visited = true;
	    u.cost = 0.0;
	    queue.offer(new AgendaItem(0.0, u));

	    AgendaItem next;
	    while (!queue.isEmpty()) {
	      next = queue.poll();
	      System.out.println(next.cost);
	      u = next.vertex;
	      u.visited = true;
	      for (Edge uv : u.getEdges()) {
	        v = uv.targetVertex;
	        if (!v.visited && (uv.cost < v.cost)) {
	          v.backpointer = u;
	          v.cost = uv.cost;
	          queue.offer(new AgendaItem(v.cost, v));
  
	        }
	      }
	    }
  }
  
  public Graph getMinimumSpanningTree(String s) { 
	    doPrim(s);
	  
	    Graph result = new Graph();
	    for (Vertex v : getVertices()) result.addVertex(new Vertex(v.name, v.posX, v.posY));
	    for (Vertex v: getVertices()) {
	      if (v.backpointer!=null)
	        result.addEdge(v.name, v.backpointer.name);
	    }
	    return result;
  }

  /*************************/

  public void printAdjacencyList() {
    for (String u : vertices.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertices.get(u).getEdges()) {
        sb.append(e.targetVertex.name);
        sb.append("(");
        sb.append(e.cost);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    g.addVertex(new Vertex("v0", 0, 0));
    g.addVertex(new Vertex("v1", 0, 1));
    g.addVertex(new Vertex("v2", 1, 0));
    
    g.addVertex(new Vertex("v3", 1, 1));
    g.addVertex(new Vertex("v4", 1, 1));
    
    g.addEdge("v0", "v1");
    g.addEdge("v1", "v2");
    g.addEdge("v2", "v3");
    g.addEdge("v3", "v0");
    g.addEdge("v0", "v2");
    g.addEdge("v1", "v3");
    g.addUndirectedEdge("v4", "v0", 1);    
    

    g.printAdjacencyList();

    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);
  }

}
