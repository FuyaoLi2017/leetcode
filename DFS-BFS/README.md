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
### 9. DFS-1 vs DFS 2
> LAICODE 063

- dfs - 1: 每层只做一件事，给一个情况分出一支
- dfs - 2: 开头unique的元素遍历一遍，never look back

### 10. "" 如果toCharArray() 结果是是什么？
是空的，长度为0的charArray

### 11. StringBuilder() toString()
直接new的StringBuilder, toString()之后返回的是长度为0 string -> ""
## backtracking
- 对于回溯问题，总结出一个递归函数模板，包括以下三点  
- 递归函数的开头写好跳出条件，满足条件才将当前结果加入总结果中  
- 已经拿过的数不再拿 if(s.contains(num)){continue;}  
- 遍历过当前节点后，为了回溯到上一步，要去掉已经加入到结果list中的当前节点。  
- **务必记得每一个添加新步骤都要进行backtrack, 包括下标，改变的String以及stack中的东西等等**
