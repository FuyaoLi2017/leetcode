
### maxHeap
```java
private static final int DEFAULT_INITIAL_CAPACITY = 11;
PriorityQueue<Integer> maxHeap=new PriorityQueue<Integer>(DEFAULT_INITIAL_CAPACITY, new Comparator<Integer>() {
       @Override
       public int compare(Integer o1, Integer o2) {                
           return o2-o1;
       }
   });


PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
```

###find the smallest kth element in size n array
    - min heap
    1. heapify all elements O(n)
    2. call pop() k times klog(n)
    3. Time: O(n + klog(n))

    - max heap
    1. heapify first k element O(k)
    2. compare with top element
        - larger than top element => ignore
        - smaller than top element => poll() and insert() the new element => O((n-k)logk)
        - O(k + (n-k)logk)
    - quick select

### find the largest kth element in size n array: similar, use the the reverse version of the heap like the previous one

### the key-value pair problem, use the HashMap and PriorityQueue! LC347

### faced with array question, top k... statement. nearest k statement.
Consider using heap. First add k(k + 1) elements to the heap and do the following operations.
>laicode325
