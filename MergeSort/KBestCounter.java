import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.stream.Stream;

public class KBestCounter<T extends Comparable<T>> {
   
    private PriorityQueue<T> heap;
    private int amount;
    //private int numOfIncreases=1;
    

    public KBestCounter(int k) {
        heap = new PriorityQueue<T>(k);
        amount = k;
    }

    public void count(T x) {
    	
    	
    	/*
    	System.out.println(heap.peek());
        if(heap.peek()==null){
        	return;
        }
    	
        T minElement = heap.peek();
    	*/
    	
    	
    	//System.out.println("Counter: "+x);
    	
    	//System.out.println("Size of Heap: "+heap.size()+" Amount: "+amount);
    	/*
    	if(heap.size()>amount*numOfIncreases){
    		System.out.println("Old Heap Size: "+heap.size());
    		PriorityQueue<T> old = heap;
    		heap = new PriorityQueue<T>(old.size()*2+1);
    		for(int i = 0; i<old.size();i++){
    			System.out.println(old.peek());
    			heap.offer(old.remove());
    		}
    		System.out.println("New Heap Size: "+heap.size());
    		numOfIncreases++;
    	}
    	*/
    	
    	if(heap.size()<amount){
        	heap.offer(x);
        }else if(heap.peek().compareTo(x)<0){
        	heap.remove();
        	heap.offer(x);
        }
    	return;
    }
    
    /*
    private void ensureCapacity(int size){
    	if(size>heap.size()-1){
    		PriorityQueue<T> old = heap;
    		heap = (T[]) new Comparable[old.size()*2+1];
    		for(int i =0; i<old.size();i++){
    			old[i];
    		}
    	}
    }
*/
    
    
    public List<T> kbest() {
    	@SuppressWarnings("unchecked")
        T[] sorted = (T[]) new Comparable[amount];
    	
        List<T> listSort = new LinkedList<T>();
        for(int i =0; i<amount;i++){
        	//System.out.println(heap.peek());
        	//System.out.println(i);
        	sorted[(amount-1)-i]=heap.remove();
        }
        
        for(int k = 0; k<sorted.length;k++){
        	T element = sorted[k];
        	System.out.println("Element: "+element);
        	listSort.add(element);
        }
        
        for(T element:listSort){
        	heap.offer(element);
        }
        return listSort; 
    } 
    
    public static void main(String args[]){
    	int k = 10;
    	KBestCounter <Integer> counter = new KBestCounter<>( k );
    	
    	List<Integer> list = new LinkedList<>();
    	
    	for(int i = 300; i>0; i--){
    		list.add(0, i);
    	}
    	
    	Iterator<Integer> stream = list.iterator();
    	
    	for ( int i = 0; i < 250; i ++){ 
    		int m = stream.next();
    		//System.out.println(m);
    		counter.count(m);

    	}
    	
    	// print k largest after 200 elements
    	List<Integer> kbest = counter.kbest();
    	System.out.println("Kbest to Array: "+Arrays.toString(kbest.toArray()));

    	for ( int i = 0; i < 10; i ++) 
    	  counter.count(stream.next());

    	// print k largest after 110 elements
    	List<Integer> kbest1 = counter.kbest();
    	System.out.println("kbest1 to array: "+Arrays.toString(kbest1.toArray()));
    }
}
