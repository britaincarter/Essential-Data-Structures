import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;


public class MergeSort {

        /**
         * Internal method that merges two sorted halves of a subarray (from Weiss Data Structures and Algorithm Analysis in Java)
         * @param a an array of Comparable items.
         * @param tmpArray an array to place the merged result.
         * @param leftPos the left-most index of the subarray.
         * @param rightPos the index of the start of the second half.
         * @param rightEnd the right-most index of the subarray.
         */
        private static void merge(Integer[] a, Integer[] tmpArray, int leftPos, int rightPos, int rightEnd) {
            int leftEnd = rightPos - 1;
            int tmpPos = leftPos;
            int numElements = rightEnd - leftPos + 1;

            //System.out.println(Arrays.toString(a));
            
            // Main loop
            while(leftPos <= leftEnd && rightPos <= rightEnd) {
                if( a[leftPos] <= a[rightPos ]) { 
                	//System.out.println("Left Position <= Right Position "+a[leftPos]);
                    tmpArray[tmpPos++] = a[leftPos++];
                    
                } else {
                	//System.out.println("Left Position >= Right Position "+a[rightPos]);
                	tmpArray[tmpPos++] = a[rightPos++];
                }   
            }  

            while( leftPos <= leftEnd ) {   // Copy rest of first half
            	//System.out.println("Left Position <= Left End "+a[leftPos]);
            	tmpArray[tmpPos++] = a[leftPos++];
                
            }

            while( rightPos <= rightEnd ) { // Copy rest of right half
            	//System.out.println("Right Position <= Right End "+a[rightPos]);
            	tmpArray[tmpPos++] = a[rightPos++];
                
            }

            // Copy tmpArray back
            for( int i = 0; i < numElements; i++, rightEnd-- ) {
                a[rightEnd] = tmpArray[rightEnd];
            }
            
            //System.out.println(Arrays.toString(a));
        }

        /**
         * Merge Sort algorithm.
         * This is the Merge Sort algorithm from from Weiss, Data Structures and Algorithm Analysis in Java, 
         * as presented in class. 
         * @param a an array of Comparable items.
         */
        public static void mergeSort(Integer[] a ) {
            Integer[] tmpArray = new Integer[a.length];
            mergeSort(a, tmpArray, 0, a.length - 1 );
        }
        /**
         * Internal method that makes recursive calls. 
         * This is part of the MergeSort algorithm from from Weiss, Data Structures and Algorithm Analysis in Java, 
         * as presented in class. 
         * @param a an array of Comparable items.
         * @param tmpArray an array to place the merged result.
         * @param left the left-most index of the subarray.
         * @param right the right-most index of the subarray.
         */
        private static void mergeSort(Integer[] a, Integer[] tmpArray, int left, int right) {
            if(left < right) {
                int center = (left + right) / 2;
                mergeSort(a, tmpArray, left, center);
                mergeSort( a, tmpArray, center + 1, right);
                merge(a, tmpArray, left, center + 1, right);
            }
        }


       /** 
         * Problem 5: Iterative Bottom-up Merge Sort
         */
        public static void mergeSortB(Integer[] inputArray) {
        	
        	System.out.println(Arrays.toString(inputArray));
        	System.out.println();
        	
        	if(inputArray.length<2){
        		return;
        	}
        	
        	
        	
        	Integer[] tmpArray = new Integer[inputArray.length];
        	
        	//width being the size of the arrays.
    		for (int width = 1; width< inputArray.length; width *= 2){
    			for (int i = 0; i < inputArray.length; i += 2*width) {
    				int left, middle, right, value, k, n;
    				
    				
    				left = i;
    				middle = i+width;
    				right = i+2*width-1;
    					
    				
    				if(middle>inputArray.length-1){
    					middle = inputArray.length-1;
    				}
    				if(right>inputArray.length-1){
    					right = inputArray.length-1;
    				}

    				System.out.println("width: "+width+" left: "+left+" middle: "+middle+" right: "+right);
    				System.out.println(Arrays.toString(inputArray));
    				System.out.println();
    				
    				
    				/*
    				for(k = left; k<middle;k++){
    					if(inputArray[k]>inputArray[k+1]){
    						value = inputArray[k];
    						n = inputArray[k+1];
    						inputArray[k]=n;
    						inputArray[k+1]=value;
    					}
    				}
    				
    				for(int j = middle; j<right;j++){
    					if(inputArray[j]>inputArray[j+1]){
    						value = inputArray[j];
    						n = inputArray[j+1];
    						inputArray[j]=n;
    						inputArray[j+1]=value;
    					}
    				}*/
    				
    				merge(inputArray, tmpArray, left, middle, right);
    				
    		   }
    			
    		}
    		
    		
        }
    
        	
        
        
        /*
         * Method for converting List<Integer> to Integer[]
         * Use in sortList, causes it to require extra space.
         */
        
        private static Integer[] toIntArray(List<Integer> list)  {
            Integer[] array = new Integer[list.size()];
            int i = 0;
            for (Integer x : list)  
                array[i++] = x.intValue();
            return array;
        }
        
        /*
         * Method for converting Integer[] to List<Integer>
         * Use in sortList, causes it to extra space
         */
        
        private static List<Integer> toListIntArray(int[] list){
			List<Integer> intList = new ArrayList<Integer>();
			for(int index = 0; index<list.length;index++){
				intList.add(list[index]);
			}
        	
        	return intList;	
        }

