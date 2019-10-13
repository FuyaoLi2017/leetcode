### Comparsion between DP and recursive
关于Recursion和DP的关系，课后看了一遍老师的例题，自己又思考了一下这个问题。有一些想法跟大家分享，不知对错，欢迎指正。

recursion，需要确定recursive rule，即确定size=n的解和size=n-1的解的关系，除问题的大小不同以外这两个解的物理意义是完全一样的，即所求问题的解和所求问题小一号的解，这也证明了为什么我们写recursion总是反复call同一个函数求解；

DP，则需计算size=1到size=n-1的“解”，但这个“解”的物理意义与题目问题的解可能不完全相同，比如：

1.  Largest sum of a subarray 的局部“解”是the largest sum of the subarray ​including the i-th element，而题目所求解不需包含最后第n个元素；

2.  ​Largest square of 1’s in a binary matrix的局部“解”是max size of square with (i, j) as the bottom right corner，但题目的解并不一定以(n, n)作为右下角；

为什么不像recursion直接求解小一号的问题呢？因为有些时候，将问题转化成求局部“解”，能够建立size=i问题和size=i+1问题直接的联系，从而极大地简化计算！比如我们可以很快由the largest sum of the subarray ​including the i-th element计算出the largest sum of the subarray ​including the i+1 element，但若由the largest sum of the subarray before i计算出the largest sum of the subarray ​before i+1就麻烦得多，还需要兼顾之前所有size问题的解转化计算。

DP的思想即是将size=1到size=n-1的“解”全部求出来，在这些小问题“解”的基础上构建size=n大问题的解，换句话说，扫一遍所有的“解”就知道题目的解了。

### DP trick
像是lc 583,很多时候可以用多的一行或者一列

### 2 DP array
有时候需要2个dp array，从2个方向看问题，非常方便了

### Key tips
We need to know what substructure or what relationship we want to store in the memo. This memo will help us to reduce duplicate calculation afterwards.
