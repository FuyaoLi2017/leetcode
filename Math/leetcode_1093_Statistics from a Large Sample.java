/*
https://leetcode.com/contest/weekly-contest-142/problems/statistics-from-a-large-sample/
*/
class Solution {
    public double[] sampleStats(int[] count) {
        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        double mean = 0;
        double median = Integer.MIN_VALUE;
        double mode = Integer.MIN_VALUE;
        int totalNum = 0;
        boolean meetMin = false;
        int maxCount = 0;
        for (int i = 0; i < count.length; i++) {
            totalNum += count[i];
            if (!meetMin && count[i] != 0) {
                min = i;
                meetMin = true;
            }
            if (count[i] != 0 && i > max) {
                max = i;
            }
            if (count[i] > maxCount) {
                maxCount = count[i];
                mode = i;
            }
        }
        // find the median and mean
        boolean isOdd = false;
        int middle =  (totalNum + 1) / 2;
        if (totalNum % 2 == 1) {
            isOdd = true;
        }
        boolean meetFirst = false;
        boolean found = false;
        int first = -1;
        int second = -1;
        int current = 0;

        for (int i = 0; i < count.length; i++) {
            current += count[i];
            if (isOdd && current >= middle && !found) {
                median = i;
                found = true;
            } else if (!isOdd && !found){
                if (current == middle && !meetFirst) {
                    first = i;
                    meetFirst = true;
                } else if (meetFirst && current > middle) {
                    second = i;
                    median = (double)(first + second) / 2;
                    found = true;
                } else if (!meetFirst && current > middle) {
                    median = i;
                    found = true;
                }
            }
            mean += i * ((double)count[i] / totalNum);
        }
        return new double[]{min, max, mean, median, mode};
    }
}