        /** 
         * Problem 6: Merge Sort for Lists, Without Side Effects
         * 
         * My sortList will require O(n+n) space because every time I go through an iteration
         * i copy everything into another temporary array previous to another recursion before
         * dumping into the return list.
         */
        public static List<Integer> sortList(List<Integer> inputList) {
        	
        	if(inputList.size()>1){
        		Integer[] ints = toIntArray(inputList);
        		Integer[] tmpArray = ints;
        		
        		
        		
        		int elementsInLeft=tmpArray.length/2;
        		int elementsInRight = elementsInLeft;
        		
        		//In case its an odd amount of array place in sub-right array
        		if((ints.length%2)==1)
        			elementsInRight+=1;
        		
        		int left[] = new int[elementsInLeft];
        		int right[] = new int[elementsInRight];
        		
        		//Left sub-array
        		for(int i = 0; i<elementsInLeft;i++){
        			left[i]=ints[i];
        		}
        		
        		//Right sub-array
        		for(int i = elementsInLeft;i<elementsInLeft+elementsInRight;i++){
        			right[i-elementsInLeft]=ints[i];
        			
        		}
        		
        		/*
        		 * Copying arrays... O(n+n) space
        		 */
        		
        		List<Integer> tmpArrLeft = new ArrayList<Integer>();
        		List<Integer> tmpArrRight = new ArrayList<Integer>();
        		
        		tmpArrLeft = toListIntArray(left);
        		tmpArrRight = toListIntArray(right);
        		
        		//Recursion occurs here
        		
        		tmpArrLeft = sortList(tmpArrLeft);
        		tmpArrRight = sortList(tmpArrRight);
        		
        		
        		//Merging of lists
        		inputList = mergeLists(tmpArrLeft, tmpArrRight);
        		
        	}
			return inputList;
        }
        

        /**
         * New merge method that merges two lists and returns a new list.
         * Use this method to implement sortList.
         */
        public static List<Integer> mergeLists(List<Integer> left, List<Integer> right) { 
            int rightIndex=0;
            int leftIndex=0;
            
           /*
            * Compare values of right and left array and add elements into a temporary array. 
            * Do this until one of the length of the arrays is completely exahusted of all its 
            * elements. Then add all the remaining elements of left/right into the temporary array.
            */
            
            
            System.out.println("Left Table: "+left.size()+" Right Table: "+right.size());
            boolean inBounds = (rightIndex<=right.size())&&(leftIndex<=left.size());
        	ArrayList<Integer> mergedList = new ArrayList<Integer>(left.size()+right.size());
        	while(inBounds == true){
        		System.out.println("Value of left arrayindex: "+left.get(leftIndex)+" Index: "+leftIndex);
        		System.out.println("Value of right arrayindex: "+right.get(rightIndex)+" Index: "+rightIndex);
        		
            	if(left.get(leftIndex).compareTo(right.get(rightIndex))<0){
            		System.out.println("Added left");
            		System.out.println();
            		mergedList.add(left.get(leftIndex));
            		leftIndex++;
            	}else if(left.get(leftIndex).compareTo(right.get(rightIndex))>0){
            		System.out.println("Added right");
            		System.out.println();
            		mergedList.add(right.get(rightIndex));
            		rightIndex++;
            	}else if(left.get(leftIndex).compareTo(right.get(rightIndex))==0){
            		System.out.println("Added both");
            		System.out.println();
            		mergedList.add(left.get(leftIndex));
            		mergedList.add(right.get(rightIndex));
            		leftIndex++;
            		rightIndex++;
            	}
            	inBounds = (rightIndex<right.size())&&(leftIndex<left.size());
            	System.out.println("inBounds: "+inBounds);
            	//System.out.println(left.get(leftIndex)+" "+right.get(rightIndex));
        	}
        	
        	System.out.println("mergedList before remaining: "+mergedList);
        
        	
        	/*
        	 * One of the arrays is exahested, now just add the remaining array.
        	 */
        	
        	boolean inBounds1 = true;
        	if(right.size()==rightIndex){
        		int i = leftIndex;
        		while(inBounds1==true){
        			mergedList.add(left.get(i));
        			i++;
        			inBounds1 = (i<left.size());
                	System.out.println("Right: "+inBounds1);
        		}
        		
        		
        	}else{//left.size() == leftIndex
        		int i = rightIndex;
        		while(inBounds1==true){
        			mergedList.add(right.get(i));
        			i++;
        			inBounds1 = (i<right.size());
                	
        			System.out.println("Left: "+inBounds1);
        		}
        		//System.out.println("Hey");
        	
        	}
        	//System.out.println("mergedList: "+mergedList);
        	return mergedList;
        }
 
        public static void main(String[] args) {
            // Weiss sort
            Integer[] a = {23, 1,4, 2, 22, 9,-1, 5};
            Integer[] b = {5,10,132,3,19};
           
            List<Integer> c = new ArrayList<Integer>();
            List<Integer> d = new ArrayList<Integer>();
            
            c.add(1); //c[]={1,13,10,3,4}
            c.add(13);
            c.add(10);
            c.add(3);
            c.add(4);
            d.add(2); //d[]={2,3,11,14,20,22}
            d.add(3);
            d.add(11);
            d.add(14);
            d.add(20);
            d.add(22);
            
            
            //c = MergeSort.sortList(c);
            //d = MergeSort.sortList(d);
            //System.out.println("sortList: "+c);
            //System.out.println("sortList: "+d);
            //System.out.println("mergeLists done: "+MergeSort.mergeLists(c, d));
            MergeSort.mergeSortB(a);
            //MergeSort.mergeSort(b);
            //System.out.println("mergeSort done: "+Arrays.toString(b));
            System.out.println("mergeSortB done: "+Arrays.toString(a)); // Should be [0, 1, 2, 4, 7, 9, 18, 19, 131, 245]
        }

}
