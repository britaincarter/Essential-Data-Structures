/**
 * Simulates python's range function
 */

public class Range implements Iterable<Integer> {

	private int max, min, increment;
	
	private class RangeIterator implements  java.util.Iterator<Integer>{
		private int idx = min;
		private int counter = 0;
		
		//Test if there is another element
		public boolean hasNext(){
			if(increment<=0){
				return idx>max;
			}else{
				return idx<max;
			}
		}
		
		
		public Integer next(){
			if(!this.hasNext()){
				throw new java.util.NoSuchElementException();	
			}
			
			//Positive increments
			if(increment>0){
				int nextItem = idx;
				idx+=increment;
				return nextItem;
			
			//Negative increments
			}else{
				
				if(counter==0){
					idx=min;
				}
				int nextItem = idx;
				idx+=increment;
				counter++;
				return nextItem;
			}
			
		}
	}
	
	public Range(int min, int max, int increment) {
		this.min = min;
		this.max = max;
		this.increment=increment;
	}

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
		this.increment = 1;
	}


	public java.util.Iterator<Integer> iterator() {
		return new RangeIterator();
	}
	
}
