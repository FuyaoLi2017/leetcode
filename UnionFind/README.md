### 947
Java, set collection, putIfAbsent方法

## intro for UF
https://www.geeksforgeeks.org/introduction-to-disjoint-set-data-structure-or-union-find-algorithm/

### Optimization
Union by Rank/Size and Path Compression
https://www.geeksforgeeks.org/union-by-rank-and-path-compression-in-union-find-algorithm/

- Time complexity: O(n) for creating n single item sets . The two techniques -path compression with the union by rank/size, the time complexity will reach nearly constant time. It turns out, that the final amortized time complexity is O(α(n)), where α(n) is the inverse Ackermann function, which grows very steadily (it does not even exceed for n<10600  approximately).

- Space complexity: O(n) because we need to store n elements in the Disjoint Set Data Structure.