// my solution
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int size = s.length();
        for(int i = 1; i <= size/2; i++){
            // i is the length of the substring
            if(size % i == 0){
                // 必须加上一个 count, 可能不是第一个字母就是循环的答案
                int count = 0;
                String subs = s.substring(0, i);
                for(int j = i; j < size; j = j + i){
                    String temp = s.substring(j, j+i);
                    // check whether the rest part of the string has the same pattern, use count to memorize
                    // if it is not a repeated pattern, the count will not be 0
                    if(! temp.equals(subs))
                        count++;
                }
                if(count == 0) return true;
            }
        }
        return false;
    }
}
// direct solution
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int size = s.length();
        for(int i = 1; i <= size/2; i++){
            if(size % i == 0){
                int m = size / i;
                String subs = s.substring(0, i);
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < m; j++){
                    sb.append(subs);
                }
                if(sb.toString().equals(s)) return true;
            }
        }
        return false;
    }
}

// a smart solution
public boolean repeatedSubstringPattern(String str) {
    String s = str + str;
    return s.substring(1, s.length() - 1).contains(str);
}
