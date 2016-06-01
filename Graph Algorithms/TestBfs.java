import java.util.Scanner;
import java.util.Stack;

public class TestBfs {

  public static void main(String[] args) throws NullPointerException {
	  
	  try{

	  MapReader mr = new MapReader();
      
      Scanner in = new Scanner(System.in);
      System.out.println("Enter first the vertexfile, than the edgefile, then specify locations: (vertex.txt edge.txt NewYork LosAngeles)");
      
      String bothfiles = in.nextLine();
      String[] a = bothfiles.split(" ");
      
      String vertexfile1 = a[0];
      String edgefile1 = a[1];
      String sourceVertex = a[2];
      String targetVertex = a[3];
      Graph g = mr.readGraph(vertexfile1, edgefile1);
      //g.doBfs(a[2]);
      g = g.getUnweightedShortestPath(sourceVertex,targetVertex);
      
      
      
      System.out.println("-------------------------");
      
//      g.printAdjacencyList();
//
//      DisplayGraph display = new DisplayGraph(g);
//      display.setVisible(true);
      DisplayGraph display = new DisplayGraph(g);
      display.setVisible(true);
      
      
      
      //should be 5 or [dallas, el paso, los angeles, las vegas, salt lake city]
	  }catch(ArrayIndexOutOfBoundsException e){
		  System.out.println("Incorrect input");
	  }
	 }

}
