/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.

You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 */

 // top down DP
 // min_diff(i, d) = min_diff(j, d - 1) + max(jobDifficulty[i:j]) for all j > i
 class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {

        int n = jobDifficulty.length;
        // Edge case: make sure there is at least one job per day
        if (n < d) {
            return -1;
        }

        int[][] mem = new int[n][d + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= d; j++) {
                mem[i][j] = -1;
            }
        }

        return minDiff(0, d, jobDifficulty, mem);
    }

    private int minDiff(int i, int daysRemaining, int[] jobDifficulty, int[][] mem) {
        // Use memoization to avoid repeated computation of states
        if (mem[i][daysRemaining] != -1) {
            return mem[i][daysRemaining];
        }

        // Base case: finish all remaining jobs in the last day
        if (daysRemaining == 1) {
            int res = 0;
            for (int j = i; j < jobDifficulty.length; j++) {
                res = Math.max(res, jobDifficulty[j]);
            }
            return res;
        }

        int res = Integer.MAX_VALUE;
        int dailyMaxJobDiff = 0;
        // Iterate through possible starting index for the next day
        // and ensure we have at least one job for each remaining day.
        for (int j = i; j < jobDifficulty.length - daysRemaining + 1; j++) {
            dailyMaxJobDiff = Math.max(dailyMaxJobDiff, jobDifficulty[j]);
            res = Math.min(res, dailyMaxJobDiff + minDiff(j + 1, daysRemaining - 1, jobDifficulty, mem));
        }
        mem[i][daysRemaining] = res;
        return res;
    }
}

/**
 * Bottom up DP
 * State definition:

Index i (starting index in the jobDifficulty array) and day d (number of remaining days) will define the DP state. min_diff[d][i] refers to the minimum difficulty when starting at the ithi^{th}i 
th
  job with d days left.

min_diff[d][0] will be the final answer since it represents starting at the beginning of the job array and finishing all jobs in d days.

State transfer function:

The number of rows in the DP matrix is the total number of remaining days plus one (d + 1), and the number of columns in the DP matrix is the length of the jobDifficulty array plus one (n + 1). Note, for n jobs to be completed, there are n + 1 possible states, ranging from 0 jobs being done, to all n jobs being completed.

The value of min_diff[d][i] only depends on the results of min_diff[d - 1][j] when j > i. The statements can be expressed as:

min_diff[d][i] = min(daily_max_job_diff + min_diff[d - 1][j]) for all j > i,

where daily_max_job_diff is the maximum difficulty of jobs between index i and index j not including j, and i is the index of the first job that is done on the current day and j is the index of the first job that is done on the next day.

Base case:

We will prefill the last column of the matrix with zeros, to indicate that when there are no remaining jobs, the maximum job difficulty to be added is 0.
 */
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {

        int n = jobDifficulty.length;
        // Edge case: make sure there is at least one job per day
        if (n < d) {
            return -1;
        }

        int[][] mem = new int[n][d + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= d; j++) {
                mem[i][j] = -1;
            }
        }

        return minDiff(0, d, jobDifficulty, mem);
    }

    private int minDiff(int i, int daysRemaining, int[] jobDifficulty, int[][] mem) {
        // Use memoization to avoid repeated computation of states
        if (mem[i][daysRemaining] != -1) {
            return mem[i][daysRemaining];
        }

        // Base case: finish all remaining jobs in the last day
        if (daysRemaining == 1) {
            int res = 0;
            for (int j = i; j < jobDifficulty.length; j++) {
                res = Math.max(res, jobDifficulty[j]);
            }
            return res;
        }

        int res = Integer.MAX_VALUE;
        int dailyMaxJobDiff = 0;
        // Iterate through possible starting index for the next day
        // and ensure we have at least one job for each remaining day.
        for (int j = i; j < jobDifficulty.length - daysRemaining + 1; j++) {
            dailyMaxJobDiff = Math.max(dailyMaxJobDiff, jobDifficulty[j]);
            res = Math.min(res, dailyMaxJobDiff + minDiff(j + 1, daysRemaining - 1, jobDifficulty, mem));
        }
        mem[i][daysRemaining] = res;
        return res;
    }
}


// Monotonic Stack
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        // min_diff_curr_day and min_diff_prev_day record the minimum total job difficulty
        // for the current day and previous day, respectively
        int[] minDiffPrevDay = new int[n];
        int[] minDiffCurrDay = new int[n];
        int[] tmp;

        Arrays.fill(minDiffPrevDay, 1000);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int day = 0; day < d; ++day) {
            // Use a monotonically decreasing stack to record job difficulties
            stack.clear();
            // The number of jobs needs to be no less than number of days passed.
            for (int i = day; i < n; i++) {
                // We initialize min_diff_curr_day[i] as only performing 1 job at the i-th index.
                // At day 0, the minimum total job difficulty is to complete the 0th job only.
                // Otherwise, we increment min_diff_prev_day[i - 1] by the i-th job difficulty
                minDiffCurrDay[i] = i > 0 ? minDiffPrevDay[i - 1] + jobDifficulty[i] : jobDifficulty[i];

                // When we find the last element in the stack is smaller than or equal to current job,
                // we need to pop out the element to maintain a monotonic decreasing stack.
                while (!stack.isEmpty() && jobDifficulty[stack.peek()] <= jobDifficulty[i]) {
                    // If we include all jobs with index j+1 to i to the current d,
                    // total job difficulty of the current d will be increased
                    // by the amount of jobDifficulty[i] - jobDifficulty[j]
                    int j = stack.pop();
                    int diffIncr = jobDifficulty[i] - jobDifficulty[j];
                    minDiffCurrDay[i] = Math.min(minDiffCurrDay[i], minDiffCurrDay[j] + diffIncr);
                }

                // When the last element in the stack is larger than current element,
                // if we include all jobs with index j+1 to i to the current d
                // the overall job difficulty will not change
                if (!stack.isEmpty()) {
                    minDiffCurrDay[i] = Math.min(minDiffCurrDay[i], minDiffCurrDay[stack.peek()]);
                }

                // Update the monotonic stack by adding in the current index
                stack.push(i);
            }
            tmp = minDiffPrevDay;
            minDiffPrevDay = minDiffCurrDay;
            minDiffCurrDay = tmp;
        }
        return minDiffPrevDay[n - 1];
    }
}