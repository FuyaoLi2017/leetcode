# Sort
### if unsorted Array combined with linear time requirement, must be bucket sort or quick sort
> bucket sort, radix sort LC164
每个位置都相当于一个桶，保存桶里面最大和最小的数字进行更新

### the summary of 8 sort method
https://blog.csdn.net/u010757264/article/details/50151345

### implement of CompareTo() method
- CompareTo比较的是this和参数对象
#### 利用Double类型的compare方法进行比较
```java
public int compareTo(Worker other) {
       return Double.compare(ratio(), other.ratio());
   }
```
#### 利用大小进行比较
```java
   class Example implements Comparable {
       int i;
       public int compareTo(Object o) {
           if (this.i > ((Example)o).i ) return -1;
           else if (this.i < ((Example)o).i ) return 1;
           else return 0;
       }
```
原文：https://blog.csdn.net/hippoppower/article/details/4460784

### Quick sort, bucket sort: amortized O(N)
#### quick sort
- TC:（nlogn）
- SC: O(logn)
- Quick select: find a number at K. TC: O(n)

#### code for quick Sort
>reference: Algorithms 4th edition

```java
private static void sort(Comparable[] a, int lo, int hi) {
  if (hi <= lo) return;
  int j = partition(a, lo, hi);
  sort(a, lo, j - 1);
  sort(a, j + 1, hi);
}

private static int partition(Comparable[] a, int lo, int hi) {
  // seperate the array to a[lo..i-1], a[i], a[i+1..hi]
  int i = lo, j = hi + 1;  // left and right scan cursor
  Comparable v = a[lo];    // seperate the elements using v
  while(true) {
    // scan the right and left, check the scan is stopped or not and exchange elements
    while(less(a[++i], v)) if(i == hi) break;
    while(less(v, a[--j])) if(j == lo) break;
    if (i >= j) break;
    exchange(a, lo, j);  // put v = a[j] into the right place
    return j;            // realize a[lo..j-1] <= a[j] <= a[j+1..hi]
  }
}

private static boolean less(){
  // compare the required elements
}
private static void exchange(Comparable[] array, Comparable a, Comparable b) {
  // swap the position of a and b
}
```

bucket sort
https://www.byvoid.com/zhs/blog/sort-radix
