### Traverse HashMap
```java
// ways to traverse Hashmap
//sort the list according to the frenquency of the words
List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());  
        //realize the compare by defining a comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
          // sort in a decreasing order
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
    });

    // print the top 25 words
    int num = 1;
        for(Map.Entry<String, Integer> result : list) {  
            if(num <= 25) {
                // output words which length is longer than 1
                if(result.getKey().length() > 1){
                    System.out.println(result.getKey() + "  -  " + result.getValue());
                    num++;
                }else {
                    continue;
                }
            }  
            else break;  
        }
```
### Use / && % to make calculation easier
> 036 ValidSudoku

### Compare HashMap LinkedHashMap TreeMap
>https://blog.csdn.net/xiyuan1999/article/details/6198394
>https://www.baeldung.com/java-treemap

### HashMap的扩容
>https://www.hollischuang.com/archives/2431   
>http://blog.720ui.com/2017/source_reading_map_dilatation/
>https://yikun.github.io/2015/04/01/Java-HashMap%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86%E5%8F%8A%E5%AE%9E%E7%8E%B0/

JDK8 以下是ArrayList和LinkedList实现HashMap
容量超过8就用红黑树

### TreeMap
TreeMap -> values(), 返回一个collections的view，所有的元素
其他的map也是，

### TreeSet
- floor(E e)
Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
- 	ceiling(E e)
Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
- 	higher(E e)
Returns the least element in this set strictly greater than the given element, or null if there is no such element.
- 	lower(E e)
Returns the greatest element in this set strictly less than the given element, or null if there is no such element.

### LinkedHashMap
protected boolean removeEldestEntry(Map.Entry<K,V> eldest)
Returns true if this map should remove its eldest entry. This method is invoked by put and putAll after inserting a new entry into the map. It provides the implementor with the opportunity to remove the eldest entry each time a new one is added. This is useful if the map represents a cache: it allows the map to reduce memory consumption by deleting stale entries.
Sample use: this override will allow the map to grow up to 100 entries and then delete the eldest entry each time a new entry is added, maintaining a steady state of 100 entries.

     private static final int MAX_ENTRIES = 100;

     protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
     }
 
