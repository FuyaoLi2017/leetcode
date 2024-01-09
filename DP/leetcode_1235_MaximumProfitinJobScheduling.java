/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

return max profit.
 */

 // My original idea is similar to https://leetcode.com/problems/maximum-profit-in-job-scheduling/solutions/409009/java-c-python-dp-solution/?envType=daily-question&envId=2024-01-06
 // the dp array should be the jobs entry, not the time
 class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    int n = startTime.length;
    int[][] jobs = new int[n][3];
    for (int i = 0; i < n; i++) {
        jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
    }
    Arrays.sort(jobs, (a, b)->a[1] - b[1]);
    TreeMap<Integer, Integer> dp = new TreeMap<>();
    dp.put(0, 0);
    for (int[] job : jobs) {
        int cur = dp.floorEntry(job[0]).getValue() + job[2];
        if (cur > dp.lastEntry().getValue())
            dp.put(job[1], cur);
    }
    return dp.lastEntry().getValue();
}
}

// top down DP + binary search
// if we sort our jobs according to start time, then we can apply binary search to find the next job.
// After sorting jobs according to startTime, to find the index of the first non-conflicting job, binary search for the endTime of the current job in the list of start times for all jobs.

// For each job, we will try two options:

// Schedule this job and move on to the next non-conflicting job using binary search.
// Skip this job and move on to the next available job.
class Solution {
    // maximum number of jobs are 50000
    int[] memo = new int[50001];
    
    private int findNextJob(int[] startTime, int lastEndingTime) {
        int start = 0, end = startTime.length - 1, nextIndex = startTime.length;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            if (startTime[mid] >= lastEndingTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }
    
    private int findMaxProfit(List<List<Integer>> jobs, int[] startTime, int n, int position) {
        // 0 profit if we have already iterated over all the jobs
        if (position == n) {
            return 0;
        }
        
        // return result directly if it's calculated 
        if (memo[position] != -1) {
            return memo[position];
        }
        
        // nextIndex is the index of next non-conflicting job
        int nextIndex = findNextJob(startTime, jobs.get(position).get(1));
        
        // find the maximum profit of our two options: skipping or scheduling the current job
        int maxProfit = Math.max(findMaxProfit(jobs, startTime, n, position + 1), 
                        jobs.get(position).get(2) + findMaxProfit(jobs, startTime, n, nextIndex));
        
        // return maximum profit and also store it for future reference (memoization)
        return memo[position] = maxProfit;
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();
        
        // marking all values to -1 so that we can differentiate 
        // if we have already calculated the answer or not
        Arrays.fill(memo, -1);
        
        // storing job's details into one list 
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);
            jobs.add(currJob);
        }
        jobs.sort(Comparator.comparingInt(a -> a.get(0)));
        
        // binary search will be used in startTime so store it as separate list
        for (int i = 0; i < length; i++) {
            startTime[i] = jobs.get(i).get(0);
        }
        
        return findMaxProfit(jobs, startTime, length, 0);
    }
}

// bottom up DP + binary search
class Solution {
    // maximum number of jobs are 50000
    int memo[] = new int[50001];
    
    private int findNextJob(int[] startTime, int lastEndingTime) {
        int start = 0, end = startTime.length - 1, nextIndex = startTime.length;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (startTime[mid] >= lastEndingTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }
    
    private int findMaxProfit(List<List<Integer>> jobs, int[] startTime) {
        int length = startTime.length;
        
        for (int position = length - 1; position >= 0; position--) {
            // it is the profit made by scheduling the current job 
            int currProfit = 0;
            
            // nextIndex is the index of next non-conflicting job
            int nextIndex = findNextJob(startTime, jobs.get(position).get(1));
            
            // if there is a non-conflicting job possible add it's profit
            // else just consider the curent job profit
            if (nextIndex != length) {
                currProfit = jobs.get(position).get(2) + memo[nextIndex];
            } else {
                currProfit = jobs.get(position).get(2);
            }
            
            // storing the maximum profit we can achieve by scheduling 
            // non - conflicting jobs from index position to the end of array
            if (position == length - 1) {
                memo[position] = currProfit;
            } else {
                memo[position] = Math.max(currProfit, memo[position + 1]);
            }
        }
        
        return memo[0];
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();
        
        // storing job's details into one list 
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);
            
            jobs.add(currJob);
        }
        
        jobs.sort(Comparator.comparingInt(a -> a.get(0)));

        // binary search will be used in startTime so store it as separate list
        for (int i = 0; i < length; i++) {
            startTime[i] = jobs.get(i).get(0);
        }
        
        return findMaxProfit(jobs, startTime);
    }
}

/**
 * 
1. Store the startTime, endTime, and profit of each job in jobs.
2. Sort the jobs according to their starting time.
3. Iterate over jobs from left to right, where i is the index of the current job. For each job
While the job chain at the top of the priority queue does not conflict with the current job, 
pop from the priority queue.
For each popped job chain compare its total profit with the maxProfit and update maxProfit accordingly.
4. Push the pair of ending time and the combined profit (maxProfit + profit of this job) into the heap. 
This item represents the chain created by adding the current job to the most profitable job chain.
After iterating over all the jobs, compare the profit of the remaining chains in the 
priority queue with the maxProfit. Return the value of maxProfit.
 */

 class Solution {
    class The_Comparator implements Comparator<ArrayList<Integer>> {
        public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
            return list1.get(0) - list2.get(0);
        }
    }
    
    private int findMaxProfit(List<List<Integer>> jobs) {
        int n = jobs.size(), maxProfit = 0;
        // min heap having {endTime, profit}
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(new The_Comparator());
        
        for (int i = 0; i < n; i++) {
            int start = jobs.get(i).get(0), end = jobs.get(i).get(1), profit = jobs.get(i).get(2);
            
            // keep popping while the heap is not empty and
            // jobs are not conflicting
            // update the value of maxProfit
            while (pq.isEmpty() == false && start >= pq.peek().get(0)) {
                maxProfit = Math.max(maxProfit, pq.peek().get(1));
                pq.remove();
            }
            
            ArrayList<Integer> combinedJob = new ArrayList<>();
            combinedJob.add(end);
            combinedJob.add(profit + maxProfit);
            
            // push the job with combined profit
            // if no non-conflicting job is present maxProfit will be 0
            pq.add(combinedJob);
        }
        
        // update the value of maxProfit by comparing with
        // profit of jobs that exits in the heap
        while (pq.isEmpty() == false) {
            maxProfit = Math.max(maxProfit, pq.peek().get(1));
            pq.remove();
        }
        
        return maxProfit;
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();
        
        // storing job's details into one list 
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);
            
            jobs.add(currJob);
        }
        
        jobs.sort(Comparator.comparingInt(a -> a.get(0)));
        return findMaxProfit(jobs);
    }
}