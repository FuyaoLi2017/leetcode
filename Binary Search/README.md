# Notes about Binary Search
### in order to avoid circle in the loop
the jump out condition of binary search
-> (low + 1 < high)
也可以有其他的做法

### binary search is high level theory, what you need to do is to cur off part of the possible solution and find out the final solution in this process.

### 几种常见的边界条件
- while (left <= right): classic binary search
- while (left < right): quit the while loop two values are equal
- while (left + 1 < right): quit the while loop when two values are neighbors, 可以避免mid + 1, mid - 1是不valid的
>laicode 327

### 找rotate point这里这样的low = mid + 1的写法注意体会，可以让结果保留在low位置出现
while (low < high) {
    int mid = (low + high) / 2;
    if(nums[mid] > nums[high]) {
        low = mid + 1;
    } else {
        high = mid;
    }
}
int rotate = low;

### overflow
- 可以选用更大的数据表示
- 可以把乘法操作变为除法操作

### Avoid overflow
left + (right - left) / 2

### 不要总被二分的边界条件限制住。其实可以简单点
像laicode 069, 其实选中的点是不是 <= mid + 1就足以判断！（其实就是==， 不会小于mid + 1）

### 遇到一个范围找第K大的值
binary search

### 遇到区间缩小问题
binary search
774. Minimize Max Distance to Gas Station
chocolate

### 744, char这类直接可以比较大小，进行二分搜索

# Binary Search Leetcode explorer
## Template 1 - classic binary search
Initial Condition: left = 0, right = length-1
Termination: left > right
Searching Left: right = mid-1
Searching Right: left = mid+1
```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```

## Template #2 is an advanced form of Binary Search.
- An advanced way to implement Binary Search.
- Use the element's right neighbor to determine if the condition is met and decide whether to go left or right
- Guarantees Search Space is at least 2 in size at each step
- Post-processing required. Loop/Recursion ends when you have 1 element left. Need to assess if the remaining element meets the condition.


```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left < right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid; }
  }

  // Post-processing:
  // End Condition: left == right
  if(nums[left] == target) return left;
  return -1;
}
```


## Template 3
- An alternative way to implement Binary Search
- Use the element's neighbors to determine if the condition is met and decide whether to go left or right
- Guarantees Search Space is at least 3 in size at each step
- Post-processing required. Loop/Recursion ends when you have 2 elements left. Need to assess if the remaining elements meet the condition.
 
```java
int binarySearch(int[] nums, int target) {
    if (nums == null || nums.length == 0)
        return -1;

    int left = 0, right = nums.length - 1;
    while (left + 1 < right){
        // Prevent (left + right) overflow
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid;
        } else {
            right = mid;
        }
    }

    // Post-processing:
    // End Condition: left + 1 == right
    if(nums[left] == target) return left;
    if(nums[right] == target) return right;
    return -1;
}
```