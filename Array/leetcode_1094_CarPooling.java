/*
https://leetcode.com/problems/car-pooling/
*/
// my solution
class Solution {
    class Pair {
        int position;
        // flag is 0 means it is a starting position, 1 means an end position
        int flag;
        int passengers;
        public Pair(int position, int flag, int passengers) {
            this.position = position;
            this.flag = flag;
            this.passengers = passengers;
        }
    }

    public boolean carPooling(int[][] trips, int capacity) {
        // 扫描线的做法
        List<Pair> schedule = new ArrayList<>();
        for (int i = 0; i < trips.length; i++) {
            schedule.add(new Pair(trips[i][1], 0, trips[i][0]));
            schedule.add(new Pair(trips[i][2], 1, trips[i][0]));
        }
        Collections.sort(schedule, (o1, o2) -> {
            if (o1.position < o2.position) return -1;
            else if (o1.position > o2.position) return 1;
            else return o2.flag - o1.flag;
        });

        int currentPassengers = 0;
        int prev = 0;
        for (int i = 0; i < schedule.size(); i++) {
            Pair p = schedule.get(i);
            if (p.position > prev) {
                // check the passenger status
                if (currentPassengers > capacity) return false;
            }
            if (p.flag == 0) {
                currentPassengers += p.passengers;
                prev = p.position;
            } else {
                currentPassengers -= p.passengers;
                prev = p.position;
            }
        }
        return true;
    }
}

// a smarter solution, just record the number of the users
public boolean carPooling(int[][] trips, int capacity) {
  int stops[] = new int[1001];
  for (int t[] : trips) {
      stops[t[1]] += t[0];
      stops[t[2]] -= t[0];
  }
  for (int i = 0; capacity >= 0 && i < 1001; ++i) capacity -= stops[i];
  return capacity >= 0;
}
