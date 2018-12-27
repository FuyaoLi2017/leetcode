### hashset
hashset在循环的过程中不能remove.
需要使用iterator遍历才不会发生报错

但是如果遍历的对象不是hashSet中的元素的话，是没有任何问题的

###DFS问题总结
https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

### DFS 在做循环的时候要细心，被循环的对象的下标要放进去，而不是起始的下标，要注意

### factor combination, 注意不要在条件判断的时候把值给修改了
