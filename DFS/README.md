### hashset
hashset在循环的过程中不能remove.
需要使用iterator遍历才不会发生报错

但是如果遍历的对象不是hashSet中的元素的话，是没有任何问题的

###DFS问题总结
https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

### DFS 在做循环的时候要细心，被循环的对象的下标要放进去，而不是起始的下标，要注意

### factor combination, 注意不要在条件判断的时候把值给修改了

### the core of backtracking
- resume to its original state after backtracking

- add a visited array to mark the visited points to avoid too much duplicate visits

- new the backtracking structure in the main processing function, not dfs() function

- when adding the result, i.e. add a list, you need to new a ArrayList<>(cur),一开始只是创建了一个ArrayList, 后面必须再加,否则backtracking回最开始，什么都没有加入

- 逻辑上，各个backtracking的路都是走一遍，所以内部都是if else去backtrack, 不要写错为while

-https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
