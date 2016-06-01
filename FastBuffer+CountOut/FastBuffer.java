<<<<<<< HEAD
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;

public class FastBuffer implements Buffer{
	
	//Create a cursor between the right and left buffer
	ArrayDeque<Character> rightBuffer = new ArrayDeque<Character>();
	ArrayDeque<Character> leftBuffer = new ArrayDeque<Character>();
	
	int size;
	char element;
	
	//Size is both cursors
	public int size(){
		size = rightBuffer.size()+leftBuffer.size();
		return size;
	}
	
	//load the character array 
	public void load(char[] initial, int cursorPosition){
		
		if(initial.length==0){
			return;
			
		}
		
		//push everything to the left of cursor to the left buffer
		for(int i = 0; i<cursorPosition; i++){
			leftBuffer.addFirst(initial[i]);
		}
		
		//push everything to the right of cursor to the right buffer
		for(int i = initial.length-1; i>=cursorPosition; i--){
			//System.out.println(i);
			rightBuffer.addFirst(initial[i]);
		}	
		
	}
	
	public char[] toArray(){
		int lbuffer = leftBuffer.size();
		int size = size();
		//Designate a array with size of both left/right buffer
		char [] newArray = new char[size()];
		//System.out.println("Size: "+size);
		if(!leftBuffer.isEmpty()){
			
			for(int i=lbuffer;i>0; i--){
				newArray[i-1]=leftBuffer.removeFirst();
			}
		}
		
		if(!rightBuffer.isEmpty()){
			
			for(int i = lbuffer; i<size; i++){
				newArray[i]=rightBuffer.removeFirst();
			}
		}
		/*
		//Load all elements of leftbuffer into array
		for(int i = 0; i<leftBuffer.size();i++){
			if(i>newArray.length){
				newArray = Arrays.copyOf(newArray, newArray.length*2);
				
			}
			
			
			if(leftBuffer.peek()!=null){
				//System.out.println(i);
				element = leftBuffer.poll();
				newArray[i]=element;
			}else{
				break;
			}
			
		}
		
		//Load all elements of rightBuffer into array
		for(int i = rightBuffer.size(); i<size();i++){
			if(i>newArray.length){
				newArray = Arrays.copyOf(newArray, newArray.length*2);
				
			}
			
			
			if(rightBuffer.peek()!=null){
				//System.out.println(i);
				element = rightBuffer.poll();
				newArray[i]=element;
			}else{
				break;
			}
		}*/
		//System.out.println(newArray);
		return newArray;
	}
	
	public int getCursor(){
		return leftBuffer.size();
	}
	
	public void setCursor(int j){
		int lbuffersize = leftBuffer.size();
		
		
		if(j>size()||j<0){
			return;
		}
		
		//If index is greater than size of leftBuffer load
		//as much of the rightbuffer onto the leftbuffer until hit index j.
		if(j<getCursor()){	
			for(int i = j; i<lbuffersize; i++){
				rightBuffer.addFirst(leftBuffer.remove());
			}
		}
		
		//If index is less than or equal to leftBuffer only load as 
		//as far as the index and push others onto the rightbuffer.
		if(j>getCursor()){
			for(int i = 0; i<j-lbuffersize; i++){
				leftBuffer.addFirst(rightBuffer.remove());
			}
		}
		
		/*
		for(int i = 0; i<=j;i++){
			if(j==i){
				for(int k = 0; k<sideBuffer.size();k++){
					element = rightBuffer.pop();
					leftbuffer.push(element);
				}
			}else{
				element = rightBuffer.pop();
				leftBuffer.push(element);
			}
		}*/
	}
	
	public void moveLeft(){
		if(leftBuffer.peek()!=null){
			rightBuffer.addFirst(leftBuffer.removeFirst());
		}else{
			return;
		}
		
	}
	
	public void moveRight(){
		if(rightBuffer.peek()!=null){
			leftBuffer.addFirst(rightBuffer.removeFirst());
		}else{
			return;
		}
	}
	
	public char deleteRight(){
		if(rightBuffer.peek()!=null){
			element = rightBuffer.removeFirst();
			return element;
		}else{
			return ' ';
		}
	}
	
	public char deleteLeft(){
		if(leftBuffer.peek()!=null){
			element = leftBuffer.removeFirst();
			return element;
		}else{
			return ' ';
		}
	}
	
	public void insertLeft(char c){
		leftBuffer.addFirst(c);
	}
	
	/*
	public static void main(String[] args){
		char[] take;
		char[] take2 = {'T','h','i','s'};
		
		FastBuffer x = new FastBuffer();
		x.load(take2, 0);
		System.out.println(x.toArray());
		for(int i = 0; i<3; i++){
			x.insertLeft('a');
			
		}
		x.setCursor(0);
		take = x.toArray();
		System.out.println(take);
		
		x.insertLeft('c');
		x.insertLeft('d');
		x.insertLeft('e');
		
		take = x.toArray();
		System.out.println(take);
		for(int i = 0; i<3; i++){
			x.insertLeft('b');
		}
		
		x.setCursor(0);
		//System.out.println(x);
		take = x.toArray();
		//System.out.println(x);
		//System.out.println("take length: "+take.length);
		System.out.println(take);
		//x.load(take, 0);
		//System.out.println(take);
		//x.setCursor(7);
		//System.out.println(take);
		//x.moveLeft();
		x.deleteLeft();
		take = x.toArray();
		//System.out.println(take);
		
		*/
		
