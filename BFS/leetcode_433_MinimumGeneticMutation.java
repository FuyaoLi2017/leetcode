// 这个题目和127Word Ladder很相似
public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if(start.equals(end)) return 0;

        Set<String> bankSet = new HashSet<>();
        for(String b: bank) bankSet.add(b);

        char[] charSet = new char[]{'A', 'C', 'G', 'T'};

        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                String curr = queue.poll();
                if(curr.equals(end)) return level;

                char[] currArray = curr.toCharArray();
                for(int i = 0; i < currArray.length; i++) {
                    char old = currArray[i];  // store the original character in ith position
                    for(char c: charSet) { // traverse through A, G, C, T
                        currArray[i] = c;
                        String next = new String(currArray);
                        if(!visited.contains(next) && bankSet.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                     // restore the original value of the string
                     // because we need to try other characters in the current string
                    currArray[i] = old;
                }
            }
            level++;
        }
        return -1;
    }
}
