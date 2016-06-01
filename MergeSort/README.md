

Implements the class `KBestCounter<T extends Comparable<T>>` that keeps track of the 
*k*-largest elements seen so far in a stream. count(T x) operates in O(log k) time, kbest() runs in O(k) time and returns k largest elements.
Does not use more than O(k) space. Functionality is implemented with a heap.

Iterative Bottom-up Merge Sort, different from recursive merge sort. The method 'mergeSortB(Integer[] inputArray)` sorts the array `inputArray` in place. 


Merge Sort for Lists, Without Side Effects; A new list is created when the sorted sublists 
(return values of each recursive call) are merged. The original list is not modified 
during sorting (unlike other merge sorts which typically modify the content of the object). 
In general, no data outside of the method, not even the state of the
parameters, is changed. This is also referred to as a *side-effect free* 
function -- an important concept in functional
programming. 'mergeLists(List<Integer> left,
List<Integer> right)`, takes two sorted lists as a parameter and returns a new 
list containing the values of `left` and `right` in increasing order.
The method 
runs in *O(left.size() + right.size())* assuming left and right are linkedlists.