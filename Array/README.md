### Speed of operating array
copy an array is slow, copy certain elements are much quicker
> leetcode 048

### How to initialize a ArrayList
https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
`ArrayList<String> places = new ArrayList<>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));`

### 对于2D array的题
- 注意可能利用bit操作，lc289 game of life.
- 一般都是先取出来横向有多少点，纵向有多少点，移动指针进行操作

### ArrayList array
ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1]; // 注意建立ArrayList数组的方法，后面不允许加上<>
- 1 List[]
不推荐，因为List是一个带Generics的class，不推荐跟数组一起使用。因为一起使用要像你这样擦掉泛型。你可以用List of List
- 2, List<Integer>[]
错的
- 3, Integer[]
是不带泛型的数组 可以用，不过wrapper class效率不如primitive，没有特殊原因，还是用int[]

### 复制数组
public static int[] copyOf(int[] original, int newLength)
Arrays.copyOf(array, 4);

### SpiralMatrix 类型的题目
direction要不断变化，第一个数字是控制的行号，第二个是列号，横向移动需要通过第二个数字进行控制，一定要记清楚！

### 防止overwrite DP数组之前存的数字
有时候可以从另一个方向遍历就可以避免问题

## 大小的排序问题
排序问题，比如905，977，大部分都可以用two pointers解决，两个指针取到的内容进行对比，根据前后比较的结果更新

### 奇数偶数中间数字处理不一致的问题
直接用(array.length+1) / 2, 能比较优雅的handle

### 转换0，1 bit
不要用conditional sentence,直接位运算

## toArray用法
不能直接在return对primitive type用toArray()

## 提及到subsequence就要想到需要使用next array
- 792
- 1055

---
## Most Popular Methods to solve Array issues
- HashMap
- Two pointers

## 2967  Minimum Cost to Make Array Equalindromic
Don't be afraid to find answer by brute force checking the palindrome, go find next number and previous number