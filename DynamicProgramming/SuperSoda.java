import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SuperSoda {
	
	/*
	 * Run time of O(n*m) m being the sodasizes array and n being the sodas.
	 */
	  
public static double minimalSodaCost(int[] sodaSizes, double[] costs, int n) {
	  
	  double[] temp = new double[n+1];
	  temp[0]=0.0;
	  
	  for(int i = 0; i<sodaSizes.length;i++){
		  temp[sodaSizes[i]]=costs[i];
	  }
	  
	  for(int i = 1; i<n+1; i++){
		  double min = Double.MAX_VALUE;
		  for(int k = 0; k<sodaSizes.length;k++){
			  if(sodaSizes[k]>i){
				  break;
			  }
			  double val=temp[i-sodaSizes[k]] +temp[sodaSizes[k]];
			  
			  if(val<min){
				  min = val;
			  }
		  }
		  temp[i]=min;
	  }
	  
	  for(int i = 0; i<temp.length;i++){
		  System.out.println(temp[i]);
	  }
	  System.out.println("-----------------");
	  
	  return temp[n];
	  
  }
	  
/*
 * Run time of O(n*m+n) or O(n*m) where m is size of sodaSize array and n = cost/sodasizes[0], an 
 * overestimate of what possibly n could be, in case the first case of the parameter cost was not the maximum
 * number of k sodas possible. 
 */
	  

  public static int maximumSodaNumber(int[] sodaSizes, double[] costs, double cost) {
	  int n = (int) ((int) 2*(cost/sodaSizes[0]));
	  int numberOfCans=0;
	  System.out.println(n);
	  
	  double[] temp = new double[n+1];
	  temp[0]=0;
	  
	  for(int i = 0; i<sodaSizes.length;i++){
		  temp[sodaSizes[i]]=costs[i];
	  }
	  
	  for(int i = 1; i<n+1; i++){
		  
		  double min = Double.MAX_VALUE;
		  
		  for(int k = 0; k<sodaSizes.length;k++){
			  
			  //if(temp[i]<cost/*&&i>numberOfCans*/) {
			  
				  if(sodaSizes[k]>i){
			  		  break;
			  	  }
			  	  
				  double val=temp[i-sodaSizes[k]] +temp[sodaSizes[k]];
			  
			  	  if(val<min){
			  		  min = val;
			  		  //numberOfCans=i;
			  	  }
			 //}
		  }	
		  	temp[i]=min;
	  }

	  int max = 0;	  

	  for(int i = 0; i< temp.length;i++){
		  //System.out.println(i+" Cost: "+temp[i]);
		  if(temp[i]<=cost){
			  //System.out.println("new max: "+i);
			  max = i;
		  }
	  }
	  
	  return max;
  }

  public static int[] minimalSodaCostCombinations(int[] sodaSizes, double[] costs, int n) {
	  
	  double[] temp = new double[n+1];
	  int[] packageSize = new int[n+1];
	  temp[0]=0.0;
	  int k;
	  int maxSodas=0;
	  for(int i = 0; i<sodaSizes.length;i++){
		  temp[sodaSizes[i]]=costs[i];
	  }
	  
	  for(int i = 1; i<n+1; i++){
		  double min = Double.MAX_VALUE;
		  for(k = 0; k<sodaSizes.length;k++){
			  if(sodaSizes[k]>i){
				  break;
			  }
			  double val=temp[i-sodaSizes[k]] +temp[sodaSizes[k]];
			  int sodas = sodaSizes[k];
			  
			  if(val<min){
				  min = val;
				  maxSodas = sodas;
			  }
		  }
		  temp[i]=min;
		  packageSize[i]=maxSodas;
		  //System.out.println(temp[i]+" "+sodaSizes[k-1]);
		  
	  }
	  for(int i = 0; i<packageSize.length;i++){
	  	System.out.println(packageSize[i]);
	  }
	  
	  int[] solution= new int[sodaSizes.length];
	  
	  for(int i = 0; i<sodaSizes.length;i++){
		  solution[i]=count(sodaSizes[i],packageSize,packageSize.length);
	  }
	  
	  System.out.println("-----------------");
	  
	  return solution; 
  }
  
  
  private static int count(int number, int array[], int length)
  {
      int counter = 0; 
      for(int i = 0; i < length; i++)
          if(array[i] == number)
              counter++;
      return counter;
  }

  

  public static void main(String[] args) {
    int[] sodaSizes = new int[] { 1, 6, 12, 25, 36 };
    double[] costs = new double[] { 0.8, 4, 7.5, 14, 20 };
    System.out.println(minimalSodaCost(sodaSizes, costs, 100));
    System.out.println(maximumSodaNumber(sodaSizes, costs, 52.8));
    System.out.println(Arrays.toString(minimalSodaCostCombinations(sodaSizes, costs, 36)));
  }
}
