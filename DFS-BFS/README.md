## DFS method
### 1.确定起始递归的条件
### 2.建立一种递归的规则，dfs()函数
### 3.每一个点进一步深入的去扩展
> LC733
### 4.可以利用seen等boolean进行标记方便处理
>LC695
### 5.可以利用recursive method 或者 interative method 解决问题， 迭代的方法用stack

### 6.对于涉及链表的问题，可以利用建立int left, int right将左右分支情况分类进行考虑

### 7. binary search tree 可以定义成左侧可以相等或者右侧可以相等，但是同一个树必须要确定一个规则

### 8. 括号匹配的isVaild确认的方法
>    private boolean isValid(StringBuilder sb){
        int left = 0, right = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                left++;
            }
            if (sb.charAt(i) == ')') {
                right++;
            }
            if (i < sb.length() - 1 && left < right) {
                return false;
            }
        }
        return left == right;
    }