		/*
		x.moveLeft();
		x.moveLeft();
		x.moveLeft();
		x.moveLeft();
		x.deleteLeft();
		x.moveRight();
		x.moveRight();
		x.moveRight();
		x.moveRight();
		x.moveRight();
		x.deleteRight();
		take2 = x.toArray();
		System.out.println(take2);
		*/
		/*
		ArrayDeque<Character> sb = new ArrayDeque<Character>();
		sb.addFirst('e');
		sb.addFirst('f');
		sb.addFirst('e');
		sb.addFirst('j');
		sb.addFirst('z');
		sb.deleteLeft();
		System.out.println(sb);
		sb.toArray();
		System.out.println();
		//System.out.println(sb.setCursor(2));
		//sb.insertleft('v');
		
		load(sb, 1);
		//System.out.println(sb);
		sb.toArray();
		//System.out.println(sb);
		//sb.setCursor(1);
		//System.out.println(sb);
		*/
		
		/*
		char[] test = {'a','b','c','d','e'};
		//load(test,2);
		//load(test,2);
		//setCursor(0);
		//test.insertLeft('l')
		
	 	char b = 'b';
		for(int i = 0; i<6; i++){
			//sb.insertLeft(b);
		}
		
	//	System.out.println(sb.toArray());
	*///}
=======
// This class should contain your implementation of the Buffer interface.

import java.util.Deque;
import java.util.LinkedList;

public class FastBuffer implements Buffer {

  private Deque<Character> left;
  private Deque<Character> right;

  public FastBuffer() {
    init();
  }

  private void init() {
    left = new LinkedList<Character>();
    right = new LinkedList<Character>();
  }

  @Override
  public int size() {
    return left.size() + right.size();
  }

  @Override
  public void load(char[] initial, int cursorPosition) {
    init();
    for (char c : initial) {
      left.addFirst(c);
    }
    setCursor(cursorPosition);
  }

  @Override
  public char[] toArray() {
    char[] array = new char[size()];
    int i = 0;
    for (Character c : left) {
      array[left.size() - 1 - i] = c;
      i++;
    }
    for (Character c : right) {
      array[i] = c;
      i++;
    }
    return array;
  }

  @Override
  public int getCursor() {
    return left.size();
  }

  @Override
  public void setCursor(int j) {
    while (getCursor() != j) {
      if (j < getCursor()) {
        moveLeft();
      }
      if (j > getCursor()) {
        moveRight();
      }
    }
  }

  @Override
  public void moveRight() {
    left.addFirst(right.removeFirst());
  }

  @Override
  public void moveLeft() {
    right.addFirst(left.removeFirst());
  }

  @Override
  public void insertLeft(char c) {
    left.addFirst(c);
  }

  @Override
  public char deleteRight() {
    return right.removeFirst();
  }

  @Override
  public char deleteLeft() {
    return left.removeFirst();
  }

    
    public static void main(String[] args) {
        
        FastBuffer myBuff = new FastBuffer();
        //System.out.println("Cursor at "+myBuff.getCursor());
        myBuff.insertLeft('1');
        //System.out.println("Cursor at "+myBuff.getCursor());
        myBuff.insertLeft('2');
        myBuff.insertLeft('3');
        myBuff.insertLeft('4');
   
        myBuff.setCursor(3);
        myBuff.insertLeft('5');
        myBuff.insertLeft('6');
        myBuff.insertLeft('7');
  
        
        char[] theArray = myBuff.toArray();

        
        for (int i = 0; i < theArray.length; i++) 
        {
            System.out.print(theArray[i]);
        }
        System.out.println();
        
        //Add "*$" at position 6
        myBuff.setCursor(6);
        myBuff.insertLeft('*');
        myBuff.insertLeft('$');
      
        // Print again: 
        theArray = myBuff.toArray();
        for (int i = 0; i < theArray.length; i++) 
        {
            System.out.print(theArray[i]);
        } 
        System.out.println();
        
        // delete the junk characters
        System.out.println("Deleted: " + myBuff.deleteLeft());
        System.out.println("Deleted: " + myBuff.deleteLeft());
        //System.out.println("Deleted: " + myBuff.deleteRight());
        
        theArray = myBuff.toArray();
        for (int i = 0; i < theArray.length; i++) {
            System.out.print(theArray[i]);
        }
        System.out.println();
        
    }
    
>>>>>>> a6acd542ffdd1cc32e39b9bafa47e76888eaf046
}