// brute force solution, Time Limit Exceeded
class Solution {
    public int numFriendRequests(int[] ages) {
        int number = ages.length;
        int count = 0;
        int subcount = 0;
        for(int i = 0; i < number; i++){
            for(int j = 0; j < number; j++){
                if(i == j)
                    continue;
                if((ages[j] <= 0.5 * ages[i] + 7) || (ages[j] > ages[i]))
                    continue;
                subcount++;
            }
            System.out.println("第" + i + "次：" + subcount);
            count += subcount;
            subcount = 0;
        }
        return count;
    }
}

// summarize the conditions, count the numbers of people of the same age
class Solution {
    public int numFriendRequests(int[] ages) {
        int res = 0;
        int[] numInAge = new int[121], sumInAge = new int[121];

        for(int i : ages)
            numInAge[i] ++;
        // sumInAge表示从1岁到这个年龄一共有多少人
        for(int i = 1; i <= 120; ++i)
            sumInAge[i] = numInAge[i] + sumInAge[i - 1];
       // A > 0.5 * A + 7 -> A>=15
        for(int i = 15; i <= 120; ++i) {
            if(numInAge[i] == 0) continue;
            int count = sumInAge[i] - sumInAge[i / 2 + 7];
            res += count * numInAge[i] - numInAge[i]; //people will not friend request themselves, so  - numInAge[i]
        }
        return res;
    }
}
// general direct Solution
// count the number of the certain aged people is faster than check every people's age
class Solution {
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int age: ages) count[age]++;

        int ans = 0;
        for (int ageA = 0; ageA <= 120; ageA++) {
            int countA = count[ageA];
            for (int ageB = 0; ageB <= 120; ageB++) {
                int countB = count[ageB];
                if (ageA * 0.5 + 7 >= ageB) continue;
                if (ageA < ageB) continue;
                if (ageA < 100 && 100 < ageB) continue;
                ans += countA * countB;
                if (ageA == ageB) ans -= countA;
            }
        }

        return ans;
    }
}
