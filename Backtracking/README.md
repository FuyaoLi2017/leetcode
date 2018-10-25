### the core of backtracking
- resume to its original state after backtracking

- add a visited array to mark the visited points to avoid too much duplicate visits

- new the backtracking structure in the main processing function, not dfs() function

- when adding the result, i.e. add a list, you need to new a ArrayList<>(cur),一开始只是创建了一个ArrayList, 后面必须再加

- 逻辑上，各个backtracking的路都是走一遍，所以内部都是if else去backtrack, 不要写错为while

-https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
