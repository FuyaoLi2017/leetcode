
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

### DEFAULT_INITIAL_CAPACITY 大小问题
capacity大小问题：必须是常数，不能后来指定

### The delete of the heap during a simple implementation of heap is usually not supported.
It could be sift up OR sift down!!!

## Use computeIfAbsent().add() to simplify logic
```java
// Insert the '(-1 * rating, name)' element in the current cuisine's set.
            cuisineFoodMap
                .computeIfAbsent(cuisines[i], k -> new TreeSet<>((a, b) -> {
                    int compareByRating = Integer.compare(a.getKey(), b.getKey());
                    if (compareByRating == 0) {
                        // If ratings are equal, compare by food name (in ascending order).
                        return a.getValue().compareTo(b.getValue());
                    }
                    return compareByRating;
                }))
                .add(new Pair(-ratings[i], foods[i]));
```
