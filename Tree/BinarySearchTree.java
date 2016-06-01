import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {

  protected BinaryNode<T> root;
  protected T min;
  protected T max;
  
  
  private class BinaryNode<U> {

	    public U data; // The data in the node
	    public BinaryNode<U> left; // Left child
	    public BinaryNode<U> right; // Right child

	    public BinaryNode(U data) {
	      this(data, null, null);
	    }

	    public BinaryNode(U data, BinaryNode<U> left, BinaryNode<U> right) {
	      this.data = data;
	      this.left = left;
	      this.right = right;
	    }
 }
  
  
  public BinarySearchTree() {
    root = null;
  }

  // problem 3a
  public boolean isBst() {
	 
	  
	  if(root==null){
		  return true;
	  }else{
		  
		  return isBinary(root);
	  }

  }
  
//recursive method to see if it is binary tree
  private boolean isBinary(BinaryNode<T> root){
	  if(root==null)
		  return true;
	  else if(root.left==null&&root.right==null)
		  return true;
	  else if(root.left==null){
		  if(root.right.data.compareTo(root.data)>0){
			  return isBinary(root.right);
		  }else if(root.left.data.compareTo(root.data)<0){
			  return isBinary(root.left);
		  }
	}else{
		return isBinary(root.left)&&isBinary(root.right);
		  }
	return true;
  }
  
  
//problem 3b
 List<T> lst = new ArrayList<T>();
 public List<T> getInterval(T min, T max) {
	  //System.out.println("Min: "+min);
	  //System.out.println("Max: "+max);
	  
	  
	  BinaryNode<T> current = root; 
	  
	  if(root==null){
		  return lst;
	  }
	  inOrder(current, min , max);
	  
	  return lst;
 }
  
  //recursive method to get interval
  private void inOrder(BinaryNode<T> current, T min, T max){
	  if(min.compareTo(findMin())< 0||max.compareTo(findMax())>0){
		  return;
	  }
	  
	  if(min.compareTo(current.data)<=0&&max.compareTo(current.data)>=0){
		  if(current.left!=null&&current.data!=min){
			  inOrder(current.left,min,max);
		  }
		  
		  lst.add(current.data);
		
		  if(current.right!=null&&current.data!=max){
			  inOrder(current.right,min,max);
		  }
		  
		  
	  }else if(min.compareTo(current.data)>0){
		  if(current!=null){
			  inOrder(current.right,min,max);  
	      }
	  }else{
		  if(current.left!=null){
			  inOrder(current.left,min,max);
		  }
	  }
 }
  
  
  
  
//problem 3c
 @Override
 //Call on post order iterator class
 public Iterator<T> iterator() {
	 return new PostOrderBinaryTreeIterator(root);
}
  
 public ArrayList<T> postorderTraversal(BinaryNode<T> root) {  
	   PostOrderBinaryTreeIterator iterator = new PostOrderBinaryTreeIterator(root);  
	   ArrayList<T> results = new ArrayList<T>();  
	   while (iterator.hasNext()) {  
	     results.add(iterator.next());  
	   }  
	   return results;  
	 }
 
 
private class PostOrderBinaryTreeIterator implements java.util.Iterator<T>{
  Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();  
  
  /** find the first leaf in a tree rooted at current node*/  
  private void findNextLeaf(BinaryNode<T> current) {  
    while (current != null) {  
      stack.push(current);  
      if (current.left != null) {  
        current = current.left;  
      } else {  
        current = current.right;  
      }  
    }  
  }  
  
  
  public PostOrderBinaryTreeIterator(BinaryNode<T> root) {  
    findNextLeaf(root);  
  }  
  
  
  @Override  
  public boolean hasNext() {  
    return !stack.isEmpty();  
  }  
  
 
  @Override  
  public T next() {  
    if (!hasNext()) {  
      throw new NoSuchElementException("All nodes have been visited!");  
    }  
  
    BinaryNode<T> result = stack.pop();  
    if (!stack.isEmpty()) {  
      BinaryNode<T> top = stack.peek();  
      if (result == top.left) {  
        findNextLeaf(top.right); // find next leaf in right sub-tree 
      }  
    }  
  
    return result.data;  
  }  
  
  @Override  
  public void remove() {  
    throw new UnsupportedOperationException("remove() is not supported.");  
  }  
}
	
	  
  public void insert(T x) {
    root = insert(x, root);
  }

  public void remove(T x) {
    root = remove(x, root);
  }

  public T findMin() {
    if (isEmpty())
      throw new IllegalStateException();
    return findMin(root).data;
  }

  public T findMax() {
    if (isEmpty())
      throw new IllegalStateException();
    return findMax(root).data;
  }

  public boolean contains(T x) {
    return contains(x, root);
  }

  public void makeEmpty() {
    root = null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public void printTree() {
    if (isEmpty())
      System.out.println("Empty tree");
    else
      printTree(root);
  }

  private BinaryNode<T> insert(T x, BinaryNode<T> t) {
    if (t == null)
      return new BinaryNode<>(x, null, null);

    int compareResult = x.compareTo(t.data);

    if (compareResult < 0)
      t.left = insert(x, t.left);
    else if (compareResult > 0)
      t.right = insert(x, t.right);
    else {
      // duplicate, do nothing
    }

    return t;
  }

  private BinaryNode<T> remove(T x, BinaryNode<T> t) {
    if (t == null)
      return t; // Item not found; do nothing

    int compareResult = x.compareTo(t.data);

    if (compareResult < 0)
      t.left = remove(x, t.left);
    else if (compareResult > 0)
      t.right = remove(x, t.right);
    else if (t.left != null && t.right != null) {
      // 2 children
      t.data = findMin(t.right).data;
      t.right = remove(t.data, t.right);
    } else
      // 1 or 0 children
      t = (t.left != null) ? t.left : t.right;
    return t;
  }

  private BinaryNode<T> findMin(BinaryNode<T> t) {
    if (t == null)
      return null;
    else if (t.left == null)
      return t;
    return findMin(t.left);
  }

  private BinaryNode<T> findMax(BinaryNode<T> t) {
    if (t != null)
      while (t.right != null)
        t = t.right;

    return t;
  }

  private boolean contains(T x, BinaryNode<T> t) {
    if (t == null)
      return false;

    int compareResult = x.compareTo(t.data);

    if (compareResult < 0)
      return contains(x, t.left);
    else if (compareResult > 0)
      return contains(x, t.right);
    else
      return true; // Match
  }

  private void printTree(BinaryNode<T> t) {
    if (t != null) {
      printTree(t.left);
      System.out.println(t.data);
      printTree(t.right);
    }
  }

  private int height(BinaryNode<T> t) {
    if (t == null)
      return -1;
    else
      return 1 + Math.max(height(t.left), height(t.right));
  }

  // Test program
  public static void main(String[] args) throws Exception {
    BinarySearchTree<Integer> t = new BinarySearchTree<>();
    final int NUMS = 100;// 4000;
    final int GAP = 37;

    System.out.println("Checking... (no more output means success)");

    for (int i = GAP; i != 0; i = (i + GAP) % NUMS){
      t.insert(i);
    }
    t.printTree();

    for (int i = 1; i < NUMS; i += 2){
      t.remove(i);
    }
    t.printTree();

    if (NUMS < 40) t.printTree();
    
    if (t.findMin() != 2 || t.findMax() != NUMS - 2)
      System.out.println("FindMin or FindMax error!");

    for (int i = 2; i < NUMS; i += 2){
      if (!t.contains(i))
        System.out.println("Find error1!");
    }

    for (int i = 1; i < NUMS; i += 2) {
      if (t.contains(i))
        System.out.println("Find error2!");
    }
    
    BinarySearchTree<String> st = new BinarySearchTree<String>();
    String s = "sdljfnzxvk234";
    
    for (int j = 0; j < s.length(); j++) {
      st.insert(s.substring(j, j + 1));
    }
    
    st.remove("f");
    
    System.out.println("x " + st.contains("x"));
    System.out.println("max " + st.findMax());
    System.out.println("min " + st.findMin());
    /*
    List<Integer> lst = new ArrayList<Integer>(); 
    lst = t.getInteval(t.findMax(), t.findMin());
    System.out.println(lst);
    */
    /*
    boolean tree = t.isBst();
    System.out.println(tree);
    boolean tree1 = st.isBst();
    System.out.println(tree1);
    
    BinarySearchTree<Integer> lst5 = new BinarySearchTree<Integer>();
    lst5.insert(1);
    lst5.insert(5);
    lst5.insert(3);
    boolean tree5 = lst5.isBst();
    System.out.println(tree5);
    */
    /*
    Iterator<String> itr = null;
    Iterator<Integer> itr1 = null;
    itr = st.iterator();
    while(itr.hasNext()){
    	System.out.println(itr.next()+" ");
    }
    itr1 = t.iterator();
    while(itr1.hasNext()){
    	System.out.println(itr1.next()+" ");
    }
    */
    //System.out.println("GetInterval: ");
    //System.out.println(t.findMin());
    //System.out.println(t.findMax());
    //System.out.println(t.getInteval(2, 10));
   
    
    
    
    
  }

 

 }
