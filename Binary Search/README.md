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

### 不要总被二分的边界条件限制住。其实可以简单点
像laicode 069, 其实选中的点是不是 <= mid + 1就足以判断！（其实就是==， 不会小于mid + 1）

### 遇到一个范围找第K大的值
binary search
