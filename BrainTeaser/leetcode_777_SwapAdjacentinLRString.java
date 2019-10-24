/*
In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX",
or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end,
 return True if and only if there exists a sequence of moves to transform one string to the other.
*/


// a very direct solution
// R can only move rightward, therefore, if (ch1[p1] == 'R' && p1 > p2) return false;
// L can only move leftward, therefore, if (ch1[p1] == 'L' && p1 < p2) return false;
class Solution {
    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) return false;        // Add this one to make sure their length is same.
        if (!start.replace("X", "").equals(end.replace("X", ""))) return false;
        int p1 = 0;
        int p2 = 0;
        char[] ch1 = start.toCharArray();
        char[] ch2 = end.toCharArray();
        while (p1 < start.length()) {
            while (p1 < ch1.length && ch1[p1] == 'X') p1++;
            while (p2 < ch2.length && ch2[p2] == 'X') p2++;
            if (p1 == ch1.length || p2 == ch2.length) return true;      // if one reach the end, the other one must reach the end too, since we have already check their order first.
            if (ch1[p1] == 'R' && p1 > p2) return false;
            if (ch1[p1] == 'L' && p1 < p2) return false;
            p1++;
            p2++;
        }

        return true;
    }
}

// a better solution, same thing, use bit manipulation to make it faster
class Solution {
    public boolean canTransform(String start, String end) {
        int N = start.length();
        char[] S = start.toCharArray(), T = end.toCharArray();
        int i = -1, j = -1;
        while (++i < N && ++j < N) {
            while (i < N && S[i] == 'X') i++;
            while (j < N && T[j] == 'X') j++;
            /* At this point, i == N or S[i] != 'X',
               and j == N or T[j] != 'X'.  i and j
               are the indices representing the next
               occurrences of non-X characters in S and T.
            */

            // If only one of i < N and j < N, then it isn't solid-
            // there's more people in one of the strings.
            if ((i < N) ^ (j < N)) return false;

            if (i < N && j < N) {
                // If the person isn't the same, it isn't solid.
                // Or, if the person moved backwards, it isn't accessible.
                if (S[i] != T[j] || (S[i] == 'L' && i < j) ||
                        (S[i] == 'R' && i > j) )
                    return false;
            }
        }
        return true;
    }
}
