
- maxHeap
```java
private static final int DEFAULT_INITIAL_CAPACITY = 11;
PriorityQueue<Integer> maxHeap=new PriorityQueue<Integer>(DEFAULT_INITIAL_CAPACITY, new Comparator<Integer>() {
       @Override
       public int compare(Integer o1, Integer o2) {                
           return o2-o1;
       }
   });
```
