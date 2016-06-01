import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class MapReader {
	

  public static Graph readGraph(String vertexfile, String edgefile) {
<<<<<<< HEAD
	  
	try{  
		
	
	  FileReader frvertex = new FileReader(vertexfile);
	  FileReader fredge = new FileReader(edgefile);
		
	  BufferedReader brvertex = new BufferedReader(frvertex);
	  BufferedReader bredge = new BufferedReader(fredge);
	  
	  Graph graph = new Graph();
	  
	  String line;
	  int word1;
	  int word2;
	  
	  //Add vertexes to graph
	  while((line=brvertex.readLine())!=null){
		  String [] words = line.split(",");
			  if(graph.getVertex(words[0])==null){
				  word1 = Integer.parseInt(words[1]);
				  word2 = Integer.parseInt(words[2]);
				  Vertex v = new Vertex(words[0], word1, word2);
			  
				  graph.addVertex(v);
			  }
		  
	  }
	  
	  //Add edges to vertexes
	  
	  while((line=bredge.readLine())!=null){
		  String [] words = line.split(",");
		  if(graph.getVertex(words[0])!=null||graph.getVertex(words[1])!=null){
				graph.addUndirectedEdge(words[0],words[1], 1.0);  
			  }
		  
	  }
	  
	  graph.printAdjacencyList();
	  return graph;
	
	}catch(IOException e){
		System.out.println("No files found...");
	}
	
	return null;
  }	
=======
    return null; // TODO 
  }
>>>>>>> 593aabe696ce3be5c65304e43ccc9c1ebe0b41ac

  public static void main(String[] args) {
      try{
	  
	  MapReader mr = new MapReader();
      
      Scanner in = new Scanner(System.in);
      System.out.println("Enter first the vertexfile than the edgefile locations: (vertex.txt edge.txt) ");
      
      String bothfiles = in.nextLine();
      String[] a = bothfiles.split(" ");
      
      String vertexfile1 = a[0];
      String edgefile1 = a[1];
      
      Graph g = mr.readGraph(vertexfile1, edgefile1);
      DisplayGraph display = new DisplayGraph(g);
      display.setVisible(true);
      
      
	  System.out.println("No operation."); // TODO 
  }catch(ArrayIndexOutOfBoundsException e){
	  System.out.println("Files not found.");
  }

  }
}
