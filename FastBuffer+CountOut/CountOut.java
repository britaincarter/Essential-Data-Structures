import java.util.*;
<<<<<<< HEAD


public class CountOut {

	private int n;
	private int k;
	private int size;
	int element;

	
  public static List<Integer> play(int n, int k) {

    Queue<Integer> loserStreak = new LinkedList<Integer>();
    Queue<Integer> remainingPlayers = new LinkedList<Integer>();

  if(k<=0){
	  loserStreak.add(-1);
	  return (List<Integer>) loserStreak;
  }
    
  if(n!=0){  
    for(int i = 0; i<n; i++){
    	remainingPlayers.add(i);
    }
    
  while(remainingPlayers.size()>0)  
    for(int i = 0; i<k;i++){

		if(remainingPlayers.size()==1){
    		loserStreak.add(remainingPlayers.poll());
    		return (List<Integer>) loserStreak;
    	}
    	
    	
    	if(i==k-1){
    		loserStreak.add(remainingPlayers.poll());
    	}else{
    		remainingPlayers.add(remainingPlayers.poll());
    	}
    }
  }
    
    return (List<Integer>) loserStreak;
=======

public class CountOut {

  public static List<Integer> play(int n, int k) 
  {
    Queue<Integer> remainingPlayers = new LinkedList<>();

    // Your code here.
    List<Integer> outPlayers = new ArrayList<>();
    for(int i = 0; i < n; i++) {
        remainingPlayers.add(i);
    }
    
    while (remainingPlayers.size() > 1) 
    {
        for (int i = 0; i < (k-1); i++) 
        {
            remainingPlayers.add(remainingPlayers.remove());;
        }
        outPlayers.add(remainingPlayers.remove());
    }
    outPlayers.add(remainingPlayers.remove()); // winner is last "out"
    return outPlayers;
>>>>>>> a6acd542ffdd1cc32e39b9bafa47e76888eaf046
  }

  //Big O(n*k) tight bound
  public static Integer findWinner(int n, int k) {
<<<<<<< HEAD
	  List<Integer> loserStreak = new ArrayList<Integer>();
	  Integer winner;
	  
	  loserStreak = play(n, k);
	  
	  winner = loserStreak.get(loserStreak.size()-1);
	  
	  return winner;
=======
    // Your code here.
    return play(n,k).get(n-1);
>>>>>>> a6acd542ffdd1cc32e39b9bafa47e76888eaf046
  }

  /*
   *.The worst case scenario for findWinnerRec is Big O(n) 
   */
  public static Integer findWinnerRec(int n, int k) {
<<<<<<< HEAD
	  if(k<=0){
		  return -1;
	  }
	  
	  if(n==0) 
		  return -1;
	  if(n==1)
		  return n-1;
	  else
		  
		  return (findWinnerRec(n-1,k)+k)%n;
=======
      if (n == 0) {
          return 0;
      }
      return (findWinnerRec(n-1, k) + k) % n;
      
  }
  
  public static void main (String[] args) {
      System.out.println(play(9, 4));
      System.out.println(findWinner(9, 4));
      System.out.println(findWinnerRec(9, 4));
>>>>>>> a6acd542ffdd1cc32e39b9bafa47e76888eaf046
  }
/*
  public static void main(String [] args){
	  int winner = findWinnerRec(10,0);
	  System.out.println(winner);
	  System.out.println(findWinner(10,0));
  }*/
}
