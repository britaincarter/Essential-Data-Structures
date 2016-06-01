import java.util.Scanner;

public class TestPrim {

  public static void main(String[] args) throws NullPointerException {
	  try{
	  MapReader mr = new MapReader();
      
      Scanner in = new Scanner(System.in);
      System.out.println("Enter first the vertexfile, than the edgefile, then specify locations: (vertex.txt edge.txt NewYork LosAngeles)");
      
      String bothfiles = in.nextLine();
      String[] a = bothfiles.split(" ");
      
      //String[] a = {"ttrvertices.txt", "ttredges.txt","NewYork"};
      String vertexfile1 = a[0];
      String edgefile1 = a[1];
      String sourceVertex = a[2];
      //String targetVertex = a[3];
      Graph g = mr.readGraph(vertexfile1, edgefile1);      
      
      
      System.out.println("-------------------------");
      
      //g.doPrim(a[2]);
      g.computeAllEuclideanCosts();
      g = g.getMinimumSpanningTree(sourceVertex);
      
      DisplayGraph display = new DisplayGraph(g);
      display.setVisible(true);
      
      
	  }catch(ArrayIndexOutOfBoundsException e){
		  System.out.println("Incorrect input");
	  }
   }

}
