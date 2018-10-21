# Sort
### if unsorted Array combined with linear time requirement, must be bucket sort or quick sort
> bucket sort, radix sort LC164
每个位置都相当于一个桶，保存桶里面最大和最小的数字进行更新

### the summary of 8 sort method
https://blog.csdn.net/u010757264/article/details/50151345

### implement of CompareTo() method
- CompareTo比较的是this和参数对象
#### 利用Double类型的compare方法进行比较
public int compareTo(Worker other) {
       return Double.compare(ratio(), other.ratio());
   }
#### 利用大小进行比较
   class Example implements Comparable {
       int i;
       public int compareTo(Object o) {
           if (this.i > ((Example)o).i ) return -1;
           else if (this.i < ((Example)o).i ) return 1;
           else return 0;
       }
原文：https://blog.csdn.net/hippoppower/article/details/4460784
