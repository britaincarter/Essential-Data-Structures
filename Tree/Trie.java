import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class Trie {
  private TrieNode root;
  public static final char NULL = '0';
  
  
  public Trie() {
    root = new TrieNode(NULL, false);
  }
  
  
  // implement your methods here
  // feel free (and you probably should) add helper private methods
  // problem 4a 
  
  
  public void addWord(String word){
	  addWord(root, word);
  }
  
  private void addWord(TrieNode parent, String word) {
	  if(word==null)return;
	  if(word.trim().length()==0){
		  return;
	  } 
	  
	  for(int i = 0; i<word.length();i++){
		  if(Character.isLowerCase(word.charAt(i)-97)){
			  throw new IllegalArgumentException();
		  }
		  int ascii = word.charAt(i)-97;
		  char currentLetter = word.charAt(i);
		  
		  if(parent.children[ascii]==null){
			  TrieNode newNode = new TrieNode(currentLetter,false);
			  parent.children[ascii]=newNode;
			  parent=newNode;
		  }else{  //(parent.children[ascii].letter==currentLetter)
			  parent = parent.children[ascii];
		  }
		  
	  }
	  parent.endOfWord=true;
  }	  
	  
	  
	 
  
  
  
  // problem 4b
  public boolean contains(String word) {
	  TrieNode current = root;
	  
	  int size = word.length();
	  if(size==0||word==null)return false;
	  
	  for(int i =0; i<size; i++){
		  int ascii = word.charAt(i)-97;
		  
		  if(current.children[ascii]==null){
			  return false;
		  }else{
			  current = current.children[ascii];
		  }
	  }
	  if(current.endOfWord==true){
		  return true;
	  }
	  return false;
  }
  
  
  // problem 4c
  
  public List<String> getStrings() {
	  List<String> lst = new ArrayList<String>();
		
		for(int i = 0; i<root.children.length; i++) {
			printTree(lst, root.children[i],"");
		}
		return lst;
}
  
  //Finds all words in the subtree from node.
  private void printTree(List<String> lst, TrieNode newNode, String word){
	  if(newNode!=null){
		  char letter = newNode.letter;
		  word = word+letter;
		  if(newNode.endOfWord){
			  lst.add(word);
		  }
		  for(int i =0; i<newNode.children.length;i++){
			  printTree(lst, newNode.children[i],word);
		  }
	  }
  }
   
  // problem 4d
  public List<String> getStartsWith(String prefix) {
	  String word = "";
	  List<String> lstPre = new ArrayList<String>();
	  if(prefix.length()==0||prefix==null){
    	  return Collections.emptyList();
      }
	  
	  TrieNode node = root;
	  
	  //Get down to the prefix node
	  for(int i = 0; i<prefix.length();i++){
		  int ascii = prefix.charAt(i)-97;
		  node = node.children[ascii];
		  
		  char currentLetter = node.letter;
		  word = word+currentLetter;
	  }
	  word = word.substring(0, word.length()-1);
	  System.out.println(word);
	  printTree(lstPre, node, word);
	  
	  return lstPre;
	  
  }	  
 
  
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    buildString(root, sb, NULL);
    return sb.toString().trim();
  }
  
  private void buildString(TrieNode node, StringBuilder sb, int layer) {
    for (int i = 0; i < layer; i++) {  // print some indentation
      sb.append(" ");
    }
    sb.append(node);    // print the node itself
    sb.append("\n");
    for (TrieNode child : node.children) {  // recursively print each subtree
      if (child != null) {
        buildString(child, sb, layer + 1);
      }
    }
  }

  private class TrieNode {
    public char letter;
    public boolean endOfWord;
    public TrieNode[] children;

    public TrieNode(char letter, boolean endOfWord) {
      this.letter = letter;
      this.endOfWord = endOfWord;
      children = new TrieNode[26]; // number of letters in English alphabet
    }

	public String toString() {
      return endOfWord ? Character.toString(Character.toUpperCase(letter)) : Character.toString(letter);
    }
  
    public char getChar(){
    	return this.letter;
    }
    
    
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    System.out.println(trie.contains("h"));
    trie.addWord("hel");
    System.out.println(trie.contains("hel"));
    trie.addWord("help");
    trie.addWord("hell");
    System.out.println(trie.contains("hell"));
    trie.addWord("avb");
    trie.addWord("avc");
    System.out.println(trie.contains("av"));
    System.out.println(trie.contains("avc"));
    //trie.addWord("helloooos");
    System.out.println(trie);
    System.out.println(trie.getStrings());
    System.out.println(trie.getStartsWith("av"));
  }
}
