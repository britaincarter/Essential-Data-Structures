AvlMap is a class `AvlMap.java` that implements the given `Map.java` interface. 

Specifically, the map holds keys (denoted by the generic `K`) and values (denoted by the generic `V`). 
`Pair.java` class acts as a "capsule" for one single key and a single value. `AvlTree.java` contains the AVL tree implementation, methods put(K key, V value) insert a pair into the AvlTree, and get(K key)
and are done in O(log N) time.

SeparateChainingMap.java implements Map.java and AvlMap.java, this overrides the same methods as above but implements them in O(1) time on average with the use of a map.

BwogBot.java implements SeparateChainingMap to read through the `comments.txt` and does a basic word count. Attempted to create a method 'getNMostPopularWords(int n)' to obtain the most common 'n' words. 

This is a common issue in Natural Language Processing