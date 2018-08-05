// just simplely calculate the front and the last item is OK
// I considered too much things about choosing the bigger values which can also match the sum of the limit
// the essence of Greedy method is a step by step best choice
// every step we choose the best outcome
public int numRescueBoats(int[] people, int limit) {
    Arrays.sort(people);
    int ans = 0;
    for (int hi = people.length - 1, lo = 0; hi >= lo; --hi, ++ans) {
        if (people[lo] + people[hi] <= limit) { ++lo; }
    }
    return ans;
}
