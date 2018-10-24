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
